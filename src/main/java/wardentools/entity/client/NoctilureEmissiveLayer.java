package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.NoctilureEntity;

@OnlyIn(Dist.CLIENT)
public class NoctilureEmissiveLayer extends RenderLayer<NoctilureEntity, Noctilure> {
    private static final RenderType EMISSIVE = RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/noctilure_emissive.png"));

    public NoctilureEmissiveLayer(RenderLayerParent<NoctilureEntity, Noctilure> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight, @NotNull NoctilureEntity entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, LightTexture.FULL_BRIGHT,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
    }
}
