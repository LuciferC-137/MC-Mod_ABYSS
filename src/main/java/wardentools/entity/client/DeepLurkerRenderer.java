package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.DeepLurkerEntity;

public class DeepLurkerRenderer extends MobRenderer<DeepLurkerEntity, DeepLurker>{
	private static final ResourceLocation DEEPLURKER_TEXTURE = 
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/deeplurker.png");

	public DeepLurkerRenderer(EntityRendererProvider.Context context) {
		super(context, new DeepLurker(context.bakeLayer(DeepLurker.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(DeepLurkerEntity pEntity) {
		return DEEPLURKER_TEXTURE;
	}
	
	//NOT NECESSARY: TO HAVE BABY SIZE
	@Override
	public void render(DeepLurkerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
						MultiBufferSource pBuffer, int pPackedLight) {
		if (pEntity.isBaby()) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}	
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
