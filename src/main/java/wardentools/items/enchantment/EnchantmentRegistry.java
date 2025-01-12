package wardentools.items.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ModMain.MOD_ID);

    public static final RegistryObject<Enchantment> STEALTH
            = ENCHANTMENTS.register("stealth",
            () -> new StealthEnchantment(Enchantment.Rarity.RARE,
                    EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD));

}
