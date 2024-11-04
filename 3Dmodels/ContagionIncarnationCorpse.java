// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ContagionIncarnation<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "contagionincarnation"), "main");
	private final ModelPart FULL;
	private final ModelPart TAIL;
	private final ModelPart SECTION_6;
	private final ModelPart SUBSECTION_6_1;
	private final ModelPart SECTION_7;
	private final ModelPart SUBSECTION_7_1;
	private final ModelPart SUBSECTION_7_2;
	private final ModelPart LEG_LEFT_3;
	private final ModelPart BASE_L_3;
	private final ModelPart FORELEG_L_3;
	private final ModelPart FEET_L_3;
	private final ModelPart LEG_RIGHT_3;
	private final ModelPart BASE_R_3;
	private final ModelPart FORELEG_R_3;
	private final ModelPart FEET_R_3;
	private final ModelPart SECTION_8;
	private final ModelPart SUBSECTION_8_1;
	private final ModelPart SUBSECTION_8_2;
	private final ModelPart LEG_LEFT_4;
	private final ModelPart BASE_L_4;
	private final ModelPart FORELEG_L_4;
	private final ModelPart FEET_L_4;
	private final ModelPart LEG_RIGHT_4;
	private final ModelPart BASE_R_4;
	private final ModelPart FORELEG_R_4;
	private final ModelPart FEET_R_4;
	private final ModelPart SECTION_9;
	private final ModelPart SUBSECTION_9_1;
	private final ModelPart SECTION_10;
	private final ModelPart SUBSECTION_10_1;
	private final ModelPart SECTION_11;
	private final ModelPart SUBSECTION_11_1;
	private final ModelPart SECTION_12;
	private final ModelPart SUBSECTION_12_1;
	private final ModelPart SECTION_13;
	private final ModelPart SUBSECTION_13_1;
	private final ModelPart SECTION_14;
	private final ModelPart SUBSECTION_14_1;
	private final ModelPart SECTION_15;
	private final ModelPart SUBSECTION_15_1;
	private final ModelPart SECTION_16;
	private final ModelPart SUBSECTION_16_1;
	private final ModelPart SECTION_17;
	private final ModelPart SUBSECTION_17_1;
	private final ModelPart SECTION_18;
	private final ModelPart SUBSECTION_18_1;
	private final ModelPart END;
	private final ModelPart FRONT_BODY;
	private final ModelPart TORSO;
	private final ModelPart BELLY;
	private final ModelPart ARM_RIGHT;
	private final ModelPart ARMBASE_R;
	private final ModelPart FOREARM_R;
	private final ModelPart HAND_R;
	private final ModelPart FINGER_R_4;
	private final ModelPart FOREFINGER_R_4;
	private final ModelPart FINGER_R_3;
	private final ModelPart FOREFINGER_R_3;
	private final ModelPart FINGER_R_2;
	private final ModelPart FOREFINGER_R_2;
	private final ModelPart FINGER_R_1;
	private final ModelPart FOREFINGER_R_1;
	private final ModelPart TORSO_BOUND_R;
	private final ModelPart LEG_LEFT_1;
	private final ModelPart BASE_L_1;
	private final ModelPart FORELEG_L_1;
	private final ModelPart FEET_L_1;
	private final ModelPart LEG_RIGHT_1;
	private final ModelPart BASE_R_1;
	private final ModelPart FORELEG_R_1;
	private final ModelPart FEET_R_1;
	private final ModelPart LEG_RIGHT_2;
	private final ModelPart FORELEG_R_2;
	private final ModelPart FEET_R_2;
	private final ModelPart BASE_R_2;
	private final ModelPart LEG_LEFT_2;
	private final ModelPart FORELEG_L_2;
	private final ModelPart FEET_L_2;
	private final ModelPart BASE_L_2;
	private final ModelPart ARM_LEFT;
	private final ModelPart ARMBASE_L;
	private final ModelPart FOREARM_L;
	private final ModelPart HAND_L;
	private final ModelPart FINGER_L_4;
	private final ModelPart FOREFINGER_L_4;
	private final ModelPart FINGER_L_3;
	private final ModelPart FOREFINGER_L_3;
	private final ModelPart FINGER_L_2;
	private final ModelPart FOREFINGER_L_2;
	private final ModelPart FINGER_L_1;
	private final ModelPart FOREFINGER_L_1;
	private final ModelPart TORSO_BOUND_L;
	private final ModelPart CHESTPLATE;
	private final ModelPart UPPER;
	private final ModelPart SECTION_5;
	private final ModelPart SUBSECTION_5_1;
	private final ModelPart SECTION_4;
	private final ModelPart SUBSECTION_4_1;
	private final ModelPart SECTION_3;
	private final ModelPart SUBSECTION_3_1;
	private final ModelPart SECTION_2;
	private final ModelPart SUBSECTION_2_1;
	private final ModelPart SECTION_1;
	private final ModelPart SUBSECTION_1_1;
	private final ModelPart HEAD;
	private final ModelPart JAW;
	private final ModelPart FOREHEAD;
	private final ModelPart MANE;
	private final ModelPart FIXED_JAW;

	public ContagionIncarnation(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.TAIL = this.FULL.getChild("TAIL");
		this.SECTION_6 = this.TAIL.getChild("SECTION_6");
		this.SUBSECTION_6_1 = this.SECTION_6.getChild("SUBSECTION_6_1");
		this.SECTION_7 = this.SECTION_6.getChild("SECTION_7");
		this.SUBSECTION_7_1 = this.SECTION_7.getChild("SUBSECTION_7_1");
		this.SUBSECTION_7_2 = this.SECTION_7.getChild("SUBSECTION_7_2");
		this.LEG_LEFT_3 = this.SECTION_7.getChild("LEG_LEFT_3");
		this.BASE_L_3 = this.LEG_LEFT_3.getChild("BASE_L_3");
		this.FORELEG_L_3 = this.LEG_LEFT_3.getChild("FORELEG_L_3");
		this.FEET_L_3 = this.FORELEG_L_3.getChild("FEET_L_3");
		this.LEG_RIGHT_3 = this.SECTION_7.getChild("LEG_RIGHT_3");
		this.BASE_R_3 = this.LEG_RIGHT_3.getChild("BASE_R_3");
		this.FORELEG_R_3 = this.LEG_RIGHT_3.getChild("FORELEG_R_3");
		this.FEET_R_3 = this.FORELEG_R_3.getChild("FEET_R_3");
		this.SECTION_8 = this.SECTION_7.getChild("SECTION_8");
		this.SUBSECTION_8_1 = this.SECTION_8.getChild("SUBSECTION_8_1");
		this.SUBSECTION_8_2 = this.SECTION_8.getChild("SUBSECTION_8_2");
		this.LEG_LEFT_4 = this.SECTION_8.getChild("LEG_LEFT_4");
		this.BASE_L_4 = this.LEG_LEFT_4.getChild("BASE_L_4");
		this.FORELEG_L_4 = this.LEG_LEFT_4.getChild("FORELEG_L_4");
		this.FEET_L_4 = this.FORELEG_L_4.getChild("FEET_L_4");
		this.LEG_RIGHT_4 = this.SECTION_8.getChild("LEG_RIGHT_4");
		this.BASE_R_4 = this.LEG_RIGHT_4.getChild("BASE_R_4");
		this.FORELEG_R_4 = this.LEG_RIGHT_4.getChild("FORELEG_R_4");
		this.FEET_R_4 = this.FORELEG_R_4.getChild("FEET_R_4");
		this.SECTION_9 = this.SECTION_8.getChild("SECTION_9");
		this.SUBSECTION_9_1 = this.SECTION_9.getChild("SUBSECTION_9_1");
		this.SECTION_10 = this.SECTION_9.getChild("SECTION_10");
		this.SUBSECTION_10_1 = this.SECTION_10.getChild("SUBSECTION_10_1");
		this.SECTION_11 = this.SECTION_10.getChild("SECTION_11");
		this.SUBSECTION_11_1 = this.SECTION_11.getChild("SUBSECTION_11_1");
		this.SECTION_12 = this.SECTION_11.getChild("SECTION_12");
		this.SUBSECTION_12_1 = this.SECTION_12.getChild("SUBSECTION_12_1");
		this.SECTION_13 = this.SECTION_12.getChild("SECTION_13");
		this.SUBSECTION_13_1 = this.SECTION_13.getChild("SUBSECTION_13_1");
		this.SECTION_14 = this.SECTION_13.getChild("SECTION_14");
		this.SUBSECTION_14_1 = this.SECTION_14.getChild("SUBSECTION_14_1");
		this.SECTION_15 = this.SECTION_14.getChild("SECTION_15");
		this.SUBSECTION_15_1 = this.SECTION_15.getChild("SUBSECTION_15_1");
		this.SECTION_16 = this.SECTION_15.getChild("SECTION_16");
		this.SUBSECTION_16_1 = this.SECTION_16.getChild("SUBSECTION_16_1");
		this.SECTION_17 = this.SECTION_16.getChild("SECTION_17");
		this.SUBSECTION_17_1 = this.SECTION_17.getChild("SUBSECTION_17_1");
		this.SECTION_18 = this.SECTION_17.getChild("SECTION_18");
		this.SUBSECTION_18_1 = this.SECTION_18.getChild("SUBSECTION_18_1");
		this.END = this.SECTION_18.getChild("END");
		this.FRONT_BODY = this.FULL.getChild("FRONT_BODY");
		this.TORSO = this.FRONT_BODY.getChild("TORSO");
		this.BELLY = this.TORSO.getChild("BELLY");
		this.ARM_RIGHT = this.TORSO.getChild("ARM_RIGHT");
		this.ARMBASE_R = this.ARM_RIGHT.getChild("ARMBASE_R");
		this.FOREARM_R = this.ARMBASE_R.getChild("FOREARM_R");
		this.HAND_R = this.FOREARM_R.getChild("HAND_R");
		this.FINGER_R_4 = this.HAND_R.getChild("FINGER_R_4");
		this.FOREFINGER_R_4 = this.FINGER_R_4.getChild("FOREFINGER_R_4");
		this.FINGER_R_3 = this.HAND_R.getChild("FINGER_R_3");
		this.FOREFINGER_R_3 = this.FINGER_R_3.getChild("FOREFINGER_R_3");
		this.FINGER_R_2 = this.HAND_R.getChild("FINGER_R_2");
		this.FOREFINGER_R_2 = this.FINGER_R_2.getChild("FOREFINGER_R_2");
		this.FINGER_R_1 = this.HAND_R.getChild("FINGER_R_1");
		this.FOREFINGER_R_1 = this.FINGER_R_1.getChild("FOREFINGER_R_1");
		this.TORSO_BOUND_R = this.ARM_RIGHT.getChild("TORSO_BOUND_R");
		this.LEG_LEFT_1 = this.TORSO.getChild("LEG_LEFT_1");
		this.BASE_L_1 = this.LEG_LEFT_1.getChild("BASE_L_1");
		this.FORELEG_L_1 = this.LEG_LEFT_1.getChild("FORELEG_L_1");
		this.FEET_L_1 = this.FORELEG_L_1.getChild("FEET_L_1");
		this.LEG_RIGHT_1 = this.TORSO.getChild("LEG_RIGHT_1");
		this.BASE_R_1 = this.LEG_RIGHT_1.getChild("BASE_R_1");
		this.FORELEG_R_1 = this.LEG_RIGHT_1.getChild("FORELEG_R_1");
		this.FEET_R_1 = this.FORELEG_R_1.getChild("FEET_R_1");
		this.LEG_RIGHT_2 = this.TORSO.getChild("LEG_RIGHT_2");
		this.FORELEG_R_2 = this.LEG_RIGHT_2.getChild("FORELEG_R_2");
		this.FEET_R_2 = this.FORELEG_R_2.getChild("FEET_R_2");
		this.BASE_R_2 = this.LEG_RIGHT_2.getChild("BASE_R_2");
		this.LEG_LEFT_2 = this.TORSO.getChild("LEG_LEFT_2");
		this.FORELEG_L_2 = this.LEG_LEFT_2.getChild("FORELEG_L_2");
		this.FEET_L_2 = this.FORELEG_L_2.getChild("FEET_L_2");
		this.BASE_L_2 = this.LEG_LEFT_2.getChild("BASE_L_2");
		this.ARM_LEFT = this.TORSO.getChild("ARM_LEFT");
		this.ARMBASE_L = this.ARM_LEFT.getChild("ARMBASE_L");
		this.FOREARM_L = this.ARMBASE_L.getChild("FOREARM_L");
		this.HAND_L = this.FOREARM_L.getChild("HAND_L");
		this.FINGER_L_4 = this.HAND_L.getChild("FINGER_L_4");
		this.FOREFINGER_L_4 = this.FINGER_L_4.getChild("FOREFINGER_L_4");
		this.FINGER_L_3 = this.HAND_L.getChild("FINGER_L_3");
		this.FOREFINGER_L_3 = this.FINGER_L_3.getChild("FOREFINGER_L_3");
		this.FINGER_L_2 = this.HAND_L.getChild("FINGER_L_2");
		this.FOREFINGER_L_2 = this.FINGER_L_2.getChild("FOREFINGER_L_2");
		this.FINGER_L_1 = this.HAND_L.getChild("FINGER_L_1");
		this.FOREFINGER_L_1 = this.FINGER_L_1.getChild("FOREFINGER_L_1");
		this.TORSO_BOUND_L = this.ARM_LEFT.getChild("TORSO_BOUND_L");
		this.CHESTPLATE = this.TORSO.getChild("CHESTPLATE");
		this.UPPER = this.FRONT_BODY.getChild("UPPER");
		this.SECTION_5 = this.UPPER.getChild("SECTION_5");
		this.SUBSECTION_5_1 = this.SECTION_5.getChild("SUBSECTION_5_1");
		this.SECTION_4 = this.SECTION_5.getChild("SECTION_4");
		this.SUBSECTION_4_1 = this.SECTION_4.getChild("SUBSECTION_4_1");
		this.SECTION_3 = this.SECTION_4.getChild("SECTION_3");
		this.SUBSECTION_3_1 = this.SECTION_3.getChild("SUBSECTION_3_1");
		this.SECTION_2 = this.SECTION_3.getChild("SECTION_2");
		this.SUBSECTION_2_1 = this.SECTION_2.getChild("SUBSECTION_2_1");
		this.SECTION_1 = this.SECTION_2.getChild("SECTION_1");
		this.SUBSECTION_1_1 = this.SECTION_1.getChild("SUBSECTION_1_1");
		this.HEAD = this.SECTION_1.getChild("HEAD");
		this.JAW = this.HEAD.getChild("JAW");
		this.FOREHEAD = this.HEAD.getChild("FOREHEAD");
		this.MANE = this.FOREHEAD.getChild("MANE");
		this.FIXED_JAW = this.HEAD.getChild("FIXED_JAW");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition TAIL = FULL.addOrReplaceChild("TAIL", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition SECTION_6 = TAIL.addOrReplaceChild("SECTION_6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0904F, 6.7868F, 0.0F, 0.1309F, 0.0F));

		PartDefinition SUBSECTION_6_1 = SECTION_6.addOrReplaceChild("SUBSECTION_6_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = SUBSECTION_6_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(70, 8).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4175F, 0.3483F, -0.4584F, -0.6545F, 0.0873F, -0.2182F));

		PartDefinition cube_r2 = SUBSECTION_6_1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 8).addBox(-1.5F, -0.5F, -4.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.3408F, -0.4584F, -0.6109F, -0.0873F, 0.3054F));

		PartDefinition cube_r3 = SUBSECTION_6_1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(72, 78).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4175F, 1.0983F, -0.9584F, -0.48F, 0.0873F, 0.7854F));

		PartDefinition SECTION_7 = SECTION_6.addOrReplaceChild("SECTION_7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.7771F, 4.2223F, 1.6118F, 0.0F, 0.1309F, 0.0F));

		PartDefinition SUBSECTION_7_1 = SECTION_7.addOrReplaceChild("SUBSECTION_7_1", CubeListBuilder.create(), PartPose.offset(-0.7771F, -4.0223F, 3.6882F));

		PartDefinition cube_r4 = SUBSECTION_7_1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(52, 36).addBox(-1.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.2404F, -0.3368F, 0.0F, -0.2182F, -1.0036F));

		PartDefinition cube_r5 = SUBSECTION_7_1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(40, 56).addBox(0.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 3.9438F, -0.4868F, 0.0F, -0.1309F, -0.3054F));

		PartDefinition cube_r6 = SUBSECTION_7_1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(6, 52).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7853F, 2.6377F, -3.3046F, 0.0F, -0.0873F, 0.7854F));

		PartDefinition cube_r7 = SUBSECTION_7_1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 36).addBox(-1.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.9904F, -6.0868F, 0.0F, -0.2182F, -1.0036F));

		PartDefinition cube_r8 = SUBSECTION_7_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 56).addBox(0.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 4.6938F, -6.2368F, 0.0F, -0.1309F, -0.3054F));

		PartDefinition cube_r9 = SUBSECTION_7_1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(29, 53).addBox(-1.0F, -1.5F, -3.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 7.4904F, -1.0868F, 0.0F, 0.2182F, 1.0036F));

		PartDefinition cube_r10 = SUBSECTION_7_1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(18, 59).addBox(0.0F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6224F, 4.4438F, -5.4868F, 0.0F, 0.1309F, 0.3054F));

		PartDefinition cube_r11 = SUBSECTION_7_1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 49).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7916F, 2.4896F, -3.3918F, 0.0F, 0.0873F, -0.829F));

		PartDefinition cube_r12 = SUBSECTION_7_1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(6, 52).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7853F, 1.8877F, 2.4454F, 0.0F, -0.0873F, 0.7854F));

		PartDefinition cube_r13 = SUBSECTION_7_1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(29, 53).addBox(-1.0F, -1.5F, -3.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 6.7404F, 4.6632F, 0.0F, 0.2182F, 1.0036F));

		PartDefinition cube_r14 = SUBSECTION_7_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(56, 49).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7916F, 1.7396F, 2.3582F, 0.0F, 0.0873F, -0.829F));

		PartDefinition cube_r15 = SUBSECTION_7_1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 59).addBox(0.0F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6224F, 3.6938F, 0.2632F, 0.0F, 0.1309F, 0.3054F));

		PartDefinition cube_r16 = SUBSECTION_7_1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(44, 11).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5904F, -0.0868F, 0.1134F, 0.0F, 0.0F));

		PartDefinition SUBSECTION_7_2 = SECTION_7.addOrReplaceChild("SUBSECTION_7_2", CubeListBuilder.create(), PartPose.offset(-0.7771F, -4.2223F, 10.0882F));

		PartDefinition cube_r17 = SUBSECTION_7_2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(87, 64).addBox(0.0F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.5904F, 1.3132F, 0.0F, 0.1745F, -1.0036F));

		PartDefinition cube_r18 = SUBSECTION_7_2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(88, 73).addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2224F, 4.8938F, 1.3132F, 0.0F, 0.1745F, -0.3054F));

		PartDefinition cube_r19 = SUBSECTION_7_2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(89, 38).addBox(1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2598F, 2.1779F, 1.3132F, 0.0F, 0.1745F, 0.6981F));

		PartDefinition cube_r20 = SUBSECTION_7_2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(17, 89).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7224F, 4.8938F, 1.3132F, 0.0F, -0.1745F, 0.3054F));

		PartDefinition cube_r21 = SUBSECTION_7_2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(90, 47).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.8404F, 1.3132F, 0.0F, -0.1745F, 1.0036F));

		PartDefinition cube_r22 = SUBSECTION_7_2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(51, 53).addBox(-1.5F, 0.0F, -3.4F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0904F, -0.2868F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r23 = SUBSECTION_7_2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(90, 85).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2598F, 2.1779F, 1.3132F, 0.0F, -0.1745F, -0.6981F));

		PartDefinition LEG_LEFT_3 = SECTION_7.addOrReplaceChild("LEG_LEFT_3", CubeListBuilder.create(), PartPose.offsetAndRotation(4.4457F, 0.0F, 7.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition BASE_L_3 = LEG_LEFT_3.addOrReplaceChild("BASE_L_3", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r24 = BASE_L_3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(154, 17).addBox(-2.7329F, -0.1568F, -0.9724F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition cube_r25 = BASE_L_3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(154, 48).addBox(-2.7489F, -1.0292F, -0.9727F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r26 = BASE_L_3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(117, 106).addBox(-1.7489F, -0.0292F, -0.9727F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r27 = BASE_L_3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(113, 118).addBox(-1.7329F, -1.1568F, -0.9724F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition FORELEG_L_3 = LEG_LEFT_3.addOrReplaceChild("FORELEG_L_3", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -3.0F, 2.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition cube_r28 = FORELEG_L_3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(5, 152).addBox(-0.5F, -3.055F, -1.067F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.853F, 2.1723F, -0.3004F, 0.0F, -0.2618F, 0.2182F));

		PartDefinition cube_r29 = FORELEG_L_3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(113, 156).addBox(-1.6909F, -0.0168F, -1.2913F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3015F, -0.0436F, 0.1014F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition FEET_L_3 = FORELEG_L_3.addOrReplaceChild("FEET_L_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 8.0F, 1.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r30 = FEET_L_3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(57, 80).addBox(-0.5959F, -1.7357F, -0.8903F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.5F, -0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_3 = SECTION_7.addOrReplaceChild("LEG_RIGHT_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 0.0F, 7.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition BASE_R_3 = LEG_RIGHT_3.addOrReplaceChild("BASE_R_3", CubeListBuilder.create(), PartPose.offset(-7.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r31 = BASE_R_3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(153, 89).addBox(-0.5801F, -0.0421F, -0.7319F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition cube_r32 = BASE_R_3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(152, 153).addBox(-0.5789F, -0.9682F, -0.7323F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r33 = BASE_R_3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(117, 90).addBox(-4.5789F, 0.0318F, -0.7323F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r34 = BASE_R_3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(117, 98).addBox(-4.5801F, -1.0421F, -0.7319F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition FORELEG_R_3 = LEG_RIGHT_3.addOrReplaceChild("FORELEG_R_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, -3.0F, 2.0F, 0.0F, 0.0F, 1.6144F));

		PartDefinition cube_r35 = FORELEG_R_3.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(72, 151).addBox(-0.355F, -3.9462F, -0.3272F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4716F, 3.5312F, -0.4039F, 0.0F, 0.2618F, -0.0873F));

		PartDefinition cube_r36 = FORELEG_R_3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(146, 155).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3015F, -0.5436F, 0.3514F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition FEET_R_3 = FORELEG_R_3.addOrReplaceChild("FEET_R_3", CubeListBuilder.create(), PartPose.offset(1.0F, 7.25F, 0.0F));

		PartDefinition cube_r37 = FEET_R_3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(77, 51).addBox(-0.5225F, -1.47F, -0.8309F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 0.0F, 0.0873F, 0.1745F, 0.3054F));

		PartDefinition SECTION_8 = SECTION_7.addOrReplaceChild("SECTION_8", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 0.0F, 12.6F, 0.0F, -0.0436F, 0.0F));

		PartDefinition SUBSECTION_8_1 = SECTION_8.addOrReplaceChild("SUBSECTION_8_1", CubeListBuilder.create(), PartPose.offset(-0.2771F, -3.9223F, 2.8882F));

		PartDefinition cube_r38 = SUBSECTION_8_1.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(7, 42).addBox(0.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.3904F, 0.3132F, 0.0F, -0.2182F, -1.0036F));

		PartDefinition cube_r39 = SUBSECTION_8_1.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(18, 49).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0776F, 4.2643F, 3.3333F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r40 = SUBSECTION_8_1.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(34, 43).addBox(1.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4598F, 1.6279F, 0.3132F, 0.0F, -0.0873F, 0.6981F));

		PartDefinition cube_r41 = SUBSECTION_8_1.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(45, 46).addBox(-1.0F, -1.5F, 2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.3904F, -0.4368F, 0.0F, 0.2182F, 1.0036F));

		PartDefinition cube_r42 = SUBSECTION_8_1.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(50, 6).addBox(-2.0F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4598F, 1.6279F, 0.3132F, 0.0F, 0.0873F, -0.6981F));

		PartDefinition cube_r43 = SUBSECTION_8_1.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(50, 25).addBox(-1.0F, -1.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6224F, 4.5938F, 0.4132F, 0.0F, 0.1309F, 0.3054F));

		PartDefinition cube_r44 = SUBSECTION_8_1.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(15, 0).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4904F, 0.8132F, 0.1134F, 0.0F, 0.0F));

		PartDefinition SUBSECTION_8_2 = SECTION_8.addOrReplaceChild("SUBSECTION_8_2", CubeListBuilder.create(), PartPose.offset(-1.7771F, 3.1182F, 9.9014F));

		PartDefinition cube_r45 = SUBSECTION_8_2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(33, 90).addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.75F, 1.6F, 0.0F, 0.1745F, -1.0036F));

		PartDefinition cube_r46 = SUBSECTION_8_2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(49, 90).addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7224F, -2.4466F, 1.6F, 0.0F, 0.1745F, -0.3054F));

		PartDefinition cube_r47 = SUBSECTION_8_2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(66, 91).addBox(1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7598F, -5.1626F, 1.6F, 0.0F, 0.1745F, 0.6981F));

		PartDefinition cube_r48 = SUBSECTION_8_2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(5, 92).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7224F, -2.4466F, 1.6F, 0.0F, -0.1745F, 0.3054F));

		PartDefinition cube_r49 = SUBSECTION_8_2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(94, 4).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -0.75F, 1.6F, 0.0F, -0.1745F, 1.0036F));

		PartDefinition cube_r50 = SUBSECTION_8_2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(56, 23).addBox(-1.5F, 0.0F, -3.4F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.25F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r51 = SUBSECTION_8_2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(76, 94).addBox(-2.0F, -1.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7598F, -5.1626F, 1.6F, 0.0F, -0.1745F, -0.6981F));

		PartDefinition LEG_LEFT_4 = SECTION_8.addOrReplaceChild("LEG_LEFT_4", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9457F, 0.0F, 7.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition BASE_L_4 = LEG_LEFT_4.addOrReplaceChild("BASE_L_4", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r52 = BASE_L_4.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(152, 63).addBox(-3.2575F, -0.1522F, -1.1431F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition cube_r53 = BASE_L_4.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(153, 13).addBox(-3.265F, -0.9362F, -1.1443F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r54 = BASE_L_4.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(26, 119).addBox(-2.265F, 0.0638F, -1.1443F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r55 = BASE_L_4.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(120, 25).addBox(-2.2575F, -1.1522F, -1.1431F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition FORELEG_L_4 = LEG_LEFT_4.addOrReplaceChild("FORELEG_L_4", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -3.0F, 2.0F, 0.0F, 0.0F, -1.4835F));

		PartDefinition cube_r56 = FORELEG_L_4.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(60, 151).addBox(-1.2385F, -4.4836F, -0.8349F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4716F, 3.7812F, -0.4039F, 0.0F, -0.2618F, 0.1745F));

		PartDefinition cube_r57 = FORELEG_L_4.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(80, 99).addBox(-1.0775F, 0.0483F, -1.6795F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1985F, -0.5436F, -0.1486F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition FEET_L_4 = FORELEG_L_4.addOrReplaceChild("FEET_L_4", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, 0.0F));

		PartDefinition cube_r58 = FEET_L_4.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 78).addBox(-0.6895F, -2.2606F, -1.0073F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_4 = SECTION_8.addOrReplaceChild("LEG_RIGHT_4", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.5F, 0.0F, 7.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition BASE_R_4 = LEG_RIGHT_4.addOrReplaceChild("BASE_R_4", CubeListBuilder.create(), PartPose.offset(-6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r59 = BASE_R_4.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(12, 80).addBox(0.0781F, -0.1064F, -0.3764F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition cube_r60 = BASE_R_4.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(151, 1).addBox(0.0808F, -0.9171F, -0.3775F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r61 = BASE_R_4.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(119, 1).addBox(-3.9192F, 0.0829F, -0.3775F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r62 = BASE_R_4.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(10, 119).addBox(-3.9219F, -1.1064F, -0.3764F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition FORELEG_R_4 = LEG_RIGHT_4.addOrReplaceChild("FORELEG_R_4", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -3.0F, 2.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r63 = FORELEG_R_4.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(92, 98).addBox(1.106F, -0.556F, -1.1734F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5515F, -0.0436F, 0.1014F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r64 = FORELEG_R_4.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(54, 151).addBox(-0.0191F, -4.5569F, -0.0371F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2425F, 3.6687F, -0.3492F, 0.0F, 0.3491F, -0.1745F));

		PartDefinition FEET_R_4 = FORELEG_R_4.addOrReplaceChild("FEET_R_4", CubeListBuilder.create(), PartPose.offset(1.0F, 7.5F, 0.25F));

		PartDefinition cube_r65 = FEET_R_4.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(6, 73).addBox(-0.4946F, -2.0209F, -0.2787F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.5F, 0.25F, 0.3927F, 0.1745F, 0.3054F));

		PartDefinition SECTION_9 = SECTION_8.addOrReplaceChild("SECTION_9", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2771F, -0.1223F, 12.4882F, -0.0436F, -0.1309F, 0.0F));

		PartDefinition SUBSECTION_9_1 = SECTION_9.addOrReplaceChild("SUBSECTION_9_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition cube_r66 = SUBSECTION_9_1.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(70, 55).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.862F, 4.7158F, 3.222F, -0.1745F, 0.0F, 2.1817F));

		PartDefinition cube_r67 = SUBSECTION_9_1.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(27, 73).addBox(-1.5F, 0.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 2.2404F, -0.0868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r68 = SUBSECTION_9_1.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(74, 36).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8184F, 4.5309F, 2.2457F, -0.1745F, 0.0F, -2.138F));

		PartDefinition cube_r69 = SUBSECTION_9_1.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(45, 74).addBox(-1.5F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 2.2404F, -0.0868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r70 = SUBSECTION_9_1.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(14, 35).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8904F, -0.0868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_10 = SECTION_9.addOrReplaceChild("SECTION_10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.2F, 6.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition SUBSECTION_10_1 = SECTION_10.addOrReplaceChild("SUBSECTION_10_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition cube_r71 = SUBSECTION_10_1.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(65, 47).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5328F, 4.0562F, 2.4473F, 0.0873F, 0.0F, 2.0944F));

		PartDefinition cube_r72 = SUBSECTION_10_1.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(46, 66).addBox(-1.5F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 2.0404F, -0.0868F, 0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r73 = SUBSECTION_10_1.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(64, 66).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5328F, 4.0562F, 2.4473F, 0.0873F, 0.0F, -2.0944F));

		PartDefinition cube_r74 = SUBSECTION_10_1.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(15, 68).addBox(-1.5F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 2.0404F, -0.0868F, 0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r75 = SUBSECTION_10_1.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(32, 29).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6904F, -0.0868F, 0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_11 = SECTION_10.addOrReplaceChild("SECTION_11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.4F, 6.0F, 0.0436F, -0.0873F, 0.0F));

		PartDefinition SUBSECTION_11_1 = SECTION_11.addOrReplaceChild("SUBSECTION_11_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition cube_r76 = SUBSECTION_11_1.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(62, 39).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1172F, 4.1251F, 2.0601F, -0.0873F, 0.0F, 2.0944F));

		PartDefinition cube_r77 = SUBSECTION_11_1.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(63, 9).addBox(-1.5F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.6404F, -0.3868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r78 = SUBSECTION_11_1.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(63, 18).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1172F, 4.1251F, 2.0601F, -0.0873F, 0.0F, -2.0071F));

		PartDefinition cube_r79 = SUBSECTION_11_1.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(28, 65).addBox(-1.5F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.6404F, -0.3868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r80 = SUBSECTION_11_1.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(30, 18).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2904F, -0.3868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_12 = SECTION_11.addOrReplaceChild("SECTION_12", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.25F, -0.6F, 5.5F, -0.0436F, 0.0873F, 0.0F));

		PartDefinition SUBSECTION_12_1 = SECTION_12.addOrReplaceChild("SUBSECTION_12_1", CubeListBuilder.create(), PartPose.offset(0.25F, -2.0F, 3.0F));

		PartDefinition cube_r81 = SUBSECTION_12_1.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(91, 94).addBox(-1.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 3.8904F, -0.1868F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r82 = SUBSECTION_12_1.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(98, 16).addBox(-1.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.7404F, -0.1868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r83 = SUBSECTION_12_1.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(96, 58).addBox(-0.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 3.8904F, -0.1868F, -0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r84 = SUBSECTION_12_1.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(98, 32).addBox(-0.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.7404F, -0.1868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r85 = SUBSECTION_12_1.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(58, 69).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.3904F, -0.2868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_13 = SECTION_12.addOrReplaceChild("SECTION_13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.25F, 0.0F, 5.8F, 0.0436F, 0.1309F, 0.0F));

		PartDefinition SUBSECTION_13_1 = SECTION_13.addOrReplaceChild("SUBSECTION_13_1", CubeListBuilder.create(), PartPose.offset(1.7F, 1.4904F, 2.7132F));

		PartDefinition cube_r86 = SUBSECTION_13_1.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(101, 88).addBox(-1.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -0.6F, 0.1F, 0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r87 = SUBSECTION_13_1.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(85, 102).addBox(-1.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -2.0F, 0.1F, 0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r88 = SUBSECTION_13_1.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(101, 96).addBox(-0.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, -0.6F, 0.1F, 0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r89 = SUBSECTION_13_1.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(103, 8).addBox(-0.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1F, -2.0F, 0.1F, 0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r90 = SUBSECTION_13_1.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(70, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -3.1F, 0.2F, 0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_14 = SECTION_13.addOrReplaceChild("SECTION_14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.2F, 6.2F, -0.0436F, 0.2182F, 0.0F));

		PartDefinition SUBSECTION_14_1 = SECTION_14.addOrReplaceChild("SUBSECTION_14_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition cube_r91 = SUBSECTION_14_1.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(69, 111).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5675F, 2.7483F, 2.6601F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r92 = SUBSECTION_14_1.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(4, 113).addBox(-1.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r93 = SUBSECTION_14_1.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(112, 4).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5675F, 2.7483F, 2.5601F, -0.0436F, -0.0873F, -1.8762F));

		PartDefinition cube_r94 = SUBSECTION_14_1.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(21, 114).addBox(-0.5F, 0.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r95 = SUBSECTION_14_1.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(81, 25).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.1904F, 0.1132F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_15 = SECTION_14.addOrReplaceChild("SECTION_15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.5F, 5.3F, -0.0436F, 0.2618F, 0.0F));

		PartDefinition SUBSECTION_15_1 = SECTION_15.addOrReplaceChild("SUBSECTION_15_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition cube_r96 = SUBSECTION_15_1.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(106, 105).addBox(0.0F, 0.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.2904F, 0.5132F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_16 = SECTION_15.addOrReplaceChild("SECTION_16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.2904F, 5.5132F, 0.0436F, 0.2094F, 0.0F));

		PartDefinition SUBSECTION_16_1 = SECTION_16.addOrReplaceChild("SUBSECTION_16_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition cube_r97 = SUBSECTION_16_1.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(-1, 0).addBox(-1.0F, 2.0F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r98 = SUBSECTION_16_1.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(116, 61).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1373F, 2.4958F, -2.2939F, -0.1309F, 0.0F, -0.9599F));

		PartDefinition cube_r99 = SUBSECTION_16_1.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(116, 70).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8988F, 2.4759F, -2.0439F, -0.1309F, 0.0F, 1.2654F));

		PartDefinition SECTION_17 = SECTION_16.addOrReplaceChild("SECTION_17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5278F, 5.2689F, -0.0349F, 0.48F, 0.0F));

		PartDefinition SUBSECTION_17_1 = SECTION_17.addOrReplaceChild("SUBSECTION_17_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition cube_r100 = SUBSECTION_17_1.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(107, 97).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.4722F, 0.5311F, 0.0F, 0.0F, 0.7854F));

		PartDefinition SECTION_18 = SECTION_17.addOrReplaceChild("SECTION_18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 5.6F, -0.0873F, 0.2618F, 0.0F));

		PartDefinition SUBSECTION_18_1 = SECTION_18.addOrReplaceChild("SUBSECTION_18_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition cube_r101 = SUBSECTION_18_1.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(20, 93).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9222F, 0.6311F, -0.0873F, 0.0873F, 0.7854F));

		PartDefinition END = SECTION_18.addOrReplaceChild("END", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.0278F, 4.5311F, 0.0873F, 0.5236F, 0.0F));

		PartDefinition cube_r102 = END.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(65, 78).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition FRONT_BODY = FULL.addOrReplaceChild("FRONT_BODY", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 4.0F, 1.0472F, 0.2182F, 0.7854F));

		PartDefinition TORSO = FRONT_BODY.addOrReplaceChild("TORSO", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition BELLY = TORSO.addOrReplaceChild("BELLY", CubeListBuilder.create(), PartPose.offset(0.5F, -6.5F, -2.5F));

		PartDefinition cube_r103 = BELLY.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(99, 135).addBox(0.0F, -2.5F, -1.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -7.0F, -2.0F, -0.1745F, 0.0F, -0.2182F));

		PartDefinition cube_r104 = BELLY.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(63, 136).addBox(-2.5F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.5F, 1.6F, -0.0873F, 0.4363F, 0.0F));

		PartDefinition cube_r105 = BELLY.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(64, 136).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.5F, 1.85F, -0.0873F, -0.3927F, 0.0F));

		PartDefinition cube_r106 = BELLY.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(137, 9).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -7.5F, 2.5F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r107 = BELLY.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(136, 63).addBox(-1.0F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -7.25F, 0.5F, -0.1745F, -0.5672F, 0.0436F));

		PartDefinition cube_r108 = BELLY.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(136, 63).addBox(-1.0F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -7.25F, 0.5F, -0.1745F, 0.6545F, 0.0436F));

		PartDefinition cube_r109 = BELLY.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(11, 127).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7586F, 5.7622F, -3.3249F, 0.4363F, 0.1309F, 0.5236F));

		PartDefinition cube_r110 = BELLY.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(21, 127).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 3.5F, -1.5F, 0.6109F, -0.0436F, 0.2182F));

		PartDefinition cube_r111 = BELLY.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(69, 126).addBox(-0.4F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.55F, 2.0F, 1.0F, 0.5236F, -0.3927F, 0.0873F));

		PartDefinition cube_r112 = BELLY.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(102, 115).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2867F, 4.4728F, -0.4426F, 0.5236F, -0.4363F, 0.0F));

		PartDefinition cube_r113 = BELLY.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(151, 129).addBox(-2.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.7F, 4.4F, 0.48F, 0.0436F, 0.0F));

		PartDefinition cube_r114 = BELLY.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(154, 5).addBox(-2.0F, -2.2F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 2.7F, 3.4F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r115 = BELLY.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(74, 139).addBox(-2.0F, -3.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.3F, 3.4F, 0.1745F, 0.2182F, 0.0F));

		PartDefinition cube_r116 = BELLY.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(46, 126).addBox(-0.0007F, -1.0068F, -1.4102F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8007F, 0.7068F, 2.4102F, 0.3054F, -0.9163F, -0.3927F));

		PartDefinition cube_r117 = BELLY.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(112, 126).addBox(-0.8F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 0.5F, 1.0F, 0.2618F, -0.3491F, -0.3491F));

		PartDefinition cube_r118 = BELLY.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(129, 114).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 1.25F, -2.0F, 0.2618F, -0.0436F, -0.3054F));

		PartDefinition cube_r119 = BELLY.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(130, 47).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 1.5F, -5.5F, 0.2182F, 0.2182F, -0.4363F));

		PartDefinition cube_r120 = BELLY.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(46, 126).addBox(-0.0007F, -1.0068F, -1.4102F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8007F, -2.2932F, 2.4102F, 0.3054F, -0.9163F, -0.3927F));

		PartDefinition cube_r121 = BELLY.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(112, 126).addBox(-0.8F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.5F, 1.0F, 0.2618F, -0.3491F, -0.3491F));

		PartDefinition cube_r122 = BELLY.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(126, 62).addBox(0.0F, 0.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.05F, -3.0F, -2.0F, 0.0F, 0.0436F, 0.3054F));

		PartDefinition cube_r123 = BELLY.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(102, 124).addBox(0.0F, -2.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.75F, -2.0F, 0.0F, 0.0436F, -0.2182F));

		PartDefinition cube_r124 = BELLY.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(91, 123).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5143F, 3.5713F, 1.2276F, 0.1745F, 0.3491F, 0.0F));

		PartDefinition cube_r125 = BELLY.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(91, 123).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2643F, 4.8213F, -3.2724F, 0.3491F, -0.3054F, -0.2182F));

		PartDefinition cube_r126 = BELLY.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(91, 123).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, 6.5F, -1.0F, 0.829F, 0.0436F, -0.2182F));

		PartDefinition cube_r127 = BELLY.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(36, 124).addBox(0.8F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -2.5F, 1.0F, 0.2618F, 0.3491F, 0.3491F));

		PartDefinition cube_r128 = BELLY.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(0, 123).addBox(0.4F, -2.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.5F, 1.0F, 0.1309F, 0.3927F, -0.0873F));

		PartDefinition cube_r129 = BELLY.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(124, 123).addBox(-0.9993F, -3.0068F, -1.4102F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8007F, -2.2932F, 2.4102F, 0.3054F, 0.9163F, 0.3927F));

		PartDefinition cube_r130 = BELLY.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(49, 139).addBox(-1.0F, -3.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.3F, 3.4F, 0.1745F, -0.2182F, 0.0F));

		PartDefinition cube_r131 = BELLY.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(153, 116).addBox(0.0F, -2.2F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 2.7F, 3.4F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r132 = BELLY.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(90, 117).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0384F, 4.4728F, 1.9869F, 0.7854F, 0.4363F, 0.0F));

		PartDefinition cube_r133 = BELLY.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(151, 95).addBox(-1.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.7F, 4.4F, 0.2182F, -0.0436F, 0.0F));

		PartDefinition cube_r134 = BELLY.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(82, 123).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -2.75F, -5.5F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r135 = BELLY.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(59, 123).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.568F, 1.2331F, -4.7723F, 0.0436F, -0.3491F, -0.1309F));

		PartDefinition cube_r136 = BELLY.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(129, 114).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.0F, -2.0F, 0.0F, -0.0436F, -0.3054F));

		PartDefinition cube_r137 = BELLY.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(130, 14).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8233F, 4.7587F, -3.9235F, 0.6545F, -0.0436F, 0.4363F));

		PartDefinition cube_r138 = BELLY.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(130, 47).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -2.75F, -5.0F, 0.0F, 0.2182F, -0.4363F));

		PartDefinition ARM_RIGHT = TORSO.addOrReplaceChild("ARM_RIGHT", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.9F, -14.5F, -3.0F, -0.48F, -0.5672F, -0.4363F));

		PartDefinition ARMBASE_R = ARM_RIGHT.addOrReplaceChild("ARMBASE_R", CubeListBuilder.create(), PartPose.offset(-0.0938F, 1.4372F, -1.25F));

		PartDefinition cube_r139 = ARMBASE_R.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(129, 72).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, -0.1309F, -0.2182F));

		PartDefinition cube_r140 = ARMBASE_R.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(45, 54).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5072F, 0.6192F, -0.25F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r141 = ARMBASE_R.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(57, 32).addBox(-1.0F, -0.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5062F, -0.5372F, -0.25F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r142 = ARMBASE_R.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(131, 5).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -0.5F, 0.0F, 0.0873F, -0.2182F));

		PartDefinition cube_r143 = ARMBASE_R.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(133, 90).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.6F, 1.9F, -0.3F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r144 = ARMBASE_R.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(135, 1).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.6F, 2.3F, -0.3F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r145 = ARMBASE_R.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(121, 34).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9F, 1.3F, -0.3F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r146 = ARMBASE_R.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(123, 58).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9F, 0.9F, -0.3F, 0.0F, 0.0F, -0.1309F));

		PartDefinition FOREARM_R = ARMBASE_R.addOrReplaceChild("FOREARM_R", CubeListBuilder.create(), PartPose.offsetAndRotation(-15.7098F, 2.4454F, -0.3205F, 1.0908F, 0.0F, 0.4363F));

		PartDefinition cube_r147 = FOREARM_R.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(157, 121).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0792F, 0.3056F, 0.0205F, -0.48F, 0.0F, -0.3054F));

		PartDefinition cube_r148 = FOREARM_R.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(1, 0).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, -0.2618F));

		PartDefinition HAND_R = FOREARM_R.addOrReplaceChild("HAND_R", CubeListBuilder.create(), PartPose.offset(0.6153F, 2.36F, -10.0373F));

		PartDefinition cube_r149 = HAND_R.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(49, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.1745F));

		PartDefinition FINGER_R_4 = HAND_R.addOrReplaceChild("FINGER_R_4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.6347F, 0.89F, -0.2127F, 0.1745F, 0.4363F, 0.0F));

		PartDefinition FOREFINGER_R_4 = FINGER_R_4.addOrReplaceChild("FOREFINGER_R_4", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 0.5F, -1.75F, 0.0F, 1.0036F, 0.0F));

		PartDefinition FINGER_R_3 = HAND_R.addOrReplaceChild("FINGER_R_3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4076F, -0.2636F, -0.5754F, 0.6981F, 0.6545F, 0.829F));

		PartDefinition cube_r150 = FINGER_R_3.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(79, 157).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7078F, -0.54F, -1.2282F, 0.2618F, -0.6109F, -1.1781F));

		PartDefinition FOREFINGER_R_3 = FINGER_R_3.addOrReplaceChild("FOREFINGER_R_3", CubeListBuilder.create(), PartPose.offsetAndRotation(1.25F, -1.0F, -2.25F, 0.6545F, -0.0873F, 0.2618F));

		PartDefinition cube_r151 = FOREFINGER_R_3.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.5F, -2.0F, -0.5672F, 0.0F, -1.3963F));

		PartDefinition cube_r152 = FOREFINGER_R_3.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(70, 3).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1422F, -0.04F, 0.2218F, 0.0873F, 0.0F, -1.3963F));

		PartDefinition FINGER_R_2 = HAND_R.addOrReplaceChild("FINGER_R_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5424F, -0.5136F, -0.9254F, -0.3491F, 0.0436F, 0.0F));

		PartDefinition cube_r153 = FINGER_R_2.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(156, 110).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2422F, -0.29F, -1.2782F, -0.3054F, 0.1309F, -0.4363F));

		PartDefinition FOREFINGER_R_2 = FINGER_R_2.addOrReplaceChild("FOREFINGER_R_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.75F, -0.5F, -2.25F, -0.2182F, 0.3927F, 0.2182F));

		PartDefinition cube_r154 = FOREFINGER_R_2.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(3, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -3.25F, 0.0F, 0.6545F, -0.5672F));

		PartDefinition cube_r155 = FOREFINGER_R_2.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(80, 70).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0078F, -0.09F, -0.9282F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition FINGER_R_1 = HAND_R.addOrReplaceChild("FINGER_R_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4924F, 0.8364F, -0.4254F, 0.0873F, -0.2182F, -0.3491F));

		PartDefinition FOREFINGER_R_1 = FINGER_R_1.addOrReplaceChild("FOREFINGER_R_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.75F, 0.5F, -2.25F, 0.0F, 1.0036F, 0.0F));

		PartDefinition TORSO_BOUND_R = ARM_RIGHT.addOrReplaceChild("TORSO_BOUND_R", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r156 = TORSO_BOUND_R.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(76, 60).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7039F, 3.6005F, 1.6464F, 0.7854F, 0.0F, -0.7418F));

		PartDefinition cube_r157 = TORSO_BOUND_R.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(22, 58).addBox(-2.0F, -0.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.7418F, -0.7418F, -0.3491F));

		PartDefinition cube_r158 = TORSO_BOUND_R.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(9, 88).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.85F, 9.8F, 0.0F, 0.0F, -0.2182F, -0.5672F));

		PartDefinition LEG_LEFT_1 = TORSO.addOrReplaceChild("LEG_LEFT_1", CubeListBuilder.create(), PartPose.offsetAndRotation(5.9229F, -1.7682F, -3.6986F, -0.0436F, -0.8727F, -0.4363F));

		PartDefinition BASE_L_1 = LEG_LEFT_1.addOrReplaceChild("BASE_L_1", CubeListBuilder.create(), PartPose.offset(7.097F, -1.5777F, -2.3996F));

		PartDefinition cube_r159 = BASE_L_1.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(154, 143).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition cube_r160 = BASE_L_1.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(78, 153).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, 0.3002F, -0.1258F));

		PartDefinition cube_r161 = BASE_L_1.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(106, 114).addBox(-2.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 1.1F, 2.0F, 0.0479F, 0.3002F, -0.1258F));

		PartDefinition cube_r162 = BASE_L_1.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(115, 30).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2F, 1.5F, 2.0F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition FORELEG_L_1 = LEG_LEFT_1.addOrReplaceChild("FORELEG_L_1", CubeListBuilder.create(), PartPose.offsetAndRotation(7.097F, -1.9277F, -2.6496F, 0.0F, 0.0F, -1.5272F));

		PartDefinition cube_r163 = FORELEG_L_1.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(1, 105).addBox(0.0F, -3.5F, -1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.397F, 4.6194F, -0.2498F, 0.0F, 0.2618F, 0.2182F));

		PartDefinition cube_r164 = FORELEG_L_1.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(77, 108).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2075F, 0.3035F, -0.1515F, 0.7854F, 0.0F, 0.7854F));

		PartDefinition FEET_L_1 = FORELEG_L_1.addOrReplaceChild("FEET_L_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.8292F, 8.634F, -0.8774F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r165 = FEET_L_1.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(25, 76).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8352F, 1.913F, -0.7227F, -0.0873F, 0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_1 = TORSO.addOrReplaceChild("LEG_RIGHT_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.9229F, -1.7682F, -3.6986F, -0.4363F, -0.829F, -1.0036F));

		PartDefinition BASE_R_1 = LEG_RIGHT_1.addOrReplaceChild("BASE_R_1", CubeListBuilder.create(), PartPose.offset(-7.097F, -1.5777F, -2.3996F));

		PartDefinition cube_r166 = BASE_R_1.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(154, 139).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition cube_r167 = BASE_R_1.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(153, 69).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, -0.3002F, 0.1258F));

		PartDefinition cube_r168 = BASE_R_1.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(113, 75).addBox(-5.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, 1.1F, 2.0F, 0.0479F, -0.3002F, 0.1258F));

		PartDefinition cube_r169 = BASE_R_1.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(88, 114).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2F, 1.5F, 2.0F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition FORELEG_R_1 = LEG_RIGHT_1.addOrReplaceChild("FORELEG_R_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -2.0F, -3.0F, 0.0873F, 0.0F, 1.1345F));

		PartDefinition cube_r170 = FORELEG_R_1.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.903F, 5.0723F, 0.3504F, 0.0F, -0.2618F, -0.2182F));

		PartDefinition cube_r171 = FORELEG_R_1.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(90, 106).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9515F, 0.2564F, -0.0514F, 0.7854F, 0.0F, -0.7854F));

		PartDefinition FEET_R_1 = FORELEG_R_1.addOrReplaceChild("FEET_R_1", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, 9.25F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition cube_r172 = FEET_R_1.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(74, 41).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.75F, -1.0F, -0.0873F, -0.1745F, 0.3054F));

		PartDefinition LEG_RIGHT_2 = TORSO.addOrReplaceChild("LEG_RIGHT_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.8198F, -1.8458F, -0.9018F, 0.4363F, -0.6981F, -1.309F));

		PartDefinition FORELEG_R_2 = LEG_RIGHT_2.addOrReplaceChild("FORELEG_R_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.6839F, -1.6543F, 2.8358F, 0.0F, 0.0F, 1.3526F));

		PartDefinition cube_r173 = FORELEG_R_2.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(100, 118).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.25F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r174 = FORELEG_R_2.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(117, 139).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 4.3159F, -0.1518F, 0.0F, 0.2618F, -0.2182F));

		PartDefinition FEET_R_2 = FORELEG_R_2.addOrReplaceChild("FEET_R_2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0015F, 9.0436F, 0.4486F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r175 = FEET_R_2.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(6, 6).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 1.45F, 0.25F, 0.3927F, 0.1745F, 0.3054F));

		PartDefinition BASE_R_2 = LEG_RIGHT_2.addOrReplaceChild("BASE_R_2", CubeListBuilder.create(), PartPose.offset(-1.7794F, -0.3884F, 0.684F));

		PartDefinition cube_r176 = BASE_R_2.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(115, 38).addBox(-5.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, 0.3002F, 0.1258F));

		PartDefinition cube_r177 = BASE_R_2.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(154, 102).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.2F, 2.0F, -0.0479F, 0.3002F, 0.1258F));

		PartDefinition cube_r178 = BASE_R_2.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(154, 147).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.1F, 2.0F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition cube_r179 = BASE_R_2.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(115, 47).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.4F, 0.0F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition LEG_LEFT_2 = TORSO.addOrReplaceChild("LEG_LEFT_2", CubeListBuilder.create(), PartPose.offsetAndRotation(5.7198F, -2.2458F, -0.9018F, -0.7418F, -0.6545F, -0.2618F));

		PartDefinition FORELEG_L_2 = LEG_LEFT_2.addOrReplaceChild("FORELEG_L_2", CubeListBuilder.create(), PartPose.offsetAndRotation(9.203F, -1.5223F, 1.6004F, 0.0F, 0.0F, -1.5272F));

		PartDefinition cube_r180 = FORELEG_L_2.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(140, 17).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.903F, 4.5723F, 0.6496F, 0.0F, -0.2618F, 0.2182F));

		PartDefinition cube_r181 = FORELEG_L_2.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(22, 123).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5485F, -0.7436F, 1.0514F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition FEET_L_2 = FORELEG_L_2.addOrReplaceChild("FEET_L_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 9.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r182 = FEET_L_2.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(33, 47).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0821F, 1.75F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition BASE_L_2 = LEG_LEFT_2.addOrReplaceChild("BASE_L_2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition cube_r183 = BASE_L_2.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(117, 80).addBox(-2.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, -0.3002F, -0.1258F));

		PartDefinition cube_r184 = BASE_L_2.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(25, 155).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.1F, 2.0F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition cube_r185 = BASE_L_2.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(154, 135).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.2F, 2.0F, -0.0479F, -0.3002F, -0.1258F));

		PartDefinition cube_r186 = BASE_L_2.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(117, 84).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.4F, 0.0F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition ARM_LEFT = TORSO.addOrReplaceChild("ARM_LEFT", CubeListBuilder.create(), PartPose.offsetAndRotation(4.9F, -14.5F, -3.0F, 0.2182F, -0.3491F, -0.3927F));

		PartDefinition ARMBASE_L = ARM_LEFT.addOrReplaceChild("ARMBASE_L", CubeListBuilder.create(), PartPose.offset(12.6938F, 3.3372F, -1.55F));

		PartDefinition cube_r187 = ARMBASE_L.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(135, 44).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r188 = ARMBASE_L.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(135, 80).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r189 = ARMBASE_L.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(123, 94).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, -0.6F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r190 = ARMBASE_L.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(64, 1).addBox(-3.0F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0928F, -1.2808F, 0.05F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r191 = ARMBASE_L.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(12, 72).addBox(-5.0F, -0.5F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0938F, -2.4372F, 0.05F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r192 = ARMBASE_L.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(123, 102).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, -1.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r193 = ARMBASE_L.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(134, 40).addBox(0.5F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, -1.9F, 0.3F, 0.0F, 0.1309F, 0.2182F));

		PartDefinition cube_r194 = ARMBASE_L.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(134, 76).addBox(0.5F, 0.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, -1.9F, -0.2F, 0.0F, -0.0873F, 0.2182F));

		PartDefinition FOREARM_L = ARMBASE_L.addOrReplaceChild("FOREARM_L", CubeListBuilder.create(), PartPose.offsetAndRotation(3.1098F, 0.5454F, -0.0205F, -1.2654F, -0.2182F, 0.2618F));

		PartDefinition cube_r195 = FOREARM_L.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(128, 156).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5792F, -0.4444F, 1.0205F, -0.48F, 0.0F, 0.3054F));

		PartDefinition cube_r196 = FOREARM_L.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(1, 13).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, 0.2618F));

		PartDefinition HAND_L = FOREARM_L.addOrReplaceChild("HAND_L", CubeListBuilder.create(), PartPose.offset(-1.7307F, 3.4564F, -11.3409F));

		PartDefinition cube_r197 = HAND_L.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(57, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8654F, -1.5964F, 1.0536F, 0.2182F, 0.0F, -0.1745F));

		PartDefinition FINGER_L_4 = HAND_L.addOrReplaceChild("FINGER_L_4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.75F, -0.75F, 1.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition FOREFINGER_L_4 = FINGER_L_4.addOrReplaceChild("FOREFINGER_L_4", CubeListBuilder.create(), PartPose.offset(-0.7693F, 1.2936F, -1.1591F));

		PartDefinition FINGER_L_3 = HAND_L.addOrReplaceChild("FINGER_L_3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.7078F, -1.36F, 0.7282F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r198 = FINGER_L_3.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(121, 157).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7078F, -0.54F, -1.2282F, 0.2618F, 0.6109F, 1.1781F));

		PartDefinition FOREFINGER_L_3 = FINGER_L_3.addOrReplaceChild("FOREFINGER_L_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.25F, -0.75F, -2.0F, -0.1309F, 0.0436F, 0.0F));

		PartDefinition cube_r199 = FOREFINGER_L_3.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(9, 5).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.75F, -2.25F, -0.5672F, 0.0F, 1.3963F));

		PartDefinition cube_r200 = FOREFINGER_L_3.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(70, 16).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1422F, -0.29F, -0.0282F, 0.0873F, 0.0F, 1.3963F));

		PartDefinition FINGER_L_2 = HAND_L.addOrReplaceChild("FINGER_L_2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.6578F, -1.61F, 0.3782F, 0.2618F, 0.1745F, -0.3927F));

		PartDefinition FOREFINGER_L_2 = FINGER_L_2.addOrReplaceChild("FOREFINGER_L_2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.75F, -0.5F, -2.25F, 0.3927F, 0.2618F, 0.0F));

		PartDefinition FINGER_L_1 = HAND_L.addOrReplaceChild("FINGER_L_1", CubeListBuilder.create(), PartPose.offsetAndRotation(1.8578F, -0.51F, 0.8782F, 0.0873F, -0.2618F, -0.1745F));

		PartDefinition cube_r201 = FINGER_L_1.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(149, 156).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7422F, 0.31F, -1.0782F, 0.0F, -0.7854F, 0.2618F));

		PartDefinition FOREFINGER_L_1 = FINGER_L_1.addOrReplaceChild("FOREFINGER_L_1", CubeListBuilder.create(), PartPose.offsetAndRotation(1.85F, 0.65F, -2.25F, 0.0436F, 0.0873F, -0.2618F));

		PartDefinition cube_r202 = FOREFINGER_L_1.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.1F, -2.25F, -0.7854F, 0.0F, 0.2182F));

		PartDefinition cube_r203 = FOREFINGER_L_1.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(77, 48).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0922F, -0.14F, 0.0718F, 0.0F, 0.0F, 0.2182F));

		PartDefinition TORSO_BOUND_L = ARM_LEFT.addOrReplaceChild("TORSO_BOUND_L", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r204 = TORSO_BOUND_L.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(100, 1).addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6F, -0.7F, 1.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r205 = TORSO_BOUND_L.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(87, 78).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3168F, -1.4333F, 3.536F, -1.3963F, 0.2618F, 0.3054F));

		PartDefinition CHESTPLATE = TORSO.addOrReplaceChild("CHESTPLATE", CubeListBuilder.create().texOffs(59, 67).addBox(-1.0F, -6.7F, -1.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -9.0F));

		PartDefinition cube_r206 = CHESTPLATE.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(145, 145).addBox(-0.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -4.0F, -2.5F, -0.3927F, 0.2182F, 1.0908F));

		PartDefinition cube_r207 = CHESTPLATE.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(136, 151).addBox(-2.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.5F, -0.6981F, 0.8727F, -1.7017F));

		PartDefinition cube_r208 = CHESTPLATE.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(94, 79).addBox(-2.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -7.0F, -1.5F, -0.3927F, -0.3927F, -1.0908F));

		PartDefinition cube_r209 = CHESTPLATE.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(145, 15).addBox(-2.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.0F, -2.5F, -0.3927F, -0.2182F, -1.0908F));

		PartDefinition cube_r210 = CHESTPLATE.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(30, 138).addBox(-2.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, -2.5F, -0.3491F, -0.0436F, -1.1345F));

		PartDefinition cube_r211 = CHESTPLATE.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(0, 26).addBox(-2.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -2.5F, -0.4363F, 0.4363F, -1.3526F));

		PartDefinition cube_r212 = CHESTPLATE.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(15, 13).addBox(-2.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.0F, -1.5F, -0.5236F, 0.6981F, -1.5272F));

		PartDefinition cube_r213 = CHESTPLATE.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(16, 152).addBox(-0.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, 0.5F, -0.6981F, -0.8727F, 1.7017F));

		PartDefinition cube_r214 = CHESTPLATE.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(36, 148).addBox(-0.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.0F, -1.5F, -0.5236F, -0.6981F, 1.5272F));

		PartDefinition cube_r215 = CHESTPLATE.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(44, 152).addBox(-0.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.0F, -1.5F, -0.3927F, 0.3927F, 1.0908F));

		PartDefinition cube_r216 = CHESTPLATE.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(145, 136).addBox(-0.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, -2.5F, -0.4363F, -0.4363F, 1.3526F));

		PartDefinition cube_r217 = CHESTPLATE.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(138, 111).addBox(-0.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, -2.5F, -0.3491F, 0.0436F, 1.1345F));

		PartDefinition UPPER = FRONT_BODY.addOrReplaceChild("UPPER", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.5F, -3.0F, 0.3491F, -0.3491F, 0.4363F));

		PartDefinition SECTION_5 = UPPER.addOrReplaceChild("SECTION_5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2648F, -0.0453F));

		PartDefinition SUBSECTION_5_1 = SECTION_5.addOrReplaceChild("SUBSECTION_5_1", CubeListBuilder.create().texOffs(145, 35).addBox(-4.75F, 0.2352F, -6.7047F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition cube_r218 = SUBSECTION_5_1.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(7, 144).addBox(-1.0F, 1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.2648F, -2.7047F, -0.0873F, 0.6109F, -0.0436F));

		PartDefinition cube_r219 = SUBSECTION_5_1.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(27, 149).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5202F, 1.2196F, -6.8796F, -0.1309F, 0.5672F, -0.0436F));

		PartDefinition cube_r220 = SUBSECTION_5_1.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(86, 149).addBox(-1.5F, 1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -0.7648F, -1.7047F, 0.0436F, -0.48F, 0.0F));

		PartDefinition cube_r221 = SUBSECTION_5_1.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(96, 149).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, 0.2352F, -1.7047F, 0.0436F, 0.48F, 0.0F));

		PartDefinition cube_r222 = SUBSECTION_5_1.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(122, 133).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -0.9547F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r223 = SUBSECTION_5_1.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(145, 55).addBox(0.0F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.7352F, -2.7047F, -0.0873F, -0.6109F, 0.0436F));

		PartDefinition SECTION_4 = SECTION_5.addOrReplaceChild("SECTION_4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0428F, -0.0436F, 0.0866F));

		PartDefinition SUBSECTION_4_1 = SECTION_4.addOrReplaceChild("SUBSECTION_4_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 0.1F));

		PartDefinition cube_r224 = SUBSECTION_4_1.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(1, 13).addBox(0.0F, 1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -3.5648F, 2.9453F, 0.1745F, -0.9163F, -0.2182F));

		PartDefinition cube_r225 = SUBSECTION_4_1.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(66, 143).addBox(0.0F, 1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -3.5648F, 0.6953F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r226 = SUBSECTION_4_1.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(104, 144).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0648F, 2.9453F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r227 = SUBSECTION_4_1.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(124, 5).addBox(0.0F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.3148F, 3.4453F, 0.1745F, 0.5672F, 0.2182F));

		PartDefinition cube_r228 = SUBSECTION_4_1.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(144, 90).addBox(0.0F, 1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.0648F, 0.6953F, 0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_3 = SECTION_4.addOrReplaceChild("SECTION_3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.7648F, 0.0453F, -0.0012F, 0.0432F, 0.3034F));

		PartDefinition SUBSECTION_3_1 = SECTION_3.addOrReplaceChild("SUBSECTION_3_1", CubeListBuilder.create(), PartPose.offset(0.0F, -4.7352F, 1.8547F));

		PartDefinition cube_r229 = SUBSECTION_3_1.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(56, 143).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, 4.0153F, -3.5495F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r230 = SUBSECTION_3_1.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(147, 49).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6769F, 0.1595F, -2.1732F, 0.7854F, 0.48F, 0.5672F));

		PartDefinition cube_r231 = SUBSECTION_3_1.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(46, 146).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1769F, 1.4095F, -0.1732F, 0.5236F, -0.9599F, -0.48F));

		PartDefinition cube_r232 = SUBSECTION_3_1.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(143, 68).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 3.4248F, -1.5364F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r233 = SUBSECTION_3_1.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(76, 146).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1769F, 2.4095F, -1.1732F, 0.5236F, 0.5236F, 0.48F));

		PartDefinition cube_r234 = SUBSECTION_3_1.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(129, 143).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -1.1047F, 0.6545F, 0.0F, 0.0F));

		PartDefinition SECTION_2 = SECTION_3.addOrReplaceChild("SECTION_2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, -0.6959F, -0.0002F, -0.0873F));

		PartDefinition SUBSECTION_2_1 = SECTION_2.addOrReplaceChild("SUBSECTION_2_1", CubeListBuilder.create(), PartPose.offset(2.4269F, -2.5757F, -1.3185F));

		PartDefinition cube_r235 = SUBSECTION_2_1.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(84, 141).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 1.3526F, -0.1745F, -0.5672F));

		PartDefinition cube_r236 = SUBSECTION_2_1.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(39, 141).addBox(0.0F, 1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(94, 141).addBox(6.1F, 0.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4269F, 2.1058F, 0.6238F, 0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r237 = SUBSECTION_2_1.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(141, 129).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, -1.1743F, 0.5685F, 1.2217F, 0.0F, 0.0F));

		PartDefinition SECTION_1 = SECTION_2.addOrReplaceChild("SECTION_1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, -3.0F, -0.5652F, 0.1735F, 0.0866F));

		PartDefinition SUBSECTION_1_1 = SECTION_1.addOrReplaceChild("SUBSECTION_1_1", CubeListBuilder.create().texOffs(94, 154).addBox(-4.25F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 120).addBox(-4.25F, -6.0F, -1.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(146, 6).addBox(0.25F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(145, 117).addBox(-6.75F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, 2.5626F, -2.1548F));

		PartDefinition cube_r238 = SUBSECTION_1_1.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(12, 124).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition cube_r239 = SUBSECTION_1_1.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(112, 121).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -4.75F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r240 = SUBSECTION_1_1.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(123, 111).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 1.0F, 0.0F, -0.5236F, -2.3562F));

		PartDefinition cube_r241 = SUBSECTION_1_1.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(24, 122).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition HEAD = SECTION_1.addOrReplaceChild("HEAD", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0138F, -0.5633F, 0.3467F, 0.0F));

		PartDefinition JAW = HEAD.addOrReplaceChild("JAW", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.95F, -3.0F, 0.4538F, 0.0F, 0.0F));

		PartDefinition cube_r242 = JAW.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(8, 57).addBox(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.8812F, -10.4716F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r243 = JAW.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(28, 110).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.2198F, -2.75F, -0.2618F, 0.0F, 0.8727F));

		PartDefinition cube_r244 = JAW.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(53, 94).addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.2198F, -2.75F, -0.2618F, 0.0F, -0.8727F));

		PartDefinition cube_r245 = JAW.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(0, 73).addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.4698F, -5.5F, -0.1745F, 0.0F, -0.8727F));

		PartDefinition cube_r246 = JAW.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(21, 101).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4923F, -0.4428F, -9.7659F, -0.3491F, 0.0F, 0.5236F));

		PartDefinition cube_r247 = JAW.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(107, 0).addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4923F, -0.4428F, -9.7659F, -0.3491F, 0.0F, -0.5236F));

		PartDefinition cube_r248 = JAW.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(115, 41).addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7923F, -0.1428F, -8.2159F, -0.2182F, 0.0F, -0.5236F));

		PartDefinition cube_r249 = JAW.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(70, 115).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7923F, -0.1428F, -8.2159F, -0.2182F, 0.0F, 0.5236F));

		PartDefinition cube_r250 = JAW.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(147, 103).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.4698F, -5.5F, -0.1745F, 0.0F, 0.8727F));

		PartDefinition FOREHEAD = HEAD.addOrReplaceChild("FOREHEAD", CubeListBuilder.create().texOffs(132, 93).addBox(3.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 113).addBox(-1.5F, -42.8F, -15.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(131, 24).addBox(-5.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 38.0F, 10.0F));

		PartDefinition cube_r251 = FOREHEAD.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(154, 35).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5758F, -37.8788F, -24.7793F, 0.9163F, -0.1309F, 0.1745F));

		PartDefinition cube_r252 = FOREHEAD.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(154, 56).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2033F, -37.975F, -23.4418F, 0.6981F, -0.2618F, 0.6981F));

		PartDefinition cube_r253 = FOREHEAD.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(81, 9).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -43.4F, -14.4F, -2.6616F, 0.0F, 0.0F));

		PartDefinition cube_r254 = FOREHEAD.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(81, 17).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.45F, -43.15F, -14.4F, -2.6616F, 0.1309F, 0.1309F));

		PartDefinition cube_r255 = FOREHEAD.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(76, 51).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -42.55F, -14.5F, -2.6616F, -0.1309F, -0.3054F));

		PartDefinition cube_r256 = FOREHEAD.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(14, 76).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -41.55F, -14.5F, -2.618F, 0.0436F, -0.7854F));

		PartDefinition cube_r257 = FOREHEAD.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(3, 71).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -40.2F, -20.75F, -2.7925F, -0.0873F, 0.4363F));

		PartDefinition cube_r258 = FOREHEAD.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(154, 72).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5758F, -37.8788F, -24.7793F, 0.9163F, 0.1309F, -0.1745F));

		PartDefinition cube_r259 = FOREHEAD.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(36, 156).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7258F, -35.1389F, -25.2042F, 0.9163F, 0.2618F, -1.0036F));

		PartDefinition cube_r260 = FOREHEAD.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(74, 156).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3758F, -35.2389F, -25.6042F, 1.309F, 0.2618F, -0.6981F));

		PartDefinition cube_r261 = FOREHEAD.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(84, 154).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2033F, -37.975F, -23.4418F, 0.6981F, 0.2618F, -0.6981F));

		PartDefinition cube_r262 = FOREHEAD.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(70, 16).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -39.5F, -19.85F, -2.7925F, -0.1745F, 0.7418F));

		PartDefinition cube_r263 = FOREHEAD.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(69, 70).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -39.5F, -19.85F, -2.7925F, 0.1745F, -0.7418F));

		PartDefinition cube_r264 = FOREHEAD.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(70, 62).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -40.2F, -20.75F, -2.7925F, 0.0873F, -0.4363F));

		PartDefinition cube_r265 = FOREHEAD.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(156, 76).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -36.0051F, -25.7508F, 1.3963F, 0.0F, 0.0F));

		PartDefinition cube_r266 = FOREHEAD.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -40.75F, -20.0F, -2.7925F, 0.0F, 0.0F));

		PartDefinition cube_r267 = FOREHEAD.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(72, 39).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -40.05F, -14.25F, -2.618F, 0.0873F, -1.0472F));

		PartDefinition cube_r268 = FOREHEAD.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(0, 79).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.45F, -43.15F, -14.4F, -2.6616F, -0.1309F, -0.1309F));

		PartDefinition cube_r269 = FOREHEAD.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(131, 105).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -39.3F, -12.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r270 = FOREHEAD.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(115, 50).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -40.55F, -12.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r271 = FOREHEAD.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(36, 77).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -41.3F, -12.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r272 = FOREHEAD.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(54, 78).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -42.55F, -14.5F, -2.6616F, 0.1309F, 0.3054F));

		PartDefinition cube_r273 = FOREHEAD.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(43, 77).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -41.55F, -14.5F, -2.618F, -0.0436F, 0.7854F));

		PartDefinition cube_r274 = FOREHEAD.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(25, 77).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.75F, -40.05F, -14.25F, -2.618F, -0.0873F, 1.0472F));

		PartDefinition cube_r275 = FOREHEAD.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(60, 115).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -41.3F, -12.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r276 = FOREHEAD.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(0, 117).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -40.55F, -12.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r277 = FOREHEAD.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(26, 132).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -39.3F, -12.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r278 = FOREHEAD.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(143, 24).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -37.05F, -22.5F, 0.4363F, -0.3927F, 0.0F));

		PartDefinition cube_r279 = FOREHEAD.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(76, 25).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -37.05F, -22.5F, 0.4363F, 0.3927F, 0.0F));

		PartDefinition cube_r280 = FOREHEAD.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(31, 155).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3758F, -35.2389F, -25.6042F, 1.309F, -0.2618F, 0.6981F));

		PartDefinition cube_r281 = FOREHEAD.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(111, 8).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7258F, -35.1389F, -25.2042F, 0.9163F, -0.2618F, 1.0036F));

		PartDefinition MANE = FOREHEAD.addOrReplaceChild("MANE", CubeListBuilder.create(), PartPose.offset(0.0F, -44.0F, -11.0F));

		PartDefinition FIXED_JAW = HEAD.addOrReplaceChild("FIXED_JAW", CubeListBuilder.create(), PartPose.offset(0.0F, 38.0F, 10.0F));

		PartDefinition cube_r282 = FIXED_JAW.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(51, 132).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r283 = FIXED_JAW.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(130, 33).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, 0.8727F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}