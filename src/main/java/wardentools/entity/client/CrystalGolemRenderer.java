package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.CrystalGolemEntity;
import wardentools.entity.custom.ProtectorEntity;

public class CrystalGolemRenderer extends MobRenderer<CrystalGolemEntity, CrystalGolem>{
	private static final ResourceLocation CRYSTAL_GOLEM_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/crystal_golem.png");

	public CrystalGolemRenderer(EntityRendererProvider.Context context) {
		super(context, new CrystalGolem(context.bakeLayer(CrystalGolem.LAYER_LOCATION)), 0.5f);
		this.addLayer(new CrystalGolemRustLayer(this));
		this.addLayer(new CrystalGolemCrystalLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull CrystalGolemEntity pEntity) {
		return CRYSTAL_GOLEM_TEXTURE;
	}
	
	@Override
	public void render(@NotNull CrystalGolemEntity pEntity, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
