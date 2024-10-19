package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.NoctilureEntity;

public class NoctilureRenderer extends MobRenderer<NoctilureEntity, Noctilure>{
	private static final ResourceLocation NOCTILURE_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/noctilure.png");

	public NoctilureRenderer(EntityRendererProvider.Context context) {
		super(context, new Noctilure(context.bakeLayer(Noctilure.LAYER_LOCATION)), 1f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull NoctilureEntity pEntity) {
		return NOCTILURE_TEXTURE;
	}
	
	@Override
	public void render(@NotNull NoctilureEntity noctilure, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		pMatrixStack.scale(1.3f, 1.3f, 1.3f);
		if (noctilure.isBaby()) {
			pMatrixStack.scale(0.6f, 0.6f, 0.6f);
		}
		super.render(noctilure, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
