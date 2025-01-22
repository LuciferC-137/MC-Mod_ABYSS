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
import wardentools.particle.ParticleRegistry;

public class ParasyteRenderer extends MobRenderer<ParasyteEntity, Parasyte>{
	private static final ResourceLocation PARASYTE_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/parasyte.png");

	public ParasyteRenderer(EntityRendererProvider.Context context) {
		super(context, new Parasyte(context.bakeLayer(Parasyte.LAYER_LOCATION)), 0.2f);
		this.addLayer(new ParasyteEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ParasyteEntity pEntity) {
		return PARASYTE_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ParasyteEntity parasyte, float pEntityYaw, float pPartialTicks,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		if (parasyte.tickCount%10 == parasyte.level().getRandom().nextInt(0, 10)){
			Vec3 particlePos = parasyte.getPosition(1f);
			parasyte.level().addParticle(ParticleRegistry.CORRUPTION.get(),
					particlePos.x, particlePos.y, particlePos.z,
					parasyte.level().getRandom().nextFloat() * 0.04f,
					parasyte.level().getRandom().nextFloat() * 0.1f,
					parasyte.level().getRandom().nextFloat() * 0.04f);
		}
		super.render(parasyte, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
	}

}
