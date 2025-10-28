package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;



public record SculkTendrilsEmergenceConfiguration (int maxLength, // The length of the first branch
                                                   float deviationStrength, // Between 1 and 0.001. The higher, the more vertical.
                                                   int maxDepth, // Max number of consecutive branches
                                                   float heightReductionFactor, // The length is multiplied by this factor for each branch level
                                                   float branchProbability, // Probability of branching for each block. Is also reduced by branch level
                                                   boolean upward // Is upward (or downward)
                                                  ) implements FeatureConfiguration {


    public static final Codec<SculkTendrilsEmergenceConfiguration> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength),
                    Codec.FLOAT.fieldOf("deviation_strength").forGetter(config -> config.deviationStrength),
                    Codec.INT.fieldOf("max_depth").forGetter(config -> config.maxDepth),
                    Codec.FLOAT.fieldOf("height_reduction_factor").forGetter(config -> config.heightReductionFactor),
                    Codec.FLOAT.fieldOf("branch_probability").forGetter(config -> config.branchProbability),
                    Codec.BOOL.fieldOf("upward").forGetter(config -> config.upward)
                    ).apply(instance, SculkTendrilsEmergenceConfiguration::new));

    public SculkTendrilsEmergenceConfiguration setDownward() {
        return new SculkTendrilsEmergenceConfiguration(this.maxLength,
                this.deviationStrength, this.maxDepth, this.heightReductionFactor,
                this.branchProbability, false);
    }

    public SculkTendrilsEmergenceConfiguration setUpward() {
        return new SculkTendrilsEmergenceConfiguration(this.maxLength,
                this.deviationStrength, this.maxDepth, this.heightReductionFactor,
                this.branchProbability, true);
    }
}