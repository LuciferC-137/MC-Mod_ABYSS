package wardentools.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, ModMain.MOD_ID);

    public static final RegistryObject<Potion> PURIFIED_POTION
            = POTIONS.register("purified_potion",
            () -> new Potion(new MobEffectInstance(
                    ModEffects.PURIFIED.getHolder().get(), 2400, 0)));
}
