package wardentools.worldgen.features.custom.sculk;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilTree;
import wardentools.blockentity.SculkTendrilBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class SculkTendrilsEmergence extends Feature<SculkTendrilsEmergenceConfiguration>  {
    private static final int MAX_ELONGATION = 8;
    private static final int MAX_TOTAL_NODES = 1000; // Security. Should not be reached.
    private static final float MIN_BRANCH_PROBABILITY = 0.01f;
    private static final Logger LOGGER = LogUtils.getLogger();

    public SculkTendrilsEmergence(Codec<SculkTendrilsEmergenceConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SculkTendrilsEmergenceConfiguration> context) {
        WorldGenLevel level = context.level();
        float deviationStrength = Math.min(1.0f, Math.max(0.001F, context.config().deviationStrength));
        int maxDepth = Math.max(0, Math.min(10, context.config().maxDepth));
        float heightReductionFactor = Math.max(0.1f, Math.min(0.9f, context.config().heightReductionFactor));
        float branchProbability = Math.max(MIN_BRANCH_PROBABILITY, Math.min(0.8f, context.config().branchProbability));
        int maxLength = Math.max(1, Math.min(20, context.config().maxLength));

        TendrilTree tree = new TendrilTree(context.origin());

        // Placing initial origin holder
        this.setBlock(level, context.origin().below(), Blocks.SCULK.defaultBlockState());

         recursiveTreeStructure(tree, context.origin(), deviationStrength,
                 0, maxDepth, maxLength, heightReductionFactor, branchProbability, level.getRandom(),
                Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom()));

        if (tree.getAllNodes().size() > MAX_TOTAL_NODES) return false;

        placeBlocksWithoutUpdate(level, tree, context.origin());
        configureBlockEntities(level, tree, context.origin());
        tree.updateAllNodes(level);

        return true;
    }

    private void recursiveTreeStructure(TendrilTree tree, BlockPos origin, float deviationStrength,
                                        int depth, int maxDepth, int maxLength, float heightReductionFactor,
                                        float branchProbability, RandomSource random, Direction directionToPrevious) {
        if (depth > maxDepth) return;

        int currentMaxLength = Math.max(1, (int)((float)maxLength * heightReductionFactor));
        float currentBranchingProbability = Math.max(MIN_BRANCH_PROBABILITY, branchProbability * heightReductionFactor);
        List<Direction> currentBranch = new ArrayList<>();

        int length = random.nextInt(currentMaxLength / 4 + 1)
                + random.nextInt(currentMaxLength / 4 + 1)
                + random.nextInt(currentMaxLength / 4 + 1)
                + random.nextInt(currentMaxLength / 4 + 1)+ 1;

        if (depth == 0) {
            // Origin branch, only straight up
            for (int i = 0; i < length; i++) {
                currentBranch.add(Direction.UP);
            }
        } else {
            Direction mainDirection = getRandomHorizontalDirectionExcept(directionToPrevious.getOpposite());
            int horizontalLength = Math.max(1, (int)((float)length * deviationStrength));
            for (int i = 0; i < horizontalLength; i++) {
                if (random.nextFloat() < 0.8F) {
                    currentBranch.add(mainDirection);
                } else {
                    currentBranch.add(getRandomHorizontalDirectionExcept(mainDirection.getOpposite(), mainDirection));
                }
            }
            for (int i = horizontalLength; i < length; i++) {
                currentBranch.add(Direction.UP);
            }
        }

        List<BlockPos> branchingPos = addPosToTree(tree, origin, currentBranch,
                currentBranchingProbability, random);

        for (BlockPos pos : branchingPos) {
            if (tree.hasNode(pos)) {
                recursiveTreeStructure(tree, pos, deviationStrength, depth + 1, maxDepth, currentMaxLength,
                        heightReductionFactor, currentBranchingProbability, random,
                        getRandomHorizontalDirectionExcept(directionToPrevious.getOpposite()));
            } else {
                LOGGER.warn("Trying to branch from non-existent node: {}", pos);
            }
        }
    }

    // Adds a chain of positions to the tree starting from origin, following the given directions.
    // Returns the list of positions where branching should occur.
    private List<BlockPos> addPosToTree(TendrilTree tree, BlockPos origin, List<Direction> directions,
                                        float branchingProbability, RandomSource random) {
        // Safety: origin must already be part of the tree
        if (!tree.hasNode(origin)) {
            return new ArrayList<>();
        }
        List<BlockPos> branchingPos = new ArrayList<>();
        BlockPos currentPos = origin;
        float localProbability = branchingProbability;
        boolean hasBranchedLastBlock = false;

        for (Direction direction : directions) {
            // Parent must exist in the tree at each step
            if (!tree.hasNode(currentPos) || !tree.canHaveChildren(currentPos)) {
                break;
            }
            BlockPos nextPos = currentPos.relative(direction);
            tree.addNode(nextPos, currentPos);
            if (!tree.hasNode(nextPos)) {
                break; // insertion refused (e.g., parent missing, duplicate, or constraints) -> stop chain
            }

            if (random.nextFloat() < localProbability && !hasBranchedLastBlock) {
                hasBranchedLastBlock = true;
                branchingPos.add(nextPos);
                localProbability *= 0.9F; // Decrease chance to branch next time
            } else {
                hasBranchedLastBlock = false;
                localProbability *= 1.1F; // Increase chance to branch next time
            }

            currentPos = nextPos;
        }
        return branchingPos;
    }

    private Direction getRandomHorizontalDirectionExcept(@Nullable Direction... exception) {
        Direction[] directions = Direction.Plane.HORIZONTAL.stream()
                .filter(dir -> exception == null || !List.of(exception).contains(dir))
                .toArray(Direction[]::new);
        return directions.length > 0 ? directions[(int)(Math.random() * directions.length)] : Direction.NORTH;
    }

    private void placeBlocksWithoutUpdate(WorldGenLevel level, TendrilTree tree, BlockPos origin) {
        for (BlockPos pos : tree.getAllNodes()) {
            if (Math.abs(origin.getX() - pos.getX()) <= MAX_ELONGATION &&
                    Math.abs(origin.getZ() - pos.getZ()) <= MAX_ELONGATION) {
                BlockState state = sculkTendril();
                level.setBlock(pos, state, Block.UPDATE_NONE);
            }
        }
    }

    private void configureBlockEntities(WorldGenLevel level, TendrilTree tree, BlockPos origin) {
        for (BlockPos pos : tree.getAllNodes()) {
            if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilEntity) {
                if (pos.equals(origin)) {
                    tendrilEntity.setTendrilTreeGraph(tree);
                }
                tendrilEntity.setOrigin(origin);
            }
        }
    }

    private static BlockState sculkTendril() {
        return BlockRegistry.SCULK_TENDRIL_BLOCK.get().defaultBlockState();
    }

}
