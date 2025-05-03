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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.block.GramophoneBlock;
import wardentools.blockentity.GramophoneBlockEntity;
import wardentools.client.model.DiscModel;

public class GramophoneRenderer implements BlockEntityRenderer<GramophoneBlockEntity> {
    private static final DiscModel MODEL =
			new DiscModel(DiscModel.createBodyLayer().bakeRoot());
    private static final float SCALE = 0.8F;
    private static final float rotationSpeed = 1.5f;
    private float lastRot = 0f;


	public GramophoneRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull GramophoneBlockEntity gramophone,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (gramophone.getLevel() == null) return;
        BlockState block = gramophone.getLevel().getBlockState(gramophone.getBlockPos());
        if (!block.is(BlockRegistry.GRAMOPHONE.get())) {return;}
        if (block.getValue(GramophoneBlock.HALF) != DoubleBlockHalf.LOWER) {return;}
        if (!gramophone.isEmpty()) {
            this.renderDisc(gramophone, partialTick, poseStack, buffer, packedOverlay);
        }
    }

    private void renderDisc(GramophoneBlockEntity gramophone, float partialTick,
                            PoseStack poseStack, MultiBufferSource buffer, int packedOverlay) {
        Level level = gramophone.getLevel();
        if (level == null) return;
        BlockPos pos = gramophone.getBlockPos();

        poseStack.pushPose();
        poseStack.translate(0.5F, -0.43F, 0.5F);
        poseStack.scale(SCALE, SCALE, SCALE);

        float relativeGameTime = (float) level.getGameTime() + partialTick;
        if (gramophone.isPlaying()) {
            this.lastRot = rotationSpeed * relativeGameTime;
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(this.lastRot));

        this.discModelRender(gramophone.getTheItem(), poseStack, buffer, packedOverlay, pos, level);

        poseStack.popPose();
    }

    private void discModelRender(ItemStack stack , PoseStack poseStack, MultiBufferSource buffer,
                                 int packedOverlay, BlockPos pos, Level level) {
        int packedLight = LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos),
                level.getBrightness(LightLayer.SKY, pos));
        ResourceLocation texture = DiscModel.resourceForItem(stack);

        VertexConsumer baseConsumer = buffer.getBuffer(RenderType.entityCutout(texture));
        MODEL.render(poseStack, baseConsumer, packedLight, packedOverlay);
    }
}
