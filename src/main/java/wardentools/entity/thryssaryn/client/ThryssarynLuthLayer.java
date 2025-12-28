package wardentools.entity.thryssaryn.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

@OnlyIn(Dist.CLIENT)
public class ThryssarynLuthLayer extends RenderLayer<ThryssarynEntity, Thryssaryn> {

    public ThryssarynLuthLayer(RenderLayerParent<ThryssarynEntity, Thryssaryn> renderer) {
        super(renderer);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull ThryssarynEntity thryssaryn, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        poseStack.pushPose();

        this.getParentModel().applyLuthPositionTransform(thryssaryn, poseStack);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                ThryssarynEntity.LUTH_ITEMSTACK,
                ItemDisplayContext.FIXED,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                thryssaryn.level(),
                thryssaryn.getId()
        );

        poseStack.popPose();
    }
}

