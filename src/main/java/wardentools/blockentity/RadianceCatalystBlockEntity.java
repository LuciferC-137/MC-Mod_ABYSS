package wardentools.blockentity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import wardentools.ModMain;
import wardentools.GUI.menu.RadianceCatalystMenu;
import wardentools.blockentity.util.CustomEnergyStorage;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.items.ItemRegistry;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystCharging;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystCharged;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystPurifying;

public class RadianceCatalystBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
	private static final Component TITLE =
			Component.translatable("container." + ModMain.MOD_ID + ".radiance_catalyst_block");
	private final ItemStackHandler inventory = new ItemStackHandler(3) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			RadianceCatalystBlockEntity.this.setChanged();
		}
	};
	private int tickFractionner = 0;
	private double rotationSpeed = 3.0;
	private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);
	
	private final CustomEnergyStorage energy = new CustomEnergyStorage(1000, 0, 100, 0); //capacity, maxReceive, maxExtract, defaultEnergy
	private final LazyOptional<CustomEnergyStorage> energyOptional = LazyOptional.of(() -> this.energy);
	
	private int burnTime, maxBurnTime = 0;
	private final static int purifyTime = 200; // WARNING this is not synched with the screen
	private int purifyingTime = 0;
	
	private final ContainerData containerData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
			case 0 -> RadianceCatalystBlockEntity.this.energy.getEnergyStored();
			case 1 -> RadianceCatalystBlockEntity.this.energy.getMaxEnergyStored();
			case 2 -> RadianceCatalystBlockEntity.this.burnTime;
			case 3 -> RadianceCatalystBlockEntity.this.maxBurnTime;
			case 4 -> RadianceCatalystBlockEntity.this.purifyingTime;
			default -> throw new IllegalStateException("Unexpected value: " + index);
			};
		}
		
		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0 -> RadianceCatalystBlockEntity.this.energy.setEnergy(value);
				case 2 -> RadianceCatalystBlockEntity.this.burnTime = value;
				case 3 -> RadianceCatalystBlockEntity.this.maxBurnTime = value;
				case 4 -> RadianceCatalystBlockEntity.this.purifyingTime = value;
			}
		}
		
		@Override
		public int getCount() {
			return 5;
		}
	};

	public RadianceCatalystBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.RADIANCE_CATALYST_BLOCK_ENTITY.get(), pos, state);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		var wardentoolsData = nbt.getCompound(ModMain.MOD_ID);
		if (wardentoolsData.isEmpty()) return;
		if (wardentoolsData.contains("Inventory", Tag.TAG_COMPOUND)) {
			this.inventory.deserializeNBT(wardentoolsData.getCompound("Inventory"));
		}
		if (wardentoolsData.contains("Energy", Tag.TAG_INT)) {
			this.energy.setEnergy(wardentoolsData.getInt("Energy"));
		}
		if (wardentoolsData.contains("BurnTime", Tag.TAG_INT)) {
			this.burnTime = wardentoolsData.getInt("BurnTime");
		}
		if (wardentoolsData.contains("MaxBurnTime", Tag.TAG_INT)) {
			this.maxBurnTime = wardentoolsData.getInt("MaxBurnTime");
		}
		if (wardentoolsData.contains("PurifyingTime", Tag.TAG_INT)) {
			this.purifyingTime = wardentoolsData.getInt("PurifyingTime");
		}
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		var wardentoolsData = new CompoundTag();
		wardentoolsData.put("Inventory", this.inventory.serializeNBT());
		wardentoolsData.putInt("Energy", this.energy.getEnergyStored());
		wardentoolsData.putInt("BurnTime", this.burnTime);
		wardentoolsData.putInt("MaxBurnTime", this.maxBurnTime);
		wardentoolsData.putInt("PurifyingTime", this.purifyingTime);
		nbt.put(ModMain.MOD_ID, wardentoolsData);
	}
	
	public void tick() {
		this.tickFractionner++;
		if (this.level != null && !this.level.isClientSide()) {
			if (this.energy.getEnergyStored() < this.energy.getMaxEnergyStored()) {
				if (this.burnTime <= 0) {
					if (canBurn(this.inventory.getStackInSlot(0))) {
						this.burnTime = this.maxBurnTime = getBurnTime(this.inventory.getStackInSlot(0));
						this.inventory.getStackInSlot(0).shrink(1);
						sendUpdate();
					}
				} else {
					// is burning
					PacketHandler.sendToAllClient(new ParticleRadianceCatalystCharging(this.getBlockPos()));
					this.burnTime--;
					this.energy.addEnergy(1);
					sendUpdate();
				}
			} else {
				// is charged
				if (this.purifyingTime > 0 && this.purifyingTime < purifyTime) {
					
					if (!canPurify(this.inventory.getStackInSlot(1))){
						this.rotationSpeed = 3.0;
						this.purifyingTime = 0;
						sendUpdate();
					} else {
						PacketHandler.sendToAllClient(new ParticleRadianceCatalystPurifying(this.getBlockPos()));
						this.purifyingTime++;
						if (this.purifyingTime>=purifyTime) {
							this.purifyingTime = 0;
							this.rotationSpeed = 3.0;
							if (this.inventory.getStackInSlot(2).isEmpty()) {
								this.energy.setEnergy(this.energy.getEnergyStored() -
										energyCost(this.inventory.getStackInSlot(1)));
								if (getPurifiedVersion(this.inventory.getStackInSlot(1))!=null) {
									this.inventory.setStackInSlot(2,
											getPurifiedVersion(this.inventory.getStackInSlot(1)));
									this.inventory.getStackInSlot(1).shrink(1);
								} else {
									
								}
								
							} else if (ItemStack.isSameItem(this.inventory.getStackInSlot(2),
											(getPurifiedVersion(this.inventory.getStackInSlot(1))))){
								this.energy.setEnergy(this.energy.getEnergyStored() -
										energyCost(this.inventory.getStackInSlot(1)));
								this.inventory.getStackInSlot(2).grow(1);
								this.inventory.getStackInSlot(1).shrink(1);
							}
						}
						sendUpdate();
					}
				} else if (canPurify(this.inventory.getStackInSlot(1))
						&& (this.inventory.getStackInSlot(2).isEmpty()
								||ItemStack.isSameItem(this.inventory.getStackInSlot(2),
										(getPurifiedVersion(this.inventory.getStackInSlot(1)))))){
					this.rotationSpeed = 6.0;
					this.purifyingTime++;
					sendUpdate();
				} else if (this.tickFractionner%5==1){
					PacketHandler.sendToAllClient(new ParticleRadianceCatalystCharged(this.getBlockPos()));
				}
			}
		}
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap){
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return this.inventoryOptional.cast();
		} else if (cap == ForgeCapabilities.ENERGY) {
			return this.energyOptional.cast();
		}
		return super.getCapability(cap);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		this.inventoryOptional.invalidate();
		this.energyOptional.invalidate();
	}
	
	@Override
	public CompoundTag getUpdateTag() {
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
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new RadianceCatalystMenu(containerId, playerInventory, this, this.containerData);
	}
	
	private void sendUpdate() {
		setChanged();
		if (this.level != null) {
			this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	public Component getDisplayName() {
		return TITLE;
	}
	
	public int getBurnTime(@NotNull ItemStack stack) {
		if (stack.is(ItemRegistry.PALE_FRAGMENT.get())) {
			return 51;
		} else if (stack.is(ItemRegistry.PALE_SHARD.get())) {
			return 251;
		}
		return 0;
	}
	
	public ItemStack getPurifiedVersion(ItemStack stack) {
		if (stack.is(ItemRegistry.CORRUPTED_ESSENCE.get())) {
			return new ItemStack(ItemRegistry.PURE_ESSENCE.get());
		} else if (stack.is(ItemRegistry.WARDEN_HEART.get())) {
			return new ItemStack(ItemRegistry.PROTECTOR_HEART.get());
		} else if (stack.is(ItemRegistry.CORRUPTED_VESSEL.get())) {
			return new ItemStack(ItemRegistry.PURE_VESSEL.get());
		}
		return null;
	}

	public int energyCost(ItemStack stack) {
		if (stack.is(ItemRegistry.CORRUPTED_ESSENCE.get())) {
			return 100;
		} else if (stack.is(ItemRegistry.WARDEN_HEART.get())) {
			return 900;
		} else if (stack.is(ItemRegistry.CORRUPTED_VESSEL.get())) {
			return 100;
		}
		return this.energy.getMaxEnergyStored();
	}

	public boolean canBurn(@NotNull ItemStack stack) {
		return getBurnTime(stack) > 0;
	}
	
	public boolean canPurify(@NotNull ItemStack stack) {
		return getPurifiedVersion(stack)!=null;
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}
	
	public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }
	
	public CustomEnergyStorage getEnergy() {
		return this.energy;
	}
	
	public void setEnergy(int energy) {
        this.energy.setEnergy(energy);
    }
	
	public LazyOptional<CustomEnergyStorage> getEnergyOptional() {
        return this.energyOptional;
    }
	
	public double getRotationSpeed() {
		return this.rotationSpeed;
	}
	
	
}
