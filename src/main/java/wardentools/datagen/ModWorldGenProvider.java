package wardentools.datagen;


import net.minecraft.core.registries.Registries;
import wardentools.worldgen.CustomNoiseSettings;
import wardentools.worldgen.ModConfiguredFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import wardentools.ModMain;
import wardentools.worldgen.ModPlacedFeatures;
import wardentools.worldgen.biome.ModBiomes;
import wardentools.worldgen.carvers.ModConfiguredCarver;
import wardentools.worldgen.dimension.ModDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
    		.add(Registries.CONFIGURED_FEATURE, context -> {
                 ModConfiguredFeatures.bootstrap(context);})
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
    		.add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
    		.add(Registries.BIOME, ModBiomes::bootstrap)
    		.add(Registries.CONFIGURED_CARVER, ModConfiguredCarver::bootstrap)
            .add(Registries.NOISE_SETTINGS, CustomNoiseSettings::bootstrap)
    		;

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ModMain.MOD_ID));
    }
}