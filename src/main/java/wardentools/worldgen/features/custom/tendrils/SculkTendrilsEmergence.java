package wardentools.worldgen.features.custom.tendrils;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.Nullable;
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilTree;
import wardentools.blockentity.SculkTendrilBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class SculkTendrilsEmergence extends Feature<SculkTendrilsEmergenceConfiguration>  {
    private static final int MAX_ELONGATION = 8;
    private static final int MAX_TOTAL_NODES = 1000; // Security. Should not be reached.
    private static final float MIN_BRANCH_PROBABILITY = 0.01f;

    public SculkTendrilsEmergence(Codec<SculkTendrilsEmergenceConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SculkTendrilsEmergenceConfiguration> context) {
        WorldGenLevel level = context.level();
        float deviationStrength = Math.min(1.0f, Math.max(0.001F, context.config().deviationStrength));
        int maxDepth = Math.max(0, Math.min(10, context.config().maxDepth));
        float heightReductionFactor = Math.max(0.1f, Math.min(0.9f, context.config().heightReductionFactor));
        float branchProbability = Math.max(0.0f, Math.min(0.8f, context.config().branchProbability));
        int maxLength = Math.max(1, Math.min(20, context.config().maxLength));

        TendrilTree tree = new TendrilTree(context.origin());

        recursiveTreeStructure(tree, context.origin(), deviationStrength,
                0, maxDepth, maxLength, heightReductionFactor, branchProbability, level.getRandom(),
                Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom()));

        if (tree.getAllNodes().size() > MAX_TOTAL_NODES) return false;

        placeBlocksWithoutUpdate(level, tree, context.origin());
        configureBlockEntities(level, tree, context.origin());
        tree.updateAllNodes(level);

        tree.updateAllNodes(level);
        return true;
    }

    private void recursiveTreeStructure(TendrilTree tree, BlockPos origin, float deviationStrength,
                                        int depth, int maxDepth, int maxLength, float heightReductionFactor,
                                        float branchProbability, RandomSource random, Direction directionToCenter) {
        if (depth > maxDepth) return;

        int lengthOfThisBranch = Math.max(1, (int)((float)maxLength * heightReductionFactor));
        float currentBranchingProbability = Math.max(MIN_BRANCH_PROBABILITY, branchProbability * heightReductionFactor);
        List<Direction> currentBranch = new ArrayList<>();
        Direction mainDirectionForThisBranch = getRandomHorizontalDirectionExcept(
                directionToCenter, directionToCenter.getOpposite());

        float x = 0;
        float y = 0;

        for (int i = 0; i < lengthOfThisBranch; i++) {
            float curve = x * x * deviationStrength;
            if (curve > y) {
                y++;
                currentBranch.add(Direction.UP);
            } else {
                x++;
                currentBranch.add(mainDirectionForThisBranch);
            }
        }

        List<BlockPos> branchingPos = addPosToTree(tree, origin, currentBranch,
                currentBranchingProbability, random);

        for (BlockPos pos : branchingPos) {
            if (tree.hasNode(pos)) {
                recursiveTreeStructure(tree, pos, deviationStrength, depth + 1, maxDepth, lengthOfThisBranch,
                        heightReductionFactor, currentBranchingProbability, random, mainDirectionForThisBranch);
            } else {
                System.out.println("WARNING: Trying to branch from non-existent node: " + pos);
            }
        }
    }

    private List<BlockPos> addPosToTree(TendrilTree tree, BlockPos origin, List<Direction> directions,
                                        float branchingProbability, RandomSource random) {
        BlockPos currentPos = copyPos(origin);
        if (!tree.hasNode(origin)) {
            return new ArrayList<>();
        }
        List<BlockPos> branchingPos = new ArrayList<>();
        for (Direction direction : directions) {
            BlockPos nextPos = currentPos.relative(direction);
            tree.addNode(nextPos, currentPos);
            if (random.nextFloat() < branchingProbability) branchingPos.add(nextPos);
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

    private static BlockPos copyPos(BlockPos pos) {
        return new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    }

}