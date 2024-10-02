package wardentools.worldgen.features.custom.cristals;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class CristalVeinConfiguration implements FeatureConfiguration {
    public final int maxLength;
    public final int spreadFactor;
    public final int cristalBudProbability;
    public final BlockState cristalBlock;
    public final BlockState cristalBud;

    public static final Codec<CristalVeinConfiguration> CODEC
            = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength),
                Codec.INT.fieldOf("spread_factor").forGetter(config -> config.spreadFactor),
                Codec.INT.fieldOf("cristal_bud_probability").forGetter(config -> config.cristalBudProbability),
                BlockState.CODEC.fieldOf("cristal_block").forGetter(config -> config.cristalBlock),
                BlockState.CODEC.fieldOf("cristal_bud").forGetter(config -> config.cristalBud)
    ).apply(instance, CristalVeinConfiguration::new));

    public CristalVeinConfiguration(int maxLength, int spreadFactor,
                                    int cristalBudProbability, BlockState cristalBlock,
                                    BlockState cristalBud) {
        this.maxLength = maxLength;
        this.spreadFactor = spreadFactor;
        this.cristalBudProbability = cristalBudProbability;
        this.cristalBlock = cristalBlock;
        this.cristalBud = cristalBud;
    }
}