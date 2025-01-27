package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.DeepLurkerEyesLayer;
import wardentools.entity.client.model.DeepLurker;
import wardentools.entity.client.renderstate.DeeplurkerRenderState;
import wardentools.entity.custom.DeepLurkerEntity;

public class DeepLurkerRenderer extends MobRenderer<DeepLurkerEntity, DeeplurkerRenderState, DeepLurker>{
	private static final ResourceLocation DEEPLURKER_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/deeplurker.png");

	public DeepLurkerRenderer(EntityRendererProvider.Context context) {
		super(context, new DeepLurker(context.bakeLayer(DeepLurker.LAYER_LOCATION)), 0.5f);
		this.addLayer(new DeepLurkerEyesLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull DeeplurkerRenderState state) {
		return DEEPLURKER_TEXTURE;
	}
	
	@Override
	public void render(DeeplurkerRenderState state,
					   @NotNull PoseStack pMatrixStack,
					   @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (state.isBaby) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}
		super.render(state, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	public @NotNull DeeplurkerRenderState createRenderState() {
		return new DeeplurkerRenderState();
	}

	@Override
	public void extractRenderState(@NotNull DeepLurkerEntity deepLurker,
								   @NotNull DeeplurkerRenderState state, float partialTicks) {
		super.extractRenderState(deepLurker, state, partialTicks);
		state.calmAnimationState.copyFrom(deepLurker.calmAnimationState);
		state.scaredAnimationState.copyFrom(deepLurker.scaredAnimationState);
	}
}
