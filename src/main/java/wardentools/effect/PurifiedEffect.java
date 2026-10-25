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
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            if (entity.hasEffect(ModEffects.CORRUPTED.get())) {
                entity.removeEffect(ModEffects.CORRUPTED.get());
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

}
