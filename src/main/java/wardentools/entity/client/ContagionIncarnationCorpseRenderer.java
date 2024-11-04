package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ContagionIncarnationCorpseEntity;
import wardentools.particle.ParticleRegistry;

public class ContagionIncarnationCorpseRenderer
		extends LivingEntityRenderer<ContagionIncarnationCorpseEntity, ContagionIncarnationCorpse> {
	private static final ResourceLocation CONTAGION_INCARNATION_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/contagion_incarnation_corpse.png");
	private static final int PARTICLE_EFFECT_DURATION = 200;

	public ContagionIncarnationCorpseRenderer(EntityRendererProvider.Context context) {
		super(context, new ContagionIncarnationCorpse(context.bakeLayer(
				ContagionIncarnationCorpse.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ContagionIncarnationCorpseEntity pEntity) {
		return CONTAGION_INCARNATION_TEXTURE;
	}

	@Override
	public void render(@NotNull ContagionIncarnationCorpseEntity entity, float a,
					   float b, @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource bufferSource, int i) {
		super.render(entity, a, b, poseStack, bufferSource, i);
		int particleCount = (PARTICLE_EFFECT_DURATION - entity.tickCount) / 10;
		if (particleCount > 0) {
			renderParticles(entity, particleCount);
		} else if (entity.tickCount <= 3 * PARTICLE_EFFECT_DURATION && entity.tickCount % 10 == 0) {
			renderParticles(entity, 1);
		}
	}

	private void renderParticles(ContagionIncarnationCorpseEntity entity, int particleCountMax) {
		double minX = entity.getBoundingBox().minX;
		double minY = entity.getBoundingBox().minY;
		double minZ = entity.getBoundingBox().minZ - 2d;
		double maxX = entity.getBoundingBox().maxX;
		double maxY = entity.getBoundingBox().maxY;
		double maxZ = entity.getBoundingBox().maxZ + 1d;
		int particleCount = entity.getRandom()
				.nextInt(0, particleCountMax);
		for (int j = 0; j < particleCount; j++) {
			double x = minX + (maxX - minX) * entity.getRandom().nextDouble();
			double y = minY + (maxY - minY) * entity.getRandom().nextDouble();
			double z = minZ + (maxZ - minZ) * entity.getRandom().nextDouble();
			entity.level().addParticle(ParticleRegistry.CORRUPTION.get(), x, y, z,
					0, -0.1, 0);
		}
	}

	@Override
	protected void scale(@NotNull ContagionIncarnationCorpseEntity entity, @NotNull PoseStack stack, float v) {
		stack.scale(2f, 2f, 2f);
	}

	@Override
	protected boolean shouldShowName(@NotNull ContagionIncarnationCorpseEntity entity) {
		return false;
	}
}
