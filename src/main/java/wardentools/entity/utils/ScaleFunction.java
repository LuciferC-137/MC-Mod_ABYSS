package wardentools.entity.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.custom.ShadowEntity;

@FunctionalInterface
public interface ScaleFunction<T extends LivingEntityRenderState> {
    void scale(@NotNull T state, @NotNull PoseStack poseStack);
}
