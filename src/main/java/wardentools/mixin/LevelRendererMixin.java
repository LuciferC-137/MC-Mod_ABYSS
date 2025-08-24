package wardentools.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import wardentools.ModMain;
import wardentools.weather.AbyssWeatherEvent;
import wardentools.weather.AbyssWeatherManager;
import wardentools.worldgen.dimension.ModDimensions;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Unique private static final ResourceLocation STAR_0 = ResourceLocation
			.fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_0.png");
	@Unique private static final Vec3i skyColor = new Vec3i(0, 65, 75);

	@Shadow private ClientLevel level;
	@Shadow @Nullable private VertexBuffer starBuffer;
	
	@Inject(method = "renderSky", at = @At("HEAD"))
	private void onRenderSky(Matrix4f pose, Matrix4f matrix,
			float time, Camera cam, boolean bool, Runnable runnable, CallbackInfo ci) {
        if (cam.getEntity().level().dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
        int BRIGHTNESS = (int)(230f * AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
						/ AbyssWeatherManager.MAX_FOG_DISTANCE);
		LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
		if (level == null) return;
		if (level.effects().renderSky(level, levelRenderer.getTicks(),
				time, cam, pose, bool, runnable)) {
			return;
		}
		time = 0F;
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
				float red = ((float)skyColor.getX()) / 255F;
				float blue = ((float)skyColor.getY()) / 255F;
				float green = ((float)skyColor.getZ()) / 255F;
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
				posestack.pushPose();
				float f11 = 1.0F - this.level.getRainLevel(time);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
				posestack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(-90.0F));
				posestack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(this.level.getTimeOfDay(time) * 360.0F));

				RenderSystem.setShader(GameRenderer::getPositionTexShader);

				float starBrightness = 1.0F;
				if (starBrightness > 0.0F) {
					RenderSystem.setShaderColor(starBrightness, starBrightness, starBrightness, starBrightness);
					FogRenderer.setupNoFog();
					this.starBuffer.bind();
					this.starBuffer.drawWithShader(posestack.last().pose(), matrix, GameRenderer.getPositionShader());
					VertexBuffer.unbind();
					runnable.run();
				}

				RenderSystem.defaultBlendFunc();
				posestack.popPose();

				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.depthMask(true);
	    	}
	    }
    }
}
