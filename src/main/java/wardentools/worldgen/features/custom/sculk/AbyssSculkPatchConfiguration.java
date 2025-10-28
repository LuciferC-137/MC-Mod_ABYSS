package wardentools.worldgen.features.custom.sculk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;


public record AbyssSculkPatchConfiguration(int chargeCount, int amountPerCharge,
                                           int spreadAttempts, int growthRounds, int spreadRounds,
                                           IntProvider extraRareGrowths, float catalystChance,
                                           boolean canHaveTendril) implements FeatureConfiguration {
    public static final Codec<AbyssSculkPatchConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.intRange(1, 32).fieldOf("charge_count")
                .forGetter(AbyssSculkPatchConfiguration::chargeCount),
            Codec.intRange(1, 500).fieldOf("amount_per_charge")
                .forGetter(AbyssSculkPatchConfiguration::amountPerCharge),
            Codec.intRange(1, 64).fieldOf("spread_attempts")
                .forGetter(AbyssSculkPatchConfiguration::spreadAttempts),
            Codec.intRange(0, 8).fieldOf("growth_rounds")
                .forGetter(AbyssSculkPatchConfiguration::growthRounds),
            Codec.intRange(0, 8).fieldOf("spread_rounds")
                .forGetter(AbyssSculkPatchConfiguration::spreadRounds),
            IntProvider.CODEC.fieldOf("extra_rare_growths")
                    .forGetter(AbyssSculkPatchConfiguration::extraRareGrowths),
            Codec.floatRange(0.0F, 1.0F).fieldOf("catalyst_chance")
                    .forGetter(AbyssSculkPatchConfiguration::catalystChance),
            Codec.BOOL.fieldOf("can_have_tendril").forGetter(AbyssSculkPatchConfiguration::canHaveTendril)
            ).apply(instance, AbyssSculkPatchConfiguration::new));
}