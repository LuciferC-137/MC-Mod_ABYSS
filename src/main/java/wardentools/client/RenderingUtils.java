package wardentools.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderingUtils {
    public static final RenderStateShard.ShaderStateShard RENDERTYPE_LIGHTNING_SHADER
            = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLightningShader);
    public static final RenderStateShard.WriteMaskStateShard COLOR_DEPTH_WRITE
            = new RenderStateShard.WriteMaskStateShard(true, true);
    public static final RenderStateShard.TransparencyStateShard LIGHTNING_TRANSPARENCY
            = new RenderStateShard.TransparencyStateShard("lightning_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    public static final RenderStateShard.CullStateShard NO_CULL = new RenderStateShard.CullStateShard(false);
    public static final RenderStateShard.OutputStateShard WEATHER_TARGET
            = new RenderStateShard.OutputStateShard("weather_target", () -> {
        if (Minecraft.useShaderTransparency()) {
            Objects.requireNonNull(Minecraft.getInstance().levelRenderer.getWeatherTarget()).bindWrite(false);
        }
    }, () -> {
        if (Minecraft.useShaderTransparency()) {
            Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        }
    });
    public static final RenderStateShard.LayeringStateShard NO_FOG_LAYERING
            = new RenderStateShard.LayeringStateShard("no_fog", FogRenderer::setupNoFog, () -> {  });


    public static final RenderType LIGHTNING = RenderType.create("lightning",
            DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS,
            1536, false, true,
            RenderType.CompositeState.builder().setShaderState(RENDERTYPE_LIGHTNING_SHADER)
                    .setWriteMaskState(COLOR_DEPTH_WRITE)
                    .setTransparencyState(LIGHTNING_TRANSPARENCY)
                    .setOutputState(WEATHER_TARGET)
                    .setLayeringState(NO_FOG_LAYERING)
                    .setCullState(NO_CULL)
                    .createCompositeState(false));

    public static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public static final RenderType COLORED_CUBE = RenderType.create(
            "colored_cube",
            DefaultVertexFormat.POSITION_COLOR_NORMAL,
            VertexFormat.Mode.QUADS,
            256,
            false,  // no culling
            true,            // needs sorting because translucent
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getPositionColorShader))
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY) // pour alpha
                    .setDepthTestState( new RenderStateShard.DepthTestStateShard("<=", 515))
                    .setCullState(new RenderStateShard.CullStateShard(false))
                    .createCompositeState(true)
    );
}
