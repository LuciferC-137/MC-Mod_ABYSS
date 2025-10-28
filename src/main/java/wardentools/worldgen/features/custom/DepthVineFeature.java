package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import wardentools.block.BlockRegistry;
import wardentools.block.depthvines.DepthVines;

public class DepthVineFeature extends Feature<DepthVineConfiguration> {
    private static final int MAX_REACH = 150;
    private static final float CHANCE_BERRY_ON_PLACEMENT = 0.11F;

    public DepthVineFeature(Codec<DepthVineConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<DepthVineConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        DepthVineConfiguration config = context.config();

        int k = 0;
        while (!level.getBlockState(origin).isAir() && k < MAX_REACH) {
            origin = origin.above();
            k++;
        }

        BlockPos pos = origin.above();

        int i = 0;
        while (level.getBlockState(pos).isAir() && i < MAX_REACH) {
            pos = pos.above();
            i++;
        }

        if (DepthVines.canHangBelow(level.getBlockState(pos), level, pos)) {

            int length;
            if (config.adaptative()) {
                int totalSpace = pos.getY() - origin.getY()
                        + availableSpaceBelow(level, origin, MAX_REACH);
                length = nextTriangular(context.random(),
                        (int)((float)totalSpace * 0.2F),
                        (int)((float)totalSpace * 0.85F),
                        (int)((float)totalSpace * 0.55F)
                        );
            } else {
                length = nextTriangular(context.random(), config.min(), config.max(),
                        config.averageLength());
            }

            BlockPos placePos = pos.below();
            for (int j = 0; j < length; j++) {
                if (level.getBlockState(placePos).isAir()) {
                    boolean hasBerries = context.random().nextFloat() < CHANCE_BERRY_ON_PLACEMENT;
                    level.setBlock(placePos, BlockRegistry.DEPTH_VINES.get().defaultBlockState()
                            .setValue(DepthVines.BERRIES, hasBerries), Block.UPDATE_ALL);
                    placePos = placePos.below();
                } else {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public static int availableSpaceBelow(WorldGenLevel level, BlockPos pos, int max) {
        int space = 0;
        while (level.getBlockState(pos).isAir() && space < max) {
            pos = pos.below();
            space++;
        }
        return space;
    }

    public static int nextTriangular(RandomSource random, double min, double max, double mode) {
        double u = random.nextDouble();
        double c = (mode - min) / (max - min);
        if (u < c) {
            return Math.round(Math.round(min + Math.sqrt(u * (max - min) * (mode - min))));
        } else {
            return Math.round(Math.round(max - Math.sqrt((1 - u) * (max - min) * (max - mode))));
        }
    }


}
