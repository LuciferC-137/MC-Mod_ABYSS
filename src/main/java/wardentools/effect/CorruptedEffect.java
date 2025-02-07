package wardentools.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
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
    public boolean applyEffectTick(@NotNull ServerLevel level, @NotNull LivingEntity entity, int amplifier) {
        if (level.registryAccess().lookup(Registries.DAMAGE_TYPE).isEmpty()) return true;
        Holder<DamageType> corruptedDamageHolder
                = level.registryAccess().lookup(Registries.DAMAGE_TYPE)
                .get().wrapAsHolder(CustomDamageType.CORRUPTED_KEY.getOrThrow(entity.level()).get());

        entity.hurtServer(level, new DamageSource(corruptedDamageHolder, null, entity, null),
                1 + amplifier );
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration%20==0;
    }
}
