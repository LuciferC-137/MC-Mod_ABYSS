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
    public static final RegistryObject<MobEffect> CORRUPTION_VESSEL = MOB_EFFECTS.register("corruption_vessel",
            () -> new CorruptionVesselEffect(MobEffectCategory.BENEFICIAL, 0x134d4c));
    public static final RegistryObject<MobEffect> RADIANCE_BRINGER = MOB_EFFECTS.register("radiance_bringer",
            () -> new RadianceBringerEffect(MobEffectCategory.BENEFICIAL, 0x78f8fa));
    public static final RegistryObject<MobEffect> PURIFIED = MOB_EFFECTS.register("purified",
            () -> new PurifiedEffect(MobEffectCategory.BENEFICIAL, 0x8ae1eb));

}
