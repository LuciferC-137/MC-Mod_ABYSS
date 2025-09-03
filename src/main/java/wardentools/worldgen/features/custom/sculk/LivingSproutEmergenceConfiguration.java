package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;


public record LivingSproutEmergenceConfiguration(int minLength, int maxLength,
                                                 float shiftProb, boolean upward) implements FeatureConfiguration {
    public static final Codec<LivingSproutEmergenceConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.intRange(1, 30).fieldOf("min_length")
                    .forGetter(LivingSproutEmergenceConfiguration::minLength),
                Codec.intRange(1, 50).fieldOf("max_length")
                    .forGetter(LivingSproutEmergenceConfiguration::maxLength),
                Codec.floatRange(0F, 1F).fieldOf("shift_prob")
                        .forGetter(LivingSproutEmergenceConfiguration::shiftProb),
                Codec.BOOL.fieldOf("upward")
                        .forGetter(LivingSproutEmergenceConfiguration::upward)
        ).apply(instance, LivingSproutEmergenceConfiguration::new);
    });

    public LivingSproutEmergenceConfiguration(int minLength, int maxLength,
                                              float shiftProb, boolean upward) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.shiftProb = shiftProb;
        this.upward = upward;
    }
}