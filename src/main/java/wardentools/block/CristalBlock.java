package wardentools.block;


import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


public class CristalBlock extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<CristalBlock> CODEC = RecordCodecBuilder.mapCodec((blockInstance) -> {
	      return blockInstance.group(Codec.FLOAT.fieldOf("height").forGetter((block) -> {
	         return block.height;
	      }), Codec.FLOAT.fieldOf("aabb_offset").forGetter((block) -> {
	         return block.aabbOffset;
	      }), propertiesCodec()).apply(blockInstance, CristalBlock::new);
	   });
	
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	protected final VoxelShape northAabb;
	protected final VoxelShape southAabb;
	protected final VoxelShape eastAabb;
	protected final VoxelShape westAabb;
	protected final VoxelShape upAabb;
	protected final VoxelShape downAabb;
	private final float height;
	private final float aabbOffset;

	public CristalBlock(float height, float length, BlockBehaviour.Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE)
				  .setValue(FACING, Direction.UP));
	      this.upAabb = Block.box((double)length, 0.0D,
				  (double)length, (double)(16.0F - length), (double)height, (double)(16.0F - length));
	      this.downAabb = Block.box((double)length, (double)(16.0F - height),
				  (double)length, (double)(16.0F - length), 16.0D, (double)(16.0F - length));
	      this.northAabb = Block.box((double)length, (double)length, (double)(16.0F - height),
				  (double)(16.0F - length), (double)(16.0F - length), 16.0D);
	      this.southAabb = Block.box((double)length, (double)length, 0.0D,
				  (double)(16.0F - length), (double)(16.0F - length), (double)height);
	      this.eastAabb = Block.box(0.0D, (double)length, (double)length,
				  (double)height, (double)(16.0F - length), (double)(16.0F - length));
	      this.westAabb = Block.box((double)(16.0F - height), (double)length,
				  (double)length, 16.0D, (double)(16.0F - length), (double)(16.0F - length));
	      this.height = height;
	      this.aabbOffset = length;
	   }

	@SuppressWarnings("deprecation")
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter getter,
										@NotNull BlockPos pos, @NotNull CollisionContext ctx) {
	      Direction direction = state.getValue(FACING);
        return switch (direction) {
            case NORTH -> this.northAabb;
            case SOUTH -> this.southAabb;
            case EAST -> this.eastAabb;
            case WEST -> this.westAabb;
            case DOWN -> this.downAabb;
            default -> this.upAabb;
        };
	   }

	@SuppressWarnings("deprecation")
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
	      Direction direction = state.getValue(FACING);
	      BlockPos blockpos = pos.relative(direction.getOpposite());
	      return level.getBlockState(blockpos).isFaceSturdy(level, blockpos, direction);
	}

	@SuppressWarnings("deprecation")
	public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction,
										   @NotNull BlockState state1, @NotNull LevelAccessor level,
										   @NotNull BlockPos pos, @NotNull BlockPos pos1) {
	      if (state.getValue(WATERLOGGED)) {
	         level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
	      }

	      return direction == state.getValue(FACING).getOpposite()
				  && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState()
				  : super.updateShape(state, direction, state1, level, pos, pos1);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
	      LevelAccessor levelaccessor = ctx.getLevel();
	      BlockPos blockpos = ctx.getClickedPos();
	      return this.defaultBlockState()
				  .setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos)
                          .getType() == Fluids.WATER).setValue(FACING, ctx.getClickedFace());
	}

	@SuppressWarnings("deprecation")
	public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
	      return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
	      return state.rotate(mirror.getRotation(state.getValue(FACING)));
	   }

	  @SuppressWarnings("deprecation")
	public @NotNull FluidState getFluidState(BlockState state) {
	      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false)
				  : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
	      stateBuilder.add(WATERLOGGED, FACING);
	}


	@Override
	protected @NotNull MapCodec<CristalBlock> codec() {
	      return CODEC;
	}
	

}
