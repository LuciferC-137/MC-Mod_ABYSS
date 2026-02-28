package wardentools.wind;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import wardentools.tags.ModTags;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;


public class ContextualWind {
    private static final HashMap<TagKey<Biome>, WhisperTags.Tag> biomeToTagMap = new HashMap<>();


    static {
        biomeToTagMap.put(ModTags.Biomes.AURORA_VALLEY, WhisperTags.Tag.AURORA_VALLEY);
        biomeToTagMap.put(ModTags.Biomes.DEEPFOREST, WhisperTags.Tag.DEEPFOREST);
        biomeToTagMap.put(ModTags.Biomes.WHITE_FOREST, WhisperTags.Tag.WHITE_FOREST);
        biomeToTagMap.put(ModTags.Biomes.CRYSTAL_CAVES, WhisperTags.Tag.CRYSTAL_CAVE);
        biomeToTagMap.put(BiomeTags.IS_OVERWORLD, WhisperTags.Tag.OVERWORLD);
        biomeToTagMap.put(ModTags.Biomes.AMETHYST_CAVES, WhisperTags.Tag.AMETHYST_CAVE);
        biomeToTagMap.put(ModTags.Biomes.RUBY_CAVES, WhisperTags.Tag.RUBY_CAVE);
        biomeToTagMap.put(ModTags.Biomes.CITRINE_CAVES, WhisperTags.Tag.CITRINE_CAVE);
        biomeToTagMap.put(ModTags.Biomes.MALACHITE_CAVES, WhisperTags.Tag.MALACHITE_CAVE);
        biomeToTagMap.put(ModTags.Biomes.ECHO_CAVES, WhisperTags.Tag.ECHO_CAVE);
        biomeToTagMap.put(ModTags.Biomes.PALE_CAVES, WhisperTags.Tag.PALE_CAVE);
    }

    public static WhisperTags.Tag getTagForBiome(Holder<Biome> biomeHolder) {
        return getTagForBiome(biomeHolder.tags());
    }

    private static WhisperTags.Tag getTagForBiome(Stream<TagKey<Biome>> biome) {
        return biome.map(biomeToTagMap::get)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(WhisperTags.Tag.SMALL_TALK);
    }
}
