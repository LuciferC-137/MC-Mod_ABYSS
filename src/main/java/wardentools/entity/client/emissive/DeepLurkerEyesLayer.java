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
import wardentools.entity.client.model.DeepLurker;
import wardentools.entity.client.renderstate.DeeplurkerRenderState;

public class DeepLurkerEyesLayer extends RenderLayer<DeeplurkerRenderState, DeepLurker> {
    private static final RenderType EYES = RenderType.eyes(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/deeplurker_eyes.png"));

    public DeepLurkerEyesLayer(RenderLayerParent<DeeplurkerRenderState, DeepLurker> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, @NotNull DeeplurkerRenderState state, float partialTicks, float ageInTicks) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EYES);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(state, 0.0F));
    }
}
