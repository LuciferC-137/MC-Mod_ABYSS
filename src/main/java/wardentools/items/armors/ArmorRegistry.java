package wardentools.items.armors;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ArmorRegistry {
	public static final DeferredRegister<Item> REGISTAR = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
	
	public static final RegistryObject<Item> DEEPCRISTAL_HELMET =
			REGISTAR.register("deepcristal_helmet",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final RegistryObject<Item> DEEPCRISTAL_CHESTPLATE =
			REGISTAR.register("deepcristal_chestplate",
			()->new ModArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final RegistryObject<Item> DEEPCRISTAL_LEGGINGS =
			REGISTAR.register("deepcristal_leggings",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final RegistryObject<Item> DEEPCRISTAL_BOOTS =
			REGISTAR.register("deepcristal_boots",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));


	public static final RegistryObject<Item> RADIANCE_CRISTAL_HELMET =
			REGISTAR.register("radiance_cristal_helmet",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_CHESTPLATE =
			REGISTAR.register("radiance_cristal_chestplate",
					()->new ModArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_LEGGINGS =
			REGISTAR.register("radiance_cristal_leggings",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_BOOTS =
			REGISTAR.register("radiance_cristal_boots",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	
}
