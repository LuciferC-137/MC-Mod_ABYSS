// java
package wardentools.blockentity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import wardentools.blockentity.AbyssPortalBlockEntity;
import wardentools.client.rendering.AbyssPortalBuilder;

public class AbyssPortalBlockRenderer implements BlockEntityRenderer<AbyssPortalBlockEntity> {

    public AbyssPortalBlockRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(@NotNull AbyssPortalBlockEntity entity, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {
        if (entity.getLevel() == null) return;

        ClientLevel level = (ClientLevel) entity.getLevel();
        Minecraft mc = Minecraft.getInstance();
        Camera camera = mc.gameRenderer.getMainCamera();

        // 1) Update the shared full-screen sky texture once per frame
        AbyssPortalBuilder.INSTANCE.updateSkyTexture(level, camera, partialTicks);
        int skyTex = AbyssPortalBuilder.INSTANCE.getSkyTextureId();
        if (skyTex == 0) return;

        poseStack.pushPose();

        RenderSystem.enableBlend();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, skyTex);

        // Use the same P and V that the shader uses to avoid drift
        Matrix4f projection = RenderSystem.getProjectionMatrix();
        Matrix4f view = RenderSystem.getModelViewMatrix();
        Matrix4f model = poseStack.last().pose();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bb = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

        for (Direction dir : Direction.values()) {
            if (!entity.shouldRenderFace(dir)) continue;
            emitFace(bb, model, view, projection, dir);
        }

        BufferUploader.drawWithShader(bb.buildOrThrow());

        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        poseStack.popPose();
    }

    private static void emitFace(BufferBuilder bb, Matrix4f model, Matrix4f view, Matrix4f projection, Direction dir) {
        float x0 = 0f, x1 = 1f, y0 = 0f, y1 = 1f, z0 = 0f, z1 = 1f;

        float[][] corners;
        switch (dir) {
            case NORTH -> corners = new float[][] { {x0,y0,z0},{x1,y0,z0},{x1,y1,z0},{x0,y1,z0} };
            case SOUTH -> corners = new float[][] { {x1,y0,z1},{x0,y0,z1},{x0,y1,z1},{x1,y1,z1} };
            case WEST  -> corners = new float[][] { {x0,y0,z1},{x0,y0,z0},{x0,y1,z0},{x0,y1,z1} };
            case EAST  -> corners = new float[][] { {x1,y0,z0},{x1,y0,z1},{x1,y1,z1},{x1,y1,z0} };
            case DOWN  -> corners = new float[][] { {x0,y0,z0},{x1,y0,z0},{x1,y0,z1},{x0,y0,z1} };
            case UP    -> corners = new float[][] { {x0,y1,z1},{x1,y1,z1},{x1,y1,z0},{x0,y1,z0} };
            default    -> { return; }
        }

        for (float[] c : corners) {
            float x = c[0], y = c[1], z = c[2];
            float[] uv = projectToUv(model, view, projection, x, y, z); // UVs from P * V * M
            bb.addVertex(model, x, y, z).setUv(uv[0], uv[1]).setColor(255, 255, 255, 255);
        }
    }

    // UVs from the same clip transform the shader uses: clip = P * V * M * v
    private static float[] projectToUv(Matrix4f model, Matrix4f view,
                                       Matrix4f proj, float x, float y, float z) {
        Vector4f v = new Vector4f(x, y, z, 1.0f);
        v.mul(model).mul(view).mul(proj);
        float w = (v.w != 0f) ? v.w : 1e-6f;
        float ndcX = v.x / w;
        float ndcY = v.y / w;
        float u = 0.5f * (ndcX + 1.0f);
        float vtex = 0.5f * (1.0f - ndcY);
        if (u < 0f) u = 0f; if (u > 1f) u = 1f;
        if (vtex < 0f) vtex = 0f; if (vtex > 1f) vtex = 1f;
        return new float[] { u, vtex };
    }
}
