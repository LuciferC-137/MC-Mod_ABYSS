package wardentools.items.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class EnchantmentRegistry {
    public static final RegistryObject<Enchantment> STEALTH = register("stealth");
    
    public static RegistryObject<Enchantment> register(String name) {
        return RegistryObject.create(
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name),
                Registries.ENCHANTMENT.location(), ModMain.MOD_ID);
    }

}
