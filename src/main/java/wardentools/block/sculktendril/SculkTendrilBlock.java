package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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

    @Override
    public void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity && this.cachedTendrilTree != null) {
            // Here, we only forward the tree to block entity if it's the origin block entity
            if (pos == this.cachedTendrilTree.getOrigin()) {
                tendrilBlockEntity.setTendrilTreeGraph(this.cachedTendrilTree);
            } else {
                tendrilBlockEntity.setOrigin(this.cachedTendrilTree.getOrigin());
            }
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
                updateGraphOnPlace(this.cachedTendrilTree, placePos, clickedBlock);
            }
        } else if (level.getBlockState(clickedBlock).is(Blocks.SCULK)) {
            this.cachedTendrilTree = new TendrilTree(placePos);
        }
    }

    private void updateGraphOnPlace(@Nullable TendrilTree tendrilTree, BlockPos myPos, BlockPos parent) {
        if (tendrilTree == null) return;
        tendrilTree.addNode(myPos, parent);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult blockHitResult) {
        String message;
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {
            TendrilTree tendrilTree = tendrilBlockEntity.getRelativeTendrilTreeGraph();
            if (tendrilTree != null) {
                message = "Tree Origin: " + tendrilTree.getOrigin().getX()
                        + " " + tendrilTree.getOrigin().getY()
                        + " " + tendrilTree.getOrigin().getZ()
                        + "    Tree width at pos: " + tendrilTree.getWidth(pos);
            } else {
                message = "No Tendril Tree found.";
            }
        } else {
            message = "No Tendril Block Entity found at this position.";
        }
        player.sendSystemMessage(Component.literal(message));
        return super.useWithoutItem(state, level, pos, player, blockHitResult);
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
}
