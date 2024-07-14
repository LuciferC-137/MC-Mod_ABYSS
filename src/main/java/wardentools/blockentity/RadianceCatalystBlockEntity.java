package wardentools.blockentity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import wardentools.ModMain;

public class RadianceCatalystBlockEntity extends BlockEntity {
	private int ticks = 0, secondsExisted = 0;
	private final ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			RadianceCatalystBlockEntity.this.setChanged();
		}
	};
	
	private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

	public RadianceCatalystBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.RADIANCE_CATALYST_BLOCK_ENTITY.get(), pos, state);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		var wardentoolsData = nbt.getCompound(ModMain.MOD_ID);
		this.secondsExisted = wardentoolsData.getInt("SecondsExisted");
		this.inventory.deserializeNBT(wardentoolsData.getCompound("Inventory"));
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		var wardentoolsData = new CompoundTag();
		wardentoolsData.put("Inventory", this.inventory.serializeNBT());
		wardentoolsData.putInt("SecondsExisted", this.secondsExisted);
		nbt.put(ModMain.MOD_ID, wardentoolsData);
	}
	
	public void tick() {
		if (this.level == null || this.level.isClientSide()) {
			return;
		}
		if (this.ticks++ % 20 == 0) {
			this.secondsExisted++;
			setChanged();
			this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}
	
	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap){
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return this.optional.cast();
		}
		return super.getCapability(cap);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}
	public int getSecondsExisted() {
		return this.secondsExisted;
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

}
