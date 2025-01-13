package wardentools.items.enchantment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class StealthEnchantment extends Enchantment {
    public StealthEnchantment(EquipmentSlot... slots) {
        super(Enchantment.definition(ItemTags.ARMOR_ENCHANTABLE,
                20, 1, // weight, maxLevel
                Enchantment.dynamicCost(1, 11), //min cost
                Enchantment.dynamicCost(12, 11), //max cost
                1, // anvil cost
                slots));
    }

}
