package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.TemperEntity;

public class TemperRenderer extends MobRenderer<TemperEntity, Temper>{
	private static final ResourceLocation TEMPER_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/temper.png");

	public TemperRenderer(EntityRendererProvider.Context context) {
		super(context, new Temper(context.bakeLayer(Temper.LAYER_LOCATION)), 0.4f);
		this.addLayer(new ItemInHandLayer<TemperEntity, Temper>(
				(RenderLayerParent<TemperEntity, Temper>)this, context.getItemInHandRenderer()));
		this.addLayer(new TemperEmissiveLayer(this));
	}


	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull TemperEntity pEntity) {
		return TEMPER_TEXTURE;
	}
	
	@Override
	public void render(@NotNull TemperEntity pEntity, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
