package wardentools.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class LevelGetterUtils {

    private static PredicateFunction TAG_PREDICATE(TagKey<Block> blockTagKey) {
        return (level, pos) -> level.getBlockState(pos).is(blockTagKey);
    }

    private static PredicateFunction BLOCK_PREDICATE(Block block) {
        return (level, pos) -> level.getBlockState(pos).is(block);
    }

    public static BlockPos findClosest(Vec3 origin, LevelReader level, TagKey<Block> blockTagKey, int searchRadius) {
        return findClosest(origin, level, TAG_PREDICATE(blockTagKey), searchRadius, 0);
    }

    public static BlockPos findClosest(Vec3 origin, LevelReader level, Block block, int searchRadius) {
        return findClosest(origin, level, BLOCK_PREDICATE(block), searchRadius, 0);
    }

    public static BlockPos findClosest(Vec3 origin, LevelReader level, TagKey<Block> blockTagKey, int searchRadius, int heightRadius) {
        return findClosest(origin, level, TAG_PREDICATE(blockTagKey), searchRadius, heightRadius);
    }

    public static @Nullable BlockPos findClosest(Vec3 origin, LevelReader level, PredicateFunction blockPredicate,
                                                 int searchRadius, int heightRadius) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int originX = (int)origin.x();
        int originY = (int)origin.y();
        int originZ = (int)origin.z();

        int maxDistance = searchRadius + heightRadius;

        for (int distance = 0; distance <= maxDistance; distance++) {
            for (int dx = -Math.min(distance, searchRadius); dx <= Math.min(distance, searchRadius); dx++) {
                int min = Math.min(distance - Math.abs(dx), heightRadius);
                for (int dy = -min; dy <= min; dy++) {
                    int remainingDist = distance - Math.abs(dx) - Math.abs(dy);
                    if (remainingDist < 0) continue;

                    int[] dzValues = remainingDist == 0 ? new int[]{0} : new int[]{-remainingDist, remainingDist};
                    for (int dz : dzValues) {
                        if (Math.abs(dz) > searchRadius) continue;

                        mutableBlockPos.set(originX + dx, originY + dy, originZ + dz);

                        if (blockPredicate.test(level, mutableBlockPos)) {
                            return mutableBlockPos.immutable();
                        }
                    }
                }
            }
        }
        return null;
    }

    public interface PredicateFunction {
        boolean test(LevelReader level, BlockPos pos);
    }
}
