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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.ContagionIncarnationSkullBlockEntity;
import wardentools.client.model.ContagionIncarnationSkull;

public class ContagionIncarnationSkullRenderer implements BlockEntityRenderer<ContagionIncarnationSkullBlockEntity> {
    private static final ContagionIncarnationSkull model =
			new ContagionIncarnationSkull(ContagionIncarnationSkull.createBodyLayer().bakeRoot());
    private static final ResourceLocation INTERIOR_TEXTURE
            = new ResourceLocation(ModMain.MOD_ID, "textures/models/contagion_incarnation_corpse_skull.png");

	public ContagionIncarnationSkullRenderer(BlockEntityRendererProvider.Context ctx) {
	}
	
	@Override
    public void render(@NotNull ContagionIncarnationSkullBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {

        Level level = blockEntity.getLevel();
        if (level == null) return;
        if (!level.getBlockState(blockEntity.getBlockPos()).is(BlockRegistry.CONTAGION_INCARNATION_SKULL.get())){
            return;
        }
        renderModel(blockEntity, poseStack, level, buffer, packedOverlay);
    }

    private static void renderModel(ContagionIncarnationSkullBlockEntity skull,
                                    PoseStack poseStack, Level level,
                                    MultiBufferSource buffer, int packedOverlay){
        BlockPos pos = skull.getBlockPos();
        BlockState state = level.getBlockState(pos);
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        switch (state.getValue(wardentools.block.ContagionIncarnationSkullBlock.FACING)) {
            case NORTH:
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                break;
            case EAST:
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
                break;
            case SOUTH:
                poseStack.mulPose(Axis.YP.rotationDegrees(0));
                break;
            case WEST:
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
                break;
            default:
                break;
        }

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(INTERIOR_TEXTURE));

        model.render(poseStack, vertexConsumer, LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos)),
                packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}
