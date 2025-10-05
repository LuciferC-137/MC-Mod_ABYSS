package wardentools.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import wardentools.ModMain;
import wardentools.misc.Star;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class LevelRendererUtils {
    public static final ResourceLocation STAR_0 = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_0.png");
    public static final ResourceLocation STAR_1 = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_1.png");
    public static final ResourceLocation STAR_2 = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_2.png");
    public static final ResourceLocation STAR_3 = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_3.png");
    public static final ResourceLocation STAR_4 = ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "textures/environment/star_4.png");
    public static final List<ResourceLocation> STAR_TEXTURES
            = List.of(STAR_0, STAR_1, STAR_2, STAR_3, STAR_4);
    public static final Vec3i SKYCOLOR = new Vec3i(0, 75, 85);

    /**
     * Renders stars in the sky for the custom dimension.
     *
     * @param level           The current client level.
     * @param rand            Random source for star placement. Seed should be fixed.
     * @param posestack       The pose stack for transformations.
     * @param starCount       Number of stars to render.
     * @param rotationAxis    List of axes for star rotation.
     * @param rotationSpeeds  Rotation speeds for each axis. In rotation per minecraft day.
     * @param averageSize     Average size of each star.
     */
    public static void renderStars(ClientLevel level, Tesselator tesselator, RandomSource rand,
                                    PoseStack posestack, int starCount, List<Axis> rotationAxis,
                                    float[] rotationSpeeds, float averageSize) {
        posestack.pushPose();
        for (int i = 0; i < rotationAxis.size(); i++) {
            posestack.mulPose(rotationAxis.get(i).rotationDegrees(
                    level.getGameTime() * rotationSpeeds[i]
                            / 20.0F / 60.0F / 20.0F * 360.0F));
        }

        Matrix4f mat = posestack.last().pose();

        float minLen2 = 0.010000001F;    // vanilla
        float maxLen2 = 1.0F;
        float radius = 140.0F;           // projection radius

        // Map texture -> corresponding star
        Map<ResourceLocation, List<Star>> starsByTex = new HashMap<>();
        for (ResourceLocation tex : STAR_TEXTURES) {
            starsByTex.put(tex, new ArrayList<>());
        }

        int placed = 0;
        while (placed < starCount) {
            ResourceLocation tex = STAR_TEXTURES.get(rand.nextInt(STAR_TEXTURES.size()));

            // Uniform distribution (vanilla)
            float f1 = rand.nextFloat() * 2.0F - 1.0F;
            float f2 = rand.nextFloat() * 2.0F - 1.0F;
            float f3 = rand.nextFloat() * 2.0F - 1.0F;
            float len2 = Mth.lengthSquared(f1, f2, f3);
            if (len2 <= minLen2 || len2 >= maxLen2) continue;

            Vector3f pos = new Vector3f(f1, f2, f3).normalize(radius);

            float size = averageSize * (rand.nextFloat() + 0.5F);
            float rot = (float)(rand.nextDouble() * Math.PI * 2.0);

            starsByTex.get(tex).add(new Star(pos, size, rot));
            placed++;
        }

        // Rendering by texture to minimize texture switches
        for (ResourceLocation tex : STAR_TEXTURES) {
            List<Star> stars = starsByTex.get(tex);
            if (stars.isEmpty()) continue;

            RenderSystem.setShaderTexture(0, tex);
            BufferBuilder starBuf = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

            for (Star star : stars) {
                Quaternionf q = new Quaternionf()
                        .rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), new Vector3f(star.pos()))
                        .rotateZ(star.rot());

                Vector3f c0 = new Vector3f( star.size(), -star.size(), 0.0F).rotate(q).add(star.pos()); // (1,0)
                Vector3f c1 = new Vector3f( star.size(),  star.size(), 0.0F).rotate(q).add(star.pos()); // (1,1)
                Vector3f c2 = new Vector3f(-star.size(),  star.size(), 0.0F).rotate(q).add(star.pos()); // (0,1)
                Vector3f c3 = new Vector3f(-star.size(), -star.size(), 0.0F).rotate(q).add(star.pos()); // (0,0)

                starBuf.addVertex(mat, c0.x(), c0.y(), c0.z()).setUv(1.0F, 0.0F);
                starBuf.addVertex(mat, c1.x(), c1.y(), c1.z()).setUv(1.0F, 1.0F);
                starBuf.addVertex(mat, c2.x(), c2.y(), c2.z()).setUv(0.0F, 1.0F);
                starBuf.addVertex(mat, c3.x(), c3.y(), c3.z()).setUv(0.0F, 0.0F);
            }

            BufferUploader.drawWithShader(starBuf.buildOrThrow());
        }

        posestack.popPose();
    }

}
