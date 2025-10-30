package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import wardentools.entity.custom.ContagionIncarnationEntity;

@OnlyIn(Dist.CLIENT)
public class ContagionIncarnationShellLayer extends RenderLayer<ContagionIncarnationEntity, ContagionIncarnation> {
    private static final RenderType EMISSIVE = RenderType.entityTranslucent(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
    		"textures/entity/contagion_incarnation/contagion_incarnation_shell.png"));

    public ContagionIncarnationShellLayer(RenderLayerParent<ContagionIncarnationEntity, ContagionIncarnation> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight, @NotNull ContagionIncarnationEntity entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(EMISSIVE);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, packedLight,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
    }
}
