package wardentools.misc.wind;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wardentools.tags.ModTags;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class ContextualWind {
    private static final Map<TagKey<Biome>, WhisperTags.Tag> biomeToTagMap = Map.of(
            ModTags.Biomes.DEEPFOREST, WhisperTags.Tag.DEEPFOREST,
            ModTags.Biomes.WHITE_FOREST, WhisperTags.Tag.WHITE_FOREST,
            ModTags.Biomes.CRYSTAL_CAVES, WhisperTags.Tag.CRYSTAL_CAVE,
            BiomeTags.IS_OVERWORLD, WhisperTags.Tag.OVERWORLD,
            ModTags.Biomes.AMETHYST_CAVES, WhisperTags.Tag.AMETHYST_CAVE,
            ModTags.Biomes.RUBY_CAVES, WhisperTags.Tag.RUBY_CAVE,
            ModTags.Biomes.CITRINE_CAVES, WhisperTags.Tag.CITRINE_CAVE,
            ModTags.Biomes.MALACHITE_CAVES, WhisperTags.Tag.MALACHITE_CAVE,
            ModTags.Biomes.ECHO_CAVES, WhisperTags.Tag.ECHO_CAVE,
            ModTags.Biomes.PALE_CAVES, WhisperTags.Tag.PALE_CAVE
    );

    public static WhisperTags.Tag getTagForBiome(Holder<Biome> biomeHolder) {
        return getTagForBiome(biomeHolder.getTagKeys());
    }

    private static WhisperTags.Tag getTagForBiome(Stream<TagKey<Biome>> biome) {
        return biome.map(biomeToTagMap::get)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(WhisperTags.Tag.SMALL_TALK);
    }
}
