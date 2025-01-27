package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.model.ContagionIncarnationCorpse;
import wardentools.entity.client.renderstate.ContagionIncarnationCorpseRenderState;
import wardentools.entity.custom.ContagionIncarnationCorpseEntity;

public class ContagionIncarnationCorpseRenderer
		extends LivingEntityRenderer<ContagionIncarnationCorpseEntity,
		ContagionIncarnationCorpseRenderState, ContagionIncarnationCorpse> {
	private static final ResourceLocation CONTAGION_INCARNATION_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/contagion_incarnation_corpse.png");

	public ContagionIncarnationCorpseRenderer(EntityRendererProvider.Context context) {
		super(context, new ContagionIncarnationCorpse(context.bakeLayer(
				ContagionIncarnationCorpse.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ContagionIncarnationCorpseRenderState state) {
		return CONTAGION_INCARNATION_TEXTURE;
	}

	@Override
	public void render(@NotNull ContagionIncarnationCorpseRenderState state,
					   @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource bufferSource, int i) {
		super.render(state, poseStack, bufferSource, i);
	}

	@Override
	protected void scale(@NotNull ContagionIncarnationCorpseRenderState state,
						 @NotNull PoseStack stack) {
		stack.scale(2f, 2f, 2f);
	}

	@Override
	protected boolean shouldShowName(@NotNull ContagionIncarnationCorpseEntity entity, double v) {
		return false;
	}

	@Override
	public @NotNull ContagionIncarnationCorpseRenderState createRenderState() {
		return new ContagionIncarnationCorpseRenderState();
	}
}
