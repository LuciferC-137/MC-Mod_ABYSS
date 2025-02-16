package wardentools.blockentity;

import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ProtectorEntity;
import wardentools.items.ItemRegistry;
import wardentools.items.ProtectorHeartItem;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceExplosion;

import java.util.Objects;

public class ProtectorInvokerBlockEntity extends BlockEntity implements TickableBlockEntity {
	public boolean protectorSuccessfullyInvoked = false;
	public static final int radiusForProtectorSpawn = 5;

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

	public InteractionResult playerInteract(Player player, BlockPos pos,
											InteractionHand interactionHand) {
		ItemStack heldItem = player.getItemInHand(interactionHand);
		if (level == null) return InteractionResult.FAIL;
		if (heldItem.is(ItemRegistry.PROTECTOR_HEART.get())
				&& this.getInventory().getStackInSlot(0).isEmpty()) {
			this.getInventory().setStackInSlot(0, heldItem.copy());
			heldItem.shrink(1);
			if (protectorWithThisInvokerExists(level, this)) {
				return InteractionResult.CONSUME;
			}
			trySpawnNewProtector(level, pos, this);
			if (!level.isClientSide) {
				ModCriteriaTriggers.SUMMON_PROTECTOR.trigger((ServerPlayer) player);
			}
			return InteractionResult.CONSUME;

		} else if (heldItem.isEmpty() && !this.getInventory().getStackInSlot(0).isEmpty()) {
			player.setItemInHand(interactionHand, this.getInventory().getStackInSlot(0).copy());
			this.getInventory().getStackInSlot(0).shrink(1);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.FAIL;
	}

	private static void trySpawnNewProtector(Level level,
									  BlockPos pos,
									  ProtectorInvokerBlockEntity invoker){
		if (level.isClientSide()) return;
		BlockPos spawnPos = findSpawnPosition(level, pos);
		if (spawnPos != null) {
			ProtectorEntity protec = ModEntities.PROTECTOR.get().create(level, EntitySpawnReason.SPAWNER);
			if (protec != null) {
				protec.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(),
						spawnPos.getZ() + 0.5, level.random.nextFloat() * 360F, 0);
				protec.setHealth(Objects.requireNonNull(invoker.heartItem()).readHealth(invoker.heartStack()));
				protec.setInvokerPos(pos);
				if (invoker.heartItem() != null) {
					Objects.requireNonNull(invoker.heartItem()).setProtector(invoker.heartStack(), protec);
				}
				protec.makeSpawnAnimation();
				Vec3 particleSource = spawnPos.above().getCenter();
				PacketHandler.sendToAllClient(new ParticleRadianceExplosion(particleSource));
				level.addFreshEntity(protec);
				invoker.protectorSuccessfullyInvoked = true;
			}
		}
	}

	public static boolean protectorWithThisInvokerExists(Level level, ProtectorInvokerBlockEntity invoker) {
		AABB searchBox = new AABB(invoker.getBlockPos()).inflate(100);
		return level.getEntitiesOfClass(ProtectorEntity.class, searchBox)
				.stream()
				.anyMatch(protector -> compareBlockPos(invoker.getBlockPos(), protector));
	}

	private static boolean compareBlockPos(BlockPos pos1, ProtectorEntity protector){
		if (protector.invokerPos == null) return false;
		BlockPos pos2 = protector.invokerPos;
		return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();
	}

	private static BlockPos findSpawnPosition(Level level, BlockPos invokerPos) {
		for (int r = 1; r <= radiusForProtectorSpawn; r++) {
			for (int dx = -r; dx <= r; dx++) {
				for (int dz = -r; dz <= r; dz++) {
					for (int dy = -2; dy <= 2; dy++) {
						if (Math.abs(dx) == r || Math.abs(dz) == r) {
							BlockPos pos = invokerPos.offset(dx, dy, dz);
							if (isSpaceAvailable(level, pos)) {
								return pos;
							}
						}
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private static boolean isSpaceAvailable(Level level, BlockPos pos) {
		// Looks for a 3x3 solid platform
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos checkPos = pos.offset(x, -1, z);
				if (!level.getBlockState(checkPos).isSolid()) {
					return false;
				}
			}
		}
		// Checks the space above is free
		for (int x = -1; x <= 1; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = -1; z <= 1; z++) {
					BlockPos checkPos = pos.offset(x, y, z);
					if (!level.getBlockState(checkPos).isAir()) {
						return false;
					}
				}
			}
		}
		return true;
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
