// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Parasyte<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "parasyte"), "main");
	private final ModelPart FULL;
	private final ModelPart BODY;
	private final ModelPart FIN_L1;
	private final ModelPart FIN_R1;
	private final ModelPart BODY2;
	private final ModelPart TAIL_1;
	private final ModelPart TAIL_2;
	private final ModelPart FIN_R2;
	private final ModelPart FIN_L2;
	private final ModelPart HEAD;

	public Parasyte(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.BODY = this.FULL.getChild("BODY");
		this.FIN_L1 = this.BODY.getChild("FIN_L1");
		this.FIN_R1 = this.BODY.getChild("FIN_R1");
		this.BODY2 = this.BODY.getChild("BODY2");
		this.TAIL_1 = this.BODY.getChild("TAIL_1");
		this.TAIL_2 = this.TAIL_1.getChild("TAIL_2");
		this.FIN_R2 = this.BODY.getChild("FIN_R2");
		this.FIN_L2 = this.BODY.getChild("FIN_L2");
		this.HEAD = this.FULL.getChild("HEAD");
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

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}