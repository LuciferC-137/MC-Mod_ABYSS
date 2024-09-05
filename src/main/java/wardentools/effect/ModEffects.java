package wardentools.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;

public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModMain.MOD_ID);

    public static final RegistryObject<MobEffect> CORRUPTED = MOB_EFFECTS.register("corrupted",
            () -> new CorruptedEffect(MobEffectCategory.HARMFUL, 0x134d4c));

}
