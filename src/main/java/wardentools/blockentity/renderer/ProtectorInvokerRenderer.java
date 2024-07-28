package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import wardentools.items.ItemRegistry;

public class ProtectorInvokerRenderer implements BlockEntityRenderer<ProtectorInvokerBlockEntity> {
	private final BlockEntityRendererProvider.Context context;
	private boolean sentParticle = false;
	
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
	        
	        poseStack.popPose();
	        
	        @SuppressWarnings("resource")
			Player player = Minecraft.getInstance().player;
            if (player != null) {
            	int packedLight = LightTexture.pack(
    	                level.getBrightness(LightLayer.BLOCK, pos),
    	                level.getBrightness(LightLayer.SKY, pos)
    	        );
                Vec3 playerPos = player.position();
                double dx = playerPos.x - (pos.getX() + 0.5);
                double dz = playerPos.z - (pos.getZ() + 0.5);
                float scaleTxt = 0.015f;

                poseStack.pushPose();
                poseStack.translate(0.5, offset + 0.35, 0.5);
                poseStack.scale(scaleTxt, -scaleTxt, scaleTxt);
                if (dz<0) {
                	poseStack.mulPose(Axis.YP.rotation(3.1415f + (float)Math.atan(dx/dz)));
                } else {
                	poseStack.mulPose(Axis.YP.rotation((float)Math.atan(dx/dz)));
                }
                Font font = this.context.getFont();
                String healthText = blockEntity.healthText();
                poseStack.translate(-font.width(healthText) / 2.0f, 0, 0);
                
                font.drawInBatch(
                		healthText,
                        0,
                        0,
                        0xECECEC,
                        false,
                        poseStack.last().pose(),
                        buffer,
                        Font.DisplayMode.NORMAL,
                        0,
                        packedLight
                );
                poseStack.popPose();
            }
	        
            if (stack.is(ItemRegistry.PROTECTOR_HEART.get())) {
            	if (level.getGameTime()%30 == 0 && !this.sentParticle) {
    	        	this.sentParticle = true;
    	        	double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
    	            double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
    	            double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
    		        level.addParticle(ParticleTypes.END_ROD, true,
    		        		pos.getX() + 0.5, pos.getY() - offset + 1.0, pos.getZ() + 0.5, offsetX, offsetY, offsetZ);
    	        } else {
    	        	this.sentParticle = false;
    	        }
            } else {
            	if (level.getGameTime()%25 == 0 && !this.sentParticle) {
    	        	this.sentParticle = true;
    	        	double offsetX = (level.random.nextDouble() - 0.5) * 0.15;
    	            double offsetY = (level.random.nextDouble() - 0.5) * 0.15;
    	            double offsetZ = (level.random.nextDouble() - 0.5) * 0.15;
    		        level.addParticle(ParticleTypes.CRIMSON_SPORE, true,
    		        		pos.getX() + 0.5, pos.getY() - offset + 1.0, pos.getZ() + 0.5, offsetX, offsetY, offsetZ);
    	        } else {
    	        	this.sentParticle = false;
    	        }
            }
        }
		
	}

}
