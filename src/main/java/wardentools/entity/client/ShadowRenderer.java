package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ShadowEntity;

import java.util.Objects;

public class ShadowRenderer extends MobRenderer<ShadowEntity, Shadow>{
	private static final ResourceLocation HUMANOID_SHADOW_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/shadow.png");
	private static final ResourceLocation GENERIC_SHADOW_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/generic_shadow.png");

	public ShadowRenderer(EntityRendererProvider.Context context) {
		super(context, new Shadow(context.bakeLayer(Shadow.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ShadowEntity shadow) {
		return shadow.getMimicEntity() == null ? HUMANOID_SHADOW_TEXTURE : GENERIC_SHADOW_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ShadowEntity shadow, float yaw, float partialTick,
					   @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int packedLight) {
		super.render(shadow, yaw, partialTick, stack, buffer, packedLight);
	}

	@Override
	protected float getBob(@NotNull ShadowEntity shadow, float ageInTick) {
		if (shadow.getGetBobFunction() == null) return super.getBob(shadow, ageInTick);
		return shadow.getGetBobFunction().getBob(shadow, ageInTick);
	}

	@Override
	protected void scale(@NotNull ShadowEntity shadow, @NotNull PoseStack poseStack, float v) {
		if (shadow.getScaleFunction() == null) super.scale(shadow, poseStack, v);
		else shadow.getScaleFunction().scale(shadow.getMimicEntity(), poseStack, v);
	}
}
