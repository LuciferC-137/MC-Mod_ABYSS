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
import wardentools.entity.custom.CrystalGolemEntity;

public class CrystalGolemRustLayer extends RenderLayer<CrystalGolemEntity, CrystalGolem> {
    private static final RenderType RUST = RenderType.entityTranslucent(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/crystal_golem_rust_overlay.png"));

    public CrystalGolemRustLayer(RenderLayerParent<CrystalGolemEntity, CrystalGolem> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, @NotNull CrystalGolemEntity entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RUST);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, packedLight,
        		LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
    }
}
