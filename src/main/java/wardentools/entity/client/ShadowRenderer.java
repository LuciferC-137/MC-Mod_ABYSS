package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ShadowEntity;
import wardentools.particle.ParticleRegistry;

public class ShadowRenderer extends MobRenderer<ShadowEntity, Shadow>{
	private static final ResourceLocation HUMANOID_SHADOW_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/shadow.png");
	private static final ResourceLocation GENERIC_SHADOW_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/generic_shadow.png");
	private static final float particleSpawnRadius = 2f;

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
		if (shadow.isStasis()) {
			if (shadow.tickCount%10 == shadow.getRandom().nextInt(10)) {
				Vec3 center = shadow.getPosition(1f).add(0f, 1f, 0f);
				float x =  (shadow.getRandom().nextFloat() - 0.5f) * 1.2f
						*  (float)shadow.getBoundingBox().getXsize();
				float y = (shadow.getRandom().nextFloat() - 0.5f) * 1.2f
						* (float)shadow.getBoundingBox().getYsize();
				float z = (shadow.getRandom().nextFloat() - 0.5f) * 1.2f
						* (float)shadow.getBoundingBox().getZsize();
				shadow.level().addParticle(ParticleRegistry.CORRUPTION.get(),
						(float)center.x + x,
						(float)center.y + y,
						(float)center.z + z,
						0, -0.2, 0);
			}
		} else {
			if (shadow.tickCount%10 == shadow.getRandom().nextInt(10)){
				Vec3 center = shadow.getPosition(1f).add(0f, 1f, 0f);
				float x =  (shadow.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float y = (shadow.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float z = (shadow.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float norm = Mth.sqrt(x*x + y*y + z*z) / 0.2f;
				shadow.level().addParticle(ParticleRegistry.CORRUPTION.get(),
						(float)center.x + x,
						(float)center.y + y,
						(float)center.z + z,
						-x / norm,
						-y / norm,
						-z / norm);
			}
		}
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
