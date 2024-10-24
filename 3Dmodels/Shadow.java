// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Shadow<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "shadow"), "main");
	private final ModelPart FULL;
	private final ModelPart HEAD;
	private final ModelPart BODY;
	private final ModelPart TORSO;
	private final ModelPart LEG_R;
	private final ModelPart LEG_L;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;

	public Shadow(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.HEAD = this.FULL.getChild("HEAD");
		this.BODY = this.FULL.getChild("BODY");
		this.TORSO = this.BODY.getChild("TORSO");
		this.LEG_R = this.BODY.getChild("LEG_R");
		this.LEG_L = this.BODY.getChild("LEG_L");
		this.ARM_L = this.BODY.getChild("ARM_L");
		this.ARM_R = this.BODY.getChild("ARM_R");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(-1.0F, 24.0F, 0.0F));

		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -24.0F, 1.0F));

		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(1.0F, -24.0F, 1.0F));

		PartDefinition TORSO = BODY.addOrReplaceChild("TORSO", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LEG_R = BODY.addOrReplaceChild("LEG_R", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition LEG_L = BODY.addOrReplaceChild("LEG_L", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition ARM_L = BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 2.0F, 0.0F));

		PartDefinition ARM_R = BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}