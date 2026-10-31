package wardentools.client.rendering;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class AbyssPortalBuilder {

    public static final AbyssPortalBuilder INSTANCE = new AbyssPortalBuilder();

    private RenderTarget skyTarget;
    private long lastGameTime = -1;
    private int lastPartialKey = -1;

    private AbyssPortalBuilder() {}

    public void updateSkyTexture(ClientLevel level, Camera camera, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();

        long gt = level.getGameTime();
        int pk = (int)(partialTicks * 1000.0f);
        if (gt == lastGameTime && pk == lastPartialKey) return;
        lastGameTime = gt;
        lastPartialKey = pk;

        if (skyTarget == null) {
            skyTarget = new TextureTarget(mc.getWindow().getWidth(), mc.getWindow().getHeight(), true, Minecraft.ON_OSX);
            skyTarget.setClearColor(0f, 0f, 0f, 1f);
        } else if (skyTarget.width != mc.getWindow().getWidth() || skyTarget.height != mc.getWindow().getHeight()) {
            skyTarget.resize(mc.getWindow().getWidth(), mc.getWindow().getHeight(), Minecraft.ON_OSX);
        }

        try {
            skyTarget.bindWrite(true);
            RenderSystem.clearColor(0f, 0f, 0f, 1f);
            RenderSystem.clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT |
                    GL11.GL_STENCIL_BUFFER_BIT, Minecraft.ON_OSX);

            RenderSystem.colorMask(true, true, true, false);

            PoseStack skyStack = new PoseStack();
            skyStack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
            skyStack.mulPose(Axis.YP.rotationDegrees(camera.getYRot() + 180.0F));
            LevelRendererUtils.renderSky(level, 255, skyStack);
        } finally {
            RenderSystem.colorMask(true, true, true, true);
            mc.getMainRenderTarget().bindWrite(true);
        }
    }

    public int getSkyTextureId() {
        return skyTarget != null ? skyTarget.getColorTextureId() : 0;
    }

}
