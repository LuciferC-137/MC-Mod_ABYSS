package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import wardentools.client.rendering.RenderingUtils;
import wardentools.entity.custom.CrystalLaserEntity;
import wardentools.misc.Crystal;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CrystalLaserRenderer extends EntityRenderer<CrystalLaserEntity> {
    private static final float LASER_MIN_THICKNESS = 0.04F;
    private static final float LASER_ALPHA = 0.4F;
    private static final int N_LAYERS = 4;
    private static final float THICKNESS_STEP = 0.8F;

    public CrystalLaserRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(@NotNull CrystalLaserEntity laser, float yaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {

        List<Vec3> points = laser.getAllTargets();
        int segment = laser.getActiveSegment();
        if (points.size() < 2 || segment >= points.size() - 1) return;

        Crystal crystal = laser.getCrystalType();

        VertexConsumer consumer = bufferSource.getBuffer(RenderingUtils.COLORED_CUBE);

        Vec3 entityPos = laser.position();
        Vec3 cameraPos = this.entityRenderDispatcher.camera.getPosition();

        Vec3 start = points.get(segment).subtract(entityPos);
        Vec3 end = points.get(segment + 1).subtract(entityPos);
        Vec3 dir = end.subtract(start).normalize();

        Vec3 midPoint = start.add(end).scale(0.5);
        Vec3 toCamera = cameraPos.subtract(entityPos.add(midPoint)).normalize();

        Vec3 sideDir = dir.cross(toCamera).normalize();

        poseStack.pushPose();
        Matrix4f matrix = poseStack.last().pose();

        for (int i = 0; i < N_LAYERS; i++) {
            float thickness = LASER_MIN_THICKNESS * (1.0F + THICKNESS_STEP * i);
            Vec3 side = sideDir.scale(thickness);

            float offset = 0.001F * i;
            Vec3 offsetVec = toCamera.scale(offset);
            Vec3 startOffset = start.add(offsetVec);
            Vec3 endOffset = end.add(offsetVec);

            this.buildRectangle(consumer, matrix, startOffset, endOffset, side,
                    crystal.getRed(), crystal.getGreen(), crystal.getBlue(), LASER_ALPHA);
        }

        poseStack.popPose();

        super.render(laser, yaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    private void buildRectangle(VertexConsumer consumer, Matrix4f matrix,
                                Vec3 start, Vec3 end, Vec3 side,
                                float r, float g, float b, float a) {
        Vec3 v1 = start.add(side);
        Vec3 v2 = start.subtract(side);
        Vec3 v3 = end.subtract(side);
        Vec3 v4 = end.add(side);
        consumer.addVertex(matrix, (float) v1.x, (float) v1.y, (float) v1.z)
                .setColor(r, g, b, a)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, (float) v2.x, (float) v2.y, (float) v2.z)
                .setColor(r, g, b, a)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, (float) v3.x, (float) v3.y, (float) v3.z)
                .setColor(r, g, b, a)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix, (float) v4.x, (float) v4.y, (float) v4.z)
                .setColor(r, g, b, a)
                .setNormal(0, 1, 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CrystalLaserEntity laser) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}

