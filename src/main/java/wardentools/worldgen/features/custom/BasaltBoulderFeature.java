package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class BasaltBoulderFeature extends Feature<NoneFeatureConfiguration> {
    private static final float RADIUS_MIN = 2.0F;
    private static final float RADIUS_MAX = 4.0F;
    private static final float HEIGHT_MIN = 2.0F;
    private static final float HEIGHT_MAX = 4.0F;

    public BasaltBoulderFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = level.getRandom();

        float radius = RADIUS_MIN + random.nextFloat() * (RADIUS_MAX - RADIUS_MIN);
        float height = HEIGHT_MIN + random.nextFloat() * (HEIGHT_MAX - HEIGHT_MIN);

        for (int y = -6; y < (int)height; y++) {
            float layerRadius = radius * (1 - (y / height) * 0.3F);
            for (int x = -(int)(layerRadius + 1); x <= layerRadius + 1; x++) {
                for (int z = -(int)(layerRadius + 1); z <= layerRadius + 1; z++) {
                    float distanceSquared = x * x + z * z;
                    if (Math.sqrt(distanceSquared) < layerRadius) {
                        if (y >= -3 || level.isEmptyBlock(origin.offset(x, y, z))) {
                            level.setBlock(origin.offset(x, y, z),
                                    Blocks.BASALT.defaultBlockState(),
                                    Block.UPDATE_ALL);
                        }
                    }
                }
            }
        }
        return true;
    }
}
