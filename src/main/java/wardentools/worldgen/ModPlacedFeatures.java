package wardentools.worldgen;



import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import java.util.List;

public class ModPlacedFeatures {
	public static final ResourceKey<PlacedFeature> DARKTREE_PLACED_KEY = registerKey("darktree_placed");
	public static final ResourceKey<PlacedFeature> WHITETREE_PLACED_KEY = registerKey("whitetree_placed");
	public static final ResourceKey<PlacedFeature> WHITE_GRASS_KEY = registerKey("white_grass");
	public static final ResourceKey<PlacedFeature> TALL_WHITE_GRASS_KEY = registerKey("tall_white_grass");
	public static final ResourceKey<PlacedFeature> WHITE_TORCHFLOWER_KEY = registerKey("white_torchflower");
	
	
	public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        
        register(context, DARKTREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARKTREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        BlockRegistry.DARKTREE_SAPLING.get()));
        
        register(context, WHITETREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITETREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(14, 0.1f, 2),
                        BlockRegistry.WHITETREE_SAPLING.get()));
        

        register(context, WHITE_GRASS_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITE_GRASS),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));
        
        register(context, TALL_WHITE_GRASS_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TALL_WHITE_GRASS),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));
        
        register(context, WHITE_TORCHFLOWER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITE_TORCHFLOWER),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));

    }
	
	
	
	
	private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ModMain.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
