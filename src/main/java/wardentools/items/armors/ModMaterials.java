package wardentools.items.armors;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

public class ModMaterials {
	public static final Holder<ArmorMaterial> DEEPCRISTAL = register("deepcristal",
			Util.make(new EnumMap<>(ArmorItem.Type.class),
					attribute -> {
						attribute.put(ArmorItem.Type.BOOTS, 5);
						attribute.put(ArmorItem.Type.LEGGINGS, 7);
						attribute.put(ArmorItem.Type.CHESTPLATE, 9);
						attribute.put(ArmorItem.Type.HELMET, 5);
						attribute.put(ArmorItem.Type.BODY, 11);
					}), 15, 4f, 0.1f, ItemRegistry.DEEPINGOTS::get);

	public static final Holder<ArmorMaterial> RADIANCE_CRISTAL = register("radiance_cristal",
			Util.make(new EnumMap<>(ArmorItem.Type.class),
					attribute -> {
						attribute.put(ArmorItem.Type.BOOTS, 5);
						attribute.put(ArmorItem.Type.LEGGINGS, 7);
						attribute.put(ArmorItem.Type.CHESTPLATE, 9);
						attribute.put(ArmorItem.Type.HELMET, 5);
						attribute.put(ArmorItem.Type.BODY, 11);
					}), 15, 4f, 0.1f, ItemRegistry.RADIANCE_INGOTS);

	ModMaterials() {
	}

	private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
												  int enchantability, float toughness, float knockbackResistance,
												  Supplier<Item> ingredientItem) {
		ResourceLocation location = new ResourceLocation(ModMain.MOD_ID, name);
		Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
		Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
		List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

		EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
		for (ArmorItem.Type type : ArmorItem.Type.values()) {
			typeMap.put(type, typeProtection.get(type));
		}

		return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
				new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
	}


}
