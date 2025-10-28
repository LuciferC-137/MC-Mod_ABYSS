package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.block.BlockRegistry;
import wardentools.block.CrystalInfuserBlock;
import wardentools.items.CrystalResonatorItem;
import wardentools.items.ItemRegistry;
import wardentools.misc.Crystal;
import wardentools.sounds.ModSounds;
import wardentools.worldgen.structure.StructureUtils;

public class CrystalInfuserBlockEntity extends BlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            if (level != null && !level.isClientSide) {
                CrystalInfuserBlockEntity.this.sendUpdate();
            }
        }
    };

    private static final int INFUSING_TIME = 120;
    private boolean isInfusing = false;
    public boolean hasEmitedParticles = false;

    private BlockPos cachedStainedGlassCenterPos = null;
    private float nextTempleOrientation = -4F;

    private static final int COMPASS_EFFECT_DURATION = 2400;
    private int compassEffectTick = 0;


    protected CrystalInfuserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CrystalInfuserBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.CRYSTAL_INFUSER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("inventory", this.inventory.serializeNBT(provider));
        tag.putBoolean("is_infusing", this.isInfusing);
        tag.putFloat("next_temple_orientation", this.nextTempleOrientation);
        tag.putInt("compass_effect_tick", this.compassEffectTick);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("inventory", Tag.TAG_COMPOUND)) {
            for (int i = 0; i < this.inventory.getSlots(); i++) {
                this.inventory.setStackInSlot(i, ItemStack.EMPTY);
            }
            this.inventory.deserializeNBT(provider, tag.getCompound("inventory"));
            }
        if (tag.contains("is_infusing", Tag.TAG_BYTE)) {
            this.isInfusing = tag.getBoolean("is_infusing");
        }
        if (tag.contains("next_temple_orientation", Tag.TAG_FLOAT)) {
            this.nextTempleOrientation = tag.getFloat("next_temple_orientation");
        }
        if (tag.contains("compass_effect_tick", Tag.TAG_INT)) {
            this.compassEffectTick = tag.getInt("compass_effect_tick");
        }
    }

    public void triggerCompassEffect() {
        this.compassEffectTick = COMPASS_EFFECT_DURATION;
    }

    public void tick() {
        if (this.compassEffectTick > 0) {
            this.compassEffectTick--;
        }
    }

    public void clientTick() {
        BlockState state = this.getBlockState();
        if (this.level == null || !state.is(BlockRegistry.CRYSTAL_INFUSER.get())) return;
        if (this.isInfusing()) {
            CrystalInfuserBlock.particleShine(state, this.worldPosition, level, level.random);
            if (!this.hasEmitedParticles) {
                level.playLocalSound(this.worldPosition,
                        ModSounds.INFUSER_CHARGING.get(),
                        SoundSource.BLOCKS,
                        1.0F, 1.0F, true);
                CrystalInfuserBlock.particleGlyph(state, this.worldPosition, level);
                this.hasEmitedParticles = true;
            }
        } else {
            this.hasEmitedParticles = false;
        }
        if (this.compassEffectTick > 0) {
            this.compassEffectTick--;
            CrystalInfuserBlock.giantCompassParticle(state, level, this);
            CrystalInfuserBlock.ambientParticles(state, this.worldPosition, level);
        }
    }

    public boolean setItemInAvailableSlot(@NotNull ItemStack stack) {
        if (this.isInfusing) return false;
        if (Crystal.isCrystalItem(stack.getItem())) {
            for (int i = 0; i < this.inventory.getSlots() - 1; i++) {
                if (this.inventory.getStackInSlot(i).isEmpty()) {
                    ItemStack copy = stack.copy();
                    copy.setCount(1);
                    this.inventory.setStackInSlot(i, copy);
                    stack.shrink(1);
                    this.beginInfuseIfAble();
                    return true;
                }
            }
        } else if (stack.is(ItemRegistry.CRYSTAL_RESONATOR.get())) {
            if (this.inventory.getStackInSlot(4).isEmpty()) {
                ItemStack copy = stack.copy();
                copy.setCount(1);
                this.inventory.setStackInSlot(4, copy);
                stack.shrink(1);
                this.beginInfuseIfAble();
                return true;
            }
        }
        return false;
    }

    public ItemStack removeItemInOrder() {
        if (this.isInfusing) return ItemStack.EMPTY;
        for (int i = this.inventory.getSlots() - 1; i >= 0; i--) {
            if (!this.inventory.getStackInSlot(i).isEmpty()) {
                ItemStack stack = this.inventory.getStackInSlot(i).copy();
                this.inventory.setStackInSlot(i, ItemStack.EMPTY);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public void beginInfuseIfAble() {
        if (this.isInfusing) return;
        ItemStack resonator = this.inventory.getStackInSlot(4);
        if (resonator.isEmpty() || !resonator.is(ItemRegistry.CRYSTAL_RESONATOR.get())) return;
        ItemStack infusingCrystal = this.inventory.getStackInSlot(0);
        if (infusingCrystal.isEmpty()) return;
        for (int i = 1; i < 4; i++) {
            ItemStack slotStack = this.inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) return;
            if (!slotStack.is(infusingCrystal.getItem())) return;
        }
        if (this.getBlockState().getValue(CrystalInfuserBlock.CRYSTAL)
                != Crystal.fromItem(infusingCrystal.getItem())) {
            return;
        }
        if (this.level == null) return;
        this.level.scheduleTick(this.worldPosition, this.getBlockState().getBlock(), INFUSING_TIME);
        this.isInfusing = true;
        this.getNextTempleOrientation(); // precompute next temple orientation for client
    }

    public void completeInfuse() {
        if (!this.isInfusing) return;
        ItemStack resonator = this.inventory.getStackInSlot(4);
        if (resonator.isEmpty() || !resonator.is(ItemRegistry.CRYSTAL_RESONATOR.get())) {
            this.isInfusing = false;
            return;
        }
        ItemStack infusingCrystal = this.inventory.getStackInSlot(0);
        if (infusingCrystal.isEmpty()) {
            this.isInfusing = false;
            return;
        }
        Crystal crystal = Crystal.fromItem(infusingCrystal.getItem());
        CrystalResonatorItem.setCrystal(resonator, crystal);
        for (int i = 0; i < 4; i++) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
        this.inventory.setStackInSlot(4, resonator);
        this.isInfusing = false;
        this.triggerCompassEffect();
        if (this.level != null) this.level.scheduleTick(this.worldPosition,
                this.getBlockState().getBlock(), 1);
        this.sendUpdate();
    }

    public BlockPos getStainedGlassCenterPos() {
        if (this.cachedStainedGlassCenterPos == null) {
            if (this.getBlockState().hasProperty(CrystalInfuserBlock.FACING)) {
                this.cachedStainedGlassCenterPos =
                        this.worldPosition.above(9)
                                .relative(this.getBlockState()
                                        .getValue(CrystalInfuserBlock.FACING), 4);
            } else {
                this.cachedStainedGlassCenterPos = this.worldPosition.above(9);
            }
        }
        return this.cachedStainedGlassCenterPos;
    }

    public float getNextTempleOrientation() {
        if (this.level == null) return 0F;
        if (this.nextTempleOrientation == -4F) {
            if (!this.level.isClientSide && this.getBlockState()
                    .hasProperty(CrystalInfuserBlock.CRYSTAL)) {
                ResourceKey<Structure> templeKey = this.getBlockState()
                        .getValue(CrystalInfuserBlock.CRYSTAL).getTempleKey();
                BlockPos nexTemplePos = StructureUtils.findNearestStructure((ServerLevel) this.level,
                        templeKey, this.worldPosition);
                if (nexTemplePos != null) {
                    Vec3 toTemple = Vec3.atCenterOf(nexTemplePos)
                            .subtract(Vec3.atCenterOf(this.worldPosition));
                    this.nextTempleOrientation = (float) Math.atan2(toTemple.z, toTemple.x);
                }
            }
            this.sendUpdate();
        }
        return this.nextTempleOrientation;
    }

    public boolean isInfusing() {
        return this.isInfusing;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void sendUpdate() {
        this.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

}
