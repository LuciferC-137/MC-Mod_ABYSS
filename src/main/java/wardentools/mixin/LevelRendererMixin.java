package wardentools.mixin;

import com.mojang.blaze3d.vertex.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import wardentools.ModMain;
import wardentools.weather.AbyssWeatherEventServer;
import wardentools.weather.AbyssWeatherManager;
import wardentools.worldgen.dimension.ModDimensions;

@OnlyIn(Dist.CLIENT)
@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Unique private static final ResourceLocation ABYSS_SKY_LOCATION
		= ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/abyss_skyb.png");
	
	@Inject(method = "renderSky", at = @At("HEAD"))
	private void onRenderSky(Matrix4f pose, Matrix4f matrix,
			float partialTick, Camera cam, boolean bool, Runnable runnable, CallbackInfo ci) {
        if (cam.getEntity().level().dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
        int BRIGHTNESS = (int)(230f
				* (AbyssWeatherEventServer.WEATHER_MANAGER.getFogDistance() / AbyssWeatherManager.MAX_FOG_DISTANCE));
		int FOG_COLOR_OVERLAY = (int)((float)BRIGHTNESS * ((float)255 / (float)230));
		LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
		if (level == null) return;
		if (level.effects().renderSky(level, levelRenderer.getTicks(),
				partialTick, matrix, cam, pose, bool, runnable)) {
			return;
		}
		runnable.run();
	    FogType fogtype = cam.getFluidInCamera();
	    if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
	    	if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				RenderSystem.enableBlend();
				RenderSystem.depthMask(false);
				RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
				RenderSystem.setShaderTexture(0, ABYSS_SKY_LOCATION);
				Tesselator tesselator = Tesselator.getInstance();
				PoseStack posestack = new PoseStack();
				posestack.mulPose(pose);
				for(int i = 0; i < 6; ++i) {
					posestack.pushPose();
					if (i == 1) posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
					if (i == 2) posestack.mulPose(Axis.XP.rotationDegrees(-90.0F));
					if (i == 3) posestack.mulPose(Axis.XP.rotationDegrees(180.0F));
					if (i == 4) posestack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					if (i == 5) posestack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
					Matrix4f matrix4f = posestack.last().pose();
					BufferBuilder bufferbuilder = tesselator
							.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
					bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, -100.0F)
							.setUv(0.0F, 0.0F).setColor(BRIGHTNESS, BRIGHTNESS, BRIGHTNESS, FOG_COLOR_OVERLAY);
					bufferbuilder.addVertex(matrix4f, -100.0F, -100.0F, 100.0F)
							.setUv(0.0F, 1.0F).setColor(BRIGHTNESS, BRIGHTNESS, BRIGHTNESS, FOG_COLOR_OVERLAY);
					bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, 100.0F)
							.setUv(1.0F, 1.0F).setColor(BRIGHTNESS, BRIGHTNESS, BRIGHTNESS, FOG_COLOR_OVERLAY);
					bufferbuilder.addVertex(matrix4f, 100.0F, -100.0F, -100.0F)
							.setUv(1.0F, 0.0F).setColor(BRIGHTNESS, BRIGHTNESS, BRIGHTNESS, FOG_COLOR_OVERLAY);
					BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
					posestack.popPose();
				}
				RenderSystem.depthMask(true);
				RenderSystem.disableBlend();
	    	}
	    }
    }
}
