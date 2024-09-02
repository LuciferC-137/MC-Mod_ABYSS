package wardentools.worldgen.features;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.worldgen.features.custom.ReplaceAirBelowYFeature;
import wardentools.worldgen.features.custom.SharpRock;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES
            = DeferredRegister.create(ForgeRegistries.FEATURES, ModMain.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> REPLACE_AIR_BELOW_Y
            = FEATURES.register("replace_air_below_y",
                () -> new ReplaceAirBelowYFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SHARP_ROCK
            = FEATURES.register("sharp_rock",
                () -> new SharpRock(NoneFeatureConfiguration.CODEC));
}
