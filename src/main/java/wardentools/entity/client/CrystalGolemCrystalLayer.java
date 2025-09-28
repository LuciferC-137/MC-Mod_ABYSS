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

public class CrystalGolemCrystalLayer extends RenderLayer<CrystalGolemEntity, CrystalGolem> {
    private static final RenderType GLOW = RenderType.entityTranslucent(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/crystal_golem_glow_overlay.png"));

    public CrystalGolemCrystalLayer(RenderLayerParent<CrystalGolemEntity, CrystalGolem> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight, @NotNull CrystalGolemEntity golem, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {
        if (golem.getState() == CrystalGolemEntity.GolemState.DEACTIVATED_1
            || golem.getState() == CrystalGolemEntity.GolemState.DEACTIVATED_2) {
            return;
        }
        VertexConsumer vertexConsumer = bufferSource.getBuffer(GLOW);
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640,
        		LivingEntityRenderer.getOverlayCoords(golem, 0.0F), golem.getCrystal().getColorARGB());
    }
}
