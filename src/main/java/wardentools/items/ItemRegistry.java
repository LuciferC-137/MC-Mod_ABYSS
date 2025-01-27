package wardentools.items;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.DarktreeBoat;

public class ItemRegistry {
	public static final DeferredRegister<Item> REGISTAR = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
	
	
	public static final RegistryObject<Item> DEEPINGOTS =
			REGISTAR.register("deepingots",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepingots")))
					));
	
	public static final RegistryObject<Item> DEEPBLOCK =
			REGISTAR.register("deepblock",
			() -> new BlockItem(BlockRegistry.DEEPBLOCK.get(), new Item.Properties()
					.rarity(Rarity.RARE)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepblock")))
					));
	
	public static final RegistryObject<Item> DEEPCRISTAL =
			REGISTAR.register("deepcristal.json",
			() -> new BlockItem(BlockRegistry.DEEP_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal.json")))
					));
	
	public static final RegistryObject<Item> RADIANCE_FRAGMENT =
			REGISTAR.register("radiance_fragment",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_fragment")))
					));
	
	public static final RegistryObject<Item> RADIANCE_CRISTAL =
			REGISTAR.register("radiance_cristal",
			() -> new BlockItem(BlockRegistry.RADIANCE_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal")))
					));
	
	public static final RegistryObject<Item> WARDEN_HEART =
			REGISTAR.register("warden_heart",
			() -> new WardenHeartItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "warden_heart")))
					));
	
	public static final RegistryObject<Item> DARKTREE_LOG =
			REGISTAR.register("darktree_log",
		    () -> new BlockItem(BlockRegistry.DARKTREE_LOG.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_log")))
			));
	
	public static final RegistryObject<Item> DARKTREE_LEAVES =
			REGISTAR.register("darktree_leaves",
			() -> new BlockItem(BlockRegistry.DARKTREE_LEAVES.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_leaves")))
			));
	
	public static final RegistryObject<Item> STRIPPED_DARKTREE_LOG =
			REGISTAR.register("stripped_darktree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_LOG.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_darktree_log")))
			));
	
	public static final RegistryObject<Item> DARKTREE_WOOD =
			REGISTAR.register("darktree_wood",
			() -> new BlockItem(BlockRegistry.DARKTREE_WOOD.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_wood")))
			));
	
	public static final RegistryObject<Item> STRIPPED_DARKTREE_WOOD =
			REGISTAR.register("stripped_darktree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_WOOD.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_darktree_wood")))
			));
	
	public static final RegistryObject<Item> DARKTREE_PLANKS =
			REGISTAR.register("darktree_planks",
			() -> new BlockItem(BlockRegistry.DARKTREE_PLANKS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_planks")))
			));
	
	public static final RegistryObject<Item> DARKTREE_STAIRS =
			REGISTAR.register("darktree_stair",
			() -> new BlockItem(BlockRegistry.DARKTREE_STAIR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_stair")))
			));
	
	public static final RegistryObject<Item> DARKTREE_SLAB =
			REGISTAR.register("darktree_slab",
			() -> new BlockItem(BlockRegistry.DARKTREE_SLAB.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_slab")))
			));
	
	public static final RegistryObject<Item> DARKTREE_BUTTON =
			REGISTAR.register("darktree_button",
			() -> new BlockItem(BlockRegistry.DARKTREE_BUTTON.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_button")))
			));
	
	public static final RegistryObject<Item> DARKTREE_PRESSURE_PLATE =
			REGISTAR.register("darktree_pressure_plate",
			() -> new BlockItem(BlockRegistry.DARKTREE_PRESSURE_PLATE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_pressure_plate")))
			));
	
	public static final RegistryObject<Item> DARKTREE_DOOR =
			REGISTAR.register("darktree_door",
			() -> new BlockItem(BlockRegistry.DARKTREE_DOOR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_door")))
			));
	
	public static final RegistryObject<Item> DARKTREE_TRAPDOOR =
			REGISTAR.register("darktree_trapdoor",
			() -> new BlockItem(BlockRegistry.DARKTREE_TRAPDOOR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_trapdoor")))
			));
	
	public static final RegistryObject<Item> DARKTREE_FENCE =
			REGISTAR.register("darktree_fence",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_fence")))
			));
	
	public static final RegistryObject<Item> DARKTREE_FENCE_GATE =
			REGISTAR.register("darktree_fence_gate",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE_GATE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_fence_gate")))
			));
	
	public static final RegistryObject<Item> DARKTREE_SAPLING =
			REGISTAR.register("darktree_sapling",
			() -> new BlockItem(BlockRegistry.DARKTREE_SAPLING.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_sapling")))
			));
	
	public static final RegistryObject<Item> DARKGRASS_BLOCK = 
			REGISTAR.register("darkgrass_block",
			() -> new BlockItem(BlockRegistry.DARKGRASS_BLOCK.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darkgrass_block")))
			));
	
	public static final RegistryObject<Item> DARKDIRT = 
			REGISTAR.register("darkdirt",
			() -> new BlockItem(BlockRegistry.DARKDIRT.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darkdirt")))
			));
	
	public static final RegistryObject<Item> DEEPLURKER_EGG =
			REGISTAR.register("deeplurker_spawn_egg",
			() -> new CustomSpawnEggItem(ModEntities.DEEPLURKER, 0x005693,
					0x00ebff, new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deeplurker_spawn_egg")))
					));
	
	public static final RegistryObject<Item> DEEP_FRUIT =
			REGISTAR.register("deep_fruit",
			() -> new Item(new Item.Properties()
					.food(ModFoods.DEEP_FRUIT_PROPERTIES, ModFoods.DEEP_FRUIT_EFFECT)
					.stacksTo(64)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deep_fruit")))
					));
	
	public static final RegistryObject<Item> ABYSS_DIVER =
			REGISTAR.register("abyss_diver",
			() -> new AbyssDiverItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.durability(20)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyss_diver")))
					));
	
	public static final RegistryObject<Item> WIND_WHISPERER = 
			REGISTAR.register("wind_whisperer",
			() -> new WindWhispererItem(BlockRegistry.WIND_WHISPERER.get(), new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "wind_whisperer")))
					));
	
	public static final RegistryObject<Item> CORRUPTED_ESSENCE =
			REGISTAR.register("corrupted_essence", 
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corrupted_essence")))
					));
	
	public static final RegistryObject<Item> CORRUPTED_VESSEL =
			REGISTAR.register("corrupted_vessel",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.RARE)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "corrupted_vessel")))
					));
	
	public static final RegistryObject<Item> DARK_STICK =
			REGISTAR.register("dark_stick",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.fireResistant()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dark_stick")))
					));
	
	public static final RegistryObject<Item> WHITETREE_LOG =
			REGISTAR.register("whitetree_log",
		    () -> new BlockItem(BlockRegistry.WHITETREE_LOG.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_log")))
			));
	
	public static final RegistryObject<Item> WHITETREE_LEAVES =
			REGISTAR.register("whitetree_leaves",
			() -> new BlockItem(BlockRegistry.WHITETREE_LEAVES.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_leaves")))
			));
	
	public static final RegistryObject<Item> STRIPPED_WHITETREE_LOG =
			REGISTAR.register("stripped_whitetree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_LOG.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_whitetree_log")))
			));
	
	public static final RegistryObject<Item> WHITETREE_WOOD =
			REGISTAR.register("whitetree_wood",
			() -> new BlockItem(BlockRegistry.WHITETREE_WOOD.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_wood")))
			));
	
	public static final RegistryObject<Item> STRIPPED_WHITETREE_WOOD =
			REGISTAR.register("stripped_whitetree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_WOOD.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "stripped_whitetree_wood")))
			));
	
	public static final RegistryObject<Item> WHITETREE_SAPLING =
			REGISTAR.register("whitetree_sapling",
			() -> new BlockItem(BlockRegistry.WHITETREE_SAPLING.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_sapling")))
			));
	
	public static final RegistryObject<Item> WHITETREE_PLANKS =
			REGISTAR.register("whitetree_planks",
			() -> new BlockItem(BlockRegistry.WHITETREE_PLANKS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_planks")))
			));
	
	public static final RegistryObject<Item> WHITETREE_STAIRS =
			REGISTAR.register("whitetree_stair",
			() -> new BlockItem(BlockRegistry.WHITETREE_STAIR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_stair")))
			));
	
	public static final RegistryObject<Item> WHITETREE_SLAB =
			REGISTAR.register("whitetree_slab",
			() -> new BlockItem(BlockRegistry.WHITETREE_SLAB.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_slab")))
			));
	
	public static final RegistryObject<Item> WHITETREE_BUTTON =
			REGISTAR.register("whitetree_button",
			() -> new BlockItem(BlockRegistry.WHITETREE_BUTTON.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_button")))
			));
	
	public static final RegistryObject<Item> WHITETREE_PRESSURE_PLATE =
			REGISTAR.register("whitetree_pressure_plate",
			() -> new BlockItem(BlockRegistry.WHITETREE_PRESSURE_PLATE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_pressure_plate")))
			));
	
	public static final RegistryObject<Item> WHITETREE_DOOR =
			REGISTAR.register("whitetree_door",
			() -> new BlockItem(BlockRegistry.WHITETREE_DOOR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_door")))
			));
	
	public static final RegistryObject<Item> WHITETREE_TRAPDOOR =
			REGISTAR.register("whitetree_trapdoor",
			() -> new BlockItem(BlockRegistry.WHITETREE_TRAPDOOR.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_trapdoor")))
			));
	
	public static final RegistryObject<Item> WHITETREE_FENCE =
			REGISTAR.register("whitetree_fence",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_fence")))
			));
	
	public static final RegistryObject<Item> WHITETREE_FENCE_GATE =
			REGISTAR.register("whitetree_fence_gate",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE_GATE.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_fence_gate")))
			));
	
	public static final RegistryObject<Item> WHITE_SEED =
			REGISTAR.register("white_seed",
			() -> new Item(new Item.Properties()
					.food(ModFoods.WHITE_SEED_PROPPERTIES, ModFoods.WHITE_SEED_EFFECT)
					.stacksTo(64)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_seed")))
					));
	
	public static final RegistryObject<Item> PALEWANDERER_EGG =
			REGISTAR.register("pale_wanderer_spawn_egg",
			() -> new CustomSpawnEggItem(ModEntities.PALE_WANDERER, 0xbcebec,
					0x00ebff, new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_wanderer_spawn_egg")))
					));
	
	public static final RegistryObject<Item> WHITE_GRASS =
			REGISTAR.register("white_grass",
			() -> new BlockItem(BlockRegistry.WHITE_GRASS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_grass")))
					));
	
	public static final RegistryObject<Item> TALL_WHITE_GRASS = 
			REGISTAR.register("tall_white_grass",
			() -> new BlockItem(BlockRegistry.TALL_WHITE_GRASS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "tall_white_grass")))
					));
	
	public static final RegistryObject<Item> WHITE_TORCHFLOWER = 
			REGISTAR.register("white_torchflower",
			() -> new BlockItem(BlockRegistry.WHITE_TORCHFLOWER.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "white_torchflower")))
					));
	
	public static final RegistryObject<Item> RADIANCE_CATALYST =
			REGISTAR.register("radiance_catalyst",
			() -> new RadianceCatalystItem(BlockRegistry.RADIANCE_CATALYST.get(),
					new Item.Properties().rarity(Rarity.RARE).stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_catalyst")))
					));
	
	public static final RegistryObject<Item> PURE_ESSENCE =
			REGISTAR.register("pure_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pure_essence")))
					));
	
	public static final RegistryObject<Item> RADIANT_CORE =
			REGISTAR.register("radiant_core",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiant_core")))
					));
	
	public static final RegistryObject<Item> PURE_VESSEL =
			REGISTAR.register("pure_vessel",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pure_vessel")))
					));
	
	public static final RegistryObject<Item> PROTECTOR_HEART =
			REGISTAR.register("protector_heart",
			() -> new ProtectorHeartItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_heart")))
					));
	
	public static final RegistryObject<Item> DEEPFLOWER = 
			REGISTAR.register("deepflower",
			() -> new BlockItem(BlockRegistry.DEEPFLOWER.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepflower")))
					));
	
	public static final RegistryObject<Item> BLUE_BUSH = 
			REGISTAR.register("blue_bush",
			() -> new BlockItem(BlockRegistry.BLUE_BUSH.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blue_bush")))
					));
	
	public static final RegistryObject<Item> TALL_DARK_GRASS = 
			REGISTAR.register("tall_dark_grass",
			() -> new BlockItem(BlockRegistry.TALL_DARK_GRASS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "tall_dark_grass")))
					));
	
	public static final RegistryObject<Item> DARK_GRASS = 
			REGISTAR.register("dark_grass",
			() -> new BlockItem(BlockRegistry.DARK_GRASS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dark_grass")))
					));
	
	public static final RegistryObject<Item> PROTECTOR_INVOKER =
			REGISTAR.register("protector_invoker",
			() -> new BlockItem(BlockRegistry.PROTECTOR_INVOKER.get(),
					new Item.Properties().rarity(Rarity.EPIC)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_invoker")))
					));
	
	public static final RegistryObject<Item> DYING_PROTECTOR_HEART = 
			REGISTAR.register("dying_protector_heart",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dying_protector_heart")))
					));
	
	public static final RegistryObject<Item> ABYSSALITE =
			REGISTAR.register("abyssalite",
			() -> new BlockItem(BlockRegistry.ABYSSALITE.get(),
					new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite")))
					));
	
	public static final RegistryObject<Item> CHISELED_ABYSSALITE =
			REGISTAR.register("chiseled_abyssalite",
			() -> new BlockItem(BlockRegistry.CHISELED_ABYSSALITE.get(),
					new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "chiseled_abyssalite")))
					));
	
	public static final RegistryObject<Item> ABYSSALITE_BRICKS =
			REGISTAR.register("abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS.get(),
					new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks")))
					));
	
	public static final RegistryObject<Item> CRACKED_ABYSSALITE_BRICKS =
			REGISTAR.register("cracked_abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get(),
					new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks")))
					));

	public static final RegistryObject<Item> DARKTREE_BOAT =
			REGISTAR.register("darktree_boat",
			() -> new BoatItem(BoatItem.Type.DARKTREE_BOAT,
					new Item.Properties().stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_boat")))
					));

	public static final RegistryObject<Item> DARKTREE_CHEST_BOAT =
			REGISTAR.register("darktree_chest_boat",
			() -> new BoatItem(BoatItem.Type.DARKTREE_CHEST_BOAT,
					new Item.Properties().stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "darktree_chest_boat")))
					));

	public static final RegistryObject<Item> WHITETREE_BOAT =
			REGISTAR.register("whitetree_boat",
					() -> new BoatItem(BoatItem.Type.WHITETREE_BOAT,
							new Item.Properties().stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_boat")))
					));

	public static final RegistryObject<Item> WHITETREE_CHEST_BOAT =
			REGISTAR.register("whitetree_chest_boat",
					() -> new BoatItem(BoatItem.Type.WHITETREE_CHEST_BOAT,
							new Item.Properties().stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whitetree_chest_boat")))
					));
	
	public static final RegistryObject<Item> ABYSSALITE_BRICKS_STAIRS =
			REGISTAR.register("abyssalite_bricks_stair",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_STAIRS.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_stair")))
					));
	
	public static final RegistryObject<Item> ABYSSALITE_BRICKS_SLAB =
			REGISTAR.register("abyssalite_bricks_slab",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_SLAB.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_slab")))
					));

	public static final RegistryObject<Item> ABYSSALITE_BRICKS_WALL =
			REGISTAR.register("abyssalite_bricks_wall",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS_WALL.get(), new Item.Properties()
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_bricks_wall")))
					));

	public static final RegistryObject<Item> DEEP_FRAGMENT =
			REGISTAR.register("deep_fragment",
					() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deep_fragment")))
					));

	public static final RegistryObject<Item> ABYSSALITE_COAL_ORE =
			REGISTAR.register("abyssalite_coal_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_COAL_ORE.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_coal_ore")))
					));

	public static final RegistryObject<Item> ABYSSALITE_LAPIS_ORE =
			REGISTAR.register("abyssalite_lapis_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_LAPIS_ORE.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_lapis_ore")))
					));

	public static final RegistryObject<Item> ABYSSALITE_DIAMOND_ORE =
			REGISTAR.register("abyssalite_diamond_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_DIAMOND_ORE.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_diamond_ore")))
					));

	public static final RegistryObject<Item> ABYSSALITE_DEEP_ORE =
			REGISTAR.register("abyssalite_deep_ore",
					() -> new BlockItem(BlockRegistry.ABYSSALITE_DEEP_ORE.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssalite_deep_ore")))
					));

	public static final RegistryObject<Item> PALE_SHARD =
			REGISTAR.register("pale_shard",
					() -> new Item(new Item.Properties().fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_shard")))
					));

	public static final RegistryObject<Item> PALE_CRISTAL =
			REGISTAR.register("pale_cristal",
					() -> new BlockItem(BlockRegistry.PALE_CRISTAL.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_cristal")))
					));

	public static final RegistryObject<Item> PALE_CRISTAL_BLOCK =
			REGISTAR.register("pale_cristal_block",
					() -> new BlockItem(BlockRegistry.PALE_CRISTAL_BLOCK.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_cristal_block")))
					));

	public static final RegistryObject<Item> CITRINE_FRAGMENT =
			REGISTAR.register("citrine_fragment",
					() -> new Item(new Item.Properties().fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine_fragment")))
					));

	public static final RegistryObject<Item> CITRINE =
			REGISTAR.register("citrine",
					() -> new BlockItem(BlockRegistry.CITRINE.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine")))
					));

	public static final RegistryObject<Item> CITRINE_BLOCK =
			REGISTAR.register("citrine_block",
					() -> new BlockItem(BlockRegistry.CITRINE_BLOCK.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "citrine_block")))
					));

	public static final RegistryObject<Item> ECHO_CRISTAL =
			REGISTAR.register("echo_cristal",
					() -> new BlockItem(BlockRegistry.ECHO_CRISTAL.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "echo_cristal")))
					));

	public static final RegistryObject<Item> ECHO_BLOCK =
			REGISTAR.register("echo_block",
					() -> new BlockItem(BlockRegistry.ECHO_BLOCK.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "echo_block")))
					));

	public static final RegistryObject<Item> RUBY_FRAGMENT =
			REGISTAR.register("ruby_fragment",
					() -> new Item(new Item.Properties().fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby_fragment")))
					));

	public static final RegistryObject<Item> RUBY =
			REGISTAR.register("ruby",
					() -> new BlockItem(BlockRegistry.RUBY.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby")))
					));

	public static final RegistryObject<Item> RUBY_BLOCK =
			REGISTAR.register("ruby_block",
					() -> new BlockItem(BlockRegistry.RUBY_BLOCK.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "ruby_block")))
					));

	public static final RegistryObject<Item> MALACHITE_FRAGMENT =
			REGISTAR.register("malachite_fragment",
					() -> new Item(new Item.Properties().fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite_fragment")))
					));

	public static final RegistryObject<Item> MALACHITE =
			REGISTAR.register("malachite",
				() -> new BlockItem(BlockRegistry.MALACHITE.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite")))
				));

	public static final RegistryObject<Item> MALACHITE_BLOCK =
			REGISTAR.register("malachite_block",
					() -> new BlockItem(BlockRegistry.MALACHITE_BLOCK.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "malachite_block")))
					));

	public static final RegistryObject<Item> RADIANCE_INGOTS =
			REGISTAR.register("radiance_ingots",
					() -> new Item(new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_ingots")))
					));

	public static final RegistryObject<Item> TEMPER_EGG =
			REGISTAR.register("temper_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.TEMPER, 0xb3fcff,
							0x72c8cc, new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "temper_spawn_egg")))
					));

	public static final RegistryObject<Item> RADIANT_STAFF =
			REGISTAR.register("radiant_staff",
					() -> new RadiantStaffItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.durability(20)
							.fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiant_staff")))
					));

	public static final RegistryObject<Item> SOLID_CORRUPTION =
			REGISTAR.register("solid_corruption",
					() -> new BlockItem(BlockRegistry.SOLID_CORRUPTION.get(), new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "solid_corruption")))
					));

	public static final RegistryObject<Item> PARASYTE_EGG =
			REGISTAR.register("parasyte_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.PARASYTE, 0x319473,
							0x30ab9c, new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "parasyte_spawn_egg")))
					));

	public static final RegistryObject<Item> PROTECTOR_EGG =
			REGISTAR.register("protector_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.PROTECTOR, 0xbad3d4,
							0x3bedf5, new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_spawn_egg")))
					));

	public static final RegistryObject<Item> CONTAGION_INCARNATION_EGG =
			REGISTAR.register("contagion_incarnation_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.CONTAGION_INCARNATION, 0x046366,
							0xbdbd8a, new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation_spawn_egg")))
					));

	public static final RegistryObject<Item> NOCTILURE_EGG =
			REGISTAR.register("noctilure_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.NOCTILURE, 0x08155c, 0xcad4fc,
							new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "noctilure_spawn_egg")))
					));

	public static final RegistryObject<Item> DYSFUNCTIONNING_CATALYST =
			REGISTAR.register("dysfunctionning_catalyst",
					() -> new BlockItem(BlockRegistry.DYSFUNCTIONNING_CATALYST.get(),
							new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "dysfunctionning_catalyst")))
					));

	public static final RegistryObject<Item> BLACK_LANTERN =
			REGISTAR.register("black_lantern",
					() -> new BlockItem(BlockRegistry.BLACK_LANTERN.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "black_lantern")))
					));

	public static final RegistryObject<Item> BLUE_GLOW_BERRIES =
			REGISTAR.register("blue_glow_berries",
					() -> new Item(new Item.Properties()
							.food(ModFoods.BLUE_GLOW_BERRY_PROPERTIES, ModFoods.BLUE_GLOW_BERRY_EFFECT)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "blue_glow_berries")))
					));

	public static final RegistryObject<Item> NOCTILURE_TREAT =
			REGISTAR.register("noctilure_treat",
					() -> new Item(new Item.Properties()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "noctilure_treat")))
					));

	public static final RegistryObject<Item> CRACKED_ABYSSALITE_BRICKS_SLAB =
			REGISTAR.register("cracked_abyssalite_bricks_slab",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_SLAB.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_slab")))
					));

	public static final RegistryObject<Item> CRACKED_ABYSSALITE_BRICKS_STAIR =
			REGISTAR.register("cracked_abyssalite_bricks_stair",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_STAIR.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_stair")))
					));

	public static final RegistryObject<Item> CRACKED_ABYSSALITE_BRICKS_WALL =
			REGISTAR.register("cracked_abyssalite_bricks_wall",
					() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS_WALL.get(),
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "cracked_abyssalite_bricks_wall")))
					));

	public static final RegistryObject<Item> SHADOW_EGG =
			REGISTAR.register("shadow_spawn_egg",
					() -> new CustomSpawnEggItem(ModEntities.SHADOW, 0x2d4854, 0x2d544a,
							new Item.Properties()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "shadow_spawn_egg")))
					));
	
	public static final RegistryObject<Item> CONTAGION_INCARNATION_SKULL =
			REGISTAR.register("contagion_incarnation_skull",
					() -> new BlockItem(BlockRegistry.CONTAGION_INCARNATION_SKULL.get(),
							new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation_skull")))
					));

	public static final RegistryObject<Item> REINFORCED_GLASS =
			REGISTAR.register("reinforced_glass",
					() -> new BlockItem(BlockRegistry.REINFORCED_GLASS.get(),
							new Item.Properties().fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "reinforced_glass")))
					));
	
	public static final RegistryObject<Item> SOUL_SPAWNER =
			REGISTAR.register("soul_spawner",
					() -> new BlockItem(BlockRegistry.SOUL_SPAWNER.get(),
							new Item.Properties().rarity(Rarity.EPIC).fireResistant()
									.setId(ResourceKey.create(Registries.ITEM,
											ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "soul_spawner")))
					));

	public static final RegistryObject<Item> ABYSSAL_SCYTHE =
			REGISTAR.register("abyssal_scythe",
					() -> new ScytheItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "abyssal_scythe")))
							, 12, -3F));

	public static final RegistryObject<Item> RADIANT_SPEAR =
			REGISTAR.register("radiant_spear",
					() -> new SpearItem(new Item.Properties()
							.stacksTo(1)
							.rarity(Rarity.RARE)
							.fireResistant()
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiant_spear")))
							, 10, -3F));

	public static final RegistryObject<Item> ABYSS_MUSIC_DISC =
			REGISTAR.register("music_disc_abyss",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.ABYSS)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "music_disc_abyss")))
							));

	public static final RegistryObject<Item> INCARNATION_MUSIC_DISC =
			REGISTAR.register("music_disc_incarnation",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.INCARNATION)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "music_disc_incarnation")))
							));

	public static final RegistryObject<Item> DEEP_FOREST_MUSIC_DISC =
			REGISTAR.register("music_disc_deepforest",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.DEEPFOREST)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "music_disc_deepforest")))
							));

	public static final RegistryObject<Item> WHITE_FOREST_MUSIC_DISC =
			REGISTAR.register("music_disc_whiteforest",
					() -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
							.jukeboxPlayable(ModJukeBoxSongsGenerator.WHITEFOREST)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "music_disc_whiteforest")))
							));

	public static final RegistryObject<Item> WHISTLE =
			REGISTAR.register("whistle",
					() -> new WhistleItem(new Item.Properties().stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "whistle")))
							));
}
