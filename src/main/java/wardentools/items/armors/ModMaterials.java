package wardentools.items.armors;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import wardentools.AbyssConfig;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

import java.util.EnumMap;
import java.util.function.Supplier;

public class ModMaterials {

	public static final ArmorMaterial DEEPCRISTAL =
			createMaterial("deepcristal", ItemRegistry.DEEPINGOTS);

	public static final ArmorMaterial RADIANCE_CRISTAL =
			createMaterial("radiance_cristal", ItemRegistry.RADIANCE_INGOTS);

	private static ArmorMaterial createMaterial(
			String name,
			Supplier<Item> ingredient
	) {
		return new ModArmorMaterial(ModMain.MOD_ID + ":" + name, 15, createProtectionMap(5, 7, 9, 5),
				15, SoundEvents.ARMOR_EQUIP_NETHERITE,
				() -> Ingredient.of(ingredient.get()), 4.0F, 0.1F);
	}

	public static int getAbyssArmorProtection(ArmorItem.Type type) {
		return switch (type) {
			case HELMET, BOOTS -> (int) ((float) AbyssConfig.COMMON.ABYSS_ARMOR_PROTECTION.get() * 5F / 9F);
			case LEGGINGS -> (int) ((float) AbyssConfig.COMMON.ABYSS_ARMOR_PROTECTION.get() * 7F / 9F);
			case CHESTPLATE -> (int) ((float) AbyssConfig.COMMON.ABYSS_ARMOR_PROTECTION.get() * 11F / 9F);
			default -> AbyssConfig.COMMON.ABYSS_ARMOR_PROTECTION.get();
		};
	}

	private static EnumMap<ArmorItem.Type, Integer> createProtectionMap(int boots, int leggings,
																		int chestplate, int helmet) {
		EnumMap<ArmorItem.Type, Integer> protection = new EnumMap<>(ArmorItem.Type.class);

		protection.put(ArmorItem.Type.BOOTS, boots);
		protection.put(ArmorItem.Type.LEGGINGS, leggings);
		protection.put(ArmorItem.Type.CHESTPLATE, chestplate);
		protection.put(ArmorItem.Type.HELMET, helmet);

		return protection;
	}

	public static void refreshArmorProtection() {
		updateArmor(ArmorRegistry.DEEPCRISTAL_BOOTS.get(), ArmorItem.Type.BOOTS);
		updateArmor(ArmorRegistry.DEEPCRISTAL_LEGGINGS.get(), ArmorItem.Type.LEGGINGS);
		updateArmor(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get(), ArmorItem.Type.CHESTPLATE);
		updateArmor(ArmorRegistry.DEEPCRISTAL_HELMET.get(), ArmorItem.Type.HELMET);
	}

	private static void updateArmor(
			Item item,
			ArmorItem.Type type
	) {
		if (item instanceof ConfigurableDefenseItem armor) {
			armor.updateDefense(getAbyssArmorProtection(type));
		}
	}

}

