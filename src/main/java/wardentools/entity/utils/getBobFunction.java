package wardentools.entity.utils;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface getBobFunction<T extends LivingEntity> {
    float getBob(T entity, float tickDelta);
}
