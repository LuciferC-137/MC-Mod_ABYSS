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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.client.model.DysfunctionningCatalystInterior;

@OnlyIn(Dist.CLIENT)
public class DysfunctionningCatalystRenderer implements BlockEntityRenderer<DysfunctionningCatalystBlockEntity> {
	private static final float averageRotationSpeed = 3.0f;
    private static final float speedAmplifier = 10.0f;
    private static final float scaleAmplifier = 1.6f;
    private static final DysfunctionningCatalystInterior model =
			new DysfunctionningCatalystInterior(DysfunctionningCatalystInterior.createBodyLayer().bakeRoot());
    private static final ResourceLocation INTERIOR_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/models/dysfunctionning_catalyst_interior.png");

	public DysfunctionningCatalystRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull DysfunctionningCatalystBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {

        Level level = blockEntity.getLevel();
        if (level == null) return;

        renderInterior(blockEntity.getBlockPos(), poseStack, level, partialTick, buffer, packedOverlay);
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
                packedOverlay);

        poseStack.popPose();
    }
}
