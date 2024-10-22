package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ParasyteEntity;
import wardentools.entity.custom.ShadowEntity;
import wardentools.particle.ParticleRegistry;

public class ShadowRenderer extends MobRenderer<ShadowEntity, Shadow>{
	private static final ResourceLocation SHADOW_TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/entity/shadow.png");

	public ShadowRenderer(EntityRendererProvider.Context context) {
		super(context, new Shadow(context.bakeLayer(Shadow.LAYER_LOCATION)), 0.2f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ShadowEntity pEntity) {
		return SHADOW_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ShadowEntity shadow, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(shadow, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
