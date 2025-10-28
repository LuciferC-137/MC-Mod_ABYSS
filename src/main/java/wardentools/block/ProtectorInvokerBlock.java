package wardentools.block;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
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
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.ProtectorInvokerBlockEntity;

public class ProtectorInvokerBlock extends Block implements EntityBlock {
	private static final VoxelShape SHAPE_FOOT = Block.box(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
	private static final VoxelShape SHAPE_BASE = Block.box(5.0, 1.0, 5.0, 11.0, 3.0, 11.0);
	private static final VoxelShape SHAPE_STICK = Block.box(6.0, 3.0, 6.0, 10.0, 9.0, 10.0);
	private static final VoxelShape SHAPE_SUPPORT = Block.box(4.0, 9.0, 4.0, 12.0, 10.0, 12.0);
	private static final VoxelShape SHAPE_GLASS = Block.box(4.5, 10.0, 4.5, 11.5, 16.0, 11.5);
	private static final VoxelShape SHAPE = Shapes.or(SHAPE_FOOT, SHAPE_BASE, SHAPE_STICK, SHAPE_SUPPORT, SHAPE_GLASS);


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
	protected boolean useShapeForLightOcclusion(@NotNull BlockState state) {
		return true;
	}

	@Override
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world,
										@NotNull BlockPos pos, @NotNull CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
													   @NotNull Level level, @NotNull BlockPos pos,
													   @NotNull Player player, @NotNull InteractionHand hand,
													   @NotNull BlockHitResult hitResult) {
		return switch (this.use(level, pos, player, hand)) {
			case InteractionResult.SUCCESS -> ItemInteractionResult.SUCCESS;
			case InteractionResult.FAIL -> ItemInteractionResult.FAIL;
			default -> ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		};
	}

	public @NotNull InteractionResult use( Level level, @NotNull BlockPos pos,
											  @NotNull Player player, @NotNull InteractionHand interactionHand) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!(blockEntity instanceof ProtectorInvokerBlockEntity invoker)) {
			return InteractionResult.PASS;
		}
        if (InteractionHand.MAIN_HAND != interactionHand) {
			return InteractionResult.FAIL;
		}
		return invoker.playerInteract(player, pos, interactionHand);
	}

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
}
