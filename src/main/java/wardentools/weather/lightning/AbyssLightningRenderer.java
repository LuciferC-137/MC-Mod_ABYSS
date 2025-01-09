package wardentools.weather.lightning;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.lang.reflect.Type;

@OnlyIn(Dist.CLIENT)
public class AbyssLightningRenderer extends EntityRenderer<AbyssLightningEntity> {
    private static final float RED = 0.1f;
    private static final float GREEN = 0.7f;
    private static final float BLUE = 0.9f;
    private static final float OPACITY = 1.f;

    public AbyssLightningRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    public void render(AbyssLightningEntity lightningEntity, float yaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (lightningEntity.isLegacyLightning()) {
            renderLegacyLightning(lightningEntity, poseStack, bufferSource);
        } else {
            drawAbyssLightning(lightningEntity, poseStack, bufferSource);
        }
    }

    private void drawAbyssLightning(AbyssLightningEntity lightningEntity, PoseStack poseStack,
                                    MultiBufferSource bufferSource){
        RandomSource randomGenerator = RandomSource.create(lightningEntity.seed);
        float length = 10f;
        float thickness = length / 15f;
        int depth = 4;
        int childrenNumber = randomGenerator.nextInt(2, 5);
        for (int i = 0; i < childrenNumber; i++) {
            float angleChild = (randomGenerator.nextFloat() - 0.5f) * (float) Math.PI  * 4f;
            float lengthChild = length * (0.4f + randomGenerator.nextFloat() * 0.4f);
            float thicknessChild = thickness * (lengthChild / length);
            float xChild =  lengthChild / 2f * (float) Math.cos(angleChild);
            float zChild = lengthChild / 2f * (float) Math.sin(angleChild);
            drawRecursiveLightning(poseStack.last().pose(),
                    bufferSource.getBuffer(RenderType.lightning()), randomGenerator,
                    xChild, zChild, lengthChild, thicknessChild, angleChild, depth);
        }
    }

    private void drawRecursiveLightning(Matrix4f matrix, VertexConsumer vertexConsumer, RandomSource randomGenerator,
                                        float startX, float startZ, float length,
                                        float thickness, float angle, int depth) {
        drawAngledRectangle(matrix, vertexConsumer, startX, startZ, length, thickness, angle, RED, GREEN, BLUE);
        if (depth == 0) return;
        float endX = startX + length / 2f * (float) Math.cos(angle);
        float endZ = startZ + length  / 2f * (float) Math.sin(angle);
        int childrenNumber = randomGenerator.nextInt(2, 5);
        for (int i = 0; i < childrenNumber; i++) {
            float angleChild = angle + (randomGenerator.nextFloat() - 0.5f) * (float) Math.PI / 2f;
            float lengthChild = length * (0.4f + randomGenerator.nextFloat() * 0.4f);
            float thicknessChild = thickness * (lengthChild / length);
            float xChild = endX + lengthChild / 2f * (float) Math.cos(angleChild);
            float zChild = endZ + lengthChild / 2f * (float) Math.sin(angleChild);
            drawRecursiveLightning(matrix, vertexConsumer, randomGenerator,
                    xChild, zChild, lengthChild, thicknessChild, angleChild, depth - 1);
        }
    }

    private void drawAngledRectangle(Matrix4f matrix, VertexConsumer vertexConsumer,
                                     float centerX, float centerZ, float length, float thickness, float angle,
                                     float red, float green, float blue) {
        // Draws a rectangle at the coordinates centerX, centerZ (as an offset respectively to the entity position)
        // Length is the length on the X coordinate.
        // Thickness is the length on the Z coordinate.
        // The angle turns the rectangle on the Y axis.
        // Therefore, all rectangles drawn with these methods are horizontals.
        float halfLength = length / 2.0F;
        float halfThickness = thickness / 2.0F;
        float cosAngle = (float) Math.cos(angle);
        float sinAngle = (float) Math.sin(angle);
        float[] xOffsets = {
                -halfLength * cosAngle - halfThickness * sinAngle,
                halfLength * cosAngle - halfThickness * sinAngle,
                halfLength * cosAngle + halfThickness * sinAngle,
                -halfLength * cosAngle + halfThickness * sinAngle
        };
        float[] zOffsets = {
                -halfLength * sinAngle + halfThickness * cosAngle,
                halfLength * sinAngle + halfThickness * cosAngle,
                halfLength * sinAngle - halfThickness * cosAngle,
                -halfLength * sinAngle - halfThickness * cosAngle
        };
        for (int i = 0; i < 4; i++) {
            int next = (i + 1) % 4;
            vertexConsumer.vertex(matrix, centerX + xOffsets[i], 0.0F,
                            centerZ + zOffsets[i]).color(red, green, blue, OPACITY).endVertex();
            vertexConsumer.vertex(matrix, centerX + xOffsets[next], 0.0F,
                            centerZ + zOffsets[next]).color(red, green, blue, OPACITY).endVertex();
        }
    }

