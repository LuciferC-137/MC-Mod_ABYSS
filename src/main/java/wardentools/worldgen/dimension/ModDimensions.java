package wardentools.worldgen.dimension;

import java.util.List;
import java.util.OptionalLong;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import wardentools.ModMain;
import wardentools.worldgen.biome.ModBiomes;


public class ModDimensions {
	public static final ResourceKey<LevelStem> ABYSS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(ModMain.MOD_ID, "abyssdim"));
    public static final ResourceKey<Level> ABYSS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(ModMain.MOD_ID, "abyssdim"));
    public static final ResourceKey<DimensionType> ABYSS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(ModMain.MOD_ID, "abyssdim_type"));
    
    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(ABYSS_DIM_TYPE, new DimensionType(
                OptionalLong.of(1000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.NETHER_EFFECTS, // Base effectsLocation
                0.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        

        
        //To have one biome on the dimension
        @SuppressWarnings("unused")
        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.DEEP_FOREST)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
        
        //To have multiple biome on the dimension. Check the datagen website to visualize
        @SuppressWarnings("unused")
		NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                        		Pair.of(Climate.parameters(0.7F, 0.9F, 0.3F, 0.5F, 0.0F, 0.3F, 0.4F),
                                        biomeRegistry.getOrThrow(ModBiomes.DEEP_FOREST)),
                                Pair.of(Climate.parameters(2.0F, 0.1F, 0.7F, 0.2F, -0.5F, 0.5F, 0.6F),
                                        biomeRegistry.getOrThrow(ModBiomes.WASTE_LAND)),
                                Pair.of(Climate.parameters(-0.5F, 0.6F, 0.5F, 0.3F, 0.0F, 0.4F, 0.5F),
                                        biomeRegistry.getOrThrow(ModBiomes.WHITE_FOREST))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
        
        //put here the selected preset
        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.ABYSS_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(ABYSS_KEY, stem);
    }
    
    
}