package wardentools.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.worldgen.tree.custom.DarktreeFoliagePlacer;
import wardentools.worldgen.tree.custom.DarktreeTrunkPlacer;
import wardentools.worldgen.tree.custom.WhitetreeFoliagePlacer;
import wardentools.worldgen.tree.custom.WhitetreeTrunkPlacer;


public class ModConfiguredFeatures {
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARKTREE_KEY = registerKey("darktree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITETREE_KEY = registerKey("whitetree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_GRASS = registerKey("white_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_WHITE_GRASS = registerKey("tall_white_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_TORCHFLOWER = registerKey("white_torchflower");
		
	
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		
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
    	  	
    	//Holder<PlacedFeature> vegetationFeature = context.lookup(Registries.PLACED_FEATURE)
    	//		.getOrThrow(ModPlacedFeatures.CHERRY_KEY);
    	
    	//register(context, WHITE_VEGETATION, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
        //        BlockTags.DIRT,
        //        BlockStateProvider.simple(Blocks.GRASS_BLOCK.defaultBlockState()),
        //        vegetationFeature,
        //        CaveSurface.FLOOR,
        //        UniformInt.of(2, 5), // Vegetation size
        //        0.4f, // Vegetation density
        //        10, // Maximum plants per chunck
        //        0.7f, // Apparition probability
        //        UniformInt.of(2, 4), // Size of the patch
        //        0.6f // Additionnal apparition probability
        //));
    }
	
	private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_,
        		PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
     }
    
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ModMain.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void
    register(BootstapContext<ConfiguredFeature<?, ?>> context,
        ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        	context.register(key, new ConfiguredFeature<>(feature, configuration));
    	}
    
    
    
}