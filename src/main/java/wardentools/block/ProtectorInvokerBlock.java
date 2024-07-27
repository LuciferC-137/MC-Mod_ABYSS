package wardentools.block;

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

public class ProtectorInvokerBlock extends Block implements EntityBlock {
	public static final int radiusForProtectorSpawn = 5;

	public ProtectorInvokerBlock(Properties prop) {
		super(prop);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityRegistry.PROTECTOR_INVOKER_BLOCK_ENTITY.get().create(pos, state);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
			BlockEntityType<T> type){
		return level.isClientSide() ? null : (level0, pos0, state0, blockEntity)
				-> ((ProtectorInvokerBlockEntity)blockEntity).tick();
	}
	
	@Override
	public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand interactionHand, BlockHitResult result) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof ProtectorInvokerBlockEntity)) {
			return InteractionResult.PASS;
		}
		ItemStack heldItem = player.getItemInHand(interactionHand);
		if (heldItem.is(ItemRegistry.PROTECTOR_HEART.get())
				&&((ProtectorInvokerBlockEntity)blockEntity).getInventory().getStackInSlot(0).isEmpty()) {
			((ProtectorInvokerBlockEntity)blockEntity).getInventory()
				.setStackInSlot(0, new ItemStack(ItemRegistry.PROTECTOR_HEART.get()));
			heldItem.shrink(1);
			
			if (!level.isClientSide()) {
	            BlockPos spawnPos = findSpawnPosition(level, pos);
	            if (spawnPos != null) {
	                ProtectorEntity protec = ModEntities.PROTECTOR.get().create(level);
	                if (protec != null) {
	                    protec.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, level.random.nextFloat() * 360F, 0);
	                    level.addFreshEntity(protec);
	                }
	            } 
	        }
			return InteractionResult.SUCCESS;

		} else if (heldItem.isEmpty()&&
				!((ProtectorInvokerBlockEntity)blockEntity).getInventory().getStackInSlot(0).isEmpty()) {
			((ProtectorInvokerBlockEntity)blockEntity).getInventory()
			.getStackInSlot(0).shrink(1);
			player.setItemInHand(interactionHand, new ItemStack(ItemRegistry.PROTECTOR_HEART.get()));
			return InteractionResult.SUCCESS;
		} else if (heldItem.is(ItemRegistry.PROTECTOR_HEART.get())
				&&!((ProtectorInvokerBlockEntity)blockEntity).getInventory().getStackInSlot(0).isEmpty()) {
			((ProtectorInvokerBlockEntity)blockEntity).getInventory()
			.getStackInSlot(0).shrink(1);
			heldItem.grow(1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!level.isClientSide()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof ProtectorInvokerBlockEntity blockEntity) {

				ItemStackHandler inventory = blockEntity.getInventory();
				for (int index = 0; index < inventory.getSlots(); index++) {
					ItemStack stackDrop = inventory.getStackInSlot(index);
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
                    if (Math.abs(dx) == r || Math.abs(dz) == r) {
                    	BlockPos pos = invokerPos.offset(dx, 0, dz);
    	                if (isSpaceAvailable(level, pos)) {
    	                    return pos;
    	                }
                    }
                }
            }
        }
	    return null;
	}
	
	private boolean isSpaceAvailable(Level level, BlockPos pos) {
	    for (int x = 0; x < 2; x++) {
	        for (int y = 0; y < 3; y++) {
	            BlockPos checkPos = pos.offset(x, y, 0);
	            if (!level.getBlockState(checkPos).isAir()) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

}
