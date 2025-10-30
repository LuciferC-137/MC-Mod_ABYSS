package wardentools.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

@OnlyIn(Dist.CLIENT)
public class DiscModel extends Model {
	private static final ResourceLocation DEFAULT_TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
					"textures/models/disc_model/music_disc_default.png");
	private final ModelPart root;

	public DiscModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -1.0F, -2.5F, 13.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-5.5F, -1.0F, -4.5F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-4.5F, -1.0F, -5.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(-2.5F, -1.0F, -6.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-5.5F, -1.0F, 2.5F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-4.5F, -1.0F, 4.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 16).addBox(-2.5F, -1.0F, 5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay, int i) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
	}

	public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}

	public static ResourceLocation resourceForItem(ItemStack stack) {
		String name = stack.getItem().getDescriptionId()
				.substring(stack.getItem().getDescriptionId().lastIndexOf('.') + 1);

		ResourceLocation location = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
				"textures/models/disc_model/" + name + ".png");

		ResourceManager manager = Minecraft.getInstance().getResourceManager();
		return manager.getResource(location).isPresent() ? location : DEFAULT_TEXTURE;
	}

}