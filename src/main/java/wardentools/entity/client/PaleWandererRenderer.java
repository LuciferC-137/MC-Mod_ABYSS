package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.PaleWandererEntity;

public class PaleWandererRenderer extends MobRenderer<PaleWandererEntity, PaleWanderer>{
	private static final ResourceLocation PALE_WANDERER_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/pale_wanderer.png");

	public PaleWandererRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleWanderer(context.bakeLayer(PaleWanderer.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PaleWandererEntity pEntity) {
		return PALE_WANDERER_TEXTURE;
	}
	
	@Override
	public void render(PaleWandererEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
						MultiBufferSource pBuffer, int pPackedLight) {
		if (pEntity.isBaby()) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}	
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
