package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.gui.menu.DysfunctionningCatalystMenu;
import wardentools.items.ItemRegistry;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionExplosion;

import java.util.List;

public class DysfunctionningCatalystBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            DysfunctionningCatalystBlockEntity.this.setChanged();
        }
    };
    private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);
    private static final Component TITLE =
            Component.translatable("container." + ModMain.MOD_ID + ".dysfunctionning_catalyst");
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> DysfunctionningCatalystBlockEntity.this.citrine;
                case 1 -> DysfunctionningCatalystBlockEntity.this.amethyst;
                case 2 -> DysfunctionningCatalystBlockEntity.this.pale_shard;
                case 3 -> DysfunctionningCatalystBlockEntity.this.ruby;
                case 4 -> DysfunctionningCatalystBlockEntity.this.malachite;
                case 5 -> DysfunctionningCatalystBlockEntity.this.echo_shard;
                case 6 -> DysfunctionningCatalystBlockEntity.this.total_charge;
                case 7 -> DysfunctionningCatalystBlockEntity.this.eye_progression;
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
        }
        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> DysfunctionningCatalystBlockEntity.this.citrine = value;
                case 1 -> DysfunctionningCatalystBlockEntity.this.amethyst = value;
                case 2 -> DysfunctionningCatalystBlockEntity.this.pale_shard = value;
                case 3 -> DysfunctionningCatalystBlockEntity.this.ruby = value;
                case 4 -> DysfunctionningCatalystBlockEntity.this.malachite = value;
                case 5 -> DysfunctionningCatalystBlockEntity.this.echo_shard = value;
                case 6 -> DysfunctionningCatalystBlockEntity.this.total_charge = value;
                case 7 -> DysfunctionningCatalystBlockEntity.this.eye_progression = value;
                default -> throw new IllegalStateException("Unexpected value: " + index);
            }
        }
        @Override
        public int getCount() {
            return 8;
        }
    };
    public static final int MAX_PROGRESSION = 28;
    public static final int MAX_TOTAL = 72;
    public static final int MAX_EYE = 40;
    private static final int UPDATE_INTERVAL = 5;
    private int citrine = 0;
    private int amethyst = 0;
    private int pale_shard = 0;
    private int ruby = 0;
    private int malachite = 0;
    private int echo_shard = 0;
    private int  total_charge = 0;
    private int eye_progression = 0;
    private int next_check = 0;

    protected DysfunctionningCatalystBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DysfunctionningCatalystBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        var wardentoolsData = nbt.getCompound(ModMain.MOD_ID);
        if (wardentoolsData.isEmpty()) return;
        if (wardentoolsData.contains("Inventory", Tag.TAG_COMPOUND)) {
            this.inventory.deserializeNBT(wardentoolsData.getCompound("Inventory"));
        }
        this.citrine = wardentoolsData.getInt("Citrine");
        this.amethyst = wardentoolsData.getInt("Amethyst");
        this.pale_shard = wardentoolsData.getInt("PaleShard");
        this.ruby = wardentoolsData.getInt("Ruby");
        this.malachite = wardentoolsData.getInt("Malachite");
        this.echo_shard = wardentoolsData.getInt("EchoShard");
        this.total_charge = wardentoolsData.getInt("TotalCharge");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        var wardentoolsData = new CompoundTag();
        wardentoolsData.put("Inventory", this.inventory.serializeNBT());
        wardentoolsData.putInt("Citrine", this.citrine);
        wardentoolsData.putInt("Amethyst", this.amethyst);
        wardentoolsData.putInt("PaleShard", this.pale_shard);
        wardentoolsData.putInt("Ruby", this.ruby);
        wardentoolsData.putInt("Malachite", this.malachite);
        wardentoolsData.putInt("EchoShard", this.echo_shard);
        wardentoolsData.putInt("TotalCharge", this.total_charge);
        nbt.put(ModMain.MOD_ID, wardentoolsData);
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            sendUpdate();
            if (this.next_check <= 0 && this.total_charge != MAX_TOTAL) {
                this.next_check = UPDATE_INTERVAL;
                if (this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())
                        && this.citrine < MAX_PROGRESSION) {
                    this.citrine +=1;
                    this.sendUpdate();
                } else if (this.citrine > 0
                        && !this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())) {
                    this.citrine -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)
                        && this.amethyst < MAX_PROGRESSION) {
                    this.amethyst +=1;
                    this.sendUpdate();
                } else if (this.amethyst > 0
                        && !this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)) {
                    this.amethyst -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())
                        && this.pale_shard < MAX_PROGRESSION) {
                    this.pale_shard +=1;
                    this.sendUpdate();
                } else if (this.pale_shard > 0
                        && !this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())) {
                    this.pale_shard -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())
                        && this.ruby < MAX_PROGRESSION) {
                    this.ruby +=1;
                    this.sendUpdate();
                } else if (this.ruby > 0
                        && !this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())) {
                    this.ruby -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())
                        && this.malachite < MAX_PROGRESSION) {
                    this.malachite +=1;
                    this.sendUpdate();
                } else if (this.malachite > 0
                        && !this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())) {
                    this.malachite -= 1;
                    this.sendUpdate();
                }
                if (this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)
                        && this.echo_shard < MAX_PROGRESSION) {
                    this.echo_shard +=1;
                    this.sendUpdate();
                } else if (this.echo_shard > 0
                        && !this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)) {
                    this.echo_shard -= 1;
                    this.sendUpdate();
                }
            } else {
                this.next_check--;
            }
            if (this.crystalsFullyCharged() && this.total_charge < MAX_TOTAL) {
                this.total_charge++;
                if (this.readyToSummon()) {
                    this.doSummoning();
                }
            } else if (this.total_charge > 0 && !this.crystalsFullyCharged() && this.total_charge < MAX_TOTAL) {
                this.total_charge--;
            }
            if (total_charge == MAX_TOTAL && this.eye_progression < MAX_EYE) {
                this.eye_progression ++;
            } else if (eye_progression > 0 && total_charge < MAX_TOTAL) {
                this.eye_progression --; // this should normally never happen.
            }
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap){
        return this.inventoryOptional.cast();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.inventoryOptional.invalidate();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory,
                                            @NotNull Player player) {
        return new DysfunctionningCatalystMenu(
                containerId, playerInventory, this, this.containerData);
    }

    private void sendUpdate() {
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public boolean crystalsFullyCharged() {
        return (this.citrine == MAX_PROGRESSION && this.amethyst == MAX_PROGRESSION
                && this.pale_shard == MAX_PROGRESSION && this.ruby == MAX_PROGRESSION
                && this.malachite == MAX_PROGRESSION && this.echo_shard == MAX_PROGRESSION);
    }

    public boolean isChargingCrystals() {
        return ( (this.inventory.getStackInSlot(0).is(ItemRegistry.CITRINE_FRAGMENT.get())
                  && this.citrine < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(1).is(Items.AMETHYST_SHARD)
                    && this.amethyst < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(2).is(ItemRegistry.PALE_SHARD.get())
                    && this.pale_shard < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(3).is(ItemRegistry.RUBY_FRAGMENT.get())
                    && this.ruby < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(4).is(ItemRegistry.MALACHITE_FRAGMENT.get())
                    && this.malachite < MAX_PROGRESSION)
                || (this.inventory.getStackInSlot(5).is(Items.ECHO_SHARD)
                    && this.echo_shard < MAX_PROGRESSION));
    }

    public boolean isChargingTotal() {
        return (this.total_charge < MAX_TOTAL && this.crystalsFullyCharged());
    }

    public boolean readyToSummon() {
        return (this.total_charge == MAX_TOTAL);
    }

    public boolean isFightActive() {
        return this.total_charge == MAX_TOTAL;
    }

    public void doSummoning() {
        this.replaceLiquidCorruptionBlock();
        this.hugeParticleExplosion();
    }

    public void replaceLiquidCorruptionBlock() {
        if (this.level != null) {
            for (BlockPos pos : getFountainBelowPositions()) {
                this.level.setBlockAndUpdate(pos, BlockRegistry.LIQUID_CORRUPTION_BLOCK.get().defaultBlockState());
            }
        }
    }

    public void hugeParticleExplosion() {
        PacketHandler.sendToAllClient(new ParticleContagionExplosion(
                this.worldPosition.getCenter(), 1f, 2f, 800));
    }

    public List<BlockPos> getFountainBelowPositions() {
        return List.of(
                this.worldPosition.offset(0, -12, 0),
                this.worldPosition.offset(1,-12,0),
                this.worldPosition.offset(0,-12,1),
                this.worldPosition.offset(1,-12,1),
                this.worldPosition.offset(2, -12, 0),
                this.worldPosition.offset(2,-12,1),
                this.worldPosition.offset(2,-12,2),
                this.worldPosition.offset(1, -12, 2),
                this.worldPosition.offset(0, -12, 2),
                this.worldPosition.offset(3, -12, 0),
                this.worldPosition.offset(3,-12,1),
                this.worldPosition.offset(3,-12,2),
                this.worldPosition.offset(3, -12, 3),
                this.worldPosition.offset(2,-12,3),
                this.worldPosition.offset(1,-12,3),
                this.worldPosition.offset(0, -12, 3),
                this.worldPosition.offset(4,-12,0),
                this.worldPosition.offset(4,-12,1),
                this.worldPosition.offset(4, -12, 2),
                this.worldPosition.offset(1,-12,0),
                this.worldPosition.offset(0,-12,1),
                this.worldPosition.offset(1,-12,1),
                this.worldPosition.offset(2, -12, 0),
                this.worldPosition.offset(2,-12,1),
                this.worldPosition.offset(2,-12,2),
                this.worldPosition.offset(1, -12, 2),
                this.worldPosition.offset(3, -12, 0),
                this.worldPosition.offset(3,-12,1),
                this.worldPosition.offset(3,-12,2),
                this.worldPosition.offset(3, -12, 3),
                this.worldPosition.offset(2,-12,3),
                this.worldPosition.offset(1,-12,3),
                this.worldPosition.offset(0, -12, 3),
                this.worldPosition.offset(4,-12,0),
                this.worldPosition.offset(4,-12,1),
                this.worldPosition.offset(4, -12, 2),
                this.worldPosition.offset(2, -12, 4),
                this.worldPosition.offset(1, -12, 4),
                this.worldPosition.offset(0, -12, 4),
                this.worldPosition.offset(-1,-12,0),
                this.worldPosition.offset(0,-12,-1),
                this.worldPosition.offset(-1,-12,-1),
                this.worldPosition.offset(-2, -12, 0),
                this.worldPosition.offset(-2,-12,-1),
                this.worldPosition.offset(-2,-12,-2),
                this.worldPosition.offset(-1, -12, -2),
                this.worldPosition.offset(0, -12, -2),
                this.worldPosition.offset(-3, -12, 0),
                this.worldPosition.offset(-3,-12,-1),
                this.worldPosition.offset(-3,-12,-2),
                this.worldPosition.offset(-3, -12, -3),
                this.worldPosition.offset(-2,-12,-3),
                this.worldPosition.offset(-1,-12,-3),
                this.worldPosition.offset(0, -12, -3),
                this.worldPosition.offset(-4,-12,0),
                this.worldPosition.offset(-4,-12,-1),
                this.worldPosition.offset(-4, -12, -2),
                this.worldPosition.offset(-2, -12, -4),
                this.worldPosition.offset(-1, -12, -4),
                this.worldPosition.offset(0, -12, -4),
                this.worldPosition.offset(-1,-12,1),
                this.worldPosition.offset(-2,-12,1),
                this.worldPosition.offset(-2,-12,2),
                this.worldPosition.offset(-1, -12, 2),
                this.worldPosition.offset(-3,-12,1),
                this.worldPosition.offset(-3,-12,2),
                this.worldPosition.offset(-3, -12, 3),
                this.worldPosition.offset(-2,-12,3),
                this.worldPosition.offset(-1,-12,3),
                this.worldPosition.offset(-4,-12,1),
                this.worldPosition.offset(-4, -12, 2),
                this.worldPosition.offset(-2, -12, 4),
                this.worldPosition.offset(-1, -12, 4),
                this.worldPosition.offset(1,-12,-1),
                this.worldPosition.offset(2,-12,-1),
                this.worldPosition.offset(2,-12,-2),
                this.worldPosition.offset(1, -12, -2),
                this.worldPosition.offset(3,-12,-1),
                this.worldPosition.offset(3,-12,-2),
                this.worldPosition.offset(3, -12, -3),
                this.worldPosition.offset(2,-12,-3),
                this.worldPosition.offset(1,-12,-3),
                this.worldPosition.offset(4,-12,-1),
                this.worldPosition.offset(4, -12, -2),
                this.worldPosition.offset(2, -12, -4),
                this.worldPosition.offset(1, -12, -4)
        );
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }
}
