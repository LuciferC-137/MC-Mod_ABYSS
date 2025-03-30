package wardentools.items;


import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ModBoatEntity;

public class ItemRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModMain.MOD_ID);
	
	
	public static final DeferredItem<Item> DEEPINGOTS =
			ITEMS.register("deepingots",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> DEEPBLOCK =
			ITEMS.register("deepblock",
			() -> new BlockItem(BlockRegistry.DEEPBLOCK.get(), new Item.Properties()
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> DEEPCRISTAL =
			ITEMS.register("deepcristal",
			() -> new BlockItem(BlockRegistry.DEEP_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> RADIANCE_FRAGMENT =
			ITEMS.register("radiance_fragment",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> RADIANCE_CRISTAL =
			ITEMS.register("radiance_cristal",
			() -> new BlockItem(BlockRegistry.RADIANCE_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> WARDEN_HEART =
			ITEMS.register("warden_heart",
			() -> new WardenHeartItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> DARKTREE_LOG =
			ITEMS.register("darktree_log",
		    () -> new BlockItem(BlockRegistry.DARKTREE_LOG.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_LEAVES =
			ITEMS.register("darktree_leaves",
			() -> new BlockItem(BlockRegistry.DARKTREE_LEAVES.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> STRIPPED_DARKTREE_LOG =
			ITEMS.register("stripped_darktree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_LOG.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_WOOD =
			ITEMS.register("darktree_wood",
			() -> new BlockItem(BlockRegistry.DARKTREE_WOOD.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> STRIPPED_DARKTREE_WOOD =
			ITEMS.register("stripped_darktree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_WOOD.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_PLANKS =
			ITEMS.register("darktree_planks",
			() -> new BlockItem(BlockRegistry.DARKTREE_PLANKS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_STAIRS =
			ITEMS.register("darktree_stair",
			() -> new BlockItem(BlockRegistry.DARKTREE_STAIR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_SLAB =
			ITEMS.register("darktree_slab",
			() -> new BlockItem(BlockRegistry.DARKTREE_SLAB.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_BUTTON =
			ITEMS.register("darktree_button",
			() -> new BlockItem(BlockRegistry.DARKTREE_BUTTON.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_PRESSURE_PLATE =
			ITEMS.register("darktree_pressure_plate",
			() -> new BlockItem(BlockRegistry.DARKTREE_PRESSURE_PLATE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_DOOR =
			ITEMS.register("darktree_door",
			() -> new BlockItem(BlockRegistry.DARKTREE_DOOR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_TRAPDOOR =
			ITEMS.register("darktree_trapdoor",
			() -> new BlockItem(BlockRegistry.DARKTREE_TRAPDOOR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_FENCE =
			ITEMS.register("darktree_fence",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_FENCE_GATE =
			ITEMS.register("darktree_fence_gate",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE_GATE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKTREE_SAPLING =
			ITEMS.register("darktree_sapling",
			() -> new BlockItem(BlockRegistry.DARKTREE_SAPLING.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKGRASS_BLOCK = 
			ITEMS.register("darkgrass_block",
			() -> new BlockItem(BlockRegistry.DARKGRASS_BLOCK.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARKDIRT = 
			ITEMS.register("darkdirt",
			() -> new BlockItem(BlockRegistry.DARKDIRT.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DEEPLURKER_EGG =
			ITEMS.register("deeplurker_spawn_egg",
			() -> new CustomSpawnEggItem(ModEntities.DEEPLURKER, 0x005693,
					0x00ebff, new Item.Properties()));
	
	public static final DeferredItem<Item> DEEP_FRUIT =
			ITEMS.register("deep_fruit",
			() -> new Item(new Item.Properties()
					.food(ModFoods.DEEP_FRUIT_PROPERTIES)
					.stacksTo(64)
					));
	
	public static final DeferredItem<Item> ABYSS_DIVER =
			ITEMS.register("abyss_diver",
			() -> new AbyssDiverItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.durability(20)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> WIND_WHISPERER = 
			ITEMS.register("wind_whisperer",
			() -> new WindWhispererItem(BlockRegistry.WIND_WHISPERER.get(), new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> CORRUPTED_ESSENCE =
			ITEMS.register("corrupted_essence", 
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> CORRUPTED_VESSEL =
			ITEMS.register("corrupted_vessel",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> DARK_STICK =
			ITEMS.register("dark_stick",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.fireResistant()
					));
	
	public static final DeferredItem<Item> WHITETREE_LOG =
			ITEMS.register("whitetree_log",
		    () -> new BlockItem(BlockRegistry.WHITETREE_LOG.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_LEAVES =
			ITEMS.register("whitetree_leaves",
			() -> new BlockItem(BlockRegistry.WHITETREE_LEAVES.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> STRIPPED_WHITETREE_LOG =
			ITEMS.register("stripped_whitetree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_LOG.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_WOOD =
			ITEMS.register("whitetree_wood",
			() -> new BlockItem(BlockRegistry.WHITETREE_WOOD.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> STRIPPED_WHITETREE_WOOD =
			ITEMS.register("stripped_whitetree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_WOOD.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_SAPLING =
			ITEMS.register("whitetree_sapling",
			() -> new BlockItem(BlockRegistry.WHITETREE_SAPLING.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_PLANKS =
			ITEMS.register("whitetree_planks",
			() -> new BlockItem(BlockRegistry.WHITETREE_PLANKS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_STAIRS =
			ITEMS.register("whitetree_stair",
			() -> new BlockItem(BlockRegistry.WHITETREE_STAIR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_SLAB =
			ITEMS.register("whitetree_slab",
			() -> new BlockItem(BlockRegistry.WHITETREE_SLAB.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_BUTTON =
			ITEMS.register("whitetree_button",
			() -> new BlockItem(BlockRegistry.WHITETREE_BUTTON.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_PRESSURE_PLATE =
			ITEMS.register("whitetree_pressure_plate",
			() -> new BlockItem(BlockRegistry.WHITETREE_PRESSURE_PLATE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_DOOR =
			ITEMS.register("whitetree_door",
			() -> new BlockItem(BlockRegistry.WHITETREE_DOOR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_TRAPDOOR =
			ITEMS.register("whitetree_trapdoor",
			() -> new BlockItem(BlockRegistry.WHITETREE_TRAPDOOR.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_FENCE =
			ITEMS.register("whitetree_fence",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITETREE_FENCE_GATE =
			ITEMS.register("whitetree_fence_gate",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE_GATE.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITE_SEED =
			ITEMS.register("white_seed",
			() -> new Item(new Item.Properties()
					.food(ModFoods.WHITE_SEED_PROPPERTIES)
					.stacksTo(64)
					));
	
	public static final DeferredItem<Item> PALEWANDERER_EGG =
			ITEMS.register("pale_wanderer_spawn_egg",
			() -> new CustomSpawnEggItem(ModEntities.PALE_WANDERER, 0xbcebec,
					0x00ebff, new Item.Properties()));
	
	public static final DeferredItem<Item> WHITE_GRASS =
			ITEMS.register("white_grass",
			() -> new BlockItem(BlockRegistry.WHITE_GRASS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> TALL_WHITE_GRASS = 
			ITEMS.register("tall_white_grass",
			() -> new BlockItem(BlockRegistry.TALL_WHITE_GRASS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> WHITE_TORCHFLOWER = 
			ITEMS.register("white_torchflower",
			() -> new BlockItem(BlockRegistry.WHITE_TORCHFLOWER.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> RADIANCE_CATALYST =
			ITEMS.register("radiance_catalyst",
			() -> new RadianceCatalystItem(BlockRegistry.RADIANCE_CATALYST.get(),
					new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
	
	public static final DeferredItem<Item> PURE_ESSENCE =
			ITEMS.register("pure_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final DeferredItem<Item> RADIANT_CORE =
			ITEMS.register("radiant_core",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final DeferredItem<Item> PURE_VESSEL =
			ITEMS.register("pure_vessel",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final DeferredItem<Item> PROTECTOR_HEART =
			ITEMS.register("protector_heart",
			() -> new ProtectorHeartItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
	
	public static final DeferredItem<Item> DEEPFLOWER = 
			ITEMS.register("deepflower",
			() -> new BlockItem(BlockRegistry.DEEPFLOWER.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> BLUE_BUSH = 
			ITEMS.register("blue_bush",
			() -> new BlockItem(BlockRegistry.BLUE_BUSH.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> TALL_DARK_GRASS = 
			ITEMS.register("tall_dark_grass",
			() -> new BlockItem(BlockRegistry.TALL_DARK_GRASS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> DARK_GRASS = 
			ITEMS.register("dark_grass",
			() -> new BlockItem(BlockRegistry.DARK_GRASS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> PROTECTOR_INVOKER =
			ITEMS.register("protector_invoker",
			() -> new BlockItem(BlockRegistry.PROTECTOR_INVOKER.get(),
					new Item.Properties().rarity(Rarity.EPIC)));
	
	public static final DeferredItem<Item> DYING_PROTECTOR_HEART = 
			ITEMS.register("dying_protector_heart",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final DeferredItem<Item> ABYSSALITE =
			ITEMS.register("abyssalite",
			() -> new BlockItem(BlockRegistry.ABYSSALITE.get(),
					new Item.Properties()));
	
	public static final DeferredItem<Item> CHISELED_ABYSSALITE =
			ITEMS.register("chiseled_abyssalite",
			() -> new BlockItem(BlockRegistry.CHISELED_ABYSSALITE.get(),
					new Item.Properties()));
	
	public static final DeferredItem<Item> ABYSSALITE_BRICKS =
			ITEMS.register("abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS.get(),
					new Item.Properties()));
	
	public static final DeferredItem<Item> CRACKED_ABYSSALITE_BRICKS =
			ITEMS.register("cracked_abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get(),
					new Item.Properties()));

	public static final DeferredItem<Item> DARKTREE_BOAT =
			ITEMS.register("darktree_boat",
			() -> new ModBoatItem(false, ModBoatEntity.Type.DARKTREE,
					new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> DARKTREE_CHEST_BOAT =
			ITEMS.register("darktree_chest_boat",
			() -> new ModBoatItem(true, ModBoatEntity.Type.DARKTREE,
					new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> WHITETREE_BOAT =
			ITEMS.register("whitetree_boat",
					() -> new ModBoatItem(false, ModBoatEntity.Type.WHITETREE,
							new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> WHITETREE_CHEST_BOAT =
			ITEMS.register("whitetree_chest_boat",
					() -> new ModBoatItem(true, ModBoatEntity.Type.WHITETREE,
							new Item.Properties().stacksTo(1)));
	
	public static final DeferredItem<Item> ABYSSALITE_BRICKS_STAIRS =
			ITEMS.register("abyssalite_bricks_stair",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_STAIRS.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> ABYSSALITE_BRICKS_SLAB =
			ITEMS.register("abyssalite_bricks_slab",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_SLAB.get(), new Item.Properties()));
	
	public static final DeferredItem<Item> ABYSSALITE_BRICKS_WALL =
			ITEMS.register("abyssalite_bricks_wall",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_WALL.get(), new Item.Properties()));

	public static final DeferredItem<Item> DEEP_FRAGMENT =
			ITEMS.register("deep_fragment",
					() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));

	public static final DeferredItem<Item> ABYSSALITE_COAL_ORE =
			ITEMS.register("abyssalite_coal_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_COAL_ORE.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> ABYSSALITE_LAPIS_ORE =
			ITEMS.register("abyssalite_lapis_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_LAPIS_ORE.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> ABYSSALITE_DIAMOND_ORE =
			ITEMS.register("abyssalite_diamond_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_DIAMOND_ORE.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> ABYSSALITE_DEEP_ORE =
			ITEMS.register("abyssalite_deep_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_DEEP_ORE.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> PALE_SHARD =
			ITEMS.register("pale_shard",
					() -> new Item(new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> PALE_CRISTAL =
			ITEMS.register("pale_cristal",
					() -> new BlockItem(BlockRegistry.PALE_CRISTAL.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> PALE_CRISTAL_BLOCK =
			ITEMS.register("pale_cristal_block",
					() -> new BlockItem(BlockRegistry.PALE_CRISTAL_BLOCK.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> CITRINE_FRAGMENT =
			ITEMS.register("citrine_fragment",
					() -> new Item(new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> CITRINE =
			ITEMS.register("citrine",
					() -> new BlockItem(BlockRegistry.CITRINE.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> CITRINE_BLOCK =
			ITEMS.register("citrine_block",
					() -> new BlockItem(BlockRegistry.CITRINE_BLOCK.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> ECHO_CRISTAL =
			ITEMS.register("echo_cristal",
					() -> new BlockItem(BlockRegistry.ECHO_CRISTAL.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> ECHO_BLOCK =
			ITEMS.register("echo_block",
					() -> new BlockItem(BlockRegistry.ECHO_BLOCK.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> RUBY_FRAGMENT =
			ITEMS.register("ruby_fragment",
					() -> new Item(new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> RUBY =
			ITEMS.register("ruby",
					() -> new BlockItem(BlockRegistry.RUBY.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> RUBY_BLOCK =
			ITEMS.register("ruby_block",
					() -> new BlockItem(BlockRegistry.RUBY_BLOCK.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> MALACHITE_FRAGMENT =
			ITEMS.register("malachite_fragment",
					() -> new Item(new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> MALACHITE =
			ITEMS.register("malachite",
				() -> new BlockItem(BlockRegistry.MALACHITE.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> MALACHITE_BLOCK =
			ITEMS.register("malachite_block",
					() -> new BlockItem(BlockRegistry.MALACHITE_BLOCK.get(),
							new Item.Properties().fireResistant()));

	public static final DeferredItem<Item> RADIANCE_INGOTS =
			ITEMS.register("radiance_ingots",
					() -> new Item(new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
					));

	public static final DeferredItem<Item> TEMPER_EGG =
			ITEMS.register("temper_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.TEMPER, 0xb3fcff,
							0x72c8cc, new Item.Properties()));

	public static final DeferredItem<Item> RADIANT_STAFF =
			ITEMS.register("radiant_staff",
					() -> new RadiantStaffItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.durability(20)
							.fireResistant()));

	public static final DeferredItem<Item> SOLID_CORRUPTION =
			ITEMS.register("solid_corruption",
					() -> new BlockItem(BlockRegistry.SOLID_CORRUPTION.get(), new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()));

	public static final DeferredItem<Item> PARASYTE_EGG =
			ITEMS.register("parasyte_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.PARASYTE, 0x319473,
							0x30ab9c, new Item.Properties()));

	public static final DeferredItem<Item> PROTECTOR_EGG =
			ITEMS.register("protector_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.PROTECTOR, 0xbad3d4,
							0x3bedf5, new Item.Properties()));

	public static final DeferredItem<Item> CONTAGION_INCARNATION_EGG =
			ITEMS.register("contagion_incarnation_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.CONTAGION_INCARNATION, 0x046366,
							0xbdbd8a, new Item.Properties()));

	public static final DeferredItem<Item> NOCTILURE_EGG =
			ITEMS.register("noctilure_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.NOCTILURE, 0x08155c, 0xcad4fc,
							new Item.Properties()));

	public static final DeferredItem<Item> DYSFUNCTIONNING_CATALYST =
			ITEMS.register("dysfunctionning_catalyst",
					() -> new BlockItem(BlockRegistry.DYSFUNCTIONNING_CATALYST.get(),
							new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));

	public static final DeferredItem<Item> BLACK_LANTERN =
			ITEMS.register("black_lantern",
					() -> new BlockItem(BlockRegistry.BLACK_LANTERN.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> BLUE_GLOW_BERRIES =
			ITEMS.register("blue_glow_berries",
					() -> new Item(new Item.Properties()
							.food(ModFoods.GLOW_BERRY_PROPERTIES)));

	public static final DeferredItem<Item> NOCTILURE_TREAT =
			ITEMS.register("noctilure_treat",
					() -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> CRACKED_ABYSSALITE_BRICKS_SLAB =
			ITEMS.register("cracked_abyssalite_bricks_slab",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> CRACKED_ABYSSALITE_BRICKS_STAIR =
			ITEMS.register("cracked_abyssalite_bricks_stair",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> CRACKED_ABYSSALITE_BRICKS_WALL =
			ITEMS.register("cracked_abyssalite_bricks_wall",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_WALL.get(),
							new Item.Properties()));

	public static final DeferredItem<Item> SHADOW_EGG =
			ITEMS.register("shadow_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.SHADOW, 0x2d4854, 0x2d544a,
							new Item.Properties()));
	
	public static final DeferredItem<Item> CONTAGION_INCARNATION_SKULL =
			ITEMS.register("contagion_incarnation_skull",
					() -> new BlockItem(BlockRegistry.CONTAGION_INCARNATION_SKULL.get(),
							new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).fireResistant()));

	public static final DeferredItem<Item> REINFORCED_GLASS =
			ITEMS.register("reinforced_glass",
					() -> new BlockItem(BlockRegistry.REINFORCED_GLASS.get(),
							new Item.Properties().fireResistant()));
	
	public static final DeferredItem<Item> SOUL_SPAWNER =
			ITEMS.register("soul_spawner",
					() -> new BlockItem(BlockRegistry.SOUL_SPAWNER.get(),
							new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

	public static final DeferredItem<Item> ABYSSAL_SCYTHE =
			ITEMS.register("abyssal_scythe",
					() -> new ScytheItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.durability(2031)
							.fireResistant()
							.attributes(ScytheItem.createAttributes(12, -3F))));

	public static final DeferredItem<Item> RADIANT_SPEAR =
			ITEMS.register("radiant_spear",
					() -> new SpearItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.durability(2031)
							.fireResistant()
							.attributes(SpearItem.createAttributes(10, -3F))));

	public static final DeferredItem<Item> ABYSS_MUSIC_DISC =
			ITEMS.register("music_disc_abyss",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.ABYSS)));

	public static final DeferredItem<Item> INCARNATION_MUSIC_DISC =
			ITEMS.register("music_disc_incarnation",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.INCARNATION)));

	public static final DeferredItem<Item> DEEP_FOREST_MUSIC_DISC =
			ITEMS.register("music_disc_deepforest",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.DEEPFOREST)));

	public static final DeferredItem<Item> WHITE_FOREST_MUSIC_DISC =
			ITEMS.register("music_disc_whiteforest",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.WHITEFOREST)));

	public static final DeferredItem<Item> WHISTLE =
			ITEMS.register("whistle",
					() -> new WhistleItem(new Item.Properties().stacksTo(1)));
}
