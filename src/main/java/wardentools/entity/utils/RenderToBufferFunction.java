package wardentools.entity.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

@FunctionalInterface
public interface RenderToBufferFunction {
    void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                        int packedLight, int packedOverlay);
}
