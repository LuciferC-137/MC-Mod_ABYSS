package wardentools.datagen;


import com.google.common.collect.ImmutableSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import wardentools.ModMain;
import wardentools.worldgen.CustomNoiseSettings;
import wardentools.worldgen.biome.ModBiomes;
import wardentools.worldgen.carvers.ModConfiguredCarver;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.features.ModConfiguredFeatures;
import wardentools.worldgen.features.ModPlacedFeatures;

import java.util.concurrent.CompletableFuture;


public class ModDataBuilderProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // WorldGen Providers
    		.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
    		.add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
    		.add(Registries.BIOME, ModBiomes::bootstrap)
    		.add(Registries.CONFIGURED_CARVER, ModConfiguredCarver::bootstrap)
            .add(Registries.NOISE_SETTINGS, CustomNoiseSettings::bootstrap);

    public ModDataBuilderProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, ImmutableSet.of(ModMain.MOD_ID, "minecraft"));

    }
}