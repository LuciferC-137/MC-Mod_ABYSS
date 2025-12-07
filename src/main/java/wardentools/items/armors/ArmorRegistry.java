package wardentools.items.armors;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

import java.util.List;

public class ArmorRegistry {
	public static final DeferredRegister.Items ARMORS = DeferredRegister.createItems(ModMain.MOD_ID);
	
	public static final DeferredItem<ConfigurableDefenseItem> DEEPCRISTAL_HELMET =
			ARMORS.register("deepcristal_helmet",
			() -> new ConfigurableDefenseItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<ArmorWithEffectItem> DEEPCRISTAL_CHESTPLATE =
			ARMORS.register("deepcristal_chestplate",
			()->new ArmorWithEffectItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<ConfigurableDefenseItem> DEEPCRISTAL_LEGGINGS =
			ARMORS.register("deepcristal_leggings",
			()->new ConfigurableDefenseItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));
	public static final DeferredItem<ConfigurableDefenseItem> DEEPCRISTAL_BOOTS =
			ARMORS.register("deepcristal_boots",
			()->new ConfigurableDefenseItem(ModMaterials.DEEPCRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
					.rarity(Rarity.UNCOMMON)
					.stacksTo(1)
					.fireResistant()));


	public static final DeferredItem<ConfigurableDefenseItem> RADIANCE_CRISTAL_HELMET =
			ARMORS.register("radiance_cristal_helmet",
					()->new ConfigurableDefenseItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.HELMET, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<ArmorWithEffectItem> RADIANCE_CRISTAL_CHESTPLATE =
			ARMORS.register("radiance_cristal_chestplate",
					()->new ArmorWithEffectItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<ConfigurableDefenseItem> RADIANCE_CRISTAL_LEGGINGS =
			ARMORS.register("radiance_cristal_leggings",
					()->new ConfigurableDefenseItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));
	public static final DeferredItem<ConfigurableDefenseItem> RADIANCE_CRISTAL_BOOTS =
			ARMORS.register("radiance_cristal_boots",
					()->new ConfigurableDefenseItem(ModMaterials.RADIANCE_CRISTAL, ArmorItem.Type.BOOTS, new Item.Properties()
							.rarity(Rarity.UNCOMMON)
							.stacksTo(1)
							.fireResistant()));

    /**
     * DO NOT CALL BEFORE REGISTRATION IS COMPLETE.
     * @return List of ConfigurableDefenseItem representing the Deepcristal armor set.
     */
    public static List<ConfigurableDefenseItem> getDeepcristalArmorSet() {
        return List.of(
                DEEPCRISTAL_HELMET.get(),
                DEEPCRISTAL_CHESTPLATE.get(),
                DEEPCRISTAL_LEGGINGS.get(),
                DEEPCRISTAL_BOOTS.get()
        );
    }

    /**
     * DO NOT CALL BEFORE REGISTRATION IS COMPLETE.
     * @return List of ConfigurableDefenseItem representing the Radiance armor set.
     */
    public static List<ConfigurableDefenseItem> getRadianceCristalArmorSet() {
        return List.of(
                RADIANCE_CRISTAL_HELMET.get(),
                RADIANCE_CRISTAL_CHESTPLATE.get(),
                RADIANCE_CRISTAL_LEGGINGS.get(),
                RADIANCE_CRISTAL_BOOTS.get()
        );
    }
	
}
