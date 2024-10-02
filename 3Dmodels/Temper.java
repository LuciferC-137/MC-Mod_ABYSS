// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Temper<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "temper"), "main");
	private final ModelPart FULL;
	private final ModelPart HEAD;
	private final ModelPart BODY;
	private final ModelPart TORSO;
	private final ModelPart WING_R;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;
	private final ModelPart WING_L;

	public Temper(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.HEAD = this.FULL.getChild("HEAD");
		this.BODY = this.FULL.getChild("BODY");
		this.TORSO = this.BODY.getChild("TORSO");
		this.WING_R = this.BODY.getChild("WING_R");
		this.ARM_L = this.BODY.getChild("ARM_L");
		this.ARM_R = this.BODY.getChild("ARM_R");
		this.WING_L = this.BODY.getChild("WING_L");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(-0.5F, 27.0F, -1.0F));

		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, 1.0F));

		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TORSO = BODY.addOrReplaceChild("TORSO", CubeListBuilder.create().texOffs(2, 18).addBox(-1.0F, 2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, 1.0F));

		PartDefinition WING_R = BODY.addOrReplaceChild("WING_R", CubeListBuilder.create(), PartPose.offset(0.0F, -6.5F, 2.0F));

		PartDefinition wing_r_r1 = WING_R.addOrReplaceChild("wing_r_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -0.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -0.4363F, 0.0F));

		PartDefinition ARM_L = BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create(), PartPose.offset(2.0F, -7.75F, 1.0F));

		PartDefinition arm_l_r1 = ARM_L.addOrReplaceChild("arm_l_r1", CubeListBuilder.create().texOffs(23, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.25F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ARM_R = BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.75F, 1.0F));

		PartDefinition arm_r_r1 = ARM_R.addOrReplaceChild("arm_r_r1", CubeListBuilder.create().texOffs(23, 6).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.25F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition WING_L = BODY.addOrReplaceChild("WING_L", CubeListBuilder.create(), PartPose.offset(1.0F, -6.5F, 2.0F));

		PartDefinition wing_l_r1 = WING_L.addOrReplaceChild("wing_l_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -0.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.4363F, 0.0F));

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