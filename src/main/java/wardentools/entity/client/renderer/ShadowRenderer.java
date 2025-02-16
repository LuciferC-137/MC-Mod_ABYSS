package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.client.event.ForgeEventFactoryClient;
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
		if (!ForgeEventFactoryClient.onRenderLivingPre(state, this, stack, buffer, packedLight)) {
			stack.pushPose();
			if (state.hasPose(Pose.SLEEPING)) {
				Direction direction = state.bedOrientation;
				if (direction != null) {
					float f = state.eyeHeight - 0.1F;
					stack.translate((float)(-direction.getStepX()) * f, 0.0F, (float)(-direction.getStepZ()) * f);
				}
			}
			float f1 = state.scale;
			stack.scale(f1, f1, f1);
			this.setupRotations(state, stack, state.bodyRot, f1);
			stack.scale(-1.0F, -1.0F, 1.0F);
			this.scale(state, stack);
			stack.translate(0.0F, -1.501F, 0.0F);
			if (state.setUpAnimFunction != null) {
				state.setUpAnimFunction.setupAnim(state.mimicRenderState);
			} else {
				this.model.setupAnim(state);
			}
			boolean flag1 = this.isBodyVisible(state);
			boolean flag = !flag1 && !state.isInvisibleToPlayer;
			RenderType rendertype = this.getRenderType(state, flag1, flag, state.appearsGlowing);
			if (rendertype != null) {
				VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
				int i = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
				int j = flag ? 654311423 : -1;
				int k = ARGB.multiply(j, this.getModelTint(state));
				if (state.renderToBufferFunction != null) {
					state.renderToBufferFunction.renderToBuffer(stack, vertexconsumer, packedLight, i);
				} else {
					this.model.renderToBuffer(stack, vertexconsumer, packedLight, i, k);
				}
			}
			if (this.shouldRenderLayers(state)) {
                for (RenderLayer<ShadowRenderState, Shadow> renderlayer : this.layers) {
                    renderlayer.render(stack, buffer, packedLight, state, state.yRot, state.xRot);
                }
			}
			stack.popPose();
			ForgeEventFactoryClient.onRenderLivingPost(state, this, stack, buffer, packedLight);
		}
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
		state.mimicRenderState = shadow.getMimicRenderState();
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
