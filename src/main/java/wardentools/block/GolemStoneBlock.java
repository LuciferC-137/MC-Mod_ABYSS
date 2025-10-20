package wardentools.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.GolemStoneBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.misc.Crystal;

import javax.annotation.Nullable;


public class GolemStoneBlock extends HorizontalDirectionalBlock implements EntityBlock {
	private static final MapCodec<GolemStoneBlock> CODEC
			= simpleCodec(GolemStoneBlock::new);

	public static final EnumProperty<Crystal> CRYSTAL;
	public static final BooleanProperty HAS_SCULK = BooleanProperty.create("has_sculk");

	static {CRYSTAL = EnumProperty.create("crystal_index", Crystal.class);}


	public GolemStoneBlock(Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState()
				  .setValue(FACING, Direction.NORTH)
				  .setValue(CRYSTAL, Crystal.getDefault())
				  .setValue(HAS_SCULK, false));
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
	      stateBuilder.add(FACING, CRYSTAL, HAS_SCULK);
	}

	@Override
	protected void onPlace(@NotNull BlockState state, Level level,
						   @NotNull BlockPos pos, @NotNull BlockState oldState, boolean moved) {
		if (level.getBlockEntity(pos) instanceof GolemStoneBlockEntity be) {
			be.markPlacedByPlayer();
		}
		super.onPlace(state, level, pos, oldState, moved);
	}

	public static void placeGolem(Level level, BlockPos pos, BlockState state) {
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
		golem.setSculk(state.getValue(HAS_SCULK));
		level.addFreshEntity(golem);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
																  @NotNull BlockState state,
																  @NotNull BlockEntityType<T> type) {
		return level.isClientSide ? null :
				(level0, pos0, state0, blockEntity)
						-> ((GolemStoneBlockEntity)blockEntity).tick()
		;
	}


	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
												@NotNull BlockState blockState) {
		return new GolemStoneBlockEntity(blockPos, blockState);
	}

}
