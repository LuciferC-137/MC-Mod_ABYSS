package wardentools.mixin;

import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.*;
import net.minecraft.util.ARGB;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import wardentools.ModMain;
import wardentools.weather.AbyssWeatherEvent;
import wardentools.weather.AbyssWeatherManager;
import wardentools.worldgen.dimension.ModDimensions;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
	@Unique private static final ResourceLocation ABYSS_SKY_LOCATION
		= ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/abyss_skyb.png");
	@Unique private static final RenderStateShard.OutputStateShard MAIN_TARGET
			= new RenderStateShard.OutputStateShard("main_target", () -> {
		Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
		}, () -> {});

	@Inject(method = "renderLevel", at = @At("TAIL"))
	private void onRenderLevel(GraphicsResourceAllocator allocator, DeltaTracker tracker,
							   boolean b, Camera cam, GameRenderer renderer,
							   LightTexture lightTexture, Matrix4f matrix1, Matrix4f matrix2,
							   CallbackInfo ci) {
		if (cam.getEntity().level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
			int BRIGHTNESS =
					(int)(230f * (AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
					/ AbyssWeatherManager.MAX_FOG_DISTANCE));
			int white = ARGB.white(1f);
			int FOG_INTENSITY_OVERLAY = ARGB.scaleRGB(white, (int)((float)BRIGHTNESS
					* ((float)255 / (float)230)));
			Minecraft mc = Minecraft.getInstance();
			ClientLevel level = mc.level;
			if (level == null) return;
			FogType fogtype = cam.getFluidInCamera();
			if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
				MAIN_TARGET.setupRenderState();
				PoseStack poseStack = new PoseStack();
				poseStack.mulPose(matrix1);
				RenderSystem.enableBlend();
				RenderSystem.depthMask(false);
				RenderSystem.setShader(CoreShaders.POSITION_TEX_COLOR);
				RenderSystem.setShaderTexture(0, ABYSS_SKY_LOCATION);
				Tesselator tesselator = Tesselator.getInstance();
				for (int i = 0; i < 6; ++i) {
					poseStack.pushPose();
					if (i == 1) poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
					if (i == 2) poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
					if (i == 3) poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
					if (i == 4) poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
					if (i == 5) poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
					Matrix4f matrix4f = poseStack.last().pose();
					BufferBuilder builder = tesselator.begin(VertexFormat.Mode.QUADS,
							DefaultVertexFormat.POSITION_TEX_COLOR);
					builder.addVertex(matrix4f, -100.0F, -100.0F, -100.0F)
							.setUv(0.0F, 0.0F).setColor(FOG_INTENSITY_OVERLAY);
					builder.addVertex(matrix4f, -100.0F, -100.0F, 100.0F)
							.setUv(0.0F, 1.0F).setColor(FOG_INTENSITY_OVERLAY);
					builder.addVertex(matrix4f, 100.0F, -100.0F, 100.0F)
							.setUv(1.0F, 1.0F).setColor(FOG_INTENSITY_OVERLAY);
					builder.addVertex(matrix4f, 100.0F, -100.0F, -100.0F)
							.setUv(1.0F, 0.0F).setColor(FOG_INTENSITY_OVERLAY);
					BufferUploader.drawWithShader(builder.buildOrThrow());
					poseStack.popPose();
				}
				RenderSystem.depthMask(true);
				RenderSystem.disableBlend();
			}
		}
	}
}
