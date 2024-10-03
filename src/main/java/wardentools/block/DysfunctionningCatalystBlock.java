package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;

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
	@SuppressWarnings("deprecation")
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
										  @NotNull Player player,
										  @NotNull InteractionHand interactionHand,
										  @NotNull BlockHitResult result) {

		return InteractionResult.FAIL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
						 @NotNull BlockState newState, boolean isMoving) {
		super.onRemove(state, level, pos, newState, isMoving);
	}	
}
