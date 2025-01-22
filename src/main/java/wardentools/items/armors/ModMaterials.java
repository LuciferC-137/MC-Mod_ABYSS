package wardentools.items.armors;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import wardentools.ModMain;
import wardentools.items.ItemRegistry;

public class ModMaterials {
	public static final Holder<ArmorMaterial> DEEPCRISTAL = ModMaterials
			.register("deepcristal", new EnumMap<>(ArmorItem.Type.class) {
				{
					this.put(ArmorItem.Type.HELMET, 13);
					this.put(ArmorItem.Type.CHESTPLATE, 15);
					this.put(ArmorItem.Type.LEGGINGS, 16);
					this.put(ArmorItem.Type.BOOTS, 11);
				}
			}, 37, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F,
					() -> {
						return Ingredient.of(ItemRegistry.DEEPINGOTS.get());
					}, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/models/armor/deepcristal_layer_1.png"))));


	public static final Holder<ArmorMaterial> RADIANCE_CRISTAL = ModMaterials
			.register("radiance_cristal", new EnumMap<>(ArmorItem.Type.class) {
				{
					this.put(ArmorItem.Type.HELMET, 13);
					this.put(ArmorItem.Type.CHESTPLATE, 15);
					this.put(ArmorItem.Type.LEGGINGS, 16);
					this.put(ArmorItem.Type.BOOTS, 11);
				}
			}, 37, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.0F,
					() -> {
						return Ingredient.of(ItemRegistry.RADIANCE_INGOTS.get());
					}, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/models/armor/radiance_cristal_layer_1.png"))));
    
    ModMaterials() {
    }

	private static Holder<ArmorMaterial> register(String name,
												  EnumMap<ArmorItem.Type, Integer> durabilities,
												  int protection, Holder<SoundEvent> sound,
												  float toughness, float knockbackResistance,
												  Supplier<Ingredient> repairIngredient,
												  List<ArmorMaterial.Layer> layerList) {
		EnumMap<ArmorItem.Type, Integer> $$8 = new EnumMap<>(ArmorItem.Type.class);
		ArmorItem.Type[] var9 = ArmorItem.Type.values();
        for (ArmorItem.Type $$9 : var9) {
            $$8.put($$9, (Integer) durabilities.get($$9));
        }
		return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.withDefaultNamespace(name), new ArmorMaterial($$8, protection, sound, repairIngredient, layerList, toughness, knockbackResistance));
	}
    
	
}
