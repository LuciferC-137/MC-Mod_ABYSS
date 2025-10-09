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
import wardentools.client.color.ColorUtils;

public class DarkGrassBlock extends Block {
    private static final int DEEP_FOREST_1 = 0x094b41;
    private static final int DEEP_FOREST_2 = 0x052a32;
    private static final int WHITE_FOREST_1 = 0x77b1ac;
    private static final int WHITE_FOREST_2 = 0x294d55;

    private static final int RADIUS = 2; // TODO Make a client config option for this
    private static final int TOTAL_SAMPLES = (RADIUS * 2 + 1) * (RADIUS * 2 + 1);

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

        int whiteForestCount = 0;

        for (int dz = -RADIUS; dz <= RADIUS; dz++) {
            for (int dx = -RADIUS; dx <= RADIUS; dx++) {
                BlockPos samplePos = pos.offset(dx, 0, dz);

                Biome biome = level.getBiome(samplePos).value();
                ResourceLocation biomeId = level.registryAccess()
                        .registryOrThrow(Registries.BIOME)
                        .getKey(biome);

                if (biomeId != null) {
                    if (biomeId.getPath().contains("white_forest")) whiteForestCount++;
                }
            }
        }
        int colorA = (index == 0) ? DEEP_FOREST_1 : DEEP_FOREST_2;
        int colorB = (index == 0) ? WHITE_FOREST_1 : WHITE_FOREST_2;

        return ColorUtils.lerpColor(colorA, colorB,
                (float) whiteForestCount / TOTAL_SAMPLES);
    }

}
