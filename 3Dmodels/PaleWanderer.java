// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class pale_wanderer<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "pale_wanderer"), "main");
	private final ModelPart FULL;
	private final ModelPart BODY;
	private final ModelPart BODY_FRONT;
	private final ModelPart NECK;
	private final ModelPart LEG_FRONT_L;
	private final ModelPart LEG_FRONT_R;
	private final ModelPart BODY_BACK;
	private final ModelPart TAIL;
	private final ModelPart TAIL_MAIN;
	private final ModelPart TAIL_END;
	private final ModelPart LEG_BACK_L;
	private final ModelPart LEG_BACK_R;
	private final ModelPart BACK;
	private final ModelPart HEAD;
	private final ModelPart EAR_R;
	private final ModelPart EAR_L;

	public pale_wanderer(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.BODY = root.getChild("BODY");
		this.BODY_FRONT = root.getChild("BODY_FRONT");
		this.NECK = root.getChild("NECK");
		this.LEG_FRONT_L = root.getChild("LEG_FRONT_L");
		this.LEG_FRONT_R = root.getChild("LEG_FRONT_R");
		this.BODY_BACK = root.getChild("BODY_BACK");
		this.TAIL = root.getChild("TAIL");
		this.TAIL_MAIN = root.getChild("TAIL_MAIN");
		this.TAIL_END = root.getChild("TAIL_END");
		this.LEG_BACK_L = root.getChild("LEG_BACK_L");
		this.LEG_BACK_R = root.getChild("LEG_BACK_R");
		this.BACK = root.getChild("BACK");
		this.HEAD = root.getChild("HEAD");
		this.EAR_R = root.getChild("EAR_R");
		this.EAR_L = root.getChild("EAR_L");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition BODY_FRONT = BODY.addOrReplaceChild("BODY_FRONT", CubeListBuilder.create().texOffs(19, 17).addBox(-3.5F, -3.5F, -4.0F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition NECK = BODY_FRONT.addOrReplaceChild("NECK", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -4.0F));

		PartDefinition neck_r1 = NECK.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(21, 7).addBox(-2.5F, -2.5F, -4.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LEG_FRONT_L = BODY_FRONT.addOrReplaceChild("LEG_FRONT_L", CubeListBuilder.create().texOffs(6, 30).addBox(0.0F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -3.0F));

		PartDefinition LEG_FRONT_R = BODY_FRONT.addOrReplaceChild("LEG_FRONT_R", CubeListBuilder.create().texOffs(12, 30).addBox(0.0F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -3.0F));

		PartDefinition BODY_BACK = BODY.addOrReplaceChild("BODY_BACK", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.0F));

		PartDefinition TAIL = BODY_BACK.addOrReplaceChild("TAIL", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 6.0F));

		PartDefinition TAIL_MAIN = TAIL.addOrReplaceChild("TAIL_MAIN", CubeListBuilder.create().texOffs(19, 0).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition TAIL_END = TAIL.addOrReplaceChild("TAIL_END", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition LEG_BACK_L = BODY_BACK.addOrReplaceChild("LEG_BACK_L", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 4.0F));

		PartDefinition LEG_BACK_R = BODY_BACK.addOrReplaceChild("LEG_BACK_R", CubeListBuilder.create().texOffs(18, 30).addBox(-1.0F, -1.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 4.0F));

		PartDefinition BACK = BODY_BACK.addOrReplaceChild("BACK", CubeListBuilder.create().texOffs(0, 12).addBox(-3.5F, -4.5F, 0.0F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 27).addBox(-2.5F, -3.0F, 5.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(10, 24).addBox(-1.5F, 0.0F, -10.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -6.0F));

		PartDefinition EAR_R = HEAD.addOrReplaceChild("EAR_R", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -3.0F, 0.0F));

		PartDefinition EAR_L = HEAD.addOrReplaceChild("EAR_L", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, 0.0F));

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