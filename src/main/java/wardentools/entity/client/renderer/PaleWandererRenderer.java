package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.model.PaleWanderer;
import wardentools.entity.client.renderstate.PaleWandererRenderState;
import wardentools.entity.custom.PaleWandererEntity;

public class PaleWandererRenderer extends MobRenderer<PaleWandererEntity, PaleWandererRenderState, PaleWanderer>{
	private static final ResourceLocation PALE_WANDERER_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/pale_wanderer.png");

	public PaleWandererRenderer(EntityRendererProvider.Context context) {
		super(context, new PaleWanderer(context.bakeLayer(PaleWanderer.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull PaleWandererRenderState state) {
		return PALE_WANDERER_TEXTURE;
	}
	
	@Override
	public void render(PaleWandererRenderState state, @NotNull PoseStack pMatrixStack,
					   @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (state.isBaby) {
			pMatrixStack.scale(0.5f, 0.5f, 0.5f);
		}
		super.render(state, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	public @NotNull PaleWandererRenderState createRenderState() {
		return new PaleWandererRenderState();
	}

	@Override
	public void extractRenderState(@NotNull PaleWandererEntity wanderer,
								   @NotNull PaleWandererRenderState state, float partialTick) {
		super.extractRenderState(wanderer, state, partialTick);
		state.calmAnimationState.copyFrom(wanderer.calmAnimationState);
	}
}
