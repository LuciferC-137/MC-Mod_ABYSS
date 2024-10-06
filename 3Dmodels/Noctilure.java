// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Noctilure<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "noctilure"), "main");
	private final ModelPart ROOT;
	private final ModelPart FULL;
	private final ModelPart BODYWINGS;
	private final ModelPart BODY_2;
	private final ModelPart WING_R;
	private final ModelPart FOREWING_R;
	private final ModelPart ENDFEATHERS_R;
	private final ModelPart FOREFEATHERS_R;
	private final ModelPart FEATHERS_R;
	private final ModelPart WING_L;
	private final ModelPart FEATHERS_L;
	private final ModelPart FOREWING_L;
	private final ModelPart FOREFEATHERS_L;
	private final ModelPart ENDFEATHERS_L;
	private final ModelPart BODY_3;
	private final ModelPart TAIL;
	private final ModelPart TAIL_FEATHERS;
	private final ModelPart LEG_L;
	private final ModelPart FORELEG_L;
	private final ModelPart FEAT_L;
	private final ModelPart ENDFEAT_L;
	private final ModelPart LEG_R;
	private final ModelPart FORELEG_R;
	private final ModelPart FEAT_R;
	private final ModelPart ENDFEAT_R;
	private final ModelPart NECK_1;
	private final ModelPart NECK_2;
	private final ModelPart HEAD;

	public Noctilure(ModelPart root) {
		this.ROOT = root.getChild("ROOT");
		this.FULL = this.ROOT.getChild("FULL");
		this.BODYWINGS = this.FULL.getChild("BODYWINGS");
		this.BODY_2 = this.BODYWINGS.getChild("BODY_2");
		this.WING_R = this.BODY_2.getChild("WING_R");
		this.FOREWING_R = this.WING_R.getChild("FOREWING_R");
		this.ENDFEATHERS_R = this.FOREWING_R.getChild("ENDFEATHERS_R");
		this.FOREFEATHERS_R = this.FOREWING_R.getChild("FOREFEATHERS_R");
		this.FEATHERS_R = this.WING_R.getChild("FEATHERS_R");
		this.WING_L = this.BODY_2.getChild("WING_L");
		this.FEATHERS_L = this.WING_L.getChild("FEATHERS_L");
		this.FOREWING_L = this.WING_L.getChild("FOREWING_L");
		this.FOREFEATHERS_L = this.FOREWING_L.getChild("FOREFEATHERS_L");
		this.ENDFEATHERS_L = this.FOREWING_L.getChild("ENDFEATHERS_L");
		this.BODY_3 = this.BODY_2.getChild("BODY_3");
		this.TAIL = this.BODY_3.getChild("TAIL");
		this.TAIL_FEATHERS = this.TAIL.getChild("TAIL_FEATHERS");
		this.LEG_L = this.BODY_2.getChild("LEG_L");
		this.FORELEG_L = this.LEG_L.getChild("FORELEG_L");
		this.FEAT_L = this.FORELEG_L.getChild("FEAT_L");
		this.ENDFEAT_L = this.FEAT_L.getChild("ENDFEAT_L");
		this.LEG_R = this.BODY_2.getChild("LEG_R");
		this.FORELEG_R = this.LEG_R.getChild("FORELEG_R");
		this.FEAT_R = this.FORELEG_R.getChild("FEAT_R");
		this.ENDFEAT_R = this.FEAT_R.getChild("ENDFEAT_R");
		this.NECK_1 = this.FULL.getChild("NECK_1");
		this.NECK_2 = this.NECK_1.getChild("NECK_2");
		this.HEAD = this.NECK_2.getChild("HEAD");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ROOT = partdefinition.addOrReplaceChild("ROOT", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition FULL = ROOT.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(-0.5F, -10.0F, -5.0F));

		PartDefinition BODYWINGS = FULL.addOrReplaceChild("BODYWINGS", CubeListBuilder.create().texOffs(0, 48).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, -5.0F));

		PartDefinition BODY_2 = BODYWINGS.addOrReplaceChild("BODY_2", CubeListBuilder.create().texOffs(0, 16).addBox(-5.5F, -2.5F, -0.25F, 11.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.25F));

		PartDefinition WING_R = BODY_2.addOrReplaceChild("WING_R", CubeListBuilder.create().texOffs(0, 30).addBox(-22.0F, -0.5F, -1.0F, 22.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.0F, 0.75F));

		PartDefinition cube_r1 = WING_R.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-8, 75).addBox(-11.0F, 0.0F, -1.0F, 22.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -0.25F, 0.0F, -0.0175F, 0.0F, 0.0F));

		PartDefinition cube_r2 = WING_R.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 22.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 0.25F, 0.0F, 0.0175F, 0.0F, 0.0F));

		PartDefinition FOREWING_R = WING_R.addOrReplaceChild("FOREWING_R", CubeListBuilder.create(), PartPose.offset(-21.5F, 0.0F, -0.25F));

		PartDefinition cube_r3 = FOREWING_R.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(31, 23).addBox(-16.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -0.25F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r4 = FOREWING_R.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(39, 80).addBox(-16.0F, 0.0F, -0.5F, 15.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.25F, 0.75F, -0.0175F, 0.5236F, 0.0F));

		PartDefinition cube_r5 = FOREWING_R.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 18).addBox(-16.0F, 0.0F, -0.5F, 15.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.25F, 0.75F, 0.0175F, 0.5236F, 0.0F));

		PartDefinition ENDFEATHERS_R = FOREWING_R.addOrReplaceChild("ENDFEATHERS_R", CubeListBuilder.create(), PartPose.offset(-12.0F, 0.0F, 9.25F));

		PartDefinition cube_r6 = ENDFEATHERS_R.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 56).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r7 = ENDFEATHERS_R.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(45, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition cube_r8 = ENDFEATHERS_R.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(47, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r9 = ENDFEATHERS_R.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(13, 48).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition FOREFEATHERS_R = FOREWING_R.addOrReplaceChild("FOREFEATHERS_R", CubeListBuilder.create(), PartPose.offset(-5.5F, 0.0F, 8.5F));

		PartDefinition cube_r10 = FOREFEATHERS_R.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(40, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, -3.25F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r11 = FOREFEATHERS_R.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(42, 55).addBox(-2.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(10, 56).addBox(-4.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(18, 56).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, -2.25F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r12 = FOREFEATHERS_R.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 56).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -1.25F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r13 = FOREFEATHERS_R.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, 0.0F, -2.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0F, 0.75F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r14 = FOREFEATHERS_R.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(2, 56).addBox(0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, -0.25F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r15 = FOREFEATHERS_R.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(6, 56).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.75F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r16 = FOREFEATHERS_R.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(8, 56).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.75F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r17 = FOREFEATHERS_R.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(12, 56).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, -3.25F, 0.0F, 0.3054F, 0.0F));

		PartDefinition FEATHERS_R = WING_R.addOrReplaceChild("FEATHERS_R", CubeListBuilder.create(), PartPose.offset(-6.5F, 0.0F, 4.0F));

		PartDefinition cube_r18 = FEATHERS_R.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(38, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r19 = FEATHERS_R.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(10, 39).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r20 = FEATHERS_R.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(12, 39).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r21 = FEATHERS_R.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(14, 39).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r22 = FEATHERS_R.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(47, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r23 = FEATHERS_R.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(49, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r24 = FEATHERS_R.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(51, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r25 = FEATHERS_R.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(53, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r26 = FEATHERS_R.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(54, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r27 = FEATHERS_R.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(54, 36).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r28 = FEATHERS_R.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(54, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r29 = FEATHERS_R.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(24, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r30 = FEATHERS_R.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(55, 25).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r31 = FEATHERS_R.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(26, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r32 = FEATHERS_R.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(28, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r33 = FEATHERS_R.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(30, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r34 = FEATHERS_R.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(32, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r35 = FEATHERS_R.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(34, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r36 = FEATHERS_R.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(36, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r37 = FEATHERS_R.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(38, 55).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition WING_L = BODY_2.addOrReplaceChild("WING_L", CubeListBuilder.create().texOffs(31, 16).addBox(-0.5F, -0.5F, -1.0F, 22.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -2.0F, 0.75F));

		PartDefinition cube_r38 = WING_L.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(-8, 83).addBox(-11.0F, 0.0F, -1.0F, 22.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -0.25F, 0.0F, -0.0175F, 0.0F, 0.0F));

		PartDefinition cube_r39 = WING_L.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 8).addBox(-21.0F, 0.0F, -1.0F, 22.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.5F, 0.25F, 0.0F, 0.0175F, 0.0F, 0.0F));

		PartDefinition FEATHERS_L = WING_L.addOrReplaceChild("FEATHERS_L", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 4.0F));

		PartDefinition cube_r40 = FEATHERS_L.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(61, 8).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r41 = FEATHERS_L.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(59, 18).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r42 = FEATHERS_L.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(44, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r43 = FEATHERS_L.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(46, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r44 = FEATHERS_L.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(48, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r45 = FEATHERS_L.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(50, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r46 = FEATHERS_L.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(52, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r47 = FEATHERS_L.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(54, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r48 = FEATHERS_L.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(56, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r49 = FEATHERS_L.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(58, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r50 = FEATHERS_L.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r51 = FEATHERS_L.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(60, 26).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r52 = FEATHERS_L.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(60, 34).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r53 = FEATHERS_L.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(60, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r54 = FEATHERS_L.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(60, 59).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r55 = FEATHERS_L.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(61, 18).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r56 = FEATHERS_L.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(62, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition cube_r57 = FEATHERS_L.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(62, 26).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r58 = FEATHERS_L.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(62, 34).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition cube_r59 = FEATHERS_L.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(62, 42).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		PartDefinition FOREWING_L = WING_L.addOrReplaceChild("FOREWING_L", CubeListBuilder.create(), PartPose.offset(21.0F, -0.5F, -0.75F));

		PartDefinition cube_r60 = FOREWING_L.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(39, 75).addBox(1.0F, 0.0F, -0.5F, 15.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.25F, 1.25F, -0.0175F, -0.5236F, 0.0F));

		PartDefinition cube_r61 = FOREWING_L.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(0, 32).addBox(1.0F, 0.0F, -0.5F, 15.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.75F, 1.25F, 0.0175F, -0.5236F, 0.0F));

		PartDefinition cube_r62 = FOREWING_L.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, -0.5F, -0.5F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, 0.25F, 0.0F, -0.5236F, 0.0F));

		PartDefinition FOREFEATHERS_L = FOREWING_L.addOrReplaceChild("FOREFEATHERS_L", CubeListBuilder.create(), PartPose.offset(6.0F, 0.5F, 8.75F));

		PartDefinition cube_r63 = FOREFEATHERS_L.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(22, 56).addBox(-1.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r64 = FOREFEATHERS_L.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(56, 33).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 1.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r65 = FOREFEATHERS_L.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(56, 47).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 1.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r66 = FOREFEATHERS_L.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(14, 57).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r67 = FOREFEATHERS_L.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r68 = FOREFEATHERS_L.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(57, 18).addBox(3.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(58, 0).addBox(1.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(58, 34).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -2.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r69 = FOREFEATHERS_L.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(58, 26).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -3.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r70 = FOREFEATHERS_L.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(58, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -3.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition ENDFEATHERS_L = FOREWING_L.addOrReplaceChild("ENDFEATHERS_L", CubeListBuilder.create(), PartPose.offset(11.0F, 0.5F, 9.75F));

		PartDefinition cube_r71 = ENDFEATHERS_L.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(15, 48).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r72 = ENDFEATHERS_L.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(49, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r73 = ENDFEATHERS_L.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(20, 56).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.0F, 0.9599F, 0.0F));

		PartDefinition cube_r74 = ENDFEATHERS_L.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(49, 47).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.7418F, 0.0F));

		PartDefinition BODY_3 = BODY_2.addOrReplaceChild("BODY_3", CubeListBuilder.create().texOffs(92, 12).addBox(-3.75F, -2.25F, 12.0F, 8.0F, 4.0F, -11.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.25F, 7.75F));

		PartDefinition TAIL = BODY_3.addOrReplaceChild("TAIL", CubeListBuilder.create().texOffs(32, 47).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -0.75F, 12.0F));

		PartDefinition TAIL_FEATHERS = TAIL.addOrReplaceChild("TAIL_FEATHERS", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 6.0F));

		PartDefinition cube_r75 = TAIL_FEATHERS.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(42, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.3491F, -0.2182F, 0.0F));

		PartDefinition cube_r76 = TAIL_FEATHERS.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(44, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, 0.3491F, 0.3054F, 0.0F));

		PartDefinition cube_r77 = TAIL_FEATHERS.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(46, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.3491F, 0.0436F, 0.0F));

		PartDefinition cube_r78 = TAIL_FEATHERS.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(16, 46).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.3491F, -0.0436F, 0.0F));

		PartDefinition cube_r79 = TAIL_FEATHERS.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(18, 46).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.3491F, 0.2182F, 0.0F));

		PartDefinition cube_r80 = TAIL_FEATHERS.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(20, 46).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, 0.3491F, -0.3054F, 0.0F));

		PartDefinition LEG_L = BODY_2.addOrReplaceChild("LEG_L", CubeListBuilder.create(), PartPose.offset(4.0F, 2.0F, 8.25F));

		PartDefinition cube_r81 = LEG_L.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.6109F, 0.0F, 0.0F));

		PartDefinition FORELEG_L = LEG_L.addOrReplaceChild("FORELEG_L", CubeListBuilder.create(), PartPose.offset(0.0F, 4.75F, 3.0F));

		PartDefinition cube_r82 = FORELEG_L.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(4, 39).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.75F, -0.5F, 1.2654F, 0.0F, 0.0F));

		PartDefinition FEAT_L = FORELEG_L.addOrReplaceChild("FEAT_L", CubeListBuilder.create().texOffs(0, 64).addBox(-2.0F, -0.2F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, 5.0F));

		PartDefinition ENDFEAT_L = FEAT_L.addOrReplaceChild("ENDFEAT_L", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition cube_r83 = ENDFEAT_L.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r84 = ENDFEAT_L.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(37, 25).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition LEG_R = BODY_2.addOrReplaceChild("LEG_R", CubeListBuilder.create(), PartPose.offset(-4.0F, 2.0F, 8.25F));

		PartDefinition cube_r85 = LEG_R.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.6109F, 0.0F, 0.0F));

		PartDefinition FORELEG_R = LEG_R.addOrReplaceChild("FORELEG_R", CubeListBuilder.create(), PartPose.offset(0.0F, 4.75F, 3.0F));

		PartDefinition cube_r86 = FORELEG_R.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(0, 39).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.75F, -0.5F, 1.2654F, 0.0F, 0.0F));

		PartDefinition FEAT_R = FORELEG_R.addOrReplaceChild("FEAT_R", CubeListBuilder.create().texOffs(27, 63).addBox(-2.0F, -0.2F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, 5.0F));

		PartDefinition ENDFEAT_R = FEAT_R.addOrReplaceChild("ENDFEAT_R", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition cube_r87 = ENDFEAT_R.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r88 = ENDFEAT_R.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition NECK_1 = FULL.addOrReplaceChild("NECK_1", CubeListBuilder.create(), PartPose.offset(0.5F, 0.5F, -5.0F));

		PartDefinition cube_r89 = NECK_1.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(24, 32).addBox(-1.5F, -1.0F, -10.5F, 3.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition NECK_2 = NECK_1.addOrReplaceChild("NECK_2", CubeListBuilder.create().texOffs(41, 25).addBox(-1.5F, -1.5F, -8.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -9.0F));

		PartDefinition HEAD = NECK_2.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(43, 67).addBox(-3.0F, -3.0F, -1.75F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.25F));

		PartDefinition cube_r90 = HEAD.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(18, 65).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, -8.75F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r91 = HEAD.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(66, 63).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, -8.75F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r92 = HEAD.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(45, 39).addBox(-2.5F, -0.5F, -8.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, -0.75F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r93 = HEAD.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(53, 3).addBox(-0.5F, -1.0F, -7.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -1.75F, 0.1745F, -0.0873F, 0.0F));

		PartDefinition cube_r94 = HEAD.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(53, 49).addBox(-0.5F, -1.0F, -7.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, -1.75F, 0.1745F, 0.0873F, 0.0F));

		PartDefinition cube_r95 = HEAD.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(0, 39).addBox(-2.5F, -0.5F, -8.0F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, -1.75F, 0.2182F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ROOT.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}