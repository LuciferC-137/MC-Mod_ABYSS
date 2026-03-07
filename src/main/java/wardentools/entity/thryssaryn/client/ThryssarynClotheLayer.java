package wardentools.entity.thryssaryn.client;

import com.mojang.blaze3d.vertex.PoseStack;
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
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

@OnlyIn(Dist.CLIENT)
public class ThryssarynClotheLayer extends RenderLayer<ThryssarynEntity, Thryssaryn> {
    private static final RenderType SHELL = RenderType.entityTranslucent(ResourceLocation // TODO
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/thryssaryn/clothes/musician.png"));

    public ThryssarynClotheLayer(RenderLayerParent<ThryssarynEntity, Thryssaryn> renderer) {
        super(renderer);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull ThryssarynEntity thryssaryn, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        this.getParentModel().renderToBuffer(poseStack, buffer.getBuffer(SHELL), packedLight,
                LivingEntityRenderer.getOverlayCoords(thryssaryn, 0.0F));
    }
}

