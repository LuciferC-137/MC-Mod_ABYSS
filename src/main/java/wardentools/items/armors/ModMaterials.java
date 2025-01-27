package wardentools.items.armors;

import java.util.EnumMap;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import wardentools.ModMain;
import wardentools.tags.ModTags;

public class ModMaterials {
	public static final ArmorMaterial DEEPCRISTAL = new ArmorMaterial(1200,
			Util.make(new EnumMap<>(ArmorType.class),
			attribute -> {
				attribute.put(ArmorType.BOOTS, 5);
				attribute.put(ArmorType.LEGGINGS, 7);
				attribute.put(ArmorType.CHESTPLATE, 9);
				attribute.put(ArmorType.HELMET, 5);
				attribute.put(ArmorType.BODY, 11);
			}), 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
			4f, 0.1f, ModTags.Items.DEEPCRISTAL_REPAIRS,
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deepcristal.json"));

	public static final ArmorMaterial RADIANCE_CRISTAL = new ArmorMaterial(1200,
			Util.make(new EnumMap<>(ArmorType.class),
					attribute -> {
						attribute.put(ArmorType.BOOTS, 5);
						attribute.put(ArmorType.LEGGINGS, 7);
						attribute.put(ArmorType.CHESTPLATE, 9);
						attribute.put(ArmorType.HELMET, 5);
						attribute.put(ArmorType.BODY, 11);
					}), 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
			4f, 0.1f, ModTags.Items.RADIANCE_CRISTAL_REPAIRS,
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_cristal"));
}
