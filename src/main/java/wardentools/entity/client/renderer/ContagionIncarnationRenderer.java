package wardentools.entity.client.renderer;

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
import wardentools.entity.client.emissive.ContagionIncarnationEmissiveLayer;
import wardentools.entity.client.model.ContagionIncarnation;
import wardentools.entity.client.renderstate.ContagionIncarnationRenderState;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.particle.ParticleRegistry;

public class ContagionIncarnationRenderer extends MobRenderer<ContagionIncarnationEntity,
		ContagionIncarnationRenderState, ContagionIncarnation>{
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
	public @NotNull ContagionIncarnationRenderState createRenderState() {
		return new ContagionIncarnationRenderState();
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ContagionIncarnationRenderState state) {
		return CONTAGION_INCARNATION_TEXTURE;
	}

	@Override
	public void extractRenderState(@NotNull ContagionIncarnationEntity incarnation,
								   @NotNull ContagionIncarnationRenderState state, float partialTick) {
		super.extractRenderState(incarnation, state, partialTick);
		state.tickSpawn = incarnation.getTickSpawn();
		state.isSonicStrikeStarted = incarnation.getSonicStrikeTick() > 0;
		state.contagionIncarnationDeathTime = incarnation.contagionIncarnationDeathTime;
		state.level = incarnation.level();
		state.dyingAnimationState.copyFrom(incarnation.dyingAnimationState);
		state.spawnAnimation.copyFrom(incarnation.spawnAnimation);
		state.ambient.copyFrom(incarnation.ambient);
		state.idleAmbient.copyFrom(incarnation.idleAmbient);
		state.sprint.copyFrom(incarnation.sprint);
		state.rightSwing.copyFrom(incarnation.rightSwing);
		state.leftSwing.copyFrom(incarnation.leftSwing);
		state.sonicStrike.copyFrom(incarnation.sonicStrike);
		state.section6XRot = incarnation.getSection_6_rotX();
		state.section6YRot = incarnation.getSection_6_rotY();
		state.section7XRot = incarnation.getSection_7_rotX();
		state.section7YRot = incarnation.getSection_7_rotY();
		state.section8XRot = incarnation.getSection_8_rotX();
		state.section8YRot = incarnation.getSection_8_rotY();
		state.section9XRot = incarnation.getSection_9_rotX();
		state.section9YRot = incarnation.getSection_9_rotY();
		state.section10XRot = incarnation.getSection_10_rotX();
		state.section10YRot = incarnation.getSection_10_rotY();
		state.section11XRot = incarnation.getSection_11_rotX();
		state.section11YRot = incarnation.getSection_11_rotY();
		state.section12XRot = incarnation.getSection_12_rotX();
		state.section12YRot = incarnation.getSection_12_rotY();
		state.section13XRot = incarnation.getSection_13_rotX();
		state.section13YRot = incarnation.getSection_13_rotY();
		state.section14XRot = incarnation.getSection_14_rotX();
		state.section14YRot = incarnation.getSection_14_rotY();
		state.section15XRot = incarnation.getSection_15_rotX();
		state.section15YRot = incarnation.getSection_15_rotY();
		state.section16XRot = incarnation.getSection_16_rotX();
		state.section16YRot = incarnation.getSection_16_rotY();
		state.section17XRot = incarnation.getSection_17_rotX();
		state.section17YRot = incarnation.getSection_17_rotY();
		state.section18XRot = incarnation.getSection_18_rotX();
		state.section18YRot = incarnation.getSection_18_rotY();
		state.sectionEndXRot = incarnation.getEnd_rotX();
		state.sectionEndYRot = incarnation.getEnd_rotY();
	}

	@Override
	public void render(ContagionIncarnationRenderState state,
					   @NotNull PoseStack pMatrixStack,
					   @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (state.tickSpawn >= ContagionIncarnationEntity.SPAWN_DURATION - 1) return;// This line prevents the visualization of a wrong first frame of the entity.

		super.render(state, pMatrixStack, pBuffer, pPackedLight);
		this.deathLightEffect(state, state.level.getGameTime(), pMatrixStack, pBuffer);
		this.deathParticleEffect(state);
		this.spawnParticleEffect(state);
	}

	private void spawnParticleEffect(ContagionIncarnationRenderState state) {
		RandomSource random = RandomSource.create();
		if (state.tickSpawn > ContagionIncarnationEntity.SPAWN_DURATION / 2) {
			double centerX = state.x;
			double centerY = state.y + state.boundingBoxHeight / 2.0;
			double centerZ = state.z;
			int particleCount = 30;
			for (int j = 0; j < particleCount; j++) {
				double x = centerX + PARTICLE_SPAWN_RADIUS * (random.nextFloat() - 0.5f);
				double y = centerY + PARTICLE_SPAWN_RADIUS * (random.nextFloat() - 0.5f);
				double z = centerZ + PARTICLE_SPAWN_RADIUS * (random.nextFloat() - 0.5f);
				state.level.addParticle(SOLID_CORRUPTION_PARTICLE, x, y, z,
						0, 0.1, 0);
			}
		}
	}

	private void deathParticleEffect(ContagionIncarnationRenderState state) {
		RandomSource random = RandomSource.create();
		if (state.contagionIncarnationDeathTime > ContagionIncarnationEntity.DEATH_DURATION * 0.9f) {
			double centerX = state.x;
			double centerY = state.y + state.boundingBoxHeight / 2.0;
			double centerZ = state.z;
			int particleCount = 100;
			for (int j = 0; j < particleCount; j++) {
				double theta = 2 * Math.PI * random.nextDouble();
				double phi = Math.acos(2 * random.nextDouble() - 1);
				double x = centerX + PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.cos(theta);
				double y = centerY + PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.sin(theta);
				double z = centerZ + PARTICLE_DEATH_RADIUS * Math.cos(phi);
				double norm = Math.sqrt((x - centerX) * (x - centerX)
						+ (y - centerY) * (y - centerY) + (z - centerZ) * (z - centerZ));
				state.level.addParticle(ParticleRegistry.CORRUPTION.get(), x, y, z,
						(x - centerX)/norm,
						(y - centerY)/norm,
						(z - centerZ)/norm);
			}
		} else if (state.contagionIncarnationDeathTime > 0) {
			double centerX = state.x;
			double centerY = state.y + state.boundingBoxHeight / 2.0;
			double centerZ = state.z;
			double theta = 2 * Math.PI * random.nextDouble();
			double phi = Math.acos(2 * random.nextDouble() - 1);
			double x = centerX + 3d * PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.cos(theta);
			double y = centerY + 3d * PARTICLE_DEATH_RADIUS * Math.sin(phi) * Math.sin(theta);
			double z = centerZ + 3d * PARTICLE_DEATH_RADIUS * Math.cos(phi);
			double norm = Math.sqrt((x - centerX) * (x - centerX)
					+ (y - centerY) * (y - centerY) + (z - centerZ) * (z - centerZ));
			state.level.addParticle(ParticleRegistry.CORRUPTION.get(), x, y, z,
					-(x - centerX)/norm,
					-(y - centerY)/norm,
					-(z - centerZ)/norm);
		}
	}

	private void deathLightEffect(ContagionIncarnationRenderState state, float partialTicks,
								  PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer) {
		if (state.contagionIncarnationDeathTime > 0) {
			float f5 = ((float)state.contagionIncarnationDeathTime + partialTicks) / 200.0F;
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
	protected void scale(@NotNull ContagionIncarnationRenderState renderState, @NotNull PoseStack stack) {
		stack.scale(2f, 2f, 2f);
		super.scale(renderState, stack);
	}
}
