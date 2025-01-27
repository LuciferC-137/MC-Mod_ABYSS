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
import wardentools.entity.client.model.Noctilure;
import wardentools.entity.client.renderstate.NoctilureRenderState;

public class NoctilureEmissiveLayer extends RenderLayer<NoctilureRenderState, Noctilure> {
    private static final RenderType EMISSIVE = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/noctilure_emissive.png"));

    public NoctilureEmissiveLayer(RenderLayerParent<NoctilureRenderState, Noctilure> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight, @NotNull NoctilureRenderState state, float partialTicks, float ageInTicks) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(state, 0.0F));
    }
}
