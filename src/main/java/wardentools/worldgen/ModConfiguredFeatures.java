package wardentools.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import wardentools.ModMain;
import wardentools.registries.BlockRegistry;
import wardentools.worldgen.tree.custom.DarktreeFoliagePlacer;
import wardentools.worldgen.tree.custom.DarktreeTrunkPlacer;


public class ModConfiguredFeatures {
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARKTREE_KEY = registerKey("darktree");
		
	
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		
    	register(context, DARKTREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.DARKTREE_LOG.get()),
                new DarktreeTrunkPlacer(6, 4, 3),
                BlockStateProvider.simple(BlockRegistry.DARKTREE_LEAVES.get()),
                new DarktreeFoliagePlacer(ConstantInt.of(5), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
    	
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