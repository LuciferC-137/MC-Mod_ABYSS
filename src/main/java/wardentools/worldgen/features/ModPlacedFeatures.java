package wardentools.worldgen.features;



import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;

import java.util.List;

public class ModPlacedFeatures {
	public static final ResourceKey<PlacedFeature> DARKTREE_PLACED_KEY = registerKey("darktree_placed");
	public static final ResourceKey<PlacedFeature> WHITETREE_PLACED_KEY = registerKey("whitetree_placed");
	public static final ResourceKey<PlacedFeature> WHITE_GRASS_KEY = registerKey("white_grass");
	public static final ResourceKey<PlacedFeature> TALL_WHITE_GRASS_KEY = registerKey("tall_white_grass");
	public static final ResourceKey<PlacedFeature> WHITE_TORCHFLOWER_KEY = registerKey("white_torchflower");
	public static final ResourceKey<PlacedFeature> DEEPFLOWER_KEY = registerKey("deepflower");
	public static final ResourceKey<PlacedFeature> BLUE_BUSH_KEY = registerKey("blue_bush");
	public static final ResourceKey<PlacedFeature> TALL_DARK_GRASS_KEY = registerKey("tall_dark_grass");
	public static final ResourceKey<PlacedFeature> DARK_GRASS_KEY = registerKey("dark_grass");
	public static final ResourceKey<PlacedFeature> COAL_ORE_KEY = registerKey("coal_ore");
	public static final ResourceKey<PlacedFeature> LAPIS_ORE_KEY = registerKey("lapis_ore");
	public static final ResourceKey<PlacedFeature> DIAMOND_ORE_KEY = registerKey("diamond_ore");
	public static final ResourceKey<PlacedFeature> DEEP_ORE_KEY = registerKey("deep_ore");
	public static final ResourceKey<PlacedFeature> LIQUID_CORRUPTION_LAKE_KEY = registerKey("liquid_corruption_lake");
	
	
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
        
        register(context, DEEPFLOWER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPFLOWER),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));
        
        register(context, BLUE_BUSH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_BUSH),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));
        
        register(context, TALL_DARK_GRASS_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TALL_DARK_GRASS),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));
        
        register(context, DARK_GRASS_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DARK_GRASS),
        		List.of(
        				NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
        				InSquarePlacement.spread(),
        				PlacementUtils.HEIGHTMAP
        				));

		register(context,
				COAL_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.COAL_ORE),
				List.of(
						CountPlacement.of(20),
						InSquarePlacement.spread(),
						HeightRangePlacement.triangle(
								VerticalAnchor.absolute(-24), VerticalAnchor.absolute(192)),
						BiomeFilter.biome()
				)
		);

		register(context,
				LAPIS_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LAPIS_ORE),
				List.of(
						CountPlacement.of(10),
						InSquarePlacement.spread(),
						HeightRangePlacement.triangle(
								VerticalAnchor.absolute(-44), VerticalAnchor.absolute(54)),
						BiomeFilter.biome()
				)
		);

		register(context,
				DIAMOND_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.DIAMOND_ORE),
				List.of(
						CountPlacement.of(9),
						InSquarePlacement.spread(),
						HeightRangePlacement.triangle(
								VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)),
						BiomeFilter.biome()
				)
		);

		register(context,
				DEEP_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.DEEP_ORE),
				List.of(
						CountPlacement.of(6),
						InSquarePlacement.spread(),
						HeightRangePlacement.triangle(
								VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10)),
						BiomeFilter.biome()
				)
		);

		register(context, LIQUID_CORRUPTION_LAKE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LIQUID_CORRUPTION_FLOOR),
				List.of(
						CountPlacement.of(1)
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
