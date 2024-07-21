package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import wardentools.blockentity.ProtectorInvokerBlockEntity;

public class ProtectorInvokerRenderer implements BlockEntityRenderer<ProtectorInvokerBlockEntity> {
	private final BlockEntityRendererProvider.Context context;
	
	public ProtectorInvokerRenderer(BlockEntityRendererProvider.Context ctx) {
		this.context = ctx;
	}

	@Override
    public void render(ProtectorInvokerBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packLight, int packedOverlay) {
		ItemStack stack = blockEntity.getInventory().getStackInSlot(0);
        if(stack.isEmpty())
            return;

        Level level = blockEntity.getLevel();
        if(level == null)
            return;

        BlockPos pos = blockEntity.getBlockPos().above();

        int packedLight = LightTexture.pack(
                level.getBrightness(LightLayer.BLOCK, pos),
                level.getBrightness(LightLayer.SKY, pos)
        );

        double relativeGameTime = level.getGameTime() + partialTick;
        double offset = 0.0;
        double rotation = relativeGameTime / 2.0;
        double scale = 0.35;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.95 + offset, 0.5);
        poseStack.scale((float) scale, (float) scale, (float) scale);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) rotation));
        this.context.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                level,
                0
        );
        
        poseStack.popPose();
		
	}

}
