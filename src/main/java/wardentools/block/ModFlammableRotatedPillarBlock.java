package wardentools.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {

	public ModFlammableRotatedPillarBlock(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
			return true;
	}
	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}
	
	
	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state,
			UseOnContext context, ToolAction action, boolean simulate) {
		
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(BlockRegistry.DARKTREE_LOG.get())) {
				return BlockRegistry.STRIPPED_DARKTREE_LOG
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}
		
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(BlockRegistry.DARKTREE_WOOD.get())) {
				return BlockRegistry.STRIPPED_DARKTREE_WOOD
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}
		return super.getToolModifiedState(state, context, action, simulate);
	}
}
