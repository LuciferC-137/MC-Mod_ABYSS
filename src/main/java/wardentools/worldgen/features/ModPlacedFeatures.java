package wardentools.worldgen.features;



import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.tags.ModTags;

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
	public static final ResourceKey<PlacedFeature> REDSTONE_ORE_KEY = registerKey("redstone_ore");
	public static final ResourceKey<PlacedFeature> LIQUID_CORRUPTION_LAKE_KEY = registerKey("liquid_corruption_lake");
	public static final ResourceKey<PlacedFeature> SHARP_ROCK_KEY = registerKey("sharp_rock");
	public static final ResourceKey<PlacedFeature> MALACHITE_CRISTAL_KEY = registerKey("malachite_cristal_key");
	public static final ResourceKey<PlacedFeature> RUBY_CRISTAL_KEY = registerKey("ruby_cristal_key");
	public static final ResourceKey<PlacedFeature> CITRINE_CRISTAL_KEY = registerKey("citrine_cristal_key");
	public static final ResourceKey<PlacedFeature> ECHO_CRISTAL_KEY = registerKey("echo_cristal_key");
	public static final ResourceKey<PlacedFeature> PALE_CRISTAL_KEY = registerKey("pale_cristal_key");
	public static final ResourceKey<PlacedFeature> AMETHYST_CRISTAL_KEY = registerKey("amethyst_cristal_key");
	public static final ResourceKey<PlacedFeature> MALACHITE_CRISTAL_DW_KEY = registerKey("malachite_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> RUBY_CRISTAL_DW_KEY = registerKey("ruby_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> CITRINE_CRISTAL_DW_KEY = registerKey("citrine_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> ECHO_CRISTAL_DW_KEY = registerKey("echo_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> PALE_CRISTAL_DW_KEY = registerKey("pale_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> AMETHYST_CRISTAL_DW_KEY = registerKey("amethyst_cristal_dw_key");
	public static final ResourceKey<PlacedFeature> MALACHITE_VEIN_KEY = registerKey("malachite_vein");
	public static final ResourceKey<PlacedFeature> RUBY_VEIN_KEY = registerKey("ruby_vein");
	public static final ResourceKey<PlacedFeature> CITRINE_VEIN_KEY = registerKey("citrine_vein");
	public static final ResourceKey<PlacedFeature> ECHO_VEIN_KEY = registerKey("echo_vein");
	public static final ResourceKey<PlacedFeature> PALE_CRISTAL_VEIN_KEY = registerKey("pale_vein");
	public static final ResourceKey<PlacedFeature> AMETHYST_VEIN_KEY = registerKey("amethyst_vein");
	public static final ResourceKey<PlacedFeature> SCULK_TENDRIL_EMERGENCE_KEY = registerKey("sculk_tendril_emergence");
	public static final ResourceKey<PlacedFeature> ABYSS_SCULK_PATCH_KEY = registerKey("abyss_sculk_patch");
	public static final ResourceKey<PlacedFeature> TALL_DEPTH_VINES_KEY = registerKey("tall_depth_vines");
	public static final ResourceKey<PlacedFeature> CAVE_SCULK_TENDRILS_KEY = registerKey("sculk_tendrils");
	public static final ResourceKey<PlacedFeature> LIVING_SPROUT_EMERGENCE_KEY = registerKey("living_sprout_emergence");


	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
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
				oreGeneration(20, -24, 192)
		);

		register(context,
				LAPIS_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LAPIS_ORE),
				oreGeneration(10, -44, 54)
		);

		register(context,
				DIAMOND_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.DIAMOND_ORE),
				oreGeneration(9, -64, 32)
		);

		register(context,
				DEEP_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.DEEP_ORE),
				oreGeneration(6, -80, 10)
		);

		register(context,
				REDSTONE_ORE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.REDSTONE_ORE),
				oreGeneration(10, -50, 50)
		);

		register(context, LIQUID_CORRUPTION_LAKE_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LIQUID_CORRUPTION_FLOOR),
				List.of(CountPlacement.of(1)));

		register(context, SHARP_ROCK_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHARP_ROCK),
				List.of(
						CountPlacement.of(UniformInt.of(2, 4)),
						InSquarePlacement.spread(),
						PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
						BiomeFilter.biome()
				));

		register(context, MALACHITE_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MALACHITE_CRISTAL),
				onCaveWallUp(4, 8));

		register(context, RUBY_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.RUBY_CRISTAL),
				onCaveWallUp(4, 8));

		register(context, CITRINE_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.CITRINE_CRISTAL),
				onCaveWallUp(4, 8));

		register(context, ECHO_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ECHO_CRISTAL),
				onCaveWallUp(4, 8));

		register(context, PALE_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.PALE_CRISTAL),
				onCaveWallUp(3, 7));

		register(context, AMETHYST_CRISTAL_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.AMETHYST_CRISTAL),
				onCaveWallUp(5, 9));

		register(context, MALACHITE_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MALACHITE_CRISTAL),
				onCaveWallDown(4, 8));

		register(context, RUBY_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.RUBY_CRISTAL),
				onCaveWallDown(4, 8));

		register(context, CITRINE_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.CITRINE_CRISTAL),
				onCaveWallDown(4, 8));

		register(context, ECHO_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ECHO_CRISTAL),
				onCaveWallDown(4, 8));

		register(context, PALE_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.PALE_CRISTAL),
				onCaveWallDown(3, 7));

		register(context, AMETHYST_CRISTAL_DW_KEY,
				context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.AMETHYST_CRISTAL),
				onCaveWallDown(5, 9));

		register(context, MALACHITE_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.MALACHITE_VEIN),
				onCaveWallDown(3, 5));

		register(context, RUBY_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.RUBY_VEIN),
				onCaveWallDown(3, 5));

		register(context, CITRINE_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.CITRINE_VEIN),
				onCaveWallDown(3, 5));

		register(context, ECHO_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.ECHO_VEIN),
				onCaveWallDown(3, 5));

		register(context, PALE_CRISTAL_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.PALE_CRISTAL_VEIN),
				onCaveWallDown(3, 5));

		register(context, AMETHYST_VEIN_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.AMETHYST_VEIN),
				onCaveWallDown(3, 5));

		register(context, SCULK_TENDRIL_EMERGENCE_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.SCULK_TENDRIL_EMERGENCE),
				List.of(CountPlacement.of(1)));

		register(context, ABYSS_SCULK_PATCH_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.ABYSS_SCULK_PATCH),
				List.of(CountPlacement.of(ConstantInt.of(256)),
						InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
						BiomeFilter.biome())
        );

		register(context, TALL_DEPTH_VINES_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.TALL_DEPTH_VINE),
				List.of(CountPlacement.of(UniformInt.of(6, 12)),
						InSquarePlacement.spread(),
						BiomeFilter.biome())
		);

		register(context, CAVE_SCULK_TENDRILS_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.SCULK_TENDRIL_EMERGENCE),
				onCaveWallDown(4, 8)
		);

		register(context, LIVING_SPROUT_EMERGENCE_KEY,
				context.lookup((Registries.CONFIGURED_FEATURE)).getOrThrow(ModConfiguredFeatures.LIVING_SPROUT_EMERGENCE),
				onCaveWallDown(6, 10)
		);

    }
	
	private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
				ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

	private static List<PlacementModifier> onCaveWallUp(int minPerChunk, int maxPerChunk){
		return List.of(
				CountPlacement.of(UniformInt.of(minPerChunk, maxPerChunk)),
				InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)),
				EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(),
						BlockPredicate.ONLY_IN_AIR_PREDICATE, 20),
				BlockPredicateFilter.forPredicate(BlockPredicate.not(
						BlockPredicate.matchesTag(ModTags.Blocks.CRISTAL_BLOCK))),
				BiomeFilter.biome()
		);
	}

	private static List<PlacementModifier> onCaveWallDown(int minPerChunk, int maxPerChunk){
		return List.of(
				CountPlacement.of(UniformInt.of(minPerChunk, maxPerChunk)),
				InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)),
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),
						BlockPredicate.ONLY_IN_AIR_PREDICATE, 20),
				BlockPredicateFilter.forPredicate(BlockPredicate.not(
						BlockPredicate.matchesTag(ModTags.Blocks.CRISTAL_BLOCK))),
				BiomeFilter.biome()
		);
	}

	private static List<PlacementModifier> oreGeneration(int veinsPerChunk, int minHeight, int maxHeight){
		return List.of(
				CountPlacement.of(veinsPerChunk),
				InSquarePlacement.spread(),
				HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
				BiomeFilter.biome()
		);
	}


}
