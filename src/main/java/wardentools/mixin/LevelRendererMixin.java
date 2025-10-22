package wardentools.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.material.FogType;
import wardentools.weather.AbyssWeatherEvent;
import wardentools.weather.AbyssWeatherManager;
import wardentools.worldgen.dimension.ModDimensions;

import static wardentools.client.rendering.LevelRendererUtils.*;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Shadow private ClientLevel level;
	
	@Inject(method = "renderSky", at = @At("HEAD"))
	private void onRenderSky(Matrix4f pose, Matrix4f matrix,
			float time, Camera cam, boolean bool, Runnable runnable, CallbackInfo ci) {
        if (cam.getEntity().level().dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
        int BRIGHTNESS = (int)(230f * AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
						/ AbyssWeatherManager.MAX_FOG_DISTANCE);
		LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
		if (level == null) return;
		if (level.effects().renderSky(level, levelRenderer.getTicks(),
				time, cam, pose, bool, runnable)) {
			return;
		}
		runnable.run();
	    FogType fogtype = cam.getFluidInCamera();
	    if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
	    	if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				PoseStack posestack = new PoseStack();
				posestack.mulPose(pose);
				Tesselator tesselator = Tesselator.getInstance();
				VertexBuffer.unbind();
				RenderSystem.enableBlend();
				RenderSystem.setShader(GameRenderer::getPositionColorShader);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

				renderSkyBackGround(posestack, tesselator, BRIGHTNESS);

				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

				float alpha = BRIGHTNESS / 255F;

				if (alpha > 0.0F) {
					renderMultipleStarLayers(level, alpha, posestack, tesselator);
				}

				// POST
				RenderSystem.defaultBlendFunc();
				posestack.popPose();
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.depthMask(true);
	    	}
	    }
    }

}
