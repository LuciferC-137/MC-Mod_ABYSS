package wardentools.blockentity;

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
import wardentools.ModMain;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.items.ItemRegistry;
import wardentools.items.ProtectorHeartItem;

public class ProtectorInvokerBlockEntity extends BlockEntity implements TickableBlockEntity {
	
	private final ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			ProtectorInvokerBlockEntity.this.setChanged();
			if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
            }
		}
	};
	private final LazyOptional<ItemStackHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);

	public ProtectorInvokerBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.PROTECTOR_INVOKER_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void tick() {
		if (!this.inventory.getStackInSlot(0).isEmpty()
				&& this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) {
			((ProtectorHeartItem)this.inventory.getStackInSlot(0).getItem())
				.saveHealth(level, this.inventory.getStackInSlot(0));
			this.setChanged();
			if (!level.isClientSide) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
            }
			if (((ProtectorHeartItem)this.inventory.getStackInSlot(0).getItem()).readHealth(
					this.inventory.getStackInSlot(0))<=0.0f) {
				this.inventory.getStackInSlot(0).shrink(1);
				this.inventory.setStackInSlot(0, new ItemStack(ItemRegistry.DYING_PROTECTOR_HEART.get()));
			}
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
	public void load(CompoundTag nbt) {
		super.load(nbt);
		var wardentoolsData = nbt.getCompound(ModMain.MOD_ID);
		if (wardentoolsData.isEmpty()) return;
		if (wardentoolsData.contains("Inventory", Tag.TAG_COMPOUND)) {
			this.inventory.deserializeNBT(wardentoolsData.getCompound("Inventory"));
		}
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		var wardentoolsData = new CompoundTag();
		wardentoolsData.put("Inventory", this.inventory.serializeNBT());
		nbt.put(ModMain.MOD_ID, wardentoolsData);
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}
	
	public LazyOptional<ItemStackHandler> getInventoryOptional() {
        return this.inventoryOptional;
    }
	
	public boolean isProtectorValid() {
		if (this.inventory.getStackInSlot(0)!=null) {
			return !this.inventory.getStackInSlot(0).isEmpty();
		}
		return false;
	}
	
	public String healthText() {
		if (this.inventory.getStackInSlot(0).is(ItemRegistry.PROTECTOR_HEART.get())) {
			return ((ProtectorHeartItem)this.inventory.getStackInSlot(0)
					.getItem()).getTextHealth(this.inventory.getStackInSlot(0));
		} else {
			return "--/--";
		}
		
	}
}
