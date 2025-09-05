package wardentools.worldgen.features.custom.sculk;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.slf4j.Logger;
import wardentools.block.sculktendril.TendrilTree;

import java.util.ArrayList;
import java.util.List;

import static wardentools.worldgen.features.custom.sculk.TendrilTreeUtils.*;

public class SculkTendrilsEmergence extends Feature<SculkTendrilsEmergenceConfiguration>  {
    private static final int MAX_TOTAL_NODES = 1000; // Security. Should not be reached.
    private static final float MIN_BRANCH_PROBABILITY = 0.01f;
    private static final Logger LOGGER = LogUtils.getLogger();

    public SculkTendrilsEmergence(Codec<SculkTendrilsEmergenceConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SculkTendrilsEmergenceConfiguration> context) {
        WorldGenLevel level = context.level();
        boolean upward = context.config().upward();
        float deviationStrength = Math.min(1.0f, Math.max(0.001F, context.config().deviationStrength()));
        int maxDepth = Math.max(0, Math.min(10, context.config().maxDepth()));
        float heightReductionFactor = Math.max(0.1f, Math.min(0.9f, context.config().heightReductionFactor()));
        float branchProbability = Math.max(MIN_BRANCH_PROBABILITY, Math.min(0.8f, context.config().branchProbability()));
        int maxLength = Math.max(1, Math.min(20, context.config().maxLength()));

        TendrilTree tree = new TendrilTree(context.origin());

        // Placing initial origin holder
        this.setBlock(level,
                upward ? context.origin().below() : context.origin().above(),
                Blocks.SCULK.defaultBlockState());

         recursiveTreeStructure(tree, context.origin(), deviationStrength,
                 0, maxDepth, maxLength, heightReductionFactor, branchProbability, level.getRandom(),
                Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom()), upward);

        if (tree.getAllNodes().size() > MAX_TOTAL_NODES) return false;

        placeBlocksWithoutUpdate(level, tree, context.origin());
        configureBlockEntities(level, tree, context.origin());
        tree.updateAllNodes(level);

        return true;
    }

    private void recursiveTreeStructure(TendrilTree tree, BlockPos origin, float deviationStrength,
                                        int depth, int maxDepth, int maxLength, float heightReductionFactor,
                                        float branchProbability, RandomSource random, Direction directionToPrevious,
                                        boolean upward) {
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
                currentBranch.add(upward ? Direction.UP : Direction.DOWN);
            }
        } else {
            Direction mainDirection = getRandomHorizontalDirectionExcept(
                    directionToPrevious.getOpposite());
            int horizontalLength = Math.max(1, (int)((float)length * deviationStrength));
            for (int i = 0; i < horizontalLength; i++) {
                if (random.nextFloat() < 0.8F) {
                    currentBranch.add(mainDirection);
                } else {
                    currentBranch.add(getRandomHorizontalDirectionExcept(
                            mainDirection.getOpposite(), mainDirection));
                }
            }
            for (int i = horizontalLength; i < length; i++) {
                currentBranch.add(upward ? Direction.UP : Direction.DOWN);
            }
        }

        List<BlockPos> branchingPos = addPosToTree(tree, origin, currentBranch,
                currentBranchingProbability, random);

        for (BlockPos pos : branchingPos) {
            if (tree.hasNode(pos)) {
                recursiveTreeStructure(tree, pos, deviationStrength, depth + 1, maxDepth, currentMaxLength,
                        heightReductionFactor, currentBranchingProbability, random,
                        getRandomHorizontalDirectionExcept(directionToPrevious.getOpposite()), upward);
            } else {
                LOGGER.warn("Trying to branch from non-existent node: {}", pos);
            }
        }
    }
}
