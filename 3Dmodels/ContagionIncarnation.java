// Made with Blockbench 4.10.4
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
		this.TAIL = root.getChild("TAIL");
		this.SECTION_6 = root.getChild("SECTION_6");
		this.SUBSECTION_6_1 = root.getChild("SUBSECTION_6_1");
		this.SECTION_7 = root.getChild("SECTION_7");
		this.SUBSECTION_7_1 = root.getChild("SUBSECTION_7_1");
		this.SUBSECTION_7_2 = root.getChild("SUBSECTION_7_2");
		this.LEG_LEFT_3 = root.getChild("LEG_LEFT_3");
		this.BASE_L_3 = root.getChild("BASE_L_3");
		this.FORELEG_L_3 = root.getChild("FORELEG_L_3");
		this.FEET_L_3 = root.getChild("FEET_L_3");
		this.LEG_RIGHT_3 = root.getChild("LEG_RIGHT_3");
		this.BASE_R_3 = root.getChild("BASE_R_3");
		this.FORELEG_R_3 = root.getChild("FORELEG_R_3");
		this.FEET_R_3 = root.getChild("FEET_R_3");
		this.SECTION_8 = root.getChild("SECTION_8");
		this.SUBSECTION_8_1 = root.getChild("SUBSECTION_8_1");
		this.SUBSECTION_8_2 = root.getChild("SUBSECTION_8_2");
		this.LEG_LEFT_4 = root.getChild("LEG_LEFT_4");
		this.BASE_L_4 = root.getChild("BASE_L_4");
		this.FORELEG_L_4 = root.getChild("FORELEG_L_4");
		this.FEET_L_4 = root.getChild("FEET_L_4");
		this.LEG_RIGHT_4 = root.getChild("LEG_RIGHT_4");
		this.BASE_R_4 = root.getChild("BASE_R_4");
		this.FORELEG_R_4 = root.getChild("FORELEG_R_4");
		this.FEET_R_4 = root.getChild("FEET_R_4");
		this.SECTION_9 = root.getChild("SECTION_9");
		this.SUBSECTION_9_1 = root.getChild("SUBSECTION_9_1");
		this.SECTION_10 = root.getChild("SECTION_10");
		this.SUBSECTION_10_1 = root.getChild("SUBSECTION_10_1");
		this.SECTION_11 = root.getChild("SECTION_11");
		this.SUBSECTION_11_1 = root.getChild("SUBSECTION_11_1");
		this.SECTION_12 = root.getChild("SECTION_12");
		this.SUBSECTION_12_1 = root.getChild("SUBSECTION_12_1");
		this.SECTION_13 = root.getChild("SECTION_13");
		this.SUBSECTION_13_1 = root.getChild("SUBSECTION_13_1");
		this.SECTION_14 = root.getChild("SECTION_14");
		this.SUBSECTION_14_1 = root.getChild("SUBSECTION_14_1");
		this.SECTION_15 = root.getChild("SECTION_15");
		this.SUBSECTION_15_1 = root.getChild("SUBSECTION_15_1");
		this.SECTION_16 = root.getChild("SECTION_16");
		this.SUBSECTION_16_1 = root.getChild("SUBSECTION_16_1");
		this.SECTION_17 = root.getChild("SECTION_17");
		this.SUBSECTION_17_1 = root.getChild("SUBSECTION_17_1");
		this.SECTION_18 = root.getChild("SECTION_18");
		this.SUBSECTION_18_1 = root.getChild("SUBSECTION_18_1");
		this.END = root.getChild("END");
		this.FRONT_BODY = root.getChild("FRONT_BODY");
		this.TORSO = root.getChild("TORSO");
		this.BELLY = root.getChild("BELLY");
		this.ARM_RIGHT = root.getChild("ARM_RIGHT");
		this.ARMBASE_R = root.getChild("ARMBASE_R");
		this.FOREARM_R = root.getChild("FOREARM_R");
		this.HAND_R = root.getChild("HAND_R");
		this.FINGER_R_4 = root.getChild("FINGER_R_4");
		this.FOREFINGER_R_4 = root.getChild("FOREFINGER_R_4");
		this.FINGER_R_3 = root.getChild("FINGER_R_3");
		this.FOREFINGER_R_3 = root.getChild("FOREFINGER_R_3");
		this.FINGER_R_2 = root.getChild("FINGER_R_2");
		this.FOREFINGER_R_2 = root.getChild("FOREFINGER_R_2");
		this.FINGER_R_1 = root.getChild("FINGER_R_1");
		this.FOREFINGER_R_1 = root.getChild("FOREFINGER_R_1");
		this.TORSO_BOUND_R = root.getChild("TORSO_BOUND_R");
		this.LEG_LEFT_1 = root.getChild("LEG_LEFT_1");
		this.BASE_L_1 = root.getChild("BASE_L_1");
		this.FORELEG_L_1 = root.getChild("FORELEG_L_1");
		this.FEET_L_1 = root.getChild("FEET_L_1");
		this.LEG_RIGHT_1 = root.getChild("LEG_RIGHT_1");
		this.BASE_R_1 = root.getChild("BASE_R_1");
		this.FORELEG_R_1 = root.getChild("FORELEG_R_1");
		this.FEET_R_1 = root.getChild("FEET_R_1");
		this.LEG_RIGHT_2 = root.getChild("LEG_RIGHT_2");
		this.FORELEG_R_2 = root.getChild("FORELEG_R_2");
		this.FEET_R_2 = root.getChild("FEET_R_2");
		this.BASE_R_2 = root.getChild("BASE_R_2");
		this.LEG_LEFT_2 = root.getChild("LEG_LEFT_2");
		this.FORELEG_L_2 = root.getChild("FORELEG_L_2");
		this.FEET_L_2 = root.getChild("FEET_L_2");
		this.BASE_L_2 = root.getChild("BASE_L_2");
		this.ARM_LEFT = root.getChild("ARM_LEFT");
		this.ARMBASE_L = root.getChild("ARMBASE_L");
		this.FOREARM_L = root.getChild("FOREARM_L");
		this.HAND_L = root.getChild("HAND_L");
		this.FINGER_L_4 = root.getChild("FINGER_L_4");
		this.FOREFINGER_L_4 = root.getChild("FOREFINGER_L_4");
		this.FINGER_L_3 = root.getChild("FINGER_L_3");
		this.FOREFINGER_L_3 = root.getChild("FOREFINGER_L_3");
		this.FINGER_L_2 = root.getChild("FINGER_L_2");
		this.FOREFINGER_L_2 = root.getChild("FOREFINGER_L_2");
		this.FINGER_L_1 = root.getChild("FINGER_L_1");
		this.FOREFINGER_L_1 = root.getChild("FOREFINGER_L_1");
		this.TORSO_BOUND_L = root.getChild("TORSO_BOUND_L");
		this.CHESTPLATE = root.getChild("CHESTPLATE");
		this.UPPER = root.getChild("UPPER");
		this.SECTION_5 = root.getChild("SECTION_5");
		this.SUBSECTION_5_1 = root.getChild("SUBSECTION_5_1");
		this.SECTION_4 = root.getChild("SECTION_4");
		this.SUBSECTION_4_1 = root.getChild("SUBSECTION_4_1");
		this.SECTION_3 = root.getChild("SECTION_3");
		this.SUBSECTION_3_1 = root.getChild("SUBSECTION_3_1");
		this.SECTION_2 = root.getChild("SECTION_2");
		this.SUBSECTION_2_1 = root.getChild("SUBSECTION_2_1");
		this.SECTION_1 = root.getChild("SECTION_1");
		this.SUBSECTION_1_1 = root.getChild("SUBSECTION_1_1");
		this.HEAD = root.getChild("HEAD");
		this.JAW = root.getChild("JAW");
		this.FOREHEAD = root.getChild("FOREHEAD");
		this.MANE = root.getChild("MANE");
		this.FIXED_JAW = root.getChild("FIXED_JAW");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition TAIL = FULL.addOrReplaceChild("TAIL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition SECTION_6 = TAIL.addOrReplaceChild("SECTION_6", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0904F, 6.7868F));

		PartDefinition SUBSECTION_6_1 = SECTION_6.addOrReplaceChild("SUBSECTION_6_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = SUBSECTION_6_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 18).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r2 = SUBSECTION_6_1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(70, 8).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7904F, 0.6132F, -0.1745F, 0.0F, -0.2618F));

		PartDefinition cube_r3 = SUBSECTION_6_1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(15, 13).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.0904F, 0.2132F, -0.0873F, -0.0436F, -0.2618F));

		PartDefinition cube_r4 = SUBSECTION_6_1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 56).addBox(-1.0F, -7.5F, -1.5F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 5.8796F, -3.7683F, 1.5708F, -0.1745F, -1.5708F));

		PartDefinition cube_r5 = SUBSECTION_6_1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(96, 99).addBox(-1.2F, -9.0F, -1.5F, 2.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, 5.5904F, -0.2868F, 1.4835F, -0.1309F, -1.309F));

		PartDefinition cube_r6 = SUBSECTION_6_1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(27, 9).addBox(-5.5F, -1.0F, -4.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0904F, 0.6132F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r7 = SUBSECTION_6_1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(72, 78).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.7904F, 0.6132F, -0.1745F, 0.0F, 0.2618F));

		PartDefinition cube_r8 = SUBSECTION_6_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(18, 24).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0904F, 0.2132F, -0.0873F, 0.0436F, 0.2618F));

		PartDefinition cube_r9 = SUBSECTION_6_1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(13, 101).addBox(-0.8F, -9.0F, -1.5F, 2.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 5.5904F, -0.2868F, 1.4835F, 0.1309F, 1.309F));

		PartDefinition SECTION_7 = SECTION_6.addOrReplaceChild("SECTION_7", CubeListBuilder.create(), PartPose.offset(-12.2229F, 9.2223F, 11.6118F));

		PartDefinition SUBSECTION_7_1 = SECTION_7.addOrReplaceChild("SUBSECTION_7_1", CubeListBuilder.create(), PartPose.offset(12.2229F, -9.0223F, -6.3118F));

		PartDefinition cube_r10 = SUBSECTION_7_1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(46, 26).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r11 = SUBSECTION_7_1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(45, 30).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.4904F, -0.5868F, 0.0F, -0.2182F, -1.0036F));

		PartDefinition cube_r12 = SUBSECTION_7_1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(33, 50).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 4.6938F, -0.4868F, 0.0F, -0.1309F, -0.3054F));

		PartDefinition cube_r13 = SUBSECTION_7_1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4598F, 1.7279F, -0.5868F, 0.0F, -0.0873F, 0.6981F));

		PartDefinition cube_r14 = SUBSECTION_7_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(22, 47).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.4904F, -0.5868F, 0.0F, 0.2182F, 1.0036F));

		PartDefinition cube_r15 = SUBSECTION_7_1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(49, 43).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4598F, 1.7279F, -0.5868F, 0.0F, 0.0873F, -0.6981F));

		PartDefinition cube_r16 = SUBSECTION_7_1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(11, 53).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6224F, 4.6938F, -0.4868F, 0.0F, 0.1309F, 0.3054F));

		PartDefinition cube_r17 = SUBSECTION_7_1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(102, 73).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.4404F, 0.1132F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r18 = SUBSECTION_7_1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(44, 11).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5904F, -0.0868F, 0.1134F, 0.0F, 0.0F));

		PartDefinition SUBSECTION_7_2 = SECTION_7.addOrReplaceChild("SUBSECTION_7_2", CubeListBuilder.create(), PartPose.offset(12.2229F, -9.2223F, 0.0882F));

		PartDefinition cube_r19 = SUBSECTION_7_2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(28, 34).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r20 = SUBSECTION_7_2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(81, 59).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.5904F, 1.3132F, 0.0F, 0.1745F, -1.0036F));

		PartDefinition cube_r21 = SUBSECTION_7_2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(82, 68).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2224F, 4.8938F, 1.3132F, 0.0F, 0.1745F, -0.3054F));

		PartDefinition cube_r22 = SUBSECTION_7_2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(83, 33).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2598F, 2.1779F, 1.3132F, 0.0F, 0.1745F, 0.6981F));

		PartDefinition cube_r23 = SUBSECTION_7_2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(12, 84).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2224F, 4.8938F, 1.3132F, 0.0F, -0.1745F, 0.3054F));

		PartDefinition cube_r24 = SUBSECTION_7_2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(84, 42).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.5904F, 1.3132F, 0.0F, -0.1745F, 1.0036F));

		PartDefinition cube_r25 = SUBSECTION_7_2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(51, 53).addBox(-1.5F, -1.0F, -3.4F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0904F, -0.2868F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r26 = SUBSECTION_7_2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(84, 80).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2598F, 2.1779F, 1.3132F, 0.0F, -0.1745F, -0.6981F));

		PartDefinition cube_r27 = SUBSECTION_7_2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(103, 13).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 7.3404F, -0.2868F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LEG_LEFT_3 = SECTION_7.addOrReplaceChild("LEG_LEFT_3", CubeListBuilder.create(), PartPose.offset(17.4457F, -5.0F, -3.0F));

		PartDefinition BASE_L_3 = LEG_LEFT_3.addOrReplaceChild("BASE_L_3", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r28 = BASE_L_3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(153, 16).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition cube_r29 = BASE_L_3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(153, 47).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r30 = BASE_L_3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(116, 105).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r31 = BASE_L_3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(112, 117).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition FORELEG_L_3 = LEG_LEFT_3.addOrReplaceChild("FORELEG_L_3", CubeListBuilder.create(), PartPose.offset(8.0F, -3.0F, 2.0F));

		PartDefinition cube_r32 = FORELEG_L_3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(4, 151).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.603F, 2.6723F, -0.0504F, 0.0F, -0.2618F, 0.2182F));

		PartDefinition cube_r33 = FORELEG_L_3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(111, 155).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition cube_r34 = FORELEG_L_3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(10, 151).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.303F, 2.7723F, -0.0504F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition FEET_L_3 = FORELEG_L_3.addOrReplaceChild("FEET_L_3", CubeListBuilder.create(), PartPose.offset(-1.0F, 8.0F, 1.0F));

		PartDefinition cube_r35 = FEET_L_3.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(72, 78).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.1745F, -0.3054F));

		PartDefinition cube_r36 = FEET_L_3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(57, 80).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.0F, -0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_3 = SECTION_7.addOrReplaceChild("LEG_RIGHT_3", CubeListBuilder.create(), PartPose.offset(8.0F, -5.0F, -3.0F));

		PartDefinition BASE_R_3 = LEG_RIGHT_3.addOrReplaceChild("BASE_R_3", CubeListBuilder.create(), PartPose.offset(-7.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r37 = BASE_R_3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(152, 88).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition cube_r38 = BASE_R_3.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(151, 152).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r39 = BASE_R_3.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(116, 89).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r40 = BASE_R_3.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(116, 97).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition FORELEG_R_3 = LEG_RIGHT_3.addOrReplaceChild("FORELEG_R_3", CubeListBuilder.create(), PartPose.offset(-9.0F, -3.0F, 2.0F));

		PartDefinition cube_r41 = FORELEG_R_3.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(71, 150).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 2.6723F, -0.0504F, 0.0F, 0.2618F, -0.2182F));

		PartDefinition cube_r42 = FORELEG_R_3.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(144, 154).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r43 = FORELEG_R_3.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(105, 150).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.303F, 2.7723F, -0.0504F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition FEET_R_3 = FORELEG_R_3.addOrReplaceChild("FEET_R_3", CubeListBuilder.create(), PartPose.offset(1.0F, 7.25F, 0.0F));

		PartDefinition cube_r44 = FEET_R_3.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(77, 51).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 1.0F, 0.0873F, 0.1745F, 0.3054F));

		PartDefinition cube_r45 = FEET_R_3.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(54, 77).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.75F, 0.5F, 0.3927F, 0.1745F, 0.3054F));

		PartDefinition SECTION_8 = SECTION_7.addOrReplaceChild("SECTION_8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 12.6F));

		PartDefinition SUBSECTION_8_1 = SECTION_8.addOrReplaceChild("SUBSECTION_8_1", CubeListBuilder.create(), PartPose.offset(12.2229F, -8.9223F, -7.1118F));

		PartDefinition cube_r46 = SUBSECTION_8_1.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(27, 6).addBox(0.0F, -3.5F, -1.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r47 = SUBSECTION_8_1.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.3904F, 0.3132F, 0.0F, -0.2182F, -1.0036F));

		PartDefinition cube_r48 = SUBSECTION_8_1.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(11, 43).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 4.5938F, 0.4132F, 0.0F, -0.1309F, -0.3054F));

		PartDefinition cube_r49 = SUBSECTION_8_1.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(27, 37).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4598F, 1.6279F, 0.3132F, 0.0F, -0.0873F, 0.6981F));

		PartDefinition cube_r50 = SUBSECTION_8_1.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(38, 40).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.3904F, 0.3132F, 0.0F, 0.2182F, 1.0036F));

		PartDefinition cube_r51 = SUBSECTION_8_1.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(43, 0).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4598F, 1.6279F, 0.3132F, 0.0F, 0.0873F, -0.6981F));

		PartDefinition cube_r52 = SUBSECTION_8_1.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(43, 19).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6224F, 4.5938F, 0.4132F, 0.0F, 0.1309F, 0.3054F));

		PartDefinition cube_r53 = SUBSECTION_8_1.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(67, 98).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.3404F, 0.2132F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r54 = SUBSECTION_8_1.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(15, 0).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4904F, 0.8132F, 0.1134F, 0.0F, 0.0F));

		PartDefinition SUBSECTION_8_2 = SECTION_8.addOrReplaceChild("SUBSECTION_8_2", CubeListBuilder.create(), PartPose.offset(10.7229F, -1.8818F, -0.0986F));

		PartDefinition cube_r55 = SUBSECTION_8_2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(103, 21).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r56 = SUBSECTION_8_2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(44, 16).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -7.2404F, -0.3132F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r57 = SUBSECTION_8_2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(28, 85).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.75F, 1.6F, 0.0F, 0.1745F, -1.0036F));

		PartDefinition cube_r58 = SUBSECTION_8_2.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(44, 85).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7224F, -2.4466F, 1.6F, 0.0F, 0.1745F, -0.3054F));

		PartDefinition cube_r59 = SUBSECTION_8_2.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(60, 86).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7598F, -5.1626F, 1.6F, 0.0F, 0.1745F, 0.6981F));

		PartDefinition cube_r60 = SUBSECTION_8_2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(0, 87).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7224F, -2.4466F, 1.6F, 0.0F, -0.1745F, 0.3054F));

		PartDefinition cube_r61 = SUBSECTION_8_2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(88, 0).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -0.75F, 1.6F, 0.0F, -0.1745F, 1.0036F));

		PartDefinition cube_r62 = SUBSECTION_8_2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(56, 23).addBox(-1.5F, -1.0F, -3.4F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.25F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r63 = SUBSECTION_8_2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(70, 89).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7598F, -5.1626F, 1.6F, 0.0F, -0.1745F, -0.6981F));

		PartDefinition LEG_LEFT_4 = SECTION_8.addOrReplaceChild("LEG_LEFT_4", CubeListBuilder.create(), PartPose.offset(17.4457F, -5.0F, -3.0F));

		PartDefinition BASE_L_4 = LEG_LEFT_4.addOrReplaceChild("BASE_L_4", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r64 = BASE_L_4.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(151, 62).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition cube_r65 = BASE_L_4.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(152, 12).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r66 = BASE_L_4.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(25, 118).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

		PartDefinition cube_r67 = BASE_L_4.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(119, 24).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

		PartDefinition FORELEG_L_4 = LEG_LEFT_4.addOrReplaceChild("FORELEG_L_4", CubeListBuilder.create(), PartPose.offset(8.0F, -3.0F, 2.0F));

		PartDefinition cube_r68 = FORELEG_L_4.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(59, 150).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.603F, 2.6723F, -0.0504F, 0.0F, -0.2618F, 0.2182F));

		PartDefinition cube_r69 = FORELEG_L_4.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(78, 98).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition cube_r70 = FORELEG_L_4.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(65, 150).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.303F, 2.7723F, -0.0504F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition FEET_L_4 = FORELEG_L_4.addOrReplaceChild("FEET_L_4", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, 0.0F));

		PartDefinition cube_r71 = FEET_L_4.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(65, 77).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 0.0873F, -0.1745F, -0.3054F));

		PartDefinition cube_r72 = FEET_L_4.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(0, 78).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_4 = SECTION_8.addOrReplaceChild("LEG_RIGHT_4", CubeListBuilder.create(), PartPose.offset(7.0F, -5.0F, -3.0F));

		PartDefinition BASE_R_4 = LEG_RIGHT_4.addOrReplaceChild("BASE_R_4", CubeListBuilder.create(), PartPose.offset(-6.197F, -2.4777F, 1.6996F));

		PartDefinition cube_r73 = BASE_R_4.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(11, 79).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition cube_r74 = BASE_R_4.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(150, 0).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r75 = BASE_R_4.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(118, 0).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

		PartDefinition cube_r76 = BASE_R_4.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(9, 118).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

		PartDefinition FORELEG_R_4 = LEG_RIGHT_4.addOrReplaceChild("FORELEG_R_4", CubeListBuilder.create(), PartPose.offset(-8.0F, -3.0F, 2.0F));

		PartDefinition cube_r77 = FORELEG_R_4.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(71, 58).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 2.6723F, -0.0504F, 0.0F, 0.2618F, -0.2182F));

		PartDefinition cube_r78 = FORELEG_R_4.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(90, 97).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r79 = FORELEG_R_4.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(53, 150).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.303F, 2.7723F, -0.0504F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition FEET_R_4 = FORELEG_R_4.addOrReplaceChild("FEET_R_4", CubeListBuilder.create(), PartPose.offset(1.0F, 7.5F, 0.25F));

		PartDefinition cube_r80 = FEET_R_4.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(0, 73).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.75F, 0.0873F, 0.1745F, 0.3054F));

		PartDefinition cube_r81 = FEET_R_4.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(6, 73).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.5F, 0.25F, 0.3927F, 0.1745F, 0.3054F));

		PartDefinition SECTION_9 = SECTION_8.addOrReplaceChild("SECTION_9", CubeListBuilder.create(), PartPose.offset(12.2229F, -8.1223F, 5.4882F));

		PartDefinition SUBSECTION_9_1 = SECTION_9.addOrReplaceChild("SUBSECTION_9_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r82 = SUBSECTION_9_1.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(0, 43).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r83 = SUBSECTION_9_1.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(65, 50).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 3.5904F, -0.0868F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r84 = SUBSECTION_9_1.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(22, 68).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.4904F, -0.0868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r85 = SUBSECTION_9_1.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(58, 95).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.8904F, -0.0868F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r86 = SUBSECTION_9_1.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(102, 55).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.5404F, 0.4132F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r87 = SUBSECTION_9_1.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(0, 96).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.8904F, -0.0868F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r88 = SUBSECTION_9_1.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(69, 31).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 3.5904F, -0.0868F, -0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r89 = SUBSECTION_9_1.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(40, 69).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.4904F, -0.0868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r90 = SUBSECTION_9_1.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(14, 35).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8904F, -0.0868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_10 = SECTION_9.addOrReplaceChild("SECTION_10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2F, 6.0F));

		PartDefinition SUBSECTION_10_1 = SECTION_10.addOrReplaceChild("SUBSECTION_10_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r91 = SUBSECTION_10_1.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(69, 28).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r92 = SUBSECTION_10_1.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(60, 42).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 3.3904F, -0.0868F, 0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r93 = SUBSECTION_10_1.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(41, 61).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.2904F, -0.0868F, 0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r94 = SUBSECTION_10_1.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(42, 94).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.6904F, -0.0868F, -0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r95 = SUBSECTION_10_1.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(39, 102).addBox(0.5F, -1.0F, -2.6F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.3404F, -0.5868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r96 = SUBSECTION_10_1.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(94, 45).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.6904F, -0.0868F, -0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r97 = SUBSECTION_10_1.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(59, 61).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 3.3904F, -0.0868F, 0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r98 = SUBSECTION_10_1.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(10, 63).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.2904F, -0.0868F, 0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r99 = SUBSECTION_10_1.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(32, 29).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6904F, -0.0868F, 0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_11 = SECTION_10.addOrReplaceChild("SECTION_11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.4F, 6.0F));

		PartDefinition SUBSECTION_11_1 = SECTION_11.addOrReplaceChild("SUBSECTION_11_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r100 = SUBSECTION_11_1.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(38, 34).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r101 = SUBSECTION_11_1.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(57, 34).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 2.9904F, -0.3868F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r102 = SUBSECTION_11_1.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(58, 4).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 0.8904F, -0.3868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r103 = SUBSECTION_11_1.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(26, 94).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.2904F, -0.3868F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r104 = SUBSECTION_11_1.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(23, 102).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.9404F, 0.1132F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r105 = SUBSECTION_11_1.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(94, 36).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.2904F, -0.3868F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r106 = SUBSECTION_11_1.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(58, 13).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 2.9904F, -0.3868F, -0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r107 = SUBSECTION_11_1.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(23, 60).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 0.8904F, -0.3868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r108 = SUBSECTION_11_1.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(30, 18).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2904F, -0.3868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_12 = SECTION_11.addOrReplaceChild("SECTION_12", CubeListBuilder.create(), PartPose.offset(0.0F, 0.4F, 5.5F));

		PartDefinition SUBSECTION_12_1 = SECTION_12.addOrReplaceChild("SUBSECTION_12_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r109 = SUBSECTION_12_1.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r110 = SUBSECTION_12_1.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(10, 93).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 3.4904F, -0.0868F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r111 = SUBSECTION_12_1.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(86, 89).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 2.8904F, -0.1868F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r112 = SUBSECTION_12_1.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(93, 11).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.4904F, -0.1868F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r113 = SUBSECTION_12_1.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(102, 64).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.0404F, 0.6132F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r114 = SUBSECTION_12_1.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(93, 19).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 3.4904F, -0.0868F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r115 = SUBSECTION_12_1.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(91, 53).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 2.8904F, -0.1868F, -0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r116 = SUBSECTION_12_1.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(93, 27).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.4904F, -0.1868F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r117 = SUBSECTION_12_1.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(58, 69).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.3904F, -0.2868F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_13 = SECTION_12.addOrReplaceChild("SECTION_13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.8F));

		PartDefinition SUBSECTION_13_1 = SECTION_13.addOrReplaceChild("SUBSECTION_13_1", CubeListBuilder.create(), PartPose.offset(1.7F, 3.4904F, -0.2868F));

		PartDefinition cube_r118 = SUBSECTION_13_1.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(92, 62).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r119 = SUBSECTION_13_1.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(96, 83).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -0.6F, 0.1F, 0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r120 = SUBSECTION_13_1.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(80, 97).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -2.0F, 0.1F, 0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r121 = SUBSECTION_13_1.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(55, 103).addBox(0.5F, -1.0F, -2.6F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 0.55F, -0.45F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r122 = SUBSECTION_13_1.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(92, 71).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r123 = SUBSECTION_13_1.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(96, 91).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, -0.6F, 0.1F, 0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r124 = SUBSECTION_13_1.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(98, 3).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1F, -2.0F, 0.1F, 0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r125 = SUBSECTION_13_1.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(70, 0).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -3.1F, 0.2F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r126 = SUBSECTION_13_1.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(60, 39).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, -3.4904F, 0.2868F, -1.0472F, 0.0F, 0.0F));

		PartDefinition SECTION_14 = SECTION_13.addOrReplaceChild("SECTION_14", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2F, 5.2F));

		PartDefinition SUBSECTION_14_1 = SECTION_14.addOrReplaceChild("SUBSECTION_14_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r127 = SUBSECTION_14_1.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(43, 7).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r128 = SUBSECTION_14_1.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(106, 81).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 3.2904F, 0.3132F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r129 = SUBSECTION_14_1.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(65, 107).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 2.6904F, 0.2132F, -0.0873F, 0.0F, 1.8762F));

		PartDefinition cube_r130 = SUBSECTION_14_1.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(0, 109).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r131 = SUBSECTION_14_1.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(33, 110).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.8404F, 0.4632F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r132 = SUBSECTION_14_1.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(106, 89).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 3.2904F, 0.3132F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r133 = SUBSECTION_14_1.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(108, 0).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 2.6904F, 0.2132F, -0.0873F, 0.0F, -1.8762F));

		PartDefinition cube_r134 = SUBSECTION_14_1.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(17, 110).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r135 = SUBSECTION_14_1.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(81, 25).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.1904F, 0.1132F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_15 = SECTION_14.addOrReplaceChild("SECTION_15", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 5.3F));

		PartDefinition SUBSECTION_15_1 = SECTION_15.addOrReplaceChild("SUBSECTION_15_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r136 = SUBSECTION_15_1.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(12, 28).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r137 = SUBSECTION_15_1.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(104, 47).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 2.1904F, 0.3132F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r138 = SUBSECTION_15_1.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(113, 8).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 1.2904F, 0.5132F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition cube_r139 = SUBSECTION_15_1.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(49, 111).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.7404F, 0.7132F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r140 = SUBSECTION_15_1.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(78, 105).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 2.1904F, 0.3132F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r141 = SUBSECTION_15_1.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(113, 16).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 1.2904F, 0.5132F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r142 = SUBSECTION_15_1.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(106, 105).addBox(0.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.2904F, 0.5132F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_16 = SECTION_15.addOrReplaceChild("SECTION_16", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7096F, 5.5132F));

		PartDefinition SUBSECTION_16_1 = SECTION_16.addOrReplaceChild("SUBSECTION_16_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r143 = SUBSECTION_16_1.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.0F, -3.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r144 = SUBSECTION_16_1.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.8096F, 0.3868F, -1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r145 = SUBSECTION_16_1.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(112, 57).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.5F, 0.5F, -0.1309F, 0.0F, -0.8727F));

		PartDefinition cube_r146 = SUBSECTION_16_1.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(104, 29).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 0.5F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition cube_r147 = SUBSECTION_16_1.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(104, 38).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.5F, 0.5F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition cube_r148 = SUBSECTION_16_1.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(112, 66).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.5F, 0.5F, -0.1309F, 0.0F, 0.8727F));

		PartDefinition SECTION_17 = SECTION_16.addOrReplaceChild("SECTION_17", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5278F, 5.2689F));

		PartDefinition SUBSECTION_17_1 = SECTION_17.addOrReplaceChild("SUBSECTION_17_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r149 = SUBSECTION_17_1.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(7, 11).addBox(0.0F, -1.5F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r150 = SUBSECTION_17_1.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(106, 97).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9722F, 0.5311F, 0.0F, 0.0F, 0.7854F));

		PartDefinition SECTION_18 = SECTION_17.addOrReplaceChild("SECTION_18", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.6F));

		PartDefinition SUBSECTION_18_1 = SECTION_18.addOrReplaceChild("SUBSECTION_18_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r151 = SUBSECTION_18_1.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(15, 7).addBox(0.0F, -0.5F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r152 = SUBSECTION_18_1.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(82, 0).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 0.9722F, 0.6311F, -0.0873F, -0.0873F, 0.7854F));

		PartDefinition cube_r153 = SUBSECTION_18_1.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(38, 85).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 0.9722F, 0.6311F, -0.0873F, 0.0873F, -0.7854F));

		PartDefinition cube_r154 = SUBSECTION_18_1.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(80, 89).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2722F, 0.6311F, 0.0873F, -0.0873F, 0.7854F));

		PartDefinition cube_r155 = SUBSECTION_18_1.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(20, 93).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6722F, 0.6311F, -0.0873F, 0.0873F, 0.7854F));

		PartDefinition END = SECTION_18.addOrReplaceChild("END", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9722F, 5.5311F));

		PartDefinition cube_r156 = END.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(65, 78).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition FRONT_BODY = FULL.addOrReplaceChild("FRONT_BODY", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 1.0F));

		PartDefinition TORSO = FRONT_BODY.addOrReplaceChild("TORSO", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition BELLY = TORSO.addOrReplaceChild("BELLY", CubeListBuilder.create(), PartPose.offset(0.5F, -6.5F, -2.5F));

		PartDefinition cube_r157 = BELLY.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(96, 133).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -7.0F, -2.0F, -0.1745F, 0.0F, -0.2182F));

		PartDefinition cube_r158 = BELLY.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(86, 133).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -7.0F, -4.0F, -0.1309F, 0.5236F, -0.3054F));

		PartDefinition cube_r159 = BELLY.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(38, 133).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -7.0F, 0.5F, -0.1745F, -0.6545F, -0.0436F));

		PartDefinition cube_r160 = BELLY.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(0, 133).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.5F, 1.85F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r161 = BELLY.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(10, 136).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.5F, -5.25F, -0.3054F, 0.3491F, 0.0F));

		PartDefinition cube_r162 = BELLY.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(63, 135).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.5F, 1.85F, -0.0873F, -0.3927F, 0.0F));

		PartDefinition cube_r163 = BELLY.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(136, 8).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.5F, 2.5F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r164 = BELLY.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(30, 22).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.8352F, 3.4547F, -1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r165 = BELLY.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(137, 121).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -6.5F, -5.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r166 = BELLY.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(135, 63).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -7.0F, 0.5F, -0.1745F, 0.6545F, 0.0436F));

		PartDefinition cube_r167 = BELLY.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(106, 135).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -2.0F, -0.1745F, 0.0F, 0.2182F));

		PartDefinition cube_r168 = BELLY.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(20, 138).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -7.0F, -4.0F, -0.1309F, -0.5236F, 0.3054F));

		PartDefinition cube_r169 = BELLY.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(10, 127).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 6.5F, -1.0F, 0.829F, -0.0436F, 0.2182F));

		PartDefinition cube_r170 = BELLY.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(20, 127).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 2.5F, -2.0F, 0.0F, -0.0436F, 0.2182F));

		PartDefinition cube_r171 = BELLY.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(68, 126).addBox(-1.4F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 2.5F, 1.0F, 0.1309F, -0.3927F, 0.0873F));

		PartDefinition cube_r172 = BELLY.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(102, 117).addBox(-2.0F, -2.2F, -3.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 4.7F, 4.4F, 0.7854F, -0.0436F, 0.0F));

		PartDefinition cube_r173 = BELLY.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(151, 129).addBox(-2.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.7F, 5.4F, 0.48F, 0.0436F, 0.0F));

		PartDefinition cube_r174 = BELLY.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(152, 4).addBox(-2.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 2.7F, 3.4F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r175 = BELLY.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(58, 9).addBox(1.0F, -2.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.8F, 3.5F, 1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r176 = BELLY.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(10, 60).addBox(1.0F, -2.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.8F, 3.9F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r177 = BELLY.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(73, 138).addBox(-2.0F, -3.0F, -1.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.3F, 3.4F, 0.1745F, 0.2182F, 0.0F));

		PartDefinition cube_r178 = BELLY.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(45, 126).addBox(-1.0007F, -3.0068F, -1.4102F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8007F, -2.2932F, 2.4102F, 0.3054F, -0.9163F, -0.3927F));

		PartDefinition cube_r179 = BELLY.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(111, 126).addBox(-1.8F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.5F, 1.0F, 0.2618F, -0.3491F, -0.3491F));

		PartDefinition cube_r180 = BELLY.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(125, 62).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -2.5F, -2.0F, 0.0F, 0.0436F, 0.3054F));

		PartDefinition cube_r181 = BELLY.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(101, 124).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.5F, -2.0F, 0.0F, 0.0436F, -0.2182F));

		PartDefinition cube_r182 = BELLY.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(91, 124).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3F, 6.5F, -1.0F, 0.829F, 0.0436F, -0.2182F));

		PartDefinition cube_r183 = BELLY.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(35, 124).addBox(-0.2F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -2.5F, 1.0F, 0.2618F, 0.3491F, 0.3491F));

		PartDefinition cube_r184 = BELLY.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(0, 124).addBox(-0.6F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.5F, 1.0F, 0.1309F, 0.3927F, -0.0873F));

		PartDefinition cube_r185 = BELLY.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(123, 123).addBox(-0.9993F, -3.0068F, -1.4102F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8007F, -2.2932F, 2.4102F, 0.3054F, 0.9163F, 0.3927F));

		PartDefinition cube_r186 = BELLY.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(48, 138).addBox(-1.0F, -3.0F, -1.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.3F, 3.4F, 0.1745F, -0.2182F, 0.0F));

		PartDefinition cube_r187 = BELLY.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(151, 115).addBox(-1.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 2.7F, 3.4F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r188 = BELLY.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(88, 117).addBox(-1.0F, -2.2F, -3.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 4.7F, 4.4F, 0.7854F, 0.0436F, 0.0F));

		PartDefinition cube_r189 = BELLY.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(151, 95).addBox(-1.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.7F, 5.4F, 0.48F, -0.0436F, 0.0F));

		PartDefinition cube_r190 = BELLY.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(81, 123).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -2.5F, -5.5F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r191 = BELLY.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(58, 123).addBox(-1.5F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 2.5F, -4.5F, 0.3054F, 0.0F, -0.1309F));

		PartDefinition cube_r192 = BELLY.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(128, 114).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.5F, -2.0F, 0.0F, -0.0436F, -0.3054F));

		PartDefinition cube_r193 = BELLY.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(129, 14).addBox(-0.5F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 2.5F, -4.5F, 0.3054F, 0.0F, 0.1309F));

		PartDefinition cube_r194 = BELLY.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(129, 47).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -2.5F, -5.5F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r195 = BELLY.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(131, 134).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.5F, -5.25F, -0.3054F, -0.3491F, 0.0F));

		PartDefinition ARM_RIGHT = TORSO.addOrReplaceChild("ARM_RIGHT", CubeListBuilder.create(), PartPose.offset(-4.9F, -14.5F, -6.0F));

		PartDefinition ARMBASE_R = ARM_RIGHT.addOrReplaceChild("ARMBASE_R", CubeListBuilder.create(), PartPose.offset(-3.0938F, 1.4372F, 1.75F));

		PartDefinition cube_r196 = ARMBASE_R.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(128, 71).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, -0.2182F));

		PartDefinition cube_r197 = ARMBASE_R.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(44, 53).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4928F, 0.6192F, -0.25F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r198 = ARMBASE_R.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(56, 31).addBox(-1.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5062F, -0.5372F, -0.25F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r199 = ARMBASE_R.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(130, 4).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.0F, 0.0873F, -0.2182F));

		PartDefinition cube_r200 = ARMBASE_R.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(132, 89).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, 1.9F, -0.3F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r201 = ARMBASE_R.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(134, 0).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, 2.3F, -0.3F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r202 = ARMBASE_R.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(120, 33).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9F, 1.3F, -0.3F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r203 = ARMBASE_R.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(122, 57).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9F, 0.9F, -0.3F, 0.0F, 0.0F, -0.1309F));

		PartDefinition FOREARM_R = ARMBASE_R.addOrReplaceChild("FOREARM_R", CubeListBuilder.create(), PartPose.offset(-12.7098F, 2.4454F, -0.3205F));

		PartDefinition cube_r204 = FOREARM_R.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(155, 120).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0792F, 0.3056F, 0.0205F, -0.48F, 0.0F, -0.3054F));

		PartDefinition cube_r205 = FOREARM_R.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, -0.2618F));

		PartDefinition HAND_R = FOREARM_R.addOrReplaceChild("HAND_R", CubeListBuilder.create(), PartPose.offset(0.6153F, 2.36F, -10.0373F));

		PartDefinition cube_r206 = HAND_R.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(49, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.1745F));

		PartDefinition FINGER_R_4 = HAND_R.addOrReplaceChild("FINGER_R_4", CubeListBuilder.create(), PartPose.offset(2.3847F, 1.64F, -3.9627F));

		PartDefinition cube_r207 = FINGER_R_4.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(22, 45).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2693F, -0.5436F, 2.6591F, 0.4363F, -0.7854F, 0.0F));

		PartDefinition FOREFINGER_R_4 = FINGER_R_4.addOrReplaceChild("FOREFINGER_R_4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r208 = FOREFINGER_R_4.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.1309F, 0.0F));

		PartDefinition cube_r209 = FOREFINGER_R_4.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(15, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5693F, -0.1436F, 1.2591F, 0.0873F, -0.1309F, 0.0F));

		PartDefinition FINGER_R_3 = HAND_R.addOrReplaceChild("FINGER_R_3", CubeListBuilder.create(), PartPose.offset(1.9076F, -1.7636F, -4.8254F));

		PartDefinition cube_r210 = FINGER_R_3.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(79, 157).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7922F, 0.96F, 3.0218F, 0.2618F, -0.6109F, -1.1781F));

		PartDefinition FOREFINGER_R_3 = FINGER_R_3.addOrReplaceChild("FOREFINGER_R_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r211 = FOREFINGER_R_3.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, -1.3963F));

		PartDefinition cube_r212 = FOREFINGER_R_3.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(70, 3).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3922F, 0.46F, 2.2218F, 0.0873F, 0.0F, -1.3963F));

		PartDefinition FINGER_R_2 = HAND_R.addOrReplaceChild("FINGER_R_2", CubeListBuilder.create(), PartPose.offset(-1.2924F, -1.2636F, -6.4254F));

		PartDefinition cube_r213 = FINGER_R_2.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(156, 110).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5078F, 0.46F, 4.2218F, -0.3054F, 0.1309F, -0.4363F));

		PartDefinition FOREFINGER_R_2 = FINGER_R_2.addOrReplaceChild("FOREFINGER_R_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r214 = FOREFINGER_R_2.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(3, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6545F, -0.5672F));

		PartDefinition cube_r215 = FOREFINGER_R_2.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(80, 70).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0078F, 0.16F, 2.3218F, 0.0873F, 0.0F, -0.5672F));

		PartDefinition FINGER_R_1 = HAND_R.addOrReplaceChild("FINGER_R_1", CubeListBuilder.create(), PartPose.offset(-2.9924F, 1.3364F, -4.9254F));

		PartDefinition cube_r216 = FINGER_R_1.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(131, 156).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5078F, -0.44F, 3.4218F, 0.0F, 0.7854F, -0.2618F));

		PartDefinition FOREFINGER_R_1 = FINGER_R_1.addOrReplaceChild("FOREFINGER_R_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r217 = FOREFINGER_R_1.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.2182F));

		PartDefinition cube_r218 = FOREFINGER_R_1.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(14, 74).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3078F, -0.24F, 2.3218F, 0.0F, 0.0F, -0.2182F));

		PartDefinition TORSO_BOUND_R = ARM_RIGHT.addOrReplaceChild("TORSO_BOUND_R", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r219 = TORSO_BOUND_R.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(77, 59).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, -0.1309F));

		PartDefinition cube_r220 = TORSO_BOUND_R.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(22, 57).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.7854F, 0.0F, -0.1309F));

		PartDefinition cube_r221 = TORSO_BOUND_R.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(10, 87).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -0.7F, 1.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition LEG_LEFT_1 = TORSO.addOrReplaceChild("LEG_LEFT_1", CubeListBuilder.create(), PartPose.offset(5.9229F, -1.7682F, -3.6986F));

		PartDefinition BASE_L_1 = LEG_LEFT_1.addOrReplaceChild("BASE_L_1", CubeListBuilder.create(), PartPose.offset(7.097F, -1.5777F, -2.3996F));

		PartDefinition cube_r222 = BASE_L_1.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(153, 142).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition cube_r223 = BASE_L_1.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(77, 152).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, 0.3002F, -0.1258F));

		PartDefinition cube_r224 = BASE_L_1.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(105, 113).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 1.1F, 2.0F, 0.0479F, 0.3002F, -0.1258F));

		PartDefinition cube_r225 = BASE_L_1.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(114, 29).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2F, 1.5F, 2.0F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition FORELEG_L_1 = LEG_LEFT_1.addOrReplaceChild("FORELEG_L_1", CubeListBuilder.create(), PartPose.offset(7.097F, -1.9277F, -2.6496F));

		PartDefinition cube_r226 = FORELEG_L_1.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(0, 104).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.897F, 4.6194F, -0.2498F, 0.0F, 0.2618F, 0.2182F));

		PartDefinition cube_r227 = FORELEG_L_1.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(75, 107).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2925F, -0.1965F, -0.1515F, 0.7854F, 0.0F, 0.7854F));

		PartDefinition cube_r228 = FORELEG_L_1.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(122, 140).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.597F, 4.7194F, -0.2498F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition FEET_L_1 = FORELEG_L_1.addOrReplaceChild("FEET_L_1", CubeListBuilder.create(), PartPose.offset(-2.8292F, 8.634F, -0.8774F));

		PartDefinition cube_r229 = FEET_L_1.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(25, 76).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8352F, 1.913F, -0.7227F, -0.0873F, 0.1745F, -0.3054F));

		PartDefinition cube_r230 = FEET_L_1.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(70, 70).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9352F, 1.913F, -0.2227F, -0.3927F, 0.1745F, -0.3054F));

		PartDefinition LEG_RIGHT_1 = TORSO.addOrReplaceChild("LEG_RIGHT_1", CubeListBuilder.create(), PartPose.offset(-5.9229F, -1.7682F, -3.6986F));

		PartDefinition BASE_R_1 = LEG_RIGHT_1.addOrReplaceChild("BASE_R_1", CubeListBuilder.create(), PartPose.offset(-7.097F, -1.5777F, -2.3996F));

		PartDefinition cube_r231 = BASE_R_1.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(153, 138).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition cube_r232 = BASE_R_1.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(152, 68).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, -0.3002F, 0.1258F));

		PartDefinition cube_r233 = BASE_R_1.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(112, 74).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, 1.1F, 2.0F, 0.0479F, -0.3002F, 0.1258F));

		PartDefinition cube_r234 = BASE_R_1.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(87, 113).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2F, 1.5F, 2.0F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition FORELEG_R_1 = LEG_RIGHT_1.addOrReplaceChild("FORELEG_R_1", CubeListBuilder.create(), PartPose.offset(-8.0F, -2.0F, -3.0F));

		PartDefinition cube_r235 = FORELEG_R_1.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.903F, 5.0723F, 0.3504F, 0.0F, -0.2618F, -0.2182F));

		PartDefinition cube_r236 = FORELEG_R_1.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(88, 105).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7015F, 0.2564F, 0.4486F, 0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r237 = FORELEG_R_1.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(140, 47).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 5.1723F, 0.3504F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition FEET_R_1 = FORELEG_R_1.addOrReplaceChild("FEET_R_1", CubeListBuilder.create(), PartPose.offset(1.5F, 9.25F, 0.0F));

		PartDefinition cube_r238 = FEET_R_1.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(74, 41).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.75F, -1.0F, -0.0873F, -0.1745F, 0.3054F));

		PartDefinition cube_r239 = FEET_R_1.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(67, 50).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 1.75F, -0.5F, -0.3927F, -0.1745F, 0.3054F));

		PartDefinition LEG_RIGHT_2 = TORSO.addOrReplaceChild("LEG_RIGHT_2", CubeListBuilder.create(), PartPose.offset(-4.8198F, -1.8458F, -0.9018F));

		PartDefinition FORELEG_R_2 = LEG_RIGHT_2.addOrReplaceChild("FORELEG_R_2", CubeListBuilder.create(), PartPose.offset(-9.6839F, -1.6543F, 2.8358F));

		PartDefinition cube_r240 = FORELEG_R_2.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(98, 117).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition cube_r241 = FORELEG_R_2.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(116, 138).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 4.8159F, 0.0982F, 0.0F, 0.2618F, -0.2182F));

		PartDefinition cube_r242 = FORELEG_R_2.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(139, 140).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3045F, 4.9159F, 0.0982F, 0.0F, 0.3054F, -0.3054F));

		PartDefinition FEET_R_2 = FORELEG_R_2.addOrReplaceChild("FEET_R_2", CubeListBuilder.create(), PartPose.offset(2.0015F, 9.0436F, 0.4486F));

		PartDefinition cube_r243 = FEET_R_2.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(36, 76).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 1.7F, 1.0F, 0.0873F, 0.1745F, 0.3054F));

		PartDefinition cube_r244 = FEET_R_2.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(6, 6).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 1.7F, 0.5F, 0.3927F, 0.1745F, 0.3054F));

		PartDefinition BASE_R_2 = LEG_RIGHT_2.addOrReplaceChild("BASE_R_2", CubeListBuilder.create(), PartPose.offset(-1.7794F, -0.3884F, 0.684F));

		PartDefinition cube_r245 = BASE_R_2.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(114, 37).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, 0.3002F, 0.1258F));

		PartDefinition cube_r246 = BASE_R_2.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(153, 101).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.2F, 2.0F, -0.0479F, 0.3002F, 0.1258F));

		PartDefinition cube_r247 = BASE_R_2.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(153, 146).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.1F, 2.0F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition cube_r248 = BASE_R_2.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(114, 46).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.4F, 0.0F, 0.0F, 0.3054F, 0.3054F));

		PartDefinition LEG_LEFT_2 = TORSO.addOrReplaceChild("LEG_LEFT_2", CubeListBuilder.create(), PartPose.offset(5.7198F, -2.2458F, -0.9018F));

		PartDefinition FORELEG_L_2 = LEG_LEFT_2.addOrReplaceChild("FORELEG_L_2", CubeListBuilder.create(), PartPose.offset(9.203F, -1.5223F, 1.6004F));

		PartDefinition cube_r249 = FORELEG_L_2.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(139, 16).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.903F, 5.0723F, 0.6496F, 0.0F, -0.2618F, 0.2182F));

		PartDefinition cube_r250 = FORELEG_L_2.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(20, 122).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2985F, 0.2564F, 0.5514F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition cube_r251 = FORELEG_L_2.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(0, 141).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.603F, 5.1723F, 0.6496F, 0.0F, -0.3054F, 0.3054F));

		PartDefinition FEET_L_2 = FORELEG_L_2.addOrReplaceChild("FEET_L_2", CubeListBuilder.create(), PartPose.offset(-2.0F, 9.0F, 1.0F));

		PartDefinition cube_r252 = FEET_L_2.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(45, 77).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0179F, 1.75F, 1.0F, 0.0873F, -0.1745F, -0.3054F));

		PartDefinition cube_r253 = FEET_L_2.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(33, 47).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0821F, 1.75F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

		PartDefinition BASE_L_2 = LEG_LEFT_2.addOrReplaceChild("BASE_L_2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition cube_r254 = BASE_L_2.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(116, 79).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, -0.3002F, -0.1258F));

		PartDefinition cube_r255 = BASE_L_2.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(24, 154).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.1F, 2.0F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition cube_r256 = BASE_L_2.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(153, 134).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.2F, 2.0F, -0.0479F, -0.3002F, -0.1258F));

		PartDefinition cube_r257 = BASE_L_2.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(116, 83).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.4F, 0.0F, 0.0F, -0.3054F, -0.3054F));

		PartDefinition ARM_LEFT = TORSO.addOrReplaceChild("ARM_LEFT", CubeListBuilder.create(), PartPose.offset(4.9F, -14.5F, -3.0F));

		PartDefinition ARMBASE_L = ARM_LEFT.addOrReplaceChild("ARMBASE_L", CubeListBuilder.create(), PartPose.offset(12.6938F, 3.3372F, -1.55F));

		PartDefinition cube_r258 = ARMBASE_L.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(134, 43).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r259 = ARMBASE_L.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(134, 79).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r260 = ARMBASE_L.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(122, 93).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, -0.6F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r261 = ARMBASE_L.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0928F, -1.2808F, 0.05F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r262 = ARMBASE_L.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(14, 71).addBox(-3.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0938F, -2.4372F, 0.05F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r263 = ARMBASE_L.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(122, 101).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, -1.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r264 = ARMBASE_L.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(130, 39).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, -1.9F, 0.3F, 0.0F, 0.1309F, 0.2182F));

		PartDefinition cube_r265 = ARMBASE_L.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(130, 75).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, -1.9F, -0.2F, 0.0F, -0.0873F, 0.2182F));

		PartDefinition FOREARM_L = ARMBASE_L.addOrReplaceChild("FOREARM_L", CubeListBuilder.create(), PartPose.offset(3.1098F, 0.5454F, -0.0205F));

		PartDefinition cube_r266 = FOREARM_L.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(126, 155).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0792F, 0.3056F, 0.0205F, -0.48F, 0.0F, 0.3054F));

		PartDefinition cube_r267 = FOREARM_L.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, 0.2618F));

		PartDefinition HAND_L = FOREARM_L.addOrReplaceChild("HAND_L", CubeListBuilder.create(), PartPose.offset(-1.7307F, 3.4564F, -11.3409F));

		PartDefinition cube_r268 = HAND_L.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(56, 34).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, -1.0964F, 1.3036F, 0.2182F, 0.0F, -0.1745F));

		PartDefinition FINGER_L_4 = HAND_L.addOrReplaceChild("FINGER_L_4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r269 = FINGER_L_4.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(11, 46).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.7854F, 0.0F));

		PartDefinition FOREFINGER_L_4 = FINGER_L_4.addOrReplaceChild("FOREFINGER_L_4", CubeListBuilder.create(), PartPose.offset(-1.2693F, 0.5436F, -2.6591F));

		PartDefinition cube_r270 = FOREFINGER_L_4.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.1309F, 0.0F));

		PartDefinition cube_r271 = FOREFINGER_L_4.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(20, 29).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5693F, -0.1436F, 1.2591F, 0.0873F, 0.1309F, 0.0F));

		PartDefinition FINGER_L_3 = HAND_L.addOrReplaceChild("FINGER_L_3", CubeListBuilder.create(), PartPose.offset(-0.7922F, -2.86F, -3.5218F));

		PartDefinition cube_r272 = FINGER_L_3.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(121, 157).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7922F, 0.96F, 3.0218F, 0.2618F, 0.6109F, 1.1781F));

		PartDefinition FOREFINGER_L_3 = FINGER_L_3.addOrReplaceChild("FOREFINGER_L_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r273 = FOREFINGER_L_3.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(9, 5).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.0F, 1.3963F));

		PartDefinition cube_r274 = FOREFINGER_L_3.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(70, 16).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3922F, 0.46F, 2.2218F, 0.0873F, 0.0F, 1.3963F));

		PartDefinition FINGER_L_2 = HAND_L.addOrReplaceChild("FINGER_L_2", CubeListBuilder.create(), PartPose.offset(2.4078F, -2.36F, -5.1218F));

		PartDefinition cube_r275 = FINGER_L_2.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(116, 156).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5078F, 0.46F, 4.2218F, -0.3054F, -0.1309F, 0.4363F));

		PartDefinition FOREFINGER_L_2 = FINGER_L_2.addOrReplaceChild("FOREFINGER_L_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r276 = FOREFINGER_L_2.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(3, 1).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.5672F));

		PartDefinition cube_r277 = FOREFINGER_L_2.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(81, 33).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0078F, 0.16F, 2.3218F, 0.0873F, 0.0F, 0.5672F));

		PartDefinition FINGER_L_1 = HAND_L.addOrReplaceChild("FINGER_L_1", CubeListBuilder.create(), PartPose.offset(4.1078F, 0.24F, -3.6218F));

		PartDefinition cube_r278 = FINGER_L_1.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(149, 156).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5078F, -0.44F, 3.4218F, 0.0F, -0.7854F, 0.2618F));

		PartDefinition FOREFINGER_L_1 = FINGER_L_1.addOrReplaceChild("FOREFINGER_L_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r279 = FOREFINGER_L_1.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.2182F));

		PartDefinition cube_r280 = FOREFINGER_L_1.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(77, 48).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3078F, -0.24F, 2.3218F, 0.0F, 0.0F, 0.2182F));

		PartDefinition TORSO_BOUND_L = ARM_LEFT.addOrReplaceChild("TORSO_BOUND_L", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r281 = TORSO_BOUND_L.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(70, 86).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.1309F));

		PartDefinition cube_r282 = TORSO_BOUND_L.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(98, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -0.7F, 1.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r283 = TORSO_BOUND_L.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(85, 77).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.7854F, 0.0F, 0.1309F));

		PartDefinition CHESTPLATE = TORSO.addOrReplaceChild("CHESTPLATE", CubeListBuilder.create().texOffs(8, 27).addBox(-1.0F, -6.7F, -1.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -9.0F));

		PartDefinition cube_r284 = CHESTPLATE.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(145, 145).addBox(-0.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -4.0F, -2.5F, -0.3927F, 0.2182F, 1.0908F));

		PartDefinition cube_r285 = CHESTPLATE.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(136, 151).addBox(-2.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.5F, -0.6981F, 0.8727F, -1.7017F));

		PartDefinition cube_r286 = CHESTPLATE.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(94, 79).addBox(-2.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -7.0F, -1.5F, -0.3927F, -0.3927F, -1.0908F));

		PartDefinition cube_r287 = CHESTPLATE.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(145, 15).addBox(-2.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.0F, -2.5F, -0.3927F, -0.2182F, -1.0908F));

		PartDefinition cube_r288 = CHESTPLATE.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(30, 138).addBox(-2.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, -2.5F, -0.3491F, -0.0436F, -1.1345F));

		PartDefinition cube_r289 = CHESTPLATE.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(0, 26).addBox(-2.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -2.5F, -0.4363F, 0.4363F, -1.3526F));

		PartDefinition cube_r290 = CHESTPLATE.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(15, 13).addBox(-2.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.0F, -1.5F, -0.5236F, 0.6981F, -1.5272F));

		PartDefinition cube_r291 = CHESTPLATE.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(16, 152).addBox(-0.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, 0.5F, -0.6981F, -0.8727F, 1.7017F));

		PartDefinition cube_r292 = CHESTPLATE.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(36, 148).addBox(-0.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.0F, -1.5F, -0.5236F, -0.6981F, 1.5272F));

		PartDefinition cube_r293 = CHESTPLATE.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(44, 152).addBox(-0.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.0F, -1.5F, -0.3927F, 0.3927F, 1.0908F));

		PartDefinition cube_r294 = CHESTPLATE.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(145, 136).addBox(-0.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, -2.5F, -0.4363F, -0.4363F, 1.3526F));

		PartDefinition cube_r295 = CHESTPLATE.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(138, 111).addBox(-0.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, -2.5F, -0.3491F, 0.0436F, 1.1345F));

		PartDefinition UPPER = FRONT_BODY.addOrReplaceChild("UPPER", CubeListBuilder.create(), PartPose.offset(0.0F, -16.5F, -3.0F));

		PartDefinition SECTION_5 = UPPER.addOrReplaceChild("SECTION_5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2648F, -0.0453F));

		PartDefinition SUBSECTION_5_1 = SECTION_5.addOrReplaceChild("SUBSECTION_5_1", CubeListBuilder.create().texOffs(144, 83).addBox(2.75F, -0.7648F, -6.7047F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(144, 35).addBox(-4.75F, -0.7648F, -6.7047F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition cube_r296 = SUBSECTION_5_1.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(27, 13).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r297 = SUBSECTION_5_1.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(6, 144).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7352F, -2.7047F, -0.0873F, 0.6109F, -0.0436F));

		PartDefinition cube_r298 = SUBSECTION_5_1.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(26, 148).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5202F, 1.4696F, -7.3796F, -0.1309F, 0.5672F, -0.0436F));

		PartDefinition cube_r299 = SUBSECTION_5_1.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(85, 148).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 0.2352F, -1.7047F, 0.0436F, -0.48F, 0.0F));

		PartDefinition cube_r300 = SUBSECTION_5_1.addOrReplaceChild("cube_r300", CubeListBuilder.create().texOffs(95, 148).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, 0.2352F, -1.7047F, 0.0436F, 0.48F, 0.0F));

		PartDefinition cube_r301 = SUBSECTION_5_1.addOrReplaceChild("cube_r301", CubeListBuilder.create().texOffs(121, 132).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -0.9547F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r302 = SUBSECTION_5_1.addOrReplaceChild("cube_r302", CubeListBuilder.create().texOffs(144, 55).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.7352F, -2.7047F, -0.0873F, -0.6109F, 0.0436F));

		PartDefinition cube_r303 = SUBSECTION_5_1.addOrReplaceChild("cube_r303", CubeListBuilder.create().texOffs(111, 149).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5202F, 1.4696F, -7.3796F, -0.1309F, -0.5672F, 0.0436F));

		PartDefinition cube_r304 = SUBSECTION_5_1.addOrReplaceChild("cube_r304", CubeListBuilder.create().texOffs(126, 149).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4852F, -8.2047F, -0.0873F, 0.0F, 0.0F));

		PartDefinition SECTION_4 = SECTION_5.addOrReplaceChild("SECTION_4", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition SUBSECTION_4_1 = SECTION_4.addOrReplaceChild("SUBSECTION_4_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 0.1F));

		PartDefinition cube_r305 = SUBSECTION_4_1.addOrReplaceChild("cube_r305", CubeListBuilder.create().texOffs(30, 25).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r306 = SUBSECTION_4_1.addOrReplaceChild("cube_r306", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -3.5F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.5648F, 1.9453F, 0.1745F, -0.5672F, -0.2182F));

		PartDefinition cube_r307 = SUBSECTION_4_1.addOrReplaceChild("cube_r307", CubeListBuilder.create().texOffs(65, 143).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(143, 90).addBox(-8.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -3.0648F, -0.3047F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r308 = SUBSECTION_4_1.addOrReplaceChild("cube_r308", CubeListBuilder.create().texOffs(146, 75).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -3.3148F, -2.8047F, 0.2182F, -0.6981F, 0.0F));

		PartDefinition cube_r309 = SUBSECTION_4_1.addOrReplaceChild("cube_r309", CubeListBuilder.create().texOffs(103, 143).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0648F, 2.9453F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r310 = SUBSECTION_4_1.addOrReplaceChild("cube_r310", CubeListBuilder.create().texOffs(123, 5).addBox(-1.0F, -3.5F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.5648F, 1.9453F, 0.1745F, 0.5672F, 0.2182F));

		PartDefinition cube_r311 = SUBSECTION_4_1.addOrReplaceChild("cube_r311", CubeListBuilder.create().texOffs(146, 109).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -3.3148F, -2.8047F, 0.2182F, 0.6981F, 0.0F));

		PartDefinition cube_r312 = SUBSECTION_4_1.addOrReplaceChild("cube_r312", CubeListBuilder.create().texOffs(147, 123).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.3148F, -3.8047F, 0.2618F, 0.0F, 0.0F));

		PartDefinition SECTION_3 = SECTION_4.addOrReplaceChild("SECTION_3", CubeListBuilder.create(), PartPose.offset(0.0F, -3.7648F, 0.0453F));

		PartDefinition SUBSECTION_3_1 = SECTION_3.addOrReplaceChild("SUBSECTION_3_1", CubeListBuilder.create(), PartPose.offset(0.0F, -4.7352F, 1.8547F));

		PartDefinition cube_r313 = SUBSECTION_3_1.addOrReplaceChild("cube_r313", CubeListBuilder.create().texOffs(49, 36).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r314 = SUBSECTION_3_1.addOrReplaceChild("cube_r314", CubeListBuilder.create().texOffs(16, 146).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1769F, -0.0905F, -3.4232F, 0.7854F, -0.48F, -0.5672F));

		PartDefinition cube_r315 = SUBSECTION_3_1.addOrReplaceChild("cube_r315", CubeListBuilder.create().texOffs(55, 143).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(142, 68).addBox(5.5F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, 3.2653F, -3.0495F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r316 = SUBSECTION_3_1.addOrReplaceChild("cube_r316", CubeListBuilder.create().texOffs(146, 49).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1769F, -0.0905F, -3.4232F, 0.7854F, 0.48F, 0.5672F));

		PartDefinition cube_r317 = SUBSECTION_3_1.addOrReplaceChild("cube_r317", CubeListBuilder.create().texOffs(45, 146).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1769F, 1.9095F, -1.1732F, 0.5236F, -0.5236F, -0.48F));

		PartDefinition cube_r318 = SUBSECTION_3_1.addOrReplaceChild("cube_r318", CubeListBuilder.create().texOffs(150, 42).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.529F, 4.5438F, -5.0879F, 0.6545F, 0.6109F, 0.2182F));

		PartDefinition cube_r319 = SUBSECTION_3_1.addOrReplaceChild("cube_r319", CubeListBuilder.create().texOffs(151, 81).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.529F, 4.5438F, -5.0879F, 0.6545F, -0.6109F, -0.2182F));

		PartDefinition cube_r320 = SUBSECTION_3_1.addOrReplaceChild("cube_r320", CubeListBuilder.create().texOffs(75, 146).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1769F, 1.9095F, -1.1732F, 0.5236F, 0.5236F, 0.48F));

		PartDefinition cube_r321 = SUBSECTION_3_1.addOrReplaceChild("cube_r321", CubeListBuilder.create().texOffs(128, 142).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -1.1047F, 0.6545F, 0.0F, 0.0F));

		PartDefinition SECTION_2 = SECTION_3.addOrReplaceChild("SECTION_2", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -2.0F));

		PartDefinition SUBSECTION_2_1 = SECTION_2.addOrReplaceChild("SUBSECTION_2_1", CubeListBuilder.create(), PartPose.offset(2.4269F, -2.5757F, -1.3185F));

		PartDefinition cube_r322 = SUBSECTION_2_1.addOrReplaceChild("cube_r322", CubeListBuilder.create().texOffs(83, 141).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.3526F, -0.1745F, -0.5672F));

		PartDefinition cube_r323 = SUBSECTION_2_1.addOrReplaceChild("cube_r323", CubeListBuilder.create().texOffs(38, 141).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(93, 141).addBox(5.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4269F, 2.1058F, 0.6238F, 0.9163F, 0.0F, 0.0F));

		PartDefinition cube_r324 = SUBSECTION_2_1.addOrReplaceChild("cube_r324", CubeListBuilder.create().texOffs(87, 51).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8538F, 0.0F, 0.0F, 1.3526F, 0.1745F, 0.5672F));

		PartDefinition cube_r325 = SUBSECTION_2_1.addOrReplaceChild("cube_r325", CubeListBuilder.create().texOffs(151, 22).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.148F, 3.3844F, -1.4146F, 1.1781F, -0.3054F, -0.8727F));

		PartDefinition cube_r326 = SUBSECTION_2_1.addOrReplaceChild("cube_r326", CubeListBuilder.create().texOffs(150, 30).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7059F, 3.3844F, -1.4146F, 1.1781F, 0.3054F, 0.8727F));

		PartDefinition cube_r327 = SUBSECTION_2_1.addOrReplaceChild("cube_r327", CubeListBuilder.create().texOffs(141, 129).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, -1.1743F, 0.5685F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r328 = SUBSECTION_2_1.addOrReplaceChild("cube_r328", CubeListBuilder.create().texOffs(11, 32).addBox(0.0F, -1.5F, -1.5F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, -1.6889F, 0.8903F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r329 = SUBSECTION_2_1.addOrReplaceChild("cube_r329", CubeListBuilder.create().texOffs(155, 106).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, 4.8883F, -1.8362F, 1.0472F, 0.0F, 0.0F));

		PartDefinition SECTION_1 = SECTION_2.addOrReplaceChild("SECTION_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));

		PartDefinition SUBSECTION_1_1 = SECTION_1.addOrReplaceChild("SUBSECTION_1_1", CubeListBuilder.create().texOffs(94, 154).addBox(-4.25F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 120).addBox(-4.25F, -6.0F, -1.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(146, 6).addBox(0.25F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(145, 117).addBox(-6.75F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, 2.5626F, -2.1548F));

		PartDefinition cube_r330 = SUBSECTION_1_1.addOrReplaceChild("cube_r330", CubeListBuilder.create().texOffs(10, 122).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition cube_r331 = SUBSECTION_1_1.addOrReplaceChild("cube_r331", CubeListBuilder.create().texOffs(112, 121).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -4.75F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r332 = SUBSECTION_1_1.addOrReplaceChild("cube_r332", CubeListBuilder.create().texOffs(121, 109).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

		PartDefinition cube_r333 = SUBSECTION_1_1.addOrReplaceChild("cube_r333", CubeListBuilder.create().texOffs(24, 122).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r334 = SUBSECTION_1_1.addOrReplaceChild("cube_r334", CubeListBuilder.create().texOffs(37, 2).addBox(0.0F, -1.5F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -5.9272F, 1.0265F, 0.7854F, 0.0F, 0.0F));

		PartDefinition HEAD = SECTION_1.addOrReplaceChild("HEAD", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition JAW = HEAD.addOrReplaceChild("JAW", CubeListBuilder.create(), PartPose.offset(0.0F, 2.95F, -10.0F));

		PartDefinition cube_r335 = JAW.addOrReplaceChild("cube_r335", CubeListBuilder.create().texOffs(120, 41).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r336 = JAW.addOrReplaceChild("cube_r336", CubeListBuilder.create().texOffs(7, 56).addBox(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.8812F, -3.9716F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r337 = JAW.addOrReplaceChild("cube_r337", CubeListBuilder.create().texOffs(103, 99).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0188F, -2.6716F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r338 = JAW.addOrReplaceChild("cube_r338", CubeListBuilder.create().texOffs(37, 118).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 4.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r339 = JAW.addOrReplaceChild("cube_r339", CubeListBuilder.create().texOffs(27, 110).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.2198F, 4.25F, -0.2618F, 0.0F, 0.8727F));

		PartDefinition cube_r340 = JAW.addOrReplaceChild("cube_r340", CubeListBuilder.create().texOffs(33, 102).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9217F, 0.7314F, 4.25F, -0.2618F, 0.0F, -0.5236F));

		PartDefinition cube_r341 = JAW.addOrReplaceChild("cube_r341", CubeListBuilder.create().texOffs(52, 94).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.2198F, 4.25F, -0.2618F, 0.0F, -0.8727F));

		PartDefinition cube_r342 = JAW.addOrReplaceChild("cube_r342", CubeListBuilder.create().texOffs(54, 86).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9217F, 0.4814F, 1.5F, -0.1745F, 0.0F, -0.5236F));

		PartDefinition cube_r343 = JAW.addOrReplaceChild("cube_r343", CubeListBuilder.create().texOffs(0, 73).addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.4698F, 1.5F, -0.1745F, 0.0F, -0.8727F));

		PartDefinition cube_r344 = JAW.addOrReplaceChild("cube_r344", CubeListBuilder.create().texOffs(20, 101).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4923F, -0.4428F, -2.7659F, -0.3491F, 0.0F, 0.5236F));

		PartDefinition cube_r345 = JAW.addOrReplaceChild("cube_r345", CubeListBuilder.create().texOffs(106, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4923F, -0.4428F, -2.7659F, -0.3491F, 0.0F, -0.5236F));

		PartDefinition cube_r346 = JAW.addOrReplaceChild("cube_r346", CubeListBuilder.create().texOffs(114, 41).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7923F, -0.1428F, -1.2159F, -0.2182F, 0.0F, -0.5236F));

		PartDefinition cube_r347 = JAW.addOrReplaceChild("cube_r347", CubeListBuilder.create().texOffs(69, 115).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7923F, -0.1428F, -1.2159F, -0.2182F, 0.0F, 0.5236F));

		PartDefinition cube_r348 = JAW.addOrReplaceChild("cube_r348", CubeListBuilder.create().texOffs(139, 104).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9217F, 0.4814F, 1.5F, -0.1745F, 0.0F, 0.5236F));

		PartDefinition cube_r349 = JAW.addOrReplaceChild("cube_r349", CubeListBuilder.create().texOffs(147, 103).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.4698F, 1.5F, -0.1745F, 0.0F, 0.8727F));

		PartDefinition cube_r350 = JAW.addOrReplaceChild("cube_r350", CubeListBuilder.create().texOffs(49, 102).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9217F, 0.7314F, 4.25F, -0.2618F, 0.0F, 0.5236F));

		PartDefinition FOREHEAD = HEAD.addOrReplaceChild("FOREHEAD", CubeListBuilder.create().texOffs(132, 93).addBox(3.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 113).addBox(-1.5F, -42.8F, -15.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(131, 24).addBox(-5.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 38.0F, 10.0F));

		PartDefinition cube_r351 = FOREHEAD.addOrReplaceChild("cube_r351", CubeListBuilder.create().texOffs(154, 35).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5758F, -37.8788F, -24.7793F, 0.9163F, -0.1309F, 0.1745F));

		PartDefinition cube_r352 = FOREHEAD.addOrReplaceChild("cube_r352", CubeListBuilder.create().texOffs(154, 56).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2033F, -37.975F, -23.4418F, 0.6981F, -0.2618F, 0.6981F));

		PartDefinition cube_r353 = FOREHEAD.addOrReplaceChild("cube_r353", CubeListBuilder.create().texOffs(81, 9).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -43.4F, -14.4F, -2.6616F, 0.0F, 0.0F));

		PartDefinition cube_r354 = FOREHEAD.addOrReplaceChild("cube_r354", CubeListBuilder.create().texOffs(81, 17).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.45F, -43.15F, -14.4F, -2.6616F, 0.1309F, 0.1309F));

		PartDefinition cube_r355 = FOREHEAD.addOrReplaceChild("cube_r355", CubeListBuilder.create().texOffs(76, 51).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -42.55F, -14.5F, -2.6616F, -0.1309F, -0.3054F));

		PartDefinition cube_r356 = FOREHEAD.addOrReplaceChild("cube_r356", CubeListBuilder.create().texOffs(14, 76).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -41.55F, -14.5F, -2.618F, 0.0436F, -0.7854F));

		PartDefinition cube_r357 = FOREHEAD.addOrReplaceChild("cube_r357", CubeListBuilder.create().texOffs(3, 71).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -40.2F, -20.75F, -2.7925F, -0.0873F, 0.4363F));

		PartDefinition cube_r358 = FOREHEAD.addOrReplaceChild("cube_r358", CubeListBuilder.create().texOffs(154, 72).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5758F, -37.8788F, -24.7793F, 0.9163F, 0.1309F, -0.1745F));

		PartDefinition cube_r359 = FOREHEAD.addOrReplaceChild("cube_r359", CubeListBuilder.create().texOffs(36, 156).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7258F, -35.1389F, -25.2042F, 0.9163F, 0.2618F, -1.0036F));

		PartDefinition cube_r360 = FOREHEAD.addOrReplaceChild("cube_r360", CubeListBuilder.create().texOffs(74, 156).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3758F, -35.2389F, -25.6042F, 1.309F, 0.2618F, -0.6981F));

		PartDefinition cube_r361 = FOREHEAD.addOrReplaceChild("cube_r361", CubeListBuilder.create().texOffs(84, 154).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2033F, -37.975F, -23.4418F, 0.6981F, 0.2618F, -0.6981F));

		PartDefinition cube_r362 = FOREHEAD.addOrReplaceChild("cube_r362", CubeListBuilder.create().texOffs(70, 16).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -39.5F, -19.85F, -2.7925F, -0.1745F, 0.7418F));

		PartDefinition cube_r363 = FOREHEAD.addOrReplaceChild("cube_r363", CubeListBuilder.create().texOffs(69, 70).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -39.5F, -19.85F, -2.7925F, 0.1745F, -0.7418F));

		PartDefinition cube_r364 = FOREHEAD.addOrReplaceChild("cube_r364", CubeListBuilder.create().texOffs(70, 62).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -40.2F, -20.75F, -2.7925F, 0.0873F, -0.4363F));

		PartDefinition cube_r365 = FOREHEAD.addOrReplaceChild("cube_r365", CubeListBuilder.create().texOffs(156, 76).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -36.0051F, -25.7508F, 1.3963F, 0.0F, 0.0F));

		PartDefinition cube_r366 = FOREHEAD.addOrReplaceChild("cube_r366", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -40.75F, -20.0F, -2.7925F, 0.0F, 0.0F));

		PartDefinition cube_r367 = FOREHEAD.addOrReplaceChild("cube_r367", CubeListBuilder.create().texOffs(72, 39).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -40.05F, -14.25F, -2.618F, 0.0873F, -1.0472F));

		PartDefinition cube_r368 = FOREHEAD.addOrReplaceChild("cube_r368", CubeListBuilder.create().texOffs(145, 62).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -37.8F, -15.5F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r369 = FOREHEAD.addOrReplaceChild("cube_r369", CubeListBuilder.create().texOffs(0, 79).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.45F, -43.15F, -14.4F, -2.6616F, -0.1309F, -0.1309F));

		PartDefinition cube_r370 = FOREHEAD.addOrReplaceChild("cube_r370", CubeListBuilder.create().texOffs(131, 105).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -39.3F, -12.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r371 = FOREHEAD.addOrReplaceChild("cube_r371", CubeListBuilder.create().texOffs(115, 50).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -40.55F, -12.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r372 = FOREHEAD.addOrReplaceChild("cube_r372", CubeListBuilder.create().texOffs(36, 77).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -41.3F, -12.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r373 = FOREHEAD.addOrReplaceChild("cube_r373", CubeListBuilder.create().texOffs(54, 78).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -42.55F, -14.5F, -2.6616F, 0.1309F, 0.3054F));

		PartDefinition cube_r374 = FOREHEAD.addOrReplaceChild("cube_r374", CubeListBuilder.create().texOffs(43, 77).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -41.55F, -14.5F, -2.618F, -0.0436F, 0.7854F));

		PartDefinition cube_r375 = FOREHEAD.addOrReplaceChild("cube_r375", CubeListBuilder.create().texOffs(25, 77).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.75F, -40.05F, -14.25F, -2.618F, -0.0873F, 1.0472F));

		PartDefinition cube_r376 = FOREHEAD.addOrReplaceChild("cube_r376", CubeListBuilder.create().texOffs(60, 115).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -41.3F, -12.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r377 = FOREHEAD.addOrReplaceChild("cube_r377", CubeListBuilder.create().texOffs(0, 117).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -40.55F, -12.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r378 = FOREHEAD.addOrReplaceChild("cube_r378", CubeListBuilder.create().texOffs(26, 132).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -39.3F, -12.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r379 = FOREHEAD.addOrReplaceChild("cube_r379", CubeListBuilder.create().texOffs(143, 24).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -37.05F, -22.5F, 0.4363F, -0.3927F, 0.0F));

		PartDefinition cube_r380 = FOREHEAD.addOrReplaceChild("cube_r380", CubeListBuilder.create().texOffs(51, 56).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.95F, -39.6F, -17.15F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r381 = FOREHEAD.addOrReplaceChild("cube_r381", CubeListBuilder.create().texOffs(22, 63).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.95F, -39.6F, -17.15F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r382 = FOREHEAD.addOrReplaceChild("cube_r382", CubeListBuilder.create().texOffs(144, 0).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -37.8F, -19.25F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r383 = FOREHEAD.addOrReplaceChild("cube_r383", CubeListBuilder.create().texOffs(144, 97).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -37.8F, -19.25F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r384 = FOREHEAD.addOrReplaceChild("cube_r384", CubeListBuilder.create().texOffs(144, 43).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -37.8F, -15.5F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r385 = FOREHEAD.addOrReplaceChild("cube_r385", CubeListBuilder.create().texOffs(76, 25).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -37.05F, -22.5F, 0.4363F, 0.3927F, 0.0F));

		PartDefinition cube_r386 = FOREHEAD.addOrReplaceChild("cube_r386", CubeListBuilder.create().texOffs(31, 155).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3758F, -35.2389F, -25.6042F, 1.309F, -0.2618F, 0.6981F));

		PartDefinition cube_r387 = FOREHEAD.addOrReplaceChild("cube_r387", CubeListBuilder.create().texOffs(111, 8).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7258F, -35.1389F, -25.2042F, 0.9163F, -0.2618F, 1.0036F));

		PartDefinition MANE = FOREHEAD.addOrReplaceChild("MANE", CubeListBuilder.create(), PartPose.offset(0.0F, -44.0F, -11.0F));

		PartDefinition cube_r388 = MANE.addOrReplaceChild("cube_r388", CubeListBuilder.create().texOffs(65, 24).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.102F, -3.5883F, 0.8438F, 1.2217F, -0.48F, -0.6981F));

		PartDefinition cube_r389 = MANE.addOrReplaceChild("cube_r389", CubeListBuilder.create().texOffs(49, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.602F, -3.0883F, 1.0938F, 1.0908F, 0.4363F, 0.8727F));

		PartDefinition cube_r390 = MANE.addOrReplaceChild("cube_r390", CubeListBuilder.create().texOffs(35, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.852F, -1.8383F, 1.0938F, 0.9599F, 0.48F, 1.0472F));

		PartDefinition cube_r391 = MANE.addOrReplaceChild("cube_r391", CubeListBuilder.create().texOffs(7, 26).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.352F, -0.0883F, 1.0938F, 0.9599F, 0.5672F, 1.2217F));

		PartDefinition cube_r392 = MANE.addOrReplaceChild("cube_r392", CubeListBuilder.create().texOffs(25, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.602F, 1.6617F, 1.8438F, 0.6545F, 0.5672F, 1.2217F));

		PartDefinition cube_r393 = MANE.addOrReplaceChild("cube_r393", CubeListBuilder.create().texOffs(29, 69).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.602F, -3.0883F, 1.0938F, 1.0908F, -0.4363F, -0.8727F));

		PartDefinition cube_r394 = MANE.addOrReplaceChild("cube_r394", CubeListBuilder.create().texOffs(30, 60).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.602F, 1.6617F, 1.8438F, 0.6545F, -0.5672F, -1.2217F));

		PartDefinition cube_r395 = MANE.addOrReplaceChild("cube_r395", CubeListBuilder.create().texOffs(48, 61).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.352F, -0.0883F, 1.0938F, 0.9599F, -0.5672F, -1.2217F));

		PartDefinition cube_r396 = MANE.addOrReplaceChild("cube_r396", CubeListBuilder.create().texOffs(47, 69).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.852F, -1.8383F, 1.0938F, 0.9599F, -0.48F, -1.0472F));

		PartDefinition FIXED_JAW = HEAD.addOrReplaceChild("FIXED_JAW", CubeListBuilder.create().texOffs(47, 120).addBox(-1.5F, -35.05F, -14.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 38.0F, 10.0F));

		PartDefinition cube_r397 = FIXED_JAW.addOrReplaceChild("cube_r397", CubeListBuilder.create().texOffs(51, 132).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r398 = FIXED_JAW.addOrReplaceChild("cube_r398", CubeListBuilder.create().texOffs(43, 110).addBox(-1.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -35.8F, -15.75F, -0.2618F, 0.0F, 1.2217F));

		PartDefinition cube_r399 = FIXED_JAW.addOrReplaceChild("cube_r399", CubeListBuilder.create().texOffs(36, 94).addBox(-0.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -35.8F, -15.75F, -0.2618F, 0.0F, -1.2217F));

		PartDefinition cube_r400 = FIXED_JAW.addOrReplaceChild("cube_r400", CubeListBuilder.create().texOffs(136, 99).addBox(-0.5F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -35.8F, -18.5F, -0.1745F, 0.0F, -1.2217F));

		PartDefinition cube_r401 = FIXED_JAW.addOrReplaceChild("cube_r401", CubeListBuilder.create().texOffs(118, 152).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5693F, -36.3069F, -21.7381F, -0.3491F, 0.0F, -1.2217F));

		PartDefinition cube_r402 = FIXED_JAW.addOrReplaceChild("cube_r402", CubeListBuilder.create().texOffs(153, 52).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5693F, -36.3069F, -21.7381F, -0.3491F, 0.0F, 1.2217F));

		PartDefinition cube_r403 = FIXED_JAW.addOrReplaceChild("cube_r403", CubeListBuilder.create().texOffs(138, 30).addBox(-1.5F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -35.8F, -18.5F, -0.1745F, 0.0F, 1.2217F));

		PartDefinition cube_r404 = FIXED_JAW.addOrReplaceChild("cube_r404", CubeListBuilder.create().texOffs(130, 83).addBox(-1.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -35.55F, -12.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r405 = FIXED_JAW.addOrReplaceChild("cube_r405", CubeListBuilder.create().texOffs(130, 33).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition cube_r406 = FIXED_JAW.addOrReplaceChild("cube_r406", CubeListBuilder.create().texOffs(129, 128).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1717F, -33.8186F, -12.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r407 = FIXED_JAW.addOrReplaceChild("cube_r407", CubeListBuilder.create().texOffs(132, 57).addBox(-0.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -35.55F, -12.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r408 = FIXED_JAW.addOrReplaceChild("cube_r408", CubeListBuilder.create().texOffs(74, 132).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1717F, -33.8186F, -12.0F, 0.0F, 0.0F, -0.5236F));

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