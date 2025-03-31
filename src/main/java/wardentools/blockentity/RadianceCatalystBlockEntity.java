package wardentools.blockentity;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.neoforged.neoforge.items.ItemStackHandler;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import wardentools.ModMain;
import wardentools.gui.menu.RadianceCatalystMenu;
import wardentools.blockentity.util.CustomEnergyStorage;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.items.ItemRegistry;
import wardentools.network.ModPackets;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystCharging;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystCharged;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystPurifying;
import wardentools.particle.ParticleRegistry;


public class RadianceCatalystBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
	public static final ParticleOptions PARTICLE = ParticleRegistry.RADIANCE.get(); //Used by the packets
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

	private final CustomEnergyStorage energy = new CustomEnergyStorage(1000, 0,
			100, 0); //capacity, maxReceive, maxExtract, defaultEnergy

	private int burnTime, maxBurnTime = 0;
	public final static int purifyTime = 200;
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
	protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
		super.loadAdditional(tag, provider);
		if (tag.isEmpty()) return;
		if (tag.contains("Inventory", Tag.TAG_COMPOUND)) {
			this.inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
		}
		if (tag.contains("Energy", Tag.TAG_INT)) {
			this.energy.setEnergy(tag.getInt("Energy"));
		}
		if (tag.contains("BurnTime", Tag.TAG_INT)) {
			this.burnTime = tag.getInt("BurnTime");
		}
		if (tag.contains("MaxBurnTime", Tag.TAG_INT)) {
			this.maxBurnTime = tag.getInt("MaxBurnTime");
		}
		if (tag.contains("PurifyingTime", Tag.TAG_INT)) {
			this.purifyingTime = tag.getInt("PurifyingTime");
		}
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
		super.saveAdditional(tag, provider);
		tag.put("Inventory", this.inventory.serializeNBT(provider));
		tag.putInt("Energy", this.energy.getEnergyStored());
		tag.putInt("BurnTime", this.burnTime);
		tag.putInt("MaxBurnTime", this.maxBurnTime);
		tag.putInt("PurifyingTime", this.purifyingTime);
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
					ModPackets.sendToAllClient(new ParticleRadianceCatalystCharging(this.getBlockPos()));
					this.burnTime--;
					this.energy.addEnergy(1);
					sendUpdate();
				}
			} else {
				// is charged
				if (this.purifyingTime > 0 && this.purifyingTime < purifyTime) {
					
					if (!canPurify(this.inventory.getStackInSlot(1))){
						this.purifyingTime = 0;
						sendUpdate();
					} else {
						ModPackets.sendToAllClient(new ParticleRadianceCatalystPurifying(this.getBlockPos()));
						this.purifyingTime++;
						if (this.purifyingTime>=purifyTime) {
							this.purifyingTime = 0;
							if (this.inventory.getStackInSlot(2).isEmpty()) {
								this.energy.setEnergy(this.energy.getEnergyStored() -
										energyCost(this.inventory.getStackInSlot(1)));
								if (getPurifiedVersion(this.inventory.getStackInSlot(1))!=null) {
									this.inventory.setStackInSlot(2,
											getPurifiedVersion(this.inventory.getStackInSlot(1)));
									this.inventory.getStackInSlot(1).shrink(1);
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
					this.purifyingTime++;
					sendUpdate();
				} else if (this.tickFractionner%5==1){
					ModPackets.sendToAllClient(new ParticleRadianceCatalystCharged(this.getBlockPos()));
				}
			}
		}
	}

	@Override
	public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
		CompoundTag nbt = super.getUpdateTag(provider);
		saveAdditional(nbt, provider);
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
		return new RadianceCatalystMenu(containerId, playerInventory, this, this.containerData);
	}
	
	private void sendUpdate() {
		setChanged();
		if (this.level != null) {
			this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	public @NotNull Component getDisplayName() {
		return TITLE;
	}
	
	public int getBurnTime(@NotNull ItemStack stack) {
		if (stack.is(ItemRegistry.RADIANCE_FRAGMENT.get())) {
			return 51;
		} else if (stack.is(ItemRegistry.RADIANCE_CRISTAL.get())) {
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
		} else if (stack.is(ItemRegistry.DYING_PROTECTOR_HEART.get())) {
			return new ItemStack(ItemRegistry.PROTECTOR_HEART.get());
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
		} else if (stack.is(ItemRegistry.DYING_PROTECTOR_HEART.get())) {
			return 600;
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
	
	public CustomEnergyStorage getEnergy() {
		return this.energy;
	}
	
	public void setEnergy(int energy) {
        this.energy.setEnergy(energy);
    }
	
	public int getPurifyingTime() {
		return this.purifyingTime;
	}
	
}
