package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SharpRock  extends Feature<NoneFeatureConfiguration> {
    private static final float INCLINATION = 1.5f;
    private static final float BASE_TO_LENGTH_RATIO = 5f;
    private static final int THICK_MAX = 5;
    private static final int THICK_MIN = 2;

    public SharpRock(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos origin = context.origin();
        int base_radius = context.level().getRandom().nextInt(THICK_MIN, THICK_MAX);
        int length = (int)((context.level().getRandom().nextFloat() + 0.5f) * BASE_TO_LENGTH_RATIO * (float)base_radius);
        float xoffset = (context.level().getRandom().nextFloat() - 0.5f) * INCLINATION;
        float zoffset = (context.level().getRandom().nextFloat() - 0.5f) * INCLINATION;
        for (int y = -3; y < length; y++){
            int x = (int)(xoffset * (float)y);
            int z = (int)(zoffset * (float)y);
            float radius = (float)base_radius * (float)(length - y) / (float)(length);
            if (radius > 0.8){
                circle(context, worldGenLevel, origin.offset(x, y, z), radius);
            }
        }
        return false;
    }

    private static void circle(FeaturePlaceContext<NoneFeatureConfiguration> context,
                               WorldGenLevel worldGenLevel, BlockPos center, float radius){
        float random_finition = radius > 2f ? context.level().getRandom().nextFloat() - 0.5f : 0f;
        for (int x = -(int)(radius + 0.5f); x <= radius; x++){
            for (int z = -(int)(radius + 0.5f); z <= radius; z++){
                if (x * x + z * z < radius * radius + random_finition){
                    worldGenLevel.setBlock(center.offset(x, 0, z),
                            Blocks.DEEPSLATE.defaultBlockState(),3);
                }
            }
        }
    }
}
