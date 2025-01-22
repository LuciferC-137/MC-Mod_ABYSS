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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.client.model.RadianceCatalystInterior;

public class RadianceCatalystRenderer implements BlockEntityRenderer<RadianceCatalystBlockEntity> {
	private static final float rotationSpeed = 1.5f;
    private static final ResourceLocation INTERIOR_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/models/radiance_catalyst_interior.png");
    private static final RadianceCatalystInterior model =
			new RadianceCatalystInterior(RadianceCatalystInterior.createBodyLayer().bakeRoot());
	
	public RadianceCatalystRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull RadianceCatalystBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        boolean isPurifying = blockEntity.getPurifyingTime() > 0;

        Level level = blockEntity.getLevel();
        if (level == null) return;

        BlockPos pos = blockEntity.getBlockPos();
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(1.0F, 1.0F, 1.0F);

        double relativeGameTime = level.getGameTime() + partialTick;
        if (!isPurifying) {
        	float rotation = ((float)relativeGameTime) * rotationSpeed;
        	poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        } else {
        	float rotation = (float) (relativeGameTime);
        	float rotationRate = 1.0f + ((float)blockEntity.getPurifyingTime()
        			/(float)RadianceCatalystBlockEntity.purifyTime * 3.0f);
        	poseStack.scale(0.8F, 0.8F, 0.8F);
        	poseStack.mulPose(Axis.YP.rotationDegrees(rotation * rotationRate));
        }

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(INTERIOR_TEXTURE));

        model.render(poseStack, vertexConsumer, LightTexture.pack(
				level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos)),
        		packedOverlay);

        poseStack.popPose();
    }
}
