package wardentools.worldgen.features.custom.tendrils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;



public class SculkTendrilsEmergenceConfiguration implements FeatureConfiguration {
    public final int maxLength;
    public final float deviationStrength;
    public final int maxDepth;
    public final float heightReductionFactor;
    public final float branchProbability;

    public static final Codec<SculkTendrilsEmergenceConfiguration> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength),
                    Codec.FLOAT.fieldOf("deviation_strength").forGetter(config -> config.deviationStrength),
                    Codec.INT.fieldOf("max_depth").forGetter(config -> config.maxDepth),
                    Codec.FLOAT.fieldOf("height_reduction_factor").forGetter(config -> config.heightReductionFactor),
                    Codec.FLOAT.fieldOf("branch_probability").forGetter(config -> config.branchProbability)
                    ).apply(instance, SculkTendrilsEmergenceConfiguration::new));

    public SculkTendrilsEmergenceConfiguration(int maxLength, float deviationStrength,
                                               int maxDepth, float heightReductionFactor,
                                               float branchProbability) {
        this.maxLength = maxLength;
        this.deviationStrength = deviationStrength;
        this.maxDepth = maxDepth;
        this.heightReductionFactor = heightReductionFactor;
        this.branchProbability = branchProbability;
    }
}