package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.PaleWandererEntity;

@OnlyIn(Dist.CLIENT)
public class PaleWandererRenderer extends MobRenderer<PaleWandererEntity, PaleWanderer>{
	private static final ResourceLocation PALE_WANDERER_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/pale_wanderer.png");

	public PaleWandererRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleWanderer(context.bakeLayer(PaleWanderer.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull PaleWandererEntity pEntity) {
		return PALE_WANDERER_TEXTURE;
	}
	
	@Override
	public void render(PaleWandererEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
					   @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (pEntity.isBaby()) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}	
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
