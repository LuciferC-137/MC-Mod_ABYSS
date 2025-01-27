package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.TemperEmissiveLayer;
import wardentools.entity.client.model.Temper;
import wardentools.entity.client.renderstate.TemperRenderState;
import wardentools.entity.custom.TemperEntity;

public class TemperRenderer extends MobRenderer<TemperEntity, TemperRenderState, Temper>{
	private static final ResourceLocation TEMPER_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/temper.png");

	public TemperRenderer(EntityRendererProvider.Context context) {
		super(context, new Temper(context.bakeLayer(Temper.LAYER_LOCATION)), 0.4f);
		this.addLayer(new ItemInHandLayer<TemperRenderState, Temper>(
				(RenderLayerParent<TemperRenderState, Temper>)this, context.getItemRenderer()));
		this.addLayer(new TemperEmissiveLayer(this));
	}


	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull TemperRenderState state) {
		return TEMPER_TEXTURE;
	}
	
	@Override
	public void render(@NotNull TemperRenderState state,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(state, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	public @NotNull TemperRenderState createRenderState() {
		return new TemperRenderState();
	}

	@Override
	public void extractRenderState(@NotNull TemperEntity temper,
								   @NotNull TemperRenderState state, float partialTicks) {
		super.extractRenderState(temper, state, partialTicks);
		state.attackAnimationState.copyFrom(temper.attackAnimationState);
		state.onGround = temper.onGround();
	}
}
