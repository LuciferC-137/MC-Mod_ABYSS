package wardentools.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;

import java.util.function.Supplier;

public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ModMain.MOD_ID);

    public static final Holder<MobEffect> CORRUPTED = MOB_EFFECTS.register("corrupted",
            () -> new CorruptedEffect(MobEffectCategory.HARMFUL, 0x134d4c));
    public static final Holder<MobEffect> CORRUPTION_VESSEL = MOB_EFFECTS.register("corruption_vessel",
            () -> new CorruptionVesselEffect(MobEffectCategory.BENEFICIAL, 0x134d4c));
    public static final Holder<MobEffect> RADIANCE_BRINGER = MOB_EFFECTS.register("radiance_bringer",
            () -> new RadianceBringerEffect(MobEffectCategory.BENEFICIAL, 0x78f8fa));

}
