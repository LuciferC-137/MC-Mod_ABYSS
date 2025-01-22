package wardentools.items.enchantment;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class EnchantmentRegistry {
    public static final RegistryObject<Enchantment> STEALTH = register("stealth");
    
    public static RegistryObject<Enchantment> register(String name) {
        return RegistryObject.create(
                ResourceLocation.fromNamespaceAndPath(name, ModMain.MOD_ID),
                EnchantmentGenerator.STEALTH_KEY.registry(), ModMain.MOD_ID);
    }

}
