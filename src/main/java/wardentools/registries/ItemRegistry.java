package wardentools.registries;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.items.ModFoods;
import wardentools.items.WardenHeartItem;

public class ItemRegistry {
	public static final DeferredRegister<Item> REGISTAR = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
	
	
	public static final RegistryObject<Item> DEEPINGOTS =
			REGISTAR.register("deepingots",
			() -> new Item(new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.EPIC)
					));
	
	public static final RegistryObject<BlockItem> DEEPBLOCK =
			REGISTAR.register("deepblock",
			() -> new BlockItem(BlockRegistry.DEEPBLOCK.get(), new Item.Properties()
					.rarity(Rarity.EPIC)
					));
	
	public static final RegistryObject<BlockItem> DEEPCRISTAL =
			REGISTAR.register("deepcristal",
			() -> new BlockItem(BlockRegistry.DEEP_CRISTAL.get(), new Item.Properties()
					.stacksTo(64)
					.rarity(Rarity.EPIC)
					));
	public static final RegistryObject<Item> WARDEN_HEART =
			REGISTAR.register("warden_heart",
			() -> new WardenHeartItem(new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.EPIC)));
	
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
	
	public static final RegistryObject<Item> ABYSS_PORTAL = 
			REGISTAR.register("abyss_portal",
			() -> new BlockItem(BlockRegistry.ABYSS_PORTAL.get(), new Item.Properties()));
	
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
}
