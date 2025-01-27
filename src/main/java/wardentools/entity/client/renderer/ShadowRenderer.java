package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.ShadowEmissiveLayer;
import wardentools.entity.client.model.Shadow;
import wardentools.entity.client.renderstate.ShadowRenderState;
import wardentools.entity.custom.ShadowEntity;

public class ShadowRenderer extends MobRenderer<ShadowEntity, ShadowRenderState, Shadow>{
	private static final ResourceLocation HUMANOID_SHADOW_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/shadow.png");
	private static final ResourceLocation GENERIC_SHADOW_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/generic_shadow.png");

    public ShadowRenderer(EntityRendererProvider.Context context) {
		super(context, new Shadow(context.bakeLayer(Shadow.LAYER_LOCATION)), 0.2f);
		this.addLayer(new ShadowEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ShadowRenderState state) {
		return state.mimicEntity == null ? HUMANOID_SHADOW_TEXTURE : GENERIC_SHADOW_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ShadowRenderState state,
					   @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int packedLight) {
		super.render(state, stack, buffer, packedLight);
	}

	@Override
	public @NotNull ShadowRenderState createRenderState() {
		return new ShadowRenderState();
	}

	@Override
	public void extractRenderState(@NotNull ShadowEntity shadow,
								   @NotNull ShadowRenderState state, float partialTicks) {
		super.extractRenderState(shadow, state, partialTicks);
		state.scaleFunction = shadow.getScaleFunction();
		state.mimicEntity = shadow.getMimicEntity();
		state.mimicRenderState = shadow.getNewRenderState();
		state.isStasis = shadow.isStasis();
		state.setUpAnimFunction = shadow.getSetUpAnimFunction();
		state.renderToBufferFunction = shadow.getRenderToBufferFunction();
		state.idleAnimation.copyFrom(shadow.idleAnimation);
		state.walkAnim.copyFrom(shadow.walkAnim);
		state.walkToIdleAnimation.copyFrom(shadow.walkToIdleAnimation);
		state.stasisAnimation.copyFrom(shadow.stasisAnimation);
	}

	@Override
	protected void scale(@NotNull ShadowRenderState state, @NotNull PoseStack poseStack) {
		if (state.scaleFunction == null) super.scale(state, poseStack);
		else state.scaleFunction.scale(state.mimicRenderState, poseStack);
	}
}
