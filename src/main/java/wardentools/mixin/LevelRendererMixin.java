package wardentools.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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

import java.util.List;

import static wardentools.utils.LevelRendererUtils.*;

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
				posestack.pushPose();

				// Rendering top hemisphere (zenith to horizon) with the sky color.
				posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
				float red = ((float) SKYCOLOR.getX()) / 255F;
				float blue = ((float) SKYCOLOR.getY()) / 255F;
				float green = ((float) SKYCOLOR.getZ()) / 255F;
				float alpha = BRIGHTNESS / 255F;
				Matrix4f matrix4f = posestack.last().pose();
				posestack.mulPose(Axis.XP.rotationDegrees(-90.0F));
				BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
				bufferbuilder.addVertex(matrix4f, 0.0F, 90.0F, 0.0F)
						.setColor(0F, 0.1F, 0.1F, alpha);

				// Draw circle from zenith to horizon
				int N = 32;
				for(int j = 0; j <= N; ++j) { // More segments for smoother circle
					float azimuth = (float)j * 6.2831855F / (float)N;
					float x = 90.0F * Mth.cos(azimuth);
					float y = -10.0F; // A bit lower than the Horizon
					float z = 90.0F * Mth.sin(azimuth);
					bufferbuilder.addVertex(matrix4f, x, y, z)
						.setColor(red, green, blue, 0.0F);
				}

				BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
				posestack.popPose();

				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);


				// Stars
				if (alpha > 0.0F) {
					// PRE
					RenderSystem.setShader(GameRenderer::getPositionTexShader);
					RenderSystem.setShaderColor(alpha, alpha, alpha, alpha);
					FogRenderer.setupNoFog();
					RenderSystem.disableCull(); // Deactivate culling necessary for quads
					RenderSystem.depthMask(false);

					RandomSource rand = RandomSource.create(1337L); // Fixed seed

					renderStars(level, tesselator, rand, posestack,
							60, List.of(Axis.XP, Axis.YP),
							new float[]{1.2F, 0.1F}, 1.0F);
					renderStars(level, tesselator, rand, posestack,
							60, List.of(Axis.XP, Axis.YP, Axis.ZP),
							new float[]{-1.1F, 0.1F, 0.3F}, 1.0F);
					renderStars(level, tesselator, rand, posestack,
							120, List.of(Axis.YP, Axis.ZP),
							new float[]{-1.0F, 0.3F}, 0.9F);
					renderStars(level, tesselator, rand, posestack,
							140, List.of(Axis.XP, Axis.ZP),
							new float[]{0.4F, -0.7F}, 0.8F);
					renderStars(level, tesselator, rand, posestack,
							160, List.of(Axis.XP, Axis.YP, Axis.ZP),
							new float[]{0.2F, -0.3F, 0.4F}, 0.7F);
					renderStars(level, tesselator, rand, posestack,
							180, List.of(Axis.XP, Axis.YP, Axis.ZP),
							new float[]{-0.4F, 0.5F, -0.2F}, 0.6F);

					// POST
					RenderSystem.enableCull();
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
