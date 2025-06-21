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
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilTree;
import wardentools.blockentity.SculkTendrilBlockEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SculkTendrilsEmergence extends Feature<SculkTendrilsEmergenceConfiguration>  {
    private static final int MAX_ELONGATION = 8;
    private static final int MAX_TOTAL_NODES = 1000; // Security. Should not be reached.
    private static final float MIN_BRANCH_PROBABILITY = 0.01f;
    private static final int MIN_HEIGHT = 1;

    public SculkTendrilsEmergence(Codec<SculkTendrilsEmergenceConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SculkTendrilsEmergenceConfiguration> context) {
        WorldGenLevel level = context.level();
        float deviationStrength = Math.max(1.0f, context.config().deviationStrength);
        int maxDepth = Math.max(0, Math.min(10, context.config().maxDepth));
        float heightReductionFactor = Math.max(0.1f, Math.min(0.9f, context.config().heightReductionFactor));
        float branchProbability = Math.max(0.0f, Math.min(0.8f, context.config().branchProbability));
        int maxLength = Math.max(1, Math.min(50, context.config().maxLength));

        // Generate the branch structure using directions
        HashMap<Integer, ArrayList<Branch>> branchMap = new HashMap<>();
        generateBranchStructure(branchMap, level.getRandom(), context.origin(),
                maxLength, branchProbability, deviationStrength, heightReductionFactor, maxDepth);

        // Convert branches to tree structure
        TendrilTree tree = buildTreeFromBranches(branchMap, context.origin());

        if (tree.getAllNodes().size() > MAX_TOTAL_NODES) return false;

        // Place blocks in the world
        placeBlocks(level, tree, context.origin());
        return true;
    }

    private void generateBranchStructure(HashMap<Integer, ArrayList<Branch>> branchMap, RandomSource random,
                                         BlockPos origin, int maxLength, float branchProbability,
                                         float deviationStrength,
                                         float branchReductionFactor, int maxDepth) {
        branchMap.put(0, new ArrayList<>());
        Branch trunk = new Branch(origin);
        buildSingleBranch(trunk, random, deviationStrength, maxLength);
        branchMap.get(0).add(trunk);

        for (int i = 0; i < trunk.directions.size(); i++) {
            if (random.nextFloat() < branchProbability && maxDepth > 0) {
                recursiveBranches(branchMap, random, trunk.getPosAt(i), maxLength, branchProbability,
                        deviationStrength, branchReductionFactor, maxDepth, 1);
            }
        }
    }

    private void recursiveBranches(HashMap<Integer, ArrayList<Branch>> branchMap, RandomSource random,
                                   BlockPos attachment, int maxLength, float branchProbability,
                                   float deviationStrength,
                                   float branchReductionFactor, int maxDepth, int currentDepth) {
        maxLength = Math.max(MIN_HEIGHT, (int)(maxLength * branchReductionFactor));
        branchProbability = Math.max(MIN_BRANCH_PROBABILITY, branchProbability * branchReductionFactor);

        Branch currentBranch = new Branch(attachment);
        buildSingleBranch(currentBranch, random, deviationStrength, maxLength);
        branchMap.computeIfAbsent(currentDepth, k -> new ArrayList<>());
        branchMap.get(currentDepth).add(currentBranch);

        for (int i = 0 ; i < currentBranch.directions.size(); i++) {
            if (random.nextFloat() < branchProbability && currentDepth < maxDepth) {
                recursiveBranches(branchMap, random, currentBranch.getPosAt(i), maxLength, branchProbability,
                        deviationStrength, branchReductionFactor, maxDepth, currentDepth + 1);
            }
        }
    }

    private void buildSingleBranch(Branch branch, RandomSource random,
                                   float deviationStrength, float height) {
        Direction mainDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        float accumulatedHeight = 0f;

        for (int i = 0; i < height; i++) {
            float t = (float) i / height;
            float curve = t * t / deviationStrength;

            if (curve > accumulatedHeight) {
                branch.add(Direction.UP);
                accumulatedHeight += 1;
            }
            branch.add(mainDirection);
        }
    }

    private TendrilTree buildTreeFromBranches(HashMap<Integer, ArrayList<Branch>> branchMap, BlockPos origin) {
        TendrilTree tree = new TendrilTree(origin);
        for (Map.Entry<Integer, ArrayList<Branch>> entry : branchMap.entrySet()) {
            System.out.println("Adding Branch with depth: " + entry.getKey());
            for (Branch branch : entry.getValue()) {
                System.out.println("Adding Branch: " + branch);
                BlockPos currentPos = branch.branchOrigin();
                BlockPos previousPos = branch.branchOrigin();
                for (Direction direction : branch.directions()) {
                    currentPos = currentPos.relative(direction);
                    if (!tree.hasNode(currentPos)) {
                        tree.addNode(currentPos, previousPos);
                    } else break;
                    previousPos = currentPos;
                }
            }
        }
        return tree;
    }

    private void placeBlocks(WorldGenLevel level, TendrilTree tree, BlockPos origin) {
        for (BlockPos pos : tree.getAllNodes()) {
            if (Math.abs(origin.getX() - pos.getX()) <= MAX_ELONGATION &&
                    Math.abs(origin.getZ() - pos.getZ()) <= MAX_ELONGATION) {
                placeTendrilBlock(level, pos, origin, tree);
            }
        }
    }

    private void placeTendrilBlock(WorldGenLevel level, BlockPos pos, BlockPos origin, TendrilTree tree) {
        BlockState state = sculkTendril();
        level.setBlock(pos, state, Block.UPDATE_ALL);
        if (level.getBlockEntity(pos) instanceof SculkTendrilBlockEntity tendrilEntity) {
            if (pos.equals(origin)) {
                tendrilEntity.setTendrilTreeGraph(tree);
            }
            tendrilEntity.setOrigin(origin);
        }
    }

    private static BlockState sculkTendril() {
        return BlockRegistry.SCULK_TENDRIL_BLOCK.get().defaultBlockState();
    }

    private record Branch(BlockPos branchOrigin, List<Direction> directions) {
        public Branch(BlockPos branchOrigin) {
            this(branchOrigin, new ArrayList<>());
        }

        public void add(Direction direction) {
            directions.add(direction);
        }

        public BlockPos getPosAt(int index) {
            if (index < 0 || index >= directions.size()) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + directions.size());
            }
            BlockPos posAt = this.branchOrigin;
            for (int i = 0; i < index; i++) {
                posAt = posAt.relative(directions.get(i));
            }
            return posAt;
        }

        public String toString() {
            return "Branch{" +
                    "branchOrigin=" + branchOrigin +
                    ", directions=" + directions +
                    '}';
        }
    }
}