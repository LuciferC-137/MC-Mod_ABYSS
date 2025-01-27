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
import wardentools.entity.client.model.Parasyte;
import wardentools.entity.client.renderstate.ParasyteRenderState;

public class ParasyteEmissiveLayer extends RenderLayer<ParasyteRenderState, Parasyte> {
    private static final RenderType EMISSIVE = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/parasyte_emissive.png"));

    public ParasyteEmissiveLayer(RenderLayerParent<ParasyteRenderState, Parasyte> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, @NotNull ParasyteRenderState entity, float partialTicks, float ageInTicks) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
    }
}
