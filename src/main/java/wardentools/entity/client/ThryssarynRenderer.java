package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ThryssarynEntity;

@OnlyIn(Dist.CLIENT)
public class ThryssarynRenderer extends MobRenderer<ThryssarynEntity, Thryssaryn>{
	private static final ResourceLocation THRYSSARYN_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
					"textures/entity/thryssaryn/thryssaryn.png");

	public ThryssarynRenderer(EntityRendererProvider.Context context) {
		super(context, new Thryssaryn(context.bakeLayer(Thryssaryn.LAYER_LOCATION)), 0.7f);
		this.addLayer(new ThryssarynLuthLayer(this));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull ThryssarynEntity thryssaryn) {
		return THRYSSARYN_TEXTURE;
	}

	@Override
	public void render(@NotNull ThryssarynEntity entity, float entityYaw,
                       float partialTicks, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}
}
