package wardentools.blockentity.renderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import wardentools.items.ItemRegistry;

@OnlyIn(Dist.CLIENT)
public class ProtectorInvokerRenderer implements BlockEntityRenderer<ProtectorInvokerBlockEntity> {
	private final BlockEntityRendererProvider.Context context;
	private boolean sentParticle = false;
	private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);
	private int time = 0; // In ticks
	private float previouspTick = 0;
	private static final int RAY_NUMBER = 5;
	private static final float RAY_SPEED = 0.2f; // In degrees per tick
	
	public ProtectorInvokerRenderer(BlockEntityRendererProvider.Context ctx) {
		this.context = ctx;
	}

	@Override
    public void render(ProtectorInvokerBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource buffer, int packLight, int packedOverlay) {
		ItemStack stack = blockEntity.getInventory().getStackInSlot(0);
        if(!stack.isEmpty()) {
	        Level level = blockEntity.getLevel();
	        if(level == null)
	            return;
	        BlockPos pos = blockEntity.getBlockPos().above();
	        double relativeGameTime = level.getGameTime() + partialTick;
	        double offset = 0.95;
	        double rotation = relativeGameTime / 0.8;
	        double scale = 0.5;
			if (stack.is(ItemRegistry.PROTECTOR_HEART.get()) && blockEntity.protectorSuccessfullyInvoked) {
				animateLight(partialTick, buffer, poseStack, offset);
			} else if (stack.is(ItemRegistry.DYING_PROTECTOR_HEART.get())) {
				animateRedParticles(level, pos, offset);
			}
	        renderItem(level, poseStack, stack, buffer, offset, scale, rotation);
			renderText(level, pos, poseStack, offset, blockEntity, buffer);
        }
		this.previouspTick = partialTick;
	}

	private void renderItem(Level level, PoseStack poseStack, ItemStack stack, MultiBufferSource buffer,
							double offset, double scale, double rotation){
		poseStack.pushPose();
		poseStack.translate(0.5, offset, 0.5);
		poseStack.scale((float) scale, (float) scale, (float) scale);
		poseStack.mulPose(Axis.YP.rotationDegrees((float) rotation));
		this.context.getItemRenderer().renderStatic(
				stack,
				ItemDisplayContext.FIXED,
				0xFF,
				OverlayTexture.WHITE_OVERLAY_V,
				poseStack,
				buffer,
				level,
				0
		);

		poseStack.popPose();
	}

	private void renderText(Level level, BlockPos pos, PoseStack poseStack, double offset,
							ProtectorInvokerBlockEntity blockEntity, MultiBufferSource buffer) {
		Player player = Minecraft.getInstance().player;
		if (player != null) {
			int packedLight = LightTexture.pack(
					level.getBrightness(LightLayer.BLOCK, pos),
					level.getBrightness(LightLayer.SKY, pos)
			);
			Vec3 playerPos = player.position();
			double dx = playerPos.x - (pos.getX() + 0.5);
			double dz = playerPos.z - (pos.getZ() + 0.5);
			float scaleTxt = 0.015f;

			poseStack.pushPose();
			poseStack.translate(0.5, offset + 0.35, 0.5);
			poseStack.scale(scaleTxt, -scaleTxt, scaleTxt);
			if (dz<0) {
				poseStack.mulPose(Axis.YP.rotation(3.1415f + (float)Math.atan(dx/dz)));
			} else {
				poseStack.mulPose(Axis.YP.rotation((float)Math.atan(dx/dz)));
			}
			Font font = this.context.getFont();
			String healthText = blockEntity.healthText();
			poseStack.translate(-font.width(healthText) / 2.0f, 0, 0);

			font.drawInBatch(
					healthText,
					0,
					0,
					0xECECEC,
					false,
					poseStack.last().pose(),
					buffer,
					Font.DisplayMode.NORMAL,
					0,
					packedLight
			);
			poseStack.popPose();
		}
	}

	private void animateRedParticles(Level level, BlockPos pos, double offset){
		if (level.getGameTime()%25 == 0 && !this.sentParticle) {
			this.sentParticle = true;
			double offsetX = (level.random.nextDouble() - 0.5) * 0.15;
			double offsetY = (level.random.nextDouble() - 0.5) * 0.15;
			double offsetZ = (level.random.nextDouble() - 0.5) * 0.15;
			level.addParticle(ParticleTypes.CRIMSON_SPORE, true,
					pos.getX() + 0.5, pos.getY() - offset + 1.0, pos.getZ() + 0.5,
					offsetX, offsetY, offsetZ);
		} else {
			this.sentParticle = false;
		}
	}

	private void animateLight(float partialTick, MultiBufferSource buffer, PoseStack poseStack, double offset){
		if (partialTick < this.previouspTick) {
			this.time++;
		}
		RandomSource randomsource = RandomSource.create(432L);
		float lengthOfRay = 0.7f + randomsource.nextFloat() * 0.3f;
		float widthOfRay = 0.1f + randomsource.nextFloat() * 0.05f;
		float f5 = (float)this.time / 20.0f;
		float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
		VertexConsumer vertexconsumer2 = buffer.getBuffer(RenderType.lightning());

		poseStack.pushPose();
		poseStack.translate(0.5, offset, 0.5);

		for (int i = 0; i < RAY_NUMBER; ++i) {
			poseStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F
					+ this.time * RAY_SPEED));
			poseStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F
					+ this.time * RAY_SPEED));
			float f3 = lengthOfRay + f7 * lengthOfRay / 4f;
			float f4 = widthOfRay + f7 * widthOfRay / 4f;
			Matrix4f matrix4f = poseStack.last().pose();
			int j = 200;
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
		poseStack.popPose();
	}
	
	private static void vertex01(VertexConsumer vertexConsumer, Matrix4f mat, int alpha) {
		vertexConsumer.addVertex(mat, 0.0F, 0.0F, 0.0F).setColor(183, 208, 226, alpha);
	   }

	private static void vertex2(VertexConsumer vertexConsumer, Matrix4f mat, float f1, float f2) {
		vertexConsumer.addVertex(mat, -HALF_SQRT_3 * f2, f1, -0.5F * f2).setColor(0, 255, 255, 0);
	   }

	private static void vertex3(VertexConsumer vertexConsumer, Matrix4f mat, float f1, float f2) {
		vertexConsumer.addVertex(mat, HALF_SQRT_3 * f2, f1, -0.5F * f2).setColor(15, 195, 183, 0);
	   }

	private static void vertex4(VertexConsumer vertexConsumer, Matrix4f mat, float f1, float f2) {
		vertexConsumer.addVertex(mat, 0.0F, f1, f2).setColor(117, 176, 216, 0);
	   }

}
