package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.GramophoneBlockEntity;

import javax.annotation.Nullable;

public class GramophoneBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF;

    public GramophoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter,
                                           @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ?
                Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0):
                Block.box(0.5, 0.0, 0.5, 15.5, 15.0, 15.5);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction direction,
                                              @NotNull BlockState state1,
                                              @NotNull LevelAccessor accessor,
                                              @NotNull BlockPos pos, @NotNull BlockPos pos1) {
        DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || state1.is(this) && state1.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, accessor, pos, pos1);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public InteractionResult use(@NotNull BlockState state, @NotNull Level level,
                                 @NotNull BlockPos pos, @NotNull Player player,
                                 @NotNull InteractionHand hand,
                                 @NotNull BlockHitResult hitResult) {
        if (!player.getItemInHand(hand).isEmpty()) {
            return this.useItemOn(player.getItemInHand(hand), state, level, pos, player, hand, hitResult);
        } else {
            return this.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected @NotNull InteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
                                                   @NotNull Level level, @NotNull BlockPos pos,
                                                   @NotNull Player player, @NotNull InteractionHand hand,
                                                   @NotNull BlockHitResult hitResult) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState stateBelow = level.getBlockState(pos.below());
            if (stateBelow.is(BlockRegistry.GRAMOPHONE.get())) {
                return ((GramophoneBlock)stateBelow.getBlock())
                        .publicUseItemOn(stack, stateBelow, level, pos.below(), player, hand, hitResult);
            }
            return InteractionResult.FAIL;
        }
        return this.publicUseItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public @NotNull InteractionResult publicUseItemOn(@NotNull ItemStack stack, @NotNull BlockState state,
                                                          @NotNull Level level, @NotNull BlockPos pos,
                                                          @NotNull Player player, @NotNull InteractionHand hand,
                                                          @NotNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GramophoneBlockEntity gramophone) {
            if (!gramophone.isEmpty()) {
                return InteractionResult.PASS;
            } else {
                ItemStack itemInHand = player.getItemInHand(hand);
                InteractionResult action = tryInsertIntoJukebox(level, pos, itemInHand, player);
                return !action.consumesAction()
                        ? InteractionResult.PASS : action;
            }
        }
        return InteractionResult.FAIL;
    }

    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState stateBelow = level.getBlockState(pos.below());
            if (stateBelow.is(BlockRegistry.GRAMOPHONE.get())) {
                return ((GramophoneBlock)stateBelow.getBlock())
                        .publicUseWithoutItem(stateBelow, level, pos.below(), player, hitResult);
            }
            return InteractionResult.FAIL;
        }
        return this.publicUseWithoutItem(state, level, pos, player, hitResult);
    }

    public @NotNull InteractionResult publicUseWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                           @NotNull BlockPos pos, @NotNull Player player,
                                                           @NotNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GramophoneBlockEntity gramophone) {
            gramophone.popOutTheItem();
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult tryInsertIntoJukebox(Level level, BlockPos pos, ItemStack stack, Player player) {
        if (!(stack.getItem() instanceof RecordItem)) {
            return InteractionResult.PASS;
        } else {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.is(BlockRegistry.GRAMOPHONE.get())) {
                if (!level.isClientSide) {
                    ItemStack disc = stack.copy();
                    stack.shrink(1);
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (blockEntity instanceof GramophoneBlockEntity gramophone) {
                        gramophone.setTheItem(disc);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                    }
                    player.awardStat(Stats.PLAY_RECORD);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockPos blockpos = placeContext.getClickedPos();
        Level level = placeContext.getLevel();
        if (blockpos.getY() >= level.getMaxBuildHeight() - 1
                || !level.getBlockState(blockpos.above()).canBeReplaced(placeContext)) {
            return null;
        }
        BlockState blockStatePlacement = super.getStateForPlacement(placeContext);
        if (blockStatePlacement != null) {
            return blockStatePlacement.setValue(FACING, placeContext.getHorizontalDirection().getOpposite());
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, @NotNull BlockState state,
                            LivingEntity entity, @NotNull ItemStack stack) {
        BlockPos blockpos = pos.above();
        level.setBlock(blockpos,
                copyWaterloggedFrom(level, blockpos, (BlockState)this.defaultBlockState()
                        .setValue(HALF, DoubleBlockHalf.UPPER)
                        .setValue(FACING, level.getBlockState(pos).getValue(FACING))), 3);
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader levelReader, @NotNull BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, levelReader, pos);
        } else {
            BlockState blockstate = levelReader.getBlockState(pos.below());
            if (state.getBlock() != this) {
                return super.canSurvive(state, levelReader, pos);
            } else {
                return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
            }
        }
    }

    public static BlockState copyWaterloggedFrom(LevelReader levelReader, BlockPos pos, BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED)
                ? (BlockState)state.setValue(BlockStateProperties.WATERLOGGED,
                levelReader.isWaterAt(pos)) : state;
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos pos,
                                  @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide) {
            preventDropFromBottomPart(level, pos, state, player);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void playerDestroy(@NotNull Level level, @NotNull Player player, @NotNull BlockPos pos,
                              @NotNull BlockState state, @Nullable BlockEntity blockEntity,
                              @NotNull ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ?
                        Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                            BlockState state1, boolean b) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof GramophoneBlockEntity gramophone) {
                gramophone.popOutTheItem();
            }
            super.onRemove(state, level, pos, state1, b);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HALF).add(FACING);
    }

    static {
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return BlockEntityRegistry.GRAMOPHONE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type){
        if (!type.isValid(state)) return null;
        return level.isClientSide() ?
                (level0, pos0, state0, blockEntity) -> ((GramophoneBlockEntity)blockEntity).clientTick() :
                (level0, pos0, state0, blockEntity) -> ((GramophoneBlockEntity)blockEntity).tick();
    }

}
