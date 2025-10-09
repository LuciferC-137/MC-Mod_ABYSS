package wardentools.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.client.color.AbyssBiomeColorCache;

public class DarkGrassBlock extends Block {
    private static final int DEEP_FOREST_1 = 0x0c2b22;
    private static final int DEEP_FOREST_2 = 0x04252c;
    private static final int WHITE_FOREST_1 = 0x77b1ac;
    private static final int WHITE_FOREST_2 = 0x294d55;

    public DarkGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (level.getBlockState(blockPos.above()).isCollisionShapeFullBlock(level, blockPos.above())) {
            level.setBlockAndUpdate(blockPos, BlockRegistry.DARKDIRT.get().defaultBlockState());
        }
    }

    public static int getColor(@NotNull BlockState state,
                               @Nullable BlockAndTintGetter getter,
                               @Nullable BlockPos pos,
                               int index) {

        if (pos == null) return index == 0 ? DEEP_FOREST_1 : DEEP_FOREST_2;

        Integer cached = AbyssBiomeColorCache.get(pos, index);
        if (cached != null) return cached;

        Level level = (getter instanceof LevelReader lr)
                ? (Level) lr
                : Minecraft.getInstance().level;

        if (level == null) return index == 0 ? DEEP_FOREST_1 : DEEP_FOREST_2;

        int color = getBlendedBiomeValue(level, pos, index);

        AbyssBiomeColorCache.put(pos, index, color);
        return color;
    }

    private static int getBlendedBiomeValue(LevelReader level, BlockPos pos, int index) {
        final int radius = 1;

        int rSum = 0;
        int gSum = 0;
        int bSum = 0;
        int count = 0;

        for (int dz = -radius; dz <= radius; dz++) {
            for (int dx = -radius; dx <= radius; dx++) {
                BlockPos samplePos = pos.offset(dx, 0, dz);

                Biome biome = level.getBiome(samplePos).value();
                ResourceLocation biomeId = level.registryAccess()
                        .registryOrThrow(Registries.BIOME)
                        .getKey(biome);

                float mix = 0.5f;
                if (biomeId != null) {
                    if (biomeId.getPath().contains("white_forest")) mix = 1.0f;
                    else if (biomeId.getPath().contains("deepforest")) mix = 0.0f;
                }

                int colorA = (index == 0) ? DEEP_FOREST_1 : DEEP_FOREST_2;
                int colorB = (index == 0) ? WHITE_FOREST_1 : WHITE_FOREST_2;

                int color = lerpColor(colorA, colorB, mix);

                rSum += (color >> 16) & 0xFF;
                gSum += (color >> 8) & 0xFF;
                bSum += color & 0xFF;
                count++;
            }
        }

        int r = rSum / count;
        int g = gSum / count;
        int b = bSum / count;
        return (r << 16) | (g << 8) | b;
    }

    private static int lerpColor(int c1, int c2, float t) {
        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;
        int r2 = (c2 >> 16) & 0xFF;
        int g2 = (c2 >> 8) & 0xFF;
        int b2 = c2 & 0xFF;
        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);
        return (r << 16) | (g << 8) | b;
    }

}
