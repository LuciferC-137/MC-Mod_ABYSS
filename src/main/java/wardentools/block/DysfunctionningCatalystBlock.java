package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
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
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;

public class DysfunctionningCatalystBlock extends Block implements EntityBlock {

	public DysfunctionningCatalystBlock(Properties prop) {
		super(prop);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return BlockEntityRegistry.DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY.get().create(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
	}

	@Override
	protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
														@NotNull BlockPos pos, @NotNull Player player,
														@NotNull BlockHitResult hitResult) {
		return this.use(level, pos, player);
	}

	@Override
	protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
													   @NotNull Level level, @NotNull BlockPos pos,
													   @NotNull Player player, @NotNull InteractionHand hand,
													   @NotNull BlockHitResult hitResult) {
        return switch (this.use(level, pos, player)) {
            case InteractionResult.SUCCESS -> ItemInteractionResult.SUCCESS;
            case InteractionResult.FAIL -> ItemInteractionResult.FAIL;
            default -> ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        };
    }

	public @NotNull InteractionResult use(Level level, @NotNull BlockPos pos,
										  @NotNull Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof DysfunctionningCatalystBlockEntity)) return InteractionResult.PASS;
		if (level.isClientSide()) return InteractionResult.SUCCESS;
		if (player instanceof ServerPlayer sPlayer) {
			sPlayer.openMenu((MenuProvider)blockEntity, pos);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
																  @NotNull BlockEntityType<T> type){
		return level.isClientSide() ?
			(level0, pos0, state0, blockEntity) -> ((DysfunctionningCatalystBlockEntity)blockEntity).clientTick() :
			(level0, pos0, state0, blockEntity) -> ((DysfunctionningCatalystBlockEntity)blockEntity).tick();
	}

	@Override
	public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
						 @NotNull BlockState newState, boolean isMoving) {
		if (!level.isClientSide()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof DysfunctionningCatalystBlockEntity blockEntity) {
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

	@Override
	public void animateTick(@NotNull BlockState state, @NotNull Level level,
							@NotNull BlockPos pos, @NotNull RandomSource random) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof DysfunctionningCatalystBlockEntity catalyst) {
			catalyst.clientTick();
		}
	}
}
