package wardentools.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
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
    // COLOR 1 is encoded as the grass color
    // COLOR 2 is encoded as the foliage color
    public static final int DEEP_FOREST_1 = 0x094b41;
    public static final int DEEP_FOREST_2 = 0x052a32;
    public static final int WHITE_FOREST_1 = 0x77b1ac;
    public static final int WHITE_FOREST_2 = 0x294d55;
	private static final int FOG_COLOR = 0x000b1c;
	private static final int WATER_COLOR = 0x0a4c5b;
    private static final GenerationStep.Decoration CRYSTAL_STEP = GenerationStep.Decoration.LOCAL_MODIFICATIONS;
    private static final GenerationStep.Decoration SCULK_STEP = GenerationStep.Decoration.UNDERGROUND_DECORATION;
	
	public static final ResourceKey<Biome> DEEP_FOREST = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deep_forest"));
	public static final ResourceKey<Biome> WASTE_LAND = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "waste_land"));
	public static final ResourceKey<Biome> WHITE_FOREST = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_forest"));
    public static final ResourceKey<Biome> CRYSTAL_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cristal_cave"));
    public static final ResourceKey<Biome> BLINDING_DEPTH = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blinding_depth"));

    public static final ResourceKey<Biome> AMETHYST_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "amethyst_cave"));
    public static final ResourceKey<Biome> CITRINE_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine_cave"));
    public static final ResourceKey<Biome> MALACHITE_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite_cave"));
    public static final ResourceKey<Biome> RUBY_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby_cave"));
    public static final ResourceKey<Biome> ECHO_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "echo_cave"));
    public static final ResourceKey<Biome> PALE_CAVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_cave"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(DEEP_FOREST, deepForest(context));
        context.register(WASTE_LAND, wasteLand(context));
        context.register(WHITE_FOREST, whiteForest(context));
        context.register(CRYSTAL_CAVE, cristalCave(context));
        context.register(BLINDING_DEPTH, blindingDepth(context));
        context.register(AMETHYST_CAVE, amethystCave(context));
        context.register(CITRINE_CAVE, citrineCave(context));
        context.register(MALACHITE_CAVE, malachiteCave(context));
        context.register(RUBY_CAVE, rubyCave(context));
        context.register(ECHO_CAVE, echoCave(context));
        context.register(PALE_CAVE, paleCave(context));
    }

    public static Biome deepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(),
                        10, 1, 4));
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.NOCTILURE.get(),
                        1, 1, 1));
        spawnBuilder.creatureGenerationProbability(0.2F);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                		context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
        defaultAbyssSculk(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARKTREE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARK_GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLUE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DEEPFLOWER_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TALL_DARK_GRASS_KEY);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST)
                        .build()).build();
    }
    
    public static Biome wasteLand(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.DEEPLURKER.get(),
                        1, 1, 4));
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.NOCTILURE.get(),
                        5, 1, 1));
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
    
    public static Biome whiteForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        
        // (EntityType, weight of entity compare to other spawns, min entity spawn in one try, max)
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(EntityType.ALLAY,
                        10, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
        		new MobSpawnSettings.SpawnerData(ModEntities.PALE_WANDERER.get(),
                        40, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.NOCTILURE.get(),
                        1, 1, 1));
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
                .specialEffects(commonBiomeSpecialEffects()
                        .grassColorOverride(WHITE_FOREST_1)
                        .foliageColorOverride(WHITE_FOREST_2)
                        .backgroundMusic(ModMusics.WHITE_FOREST)
                        .build()).build();
    }

    public static Biome cristalCave(BootstrapContext<Biome> context) {
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

        allCrystalsOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.WHITE_FOREST)
                        .foliageColorOverride(0x57ab6a)
                        .grassColorOverride(0x57ab6a).build()).build();
    }

    public static Biome blindingDepth(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
        defaultAbyssSculk(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.TALL_DEPTH_VINES_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.CAVE_SCULK_TENDRILS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.LIVING_SPROUT_EMERGENCE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.SCULK_TENDRIL_EMERGENCE_DOWN_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome amethystCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);

        amethystOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome citrineCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);

        citrineOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome malachiteCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);

        malachiteOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome rubyCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);

        rubyOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome echoCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.SHADOW.get(), 1, 1, 1));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);
        defaultAbyssSculk(biomeBuilder);

        echoOnCaveWalls(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.SCULK_TENDRIL_EMERGENCE_DOWN_KEY);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static Biome paleCave(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalAbyssGeneration(biomeBuilder);
        defaultAbyssCaves(biomeBuilder);
        defaultAbyssOres(biomeBuilder);

        paleOnCaveWalls(biomeBuilder);
        abyssLushVegetationFeatures(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(ModMusics.DEEP_FOREST).build()).build();

    }

    public static void globalAbyssGeneration(BiomeGenerationSettings.Builder builder) {
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
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.REDSTONE_ORE_KEY);
    }

    public static void defaultAbyssSculk(BiomeGenerationSettings.Builder builder){
        builder.addFeature(SCULK_STEP, ModPlacedFeatures.ABYSS_SCULK_PATCH_KEY);
        //builder.addFeature(SCULK_STEP, CavePlacements.SCULK_VEIN);
    }

    public static BiomeSpecialEffects.Builder commonBiomeSpecialEffects(){
        return new BiomeSpecialEffects.Builder()
                .waterColor(WATER_COLOR)
                .waterFogColor(FOG_COLOR)
                .skyColor(FOG_COLOR)
                .grassColorOverride(DEEP_FOREST_1)
                .foliageColorOverride(DEEP_FOREST_2)
                .fogColor(FOG_COLOR)
                .ambientParticle(new AmbientParticleSettings(ParticleRegistry.ABYSS_AMBIENT.get(), 0.0025F))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
    }

    public static void amethystOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.AMETHYST_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.AMETHYST_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.AMETHYST_VEIN_KEY);
    }

    public static void citrineOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.CITRINE_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.CITRINE_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.CITRINE_VEIN_KEY);
    }

    public static void malachiteOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.MALACHITE_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.MALACHITE_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.MALACHITE_VEIN_KEY);
    }

    public static void rubyOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.RUBY_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.RUBY_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.RUBY_VEIN_KEY);
    }

    public static void echoOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.ECHO_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.ECHO_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.ECHO_VEIN_KEY);
    }

    public static void paleOnCaveWalls(BiomeGenerationSettings.Builder builder){
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.PALE_CRISTAL_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.PALE_CRISTAL_DW_KEY);
        builder.addFeature(CRYSTAL_STEP, ModPlacedFeatures.PALE_CRISTAL_VEIN_KEY);
    }

    public static void allCrystalsOnCaveWalls(BiomeGenerationSettings.Builder builder){
        amethystOnCaveWalls(builder);
        citrineOnCaveWalls(builder);
        malachiteOnCaveWalls(builder);
        rubyOnCaveWalls(builder);
        echoOnCaveWalls(builder);
        paleOnCaveWalls(builder);
    }

    public static void abyssLushVegetationFeatures(BiomeGenerationSettings.Builder p_176851_) {
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TALL_DEPTH_VINES_KEY);
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.CAVE_VINES);
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.LUSH_CAVES_CLAY);
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.LUSH_CAVES_VEGETATION);
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.SPORE_BLOSSOM);
        p_176851_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.CLASSIC_VINES);
    }
}

/*
    =============
    STEP REMINDER
    =============
 * raw_generation
 * lakes
 * local_modifications
 * underground_structures
 * surface_structures
 * strongholds
 * underground_ores
 * underground_decoration
 * fluid_springs
 * vegetal_decoration
 * top_layer_modification
*/
