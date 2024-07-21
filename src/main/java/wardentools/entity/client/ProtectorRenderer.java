package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.ProtectorEntity;

public class ProtectorRenderer extends MobRenderer<ProtectorEntity, Protector>{
	private static final ResourceLocation PROTECTOR_TEXTURE = 
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/protector.png");

	public ProtectorRenderer(EntityRendererProvider.Context context) {
		super(context, new Protector(context.bakeLayer(Protector.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ProtectorEntity pEntity) {
		return PROTECTOR_TEXTURE;
	}
	
	@Override
	public void render(ProtectorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
						MultiBufferSource pBuffer, int pPackedLight) {
		if (pEntity.isBaby()) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}	
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
