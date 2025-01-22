package wardentools.entity.client;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.particle.ParticleRegistry;

public class ContagionIncarnationRenderer extends MobRenderer<ContagionIncarnationEntity, ContagionIncarnation>{
	private static final ResourceLocation CONTAGION_INCARNATION_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/contagion_incarnation.png");
	private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);
	private static final float PARTICLE_DEATH_RADIUS = 2f;
	private static final float PARTICLE_SPAWN_RADIUS = 4f;
	private static final BlockParticleOption SOLID_CORRUPTION_PARTICLE
			= new BlockParticleOption(ParticleTypes.BLOCK,
			BlockRegistry.SOLID_CORRUPTION.get().defaultBlockState());

	public ContagionIncarnationRenderer(EntityRendererProvider.Context context) {
		super(context, new ContagionIncarnation(context.bakeLayer(ContagionIncarnation.LAYER_LOCATION)), 3f);
		this.addLayer(new ContagionIncarnationEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ContagionIncarnationEntity pEntity) {
		return CONTAGION_INCARNATION_TEXTURE;
	}
	
	@Override
	public void render(ContagionIncarnationEntity pEntity, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack,
					   @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (pEntity.getTickSpawn() >= ContagionIncarnationEntity.SPAWN_DURATION - 1) return;// This line prevents the visualization of a wrong first frame of the entity.
		int blockLight = (pPackedLight >> 4) & 0xF;
		int skyLight = (pPackedLight >> 20) & 0xF;
		float reductionFactor = ((float)ContagionIncarnationEntity.DEATH_DURATION - (float)pEntity.contagionIncarnationDeathTime)
		                        / (float)ContagionIncarnationEntity.DEATH_DURATION;
		int decreasingBlockLight = (int)(blockLight * reductionFactor);
		int decreasingSkyLight = (int)(skyLight * reductionFactor);
		int decreasingLight = LightTexture.pack(decreasingSkyLight, decreasingBlockLight);

		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, decreasingLight);
		this.deathLightEffect(pEntity, pPartialTicks, pMatrixStack, pBuffer);
		this.deathParticleEffect(pEntity);
		this.spawnParticleEffect(pEntity);
	}

	private void spawnParticleEffect(ContagionIncarnationEntity entity) {
		if (entity.getTickSpawn() > ContagionIncarnationEntity.SPAWN_DURATION / 2) {
			double centerX = entity.getX();
			double centerY = entity.getY() + entity.getBbHeight() / 2.0;
			double centerZ = entity.getZ();
			int particleCount = 30;
			for (int j = 0; j < particleCount; j++) {
				double x = centerX + PARTICLE_SPAWN_RADIUS * (entity.getRandom().nextFloat() - 0.5f);
				double y = centerY + PARTICLE_SPAWN_RADIUS * (entity.getRandom().nextFloat() - 0.5f);
				double z = centerZ + PARTICLE_SPAWN_RADIUS * (entity.getRandom().nextFloat() - 0.5f);
				entity.level().addParticle(SOLID_CORRUPTION_PARTICLE, x, y, z,
						0, 0.1, 0);
			}
		}
	}

	private void deathParticleEffect(ContagionIncarnationEntity entity) {
		if (entity.contagionIncarnationDeathTime > ContagionIncarnationEntity.DEATH_DURATION * 0.9f) {
			double centerX = entity.getX();
			double centerY = entity.getY() + entity.getBbHeight() / 2.0;
			double centerZ = entity.getZ();
			int particleCount = 100;
			for (int j = 0; j < particleCount; j++) {
				double theta = 2 * Math.PI * entity.getRandom().nextDouble();
				double phi = Math.acos(2 * entity.getRandom().nextDouble() - 1);
				double x = centerX + PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.cos(theta);
				double y = centerY + PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.sin(theta);
				double z = centerZ + PARTICLE_DEATH_RADIUS * Math.cos(phi);
				double norm = Math.sqrt((x - centerX) * (x - centerX)
						+ (y - centerY) * (y - centerY) + (z - centerZ) * (z - centerZ));
				entity.level().addParticle(ParticleRegistry.CORRUPTION.get(), x, y, z,
						(x - centerX)/norm,
						(y - centerY)/norm,
						(z - centerZ)/norm);
			}
		} else if (entity.contagionIncarnationDeathTime > 0) {
			double centerX = entity.getX();
			double centerY = entity.getY() + entity.getBbHeight() / 2.0;
			double centerZ = entity.getZ();
			double theta = 2 * Math.PI * entity.getRandom().nextDouble();
			double phi = Math.acos(2 * entity.getRandom().nextDouble() - 1);
			double x = centerX + 3d * PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.cos(theta);
			double y = centerY + 3d * PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.sin(theta);
			double z = centerZ + 3d * PARTICLE_DEATH_RADIUS * Math.cos(phi);
			double norm = Math.sqrt((x - centerX) * (x - centerX)
					+ (y - centerY) * (y - centerY) + (z - centerZ) * (z - centerZ));
			entity.level().addParticle(ParticleRegistry.CORRUPTION.get(), x, y, z,
					-(x - centerX)/norm,
					-(y - centerY)/norm,
					-(z - centerZ)/norm);
		}
	}

	private void deathLightEffect(ContagionIncarnationEntity pEntity, float pPartialTicks,
								  PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer) {
		if (pEntity.contagionIncarnationDeathTime > 0) {
			float f5 = ((float)pEntity.contagionIncarnationDeathTime + pPartialTicks) / 200.0F;
			float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
			RandomSource randomsource = RandomSource.create(432L);
			VertexConsumer vertexconsumer2 = pBuffer.getBuffer(RenderType.lightning());
			pMatrixStack.pushPose();
			pMatrixStack.translate(0.0F, 1.0F, 0.0F);

			for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
				pMatrixStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
				pMatrixStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
				pMatrixStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F));
				pMatrixStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
				pMatrixStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
				pMatrixStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F + f5 * 90.0F));
				float f3 = randomsource.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
				float f4 = randomsource.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
				Matrix4f matrix4f = pMatrixStack.last().pose();
				int j = (int)(255.0F * (1.0F - f7));
				vertex01(vertexconsumer2, matrix4f, j);
				vertex2(vertexconsumer2, matrix4f, f3, f4);
				vertex3(vertexconsumer2, matrix4f, f3, f4);
				vertex01(vertexconsumer2, matrix4f, j);
				vertex3(vertexconsumer2, matrix4f, f3, f4);
				vertex4(vertexconsumer2, matrix4f, f3, f4);
				vertex01(vertexconsumer2, matrix4f, j);
				vertex4(vertexconsumer2, matrix4f, f3, f4);
				vertex2(vertexconsumer2, matrix4f, f3, f4);
			}

			pMatrixStack.popPose();
		}
	}
	
	private static void vertex01(VertexConsumer consumer, Matrix4f matrix, int alpha) {
		consumer.addVertex(matrix, 0.0F, 0.0F, 0.0F).setColor(0, 55, 70, alpha);
	   }

	   private static void vertex2(VertexConsumer consumer, Matrix4f matrix, float f1, float f2) {
		   consumer.addVertex(matrix, -HALF_SQRT_3 * f2, f1, -0.5F * f2).setColor(0, 55, 70, 0);
	   }

	   private static void vertex3(VertexConsumer consumer, Matrix4f matrix, float f1, float f2) {
		   consumer.addVertex(matrix, HALF_SQRT_3 * f2, f1, -0.5F * f2).setColor(0, 55, 70, 0);
	   }

	   private static void vertex4(VertexConsumer consumer, Matrix4f matrix, float f1, float f2) {
		   consumer.addVertex(matrix, 0.0F, f1, f2).setColor(0, 55, 70, 0);
	   }

	@Override
	protected void scale(@NotNull ContagionIncarnationEntity entity, @NotNull PoseStack stack, float v) {
		stack.scale(2f, 2f, 2f);
		super.scale(entity, stack, v);
	}
}
