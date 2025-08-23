package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;



public class SculkTendrilsEmergenceConfiguration implements FeatureConfiguration {
    public final int maxLength; // The length of the first branch
    public final float deviationStrength; // Between 1 and 0.001. The higher, the more vertical.
    public final int maxDepth; // Max number of consecutive branches
    public final float heightReductionFactor; // The length is multiplied by this factor for each branch level
    public final float branchProbability; // Probability of branching for each block. Is also reduced by branch level

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