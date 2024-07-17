package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import wardentools.blockentity.RadianceCatalystBlockEntity;
import wardentools.items.ItemRegistry;

public class RadianceCatalystRenderer implements BlockEntityRenderer<RadianceCatalystBlockEntity> {
	private final BlockEntityRendererProvider.Context context;
	
	public RadianceCatalystRenderer(BlockEntityRendererProvider.Context ctx) {
		this.context = ctx;
	}

	@Override
	public void render(RadianceCatalystBlockEntity blockEntity, float partialTick, PoseStack poseStack,
			MultiBufferSource buffer, int packedLight, int packedOverlay) {
		ItemStack stack = new ItemStack(ItemRegistry.RADIANCE_CATALYST_INTERIOR.get());
		if (stack.isEmpty()) return;
		
		Level level = blockEntity.getLevel();
		if (level==null) return;
		
		BlockPos pos = blockEntity.getBlockPos();
		
		double relativeGameTime = level.getGameTime() + partialTick;
		double offset = 0.0;
		double rotation = relativeGameTime * 3.0;
		double scale = 1.0;
		
		poseStack.pushPose();
		poseStack.translate(0.5, 0.5 + offset, 0.5);
		poseStack.scale((float) scale, (float) scale, (float) scale);
		poseStack.mulPose(Axis.YP.rotationDegrees((float)rotation));
		poseStack.mulPose(Axis.XP.rotationDegrees((float)rotation));
		
		this.context.getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED,
				LightTexture.pack(
						level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos)),
				packedOverlay, poseStack, buffer, level, 0);
		poseStack.popPose();	
	}

}
