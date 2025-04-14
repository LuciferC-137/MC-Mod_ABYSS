package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ContagionIncarnationCorpseEntity;
import wardentools.particle.ParticleRegistry;

@OnlyIn(Dist.CLIENT)
public class ContagionIncarnationCorpseRenderer
		extends LivingEntityRenderer<ContagionIncarnationCorpseEntity, ContagionIncarnationCorpse> {
	private static final ResourceLocation CONTAGION_INCARNATION_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/contagion_incarnation_corpse.png");
	private static final int PARTICLE_EFFECT_DURATION = 200;

	public ContagionIncarnationCorpseRenderer(EntityRendererProvider.Context context) {
		super(context, new ContagionIncarnationCorpse(context.bakeLayer(
				ContagionIncarnationCorpse.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ContagionIncarnationCorpseEntity pEntity) {
		return CONTAGION_INCARNATION_TEXTURE;
	}

	@Override
	public void render(@NotNull ContagionIncarnationCorpseEntity entity, float a,
					   float b, @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource bufferSource, int i) {
		super.render(entity, a, b, poseStack, bufferSource, i);
	}



	@Override
	protected void scale(@NotNull ContagionIncarnationCorpseEntity entity, @NotNull PoseStack stack, float v) {
		stack.scale(2f, 2f, 2f);
	}

	@Override
	protected boolean shouldShowName(@NotNull ContagionIncarnationCorpseEntity entity) {
		return false;
	}
}
