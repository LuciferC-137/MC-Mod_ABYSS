package wardentools.blockentity;

import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.entity.custom.ProtectorEntity;
import wardentools.items.ItemRegistry;
import wardentools.items.ProtectorHeartItem;

import java.util.Objects;

public class ProtectorInvokerBlockEntity extends BlockEntity implements TickableBlockEntity {
	public boolean protectorSuccessfullyInvoked = false;
	
	private final ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			ProtectorInvokerBlockEntity.this.setChanged();
			if (level == null) return;
			if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
            }
		}
	};
	private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);

	public ProtectorInvokerBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.PROTECTOR_INVOKER_BLOCK_ENTITY.get(), pos, state);
	}

	public void saveHealth(ProtectorEntity protector){
		this.saveHealth(protector.getHealth());

	}

	public void saveHealth(float health) {
		this.sendUpdate();
		if (!this.inventory.getStackInSlot(0).isEmpty()
				&& this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) {
			((ProtectorHeartItem)this.inventory.getStackInSlot(0).getItem())
					.saveHealth(this.inventory.getStackInSlot(0), health);
			if (((ProtectorHeartItem)this.inventory.getStackInSlot(0).getItem())
					.readHealth(this.inventory.getStackInSlot(0))<=0.0f) {
				this.inventory.getStackInSlot(0).shrink(1);
				this.inventory.setStackInSlot(0, new ItemStack(ItemRegistry.DYING_PROTECTOR_HEART.get()));
			}
		}
	}

	private void sendUpdate() {
		this.setChanged();
		if (this.level != null) {
			this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap){
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return this.inventoryOptional.cast();
		}
		return super.getCapability(cap);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		this.inventoryOptional.invalidate();
	}


	@Override
	public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
		CompoundTag nbt = super.getUpdateTag(provider);
		this.saveAdditional(nbt, provider);
		return nbt;
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket(){return ClientboundBlockEntityDataPacket.create(this);}



	@Override
	protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
		super.loadAdditional(tag, provider);
		if (tag.isEmpty()) return;
		if (tag.contains("Inventory", Tag.TAG_COMPOUND)) {
			this.inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
		}
		if (tag.contains("ProtectorSuccessfullyInvoked", Tag.TAG_BYTE)) {
			this.protectorSuccessfullyInvoked = tag.getBoolean("ProtectorSuccessfullyInvoked");
		}
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
		super.saveAdditional(tag, provider);
		tag.put("Inventory", this.inventory.serializeNBT(provider));
		tag.putBoolean("ProtectorSuccessfullyInvoked", protectorSuccessfullyInvoked);
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}
	
	public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }

	
	public boolean isProtectorValid(ProtectorEntity protector) {
		if (!this.inventory.getStackInSlot(0).isEmpty()) {
			if (heartItem() == null){
				return false;
			}
			if (Objects.requireNonNull(heartItem())
					.getProtectorID(this.inventory.getStackInSlot(0)) == protector.getId()){
				return true;
			}
			if (Objects.requireNonNull(heartItem()).getProtectorUUID(heartStack()) == null) {
				return false;
			}
			if (Objects.equals(Objects.requireNonNull(heartItem())
					.getProtectorUUID(this.inventory.getStackInSlot(0)), protector.getUUID())) {
				Objects.requireNonNull(heartItem()).setProtector(this.inventory.getStackInSlot(0), protector);
				return true;
			}
		}
		return false;
	}

	public @Nullable ProtectorHeartItem heartItem() {
		if (this.inventory.getStackInSlot(0).isEmpty()) return null;
		if (!this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) return null;
		return (ProtectorHeartItem)this.inventory.getStackInSlot(0).getItem();
	}

	public @Nullable ItemStack heartStack() {
		if (this.inventory.getStackInSlot(0).isEmpty()) return null;
		if (!this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) return null;
		return this.inventory.getStackInSlot(0);
	}
	
	public String healthText() {
		if (this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) {
			return ((ProtectorHeartItem)this.inventory.getStackInSlot(0)
					.getItem()).getTextHealth(this.inventory.getStackInSlot(0));
		} else {
			return "--/--";
		}
		
	}

	@Override
	public void tick() {
	}
}
