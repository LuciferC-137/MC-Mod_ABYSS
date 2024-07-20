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
import wardentools.ModMain;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.client.model.RadianceCatalystInterior;

public class RadianceCatalystRenderer implements BlockEntityRenderer<RadianceCatalystBlockEntity> {
	private double rotationSpeed = 3.0;
	private static final RadianceCatalystInterior model =
			new RadianceCatalystInterior(RadianceCatalystInterior.createBodyLayer().bakeRoot());
	
	public RadianceCatalystRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(RadianceCatalystBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (blockEntity == null) return;
        
        this.rotationSpeed = blockEntity.getRotationSpeed();
        Level level = blockEntity.getLevel();
        if (level == null) return;

        BlockPos pos = blockEntity.getBlockPos();
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(1.0F, 1.0F, 1.0F);

        double relativeGameTime = level.getGameTime() + partialTick;
        float rotation = (float) (relativeGameTime * this.rotationSpeed);
        poseStack.mulPose(Axis.XP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(
        		new ResourceLocation(ModMain.MOD_ID, "textures/models/radiance_catalyst_interior.png")));

        model.render(poseStack, vertexConsumer, LightTexture.pack(
				level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos)),
        		packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}
