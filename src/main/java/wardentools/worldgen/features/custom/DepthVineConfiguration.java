package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;


public record DepthVineConfiguration(boolean adaptative, int averageLength, int min, int max) implements FeatureConfiguration {
    public static final int MAX_LENGTH = 100;

    public static final Codec<DepthVineConfiguration> CODEC = RecordCodecBuilder.create((instance)
            -> instance.group(
            Codec.BOOL.fieldOf("adaptative")
                .forGetter(DepthVineConfiguration::adaptative),
            Codec.intRange(1, MAX_LENGTH).fieldOf("average_length")
                .forGetter(DepthVineConfiguration::averageLength),
            Codec.intRange(0, MAX_LENGTH).fieldOf("min")
                .forGetter(DepthVineConfiguration::min),
            Codec.intRange(1, MAX_LENGTH).fieldOf("max")
                .forGetter(DepthVineConfiguration::max)
            ).apply(instance, DepthVineConfiguration::new));

    public DepthVineConfiguration(boolean adaptative, int averageLength, int min, int max) {
        this.adaptative = adaptative;
        this.averageLength = averageLength;
        this.min = min;
        this.max = max;
    }
}