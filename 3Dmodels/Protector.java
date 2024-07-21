// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Protector<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "protector"), "main");
	private final ModelPart FULL;
	private final ModelPart BODY;
	private final ModelPart HEAD;
	private final ModelPart EAR_L;
	private final ModelPart EAR_R;
	private final ModelPart TORSO;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;
	private final ModelPart LEG_R;
	private final ModelPart LEG_L;

	public Protector(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.BODY = root.getChild("BODY");
		this.HEAD = root.getChild("HEAD");
		this.EAR_L = root.getChild("EAR_L");
		this.EAR_R = root.getChild("EAR_R");
		this.TORSO = root.getChild("TORSO");
		this.ARM_L = root.getChild("ARM_L");
		this.ARM_R = root.getChild("ARM_R");
		this.LEG_R = root.getChild("LEG_R");
		this.LEG_L = root.getChild("LEG_L");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition HEAD = BODY.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

		PartDefinition EAR_L = HEAD.addOrReplaceChild("EAR_L", CubeListBuilder.create().texOffs(59, 6).addBox(8.0F, -52.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 33.0F, 0.0F));

		PartDefinition EAR_R = HEAD.addOrReplaceChild("EAR_R", CubeListBuilder.create().texOffs(58, 37).addBox(-18.0F, -52.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 33.0F, 0.0F));

		PartDefinition TORSO = BODY.addOrReplaceChild("TORSO", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, -5.0F, 18.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

		PartDefinition ARM_L = TORSO.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(44, 50).addBox(0.0F, -3.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, 0.0F));

		PartDefinition ARM_R = TORSO.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(0, 58).addBox(-8.0F, -3.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 4.0F, 0.0F));

		PartDefinition LEG_R = FULL.addOrReplaceChild("LEG_R", CubeListBuilder.create().texOffs(76, 77).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 11.0F, 0.0F));

		PartDefinition LEG_L = FULL.addOrReplaceChild("LEG_L", CubeListBuilder.create().texOffs(76, 49).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}