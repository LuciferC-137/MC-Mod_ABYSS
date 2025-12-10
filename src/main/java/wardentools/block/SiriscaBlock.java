package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SiriscaBlock extends Block implements BonemealableBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final int MAX_AGE = 6;
    public static final int DOUBLE_BLOCK_AGE_START = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("sirisca_age", 0, MAX_AGE);


    public SiriscaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(AGE, 0));
    }

    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                                           @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return shapeByDimensions(heightForState(state), widthForState(state));
    }

    private static float heightForState(BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return switch (state.getValue(AGE)) {
                case 3 -> 3.0F;
                case 4 -> 9.0F;
                case 5 -> 14.0F;
                case 6 -> 15.0F;
                default -> 1.0F;
            };
        }
        return switch (state.getValue(AGE)) {
            case 0 -> 6.0F;
            case 1 -> 10.0F;
            case 2 -> 15.0F;
            default -> 16.0F;
        };
    }

    private static float widthForState(BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return switch (state.getValue(AGE)) {
                case 3, 4 -> 8.0F;
                case 5 -> 10.0F;
                case 6 -> 12.0F;
                default -> 1.0F;
            };
        }
        return switch (state.getValue(AGE)) {
            case 0, 1 -> 8.0F;
            case 2 -> 12.0F;
            default -> 14.0F;
        };
    }

    private static @NotNull VoxelShape shapeByDimensions(float height, float width) {
        float half_space = (16F - width) / 2F;
        return Block.box(half_space, 0.0F, half_space,
                half_space + width, height, half_space + width);
    }

    protected @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing,
                                              @NotNull BlockState facingState,
                                              @NotNull LevelAccessor level,
                                              @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if (state.getValue(AGE) < DOUBLE_BLOCK_AGE_START) {
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y 
                || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) 
                || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN 
                    && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() 
                    : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1
                && level.getBlockState(blockpos.above()).canBeReplaced(context)
                ? super.getStateForPlacement(context) : null;
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    protected static float getGrowthSpeed(BlockState blockState, BlockGetter level, BlockPos pos) {
        return 2F;
    }

    public void growCrops(Level level, BlockPos pos, BlockState state, int fertilizerAmount) {
        int new_age = this.getAge(state) + fertilizerAmount;
        if (new_age > MAX_AGE) {
            new_age = MAX_AGE;
        }
        if (new_age < DOUBLE_BLOCK_AGE_START) {
            level.setBlock(pos, this.getStateForAge(new_age), UPDATE_CLIENTS);
        }
        if (new_age >= DOUBLE_BLOCK_AGE_START) {
            BlockPos upperPos = pos.above();
            BlockState upperState = level.getBlockState(upperPos);
            if (upperState.isAir() || upperState.is(this)) {
                level.setBlock(pos, this.getStateForAge(new_age), UPDATE_CLIENTS);
                level.setBlock(upperPos, this.defaultBlockState()
                        .setValue(HALF, DoubleBlockHalf.UPPER)
                        .setValue(AGE, new_age), new_age == DOUBLE_BLOCK_AGE_START ?
                                  UPDATE_ALL : UPDATE_CLIENTS);
            }
        }
    }

    @Override
    protected boolean isRandomlyTicking(@NotNull BlockState state) {
        return this.canStillGrow(state) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level,
                              @NotNull BlockPos pos, @NotNull RandomSource random) {
        int age = this.getAge(state);
        if (age < MAX_AGE) {
            float f = getGrowthSpeed(state, level, pos);
            if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                this.growCrops(level, pos, state, 1);
            }
        }
    }

    public final boolean canStillGrow(BlockState state) {
        return this.getAge(state) < MAX_AGE;
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }


    public int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 2, 4);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level,
                                         @NotNull BlockPos pos, @NotNull BlockState state) {
        return this.canStillGrow(state);
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random,
                                     @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random,
                                @NotNull BlockPos pos, @NotNull BlockState state) {
        this.growCrops(level, pos, state, this.getBonemealAgeIncrease(level));
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level,
                                 @NotNull BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState blockstate = level.getBlockState(pos.below());
            if (!blockstate.is(BlockRegistry.PURPLE_FARMLAND.get())) {
                return false;
            }
        }
        return super.canSurvive(state, level, pos);
    }

    public @NotNull BlockState playerWillDestroy(Level level, @NotNull BlockPos pos,
                                                 @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf =state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER)
                        ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF).add(AGE);
    }

}
