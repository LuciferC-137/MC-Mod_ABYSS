package wardentools.entity.utils;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SetUpAnimFunction<T extends LivingEntity> {
    void setupAnim(@NotNull T entity, float limbSwing,
                   float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);
}
