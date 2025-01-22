package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ProtectorEntity;

public class ProtectorRenderer extends MobRenderer<ProtectorEntity, Protector>{
	private static final ResourceLocation PROTECTOR_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/protector.png");

	public ProtectorRenderer(EntityRendererProvider.Context context) {
		super(context, new Protector(context.bakeLayer(Protector.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ProtectorEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ProtectorEntity pEntity) {
		return PROTECTOR_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ProtectorEntity pEntity, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
