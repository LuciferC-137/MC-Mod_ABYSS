package wardentools.client.color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.tags.ModTags;
import wardentools.worldgen.biome.ModBiomes;

import org.jetbrains.annotations.Nullable;

import static wardentools.block.VerdantGrassBlock.AURORA_VALLEY_GREEN;
import static wardentools.block.VerdantGrassBlock.AURORA_VALLEY_LIGHTER;

@OnlyIn(Dist.CLIENT)
public class ContextColorMethods {

    public static int getVerdantGrassColor(BlockState state, @Nullable BlockAndTintGetter getter,
                                           @Nullable BlockPos pos, int index) {
        if (pos == null || getter == null) return defaultColors(index);
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return defaultColors(index);
        Holder<Biome> biome = level.getBiome(pos);
        if (biome.is(ModTags.Biomes.AURORA_VALLEY)) {
            return auroraValleyOverride(getter, pos, index);
        }
        return defaultBiomeColors(getter, pos, index);
    }

    public static int auroraValleyOverride(@Nullable BlockAndTintGetter getter,
                                           @Nullable BlockPos pos, int index) {
        if (getter == null || pos == null) return defaultColors(index);
        int color = BiomeColors.getAverageGrassColor(getter, pos);
        float proximity = auroraValleyProximity(BiomeColors.getAverageGrassColor(getter, pos));
        if (index == 0) {
            return ColorUtils.lerpColor(color, AURORA_VALLEY_GREEN, proximity);
        } else if (index == 1) {
            return ColorUtils.lerpColor(color, AURORA_VALLEY_LIGHTER, proximity);
        }
        return defaultBiomeColors(getter, pos, index);
    }

    public static float auroraValleyProximity(int colorGrass) {
        return 1F - (float)Math.abs(colorGrass - ModBiomes.AURORA_VALLEY_1)
                / (float)Math.abs(colorGrass + ModBiomes.AURORA_VALLEY_2) * 2f;
    }

    public static int defaultBiomeColors(BlockAndTintGetter getter,
                                         BlockPos pos, int index) {
        if (index == 0) {
            return BiomeColors.getAverageGrassColor(getter, pos);
        } else if (index == 1) {
            return BiomeColors.getAverageFoliageColor(getter, pos);
        } else return -1;
    }

    public static int defaultColors(int index) {
        if (index == 0) return ModBiomes.AURORA_VALLEY_1;
        else if (index == 1) return ModBiomes.AURORA_VALLEY_2;
        else return -1;
    }
}
