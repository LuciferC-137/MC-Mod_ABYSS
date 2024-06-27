package wardentools.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.worldgen.ModPlacedFeatures;

public class ModBiomes {
	
	public static final ResourceKey<Biome> DEEP_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "deep_forest"));
	public static final ResourceKey<Biome> WASTE_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "waste_land"));
	

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(DEEP_FOREST, deepForest(context));
        context.register(WASTE_LAND, wasteLand(context));
    }

    public static void globalAbyssGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
        BiomeDefaultFeatures.addSculk(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
    }

    public static Biome deepForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(), 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 5, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        //need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalAbyssGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARKTREE_PLACED_KEY);
        
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x0a4c5b)
                        .waterFogColor(0x296472)
                        .skyColor(0x063D45)
                        .grassColorOverride(0x147B75)
                        .foliageColorOverride(0x147B63)
                        .fogColor(0x082127)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic())
                        .build()).build();
    }
    
    public static Biome wasteLand(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(), 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 5, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        //need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalAbyssGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARKTREE_PLACED_KEY);
        
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x0a4c5b)
                        .waterFogColor(0x296472)
                        .skyColor(0x063D45)
                        .grassColorOverride(0x147B75)
                        .foliageColorOverride(0x147B63)
                        .fogColor(0x082127)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        //.backgroundMusic(Musics.createGameMusic())
                        .build()).build();
    }

}
