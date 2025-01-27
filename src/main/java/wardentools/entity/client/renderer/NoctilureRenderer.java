package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.NoctilureEmissiveLayer;
import wardentools.entity.client.model.Noctilure;
import wardentools.entity.client.renderstate.NoctilureRenderState;
import wardentools.entity.custom.NoctilureEntity;

public class NoctilureRenderer extends MobRenderer<NoctilureEntity, NoctilureRenderState, Noctilure>{
	private static final ResourceLocation NOCTILURE_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/noctilure.png");

	public NoctilureRenderer(EntityRendererProvider.Context context) {
		super(context, new Noctilure(context.bakeLayer(Noctilure.LAYER_LOCATION)), 1f);
		this.addLayer(new NoctilureEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull NoctilureRenderState state) {
		return NOCTILURE_TEXTURE;
	}
	
	@Override
	public void render(@NotNull NoctilureRenderState state,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(state, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	public @NotNull NoctilureRenderState createRenderState() {
		return new NoctilureRenderState();
	}

	@Override
	public void extractRenderState(@NotNull NoctilureEntity noctilure,
								   @NotNull NoctilureRenderState state, float partialTick) {
		super.extractRenderState(noctilure, state, partialTick);
		state.isFlightSprinting = noctilure.getFlightSprinting();
		state.speed = (float)noctilure.getDeltaMovement().horizontalDistance();
		state.isFreeFalling = !noctilure.getIsFlying() && noctilure.getDeltaMovement().y() < -0.1;
		state.standing.copyFrom(noctilure.standing);
		state.walking.copyFrom(noctilure.walking);
		state.flying.copyFrom(noctilure.flying);
		state.idleFlying.copyFrom(noctilure.idleFlying);
		state.idleFlyToFly.copyFrom(noctilure.idleFlyToFly);
		state.landing.copyFrom(noctilure.landing);
	}

	@Override
	protected void scale(@NotNull NoctilureRenderState state, @NotNull PoseStack stack) {
		stack.scale(1.3f, 1.3f, 1.3f);
		if (state.isBaby) {
			stack.scale(0.6f, 0.6f, 0.6f);
		}
	}
}
