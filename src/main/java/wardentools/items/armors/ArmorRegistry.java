package wardentools.items.armors;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

public class ArmorRegistry {
	public static final DeferredRegister.Items ARMORS = DeferredRegister.createItems(ModMain.MOD_ID);
	
	public static final DeferredItem<Item> DEEPCRISTAL_HELMET =
			ARMORS.register("deepcristal_helmet",
			() -> new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<Item> DEEPCRISTAL_CHESTPLATE =
			ARMORS.register("deepcristal_chestplate",
			()->new ModArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<Item> DEEPCRISTAL_LEGGINGS =
			ARMORS.register("deepcristal_leggings",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<Item> DEEPCRISTAL_BOOTS =
			ARMORS.register("deepcristal_boots",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));


	public static final DeferredItem<Item> RADIANCE_CRISTAL_HELMET =
			ARMORS.register("radiance_cristal_helmet",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<Item> RADIANCE_CRISTAL_CHESTPLATE =
			ARMORS.register("radiance_cristal_chestplate",
					()->new ModArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<Item> RADIANCE_CRISTAL_LEGGINGS =
			ARMORS.register("radiance_cristal_leggings",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<Item> RADIANCE_CRISTAL_BOOTS =
			ARMORS.register("radiance_cristal_boots",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	
}
