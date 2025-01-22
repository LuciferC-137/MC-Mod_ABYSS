package wardentools.worldgen;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import wardentools.ModMain;
import wardentools.worldgen.biome.surface.ModSurfaceRules;

import java.util.stream.Stream;

public class CustomNoiseSettings {
    private static final ResourceKey<DensityFunction> Y = createKey("y");
    private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
    private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
    public static final ResourceKey<DensityFunction> CONTINENTS = createKey("overworld/continents");
    public static final ResourceKey<DensityFunction> EROSION = createKey("overworld/erosion");
    public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
    public static final ResourceKey<DensityFunction> FACTOR = createKey("overworld/factor");
    public static final ResourceKey<DensityFunction> DEPTH = createKey("overworld/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("overworld/sloped_cheese");
    public static final ResourceKey<DensityFunction> CONTINENTS_LARGE = createKey("overworld/continents");
    public static final ResourceKey<DensityFunction> EROSION_LARGE = createKey("overworld/erosion");
    private static final ResourceKey<DensityFunction> FACTOR_LARGE = createKey("overworld/factor");
    private static final ResourceKey<DensityFunction> DEPTH_LARGE = createKey("overworld/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = createKey("overworld/sloped_cheese");
    private static final ResourceKey<DensityFunction> FACTOR_AMPLIFIED = createKey("overworld/factor");
    private static final ResourceKey<DensityFunction> DEPTH_AMPLIFIED = createKey("overworld/depth");
    private static final ResourceKey<DensityFunction> SLOPED_CHEESE_AMPLIFIED = createKey("overworld/sloped_cheese");
    private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
    private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
    private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
    private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
    private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

    public static final Codec<NoiseGeneratorSettings> DIRECT_CODEC
            = RecordCodecBuilder.create((p_64475_) -> {
        return p_64475_.group(NoiseSettings.CODEC.fieldOf("noise")
                    .forGetter(NoiseGeneratorSettings::noiseSettings),
                BlockState.CODEC.fieldOf("default_block")
                        .forGetter(NoiseGeneratorSettings::defaultBlock),
                BlockState.CODEC.fieldOf("default_fluid")
                        .forGetter(NoiseGeneratorSettings::defaultFluid),
                NoiseRouter.CODEC.fieldOf("noise_router")
                        .forGetter(NoiseGeneratorSettings::noiseRouter),
                SurfaceRules.RuleSource.CODEC.fieldOf("surface_rule")
                        .forGetter(NoiseGeneratorSettings::surfaceRule),
                Climate.ParameterPoint.CODEC.listOf().fieldOf("spawn_target")
                        .forGetter(NoiseGeneratorSettings::spawnTarget),
                Codec.INT.fieldOf("sea_level").forGetter(NoiseGeneratorSettings::seaLevel),
                Codec.BOOL.fieldOf("disable_mob_generation")
                        .forGetter(NoiseGeneratorSettings::disableMobGeneration),
                Codec.BOOL.fieldOf("aquifers_enabled")
                        .forGetter(NoiseGeneratorSettings::isAquifersEnabled),
                Codec.BOOL.fieldOf("ore_veins_enabled")
                        .forGetter(NoiseGeneratorSettings::oreVeinsEnabled),
                Codec.BOOL.fieldOf("legacy_random_source")
                        .forGetter(NoiseGeneratorSettings::useLegacyRandomSource))
                        .apply(p_64475_, NoiseGeneratorSettings::new);
    });
    public static final Codec<Holder<NoiseGeneratorSettings>> CODEC
            = RegistryFileCodec.create(Registries.NOISE_SETTINGS, DIRECT_CODEC);
    public static final ResourceKey<NoiseGeneratorSettings> ABYSS_NOISE = createNoiseKey("abyss_noise");

    private static ResourceKey<NoiseGeneratorSettings> createNoiseKey(String name) {
        return ResourceKey.create(Registries.NOISE_SETTINGS,
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static ResourceKey<DensityFunction> createKey(String name) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION,
                ResourceLocation.fromNamespaceAndPath("minecraft", name));
    }

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> ctx) {
        ctx.register(ABYSS_NOISE, createAbyssNoiseSettings(ctx, false, false));
    }
    
    private static NoiseGeneratorSettings createAbyssNoiseSettings(
            BootstrapContext<?> p_256460_, boolean amplified, boolean large){
        return new NoiseGeneratorSettings(
                NoiseSettings.create(-64, 384, 1, 2),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                overworld(p_256460_.lookup(Registries.DENSITY_FUNCTION),
                          p_256460_.lookup(Registries.NOISE), large, amplified),
                ModSurfaceRules.makeRules(),
                (new OverworldBiomeBuilder()).spawnTarget(),
                -100,
                false,
                false,
                true,
                false);
    }

    protected static NoiseRouter overworld(HolderGetter<DensityFunction> densFunc,
                                           HolderGetter<NormalNoise.NoiseParameters> noiseParam,
                                           boolean large, boolean amplified) {
        DensityFunction densityfunction
                = DensityFunctions.noise(noiseParam.getOrThrow(Noises.AQUIFER_BARRIER), 0.5D);
        DensityFunction densityfunction1
                = DensityFunctions.noise(noiseParam.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.3D);
        DensityFunction densityfunction2
                = DensityFunctions.noise(noiseParam.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.5D);
        DensityFunction densityfunction3 = DensityFunctions.constant(0D);
                //= DensityFunctions.noise(noiseParam.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(densFunc, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(densFunc, SHIFT_Z);
        DensityFunction densityfunction6
                = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D,
                    noiseParam.getOrThrow(large ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE));
        DensityFunction densityfunction7
                = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5,
                0.25D, noiseParam.getOrThrow(large ? Noises.VEGETATION_LARGE : Noises.VEGETATION));
        DensityFunction densityfunction8
                = getFunction(densFunc, large ? FACTOR_LARGE : (amplified ? FACTOR_AMPLIFIED : FACTOR));
        DensityFunction densityfunction9 = getFunction(densFunc, large ? DEPTH_LARGE : (amplified ? DEPTH_AMPLIFIED : DEPTH));
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11
                = getFunction(densFunc, large ? SLOPED_CHEESE_LARGE : (amplified ? SLOPED_CHEESE_AMPLIFIED : SLOPED_CHEESE));
        DensityFunction densityfunction12
                = DensityFunctions.min(densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0D),
                    getFunction(densFunc, ENTRANCES)));
        DensityFunction densityfunction13
                = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D,
                densityfunction12, underground(densFunc, noiseParam, densityfunction11));
        DensityFunction densityfunction14
                = DensityFunctions.min(postProcess(slideOverworld(amplified, densityfunction13)),
                getFunction(densFunc, NOODLE));
        DensityFunction densityfunction15 = getFunction(densFunc, Y);
        int i = Stream.of(VeinType.values()).mapToInt((p_224495_) -> {
            return p_224495_.minY;
        }).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(VeinType.values()).mapToInt((p_224457_) -> {
            return p_224457_.maxY;
        }).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction densityfunction16
                = yLimitedInterpolatable(densityfunction15,
                    DensityFunctions.noise(noiseParam.getOrThrow(Noises.ORE_VEININESS),
                        1.5D, 1.5D), i, j, 0);
        float f = 4.0F;
        DensityFunction densityfunction17
                = yLimitedInterpolatable(densityfunction15,
                    DensityFunctions.noise(noiseParam.getOrThrow(Noises.ORE_VEIN_A),
                        4.0D, 4.0D), i, j, 0).abs();
        DensityFunction densityfunction18
                = yLimitedInterpolatable(densityfunction15,
                    DensityFunctions.noise(noiseParam.getOrThrow(Noises.ORE_VEIN_B),
                            4.0D, 4.0D), i, j, 0).abs();
        DensityFunction densityfunction19
                = DensityFunctions.add(DensityFunctions.constant((double)-0.08F),
                    DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction densityfunction20 =
                DensityFunctions.noise(noiseParam.getOrThrow(Noises.ORE_GAP));
        return new NoiseRouter(densityfunction,
                densityfunction1, densityfunction2, densityfunction3, densityfunction6,
                densityfunction7, getFunction(densFunc, large ? CONTINENTS_LARGE : CONTINENTS),
                getFunction(densFunc, large ? EROSION_LARGE : EROSION), densityfunction9,
                getFunction(densFunc, RIDGES), slideOverworld(amplified,
                DensityFunctions.add(densityfunction10,
                        DensityFunctions.constant(-0.703125D)).clamp(-64.0D, 64.0D)),
                densityfunction14, densityfunction16, densityfunction19, densityfunction20);
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_, ResourceKey<DensityFunction> p_256077_) {
        return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
    }

    private static DensityFunction underground(HolderGetter<DensityFunction> densFuncHold, HolderGetter<NormalNoise.NoiseParameters> noiseParam, DensityFunction densFunc) {
        DensityFunction densityfunction = getFunction(densFuncHold, SPAGHETTI_2D);
        DensityFunction densityfunction1 = getFunction(densFuncHold, SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction densityfunction2 = DensityFunctions.noise(noiseParam.getOrThrow(Noises.CAVE_LAYER), 8.0D);
        DensityFunction densityfunction3
                = DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction2.square());
        DensityFunction densityfunction4
                = DensityFunctions.noise(noiseParam.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666D);
        DensityFunction densityfunction5
                = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27D),
                    densityfunction4).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(1.5D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), densFunc)).clamp(0.0D, 0.5D));
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
        DensityFunction densityfunction7
                = DensityFunctions.min(DensityFunctions.min(densityfunction6,
                    getFunction(densFuncHold, ENTRANCES)), DensityFunctions.add(densityfunction, densityfunction1));
        DensityFunction densityfunction8 = getFunction(densFuncHold, PILLARS);
        DensityFunction densityfunction9
                = DensityFunctions.rangeChoice(densityfunction8,
                -1000000.0D, 0.03D, DensityFunctions.constant(-1000000.0D), densityfunction8);
        return DensityFunctions.max(densityfunction7, densityfunction9);
    }

    private static DensityFunction postProcess(DensityFunction densFunc) {
        DensityFunction densityfunction = DensityFunctions.blendDensity(densFunc);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction),
               DensityFunctions.constant(0.64D)).squeeze();
    }

    private static DensityFunction noiseGradientDensity(DensityFunction densFunc1, DensityFunction densFunc2) {
        DensityFunction densityfunction = DensityFunctions.mul(densFunc2, densFunc1);
        return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
    }

    private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
        return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, (double)p_209474_, (double)(p_209475_ + 1), p_209473_, DensityFunctions.constant((double)p_209476_)));
    }

    private static DensityFunction slideOverworld(boolean amplified, DensityFunction densFunc) {
        return slide(densFunc, -64, 384, amplified ? 16 : 80,
                amplified ? 0 : 64, -0.078125D, 0, 24, amplified ? 0.4D : 0.1171875D);
    }

    private static DensityFunction slide(DensityFunction densFunc,
             int y_floor, int y_roof, int i1, int i2, double d1, int i3, int i4, double d2) {
        DensityFunction densityfunction1
                = DensityFunctions.yClampedGradient(y_floor + y_roof - i1,
                y_floor + y_roof - i2, 1.0D, 0.0D);
        DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, d1, densFunc);
        DensityFunction densityfunction2
                = DensityFunctions.yClampedGradient(y_floor + i3,
                y_floor + i4, 0.0D, 1.0D);
        return DensityFunctions.lerp(densityfunction2, d2, $$9);
    }

    protected static enum VeinType {
        //COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(),
        //        Blocks.GRANITE.defaultBlockState(), 0, 50),
        IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(),
                Blocks.TUFF.defaultBlockState(), -60, -8);

        final BlockState ore;
        final BlockState rawOreBlock;
        final BlockState filler;
        protected final int minY;
        protected final int maxY;

        private VeinType(BlockState p_209684_, BlockState p_209685_, BlockState p_209686_, int p_209687_, int p_209688_) {
            this.ore = p_209684_;
            this.rawOreBlock = p_209685_;
            this.filler = p_209686_;
            this.minY = p_209687_;
            this.maxY = p_209688_;
        }
    }
}
