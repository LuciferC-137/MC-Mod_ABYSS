package wardentools.entity.utils;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface getBobFunction<T extends LivingEntity> {
    float getBob(T p_115305_, float p_115306_);
}
