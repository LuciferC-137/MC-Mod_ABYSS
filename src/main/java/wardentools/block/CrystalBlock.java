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


public class CrystalBlock extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<CrystalBlock> CODEC = RecordCodecBuilder
			.mapCodec((blockInstance) -> blockInstance.group(
					Codec.FLOAT.fieldOf("height").forGetter((block) -> block.height),
					Codec.FLOAT.fieldOf("aabb_offset").forGetter((block) -> block.aabbOffset),
					Codec.INT.fieldOf("base_light_level").forGetter((block) -> block.baseLightLevel),
					propertiesCodec())
				.apply(blockInstance, CrystalBlock::new));
	
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty OVERCHARGED;
	protected final VoxelShape northAabb;
	protected final VoxelShape southAabb;
	protected final VoxelShape eastAabb;
	protected final VoxelShape westAabb;
	protected final VoxelShape upAabb;
	protected final VoxelShape downAabb;
	private final float height;
	private final float aabbOffset;

	private final int baseLightLevel;

	static {OVERCHARGED = BooleanProperty.create("overcharged");}

	public CrystalBlock(float height, float length, int baseLightLevel, BlockBehaviour.Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState()
				  .setValue(WATERLOGGED, Boolean.FALSE)
				  .setValue(FACING, Direction.UP)
				  .setValue(OVERCHARGED, false));
	      this.upAabb = Block.box(length, 0.0D,
				  length, 16.0F - length, height, (16.0F - length));
	      this.downAabb = Block.box(length, (16.0F - height),
				  length, (16.0F - length), 16.0D, (16.0F - length));
	      this.northAabb = Block.box(length, length, (16.0F - height),
				  (16.0F - length), (16.0F - length), 16.0D);
	      this.southAabb = Block.box(length, length, 0.0D,
				  (16.0F - length), (16.0F - length), height);
	      this.eastAabb = Block.box(0.0D, length, length,
				  height, (16.0F - length), (16.0F - length));
	      this.westAabb = Block.box((16.0F - height), length,
				  length, 16.0D, (16.0F - length), (16.0F - length));
	      this.height = height;
	      this.aabbOffset = length;
		  this.baseLightLevel = baseLightLevel;
		  System.out.println("Construct CrystalBlock base="+baseLightLevel+" id="+this);
	   }

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

	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
	      Direction direction = state.getValue(FACING);
	      BlockPos blockpos = pos.relative(direction.getOpposite());
	      return level.getBlockState(blockpos).isFaceSturdy(level, blockpos, direction);
	}

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
                          .getType() == Fluids.WATER)
				  .setValue(FACING, ctx.getClickedFace())
				  .setValue(OVERCHARGED, false);
	}

	public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
	      return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
	      return state.rotate(mirror.getRotation(state.getValue(FACING)));
	   }

	public @NotNull FluidState getFluidState(BlockState state) {
	      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false)
				  : super.getFluidState(state);
	}

	public static int getLightLevel(@NotNull BlockState state) {
		return state.getValue(OVERCHARGED) ? 15 : 0;
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
		if (state.getBlock() instanceof CrystalBlock) {
			return state.getValue(OVERCHARGED) ? 15 : baseLightLevel;
		}
		return super.getLightEmission(state, level, pos);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
	      stateBuilder.add(WATERLOGGED, FACING, OVERCHARGED);
	}


	@Override
	protected @NotNull MapCodec<CrystalBlock> codec() {
	      return CODEC;
	}
	

}
