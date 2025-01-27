package wardentools.entity.utils;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SetUpAnimFunction<T extends LivingEntityRenderState> {
    void setupAnim(@NotNull T entity);
}
