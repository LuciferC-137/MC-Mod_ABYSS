package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;


public record AbyssLakeConfiguration(BlockStateProvider fluid,
                                     BlockStateProvider barrier,
                                     boolean hasNenuphar) implements FeatureConfiguration {
    public static final Codec<AbyssLakeConfiguration> CODEC = RecordCodecBuilder
            .create((instance)
                    -> instance
                    .group(BlockStateProvider.CODEC.fieldOf("fluid")
                                    .forGetter(AbyssLakeConfiguration::fluid),
                            BlockStateProvider.CODEC.fieldOf("barrier")
                                    .forGetter(AbyssLakeConfiguration::barrier),
                            Codec.BOOL.fieldOf("has_nenuphar")
                                    .forGetter(AbyssLakeConfiguration::hasNenuphar)
                    )
                    .apply(instance, AbyssLakeConfiguration::new));
}
