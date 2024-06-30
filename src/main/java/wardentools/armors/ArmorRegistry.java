package wardentools.armors;

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
			()->new ArmorItem(DeepCristalMaterial.DEEPCRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
					.rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DEEPCRISTAL_CHESTPLATE =
			REGISTAR.register("deepcristal_chestplate",
			()->new ArmorItem(DeepCristalMaterial.DEEPCRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
					.rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DEEPCRISTAL_LEGGINGS =
			REGISTAR.register("deepcristal_leggings",
			()->new ArmorItem(DeepCristalMaterial.DEEPCRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
					.rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> DEEPCRISTAL_BOOTS =
			REGISTAR.register("deepcristal_boots",
			()->new ArmorItem(DeepCristalMaterial.DEEPCRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
					.rarity(Rarity.EPIC)));
	
}
