package wardentools.items.armors;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ArmorRegistry {
	public static final DeferredRegister<Item> REGISTAR = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);
	
	public static final RegistryObject<Item> DEEPCRISTAL_HELMET =
			REGISTAR.register("deepcristal_helmet",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorType.HELMET, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal_helmet")))
			));
	public static final RegistryObject<Item> DEEPCRISTAL_CHESTPLATE =
			REGISTAR.register("deepcristal_chestplate",
			()->new ModArmorItem(ModMaterials.DEEPCRISTAL, ArmorType.CHESTPLATE, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal_chestplate")))
			));
	public static final RegistryObject<Item> DEEPCRISTAL_LEGGINGS =
			REGISTAR.register("deepcristal_leggings",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorType.LEGGINGS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal_leggings")))
			));
	public static final RegistryObject<Item> DEEPCRISTAL_BOOTS =
			REGISTAR.register("deepcristal_boots",
			()->new ArmorItem(ModMaterials.DEEPCRISTAL, ArmorType.BOOTS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.fireResistant()
					.stacksTo(1)
					.setId(ResourceKey.create(Registries.ITEM,
							ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal_boots")))
			));


	public static final RegistryObject<Item> RADIANCE_CRISTAL_HELMET =
			REGISTAR.register("radiance_cristal_helmet",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorType.HELMET, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal_helmet")))
					));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_CHESTPLATE =
			REGISTAR.register("radiance_cristal_chestplate",
					()->new ModArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorType.CHESTPLATE, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal_chestplate")))
					));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_LEGGINGS =
			REGISTAR.register("radiance_cristal_leggings",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorType.LEGGINGS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal_leggings")))
					));
	public static final RegistryObject<Item> RADIANCE_CRISTAL_BOOTS =
			REGISTAR.register("radiance_cristal_boots",
					()->new ArmorItem(ModMaterials.RADIANCE_CRISTAL, ArmorType.BOOTS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.fireResistant()
							.stacksTo(1)
							.setId(ResourceKey.create(Registries.ITEM,
									ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal_boots")))
					));
	
}
