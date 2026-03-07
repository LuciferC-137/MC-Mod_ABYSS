package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;


/**
 * Copied from LeavesBlock with minor modifications to the decay distance.
 */
public class LongDecayDistanceLeavesBlock extends Block implements SimpleWaterloggedBlock, IShearable {
    public static final MapCodec<LeavesBlock> CODEC = simpleCodec(LeavesBlock::new);
    public static final int DECAY_DISTANCE = 9;
    public static final IntegerProperty DISTANCE;
    public static final BooleanProperty PERSISTENT;
    public static final BooleanProperty WATERLOGGED;
    private static final int TICK_DELAY = 1;

    public @NotNull MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    public LongDecayDistanceLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DISTANCE, DECAY_DISTANCE)).setValue(PERSISTENT, false)).setValue(WATERLOGGED, false));
    }

    protected @NotNull VoxelShape getBlockSupportShape(@NotNull BlockState state,
                                                       @NotNull BlockGetter reader, @NotNull BlockPos pos) {
        return Shapes.empty();
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(DISTANCE) == DECAY_DISTANCE && !(Boolean)state.getValue(PERSISTENT);
    }

    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level,
                              @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (this.decaying(state)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }

    }

    protected boolean decaying(BlockState state) {
        return !(Boolean)state.getValue(PERSISTENT) && (Integer)state.getValue(DISTANCE) == DECAY_DISTANCE;
    }

    protected void tick(@NotNull BlockState state, ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    protected int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter level,
                                @NotNull BlockPos pos) {
        return 1;
    }

    protected @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing,
                                              @NotNull BlockState facingState,
                                              @NotNull LevelAccessor level, @NotNull BlockPos currentPos,
                                              @NotNull BlockPos facingPos) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        int i = getDistanceAt(facingState) + 1;
        if (i != 1 || (Integer)state.getValue(DISTANCE) != i) {
            level.scheduleTick(currentPos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = DECAY_DISTANCE;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceAt(level.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return (BlockState)state.setValue(DISTANCE, i);
    }

    private static int getDistanceAt(BlockState neighbor) {
        return getOptionalDistanceAt(neighbor).orElse(DECAY_DISTANCE);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return state.hasProperty(DISTANCE) ? OptionalInt.of((Integer)state.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    protected @NotNull FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ?
                Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public void animateTick(@NotNull BlockState state, Level level,
                            BlockPos pos, @NotNull RandomSource random) {
        if (level.isRainingAt(pos.above()) && random.nextInt(15) == 1) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, ParticleTypes.DRIPPING_WATER);
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DISTANCE, PERSISTENT, WATERLOGGED});
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockstate = (BlockState)((BlockState)this.defaultBlockState().setValue(PERSISTENT, true)).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return updateDistance(blockstate, context.getLevel(), context.getClickedPos());
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull Direction direction) {
        return 60;
    }
    @Override
    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                  @NotNull BlockPos pos, @NotNull Direction direction) {
        return 30;
    }

    static {
        DISTANCE = IntegerProperty.create("darktree_distance", 1, DECAY_DISTANCE);
        PERSISTENT = BlockStateProperties.PERSISTENT;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }
}