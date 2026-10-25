package wardentools.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.CustomDamageType;

public class CorruptedEffect extends MobEffect {

    protected CorruptedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.hasEffect(ModEffects.PURIFIED.get()) || entity.tickCount % 20 != 0) {
            super.applyEffectTick(entity, amplifier);
            return;
        }
        Holder<DamageType> corruptedDamageHolder
                = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(CustomDamageType.CORRUPTED_KEY);

        entity.hurt(new DamageSource(corruptedDamageHolder, null, entity, null),
                1 + amplifier );
        super.applyEffectTick(entity, amplifier);
    }
}
