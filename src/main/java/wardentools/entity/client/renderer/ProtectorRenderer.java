package wardentools.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.client.emissive.ProtectorEmissiveLayer;
import wardentools.entity.client.model.Protector;
import wardentools.entity.client.renderstate.ProtectorRenderState;
import wardentools.entity.custom.ProtectorEntity;

public class ProtectorRenderer extends LivingEntityRenderer<ProtectorEntity, ProtectorRenderState, Protector> {
	private static final ResourceLocation PROTECTOR_TEXTURE = 
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/protector.png");

	public ProtectorRenderer(EntityRendererProvider.Context context) {
		super(context, new Protector(context.bakeLayer(Protector.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ProtectorEmissiveLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ProtectorRenderState state) {
		return PROTECTOR_TEXTURE;
	}
	
	@Override
	public void render(@NotNull ProtectorRenderState pEntity,
					   @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pMatrixStack, pBuffer, pPackedLight);
	}

	@Override
	protected boolean shouldShowName(@NotNull ProtectorEntity entity, double v) {
		return entity.hasCustomName() && super.shouldShowName(entity, v);
	}

	@Override
	public @NotNull ProtectorRenderState createRenderState() {
		return new ProtectorRenderState();
	}

	@Override
	public void extractRenderState(@NotNull ProtectorEntity entity, @NotNull ProtectorRenderState state,
								   float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.isSpawning = entity.isSpawning();
		state.isDispawning = entity.isDispawning();
		state.isSprinting = entity.isSprinting();
		state.attackAnimationState.copyFrom(entity.attackAnimationState);
		state.earTickle.copyFrom(entity.earTickle);
		state.spawning.copyFrom(entity.spawning);
		state.dispawning.copyFrom(entity.dispawning);
	}
}
