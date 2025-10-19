package wardentools.worldgen.features.custom.sculk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilTree;
import wardentools.blockentity.SculkTendrilBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class TendrilTreeUtils {
    public static final int MAX_ELONGATION = 8;


    // Adds a chain of positions to the tree starting from origin, following the given directions.
    // Returns the list of positions where branching should occur.
    public static List<BlockPos> addPosToTree(TendrilTree tree, BlockPos origin, List<Direction> directions,
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

    public static Direction getRandomHorizontalDirectionExcept(@Nullable Direction... exception) {
        Direction[] directions = Direction.Plane.HORIZONTAL.stream()
                .filter(dir -> exception == null || !List.of(exception).contains(dir))
                .toArray(Direction[]::new);
        return directions.length > 0 ? directions[(int)(Math.random() * directions.length)] : Direction.NORTH;
    }

    public static void placeBlocksWithoutUpdate(WorldGenLevel level, TendrilTree tree, BlockPos origin) {
        List<BlockPos> toRemove = new ArrayList<>();
        for (BlockPos pos : tree.getAllNodes()) {
            if (Math.abs(origin.getX() - pos.getX()) <= MAX_ELONGATION &&
                    Math.abs(origin.getZ() - pos.getZ()) <= MAX_ELONGATION) {
                if (!level.getBlockState(pos).isAir()) {
                    toRemove.add(pos);
                }
            } else {
                toRemove.add(pos);
            }
        }
        for (BlockPos pos : toRemove) {
            tree.recursiveRemove(pos);
        }
        for (BlockPos pos : tree.getAllNodes()) {
            level.setBlock(pos, sculkTendril(), 3);
        }
    }


    public static void configureBlockEntities(WorldGenLevel level, TendrilTree tree, BlockPos origin) {
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
