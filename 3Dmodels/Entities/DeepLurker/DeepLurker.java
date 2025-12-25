// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class DeepLurker<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "deeplurker"), "main");
	private final ModelPart FULL;
	private final ModelPart LOW_BODY;
	private final ModelPart body;
	private final ModelPart LegR;
	private final ModelPart LegL;
	private final ModelPart ARM_R;
	private final ModelPart foreArmR;
	private final ModelPart ARM_L;
	private final ModelPart foreArmL;
	private final ModelPart HEAD;
	private final ModelPart earL;
	private final ModelPart earR;

	public DeepLurker(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.LOW_BODY = this.FULL.getChild("LOW_BODY");
		this.body = this.LOW_BODY.getChild("body");
		this.LegR = this.LOW_BODY.getChild("LegR");
		this.LegL = this.LOW_BODY.getChild("LegL");
		this.ARM_R = this.LOW_BODY.getChild("ARM_R");
		this.foreArmR = this.ARM_R.getChild("foreArmR");
		this.ARM_L = this.LOW_BODY.getChild("ARM_L");
		this.foreArmL = this.ARM_L.getChild("foreArmL");
		this.HEAD = this.FULL.getChild("HEAD");
		this.earL = this.HEAD.getChild("earL");
		this.earR = this.HEAD.getChild("earR");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition LOW_BODY = FULL.addOrReplaceChild("LOW_BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = LOW_BODY.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -2.9955F, -0.0136F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -1.0F));

		PartDefinition LegR = LOW_BODY.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(24, 0).addBox(-1.1F, 1.1F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 13).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 1.0F));

		PartDefinition LegL = LOW_BODY.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(24, 8).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(0.1F, 1.1F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 1.0F));

		PartDefinition ARM_R = LOW_BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(0, 17).addBox(-0.5F, -0.5F, -3.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.5F, 0.25F));

		PartDefinition foreArmR = ARM_R.addOrReplaceChild("foreArmR", CubeListBuilder.create().texOffs(20, 21).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, -0.1F, -2.75F));

		PartDefinition ARM_L = LOW_BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(10, 21).addBox(-0.5F, -0.5F, -3.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 1.5F, 0.5F));

		PartDefinition foreArmL = ARM_L.addOrReplaceChild("foreArmL", CubeListBuilder.create().texOffs(24, 4).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -0.1F, -3.0F));

		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition earL = HEAD.addOrReplaceChild("earL", CubeListBuilder.create().texOffs(14, 15).addBox(-1.0F, -3.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, -0.5F));

		PartDefinition earR = HEAD.addOrReplaceChild("earR", CubeListBuilder.create().texOffs(14, 9).addBox(-3.0F, -3.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, -0.5F));

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