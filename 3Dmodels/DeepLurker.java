// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class deeplurker<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "deeplurker"), "main");
	private final ModelPart FULL;
	private final ModelPart HEAD;
	private final ModelPart earL;
	private final ModelPart earR;
	private final ModelPart LOW_BODY;
	private final ModelPart body;
	private final ModelPart LegL;
	private final ModelPart ARM_R;
	private final ModelPart foreArmR;
	private final ModelPart LegR;
	private final ModelPart ARM_L;
	private final ModelPart foreArmL;

	public deeplurker(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.HEAD = root.getChild("HEAD");
		this.earL = root.getChild("earL");
		this.earR = root.getChild("earR");
		this.LOW_BODY = root.getChild("LOW_BODY");
		this.body = root.getChild("body");
		this.LegL = root.getChild("LegL");
		this.ARM_R = root.getChild("ARM_R");
		this.foreArmR = root.getChild("foreArmR");
		this.LegR = root.getChild("LegR");
		this.ARM_L = root.getChild("ARM_L");
		this.foreArmL = root.getChild("foreArmL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 19).addBox(-3.0F, 0.0F, -4.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 0.0F));

		PartDefinition earL = HEAD.addOrReplaceChild("earL", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 3.0F));

		PartDefinition earR = HEAD.addOrReplaceChild("earR", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));

		PartDefinition earR_r1 = earR.addOrReplaceChild("earR_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition LOW_BODY = FULL.addOrReplaceChild("LOW_BODY", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 0.0F));

		PartDefinition body = LOW_BODY.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -2.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition LegL = LOW_BODY.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(7, 0).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -5.0F, 2.0F));

		PartDefinition ARM_R = LOW_BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(6, 2).addBox(0.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, -2.0F));

		PartDefinition foreArmR = ARM_R.addOrReplaceChild("foreArmR", CubeListBuilder.create().texOffs(7, 2).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -1.0F));

		PartDefinition LegR = LOW_BODY.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(7, 0).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -5.0F, -3.0F));

		PartDefinition ARM_L = LOW_BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(6, 2).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 2.0F));

		PartDefinition foreArmL = ARM_L.addOrReplaceChild("foreArmL", CubeListBuilder.create().texOffs(7, 2).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

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