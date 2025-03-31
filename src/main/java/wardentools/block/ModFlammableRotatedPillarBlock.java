package wardentools.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {

	public ModFlammableRotatedPillarBlock(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
							   @NotNull BlockPos pos, @NotNull Direction direction) {
			return true;
	}
	@Override
	public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
							   @NotNull BlockPos pos, @NotNull Direction direction) {
		return 5;
	}
	@Override
	public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
								  @NotNull BlockPos pos, @NotNull Direction direction) {
		return 5;
	}



	@Override
	public @Nullable BlockState getToolModifiedState(@NotNull BlockState state,
													 UseOnContext context, @NotNull ItemAbility ability,
													 boolean simulate) {
		
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(BlockRegistry.DARKTREE_LOG.get())) {
				return BlockRegistry.STRIPPED_DARKTREE_LOG
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
			else if (state.is(BlockRegistry.WHITETREE_LOG.get())) {
				return BlockRegistry.STRIPPED_WHITETREE_LOG
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}
		
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(BlockRegistry.DARKTREE_WOOD.get())) {
				return BlockRegistry.STRIPPED_DARKTREE_WOOD
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
			else if (state.is(BlockRegistry.WHITETREE_WOOD.get())) {
				return BlockRegistry.STRIPPED_WHITETREE_WOOD
						.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}
		return super.getToolModifiedState(state, context, ability, simulate);
	}
}
