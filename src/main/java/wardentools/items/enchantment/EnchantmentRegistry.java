package wardentools.items.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import wardentools.ModMain;

public class EnchantmentRegistry {
    public static final ResourceKey<Enchantment> STEALTH = register("stealth");
    
    public static ResourceKey<Enchantment> register(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT,
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }
}
