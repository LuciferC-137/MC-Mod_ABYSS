package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.ParasyteEmissiveLayer;
import wardentools.entity.client.model.Parasyte;
import wardentools.entity.client.renderstate.ParasyteRenderState;
import wardentools.entity.custom.ParasyteEntity;

public class ParasyteRenderer extends MobRenderer<ParasyteEntity, ParasyteRenderState, Parasyte> {
	private static final ResourceLocation PARASYTE_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/parasyte.png");

	public ParasyteRenderer(EntityRendererProvider.Context context) {
		super(context, new Parasyte(context.bakeLayer(Parasyte.LAYER_LOCATION)), 0.2f);
		this.addLayer(new ParasyteEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ParasyteRenderState state) {
		return PARASYTE_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ParasyteRenderState state,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(state, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	public @NotNull ParasyteRenderState createRenderState() {
		return new ParasyteRenderState();
	}

}
