package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.custom.ContagionIncarnationEntity;

public class ContagionIncarnationRenderer extends MobRenderer<ContagionIncarnationEntity, ContagionIncarnation>{
	private static final ResourceLocation CONTAGION_INCARNATION_TEXTURE = 
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/contagion_incarnation.png");

	public ContagionIncarnationRenderer(EntityRendererProvider.Context context) {
		super(context, new ContagionIncarnation(context.bakeLayer(ContagionIncarnation.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ContagionIncarnationEntity pEntity) {
		return CONTAGION_INCARNATION_TEXTURE;
	}
	
	@Override
	public void render(ContagionIncarnationEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
						MultiBufferSource pBuffer, int pPackedLight) {

		pMatrixStack.scale(1.5f, 1.5f, 1.5f);

		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
