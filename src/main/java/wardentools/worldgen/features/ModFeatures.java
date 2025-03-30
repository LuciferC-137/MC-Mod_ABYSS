package wardentools.worldgen.features;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.worldgen.features.custom.PlaceAbyssPortal;
import wardentools.worldgen.features.custom.ReplaceAirBelowYFeature;
import wardentools.worldgen.features.custom.SharpRock;
import wardentools.worldgen.features.custom.cristals.CristalFormation;
import wardentools.worldgen.features.custom.cristals.CristalFormationConfiguration;
import wardentools.worldgen.features.custom.cristals.CristalVein;
import wardentools.worldgen.features.custom.cristals.CristalVeinConfiguration;

import java.util.function.Supplier;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES
            = DeferredRegister.create(BuiltInRegistries.FEATURE, ModMain.MOD_ID);

    public static final Supplier<Feature<NoneFeatureConfiguration>> REPLACE_AIR_BELOW_Y
            = FEATURES.register("replace_air_below_y",
                () -> new ReplaceAirBelowYFeature(NoneFeatureConfiguration.CODEC));

    public static final Supplier<Feature<NoneFeatureConfiguration>> SHARP_ROCK
            = FEATURES.register("sharp_rock",
                () -> new SharpRock(NoneFeatureConfiguration.CODEC));

    public static final Supplier<Feature<CristalFormationConfiguration>> CRISTAL_FORMATION
            = FEATURES.register("cristal_formation",
                () -> new CristalFormation(CristalFormationConfiguration.CODEC));

    public static final Supplier<Feature<CristalVeinConfiguration>> CRISTAL_VEIN
            = FEATURES.register("cristal_vein", () -> new CristalVein(CristalVeinConfiguration.CODEC));

    public static final Supplier<Feature<NoneFeatureConfiguration>> ABYSS_PORTAL
            = FEATURES.register("abyss_portal", () -> new PlaceAbyssPortal(NoneFeatureConfiguration.CODEC));
}
