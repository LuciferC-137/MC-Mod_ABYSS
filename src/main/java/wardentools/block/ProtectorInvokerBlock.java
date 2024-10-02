package wardentools.block;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemStackHandler;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ProtectorEntity;
import wardentools.items.ItemRegistry;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceExplosion;

public class ProtectorInvokerBlock extends Block implements EntityBlock {
	public static final int radiusForProtectorSpawn = 5;

	public ProtectorInvokerBlock(Properties prop) {
		super(prop);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return BlockEntityRegistry.PROTECTOR_INVOKER_BLOCK_ENTITY.get().create(pos, state);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
																  @NotNull BlockEntityType<T> type){
		return level.isClientSide() ? null : (level0, pos0, state0, blockEntity)
				-> ((ProtectorInvokerBlockEntity)blockEntity).tick();
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos,
										  @NotNull Player player, @NotNull InteractionHand interactionHand,
										  @NotNull BlockHitResult result) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof ProtectorInvokerBlockEntity invoker)) {
			return InteractionResult.PASS;
		}
        if (InteractionHand.MAIN_HAND != interactionHand) {
			return InteractionResult.FAIL;
		}
		ItemStack heldItem = player.getItemInHand(interactionHand);
		if (heldItem.is(ItemRegistry.PROTECTOR_HEART.get())
				&& invoker.getInventory().getStackInSlot(0).isEmpty()) {
			invoker.getInventory().setStackInSlot(0, heldItem.copy());
			heldItem.shrink(1);
			if (protectorWithThisInvokerExists(level, invoker)) {
				return InteractionResult.SUCCESS;
			}
			trySpawnNewProtector(level, pos, invoker);
			return InteractionResult.SUCCESS;

		} else if (heldItem.isEmpty() && !invoker.getInventory().getStackInSlot(0).isEmpty()) {
			player.setItemInHand(interactionHand, invoker.getInventory().getStackInSlot(0).copy());
			invoker.getInventory().getStackInSlot(0).shrink(1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	private void trySpawnNewProtector(Level level,
									  BlockPos pos,
									  ProtectorInvokerBlockEntity invoker){
		if (level.isClientSide()) return;
		BlockPos spawnPos = findSpawnPosition(level, pos);
		if (spawnPos != null) {
			ProtectorEntity protec = ModEntities.PROTECTOR.get().create(level);
			if (protec != null) {
				protec.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(),
							spawnPos.getZ() + 0.5, level.random.nextFloat() * 360F, 0);
				protec.setHealth(invoker.heartItem().readHealth(invoker.heartStack()));
				protec.setInvokerPos(pos);
				if (invoker.heartItem() != null) {
					invoker.heartItem().setProtector(invoker.heartStack(), protec);
				}
				protec.makeSpawnAnimation();
				Vec3 particleSource = spawnPos.above().getCenter();
				PacketHandler.sendToAllClient(new ParticleRadianceExplosion(particleSource));
				level.addFreshEntity(protec);
				invoker.protectorSuccessfullyInvoked = true;
			}
		}
	}

	public boolean protectorWithThisInvokerExists(Level level, ProtectorInvokerBlockEntity invoker) {
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(@NotNull BlockState state, Level level,
						 @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
		if (!level.isClientSide()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof ProtectorInvokerBlockEntity blockEntity) {

				ItemStackHandler inventory = blockEntity.getInventory();
				for (int index = 0; index < inventory.getSlots(); index++) {
					ItemStack stackDrop = inventory.getStackInSlot(index).copy();
					var entity = new ItemEntity(level, pos.getX() + 0.5D,
							pos.getY() + 0.5D, pos.getZ() + 0.5D, stackDrop);
					level.addFreshEntity(entity);
				}
			}
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}
	
	private BlockPos findSpawnPosition(Level level, BlockPos invokerPos) {
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
	private boolean isSpaceAvailable(Level level, BlockPos pos) {
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
}
