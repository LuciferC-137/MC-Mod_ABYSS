package wardentools.client.rendering;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.blaze3d.vertex.VertexSorting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import org.joml.Matrix4f;
import wardentools.AbyssConfig;
import wardentools.ModMain;
import wardentools.client.AbyssDimensionSpecialEffect;
import wardentools.client.color.ColorUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Manages post-process rendering of aurora borealis in the Abyss dimension.
 */
@OnlyIn(Dist.CLIENT)
public class AuroraShaderManager {
    public static final AuroraShaderManager AURORA_MANAGER = new AuroraShaderManager();

    private static ShaderInstance auroraShader;

    private float[] skyColor = new float[]{0, 0, 0};
    private float[] targetSkyColor = new float[]{0, 0, 0};

    private static final float LERP_SPEED = 0.01F;

    private float auroraIntensity = 0F;
    private float targetAuroraIntensity = 0F;

    public AuroraShaderManager() {
    }

    public void tick() {
        if (this.skyColor[0] == 0 && this.skyColor[1] == 0 && this.skyColor[2] == 0) {
            // Initialize sky color to target on first tick
            this.skyColor = this.targetSkyColor.clone();
        } else if (Math.abs(this.skyColor[0] - this.targetSkyColor[0]) > 0.001F ||
            Math.abs(this.skyColor[1] - this.targetSkyColor[1]) > 0.001F ||
            Math.abs(this.skyColor[2] - this.targetSkyColor[2]) > 0.001F) {
            this.skyColor = ColorUtils.lerpColor(skyColor, targetSkyColor, LERP_SPEED);
        }
        if (targetAuroraIntensity == 0F) {
            this.auroraIntensity = targetAuroraIntensity;
        } else if (Math.abs(this.auroraIntensity - this.targetAuroraIntensity) > 0.001F) {
            this.auroraIntensity += (this.targetAuroraIntensity - this.auroraIntensity) * LERP_SPEED;
        }
    }

    public static void registerShader(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(
                        event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "aurora"),
                        DefaultVertexFormat.POSITION
                ),
                shader -> auroraShader = shader
        );
    }

    /**
     * Applies the aurora shader on top of the existing sky rendering.
     * Called after the base sky rendering.
     */
    public static void applyAuroraEffect(Matrix4f modelViewMatrix,
                                         Matrix4f projectionMatrix,
                                         float brightnessOverride,
                                         int mainSkyColor, float intensityFactor) {
        float brightnessFactor = brightnessOverride / AbyssDimensionSpecialEffect.BASE_BRIGHTNESS;
        AURORA_MANAGER.targetSkyColor = ColorUtils.hexToNormalizedRGB(mainSkyColor);
        AURORA_MANAGER.targetAuroraIntensity = AbyssConfig.CLIENT.AURORA_INTENSITY.get().floatValue() * intensityFactor;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        // Rendering configuration - additive mode to overlay aurora on sky
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        // Additive blend mode to layer aurora on top of existing sky
        RenderSystem.blendFunc(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE
        );
        RenderSystem.disableCull();

        // Send uniforms to shader BEFORE activating it
        if (auroraShader.getUniform("ScreenSize") != null) {
            Objects.requireNonNull(auroraShader.getUniform("ScreenSize")).set(
                    (float) mc.getMainRenderTarget().width,
                    (float) mc.getMainRenderTarget().height
            );
        }

        if (auroraShader.getUniform("Time") != null) {
            Objects.requireNonNull(auroraShader.getUniform("Time")).set(
                    (mc.level.getGameTime() + mc.getTimer().getGameTimeDeltaPartialTick(false)) / 20.0F
            );
        }

        if (auroraShader.getUniform("AuroraIntensity") != null) {
            Objects.requireNonNull(auroraShader.getUniform("AuroraIntensity"))
                    .set(AURORA_MANAGER.auroraIntensity * brightnessFactor);
        }

        if (auroraShader.getUniform("AuroraColor") != null) {
            Objects.requireNonNull(auroraShader.getUniform("AuroraColor"))
                    .set(AURORA_MANAGER.skyColor);
        }
        if (auroraShader.getUniform("AuroraSize") != null) {
            Objects.requireNonNull(auroraShader.getUniform("AuroraSize"))
                    .set(AbyssConfig.CLIENT.AURORA_SIZE.get());
        }

        // Draw dome with shader
        renderFullScreenQuad(modelViewMatrix, projectionMatrix);

        // Restore OpenGL state
        RenderSystem.enableCull();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
    }

    /**
     * Renders aurora on a 3D dome using the same transformations as renderSky.
     * This ensures aurora undergoes exactly the same transformations (FOV, bobbing, etc.)
     */
    private static void renderFullScreenQuad(Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
        // Like renderSky, we use ModelViewMatrix
        // BUT we don't transform the vertices on the Java side
        // The shader will apply ModelViewMat and ProjMat automatically

        // Configure global RenderSystem matrices
        // This is what Minecraft does implicitly for vanilla shaders
        RenderSystem.getModelViewStack().pushMatrix();
        RenderSystem.getModelViewStack().set(modelViewMatrix);
        RenderSystem.applyModelViewMatrix();

        RenderSystem.setProjectionMatrix(projectionMatrix, VertexSorting.DISTANCE_TO_ORIGIN);

        // Configure shader via RenderSystem
        RenderSystem.setShader(() -> auroraShader);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION);

        // Draw a dome (upper hemisphere) like the sky
        // Vertices are in WORLD SPACE (not transformed on Java side)
        // Shader will transform them with ModelViewMat * ProjMat
        float radius = 100.0F;
        int segments = 16; //32;
        int rings = 8; //16;

        for (int ring = 0; ring < rings; ring++) {
            float theta1 = (float) (ring * Math.PI / 2.0 / rings);
            float theta2 = (float) ((ring + 1) * Math.PI / 2.0 / rings);

            float y1 = radius * (float) Math.cos(theta1);
            float y2 = radius * (float) Math.cos(theta2);
            float r1 = radius * (float) Math.sin(theta1);
            float r2 = radius * (float) Math.sin(theta2);

            for (int seg = 0; seg < segments; seg++) {
                float phi1 = (float) (seg * 2.0 * Math.PI / segments);
                float phi2 = (float) ((seg + 1) * 2.0 * Math.PI / segments);

                float x1r1 = r1 * (float) Math.cos(phi1);
                float z1r1 = r1 * (float) Math.sin(phi1);
                float x2r1 = r1 * (float) Math.cos(phi2);
                float z2r1 = r1 * (float) Math.sin(phi2);

                float x1r2 = r2 * (float) Math.cos(phi1);
                float z1r2 = r2 * (float) Math.sin(phi1);
                float x2r2 = r2 * (float) Math.cos(phi2);
                float z2r2 = r2 * (float) Math.sin(phi2);

                // First triangle - vertices in world space (untransformed)
                buffer.addVertex(x1r1, y1, z1r1);
                buffer.addVertex(x1r2, y2, z1r2);
                buffer.addVertex(x2r1, y1, z2r1);

                // Second triangle
                buffer.addVertex(x2r1, y1, z2r1);
                buffer.addVertex(x1r2, y2, z1r2);
                buffer.addVertex(x2r2, y2, z2r2);
            }
        }

        BufferUploader.drawWithShader(buffer.buildOrThrow());

        // Restore ModelView matrix
        RenderSystem.getModelViewStack().popMatrix();
        RenderSystem.applyModelViewMatrix();
    }
}