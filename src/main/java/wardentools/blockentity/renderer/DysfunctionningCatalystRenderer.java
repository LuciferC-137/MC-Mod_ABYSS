package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.client.model.DysfunctionningCatalystInterior;
import wardentools.particle.ParticleRegistry;

public class DysfunctionningCatalystRenderer implements BlockEntityRenderer<DysfunctionningCatalystBlockEntity> {
	private static final float averageRotationSpeed = 3.0f;
    private static final float speedAmplifier = 10.0f;
    private static final float scaleAmplifier = 1.6f;
    private static final float particleSpawnRadius = 2f;
    private static final DysfunctionningCatalystInterior model =
			new DysfunctionningCatalystInterior(DysfunctionningCatalystInterior.createBodyLayer().bakeRoot());
    private static final ResourceLocation INTERIOR_TEXTURE
            = new ResourceLocation(ModMain.MOD_ID, "textures/models/dysfunctionning_catalyst_interior.png");

	public DysfunctionningCatalystRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull DysfunctionningCatalystBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {

        Level level = blockEntity.getLevel();
        if (level == null) return;

        renderInterior(blockEntity.getBlockPos(), poseStack, level, partialTick, buffer, packedOverlay);
        addParticle(level, blockEntity);
    }

    private static void addParticle(Level level, DysfunctionningCatalystBlockEntity blockEntity){
        if (level.getGameTime()%10 == level.getRandom().nextInt(10)){
            Vec3 center = blockEntity.getBlockPos().getCenter();
            float x =  (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float y = (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float z = (level.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
            float norm = Mth.sqrt(x*x + y*y + z*z) / 0.2f;
            level.addParticle(ParticleRegistry.CORRUPTION.get(),
                    (float)center.x + x,
                    (float)center.y + y,
                    (float)center.z + z,
                    -x / norm,
                    -y / norm,
                    -z / norm);
        }
    }

    private static void renderInterior(BlockPos pos, PoseStack poseStack, Level level, float partialTick,
                                       MultiBufferSource buffer, int packedOverlay){
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);

        double relativeGameTime = level.getGameTime() + partialTick;

        float speedX = Mth.sin((float)relativeGameTime / 80f) * speedAmplifier;
        float speedY = Mth.sin((float)relativeGameTime / 100f) * speedAmplifier;
        float speedZ = Mth.sin((float)relativeGameTime / 130f) * speedAmplifier;

        float rotationX = (((float)relativeGameTime) + speedX) * averageRotationSpeed;
        float rotationY = (((float)relativeGameTime) + speedY) * averageRotationSpeed;
        float rotationZ = (((float)relativeGameTime) + speedZ ) * averageRotationSpeed;

        poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotationY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));

        float scale = (Mth.sin((float)relativeGameTime / 20f)
                * Mth.sin((float)relativeGameTime / 60f)
                / 4f + 1f) * scaleAmplifier;
        poseStack.scale(scale, scale, scale);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(INTERIOR_TEXTURE));

        model.render(poseStack, vertexConsumer, LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos)),
                packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}
