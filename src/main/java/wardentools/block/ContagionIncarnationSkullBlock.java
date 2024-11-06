package wardentools.block;


import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;

import javax.annotation.Nullable;


public class ContagionIncarnationSkullBlock extends HorizontalDirectionalBlock implements EntityBlock {
	private static final MapCodec<ContagionIncarnationSkullBlock> CODEC
			= simpleCodec(ContagionIncarnationSkullBlock::new);

	public ContagionIncarnationSkullBlock(Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

	@Override
	protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@SuppressWarnings("deprecation")
	public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter,
										@NotNull BlockPos pos, @NotNull CollisionContext ctx) {
		return Block.box(1.0D, 0.0D, 1.0D,
				15.0D, 15.0D, 15.0D);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
	      return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
	      stateBuilder.add(FACING);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return BlockEntityRegistry.CONTAGION_INCARNATION_SKULL_BLOCK_ENTITY.get().create(pos, state);
	}
}
