package wardentools.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class PurifiedEffect extends MobEffect {

    protected PurifiedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            if (ModEffects.CORRUPTED.getHolder().isPresent()) {
                if (entity.hasEffect(ModEffects.CORRUPTED.getHolder().get())) {
                    entity.removeEffect(ModEffects.CORRUPTED.getHolder().get());
                }
            }
        }
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration%20==0;
    }
}
