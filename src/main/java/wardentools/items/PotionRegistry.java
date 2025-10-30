package wardentools.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.effect.ModEffects;

import java.util.function.Supplier;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(BuiltInRegistries.POTION, ModMain.MOD_ID);

    public static final Supplier<Potion> PURIFIED_POTION
            = POTIONS.register("purified_potion",
            () -> new Potion(new MobEffectInstance(
                    ModEffects.PURIFIED, 2400, 0)));
}
