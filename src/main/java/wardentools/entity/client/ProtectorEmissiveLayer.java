package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ProtectorEntity;

public class ProtectorEmissiveLayer extends RenderLayer<ProtectorEntity, Protector> {
    private static final RenderType EMISSIVE = RenderType.eyes(new ResourceLocation(ModMain.MOD_ID,
    		"textures/entity/protector_emissive.png"));

    public ProtectorEmissiveLayer(RenderLayerParent<ProtectorEntity, Protector> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, @NotNull ProtectorEntity entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
