package wardentools.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.misc.Crystal;

import javax.annotation.Nullable;


public class GolemStoneBlock extends HorizontalDirectionalBlock {
	private static final MapCodec<GolemStoneBlock> CODEC
			= simpleCodec(GolemStoneBlock::new);

	public static final EnumProperty<Crystal> CRYSTAL;
	static {CRYSTAL = EnumProperty.create("crystal_index", Crystal.class);}


	public GolemStoneBlock(Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState()
				  .setValue(FACING, Direction.NORTH).setValue(CRYSTAL, Crystal.getDefault()));
    }

	@Override
	protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
	      return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
	      stateBuilder.add(FACING, CRYSTAL);
	}

	@Override
	protected void onPlace(@NotNull BlockState state, @NotNull Level level,
						   @NotNull BlockPos pos, @NotNull BlockState state1, boolean b) {
		CrystalGolemEntity golem = new CrystalGolemEntity(ModEntities.CRYSTAL_GOLEM.get(), level);
		float yRot = state.getValue(FACING).toYRot();
		golem.setSyncedYRot(yRot);
		golem.moveTo(pos.getX() + 0.5, pos.getY() + 1,
				pos.getZ() + 0.5, yRot, 0);
		golem.setCrystalType(state.getValue(CRYSTAL));
		golem.setGolemStonePos(pos);
		golem.setState(level.getRandom().nextBoolean() ?
				CrystalGolemEntity.GolemState.DEACTIVATED_1 :
				CrystalGolemEntity.GolemState.DEACTIVATED_2);
		level.addFreshEntity(golem);

		super.onPlace(state, level, pos, state1, b);
	}
}
