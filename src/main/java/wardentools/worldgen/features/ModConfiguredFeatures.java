package wardentools.worldgen.features;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.worldgen.features.custom.cristals.CristalFormationConfiguration;
import wardentools.worldgen.features.custom.cristals.CristalVeinConfiguration;
import wardentools.worldgen.features.custom.tendrils.SculkTendrilsEmergenceConfiguration;
import wardentools.worldgen.tree.custom.DarktreeFoliagePlacer;
import wardentools.worldgen.tree.custom.DarktreeTrunkPlacer;
import wardentools.worldgen.tree.custom.WhitetreeFoliagePlacer;
import wardentools.worldgen.tree.custom.WhitetreeTrunkPlacer;

import java.util.List;


public class ModConfiguredFeatures {
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARKTREE_KEY = registerKey("darktree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITETREE_KEY = registerKey("whitetree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_GRASS = registerKey("white_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_WHITE_GRASS = registerKey("tall_white_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_TORCHFLOWER = registerKey("white_torchflower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPFLOWER = registerKey("deepflower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_BUSH = registerKey("blue_bush");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_DARK_GRASS = registerKey("tall_dark_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_GRASS = registerKey("dark_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_ORE = registerKey("coal_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LAPIS_ORE = registerKey("lapis_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_ORE = registerKey("diamond_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEP_ORE = registerKey("deep_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LIQUID_CORRUPTION_FLOOR = registerKey("liquid_corruption_floor");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHARP_ROCK = registerKey("sharp_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MALACHITE_CRISTAL = registerKey("malachite_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_CRISTAL = registerKey("ruby_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_CRISTAL = registerKey("citrine_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ECHO_CRISTAL = registerKey("echo_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_CRISTAL = registerKey("pale_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_CRISTAL = registerKey("amethyst_cristal");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MALACHITE_VEIN = registerKey("malachite_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_VEIN = registerKey("ruby_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CITRINE_VEIN = registerKey("citrine_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ECHO_VEIN = registerKey("echo_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_CRISTAL_VEIN = registerKey("pale_cristal_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_VEIN = registerKey("amethyst_vein");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_TENDRIL_EMERGENCE = registerKey("sculk_tendril_emergence");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		
    	register(context, DARKTREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.DARKTREE_LOG.get()),
                new DarktreeTrunkPlacer(6, 4, 3),
                BlockStateProvider.simple(BlockRegistry.DARKTREE_LEAVES.get()),
                new DarktreeFoliagePlacer(ConstantInt.of(5), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
    	
    	register(context, WHITETREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.WHITETREE_LOG.get()),
                new WhitetreeTrunkPlacer(6, 4, 4),
                BlockStateProvider.simple(BlockRegistry.WHITETREE_LEAVES.get()),
                new WhitetreeFoliagePlacer(ConstantInt.of(5), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
    	
    	register(context, WHITE_GRASS, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.WHITE_GRASS.get()), 40));
    	
    	register(context, TALL_WHITE_GRASS, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.TALL_WHITE_GRASS.get()), 20));
    	
    	register(context, WHITE_TORCHFLOWER, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.WHITE_TORCHFLOWER.get()), 2));
    	
    	register(context, DEEPFLOWER, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.DEEPFLOWER.get()), 1));
    	
    	register(context, BLUE_BUSH, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.BLUE_BUSH.get()), 10));
    	
    	register(context, TALL_DARK_GRASS, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.TALL_DARK_GRASS.get()), 5));
    	
    	register(context, DARK_GRASS, Feature.RANDOM_PATCH,
    			grassPatch(BlockStateProvider.simple(BlockRegistry.DARK_GRASS.get()), 15));

		register(context, COAL_ORE, Feature.ORE,
				oreGeneration(17, 0.0F,
						BlockRegistry.ABYSSALITE_COAL_ORE.get(), Blocks.DEEPSLATE_COAL_ORE));

		register(context, LAPIS_ORE, Feature.ORE,
				oreGeneration(8, 0.0F,
						BlockRegistry.ABYSSALITE_LAPIS_ORE.get(), Blocks.DEEPSLATE_LAPIS_ORE));

		register(context, DIAMOND_ORE, Feature.ORE,
				oreGeneration(8, 0.7F,
						BlockRegistry.ABYSSALITE_DIAMOND_ORE.get(), Blocks.DEEPSLATE_DIAMOND_ORE));

		register(context, DEEP_ORE, Feature.ORE,
				oreGeneration(5, 0.0F,
						BlockRegistry.ABYSSALITE_DEEP_ORE.get()));

		register(context, LIQUID_CORRUPTION_FLOOR, ModFeatures.REPLACE_AIR_BELOW_Y.get(),
					new NoneFeatureConfiguration());

		register(context, SHARP_ROCK, ModFeatures.SHARP_ROCK.get(), new NoneFeatureConfiguration());

		register(context, MALACHITE_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(12, 3, 5,
						BlockRegistry.MALACHITE_BLOCK.get().defaultBlockState(),
						BlockRegistry.MALACHITE.get().defaultBlockState()));

		register(context, RUBY_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(9, 4, 7,
						BlockRegistry.RUBY_BLOCK.get().defaultBlockState(),
						BlockRegistry.RUBY.get().defaultBlockState()));

		register(context, CITRINE_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(15, 5, 4,
						BlockRegistry.CITRINE_BLOCK.get().defaultBlockState(),
						BlockRegistry.CITRINE.get().defaultBlockState()));

		register(context, ECHO_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(12, 6, 4,
						BlockRegistry.ECHO_BLOCK.get().defaultBlockState(),
						BlockRegistry.ECHO_CRISTAL.get().defaultBlockState()));

		register(context, PALE_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(13, 5, 10,
						BlockRegistry.PALE_CRISTAL_BLOCK.get().defaultBlockState(),
						BlockRegistry.PALE_CRISTAL.get().defaultBlockState()));

		register(context, AMETHYST_CRISTAL, ModFeatures.CRISTAL_FORMATION.get(),
				new CristalFormationConfiguration(13, 5, 10,
						Blocks.AMETHYST_BLOCK.defaultBlockState(),
						Blocks.AMETHYST_CLUSTER.defaultBlockState()));

		register(context, MALACHITE_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						BlockRegistry.MALACHITE_BLOCK.get().defaultBlockState(),
						BlockRegistry.MALACHITE.get().defaultBlockState()));

		register(context, RUBY_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						BlockRegistry.RUBY_BLOCK.get().defaultBlockState(),
						BlockRegistry.RUBY.get().defaultBlockState()));

