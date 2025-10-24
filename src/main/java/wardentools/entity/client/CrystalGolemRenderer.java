package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.client.rendering.RenderingUtils;
import wardentools.entity.custom.CrystalGolemEntity;

public class CrystalGolemRenderer extends MobRenderer<CrystalGolemEntity, CrystalGolem>{
	private static final ResourceLocation CRYSTAL_GOLEM_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/crystal_golem/crystal_golem.png");

	private static final float MIN_CUBE_SIZE = 0.2F;
	private static final float MAX_CUBE_SIZE = 0.5F;

	public CrystalGolemRenderer(EntityRendererProvider.Context context) {
		super(context, new CrystalGolem(context.bakeLayer(CrystalGolem.LAYER_LOCATION)), 0.5f);
		this.addLayer(new CrystalGolemRustLayer(this));
		this.addLayer(new CrystalGolemSculkLayer(this));
		this.addLayer(new CrystalGolemCrystalLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull CrystalGolemEntity pEntity) {
		return CRYSTAL_GOLEM_TEXTURE;
	}

	@Override
	public void render(@NotNull CrystalGolemEntity golem, float yaw, float partialTicks,
					   @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource buffer, int packedLight) {
		super.render(golem, yaw, partialTicks, poseStack, buffer, packedLight);

		if (golem.getLaserTick() > 0) {
			poseStack.pushPose();
			poseStack.translate(0.0F, golem.getLaserChargingPosition(), 0.0F);

			float a = 0.3F;
			float r = golem.getCrystal().getRed();
			float g = golem.getCrystal().getGreen();
			float b = golem.getCrystal().getBlue();

			VertexConsumer consumer = buffer.getBuffer(RenderingUtils.COLORED_CUBE);

			float progress = ((float)CrystalGolemEntity.LASER_DURATION - (float)golem.getLaserTick())
					/ (float)CrystalGolemEntity.LASER_DURATION;
			float baseSize = MIN_CUBE_SIZE + (MAX_CUBE_SIZE - MIN_CUBE_SIZE) * progress;

			// Three cubes with same centers
			for (int i = 0; i < 3; i++) {
				poseStack.pushPose();

				float scale = baseSize + i * baseSize / 2F; // increase size for each cube
				poseStack.scale(scale, scale, scale);
				float rotation = (golem.tickCount + partialTicks) * (5F + i * 10F);

				if (i == 1) {
					poseStack.mulPose(Axis.XP.rotationDegrees(rotation / 2F));
					poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
					poseStack.mulPose(Axis.ZP.rotationDegrees(rotation / 3F));
				} else if (i == 2) {
					poseStack.mulPose(Axis.XP.rotationDegrees(-rotation / 3F));
					poseStack.mulPose(Axis.YP.rotationDegrees(-rotation / 2F));
					poseStack.mulPose(Axis.ZP.rotationDegrees(-rotation));
				} else {
					poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
					poseStack.mulPose(Axis.YP.rotationDegrees(rotation / 3F));
					poseStack.mulPose(Axis.ZP.rotationDegrees(-rotation / 2F));
				}

				renderCube(poseStack, consumer, r, g, b, a);

				poseStack.popPose();
			}

			poseStack.popPose();
		}
	}

	private static void renderCube(PoseStack poseStack, VertexConsumer consumer,
								   float r, float g, float b, float a) {
		PoseStack.Pose entry = poseStack.last();

		int ri = Math.max(0, Math.min(255, (int)(r * 255f)));
		int gi = Math.max(0, Math.min(255, (int)(g * 255f)));
		int bi = Math.max(0, Math.min(255, (int)(b * 255f)));
		int ai = Math.max(0, Math.min(255, (int)(a * 255f)));

		float s = 0.3F; // Cube Half Size

		// Down Face
		consumer.addVertex(entry.pose(), -s, -s, -s).setColor(ri, gi, bi, ai).setNormal(0f, -1f, 0f);
		consumer.addVertex(entry.pose(),  s, -s, -s).setColor(ri, gi, bi, ai).setNormal(0f, -1f, 0f);
		consumer.addVertex(entry.pose(),  s, -s,  s).setColor(ri, gi, bi, ai).setNormal(0f, -1f, 0f);
		consumer.addVertex(entry.pose(), -s, -s,  s).setColor(ri, gi, bi, ai).setNormal(0f, -1f, 0f);

		// Up Face
		consumer.addVertex(entry.pose(), -s,  s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 1f, 0f);
		consumer.addVertex(entry.pose(), -s,  s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 1f, 0f);
		consumer.addVertex(entry.pose(),  s,  s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 1f, 0f);
		consumer.addVertex(entry.pose(),  s,  s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 1f, 0f);

		// North Face
		consumer.addVertex(entry.pose(), -s, -s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, -1f);
		consumer.addVertex(entry.pose(), -s,  s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, -1f);
		consumer.addVertex(entry.pose(),  s,  s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, -1f);
		consumer.addVertex(entry.pose(),  s, -s, -s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, -1f);

		// South Face
		consumer.addVertex(entry.pose(), -s, -s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, 1f);
		consumer.addVertex(entry.pose(),  s, -s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, 1f);
		consumer.addVertex(entry.pose(),  s,  s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, 1f);
		consumer.addVertex(entry.pose(), -s,  s,  s).setColor(ri, gi, bi, ai).setNormal(0f, 0f, 1f);

		// Left Face
		consumer.addVertex(entry.pose(), -s, -s, -s).setColor(ri, gi, bi, ai).setNormal(-1f, 0f, 0f);
		consumer.addVertex(entry.pose(), -s, -s,  s).setColor(ri, gi, bi, ai).setNormal(-1f, 0f, 0f);
		consumer.addVertex(entry.pose(), -s,  s,  s).setColor(ri, gi, bi, ai).setNormal(-1f, 0f, 0f);
		consumer.addVertex(entry.pose(), -s,  s, -s).setColor(ri, gi, bi, ai).setNormal(-1f, 0f, 0f);

		// East Face
		consumer.addVertex(entry.pose(),  s, -s, -s).setColor(ri, gi, bi, ai).setNormal(1f, 0f, 0f);
		consumer.addVertex(entry.pose(),  s,  s, -s).setColor(ri, gi, bi, ai).setNormal(1f, 0f, 0f);
		consumer.addVertex(entry.pose(),  s,  s,  s).setColor(ri, gi, bi, ai).setNormal(1f, 0f, 0f);
		consumer.addVertex(entry.pose(),  s, -s,  s).setColor(ri, gi, bi, ai).setNormal(1f, 0f, 0f);
	}

}
