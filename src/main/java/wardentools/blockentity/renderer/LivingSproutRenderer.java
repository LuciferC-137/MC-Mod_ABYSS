package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.blockentity.LivingSproutBlockEntity;
import wardentools.client.model.LivingSproutCoreModel;

public class LivingSproutRenderer implements BlockEntityRenderer<LivingSproutBlockEntity> {
    private static final LivingSproutCoreModel model =
			new LivingSproutCoreModel(LivingSproutCoreModel.createBodyLayer().bakeRoot());
    private static final ResourceLocation CORE_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/models/living_sprout_core.png");

    public static final float sizeAtBeat = 0.5F;
    public static final float sizeBetweenBeats = 0.375F;

    public static final int HEART_BEAT_DURATION = 216; // Total duration of the sound
    public static final int[] BEAT_TICKS = new int[]{
            11, 16, 25, 30, 40, 45, 55,
            60, 69, 73, 83, 88, 99, 105, 117, 122, 136,
            141, 155, 163, 180, 187, 204, 121
    }; // Ticks at which beats occur

	public LivingSproutRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull LivingSproutBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {

        Level level = blockEntity.getLevel();
        if (level == null) return;
        renderInterior(blockEntity.getBlockPos(), poseStack, level, partialTick, buffer, packedOverlay);
    }


    private static void renderInterior(BlockPos pos, PoseStack poseStack, Level level, float partialTick,
                                       MultiBufferSource buffer, int packedOverlay){

        if (level.getBlockEntity(pos) instanceof LivingSproutBlockEntity sprout) {
            if (sprout.isPulsing()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 0.5625F, 0.5F);
                poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
                float scale = 3.0F * getSizeAtTick(sprout.getLastTimeHeard(), (int)level.getGameTime(), partialTick);
                poseStack.scale(scale, scale, scale);
                VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(CORE_TEXTURE));

                model.render(poseStack, vertexConsumer,
                        LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos),
                                level.getBrightness(LightLayer.SKY, pos)),
                        packedOverlay);

                poseStack.popPose();
            }
        }
    }

    private static float getSizeAtTick(int startTick, int tick, float partialTick) {
        // Calculate the current tick relative to when the heartbeat started
        float currentTick = (tick - startTick) + partialTick;

        // Handle the case before the heartbeat starts (tick 0) and after it ends
        if (currentTick <= 0 || currentTick >= HEART_BEAT_DURATION + 10) {
            return sizeBetweenBeats;
        }

        // Find the closest beat tick and calculate distance
        float closestDistance = Float.MAX_VALUE;
        for (int beatTick : BEAT_TICKS) {
            float distance = Math.abs(currentTick - beatTick);
            if (distance < closestDistance) {
                closestDistance = distance;
            }
        }

        // Define the transition range (how many ticks around a beat to interpolate)
        float transitionRange = 2.0F;

        if (closestDistance <= transitionRange) {
            // Calculate interpolation factor (0 = far from beat, 1 = on beat)
            float factor = 1.0F - (closestDistance / transitionRange);

            // Smooth the transition using a sine wave for more natural animation
            factor = (float) (Math.sin(factor * Math.PI * 0.5));

            return sizeBetweenBeats + (sizeAtBeat - sizeBetweenBeats) * factor;
        }
        return sizeBetweenBeats;
    }
}
