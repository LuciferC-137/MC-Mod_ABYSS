package wardentools.items.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import wardentools.ModMain;

public class EnchantmentGenerator {

    public static final ResourceKey<Enchantment> STEALTH_KEY = key("stealth");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, STEALTH_KEY, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                5, // weight
                1,           // maxLevel
                Enchantment.dynamicCost(1, 11), //min cost
                Enchantment.dynamicCost(10, 11), //max cost
                2, // anvil cost
                EquipmentSlotGroup.ARMOR)
        ));
    }


    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT,
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

}
