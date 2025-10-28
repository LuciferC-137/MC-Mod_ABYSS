package wardentools.worldgen.dimension;

import java.util.List;
import java.util.OptionalLong;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import wardentools.ModMain;
import wardentools.worldgen.CustomNoiseSettings;
import wardentools.worldgen.biome.ModBiomes;


public class ModDimensions {

    public static final float ABYSS_AMBIENT_LIGHT = 0.01F;
    public static final float CRYSTAL_BIOMES_DEPTH = 0.7F;


	public static final ResourceKey<LevelStem> ABYSS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssdim"));
    public static final ResourceKey<Level> ABYSS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssdim"));
    public static final ResourceKey<DimensionType> ABYSS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssdim_type"));
    
    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(ABYSS_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.NETHER_EFFECTS, // Base effectsLocation
                ABYSS_AMBIENT_LIGHT, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(biomes(biomeRegistry)),
                noiseGenSettings.getOrThrow(CustomNoiseSettings.ABYSS_NOISE));
        
        //put here the selected preset
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.ABYSS_DIM_TYPE), chunkGenerator);

        context.register(ABYSS_KEY, stem);
    }

    public static Climate.ParameterList<Holder<Biome>> biomes(HolderGetter<Biome> biomeRegistry) {
        return new Climate.ParameterList<>(List.of(
                climate(0F, 0F, 0F, 0F, 0F, 0F,
                        ModBiomes.DEEP_FOREST, biomeRegistry),

                climate(0.6F, -0.4F, 0F, 0.5F, 0.0F, 0F,
                        ModBiomes.WASTE_LAND, biomeRegistry),

                climate(-0.6F, 0.4F, -0.5F, -0.5F, 0.0F, 0F,
                        ModBiomes.WHITE_FOREST, biomeRegistry),

                climate(0F, 0.5F, 0.6F, 0.1F, 0.8F, 0.3F,
                        ModBiomes.CRYSTAL_CAVE, biomeRegistry),

                climate(0F, 0F, 0.5F, 0F, 0.9F, 0F,
                        ModBiomes.BLINDING_DEPTH, biomeRegistry),

                climate(-0.1F, -0.6F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.2F,
                        ModBiomes.AMETHYST_CAVE, biomeRegistry),

                climate(0.5F, -0.1F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.2F,
                        ModBiomes.CITRINE_CAVE, biomeRegistry),

                climate(0.1F, 0.5F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.2F,
                        ModBiomes.MALACHITE_CAVE, biomeRegistry),

                climate(0.4F, -0.5F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.2F,
                        ModBiomes.RUBY_CAVE, biomeRegistry),

                climate(-0.5F, 0.3F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.1F,
                        ModBiomes.ECHO_CAVE, biomeRegistry),

                climate(-0.8F, 0.2F, 0.0F, 0.1F, CRYSTAL_BIOMES_DEPTH, 0.4F,
                        ModBiomes.PALE_CAVE, biomeRegistry)
        ));
    }

    // Helper method to create Climate.ParameterPoint with 6 parameters. Every one is between -1 and 1.
    public static Pair<Climate.ParameterPoint, Holder<Biome>> climate(float temperature,
                                                                                float humidity,
                                                                                float continentalness,
                                                                                float erosion,
                                                                                float depth,
                                                                                float weirdness,
                                                                                ResourceKey<Biome> biomeKey,
                                                                                HolderGetter<Biome> biomeRegistry) {
        return Pair.of(Climate.parameters(temperature, humidity, continentalness,
                erosion, depth, weirdness, 0.0F), biomeRegistry.getOrThrow(biomeKey));
    }
}
