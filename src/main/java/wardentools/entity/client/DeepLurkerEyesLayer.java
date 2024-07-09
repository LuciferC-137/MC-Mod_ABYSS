package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.DeepLurkerEntity;

public class DeepLurkerEyesLayer extends RenderLayer<DeepLurkerEntity, DeepLurker> {
    private static final RenderType EYES = RenderType.eyes(new ResourceLocation(ModMain.MOD_ID,
    		"textures/entity/deeplurker_eyes.png"));

    public DeepLurkerEyesLayer(RenderLayerParent<DeepLurkerEntity, DeepLurker> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource,
    		int packedLight, DeepLurkerEntity entity, float limbSwing,
    		float limbSwingAmount, float partialTicks, float ageInTicks,
    		float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EYES);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
