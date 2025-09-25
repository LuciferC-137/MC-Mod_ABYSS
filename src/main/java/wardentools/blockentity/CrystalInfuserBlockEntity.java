package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.block.CrystalInfuserBlock;
import wardentools.items.CrystalResonatorItem;
import wardentools.items.ItemRegistry;
import wardentools.misc.Crystal;

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

    private boolean isInfusing = false;

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
        this.level.scheduleTick(this.worldPosition, this.getBlockState().getBlock(), 120);
        this.isInfusing = true;
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
        if (this.level != null) this.level.scheduleTick(this.worldPosition,
                this.getBlockState().getBlock(), 1);
        this.sendUpdate();
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
