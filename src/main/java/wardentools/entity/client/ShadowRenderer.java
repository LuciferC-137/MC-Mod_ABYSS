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
		return shadow.getDeadEntity() == null ? HUMANOID_SHADOW_TEXTURE : GENERIC_SHADOW_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ShadowEntity shadow, float yaw, float partialTick,
					   @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int p_115313_) {
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre<ShadowEntity, Shadow>(shadow, this, partialTick, stack, buffer, p_115313_))) return;
		stack.pushPose();
		this.model.attackTime = this.getAttackAnim(shadow, partialTick);
		boolean shouldSit = shadow.isPassenger() && (shadow.getVehicle() != null && shadow.getVehicle().shouldRiderSit());
		this.model.riding = shouldSit;
		this.model.young = shadow.isBaby();
		float f = Mth.rotLerp(partialTick, shadow.yBodyRotO, shadow.yBodyRot);
		float f1 = Mth.rotLerp(partialTick, shadow.yHeadRotO, shadow.yHeadRot);
		float f2 = f1 - f;
		if (shouldSit && shadow.getVehicle() instanceof LivingEntity) {
			Entity $$14 = shadow.getVehicle();
			if ($$14 instanceof LivingEntity livingentity) {
                f = Mth.rotLerp(partialTick, livingentity.yBodyRotO, livingentity.yBodyRot);
				f2 = f1 - f;
				float f6 = Mth.wrapDegrees(f2);
				if (f6 < -85.0F) f6 = -85.0F;
				if (f6 >= 85.0F) f6 = 85.0F;
				f = f1 - f6;
				if (f6 * f6 > 2500.0F) f += f6 * 0.2F;
				f2 = f1 - f;
			}
		}
		float f5 = Mth.lerp(partialTick, shadow.xRotO, shadow.getXRot());
		if (isEntityUpsideDown(shadow)) {
			f5 *= -1.0F;
			f2 *= -1.0F;
		}
		if (shadow.hasPose(Pose.SLEEPING)) {
			Direction direction = shadow.getBedOrientation();
			if (direction != null) {
				float f3 = shadow.getEyeHeight(Pose.STANDING) - 0.1F;
				stack.translate((float)(-direction.getStepX()) * f3, 0.0F, (float)(-direction.getStepZ()) * f3);
			}
		}

		float f7 = this.getBob(shadow, partialTick);
		this.setupRotations(shadow, stack, f7, f, partialTick);
		stack.scale(-1.0F, -1.0F, 1.0F);
		this.scale(shadow, stack, partialTick);
		stack.translate(0.0F, -1.501F, 0.0F);
		float f8 = 0.0F;
		float f4 = 0.0F;
		if (!shouldSit && shadow.isAlive()) {
			f8 = shadow.walkAnimation.speed(partialTick);
			f4 = shadow.walkAnimation.position(partialTick);
			if (shadow.isBaby()) f4 *= 3.0F;
			if (f8 > 1.0F) f8 = 1.0F;
		}
		this.model.prepareMobModel(shadow, f4, f8, partialTick);
		this.model.setupAnim(shadow, f4, f8, f7, f2, f5);
		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = true;
        if (!flag) shadow.isInvisibleTo(Objects.requireNonNull(minecraft.player));
        boolean flag1 = false;
		boolean flag2 = minecraft.shouldEntityAppearGlowing(shadow);
		RenderType rendertype = this.getRenderType(shadow, flag, flag1, flag2);
		if (rendertype != null) {
			VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
			int i = getOverlayCoords(shadow, this.getWhiteOverlayProgress(shadow, partialTick));
			this.model.renderToBuffer(stack, vertexconsumer, p_115313_, i, 1.0F, 1.0F, 1.0F, 1.0F);
		}
		if (!shadow.isSpectator()) {
			for(RenderLayer<ShadowEntity, Shadow> renderlayer : this.layers) {
				renderlayer.render(stack, buffer, p_115313_, shadow, f4, f8, partialTick, f7, f2, f5);
			}
		}
		stack.popPose();
		super.render(shadow, yaw, partialTick, stack, buffer, p_115313_);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post<ShadowEntity, Shadow>(shadow, this, partialTick, stack, buffer, p_115313_));
	}

	@Override
	protected float getBob(@NotNull ShadowEntity shadow, float ageInTick) {
		if (shadow.getGetBobFunction() == null) return super.getBob(shadow, ageInTick);
		return shadow.getGetBobFunction().getBob(shadow, ageInTick);
	}
}