		register(context, CITRINE_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						BlockRegistry.CITRINE_BLOCK.get().defaultBlockState(),
						BlockRegistry.CITRINE.get().defaultBlockState()));

		register(context, ECHO_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						BlockRegistry.ECHO_BLOCK.get().defaultBlockState(),
						BlockRegistry.ECHO_CRISTAL.get().defaultBlockState()));

		register(context, PALE_CRISTAL_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						BlockRegistry.PALE_CRISTAL_BLOCK.get().defaultBlockState(),
						BlockRegistry.PALE_CRISTAL.get().defaultBlockState()));

		register(context, AMETHYST_VEIN, ModFeatures.CRISTAL_VEIN.get(),
				new CristalVeinConfiguration(5, 2, 4,
						Blocks.AMETHYST_BLOCK.defaultBlockState(),
						Blocks.AMETHYST_CLUSTER.defaultBlockState()));

		register(context, SCULK_TENDRIL_EMERGENCE, ModFeatures.SCULK_TENDRILS_EMERGENCE.get(),
				new SculkTendrilsEmergenceConfiguration(8, 0.2F,
						4, 0.7F, 0.7F));
    }
	
	private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries,
				PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(stateProvider),
						BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.SCULK_VEIN)));
    }

	public static OreConfiguration oreGeneration(int size, float discardChanceOnAirExposure,
									  Block abyssaliteReplaceable, Block deepslateReplaceable){
		// Configure the target for abyssalite replacement
		OreConfiguration.TargetBlockState abyssaliteTarget = OreConfiguration
				.target(new BlockMatchTest(BlockRegistry.ABYSSALITE.get()),
						abyssaliteReplaceable.defaultBlockState());
		// Configure the target for deepslate replacement
		OreConfiguration.TargetBlockState deepslateTarget = OreConfiguration
				.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
						deepslateReplaceable.defaultBlockState());
		// Create the ore configuration
		return new OreConfiguration(
				List.of(abyssaliteTarget, deepslateTarget),
				size, discardChanceOnAirExposure);
	}

	public static OreConfiguration oreGeneration(int size, float discardChanceOnAirExposure,
				 Block abyssaliteReplaceable){
		OreConfiguration.TargetBlockState abyssaliteTarget = OreConfiguration
				.target(new BlockMatchTest(BlockRegistry.ABYSSALITE.get()),
						abyssaliteReplaceable.defaultBlockState());

		return new OreConfiguration(List.of(abyssaliteTarget), size, discardChanceOnAirExposure);
	}
    
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
				ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
			BootstrapContext<ConfiguredFeature<?, ?>> context,
        ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        	context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
    
}