package wardentools.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.ParasyteAnimation;
import wardentools.entity.custom.ParasyteEntity;

@SuppressWarnings("unused")
public class Parasyte extends HierarchicalModel<ParasyteEntity> {
	public static final ModelLayerLocation LAYER_LOCATION
			= new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, "parasyte"), "main");
	private final Parasyte.ModelParts parts;

	public Parasyte(ModelPart root) {
		ModelPart FULL = root.getChild("FULL");
		ModelPart BODY = FULL.getChild("BODY");
		ModelPart FIN_L1 = BODY.getChild("FIN_L1");
		ModelPart FIN_R1 = BODY.getChild("FIN_R1");
		ModelPart BODY2 = BODY.getChild("BODY2");
		ModelPart TAIL_1 = BODY.getChild("TAIL_1");
		ModelPart TAIL_2 = TAIL_1.getChild("TAIL_2");
		ModelPart FIN_R2 = BODY.getChild("FIN_R2");
		ModelPart FIN_L2 = BODY.getChild("FIN_L2");
		ModelPart HEAD = FULL.getChild("HEAD");

		this.parts = new ModelParts(FULL, BODY, HEAD, FIN_L1, FIN_R1, BODY2, TAIL_1, TAIL_2, FIN_R2, FIN_L2);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
    	PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -2.0F));
		PartDefinition FIN_L1 = BODY.addOrReplaceChild("FIN_L1", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, 1.0F));
		PartDefinition fin_l1_r1 = FIN_L1.addOrReplaceChild("fin_l1_r1", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0036F, 0.0F));
	    PartDefinition FIN_R1 = BODY.addOrReplaceChild("FIN_R1", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, 1.0F));
		PartDefinition fin_r1_r1 = FIN_R1.addOrReplaceChild("fin_r1_r1", CubeListBuilder.create().texOffs(4, 14).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0036F, 0.0F));
		PartDefinition BODY2 = BODY.addOrReplaceChild("BODY2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 1.0F));
		PartDefinition TAIL_1 = BODY.addOrReplaceChild("TAIL_1", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));
		PartDefinition fin_r3_r1 = TAIL_1.addOrReplaceChild("fin_r3_r1", CubeListBuilder.create().texOffs(16, 7).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.25F, 0.75F, 0.0F, 1.0472F, 0.0F));
		PartDefinition fin_l3_r1 = TAIL_1.addOrReplaceChild("fin_l3_r1", CubeListBuilder.create().texOffs(16, 6).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -0.25F, 0.75F, 0.0F, -1.0472F, 0.0F));
		PartDefinition TAIL_2 = TAIL_1.addOrReplaceChild("TAIL_2", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition FIN_R2 = BODY.addOrReplaceChild("FIN_R2", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, 2.5F));
		PartDefinition fin_r2_r1 = FIN_R2.addOrReplaceChild("fin_r2_r1", CubeListBuilder.create().texOffs(16, 4).addBox(-2.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 1.0036F, 0.0F));
		PartDefinition FIN_L2 = BODY.addOrReplaceChild("FIN_L2", CubeListBuilder.create(), PartPose.offset(2.0F, -1.0F, 2.5F));
		PartDefinition fin_l2_r1 = FIN_L2.addOrReplaceChild("fin_l2_r1", CubeListBuilder.create().texOffs(16, 2).addBox(0.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -1.0036F, 0.0F));
		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(12, 8).addBox(-1.75F, -1.25F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 14).addBox(-1.5F, -1.75F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(0.0F, -1.75F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -1.0F, -1.75F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	private record ModelParts(ModelPart FULL, ModelPart BODY, ModelPart HEAD,
							  ModelPart FIN_L1, ModelPart FIN_R1, ModelPart BODY2,
							  ModelPart TAIL_1, ModelPart TAIL_2, ModelPart FIN_R2, ModelPart FIN_L2) {}



	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay,
							   float red, float green, float blue, float alpha) {
		this.parts.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.parts.FULL;
	}

	@Override
	public void setupAnim(@NotNull ParasyteEntity entity, float limbSwing,
						  float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.idleAnimation, ParasyteAnimation.idle, ageInTicks);
		animateWalk(ParasyteAnimation.crawl, limbSwing, limbSwingAmount, 1f, 2.5f);
	}
}