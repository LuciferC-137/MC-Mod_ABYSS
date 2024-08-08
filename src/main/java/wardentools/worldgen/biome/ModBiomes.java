package wardentools.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
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
import wardentools.worldgen.carvers.ModConfiguredCarver;

public class ModBiomes {
	private static final int FOG_COLOR = 0x010d24;
	private static final int WATER_COLOR = 0x0a4c5b;
	
	public static final ResourceKey<Biome> DEEP_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "deep_forest"));
	public static final ResourceKey<Biome> WASTE_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "waste_land"));
	public static final ResourceKey<Biome> WHITE_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "white_forest"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(DEEP_FOREST, deepForest(context));
        context.register(WASTE_LAND, wasteLand(context));
        context.register(WHITE_FOREST, whiteForest(context));
    }

    public static void globalAbyssGeneration(BiomeGenerationSettings.Builder builder) {
        //BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
    }
    
    public static void defaultAbyssCaves(BiomeGenerationSettings.Builder builder) {
    	builder.addCarver(GenerationStep.Carving.AIR, ModConfiguredCarver.ABYSS_CAVE);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
    }

    public static Biome deepForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(), 100, 1, 4));
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 1, 1, 1));
        spawnBuilder.creatureGenerationProbability(0.2F);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        //need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalAbyssGeneration(biomeBuilder);
        
        defaultAbyssCaves(biomeBuilder);
        
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavePlacements.SCULK_PATCH_DEEP_DARK);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, CavePlacements.SCULK_VEIN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARKTREE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DEEPFLOWER_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLUE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TALL_DARK_GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARK_GRASS_KEY);
        
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                .waterColor(WATER_COLOR)
                .waterFogColor(FOG_COLOR)
                .skyColor(FOG_COLOR)
                .grassColorOverride(0x147B75)
                .foliageColorOverride(0x147B63)
                .fogColor(FOG_COLOR)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                //.backgroundMusic(Musics.createGameMusic())
                .build()).build();
    }
    
    public static Biome wasteLand(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(), 5, 1, 4));
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 1, 1, 1));
        spawnBuilder.creatureGenerationProbability(0.1F);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        globalAbyssGeneration(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addSculk(biomeBuilder);
        
        defaultAbyssCaves(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                .waterColor(WATER_COLOR)
                .waterFogColor(FOG_COLOR)
                .skyColor(FOG_COLOR)
                .grassColorOverride(0x147B75)
                .foliageColorOverride(0x147B63)
                .fogColor(FOG_COLOR)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                //.backgroundMusic(Musics.createGameMusic())
                .build()).build();
    }
    
    public static Biome whiteForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 1, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.PALE_WANDERER.get(), 4, 1, 1));
        spawnBuilder.creatureGenerationProbability(0.3F);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        //need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalAbyssGeneration(biomeBuilder);
        
        defaultAbyssCaves(biomeBuilder);
        
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WHITETREE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WHITE_GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TALL_WHITE_GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WHITE_TORCHFLOWER_KEY);
        
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                .waterColor(WATER_COLOR)
                .waterFogColor(FOG_COLOR)
                .skyColor(FOG_COLOR)
                .grassColorOverride(0x147B75)
                .foliageColorOverride(0x147B63)
                .fogColor(FOG_COLOR)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                //.backgroundMusic(Musics.createGameMusic())
                .build()).build();
    }

}
