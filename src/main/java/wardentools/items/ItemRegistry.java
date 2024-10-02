package wardentools.items;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ModBoatEntity;
import wardentools.entity.custom.ModChestBoatEntity;

public class ItemRegistry {
	public static final DeferredRegister<Item> REGISTAR = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
	
	
	public static final RegistryObject<Item> DEEPINGOTS =
			REGISTAR.register("deepingots",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final RegistryObject<BlockItem> DEEPBLOCK =
			REGISTAR.register("deepblock",
			() -> new BlockItem(BlockRegistry.DEEPBLOCK.get(), new Item.Properties()
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final RegistryObject<BlockItem> DEEPCRISTAL =
			REGISTAR.register("deepcristal",
			() -> new BlockItem(BlockRegistry.DEEP_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> PALE_FRAGMENT =
			REGISTAR.register("pale_fragment", 
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final RegistryObject<BlockItem> PALE_SHARD = 
			REGISTAR.register("pale_shard",
			() -> new BlockItem(BlockRegistry.PALE_SHARD.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> WARDEN_HEART =
			REGISTAR.register("warden_heart",
			() -> new WardenHeartItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> DARKTREE_LOG =
			REGISTAR.register("darktree_log",
		    () -> new BlockItem(BlockRegistry.DARKTREE_LOG.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_LEAVES =
			REGISTAR.register("darktree_leaves",
			() -> new BlockItem(BlockRegistry.DARKTREE_LEAVES.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> STRIPPED_DARKTREE_LOG =
			REGISTAR.register("stripped_darktree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_LOG.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_WOOD =
			REGISTAR.register("darktree_wood",
			() -> new BlockItem(BlockRegistry.DARKTREE_WOOD.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> STRIPPED_DARKTREE_WOOD =
			REGISTAR.register("stripped_darktree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_DARKTREE_WOOD.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_PLANKS =
			REGISTAR.register("darktree_planks",
			() -> new BlockItem(BlockRegistry.DARKTREE_PLANKS.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_STAIRS =
			REGISTAR.register("darktree_stair",
			() -> new BlockItem(BlockRegistry.DARKTREE_STAIR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_SLAB =
			REGISTAR.register("darktree_slab",
			() -> new BlockItem(BlockRegistry.DARKTREE_SLAB.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_BUTTON =
			REGISTAR.register("darktree_button",
			() -> new BlockItem(BlockRegistry.DARKTREE_BUTTON.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_PRESSURE_PLATE =
			REGISTAR.register("darktree_pressure_plate",
			() -> new BlockItem(BlockRegistry.DARKTREE_PRESSURE_PLATE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_DOOR =
			REGISTAR.register("darktree_door",
			() -> new BlockItem(BlockRegistry.DARKTREE_DOOR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_TRAPDOOR =
			REGISTAR.register("darktree_trapdoor",
			() -> new BlockItem(BlockRegistry.DARKTREE_TRAPDOOR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_FENCE =
			REGISTAR.register("darktree_fence",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_FENCE_GATE =
			REGISTAR.register("darktree_fence_gate",
			() -> new BlockItem(BlockRegistry.DARKTREE_FENCE_GATE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKTREE_SAPLING =
			REGISTAR.register("darktree_sapling",
			() -> new BlockItem(BlockRegistry.DARKTREE_SAPLING.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKGRASS_BLOCK = 
			REGISTAR.register("darkgrass_block",
			() -> new BlockItem(BlockRegistry.DARKGRASS_BLOCK.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARKDIRT = 
			REGISTAR.register("darkdirt",
			() -> new BlockItem(BlockRegistry.DARKDIRT.get(), new Item.Properties()));
	
	public static final RegistryObject<ForgeSpawnEggItem> DEEPLURKER_EGG =
			REGISTAR.register("deeplurker_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntities.DEEPLURKER, 0x005693,
					0x00ebff, new Item.Properties()));
	
	public static final RegistryObject<Item> DEEP_FRUIT =
			REGISTAR.register("deep_fruit",
			() -> new Item(new Item.Properties()
					.food(ModFoods.DEEP_FRUIT_PROPERTIES)
					.stacksTo(64)
					));
	
	public static final RegistryObject<Item> ABYSS_DIVER =
			REGISTAR.register("abyss_diver",
			() -> new AbyssDiverItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.defaultDurability(20)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> WIND_WHISPERER = 
			REGISTAR.register("wind_whisperer",
			() -> new WindWhisperer(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> CORRUPTED_ESSENCE =
			REGISTAR.register("corrupted_essence", 
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> CORRUPTED_VESSEL =
			REGISTAR.register("corrupted_vessel",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.RARE)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> DARK_STICK =
			REGISTAR.register("dark_stick",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.fireResistant()
					));
	
	public static final RegistryObject<Item> WHITETREE_LOG =
			REGISTAR.register("whitetree_log",
		    () -> new BlockItem(BlockRegistry.WHITETREE_LOG.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_LEAVES =
			REGISTAR.register("whitetree_leaves",
			() -> new BlockItem(BlockRegistry.WHITETREE_LEAVES.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> STRIPPED_WHITETREE_LOG =
			REGISTAR.register("stripped_whitetree_log",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_LOG.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_WOOD =
			REGISTAR.register("whitetree_wood",
			() -> new BlockItem(BlockRegistry.WHITETREE_WOOD.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> STRIPPED_WHITETREE_WOOD =
			REGISTAR.register("stripped_whitetree_wood",
			() -> new BlockItem(BlockRegistry.STRIPPED_WHITETREE_WOOD.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_SAPLING =
			REGISTAR.register("whitetree_sapling",
			() -> new BlockItem(BlockRegistry.WHITETREE_SAPLING.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_PLANKS =
			REGISTAR.register("whitetree_planks",
			() -> new BlockItem(BlockRegistry.WHITETREE_PLANKS.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_STAIRS =
			REGISTAR.register("whitetree_stair",
			() -> new BlockItem(BlockRegistry.WHITETREE_STAIR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_SLAB =
			REGISTAR.register("whitetree_slab",
			() -> new BlockItem(BlockRegistry.WHITETREE_SLAB.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_BUTTON =
			REGISTAR.register("whitetree_button",
			() -> new BlockItem(BlockRegistry.WHITETREE_BUTTON.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_PRESSURE_PLATE =
			REGISTAR.register("whitetree_pressure_plate",
			() -> new BlockItem(BlockRegistry.WHITETREE_PRESSURE_PLATE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_DOOR =
			REGISTAR.register("whitetree_door",
			() -> new BlockItem(BlockRegistry.WHITETREE_DOOR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_TRAPDOOR =
			REGISTAR.register("whitetree_trapdoor",
			() -> new BlockItem(BlockRegistry.WHITETREE_TRAPDOOR.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_FENCE =
			REGISTAR.register("whitetree_fence",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITETREE_FENCE_GATE =
			REGISTAR.register("whitetree_fence_gate",
			() -> new BlockItem(BlockRegistry.WHITETREE_FENCE_GATE.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITE_SEED =
			REGISTAR.register("white_seed",
			() -> new Item(new Item.Properties()
					.food(ModFoods.WHITE_SEED_PROPPERTIES)
					.stacksTo(64)
					));
	
	public static final RegistryObject<ForgeSpawnEggItem> PALEWANDERER_EGG =
			REGISTAR.register("pale_wanderer_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntities.PALE_WANDERER, 0xbcebec,
					0x00ebff, new Item.Properties()));
	
	public static final RegistryObject<Item> WHITE_GRASS =
			REGISTAR.register("white_grass",
			() -> new BlockItem(BlockRegistry.WHITE_GRASS.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> TALL_WHITE_GRASS = 
			REGISTAR.register("tall_white_grass",
			() -> new BlockItem(BlockRegistry.TALL_WHITE_GRASS.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> WHITE_TORCHFLOWER = 
			REGISTAR.register("white_torchflower",
			() -> new BlockItem(BlockRegistry.WHITE_TORCHFLOWER.get(), new Item.Properties()));
	
	public static final RegistryObject<RadianceCatalystItem> RADIANCE_CATALYST =
			REGISTAR.register("radiance_catalyst",
			() -> new RadianceCatalystItem(BlockRegistry.RADIANCE_CATALYST.get(),
					new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
	
	public static final RegistryObject<Item> PURE_ESSENCE =
			REGISTAR.register("pure_essence",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final RegistryObject<Item> RADIANT_CORE =
			REGISTAR.register("radiant_core",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final RegistryObject<Item> PURE_VESSEL =
			REGISTAR.register("pure_vessel",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final RegistryObject<Item> PROTECTOR_HEART =
			REGISTAR.register("protector_heart",
			() -> new ProtectorHeartItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
	
	public static final RegistryObject<Item> DEEPFLOWER = 
			REGISTAR.register("deepflower",
			() -> new BlockItem(BlockRegistry.DEEPFLOWER.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> BLUE_BUSH = 
			REGISTAR.register("blue_bush",
			() -> new BlockItem(BlockRegistry.BLUE_BUSH.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> TALL_DARK_GRASS = 
			REGISTAR.register("tall_dark_grass",
			() -> new BlockItem(BlockRegistry.TALL_DARK_GRASS.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> DARK_GRASS = 
			REGISTAR.register("dark_grass",
			() -> new BlockItem(BlockRegistry.DARK_GRASS.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> PROTECTOR_INVOKER =
			REGISTAR.register("protector_invoker",
			() -> new BlockItem(BlockRegistry.PROTECTOR_INVOKER.get(),
					new Item.Properties().rarity(Rarity.EPIC)));
	
	public static final RegistryObject<Item> DYING_PROTECTOR_HEART = 
			REGISTAR.register("dying_protector_heart",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	
	public static final RegistryObject<BlockItem> ABYSSALITE =
			REGISTAR.register("abyssalite",
			() -> new BlockItem(BlockRegistry.ABYSSALITE.get(),
					new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CHISELED_ABYSSALITE =
			REGISTAR.register("chiseled_abyssalite",
			() -> new BlockItem(BlockRegistry.CHISELED_ABYSSALITE.get(),
					new Item.Properties()));
	
	public static final RegistryObject<BlockItem> ABYSSALITE_BRICKS =
			REGISTAR.register("abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.ABYSSALITE_BRICKS.get(),
					new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CRACKED_ABYSSALITE_BRICKS =
			REGISTAR.register("cracked_abyssalite_bricks",
			() -> new BlockItem(BlockRegistry.CRACKED_ABYSSALITE_BRICKS.get(),
					new Item.Properties()));

	public static final RegistryObject<Item> DARKTREE_BOAT =
			REGISTAR.register("darktree_boat",
			() -> new ModBoatItem(false, ModBoatEntity.Type.DARKTREE,
					new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> DARKTREE_CHEST_BOAT =
			REGISTAR.register("darktree_chest_boat",
			() -> new ModBoatItem(true, ModBoatEntity.Type.DARKTREE,
					new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> WHITETREE_BOAT =
			REGISTAR.register("whitetree_boat",
					() -> new ModBoatItem(false, ModBoatEntity.Type.WHITETREE,
							new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> WHITETREE_CHEST_BOAT =
			REGISTAR.register("whitetree_chest_boat",
					() -> new ModBoatItem(true, ModBoatEntity.Type.WHITETREE,
							new Item.Properties().stacksTo(1)));
}
