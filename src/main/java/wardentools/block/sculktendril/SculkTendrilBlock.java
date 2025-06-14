package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.SculkTendrilBlockEntity;

import javax.annotation.Nullable;

public class SculkTendrilBlock extends DropExperienceBlock implements EntityBlock {
    private TendrilTree cachedTendrilTree = null;

    public SculkTendrilBlock(IntProvider intProvider, Properties properties) {
        super(intProvider, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityRegistry.SCULK_TENDRIL_BLOCK_ENTITY.get().create(pos, state);
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level,
                        @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity && this.cachedTendrilTree != null) {
            // Here, we only forward the tree to block entity if it's the origin block entity
            if (pos == this.cachedTendrilTree.getOrigin()) {
                tendrilBlockEntity.setTendrilTreeGraph(this.cachedTendrilTree);
            } else {
                tendrilBlockEntity.setOrigin(this.cachedTendrilTree.getOrigin());
            }
            this.recursiveWidthUpdate(level, pos, 1);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        // Retrieve the block position where the block is about to be placed
        BlockPos placePos = placeContext.getClickedPos().relative(placeContext.getClickedFace().getOpposite());

        this.createCacheOnPlace(placeContext.getClickedPos(), placePos, placeContext.getLevel());
        return super.getStateForPlacement(placeContext);
    }

    private void createCacheOnPlace(BlockPos placePos, BlockPos clickedBlock, Level level) {
        if (level.getBlockEntity(clickedBlock) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            BlockPos commonOrigin = tendrilBlockEntity.getOrigin();
            if (level.getBlockEntity(commonOrigin) instanceof SculkTendrilBlockEntity originEntity) {
                this.cachedTendrilTree = originEntity.getTendrilTreeGraph();
                updateGraphOnPlace(this.cachedTendrilTree, placePos, clickedBlock, level);
            }
        } else if (level.getBlockState(clickedBlock).is(Blocks.SCULK)) {
            this.cachedTendrilTree = new TendrilTree(placePos);
        }
    }

    private void updateGraphOnPlace(@Nullable TendrilTree tendrilTree, BlockPos myPos,
                                    BlockPos parent, Level level) {
        if (tendrilTree == null) return;
        tendrilTree.addNode(myPos, parent);
    }

    public void recursiveWidthUpdate(Level level, BlockPos pos, int depth) {
        if (depth > 64) return;
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            level.scheduleTick(pos, this, depth);
            TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
            if (tendrilTree != null) {
                BlockPos parentPos = tendrilTree.getParentOf(pos);
                if (parentPos != null) {
                    if (level.getBlockState(parentPos).getBlock() instanceof SculkTendrilBlock parentTendril) {
                        parentTendril.recursiveWidthUpdate(level, tendrilTree.getParentOf(pos), depth + 1);
                    }
                }
            }
        }
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level,
                                   @NotNull BlockPos pos, @NotNull Block block,
                                   @NotNull BlockPos neighbor, boolean b) {
        super.neighborChanged(state, level, pos, block, neighbor, b);
        if (level.isClientSide) return;
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
            if (tendrilTree == null || ! tendrilTree.hasNode(pos)) {
                level.destroyBlock(pos, true, null);
            } else {
                tendrilBlockEntity.updateWidth();
                this.recursiveWidthUpdate(level, pos, 1);
            }
        }
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.tick(state, level, pos, random);
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            tendrilBlockEntity.updateWidth();
        }
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader levelReader,
                                 @NotNull BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos supportPosition = pos.relative(direction);
            if (anyTreeCanSupport(supportPosition, levelReader)) {
                return true;
            }
        }
        return hasSculkNeighbor(pos, levelReader);
    }

    private boolean anyTreeCanSupport(BlockPos supportPosition, LevelReader levelReader) {
        if (levelReader.getBlockEntity(supportPosition) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
            if (tendrilTree != null) {
                return tendrilTree.canHaveChildren(supportPosition);
            }
        }
        return false;
    }

    private boolean hasSculkNeighbor (BlockPos pos, LevelReader level) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            if (level.getBlockState(neighborPos).is(Blocks.SCULK)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onRemove(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull BlockState state1, boolean b) {
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
            if (tendrilTree != null) {
                tendrilTree.recursiveRemove(pos);
            }
        }
        super.onRemove(state, level, pos, state1, b);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof  SculkTendrilBlockEntity tendrilBlockEntity) {
                TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
                if (tendrilTree != null) {
                    player.sendSystemMessage(Component
                            .literal("Width: "
                                    + tendrilBlockEntity.getRelativeTendrilTreeGraph().getWidth(pos)));
                    player.sendSystemMessage(Component
                            .literal("Depth: "
                                    + tendrilBlockEntity.getRelativeTendrilTreeGraph().getTotalDepth(pos)));
                    player.sendSystemMessage(Component
                            .literal("Ascending Length: "
                                    + tendrilBlockEntity.getRelativeTendrilTreeGraph().getAscendingLength(pos)));
                    player.sendSystemMessage(Component
                            .literal("Descending Length: "
                                    + tendrilBlockEntity.getRelativeTendrilTreeGraph().getDescendingLength(pos)));
                }
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}