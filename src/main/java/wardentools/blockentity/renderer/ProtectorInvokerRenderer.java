package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
        if(!stack.isEmpty()) {
            
	        Level level = blockEntity.getLevel();
	        if(level == null)
	            return;
	
	        BlockPos pos = blockEntity.getBlockPos().above();
	
	        double relativeGameTime = level.getGameTime() + partialTick;
	        double offset = 0.95;
	        double rotation = relativeGameTime / 0.8;
	        double scale = 0.5;
	
	        poseStack.pushPose();
	        poseStack.translate(0.5, offset, 0.5);
	        poseStack.scale((float) scale, (float) scale, (float) scale);
	        poseStack.mulPose(Axis.YP.rotationDegrees((float) rotation));
	        this.context.getItemRenderer().renderStatic(
	                stack,
	                ItemDisplayContext.FIXED,
	                0xFF,
	                OverlayTexture.WHITE_OVERLAY_V,
	                poseStack,
	                buffer,
	                level,
	                0
	        );
	        
	        if (level.getGameTime()%30 == 0) {
	        	double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
	            double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
	            double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
		        level.addParticle(ParticleTypes.END_ROD, true,
		        		pos.getX() + 0.5, pos.getY() - offset + 1.0, pos.getZ() + 0.5, offsetX, offsetY, offsetZ);
	        }
	        
	        poseStack.popPose();
        }
		
	}

}
