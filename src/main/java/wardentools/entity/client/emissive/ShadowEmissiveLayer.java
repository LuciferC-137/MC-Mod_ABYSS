package wardentools.entity.client.emissive;

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
import wardentools.entity.client.model.Shadow;
import wardentools.entity.client.renderstate.ShadowRenderState;

public class ShadowEmissiveLayer extends RenderLayer<ShadowRenderState, Shadow> {
    private static final RenderType EMISSIVE = RenderType.eyes(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/shadow_emissive.png"));

    public ShadowEmissiveLayer(RenderLayerParent<ShadowRenderState, Shadow> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight, @NotNull ShadowRenderState state,  float partialTicks, float ageInTicks) {
        if (state.mimicEntity != null) return;
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(state, 0.0F));
    }
}
