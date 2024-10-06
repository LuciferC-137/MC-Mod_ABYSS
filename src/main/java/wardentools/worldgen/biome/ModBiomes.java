package wardentools.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.particle.ParticleRegistry;
import wardentools.sounds.ModMusics;
import wardentools.worldgen.features.ModPlacedFeatures;
import wardentools.worldgen.carvers.ModConfiguredCarver;

public class ModBiomes {
	private static final int FOG_COLOR = 0x000b1c;
	private static final int WATER_COLOR = 0x0a4c5b;
	
	public static final ResourceKey<Biome> DEEP_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "deep_forest"));
	public static final ResourceKey<Biome> WASTE_LAND = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "waste_land"));
	public static final ResourceKey<Biome> WHITE_FOREST = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "white_forest"));
    public static final ResourceKey<Biome> CRISTAL_CAVE = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(ModMain.MOD_ID, "cristal_cave"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(DEEP_FOREST, deepForest(context));
        context.register(WASTE_LAND, wasteLand(context));
        context.register(WHITE_FOREST, whiteForest(context));
        context.register(CRISTAL_CAVE, cristalCave(context));
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

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
        defaultAbyssSculk(biomeBuilder);

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
                .specialEffects(commonBiomeSpecialEffects().build()).build();
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
        defaultAbyssSculk(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacedFeatures.SHARP_ROCK_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects().build()).build();
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

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
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
                .specialEffects(commonBiomeSpecialEffects().build()).build();
    }

    public static Biome cristalCave(BootstapContext<Biome> context) {
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
        defaultAbyssOres(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);

        cristalOnCaveWalls(biomeBuilder);

        BiomeDefaultFeatures.addLushCavesVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .foliageColorOverride(0x57ab6a)
                        .grassColorOverride(0x57ab6a).build()).build();
    }

    public static void globalAbyssGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        builder.addFeature(GenerationStep.Decoration.RAW_GENERATION, ModPlacedFeatures.LIQUID_CORRUPTION_LAKE_KEY);
    }

    public static void defaultAbyssCaves(BiomeGenerationSettings.Builder builder) {
        builder.addCarver(GenerationStep.Carving.AIR, ModConfiguredCarver.ABYSS_CAVE);
    }

    public static void defaultAbyssOres(BiomeGenerationSettings.Builder builder){
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COAL_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.LAPIS_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.DIAMOND_ORE_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.DEEP_ORE_KEY);
    }

    public static void defaultAbyssSculk(BiomeGenerationSettings.Builder builder){
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, CavePlacements.SCULK_PATCH_DEEP_DARK);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, CavePlacements.SCULK_VEIN);
    }

    public static BiomeSpecialEffects.Builder commonBiomeSpecialEffects(){
        return new BiomeSpecialEffects.Builder()
                .waterColor(WATER_COLOR)
                .waterFogColor(FOG_COLOR)
                .skyColor(FOG_COLOR)
                .grassColorOverride(0x147B75)
                .foliageColorOverride(0x147B63)
                .fogColor(FOG_COLOR)
                .backgroundMusic(ModMusics.DEEP_FOREST)
                .ambientParticle(new AmbientParticleSettings(ParticleRegistry.ABYSS_AMBIENT.get(), 0.0025F))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
    }

    public static void cristalOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.MALACHITE_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.CITRINE_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.RUBY_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.ECHO_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.PALE_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.AMETHYST_CRISTAL_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.MALACHITE_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.CITRINE_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.RUBY_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.ECHO_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.PALE_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.AMETHYST_CRISTAL_DW_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.MALACHITE_VEIN_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.RUBY_VEIN_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.CITRINE_VEIN_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.ECHO_VEIN_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.PALE_CRISTAL_VEIN_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.AMETHYST_VEIN_KEY);
    }
}
