package wardentools.worldgen.features.custom.cristals;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;
import wardentools.tags.ModTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CristalVein extends Feature<CristalVeinConfiguration>  {

    public CristalVein(Codec<CristalVeinConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CristalVeinConfiguration> context) {
        placeAndSpread(context, context.origin(), 0, null);
        return true;
    }

    public void placeAndSpread(FeaturePlaceContext<CristalVeinConfiguration> context,
                               BlockPos currentPos, int distance, BlockPos parent) {
        context.level().setBlock(currentPos, context.config().cristalBlock, Block.UPDATE_ALL);
        for (Direction direction : Direction.values()) {
            if (context.level().getBlockState(currentPos.relative(direction)).isAir()) {
                if (context.level().getRandom().nextInt(0, context.config().cristalBudProbability) == 0){
                    context.level().setBlock(currentPos.relative(direction),
                            context.config().cristalBud.setValue(BlockStateProperties.FACING, direction),
                            Block.UPDATE_ALL);
                };
            }
        }
        if (distance < context.config().maxLength) {
            List<BlockPos> neighbors = getRandomNonAirNeighbors(context, currentPos, parent);
            for (BlockPos neighbor : neighbors) {
                placeAndSpread(context, neighbor, distance + 1, parent);
            }
        }
    }

    public static List<BlockPos> getRandomNonAirNeighbors(FeaturePlaceContext<CristalVeinConfiguration> context,
                                                          BlockPos pos, BlockPos parent) {
        List<BlockPos> validNeighbors = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    BlockPos neighborPos = pos.offset(x, y, z);
                    BlockState neighborState = context.level().getBlockState(neighborPos);
                    if (!neighborState.isAir()
                            && !neighborState.is(context.config().cristalBud.getBlock())
                            && BlockPredicate.solid().test(context.level(), neighborPos)
                            && isExposedToAir(context, neighborPos)
                            && farEnoughFromParent(parent, neighborPos)) {
                        validNeighbors.add(neighborPos);
                    }
                }
            }
        }
        Collections.shuffle(validNeighbors, new Random());
        int number_to_return = context.level().getRandom()
                .nextInt(context.config().spreadFactor / 2, context.config().spreadFactor * 3 / 2);
        return validNeighbors.stream().limit(number_to_return).toList();
    }

    public static boolean farEnoughFromParent(BlockPos parent, BlockPos neighbor){
        if (parent == null){
            return true;
        }
        return Math.abs(parent.getX() - neighbor.getX()) > 1 &&
                + Math.abs(parent.getY() - neighbor.getY()) > 1 &&
                + Math.abs(parent.getZ() - neighbor.getZ()) > 1;
    }

    public static boolean isExposedToAir(FeaturePlaceContext<CristalVeinConfiguration> context, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (context.level().getBlockState(pos.relative(direction)).isAir()) {
                return true;
            }
        }
        return false;
    }
}
