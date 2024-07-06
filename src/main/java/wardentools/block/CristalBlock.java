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


public class CristalBlock extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<CristalBlock> CODEC = RecordCodecBuilder.mapCodec((p_313213_) -> {
	      return p_313213_.group(Codec.FLOAT.fieldOf("height").forGetter((p_313043_) -> {
	         return p_313043_.height;
	      }), Codec.FLOAT.fieldOf("aabb_offset").forGetter((p_310115_) -> {
	         return p_310115_.aabbOffset;
	      }), propertiesCodec()).apply(p_313213_, CristalBlock::new);
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

	public CristalBlock(float height, float length, BlockBehaviour.Properties p_152017_) {
	      super(p_152017_);
	      this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.UP));
	      this.upAabb = Block.box((double)length, 0.0D, (double)length, (double)(16.0F - length), (double)height, (double)(16.0F - length));
	      this.downAabb = Block.box((double)length, (double)(16.0F - height), (double)length, (double)(16.0F - length), 16.0D, (double)(16.0F - length));
	      this.northAabb = Block.box((double)length, (double)length, (double)(16.0F - height), (double)(16.0F - length), (double)(16.0F - length), 16.0D);
	      this.southAabb = Block.box((double)length, (double)length, 0.0D, (double)(16.0F - length), (double)(16.0F - length), (double)height);
	      this.eastAabb = Block.box(0.0D, (double)length, (double)length, (double)height, (double)(16.0F - length), (double)(16.0F - length));
	      this.westAabb = Block.box((double)(16.0F - height), (double)length, (double)length, 16.0D, (double)(16.0F - length), (double)(16.0F - length));
	      this.height = height;
	      this.aabbOffset = length;
	   }
	
	
	public VoxelShape getShape(BlockState p_152021_, BlockGetter p_152022_, BlockPos p_152023_, CollisionContext p_152024_) {
	      Direction direction = p_152021_.getValue(FACING);
	      switch (direction) {
	         case NORTH:
	            return this.northAabb;
	         case SOUTH:
	            return this.southAabb;
	         case EAST:
	            return this.eastAabb;
	         case WEST:
	            return this.westAabb;
	         case DOWN:
	            return this.downAabb;
	         case UP:
	         default:
	            return this.upAabb;
	      }
	   }

	   public boolean canSurvive(BlockState p_152026_, LevelReader p_152027_, BlockPos p_152028_) {
	      Direction direction = p_152026_.getValue(FACING);
	      BlockPos blockpos = p_152028_.relative(direction.getOpposite());
	      return p_152027_.getBlockState(blockpos).isFaceSturdy(p_152027_, blockpos, direction);
	   }

	   @SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState p_152036_, Direction p_152037_, BlockState p_152038_, LevelAccessor p_152039_, BlockPos p_152040_, BlockPos p_152041_) {
	      if (p_152036_.getValue(WATERLOGGED)) {
	         p_152039_.scheduleTick(p_152040_, Fluids.WATER, Fluids.WATER.getTickDelay(p_152039_));
	      }

	      return p_152037_ == p_152036_.getValue(FACING).getOpposite() && !p_152036_.canSurvive(p_152039_, p_152040_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_152036_, p_152037_, p_152038_, p_152039_, p_152040_, p_152041_);
	   }

	   @Nullable
	   public BlockState getStateForPlacement(BlockPlaceContext p_152019_) {
	      LevelAccessor levelaccessor = p_152019_.getLevel();
	      BlockPos blockpos = p_152019_.getClickedPos();
	      return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER)).setValue(FACING, p_152019_.getClickedFace());
	   }

	   public BlockState rotate(BlockState p_152033_, Rotation p_152034_) {
	      return p_152033_.setValue(FACING, p_152034_.rotate(p_152033_.getValue(FACING)));
	   }

	   @SuppressWarnings("deprecation")
	public BlockState mirror(BlockState p_152030_, Mirror p_152031_) {
	      return p_152030_.rotate(p_152031_.getRotation(p_152030_.getValue(FACING)));
	   }

	   @SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState p_152045_) {
	      return p_152045_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152045_);
	   }

	   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152043_) {
	      p_152043_.add(WATERLOGGED, FACING);
	   }


	@Override
	protected MapCodec<CristalBlock> codec() {
	      return CODEC;
	   }
	

}