    private void renderLegacyLightning(AbyssLightningEntity lightningEntity,
                                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource) {
        float[] xOffsets = new float[8];
        float[] zOffsets = new float[8];
        float previousXOffset = 0.0F;
        float previousZOffset = 0.0F;
        RandomSource randomGenerator = RandomSource.create(lightningEntity.seed);
        for (int segment = 7; segment >= 0; --segment) {
            xOffsets[segment] = previousXOffset;
            zOffsets[segment] = previousZOffset;
            previousXOffset += (float)(randomGenerator.nextInt(11) - 5);
            previousZOffset += (float)(randomGenerator.nextInt(11) - 5);
        }
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lightning());
        Matrix4f transformationMatrix = poseStack.last().pose();
        for (int branch = 0; branch < 4; ++branch) {
            RandomSource branchRandomGenerator = RandomSource.create(lightningEntity.seed);
            for (int pass = 0; pass < 3; ++pass) {
                int startSegment = 7;
                int endSegment = 0;
                if (pass > 0) startSegment = 7 - pass;
                if (pass > 0) endSegment = startSegment - 2;
                float currentXOffset = xOffsets[startSegment] - previousXOffset;
                float currentZOffset = zOffsets[startSegment] - previousZOffset;
                for (int segment = startSegment; segment >= endSegment; --segment) {
                    float nextXOffset = currentXOffset;
                    float nextZOffset = currentZOffset;
                    if (pass == 0) {
                        currentXOffset += (float)(branchRandomGenerator.nextInt(11) - 5);
                        currentZOffset += (float)(branchRandomGenerator.nextInt(11) - 5);
                    } else {
                        currentXOffset += (float)(branchRandomGenerator.nextInt(31) - 15);
                        currentZOffset += (float)(branchRandomGenerator.nextInt(31) - 15);
                    }
                    float red = RED;
                    float green = GREEN;
                    float blue = BLUE;
                    float thicknessStart = 0.1F + (float)branch * 0.2F;
                    if (pass == 0) {
                        thicknessStart *= (float)segment * 0.1F + 1.0F;
                    }
                    float thicknessEnd = 0.1F + (float)branch * 0.2F;
                    if (pass == 0) {
                        thicknessEnd *= ((float)segment - 1.0F) * 0.1F + 1.0F;
                    }
                    drawQuad(transformationMatrix, vertexConsumer, currentXOffset, currentZOffset, segment, nextXOffset, nextZOffset,
                            red, green, blue, thicknessStart, thicknessEnd, false, false, true, false);
                    drawQuad(transformationMatrix, vertexConsumer, currentXOffset, currentZOffset, segment, nextXOffset, nextZOffset,
                            red, green, blue, thicknessStart, thicknessEnd, true, false, true, true);
                    drawQuad(transformationMatrix, vertexConsumer, currentXOffset, currentZOffset, segment, nextXOffset, nextZOffset,
                            red, green, blue, thicknessStart, thicknessEnd, true, true, false, true);
                    drawQuad(transformationMatrix, vertexConsumer, currentXOffset, currentZOffset, segment, nextXOffset, nextZOffset,
                            red, green, blue, thicknessStart, thicknessEnd, false, true, false, false);
                }
            }
        }
    }

    private static void drawQuad(Matrix4f matrix, VertexConsumer vertexConsumer, float startX, float startZ, int segment,
                                 float endX, float endZ, float red, float green, float blue, float thicknessStart, float thicknessEnd,
                                 boolean flipXStart, boolean flipZStart, boolean flipXEnd, boolean flipZEnd) {
        vertexConsumer.vertex(matrix, startX + (flipXStart ? thicknessEnd : -thicknessEnd), (float)(segment * 16), startZ + (flipZStart ? thicknessEnd : -thicknessEnd))
                .color(red, green, blue, OPACITY).endVertex();
        vertexConsumer.vertex(matrix, endX + (flipXStart ? thicknessStart : -thicknessStart), (float)((segment + 1) * 16), endZ + (flipZStart ? thicknessStart : -thicknessStart))
                .color(red, green, blue, OPACITY).endVertex();
        vertexConsumer.vertex(matrix, endX + (flipXEnd ? thicknessStart : -thicknessStart), (float)((segment + 1) * 16), endZ + (flipZEnd ? thicknessStart : -thicknessStart))
                .color(red, green, blue, OPACITY).endVertex();
        vertexConsumer.vertex(matrix, startX + (flipXEnd ? thicknessEnd : -thicknessEnd), (float)(segment * 16), startZ + (flipZEnd ? thicknessEnd : -thicknessEnd))
                .color(red, green, blue, OPACITY).endVertex();
    }


    @SuppressWarnings("deprecation")
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbyssLightningEntity lightning) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
