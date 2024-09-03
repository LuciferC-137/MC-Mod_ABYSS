package wardentools.worldgen.features.custom.cristals;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class CristalFormationConfiguration implements FeatureConfiguration {
    public final int maxLength;
    public final float lengthToRadiusRatio;
    public final int cristalBudRarity;
    public final BlockState cristalBlock;
    public final BlockState cristalBud;

    public static final Codec<CristalFormationConfiguration> CODEC
            = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("max_length").forGetter(config -> config.maxLength),
                Codec.FLOAT.fieldOf("length_to_radius_ratio").forGetter(config -> config.lengthToRadiusRatio),
                Codec.INT.fieldOf("cristal_bud_rarity").forGetter(config -> config.cristalBudRarity),
                BlockState.CODEC.fieldOf("cristal_block").forGetter(config -> config.cristalBlock),
                BlockState.CODEC.fieldOf("cristal_bud").forGetter(config -> config.cristalBud)
    ).apply(instance, CristalFormationConfiguration::new));

    public CristalFormationConfiguration(int maxLength, float lengthToRadiusRatio,
                                         int cristalBudRarity, BlockState cristalBlock,
                                         BlockState cristalBud) {
        this.maxLength = maxLength;
        this.lengthToRadiusRatio = lengthToRadiusRatio;
        this.cristalBudRarity = cristalBudRarity;
        this.cristalBlock = cristalBlock;
        this.cristalBud = cristalBud;
    }
}