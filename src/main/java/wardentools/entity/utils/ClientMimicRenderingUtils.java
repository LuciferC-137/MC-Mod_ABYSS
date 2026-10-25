package wardentools.entity.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

import java.lang.reflect.Method;

@OnlyIn(Dist.CLIENT)
public class ClientMimicRenderingUtils {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static RenderToBufferFunction getRenderToBufferFunction(LivingEntity mimic) {
        EntityRenderer<? super LivingEntity> renderer =
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(mimic);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            return (poseStack, vertexConsumer, i, j, r, g, b, a) ->
                    livingRenderer.getModel().renderToBuffer(poseStack, vertexConsumer, i, j, r, g, b, a);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends LivingEntity> SetUpAnimFunction<T> getSetUpAnimFunction(T mimic) {
        EntityRenderer<? super T> renderer =
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(mimic);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            return ((LivingEntityRenderer<T, ?>) livingRenderer).getModel()::setupAnim;
        }
        return null;
    }

    public static <T extends LivingEntity> getBobFunction<T> getGetBobFunction(T mimic) {
        EntityRenderer<? super T> renderer =
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(mimic);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            try {
                Method getBobMethod = LivingEntityRenderer.class
                        .getDeclaredMethod("getBob", LivingEntity.class, float.class);
                getBobMethod.setAccessible(true);
                return (getBobFunction<T>) (entity, partialTicks) -> {
                    if (mimic instanceof Chicken || mimic instanceof Parrot) return 0.0f;
                    try {
                        return (float) getBobMethod.invoke(livingRenderer, entity, partialTicks);
                    } catch (Exception e) {
                        LOGGER.error("DITA Mimic Entity Error: could not invoke getBob method", e);
                        return 0.0f;
                    }
                };
            } catch (NoSuchMethodException e) {
                LOGGER.error("DITA Mimic Entity Error: could not find getBob method", e);
            }
        }
        return null;
    }

    public static <T extends LivingEntity> ScaleFunction<T> getScaleFunction(T mimic) {
        EntityRenderer<? super T> renderer =
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(mimic);
        if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
            try {
                Method scaleMethod = LivingEntityRenderer.class
                        .getDeclaredMethod("scale", LivingEntity.class, PoseStack.class, float.class);
                scaleMethod.setAccessible(true);
                return (ScaleFunction<T>) (entity, poseStack, v) -> {
                    try {
                        scaleMethod.invoke(livingRenderer, entity, poseStack, v);
                    } catch (Exception e) {
                        LOGGER.error("DITA Mimic Entity Error: could not invoke scaleMethod", e);
                    }
                };
            } catch (NoSuchMethodException e) {
                LOGGER.error("DITA Mimic Entity Error: could not find scale method", e);
            }
        }
        return null;
    }
}
