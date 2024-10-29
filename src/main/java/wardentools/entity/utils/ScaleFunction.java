package wardentools.entity.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.ShadowEntity;

@FunctionalInterface
public interface ScaleFunction<T extends LivingEntity> {
    void scale(@NotNull T entity, @NotNull PoseStack poseStack, float v);
}
