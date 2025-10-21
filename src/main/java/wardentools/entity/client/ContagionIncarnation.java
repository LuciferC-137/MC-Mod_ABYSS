package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.ContagionIncarnationAnimation;
import wardentools.entity.custom.ContagionIncarnationEntity;

@SuppressWarnings("unused")
public class ContagionIncarnation extends HierarchicalModel<ContagionIncarnationEntity> {
    public static final ModelLayerLocation LAYER_LOCATION
            = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_incarnation"), "main");
    private static final float rad = (float) Math.PI / 180f;
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
    private final Section head;
    private final Section section_1;
    private final Section section_2;
    private final Section section_3;
    private final Section section_4;
    private final Section section_5;

    public ContagionIncarnation(ModelPart root) {
        this.FULL = root.getChild("FULL");
        this.TAIL = FULL.getChild("TAIL");
        this.SECTION_6 = TAIL.getChild("SECTION_6");
        this.SUBSECTION_6_1 = SECTION_6.getChild("SUBSECTION_6_1");
        this.SECTION_7 = SECTION_6.getChild("SECTION_7");
        this.SUBSECTION_7_1 = SECTION_7.getChild("SUBSECTION_7_1");
        this.SUBSECTION_7_2 = SECTION_7.getChild("SUBSECTION_7_2");
        this.LEG_LEFT_3 = SECTION_7.getChild("LEG_LEFT_3");
        this.BASE_L_3 = LEG_LEFT_3.getChild("BASE_L_3");
        this.FORELEG_L_3 = LEG_LEFT_3.getChild("FORELEG_L_3");
        this.FEET_L_3 = FORELEG_L_3.getChild("FEET_L_3");
        this.LEG_RIGHT_3 = SECTION_7.getChild("LEG_RIGHT_3");
        this.BASE_R_3 = LEG_RIGHT_3.getChild("BASE_R_3");
        this.FORELEG_R_3 = LEG_RIGHT_3.getChild("FORELEG_R_3");
        this.FEET_R_3 = FORELEG_R_3.getChild("FEET_R_3");
        this.SECTION_8 = SECTION_7.getChild("SECTION_8");
        this.SUBSECTION_8_1 = SECTION_8.getChild("SUBSECTION_8_1");
        this.SUBSECTION_8_2 = SECTION_8.getChild("SUBSECTION_8_2");
        this.LEG_LEFT_4 = SECTION_8.getChild("LEG_LEFT_4");
        this.BASE_L_4 = LEG_LEFT_4.getChild("BASE_L_4");
        this.FORELEG_L_4 = LEG_LEFT_4.getChild("FORELEG_L_4");
        this.FEET_L_4 = FORELEG_L_4.getChild("FEET_L_4");
        this.LEG_RIGHT_4 = SECTION_8.getChild("LEG_RIGHT_4");
        this.BASE_R_4 = LEG_RIGHT_4.getChild("BASE_R_4");
        this.FORELEG_R_4 = LEG_RIGHT_4.getChild("FORELEG_R_4");
        this.FEET_R_4 = FORELEG_R_4.getChild("FEET_R_4");
        this.SECTION_9 = SECTION_8.getChild("SECTION_9");
        this.SUBSECTION_9_1 = SECTION_9.getChild("SUBSECTION_9_1");
        this.SECTION_10 = SECTION_9.getChild("SECTION_10");
        this.SUBSECTION_10_1 = SECTION_10.getChild("SUBSECTION_10_1");
        this.SECTION_11 = SECTION_10.getChild("SECTION_11");
        this.SUBSECTION_11_1 = SECTION_11.getChild("SUBSECTION_11_1");
        this.SECTION_12 = SECTION_11.getChild("SECTION_12");
        this.SUBSECTION_12_1 = SECTION_12.getChild("SUBSECTION_12_1");
        this.SECTION_13 = SECTION_12.getChild("SECTION_13");
        this.SUBSECTION_13_1 = SECTION_13.getChild("SUBSECTION_13_1");
        this.SECTION_14 = SECTION_13.getChild("SECTION_14");
        this.SUBSECTION_14_1 = SECTION_14.getChild("SUBSECTION_14_1");
        this.SECTION_15 = SECTION_14.getChild("SECTION_15");
        this.SUBSECTION_15_1 = SECTION_15.getChild("SUBSECTION_15_1");
        this.SECTION_16 = SECTION_15.getChild("SECTION_16");
        this.SUBSECTION_16_1 = SECTION_16.getChild("SUBSECTION_16_1");
        this.SECTION_17 = SECTION_16.getChild("SECTION_17");
        this.SUBSECTION_17_1 = SECTION_17.getChild("SUBSECTION_17_1");
        this.SECTION_18 = SECTION_17.getChild("SECTION_18");
        this.SUBSECTION_18_1 = SECTION_18.getChild("SUBSECTION_18_1");
        this.END = SECTION_18.getChild("END");
        this.FRONT_BODY = FULL.getChild("FRONT_BODY");
        this.TORSO = FRONT_BODY.getChild("TORSO");
        this.BELLY = TORSO.getChild("BELLY");
        this.ARM_RIGHT = TORSO.getChild("ARM_RIGHT");
        this.ARMBASE_R = ARM_RIGHT.getChild("ARMBASE_R");
        this.FOREARM_R = ARMBASE_R.getChild("FOREARM_R");
        this.HAND_R = FOREARM_R.getChild("HAND_R");
        this.FINGER_R_4 = HAND_R.getChild("FINGER_R_4");
        this.FOREFINGER_R_4 = FINGER_R_4.getChild("FOREFINGER_R_4");
        this.FINGER_R_3 = HAND_R.getChild("FINGER_R_3");
        this.FOREFINGER_R_3 = FINGER_R_3.getChild("FOREFINGER_R_3");
        this.FINGER_R_2 = HAND_R.getChild("FINGER_R_2");
        this.FOREFINGER_R_2 = FINGER_R_2.getChild("FOREFINGER_R_2");
        this.FINGER_R_1 = HAND_R.getChild("FINGER_R_1");
        this.FOREFINGER_R_1 = FINGER_R_1.getChild("FOREFINGER_R_1");
        this.TORSO_BOUND_R = ARM_RIGHT.getChild("TORSO_BOUND_R");
        this.LEG_LEFT_1 = TORSO.getChild("LEG_LEFT_1");
        this.BASE_L_1 = LEG_LEFT_1.getChild("BASE_L_1");
        this.FORELEG_L_1 = LEG_LEFT_1.getChild("FORELEG_L_1");
        this.FEET_L_1 = FORELEG_L_1.getChild("FEET_L_1");
        this.LEG_RIGHT_1 = TORSO.getChild("LEG_RIGHT_1");
        this.BASE_R_1 = LEG_RIGHT_1.getChild("BASE_R_1");
        this.FORELEG_R_1 = LEG_RIGHT_1.getChild("FORELEG_R_1");
        this.FEET_R_1 = FORELEG_R_1.getChild("FEET_R_1");
        this.LEG_RIGHT_2 = TORSO.getChild("LEG_RIGHT_2");
        this.FORELEG_R_2 = LEG_RIGHT_2.getChild("FORELEG_R_2");
        this.FEET_R_2 = FORELEG_R_2.getChild("FEET_R_2");
        this.BASE_R_2 = LEG_RIGHT_2.getChild("BASE_R_2");
        this.LEG_LEFT_2 = TORSO.getChild("LEG_LEFT_2");
        this.FORELEG_L_2 = LEG_LEFT_2.getChild("FORELEG_L_2");
        this.FEET_L_2 = FORELEG_L_2.getChild("FEET_L_2");
        this.BASE_L_2 = LEG_LEFT_2.getChild("BASE_L_2");
        this.ARM_LEFT = TORSO.getChild("ARM_LEFT");
        this.ARMBASE_L = ARM_LEFT.getChild("ARMBASE_L");
        this.FOREARM_L = ARMBASE_L.getChild("FOREARM_L");
        this.HAND_L = FOREARM_L.getChild("HAND_L");
        this.FINGER_L_4 = HAND_L.getChild("FINGER_L_4");
        this.FOREFINGER_L_4 = FINGER_L_4.getChild("FOREFINGER_L_4");
        this.FINGER_L_3 = HAND_L.getChild("FINGER_L_3");
        this.FOREFINGER_L_3 = FINGER_L_3.getChild("FOREFINGER_L_3");
        this.FINGER_L_2 = HAND_L.getChild("FINGER_L_2");
        this.FOREFINGER_L_2 = FINGER_L_2.getChild("FOREFINGER_L_2");
        this.FINGER_L_1 = HAND_L.getChild("FINGER_L_1");
        this.FOREFINGER_L_1 = FINGER_L_1.getChild("FOREFINGER_L_1");
        this.TORSO_BOUND_L = ARM_LEFT.getChild("TORSO_BOUND_L");
        this.CHESTPLATE = TORSO.getChild("CHESTPLATE");
        this.UPPER = FRONT_BODY.getChild("UPPER");
        this.SECTION_5 = UPPER.getChild("SECTION_5");
        this.SUBSECTION_5_1 = SECTION_5.getChild("SUBSECTION_5_1");
        this.SECTION_4 = SECTION_5.getChild("SECTION_4");
        this.SUBSECTION_4_1 = SECTION_4.getChild("SUBSECTION_4_1");
        this.SECTION_3 = SECTION_4.getChild("SECTION_3");
        this.SUBSECTION_3_1 = SECTION_3.getChild("SUBSECTION_3_1");
        this.SECTION_2 = SECTION_3.getChild("SECTION_2");
        this.SUBSECTION_2_1 = SECTION_2.getChild("SUBSECTION_2_1");
        this.SECTION_1 = SECTION_2.getChild("SECTION_1");
        this.SUBSECTION_1_1 = SECTION_1.getChild("SUBSECTION_1_1");
        this.HEAD = SECTION_1.getChild("HEAD");
        this.JAW = HEAD.getChild("JAW");
        this.FOREHEAD = HEAD.getChild("FOREHEAD");
        this.MANE = FOREHEAD.getChild("MANE");
        this.FIXED_JAW = HEAD.getChild("FIXED_JAW");
        // Sections init for rotation chain handling.
        this.head = new Section(10f, HEAD, null);
        this.section_1 = new Section(4f, SECTION_1, head);
        this.section_2 = new Section(4f, SECTION_2, section_1);
        this.section_3 = new Section(4f, SECTION_3, section_2);
        this.section_4 = new Section(4f, SECTION_4, section_3);
        this.section_5 = new Section(4f, SECTION_5, section_4);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition TAIL = FULL.addOrReplaceChild("TAIL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition SECTION_6 = TAIL.addOrReplaceChild("SECTION_6", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0904F, 6.7868F));

        PartDefinition SUBSECTION_6_1 = SECTION_6.addOrReplaceChild("SUBSECTION_6_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = SUBSECTION_6_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 179).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

        PartDefinition cube_r2 = SUBSECTION_6_1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(92, 46).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.7904F, 0.6132F, -0.1745F, 0.0F, -0.2618F));

        PartDefinition cube_r3 = SUBSECTION_6_1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.0904F, 0.2132F, -0.0873F, -0.0436F, -0.2618F));

        PartDefinition cube_r4 = SUBSECTION_6_1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(18, 63).addBox(-1.0F, -7.5F, -1.5F, 2.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 5.8796F, -3.7683F, 1.5708F, -0.1745F, -1.5708F));

        PartDefinition cube_r5 = SUBSECTION_6_1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 119).addBox(-1.2F, -9.0F, -1.5F, 2.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, 5.5904F, -0.2868F, 1.4835F, -0.1309F, -1.309F));

        PartDefinition cube_r6 = SUBSECTION_6_1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 26).addBox(-5.5F, -1.0F, -4.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0904F, 0.6132F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r7 = SUBSECTION_6_1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(82, 16).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.7904F, 0.6132F, -0.1745F, 0.0F, 0.2618F));

        PartDefinition cube_r8 = SUBSECTION_6_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0904F, 0.2132F, -0.0873F, 0.0436F, 0.2618F));

        PartDefinition cube_r9 = SUBSECTION_6_1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(48, 119).addBox(-0.8F, -9.0F, -1.5F, 2.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8F, 5.5904F, -0.2868F, 1.4835F, 0.1309F, 1.309F));

        PartDefinition SECTION_7 = SECTION_6.addOrReplaceChild("SECTION_7", CubeListBuilder.create(), PartPose.offset(0.7771F, 4.2223F, 1.6118F));

        PartDefinition SUBSECTION_7_1 = SECTION_7.addOrReplaceChild("SUBSECTION_7_1", CubeListBuilder.create(), PartPose.offset(-0.7771F, -4.0223F, 3.6882F));

        PartDefinition cube_r10 = SUBSECTION_7_1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(76, 179).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r11 = SUBSECTION_7_1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(58, 42).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.4904F, -0.5868F, 0.0F, -0.2182F, -1.0036F));

        PartDefinition cube_r12 = SUBSECTION_7_1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(36, 62).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 4.6938F, -0.4868F, 0.0F, -0.1309F, -0.3054F));

        PartDefinition cube_r13 = SUBSECTION_7_1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(56, 52).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4598F, 1.7279F, -0.5868F, 0.0F, -0.0873F, 0.6981F));

        PartDefinition cube_r14 = SUBSECTION_7_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 55).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.4904F, -0.5868F, 0.0F, 0.2182F, 1.0036F));

        PartDefinition cube_r15 = SUBSECTION_7_1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 53).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4598F, 1.7279F, -0.5868F, 0.0F, 0.0873F, -0.6981F));

        PartDefinition cube_r16 = SUBSECTION_7_1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(60, 28).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6224F, 4.6938F, -0.4868F, 0.0F, 0.1309F, 0.3054F));

        PartDefinition cube_r17 = SUBSECTION_7_1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(116, 121).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.4404F, 0.1132F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r18 = SUBSECTION_7_1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(62, 20).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5904F, -0.0868F, 0.1134F, 0.0F, 0.0F));

        PartDefinition SUBSECTION_7_2 = SECTION_7.addOrReplaceChild("SUBSECTION_7_2", CubeListBuilder.create(), PartPose.offset(-0.7771F, -4.2223F, 10.0882F));

        PartDefinition cube_r19 = SUBSECTION_7_2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(52, 180).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

        PartDefinition cube_r20 = SUBSECTION_7_2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 103).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.5904F, 1.3132F, 0.0F, 0.1745F, -1.0036F));

        PartDefinition cube_r21 = SUBSECTION_7_2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(68, 102).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2224F, 4.8938F, 1.3132F, 0.0F, 0.1745F, -0.3054F));

        PartDefinition cube_r22 = SUBSECTION_7_2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(52, 102).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2598F, 2.1779F, 1.3132F, 0.0F, 0.1745F, 0.6981F));

        PartDefinition cube_r23 = SUBSECTION_7_2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(36, 102).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2224F, 4.8938F, 1.3132F, 0.0F, -0.1745F, 0.3054F));

        PartDefinition cube_r24 = SUBSECTION_7_2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(102, 0).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.5904F, 1.3132F, 0.0F, -0.1745F, 1.0036F));

        PartDefinition cube_r25 = SUBSECTION_7_2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(54, 62).addBox(-1.5F, -1.0F, -3.4F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0904F, -0.2868F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r26 = SUBSECTION_7_2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(20, 96).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2598F, 2.1779F, 1.3132F, 0.0F, -0.1745F, -0.6981F));

        PartDefinition cube_r27 = SUBSECTION_7_2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(124, 62).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 7.3404F, -0.2868F, 0.1745F, 0.0F, 0.0F));

        PartDefinition LEG_LEFT_3 = SECTION_7.addOrReplaceChild("LEG_LEFT_3", CubeListBuilder.create(), PartPose.offset(4.4457F, 0.0F, 7.0F));

        PartDefinition BASE_L_3 = LEG_LEFT_3.addOrReplaceChild("BASE_L_3", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

        PartDefinition cube_r28 = BASE_L_3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(128, 177).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

        PartDefinition cube_r29 = BASE_L_3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(118, 177).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

        PartDefinition cube_r30 = BASE_L_3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(66, 16).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

        PartDefinition cube_r31 = BASE_L_3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(60, 38).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

        PartDefinition FORELEG_L_3 = LEG_LEFT_3.addOrReplaceChild("FORELEG_L_3", CubeListBuilder.create(), PartPose.offset(8.0F, -3.0F, 2.0F));

        PartDefinition cube_r32 = FORELEG_L_3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(58, 172).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.603F, 2.6723F, -0.0504F, 0.0F, -0.2618F, 0.2182F));

        PartDefinition cube_r33 = FORELEG_L_3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(42, 181).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r34 = FORELEG_L_3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(172, 32).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.303F, 2.7723F, -0.0504F, 0.0F, -0.3054F, 0.3054F));

        PartDefinition FEET_L_3 = FORELEG_L_3.addOrReplaceChild("FEET_L_3", CubeListBuilder.create(), PartPose.offset(-1.0F, 8.0F, 1.0F));

        PartDefinition cube_r35 = FEET_L_3.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(106, 184).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.1745F, -0.3054F));

        PartDefinition cube_r36 = FEET_L_3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(184, 102).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.0F, -0.5F, 0.3927F, -0.1745F, -0.3054F));

        PartDefinition LEG_RIGHT_3 = SECTION_7.addOrReplaceChild("LEG_RIGHT_3", CubeListBuilder.create(), PartPose.offset(-5.0F, 0.0F, 7.0F));

        PartDefinition BASE_R_3 = LEG_RIGHT_3.addOrReplaceChild("BASE_R_3", CubeListBuilder.create(), PartPose.offset(-7.197F, -2.4777F, 1.6996F));

        PartDefinition cube_r37 = BASE_R_3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(148, 177).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

        PartDefinition cube_r38 = BASE_R_3.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(138, 177).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

        PartDefinition cube_r39 = BASE_R_3.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(14, 138).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

        PartDefinition cube_r40 = BASE_R_3.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(76, 46).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

        PartDefinition FORELEG_R_3 = LEG_RIGHT_3.addOrReplaceChild("FORELEG_R_3", CubeListBuilder.create(), PartPose.offset(-9.0F, -3.0F, 2.0F));

        PartDefinition cube_r41 = FORELEG_R_3.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(70, 172).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 2.6723F, -0.0504F, 0.0F, 0.2618F, -0.2182F));

        PartDefinition cube_r42 = FORELEG_R_3.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(58, 181).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r43 = FORELEG_R_3.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(64, 172).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.303F, 2.7723F, -0.0504F, 0.0F, 0.3054F, -0.3054F));

        PartDefinition FEET_R_3 = FORELEG_R_3.addOrReplaceChild("FEET_R_3", CubeListBuilder.create(), PartPose.offset(1.0F, 7.25F, 0.0F));

        PartDefinition cube_r44 = FEET_R_3.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(184, 183).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 1.0F, 0.0873F, 0.1745F, 0.3054F));

        PartDefinition cube_r45 = FEET_R_3.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(184, 179).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.75F, 0.5F, 0.3927F, 0.1745F, 0.3054F));

        PartDefinition SECTION_8 = SECTION_7.addOrReplaceChild("SECTION_8", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 12.6F));

        PartDefinition SUBSECTION_8_1 = SECTION_8.addOrReplaceChild("SUBSECTION_8_1", CubeListBuilder.create(), PartPose.offset(-0.2771F, -3.9223F, 2.8882F));

        PartDefinition cube_r46 = SUBSECTION_8_1.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(36, 174).addBox(0.0F, -3.5F, -1.5F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r47 = SUBSECTION_8_1.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(46, 0).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 6.3904F, 0.3132F, 0.0F, -0.2182F, -1.0036F));

        PartDefinition cube_r48 = SUBSECTION_8_1.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(38, 52).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6224F, 4.5938F, 0.4132F, 0.0F, -0.1309F, -0.3054F));

        PartDefinition cube_r49 = SUBSECTION_8_1.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(0, 45).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4598F, 1.6279F, 0.3132F, 0.0F, -0.0873F, 0.6981F));

        PartDefinition cube_r50 = SUBSECTION_8_1.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(20, 43).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 6.3904F, 0.3132F, 0.0F, 0.2182F, 1.0036F));

        PartDefinition cube_r51 = SUBSECTION_8_1.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(40, 42).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4598F, 1.6279F, 0.3132F, 0.0F, 0.0873F, -0.6981F));

        PartDefinition cube_r52 = SUBSECTION_8_1.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(48, 10).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6224F, 4.5938F, 0.4132F, 0.0F, 0.1309F, 0.3054F));

        PartDefinition cube_r53 = SUBSECTION_8_1.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.3404F, 0.2132F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r54 = SUBSECTION_8_1.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(26, 11).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4904F, 0.8132F, 0.1134F, 0.0F, 0.0F));

        PartDefinition SUBSECTION_8_2 = SECTION_8.addOrReplaceChild("SUBSECTION_8_2", CubeListBuilder.create(), PartPose.offset(-1.7771F, 3.1182F, 9.9014F));

        PartDefinition cube_r55 = SUBSECTION_8_2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(124, 84).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r56 = SUBSECTION_8_2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(106, 176).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -7.2404F, -0.3132F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r57 = SUBSECTION_8_2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(110, 53).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.75F, 1.6F, 0.0F, 0.1745F, -1.0036F));

        PartDefinition cube_r58 = SUBSECTION_8_2.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(110, 44).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7224F, -2.4466F, 1.6F, 0.0F, 0.1745F, -0.3054F));

        PartDefinition cube_r59 = SUBSECTION_8_2.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(108, 67).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7598F, -5.1626F, 1.6F, 0.0F, 0.1745F, 0.6981F));

        PartDefinition cube_r60 = SUBSECTION_8_2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(16, 105).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7224F, -2.4466F, 1.6F, 0.0F, -0.1745F, 0.3054F));

        PartDefinition cube_r61 = SUBSECTION_8_2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(100, 104).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -0.75F, 1.6F, 0.0F, -0.1745F, 1.0036F));

        PartDefinition cube_r62 = SUBSECTION_8_2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(64, 0).addBox(-1.5F, -1.0F, -3.4F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.25F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r63 = SUBSECTION_8_2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(84, 104).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7598F, -5.1626F, 1.6F, 0.0F, -0.1745F, -0.6981F));

        PartDefinition LEG_LEFT_4 = SECTION_8.addOrReplaceChild("LEG_LEFT_4", CubeListBuilder.create(), PartPose.offset(4.9457F, 0.0F, 7.0F));

        PartDefinition BASE_L_4 = LEG_LEFT_4.addOrReplaceChild("BASE_L_4", CubeListBuilder.create(), PartPose.offset(6.197F, -2.4777F, 1.6996F));

        PartDefinition cube_r64 = BASE_L_4.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(174, 139).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, -0.4363F));

        PartDefinition cube_r65 = BASE_L_4.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(174, 104).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, -0.3002F, -0.2567F));

        PartDefinition cube_r66 = BASE_L_4.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(140, 86).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.1F, 2.0F, -2.0F, -0.0479F, -0.3002F, -0.2567F));

        PartDefinition cube_r67 = BASE_L_4.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(140, 82).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.2F, -2.0F, 0.0F, -0.3054F, -0.4363F));

        PartDefinition FORELEG_L_4 = LEG_LEFT_4.addOrReplaceChild("FORELEG_L_4", CubeListBuilder.create(), PartPose.offset(8.0F, -3.0F, 2.0F));

        PartDefinition cube_r68 = FORELEG_L_4.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(52, 171).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.603F, 2.6723F, -0.0504F, 0.0F, -0.2618F, 0.2182F));

        PartDefinition cube_r69 = FORELEG_L_4.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(162, 121).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r70 = FORELEG_L_4.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(112, 170).addBox(0.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.303F, 2.7723F, -0.0504F, 0.0F, -0.3054F, 0.3054F));

        PartDefinition FEET_L_4 = FORELEG_L_4.addOrReplaceChild("FEET_L_4", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, 0.0F));

        PartDefinition cube_r71 = FEET_L_4.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(184, 175).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 0.0873F, -0.1745F, -0.3054F));

        PartDefinition cube_r72 = FEET_L_4.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(184, 138).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

        PartDefinition LEG_RIGHT_4 = SECTION_8.addOrReplaceChild("LEG_RIGHT_4", CubeListBuilder.create(), PartPose.offset(-5.5F, 0.0F, 7.0F));

        PartDefinition BASE_R_4 = LEG_RIGHT_4.addOrReplaceChild("BASE_R_4", CubeListBuilder.create(), PartPose.offset(-6.197F, -2.4777F, 1.6996F));

        PartDefinition cube_r73 = BASE_R_4.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(176, 73).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.4363F));

        PartDefinition cube_r74 = BASE_R_4.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(176, 25).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.3F, 0.0F, -0.0479F, 0.3002F, 0.2567F));

        PartDefinition cube_r75 = BASE_R_4.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(14, 142).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.1F, 2.0F, -2.0F, -0.0479F, 0.3002F, 0.2567F));

        PartDefinition cube_r76 = BASE_R_4.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(46, 141).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.2F, -2.0F, 0.0F, 0.3054F, 0.4363F));

        PartDefinition FORELEG_R_4 = LEG_RIGHT_4.addOrReplaceChild("FORELEG_R_4", CubeListBuilder.create(), PartPose.offset(-8.0F, -3.0F, 2.0F));

        PartDefinition cube_r77 = FORELEG_R_4.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(20, 172).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 2.6723F, -0.0504F, 0.0F, 0.2618F, -0.2182F));

        PartDefinition cube_r78 = FORELEG_R_4.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(180, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3015F, -0.0436F, -0.1486F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r79 = FORELEG_R_4.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(172, 16).addBox(-1.0F, -2.5F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.303F, 2.7723F, -0.0504F, 0.0F, 0.3054F, -0.3054F));

        PartDefinition FEET_R_4 = FORELEG_R_4.addOrReplaceChild("FEET_R_4", CubeListBuilder.create(), PartPose.offset(1.0F, 7.5F, 0.25F));

        PartDefinition cube_r80 = FEET_R_4.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(44, 185).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.75F, 0.0873F, 0.1745F, 0.3054F));

        PartDefinition cube_r81 = FEET_R_4.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(40, 185).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.5F, 0.25F, 0.3927F, 0.1745F, 0.3054F));

        PartDefinition SECTION_9 = SECTION_8.addOrReplaceChild("SECTION_9", CubeListBuilder.create(), PartPose.offset(-0.2771F, -0.1223F, 12.4882F));

        PartDefinition SUBSECTION_9_1 = SECTION_9.addOrReplaceChild("SUBSECTION_9_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

        PartDefinition cube_r82 = SUBSECTION_9_1.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(176, 46).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

        PartDefinition cube_r83 = SUBSECTION_9_1.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(72, 70).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 3.5904F, -0.0868F, -0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r84 = SUBSECTION_9_1.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(28, 72).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.4904F, -0.0868F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r85 = SUBSECTION_9_1.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(48, 111).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.8904F, -0.0868F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r86 = SUBSECTION_9_1.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(68, 121).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.5404F, 0.4132F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r87 = SUBSECTION_9_1.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(32, 111).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.8904F, -0.0868F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r88 = SUBSECTION_9_1.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(54, 70).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 3.5904F, -0.0868F, -0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r89 = SUBSECTION_9_1.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(66, 8).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.4904F, -0.0868F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r90 = SUBSECTION_9_1.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8904F, -0.0868F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_10 = SECTION_9.addOrReplaceChild("SECTION_10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2F, 6.0F));

        PartDefinition SUBSECTION_10_1 = SECTION_10.addOrReplaceChild("SUBSECTION_10_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

        PartDefinition cube_r91 = SUBSECTION_10_1.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(94, 179).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r92 = SUBSECTION_10_1.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(76, 38).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 3.3904F, -0.0868F, 0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r93 = SUBSECTION_10_1.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(74, 60).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.2904F, -0.0868F, 0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r94 = SUBSECTION_10_1.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(0, 112).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.6904F, -0.0868F, -0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r95 = SUBSECTION_10_1.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(84, 121).addBox(0.5F, -1.0F, -2.6F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.3404F, -0.5868F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r96 = SUBSECTION_10_1.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(64, 111).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.6904F, -0.0868F, -0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r97 = SUBSECTION_10_1.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(74, 52).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 3.3904F, -0.0868F, 0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r98 = SUBSECTION_10_1.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(0, 74).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.2904F, -0.0868F, 0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r99 = SUBSECTION_10_1.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(40, 34).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6904F, -0.0868F, 0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_11 = SECTION_10.addOrReplaceChild("SECTION_11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.4F, 6.0F));

        PartDefinition SUBSECTION_11_1 = SECTION_11.addOrReplaceChild("SUBSECTION_11_1", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

        PartDefinition cube_r100 = SUBSECTION_11_1.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(176, 54).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r101 = SUBSECTION_11_1.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(18, 80).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, 2.9904F, -0.3868F, -0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r102 = SUBSECTION_11_1.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(64, 78).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 0.8904F, -0.3868F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r103 = SUBSECTION_11_1.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(80, 113).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 4.2904F, -0.3868F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r104 = SUBSECTION_11_1.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(100, 121).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.9404F, 0.1132F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r105 = SUBSECTION_11_1.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(112, 34).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.2904F, -0.3868F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r106 = SUBSECTION_11_1.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(46, 78).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8F, 2.9904F, -0.3868F, -0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r107 = SUBSECTION_11_1.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(78, 28).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 0.8904F, -0.3868F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r108 = SUBSECTION_11_1.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(42, 20).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2904F, -0.3868F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_12 = SECTION_11.addOrReplaceChild("SECTION_12", CubeListBuilder.create(), PartPose.offset(-0.25F, -0.6F, 5.5F));

        PartDefinition SUBSECTION_12_1 = SECTION_12.addOrReplaceChild("SUBSECTION_12_1", CubeListBuilder.create(), PartPose.offset(0.25F, -2.0F, 3.0F));

        PartDefinition cube_r109 = SUBSECTION_12_1.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(100, 176).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r110 = SUBSECTION_12_1.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(116, 21).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 3.4904F, -0.0868F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r111 = SUBSECTION_12_1.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(110, 94).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 2.8904F, -0.1868F, -0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r112 = SUBSECTION_12_1.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(16, 114).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.4904F, -0.1868F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r113 = SUBSECTION_12_1.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(16, 122).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.0404F, 0.6132F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r114 = SUBSECTION_12_1.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(112, 113).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 3.4904F, -0.0868F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r115 = SUBSECTION_12_1.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(108, 86).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 2.8904F, -0.1868F, -0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r116 = SUBSECTION_12_1.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(96, 113).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.4904F, -0.1868F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r117 = SUBSECTION_12_1.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(0, 82).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.3904F, -0.2868F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_13 = SECTION_12.addOrReplaceChild("SECTION_13", CubeListBuilder.create(), PartPose.offset(0.25F, 0.0F, 5.8F));

        PartDefinition SUBSECTION_13_1 = SECTION_13.addOrReplaceChild("SUBSECTION_13_1", CubeListBuilder.create(), PartPose.offset(1.7F, 1.4904F, 2.7132F));

        PartDefinition cube_r118 = SUBSECTION_13_1.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(118, 0).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r119 = SUBSECTION_13_1.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(32, 119).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -0.6F, 0.1F, 0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r120 = SUBSECTION_13_1.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(120, 76).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -2.0F, 0.1F, 0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r121 = SUBSECTION_13_1.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(126, 42).addBox(0.5F, -1.0F, -2.6F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 0.55F, -0.45F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r122 = SUBSECTION_13_1.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(116, 102).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r123 = SUBSECTION_13_1.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(118, 8).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, -0.6F, 0.1F, 0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r124 = SUBSECTION_13_1.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(0, 120).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1F, -2.0F, 0.1F, 0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r125 = SUBSECTION_13_1.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(92, 54).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, -3.1F, 0.2F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r126 = SUBSECTION_13_1.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(112, 179).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, -3.4904F, 0.2868F, -1.0472F, 0.0F, 0.0F));

        PartDefinition SECTION_14 = SECTION_13.addOrReplaceChild("SECTION_14", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2F, 6.2F));

        PartDefinition SUBSECTION_14_1 = SECTION_14.addOrReplaceChild("SUBSECTION_14_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition cube_r127 = SUBSECTION_14_1.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(178, 175).addBox(0.0F, -2.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r128 = SUBSECTION_14_1.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(128, 110).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 3.2904F, 0.3132F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r129 = SUBSECTION_14_1.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(16, 130).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1F, 2.6904F, 0.2132F, -0.0873F, 0.0F, 1.8762F));

        PartDefinition cube_r130 = SUBSECTION_14_1.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(132, 100).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r131 = SUBSECTION_14_1.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(68, 129).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.8404F, 0.4632F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r132 = SUBSECTION_14_1.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(128, 29).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 3.2904F, 0.3132F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r133 = SUBSECTION_14_1.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(116, 129).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 2.6904F, 0.2132F, -0.0873F, 0.0F, -1.8762F));

        PartDefinition cube_r134 = SUBSECTION_14_1.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(132, 16).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, 1.2904F, 0.2132F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r135 = SUBSECTION_14_1.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(72, 94).addBox(-1.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 0.1904F, 0.1132F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_15 = SECTION_14.addOrReplaceChild("SECTION_15", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 5.3F));

        PartDefinition SUBSECTION_15_1 = SECTION_15.addOrReplaceChild("SUBSECTION_15_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition cube_r136 = SUBSECTION_15_1.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(180, 85).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r137 = SUBSECTION_15_1.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(126, 92).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 2.1904F, 0.3132F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r138 = SUBSECTION_15_1.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(48, 133).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, 1.2904F, 0.5132F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition cube_r139 = SUBSECTION_15_1.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(132, 118).addBox(0.5F, -1.0F, -3.4F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.7404F, 0.7132F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r140 = SUBSECTION_15_1.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(126, 50).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 2.1904F, 0.3132F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r141 = SUBSECTION_15_1.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(132, 126).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7F, 1.2904F, 0.5132F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r142 = SUBSECTION_15_1.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(84, 129).addBox(0.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.2904F, 0.5132F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_16 = SECTION_15.addOrReplaceChild("SECTION_16", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2904F, 5.5132F));

        PartDefinition SUBSECTION_16_1 = SECTION_16.addOrReplaceChild("SUBSECTION_16_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition cube_r143 = SUBSECTION_16_1.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(26, 20).addBox(-1.0F, 1.0F, -3.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r144 = SUBSECTION_16_1.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(180, 9).addBox(0.0F, -1.5F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.8096F, 0.3868F, -1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r145 = SUBSECTION_16_1.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(134, 8).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.5F, 0.5F, -0.1309F, 0.0F, -0.8727F));

        PartDefinition cube_r146 = SUBSECTION_16_1.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(0, 128).addBox(-0.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 0.5F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition cube_r147 = SUBSECTION_16_1.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(32, 127).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.5F, 0.5F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition cube_r148 = SUBSECTION_16_1.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(134, 0).addBox(-1.5F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.5F, 0.5F, -0.1309F, 0.0F, 0.8727F));

        PartDefinition SECTION_17 = SECTION_16.addOrReplaceChild("SECTION_17", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5278F, 5.2689F));

        PartDefinition SUBSECTION_17_1 = SECTION_17.addOrReplaceChild("SUBSECTION_17_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition cube_r149 = SUBSECTION_17_1.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(32, 105).addBox(0.0F, -1.5F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition cube_r150 = SUBSECTION_17_1.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(100, 129).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9722F, 0.5311F, 0.0F, 0.0F, 0.7854F));

        PartDefinition SECTION_18 = SECTION_17.addOrReplaceChild("SECTION_18", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.6F));

        PartDefinition SUBSECTION_18_1 = SECTION_18.addOrReplaceChild("SUBSECTION_18_1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));

        PartDefinition cube_r151 = SUBSECTION_18_1.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(64, 133).addBox(0.0F, -0.5F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r152 = SUBSECTION_18_1.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(160, 33).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 0.9722F, 0.6311F, -0.0873F, -0.0873F, 0.7854F));

        PartDefinition cube_r153 = SUBSECTION_18_1.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(132, 159).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 0.9722F, 0.6311F, -0.0873F, 0.0873F, -0.7854F));

        PartDefinition cube_r154 = SUBSECTION_18_1.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(120, 159).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2722F, 0.6311F, 0.0873F, -0.0873F, 0.7854F));

        PartDefinition cube_r155 = SUBSECTION_18_1.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(22, 159).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6722F, 0.6311F, -0.0873F, 0.0873F, 0.7854F));

        PartDefinition END = SECTION_18.addOrReplaceChild("END", CubeListBuilder.create(), PartPose.offset(0.0F, -0.0278F, 4.5311F));

        PartDefinition cube_r156 = END.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(34, 160).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition FRONT_BODY = FULL.addOrReplaceChild("FRONT_BODY", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 1.0F));

        PartDefinition TORSO = FRONT_BODY.addOrReplaceChild("TORSO", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition BELLY = TORSO.addOrReplaceChild("BELLY", CubeListBuilder.create(), PartPose.offset(0.5F, -6.5F, -2.5F));

        PartDefinition cube_r157 = BELLY.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(70, 153).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -7.0F, -2.0F, -0.1745F, 0.0F, -0.2182F));

        PartDefinition cube_r158 = BELLY.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(80, 153).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -7.0F, -4.0F, -0.1309F, 0.5236F, -0.3054F));

        PartDefinition cube_r159 = BELLY.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(90, 153).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -7.0F, 0.5F, -0.1745F, -0.6545F, -0.0436F));

        PartDefinition cube_r160 = BELLY.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(100, 153).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.5F, 1.85F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r161 = BELLY.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(0, 152).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.5F, -5.25F, -0.3054F, 0.3491F, 0.0F));

        PartDefinition cube_r162 = BELLY.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(152, 152).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.5F, 1.85F, -0.0873F, -0.3927F, 0.0F));

        PartDefinition cube_r163 = BELLY.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(152, 41).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.5F, 2.5F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r164 = BELLY.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(76, 172).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.8352F, 3.4547F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r165 = BELLY.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(30, 151).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -6.5F, -5.5F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r166 = BELLY.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(152, 144).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -7.0F, 0.5F, -0.1745F, 0.6545F, 0.0436F));

        PartDefinition cube_r167 = BELLY.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(152, 90).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -2.0F, -0.1745F, 0.0F, 0.2182F));

        PartDefinition cube_r168 = BELLY.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(20, 151).addBox(-1.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -7.0F, -4.0F, -0.1309F, -0.5236F, 0.3054F));

        PartDefinition cube_r169 = BELLY.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(144, 28).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, 6.5F, -1.0F, 0.829F, -0.0436F, 0.2182F));

        PartDefinition cube_r170 = BELLY.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(0, 143).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 2.5F, -2.0F, 0.0F, -0.0436F, 0.2182F));

        PartDefinition cube_r171 = BELLY.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(62, 144).addBox(-1.4F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, 2.5F, 1.0F, 0.1309F, -0.3927F, 0.0873F));

        PartDefinition cube_r172 = BELLY.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(32, 135).addBox(-2.0F, -2.2F, -3.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 4.7F, 4.4F, 0.7854F, -0.0436F, 0.0F));

        PartDefinition cube_r173 = BELLY.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(172, 41).addBox(-2.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.7F, 5.4F, 0.48F, 0.0436F, 0.0F));

        PartDefinition cube_r174 = BELLY.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(170, 127).addBox(-2.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 2.7F, 3.4F, 0.0F, 0.2182F, 0.0F));

        PartDefinition cube_r175 = BELLY.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(88, 179).addBox(1.0F, -2.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.8F, 3.5F, 1.3526F, 0.0F, 0.0F));

        PartDefinition cube_r176 = BELLY.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(82, 179).addBox(1.0F, -2.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.8F, 3.9F, 0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r177 = BELLY.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(150, 136).addBox(-2.0F, -3.0F, -1.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.3F, 3.4F, 0.1745F, 0.2182F, 0.0F));

        PartDefinition cube_r178 = BELLY.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(72, 144).addBox(-1.0007F, -3.0068F, -1.4102F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8007F, -2.2932F, 2.4102F, 0.3054F, -0.9163F, -0.3927F));

        PartDefinition cube_r179 = BELLY.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(144, 50).addBox(-1.8F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.5F, 1.0F, 0.2618F, -0.3491F, -0.3491F));

        PartDefinition cube_r180 = BELLY.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(82, 144).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -2.5F, -2.0F, 0.0F, 0.0436F, 0.3054F));

        PartDefinition cube_r181 = BELLY.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(92, 144).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.5F, -2.0F, 0.0F, 0.0436F, -0.2182F));

        PartDefinition cube_r182 = BELLY.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(102, 144).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3F, 6.5F, -1.0F, 0.829F, 0.0436F, -0.2182F));

        PartDefinition cube_r183 = BELLY.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(144, 108).addBox(-0.2F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -2.5F, 1.0F, 0.2618F, 0.3491F, 0.3491F));

        PartDefinition cube_r184 = BELLY.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(112, 144).addBox(-0.6F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 2.5F, 1.0F, 0.1309F, 0.3927F, -0.0873F));

        PartDefinition cube_r185 = BELLY.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(122, 144).addBox(-0.9993F, -3.0068F, -1.4102F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8007F, -2.2932F, 2.4102F, 0.3054F, 0.9163F, 0.3927F));

        PartDefinition cube_r186 = BELLY.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(10, 151).addBox(-1.0F, -3.0F, -1.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -2.3F, 3.4F, 0.1745F, -0.2182F, 0.0F));

        PartDefinition cube_r187 = BELLY.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(118, 172).addBox(-1.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 2.7F, 3.4F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r188 = BELLY.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(0, 136).addBox(-1.0F, -2.2F, -3.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 4.7F, 4.4F, 0.7854F, 0.0436F, 0.0F));

        PartDefinition cube_r189 = BELLY.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(128, 172).addBox(-1.0F, -2.2F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.7F, 5.4F, 0.48F, -0.0436F, 0.0F));

        PartDefinition cube_r190 = BELLY.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(132, 144).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -2.5F, -5.5F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r191 = BELLY.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(142, 144).addBox(-1.5F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 2.5F, -4.5F, 0.3054F, 0.0F, -0.1309F));

        PartDefinition cube_r192 = BELLY.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(142, 90).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -2.5F, -2.0F, 0.0F, -0.0436F, -0.3054F));

        PartDefinition cube_r193 = BELLY.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(142, 41).addBox(-0.5F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 2.5F, -4.5F, 0.3054F, 0.0F, 0.1309F));

        PartDefinition cube_r194 = BELLY.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(30, 142).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -2.5F, -5.5F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r195 = BELLY.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(60, 153).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.5F, -5.25F, -0.3054F, -0.3491F, 0.0F));

        PartDefinition ARM_RIGHT = TORSO.addOrReplaceChild("ARM_RIGHT", CubeListBuilder.create(), PartPose.offset(-4.9F, -14.5F, -3.0F));

        PartDefinition ARMBASE_R = ARM_RIGHT.addOrReplaceChild("ARMBASE_R", CubeListBuilder.create(), PartPose.offset(-0.0938F, 1.4372F, -1.25F));

        PartDefinition cube_r196 = ARMBASE_R.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(46, 160).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, -0.1309F, -0.2182F));

        PartDefinition cube_r197 = ARMBASE_R.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(172, 6).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5072F, 0.6192F, -0.25F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r198 = ARMBASE_R.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(168, 70).addBox(-1.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5062F, -0.5372F, -0.25F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r199 = ARMBASE_R.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(158, 132).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -0.5F, 0.0F, 0.0873F, -0.2182F));

        PartDefinition cube_r200 = ARMBASE_R.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(158, 160).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.6F, 1.9F, -0.3F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r201 = ARMBASE_R.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(144, 160).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.6F, 2.3F, -0.2F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r202 = ARMBASE_R.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(148, 122).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9F, 1.3F, -0.3F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r203 = ARMBASE_R.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(148, 104).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.9F, 0.9F, -0.4F, 0.0F, 0.0F, -0.1309F));

        PartDefinition FOREARM_R = ARMBASE_R.addOrReplaceChild("FOREARM_R", CubeListBuilder.create(), PartPose.offset(-15.7098F, 2.4454F, -0.3205F));

        PartDefinition cube_r204 = FOREARM_R.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(180, 128).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0792F, 0.3056F, 0.0205F, -0.48F, 0.0F, -0.3054F));

        PartDefinition cube_r205 = FOREARM_R.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, -0.2618F));

        PartDefinition HAND_R = FOREARM_R.addOrReplaceChild("HAND_R", CubeListBuilder.create(), PartPose.offset(0.6153F, 2.36F, -10.0373F));

        PartDefinition cube_r206 = HAND_R.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(182, 146).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.1745F));

        PartDefinition FINGER_R_4 = HAND_R.addOrReplaceChild("FINGER_R_4", CubeListBuilder.create(), PartPose.offset(0.6347F, 0.89F, -0.2127F));

        PartDefinition cube_r207 = FINGER_R_4.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(182, 135).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4807F, 0.2064F, -1.0909F, 0.4363F, -0.7854F, 0.0F));

        PartDefinition FOREFINGER_R_4 = FINGER_R_4.addOrReplaceChild("FOREFINGER_R_4", CubeListBuilder.create(), PartPose.offset(1.0F, 0.5F, -1.75F));

        PartDefinition cube_r208 = FOREFINGER_R_4.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(46, 32).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 0.25F, -2.0F, -0.6981F, -0.1309F, 0.0F));

        PartDefinition cube_r209 = FOREFINGER_R_4.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(24, 146).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1807F, 0.1064F, -0.7409F, 0.0873F, -0.1309F, 0.0F));

        PartDefinition FINGER_R_3 = HAND_R.addOrReplaceChild("FINGER_R_3", CubeListBuilder.create(), PartPose.offset(0.4076F, -0.2636F, -0.5754F));

        PartDefinition cube_r210 = FINGER_R_3.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(16, 182).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7078F, -0.54F, -1.2282F, 0.2618F, -0.6109F, -1.1781F));

        PartDefinition FOREFINGER_R_3 = FINGER_R_3.addOrReplaceChild("FOREFINGER_R_3", CubeListBuilder.create(), PartPose.offset(1.25F, -1.0F, -2.25F));

        PartDefinition cube_r211 = FOREFINGER_R_3.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(18, 45).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.5F, -2.0F, -0.5672F, 0.0F, -1.3963F));

        PartDefinition cube_r212 = FOREFINGER_R_3.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(184, 4).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1422F, -0.04F, 0.2218F, 0.0873F, 0.0F, -1.3963F));

        PartDefinition FINGER_R_2 = HAND_R.addOrReplaceChild("FINGER_R_2", CubeListBuilder.create(), PartPose.offset(-0.5424F, -0.5136F, -0.9254F));

        PartDefinition cube_r213 = FINGER_R_2.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(158, 181).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2422F, -0.29F, -1.2782F, -0.3054F, 0.1309F, -0.4363F));

        PartDefinition FOREFINGER_R_2 = FINGER_R_2.addOrReplaceChild("FOREFINGER_R_2", CubeListBuilder.create(), PartPose.offset(-0.75F, -0.5F, -2.25F));

        PartDefinition cube_r214 = FOREFINGER_R_2.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(42, 33).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -3.25F, 0.0F, 0.6545F, -0.5672F));

        PartDefinition cube_r215 = FOREFINGER_R_2.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(52, 154).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0078F, -0.09F, -0.9282F, 0.0873F, 0.0F, -0.5672F));

        PartDefinition FINGER_R_1 = HAND_R.addOrReplaceChild("FINGER_R_1", CubeListBuilder.create(), PartPose.offset(-0.4924F, 0.8364F, -0.4254F));

        PartDefinition cube_r216 = FINGER_R_1.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(0, 182).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9922F, 0.06F, -1.0782F, 0.0F, 0.7854F, -0.2618F));

        PartDefinition FOREFINGER_R_1 = FINGER_R_1.addOrReplaceChild("FOREFINGER_R_1", CubeListBuilder.create(), PartPose.offset(-1.75F, 0.5F, -2.25F));

        PartDefinition cube_r217 = FOREFINGER_R_1.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(38, 47).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, -2.25F, -0.7854F, 0.0F, -0.2182F));

        PartDefinition cube_r218 = FOREFINGER_R_1.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(100, 184).addBox(-0.4116F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4422F, -0.24F, 0.0718F, 0.0F, 0.0F, -0.2182F));

        PartDefinition TORSO_BOUND_R = ARM_RIGHT.addOrReplaceChild("TORSO_BOUND_R", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

        PartDefinition cube_r219 = TORSO_BOUND_R.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(180, 96).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, -0.1309F));

        PartDefinition cube_r220 = TORSO_BOUND_R.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(182, 41).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.7854F, 0.0F, -0.1309F));

        PartDefinition cube_r221 = TORSO_BOUND_R.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(182, 132).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, -0.7F, 1.5F, 0.0F, 0.0F, -0.2182F));

        PartDefinition LEG_LEFT_1 = TORSO.addOrReplaceChild("LEG_LEFT_1", CubeListBuilder.create(), PartPose.offset(5.9229F, -1.7682F, -3.6986F));

        PartDefinition BASE_L_1 = LEG_LEFT_1.addOrReplaceChild("BASE_L_1", CubeListBuilder.create(), PartPose.offset(7.097F, -1.5777F, -2.3996F));

        PartDefinition cube_r222 = BASE_L_1.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(178, 16).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, -0.3054F));

        PartDefinition cube_r223 = BASE_L_1.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(158, 177).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, 0.3002F, -0.1258F));

        PartDefinition cube_r224 = BASE_L_1.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(132, 24).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, 1.1F, 2.0F, 0.0479F, 0.3002F, -0.1258F));

        PartDefinition cube_r225 = BASE_L_1.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(128, 37).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2F, 1.5F, 2.0F, 0.0F, 0.3054F, -0.3054F));

        PartDefinition FORELEG_L_1 = LEG_LEFT_1.addOrReplaceChild("FORELEG_L_1", CubeListBuilder.create(), PartPose.offset(7.097F, -1.9277F, -2.6496F));

        PartDefinition cube_r226 = FORELEG_L_1.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(60, 161).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.897F, 4.6194F, -0.2498F, 0.0F, 0.2618F, 0.2182F));

        PartDefinition cube_r227 = FORELEG_L_1.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(180, 92).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2925F, -0.1965F, -0.1515F, 0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r228 = FORELEG_L_1.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(0, 160).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.597F, 4.7194F, -0.2498F, 0.0F, 0.3054F, 0.3054F));

        PartDefinition FEET_L_1 = FORELEG_L_1.addOrReplaceChild("FEET_L_1", CubeListBuilder.create(), PartPose.offset(-2.8292F, 8.634F, -0.8774F));

        PartDefinition cube_r229 = FEET_L_1.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(182, 165).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8352F, 1.913F, -0.7227F, -0.0873F, 0.1745F, -0.3054F));

        PartDefinition cube_r230 = FEET_L_1.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(182, 155).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9352F, 1.913F, -0.2227F, -0.3927F, 0.1745F, -0.3054F));

        PartDefinition LEG_RIGHT_1 = TORSO.addOrReplaceChild("LEG_RIGHT_1", CubeListBuilder.create(), PartPose.offset(-5.9229F, -1.7682F, -3.6986F));

        PartDefinition BASE_R_1 = LEG_RIGHT_1.addOrReplaceChild("BASE_R_1", CubeListBuilder.create(), PartPose.offset(-7.097F, -1.5777F, -2.3996F));

        PartDefinition cube_r231 = BASE_R_1.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(178, 20).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.3054F));

        PartDefinition cube_r232 = BASE_R_1.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(168, 177).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0479F, -0.3002F, 0.1258F));

        PartDefinition cube_r233 = BASE_R_1.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(136, 74).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, 1.1F, 2.0F, 0.0479F, -0.3002F, 0.1258F));

        PartDefinition cube_r234 = BASE_R_1.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(132, 134).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.2F, 1.5F, 2.0F, 0.0F, -0.3054F, 0.3054F));

        PartDefinition FORELEG_R_1 = LEG_RIGHT_1.addOrReplaceChild("FORELEG_R_1", CubeListBuilder.create(), PartPose.offset(-8.0F, -2.0F, -3.0F));

        PartDefinition cube_r235 = FORELEG_R_1.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(72, 161).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.903F, 5.0723F, 0.3504F, 0.0F, -0.2618F, -0.2182F));

        PartDefinition cube_r236 = FORELEG_R_1.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(180, 120).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7015F, 0.2564F, 0.4486F, 0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r237 = FORELEG_R_1.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(96, 161).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.603F, 5.1723F, 0.3504F, 0.0F, -0.3054F, -0.3054F));

        PartDefinition FEET_R_1 = FORELEG_R_1.addOrReplaceChild("FEET_R_1", CubeListBuilder.create(), PartPose.offset(1.5F, 9.25F, 0.0F));

        PartDefinition cube_r238 = FEET_R_1.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(182, 160).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.75F, -1.0F, -0.0873F, -0.1745F, 0.3054F));

        PartDefinition cube_r239 = FEET_R_1.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(42, 166).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 1.75F, -0.5F, -0.3927F, -0.1745F, 0.3054F));

        PartDefinition LEG_RIGHT_2 = TORSO.addOrReplaceChild("LEG_RIGHT_2", CubeListBuilder.create(), PartPose.offset(-4.8198F, -1.8458F, -0.9018F));

        PartDefinition FORELEG_R_2 = LEG_RIGHT_2.addOrReplaceChild("FORELEG_R_2", CubeListBuilder.create(), PartPose.offset(-9.6839F, -1.6543F, 2.8358F));

        PartDefinition cube_r240 = FORELEG_R_2.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(180, 116).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r241 = FORELEG_R_2.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(90, 161).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 4.8159F, 0.0982F, 0.0F, 0.2618F, -0.2182F));

        PartDefinition cube_r242 = FORELEG_R_2.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(84, 161).addBox(-1.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3045F, 4.9159F, 0.0982F, 0.0F, 0.3054F, -0.3054F));

        PartDefinition FEET_R_2 = FORELEG_R_2.addOrReplaceChild("FEET_R_2", CubeListBuilder.create(), PartPose.offset(2.0015F, 9.0436F, 0.4486F));

        PartDefinition cube_r243 = FEET_R_2.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(174, 181).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 1.7F, 1.0F, 0.0873F, 0.1745F, 0.3054F));

        PartDefinition cube_r244 = FEET_R_2.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(6, 160).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 1.7F, 0.5F, 0.3927F, 0.1745F, 0.3054F));

        PartDefinition BASE_R_2 = LEG_RIGHT_2.addOrReplaceChild("BASE_R_2", CubeListBuilder.create(), PartPose.offset(-1.7794F, -0.3884F, 0.684F));

        PartDefinition cube_r245 = BASE_R_2.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(126, 58).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, 0.3002F, 0.1258F));

        PartDefinition cube_r246 = BASE_R_2.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(42, 177).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.2F, 2.0F, -0.0479F, 0.3002F, 0.1258F));

        PartDefinition cube_r247 = BASE_R_2.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(10, 178).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3F, -1.1F, 2.0F, 0.0F, 0.3054F, 0.3054F));

        PartDefinition cube_r248 = BASE_R_2.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(124, 70).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.4F, 0.0F, 0.0F, 0.3054F, 0.3054F));

        PartDefinition LEG_LEFT_2 = TORSO.addOrReplaceChild("LEG_LEFT_2", CubeListBuilder.create(), PartPose.offset(5.7198F, -2.2458F, -0.9018F));

        PartDefinition FORELEG_L_2 = LEG_LEFT_2.addOrReplaceChild("FORELEG_L_2", CubeListBuilder.create(), PartPose.offset(9.203F, -1.5223F, 1.6004F));

        PartDefinition cube_r249 = FORELEG_L_2.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(66, 161).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.903F, 5.0723F, 0.6496F, 0.0F, -0.2618F, 0.2182F));

        PartDefinition cube_r250 = FORELEG_L_2.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(180, 112).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2985F, 0.2564F, 0.5514F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r251 = FORELEG_L_2.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(78, 161).addBox(0.0F, -4.5F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.603F, 5.1723F, 0.6496F, 0.0F, -0.3054F, 0.3054F));

        PartDefinition FEET_L_2 = FORELEG_L_2.addOrReplaceChild("FEET_L_2", CubeListBuilder.create(), PartPose.offset(-2.0F, 9.0F, 1.0F));

        PartDefinition cube_r252 = FEET_L_2.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(36, 183).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0179F, 1.75F, 1.0F, 0.0873F, -0.1745F, -0.3054F));

        PartDefinition cube_r253 = FEET_L_2.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(56, 164).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0821F, 1.75F, 0.5F, 0.3927F, -0.1745F, -0.3054F));

        PartDefinition BASE_L_2 = LEG_LEFT_2.addOrReplaceChild("BASE_L_2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition cube_r254 = BASE_L_2.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(136, 78).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0479F, -0.3002F, -0.1258F));

        PartDefinition cube_r255 = BASE_L_2.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(0, 178).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.1F, 2.0F, 0.0F, -0.3054F, -0.3054F));

        PartDefinition cube_r256 = BASE_L_2.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(176, 108).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3F, -1.2F, 2.0F, -0.0479F, -0.3002F, -0.1258F));

        PartDefinition cube_r257 = BASE_L_2.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(42, 28).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.4F, 0.0F, 0.0F, -0.3054F, -0.3054F));

        PartDefinition ARM_LEFT = TORSO.addOrReplaceChild("ARM_LEFT", CubeListBuilder.create(), PartPose.offset(4.9F, -14.5F, -3.0F));

        PartDefinition ARMBASE_L = ARM_LEFT.addOrReplaceChild("ARMBASE_L", CubeListBuilder.create(), PartPose.offset(0.6938F, 0.3372F, -1.55F));

        PartDefinition cube_r258 = ARMBASE_L.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(160, 140).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 3.0F, -0.1F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r259 = ARMBASE_L.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(160, 136).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 3.4F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r260 = ARMBASE_L.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(146, 37).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.3F, 2.4F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r261 = ARMBASE_L.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(116, 110).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9072F, 1.7192F, 0.05F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r262 = ARMBASE_L.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(100, 21).addBox(-3.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9062F, 0.5628F, 0.05F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r263 = ARMBASE_L.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(82, 24).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.3F, 2.0F, 0.1F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r264 = ARMBASE_L.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(158, 6).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.1F, 0.3F, 0.0F, 0.1309F, 0.2182F));

        PartDefinition cube_r265 = ARMBASE_L.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(156, 85).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.1F, -0.3F, 0.0F, -0.0873F, 0.2182F));

        PartDefinition FOREARM_L = ARMBASE_L.addOrReplaceChild("FOREARM_L", CubeListBuilder.create(), PartPose.offset(15.1098F, 3.5454F, -0.0205F));

        PartDefinition cube_r266 = FOREARM_L.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(180, 124).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0792F, 0.3056F, 0.0205F, -0.48F, 0.0F, 0.3054F));

        PartDefinition cube_r267 = FOREARM_L.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2792F, 1.0056F, -3.4795F, 0.2182F, 0.0F, 0.2618F));

        PartDefinition HAND_L = FOREARM_L.addOrReplaceChild("HAND_L", CubeListBuilder.create(), PartPose.offset(-1.7307F, 3.4564F, -11.3409F));

        PartDefinition cube_r268 = HAND_L.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(182, 143).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1154F, -1.0964F, 1.3036F, 0.2182F, 0.0F, -0.1745F));

        PartDefinition FINGER_L_4 = HAND_L.addOrReplaceChild("FINGER_L_4", CubeListBuilder.create(), PartPose.offset(0.75F, -0.75F, 1.0F));

        PartDefinition cube_r269 = FINGER_L_4.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(154, 33).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.75F, -1.0F, 0.4363F, 0.7854F, 0.0F));

        PartDefinition FOREFINGER_L_4 = FINGER_L_4.addOrReplaceChild("FOREFINGER_L_4", CubeListBuilder.create(), PartPose.offset(-0.7693F, 1.2936F, -1.1591F));

        PartDefinition cube_r270 = FOREFINGER_L_4.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(38, 45).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, 0.0F, -2.5F, -0.6981F, 0.1309F, 0.0F));

        PartDefinition cube_r271 = FOREFINGER_L_4.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(40, 142).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6807F, -0.1436F, -1.2409F, 0.0873F, 0.1309F, 0.0F));

        PartDefinition FINGER_L_3 = HAND_L.addOrReplaceChild("FINGER_L_3", CubeListBuilder.create(), PartPose.offset(0.7078F, -1.36F, 0.7282F));

        PartDefinition cube_r272 = FINGER_L_3.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(8, 182).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7078F, -0.54F, -1.2282F, 0.2618F, 0.6109F, 1.1781F));

        PartDefinition FOREFINGER_L_3 = FINGER_L_3.addOrReplaceChild("FOREFINGER_L_3", CubeListBuilder.create(), PartPose.offset(-1.25F, -0.75F, -2.0F));

        PartDefinition cube_r273 = FOREFINGER_L_3.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(38, 43).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.75F, -2.25F, -0.5672F, 0.0F, 1.3963F));

        PartDefinition cube_r274 = FOREFINGER_L_3.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(178, 183).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1422F, -0.29F, -0.0282F, 0.0873F, 0.0F, 1.3963F));

        PartDefinition FINGER_L_2 = HAND_L.addOrReplaceChild("FINGER_L_2", CubeListBuilder.create(), PartPose.offset(1.6578F, -1.61F, 0.3782F));

        PartDefinition cube_r275 = FINGER_L_2.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(150, 181).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2422F, -0.29F, -1.2782F, -0.3054F, -0.1309F, 0.4363F));

        PartDefinition FOREFINGER_L_2 = FINGER_L_2.addOrReplaceChild("FOREFINGER_L_2", CubeListBuilder.create(), PartPose.offset(0.75F, -0.5F, -2.25F));

        PartDefinition cube_r276 = FOREFINGER_L_2.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -3.25F, 0.0F, -0.6545F, 0.5672F));

        PartDefinition cube_r277 = FOREFINGER_L_2.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(46, 72).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0078F, -0.09F, -0.9282F, 0.0873F, 0.0F, 0.5672F));

        PartDefinition FINGER_L_1 = HAND_L.addOrReplaceChild("FINGER_L_1", CubeListBuilder.create(), PartPose.offset(1.8578F, -0.51F, 0.8782F));

        PartDefinition cube_r278 = FINGER_L_1.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(166, 181).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7422F, 0.31F, -1.0782F, 0.0F, -0.7854F, 0.2618F));

        PartDefinition FOREFINGER_L_1 = FINGER_L_1.addOrReplaceChild("FOREFINGER_L_1", CubeListBuilder.create(), PartPose.offset(1.85F, 0.65F, -2.25F));

        PartDefinition cube_r279 = FOREFINGER_L_1.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(18, 47).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.1F, -2.25F, -0.7854F, 0.0F, 0.2182F));

        PartDefinition cube_r280 = FOREFINGER_L_1.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(184, 99).addBox(-0.5884F, -0.3742F, -2.0386F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0922F, -0.14F, 0.0718F, 0.0F, 0.0F, 0.2182F));

        PartDefinition TORSO_BOUND_L = ARM_LEFT.addOrReplaceChild("TORSO_BOUND_L", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

        PartDefinition cube_r281 = TORSO_BOUND_L.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(154, 114).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.1309F));

        PartDefinition cube_r282 = TORSO_BOUND_L.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(182, 59).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, -0.7F, 1.5F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r283 = TORSO_BOUND_L.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(180, 70).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.7854F, 0.0F, 0.1309F));

        PartDefinition CHESTPLATE = TORSO.addOrReplaceChild("CHESTPLATE", CubeListBuilder.create().texOffs(32, 179).addBox(-1.0F, -6.7F, -1.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -9.0F));

        PartDefinition cube_r284 = CHESTPLATE.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(28, 63).addBox(-0.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -4.0F, -2.5F, -0.3927F, 0.2182F, 1.0908F));

        PartDefinition cube_r285 = CHESTPLATE.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(172, 92).addBox(-2.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 6.0F, 0.5F, -0.6981F, 0.8727F, -1.7017F));

        PartDefinition cube_r286 = CHESTPLATE.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(172, 114).addBox(-2.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -7.0F, -1.5F, -0.3927F, -0.3927F, -1.0908F));

        PartDefinition cube_r287 = CHESTPLATE.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(110, 161).addBox(-2.5F, -8.0F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.0F, -2.5F, -0.3927F, -0.2182F, -1.0908F));

        PartDefinition cube_r288 = CHESTPLATE.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(150, 126).addBox(-2.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, -2.5F, -0.3491F, -0.0436F, -1.1345F));

        PartDefinition cube_r289 = CHESTPLATE.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(162, 10).addBox(-2.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -2.5F, -0.4363F, 0.4363F, -1.3526F));

        PartDefinition cube_r290 = CHESTPLATE.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(34, 166).addBox(-2.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.0F, -1.5F, -0.5236F, 0.6981F, -1.5272F));

        PartDefinition cube_r291 = CHESTPLATE.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(92, 172).addBox(-0.5F, -6.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 6.0F, 0.5F, -0.6981F, -0.8727F, 1.7017F));

        PartDefinition cube_r292 = CHESTPLATE.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(26, 165).addBox(-0.5F, -7.5F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 4.0F, -1.5F, -0.5236F, -0.6981F, 1.5272F));

        PartDefinition cube_r293 = CHESTPLATE.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(172, 85).addBox(-0.5F, -6.0F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.0F, -1.5F, -0.3927F, 0.3927F, 1.0908F));

        PartDefinition cube_r294 = CHESTPLATE.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(102, 161).addBox(-0.5F, -8.5F, -0.5F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, -2.5F, -0.4363F, -0.4363F, 1.3526F));

        PartDefinition cube_r295 = CHESTPLATE.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(150, 0).addBox(-0.5F, -9.0F, -0.5F, 3.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, -2.5F, -0.3491F, 0.0436F, 1.1345F));

        PartDefinition UPPER = FRONT_BODY.addOrReplaceChild("UPPER", CubeListBuilder.create(), PartPose.offset(0.0F, -16.5F, -3.0F));

        PartDefinition SECTION_5 = UPPER.addOrReplaceChild("SECTION_5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.2648F, -0.0453F));

        PartDefinition SUBSECTION_5_1 = SECTION_5.addOrReplaceChild("SUBSECTION_5_1", CubeListBuilder.create().texOffs(162, 19).addBox(2.75F, -0.7648F, -6.7047F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(162, 89).addBox(-4.75F, -0.7648F, -6.7047F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 5.0F));

        PartDefinition cube_r296 = SUBSECTION_5_1.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(144, 153).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9163F, 0.0F, 0.0F));

        PartDefinition cube_r297 = SUBSECTION_5_1.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(162, 96).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.7352F, -2.7047F, -0.0873F, 0.6109F, -0.0436F));

        PartDefinition cube_r298 = SUBSECTION_5_1.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(166, 58).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5202F, 1.4696F, -7.3796F, -0.1309F, 0.5672F, -0.0436F));

        PartDefinition cube_r299 = SUBSECTION_5_1.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(166, 52).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 0.2352F, -1.7047F, 0.0436F, -0.48F, 0.0F));

        PartDefinition cube_r300 = SUBSECTION_5_1.addOrReplaceChild("cube_r300", CubeListBuilder.create().texOffs(166, 46).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, 0.2352F, -1.7047F, 0.0436F, 0.48F, 0.0F));

        PartDefinition cube_r301 = SUBSECTION_5_1.addOrReplaceChild("cube_r301", CubeListBuilder.create().texOffs(110, 153).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -0.9547F, 0.0436F, 0.0F, 0.0F));

        PartDefinition cube_r302 = SUBSECTION_5_1.addOrReplaceChild("cube_r302", CubeListBuilder.create().texOffs(162, 39).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.7352F, -2.7047F, -0.0873F, -0.6109F, 0.0436F));

        PartDefinition cube_r303 = SUBSECTION_5_1.addOrReplaceChild("cube_r303", CubeListBuilder.create().texOffs(166, 26).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5202F, 1.4696F, -7.3796F, -0.1309F, -0.5672F, 0.0436F));

        PartDefinition cube_r304 = SUBSECTION_5_1.addOrReplaceChild("cube_r304", CubeListBuilder.create().texOffs(36, 80).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4852F, -8.2047F, -0.0873F, 0.0F, 0.0F));

        PartDefinition SECTION_4 = SECTION_5.addOrReplaceChild("SECTION_4", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition SUBSECTION_4_1 = SECTION_4.addOrReplaceChild("SUBSECTION_4_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 0.1F));

        PartDefinition cube_r305 = SUBSECTION_4_1.addOrReplaceChild("cube_r305", CubeListBuilder.create().texOffs(84, 172).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 4.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r306 = SUBSECTION_4_1.addOrReplaceChild("cube_r306", CubeListBuilder.create().texOffs(50, 145).addBox(-1.0F, -3.5F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.5648F, 1.9453F, 0.1745F, -0.5672F, -0.2182F));

        PartDefinition cube_r307 = SUBSECTION_4_1.addOrReplaceChild("cube_r307", CubeListBuilder.create().texOffs(162, 151).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(162, 114).addBox(-8.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -3.0648F, -0.3047F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r308 = SUBSECTION_4_1.addOrReplaceChild("cube_r308", CubeListBuilder.create().texOffs(168, 64).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -3.3148F, -2.8047F, 0.2182F, -0.6981F, 0.0F));

        PartDefinition cube_r309 = SUBSECTION_4_1.addOrReplaceChild("cube_r309", CubeListBuilder.create().texOffs(162, 144).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0648F, 2.9453F, 0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r310 = SUBSECTION_4_1.addOrReplaceChild("cube_r310", CubeListBuilder.create().texOffs(40, 145).addBox(-1.0F, -3.5F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.5648F, 1.9453F, 0.1745F, 0.5672F, 0.2182F));

        PartDefinition cube_r311 = SUBSECTION_4_1.addOrReplaceChild("cube_r311", CubeListBuilder.create().texOffs(166, 108).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -3.3148F, -2.8047F, 0.2182F, 0.6981F, 0.0F));

        PartDefinition cube_r312 = SUBSECTION_4_1.addOrReplaceChild("cube_r312", CubeListBuilder.create().texOffs(166, 73).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.3148F, -3.8047F, 0.2618F, 0.0F, 0.0F));

        PartDefinition SECTION_3 = SECTION_4.addOrReplaceChild("SECTION_3", CubeListBuilder.create(), PartPose.offset(0.0F, -3.7648F, 0.0453F));

        PartDefinition SUBSECTION_3_1 = SECTION_3.addOrReplaceChild("SUBSECTION_3_1", CubeListBuilder.create(), PartPose.offset(0.0F, -4.7352F, 1.8547F));

        PartDefinition cube_r313 = SUBSECTION_3_1.addOrReplaceChild("cube_r313", CubeListBuilder.create().texOffs(174, 164).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r314 = SUBSECTION_3_1.addOrReplaceChild("cube_r314", CubeListBuilder.create().texOffs(102, 170).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1769F, -0.0905F, -3.4232F, 0.7854F, -0.48F, -0.5672F));

        PartDefinition cube_r315 = SUBSECTION_3_1.addOrReplaceChild("cube_r315", CubeListBuilder.create().texOffs(46, 164).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(154, 164).addBox(5.5F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, 3.2653F, -3.0495F, 0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r316 = SUBSECTION_3_1.addOrReplaceChild("cube_r316", CubeListBuilder.create().texOffs(170, 0).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1769F, -0.0905F, -3.4232F, 0.7854F, 0.48F, 0.5672F));

        PartDefinition cube_r317 = SUBSECTION_3_1.addOrReplaceChild("cube_r317", CubeListBuilder.create().texOffs(170, 10).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1769F, 1.9095F, -1.1732F, 0.5236F, -0.5236F, -0.48F));

        PartDefinition cube_r318 = SUBSECTION_3_1.addOrReplaceChild("cube_r318", CubeListBuilder.create().texOffs(172, 154).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.529F, 4.5438F, -5.0879F, 0.6545F, 0.6109F, 0.2182F));

        PartDefinition cube_r319 = SUBSECTION_3_1.addOrReplaceChild("cube_r319", CubeListBuilder.create().texOffs(172, 144).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.529F, 4.5438F, -5.0879F, 0.6545F, -0.6109F, -0.2182F));

        PartDefinition cube_r320 = SUBSECTION_3_1.addOrReplaceChild("cube_r320", CubeListBuilder.create().texOffs(168, 79).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1769F, 1.9095F, -1.1732F, 0.5236F, 0.5236F, 0.48F));

        PartDefinition cube_r321 = SUBSECTION_3_1.addOrReplaceChild("cube_r321", CubeListBuilder.create().texOffs(144, 164).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2352F, -1.1047F, 0.6545F, 0.0F, 0.0F));

        PartDefinition SECTION_2 = SECTION_3.addOrReplaceChild("SECTION_2", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -2.0F));

        PartDefinition SUBSECTION_2_1 = SECTION_2.addOrReplaceChild("SUBSECTION_2_1", CubeListBuilder.create(), PartPose.offset(2.4269F, -2.5757F, -1.3185F));

        PartDefinition cube_r322 = SUBSECTION_2_1.addOrReplaceChild("cube_r322", CubeListBuilder.create().texOffs(16, 165).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.3526F, -0.1745F, -0.5672F));

        PartDefinition cube_r323 = SUBSECTION_2_1.addOrReplaceChild("cube_r323", CubeListBuilder.create().texOffs(118, 165).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 165).addBox(5.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4269F, 2.1058F, 0.6238F, 0.9163F, 0.0F, 0.0F));

        PartDefinition cube_r324 = SUBSECTION_2_1.addOrReplaceChild("cube_r324", CubeListBuilder.create().texOffs(128, 165).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8538F, 0.0F, 0.0F, 1.3526F, 0.1745F, 0.5672F));

        PartDefinition cube_r325 = SUBSECTION_2_1.addOrReplaceChild("cube_r325", CubeListBuilder.create().texOffs(172, 149).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.148F, 3.3844F, -1.4146F, 1.1781F, -0.3054F, -0.8727F));

        PartDefinition cube_r326 = SUBSECTION_2_1.addOrReplaceChild("cube_r326", CubeListBuilder.create().texOffs(172, 159).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7059F, 3.3844F, -1.4146F, 1.1781F, 0.3054F, 0.8727F));

        PartDefinition cube_r327 = SUBSECTION_2_1.addOrReplaceChild("cube_r327", CubeListBuilder.create().texOffs(164, 164).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, -1.1743F, 0.5685F, 1.2217F, 0.0F, 0.0F));

        PartDefinition cube_r328 = SUBSECTION_2_1.addOrReplaceChild("cube_r328", CubeListBuilder.create().texOffs(174, 132).addBox(0.0F, -1.5F, -1.5F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, -1.6889F, 0.8903F, 0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r329 = SUBSECTION_2_1.addOrReplaceChild("cube_r329", CubeListBuilder.create().texOffs(178, 29).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4269F, 4.8883F, -1.8362F, 1.0472F, 0.0F, 0.0F));

        PartDefinition SECTION_1 = SECTION_2.addOrReplaceChild("SECTION_1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));

        PartDefinition SUBSECTION_1_1 = SECTION_1.addOrReplaceChild("SUBSECTION_1_1", CubeListBuilder.create().texOffs(178, 33).addBox(-4.25F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(120, 138).addBox(-4.25F, -6.0F, -1.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(170, 121).addBox(0.25F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(42, 171).addBox(-6.75F, -3.5F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, 2.5626F, -2.1548F));

        PartDefinition cube_r330 = SUBSECTION_1_1.addOrReplaceChild("cube_r330", CubeListBuilder.create().texOffs(10, 146).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

        PartDefinition cube_r331 = SUBSECTION_1_1.addOrReplaceChild("cube_r331", CubeListBuilder.create().texOffs(148, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -4.75F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r332 = SUBSECTION_1_1.addOrReplaceChild("cube_r332", CubeListBuilder.create().texOffs(148, 99).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

        PartDefinition cube_r333 = SUBSECTION_1_1.addOrReplaceChild("cube_r333", CubeListBuilder.create().texOffs(142, 68).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r334 = SUBSECTION_1_1.addOrReplaceChild("cube_r334", CubeListBuilder.create().texOffs(138, 165).addBox(0.0F, -1.5F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -5.9272F, 1.0265F, 0.7854F, 0.0F, 0.0F));

        PartDefinition HEAD = SECTION_1.addOrReplaceChild("HEAD", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

        PartDefinition JAW = HEAD.addOrReplaceChild("JAW", CubeListBuilder.create(), PartPose.offset(0.0F, 2.95F, -3.0F));

        PartDefinition cube_r335 = JAW.addOrReplaceChild("cube_r335", CubeListBuilder.create().texOffs(148, 117).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r336 = JAW.addOrReplaceChild("cube_r336", CubeListBuilder.create().texOffs(144, 59).addBox(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.8812F, -10.9716F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r337 = JAW.addOrReplaceChild("cube_r337", CubeListBuilder.create().texOffs(182, 53).addBox(-0.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0188F, -9.6716F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r338 = JAW.addOrReplaceChild("cube_r338", CubeListBuilder.create().texOffs(140, 62).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, -3.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r339 = JAW.addOrReplaceChild("cube_r339", CubeListBuilder.create().texOffs(156, 67).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.2198F, -2.75F, -0.2618F, 0.0F, 0.8727F));

        PartDefinition cube_r340 = JAW.addOrReplaceChild("cube_r340", CubeListBuilder.create().texOffs(158, 0).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9217F, 0.7314F, -2.75F, -0.2618F, 0.0F, -0.5236F));

        PartDefinition cube_r341 = JAW.addOrReplaceChild("cube_r341", CubeListBuilder.create().texOffs(158, 126).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.2198F, -2.75F, -0.2618F, 0.0F, -0.8727F));

        PartDefinition cube_r342 = JAW.addOrReplaceChild("cube_r342", CubeListBuilder.create().texOffs(162, 103).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9217F, 0.4814F, -5.5F, -0.1745F, 0.0F, -0.5236F));

        PartDefinition cube_r343 = JAW.addOrReplaceChild("cube_r343", CubeListBuilder.create().texOffs(174, 99).addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.171F, -0.4698F, -5.5F, -0.1745F, 0.0F, -0.8727F));

        PartDefinition cube_r344 = JAW.addOrReplaceChild("cube_r344", CubeListBuilder.create().texOffs(182, 56).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4923F, -0.4428F, -9.7659F, -0.3491F, 0.0F, 0.5236F));

        PartDefinition cube_r345 = JAW.addOrReplaceChild("cube_r345", CubeListBuilder.create().texOffs(182, 50).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4923F, -0.4428F, -9.7659F, -0.3491F, 0.0F, -0.5236F));

        PartDefinition cube_r346 = JAW.addOrReplaceChild("cube_r346", CubeListBuilder.create().texOffs(182, 47).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7923F, -0.1428F, -8.2159F, -0.2182F, 0.0F, -0.5236F));

        PartDefinition cube_r347 = JAW.addOrReplaceChild("cube_r347", CubeListBuilder.create().texOffs(182, 44).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7923F, -0.1428F, -8.2159F, -0.2182F, 0.0F, 0.5236F));

        PartDefinition cube_r348 = JAW.addOrReplaceChild("cube_r348", CubeListBuilder.create().texOffs(112, 62).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9217F, 0.4814F, -5.5F, -0.1745F, 0.0F, 0.5236F));

        PartDefinition cube_r349 = JAW.addOrReplaceChild("cube_r349", CubeListBuilder.create().texOffs(26, 174).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.171F, -0.4698F, -5.5F, -0.1745F, 0.0F, 0.8727F));

        PartDefinition cube_r350 = JAW.addOrReplaceChild("cube_r350", CubeListBuilder.create().texOffs(156, 79).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9217F, 0.7314F, -2.75F, -0.2618F, 0.0F, 0.5236F));

        PartDefinition FOREHEAD = HEAD.addOrReplaceChild("FOREHEAD", CubeListBuilder.create().texOffs(150, 10).addBox(3.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(102, 9).addBox(-1.5F, -42.8F, -15.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(154, 49).addBox(-5.75F, -38.8F, -14.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 38.0F, 10.0F));

        PartDefinition cube_r351 = FOREHEAD.addOrReplaceChild("cube_r351", CubeListBuilder.create().texOffs(178, 77).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5758F, -37.8788F, -24.7793F, 0.9163F, -0.1309F, 0.1745F));

        PartDefinition cube_r352 = FOREHEAD.addOrReplaceChild("cube_r352", CubeListBuilder.create().texOffs(178, 66).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2033F, -37.975F, -23.4418F, 0.6981F, -0.2618F, 0.6981F));

        PartDefinition cube_r353 = FOREHEAD.addOrReplaceChild("cube_r353", CubeListBuilder.create().texOffs(94, 36).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -43.4F, -14.4F, -2.6616F, 0.0F, 0.0F));

        PartDefinition cube_r354 = FOREHEAD.addOrReplaceChild("cube_r354", CubeListBuilder.create().texOffs(36, 94).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.45F, -43.15F, -14.4F, -2.6616F, 0.1309F, 0.1309F));

        PartDefinition cube_r355 = FOREHEAD.addOrReplaceChild("cube_r355", CubeListBuilder.create().texOffs(36, 86).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -42.55F, -14.5F, -2.6616F, -0.1309F, -0.3054F));

        PartDefinition cube_r356 = FOREHEAD.addOrReplaceChild("cube_r356", CubeListBuilder.create().texOffs(54, 86).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -41.55F, -14.5F, -2.618F, 0.0436F, -0.7854F));

        PartDefinition cube_r357 = FOREHEAD.addOrReplaceChild("cube_r357", CubeListBuilder.create().texOffs(18, 88).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -40.2F, -20.75F, -2.7925F, -0.0873F, 0.4363F));

        PartDefinition cube_r358 = FOREHEAD.addOrReplaceChild("cube_r358", CubeListBuilder.create().texOffs(178, 62).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5758F, -37.8788F, -24.7793F, 0.9163F, 0.1309F, -0.1745F));

        PartDefinition cube_r359 = FOREHEAD.addOrReplaceChild("cube_r359", CubeListBuilder.create().texOffs(126, 181).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7258F, -35.1389F, -25.2042F, 0.9163F, 0.2618F, -1.0036F));

        PartDefinition cube_r360 = FOREHEAD.addOrReplaceChild("cube_r360", CubeListBuilder.create().texOffs(118, 181).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3758F, -35.2389F, -25.6042F, 1.309F, 0.2618F, -0.6981F));

        PartDefinition cube_r361 = FOREHEAD.addOrReplaceChild("cube_r361", CubeListBuilder.create().texOffs(178, 37).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2033F, -37.975F, -23.4418F, 0.6981F, 0.2618F, -0.6981F));

        PartDefinition cube_r362 = FOREHEAD.addOrReplaceChild("cube_r362", CubeListBuilder.create().texOffs(90, 86).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -39.5F, -19.85F, -2.7925F, -0.1745F, 0.7418F));

        PartDefinition cube_r363 = FOREHEAD.addOrReplaceChild("cube_r363", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -39.5F, -19.85F, -2.7925F, 0.1745F, -0.7418F));

        PartDefinition cube_r364 = FOREHEAD.addOrReplaceChild("cube_r364", CubeListBuilder.create().texOffs(90, 68).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -40.2F, -20.75F, -2.7925F, 0.0873F, -0.4363F));

        PartDefinition cube_r365 = FOREHEAD.addOrReplaceChild("cube_r365", CubeListBuilder.create().texOffs(66, 181).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -36.0051F, -25.7508F, 1.3963F, 0.0F, 0.0F));

        PartDefinition cube_r366 = FOREHEAD.addOrReplaceChild("cube_r366", CubeListBuilder.create().texOffs(20, 34).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -40.75F, -20.0F, -2.7925F, 0.0F, 0.0F));

        PartDefinition cube_r367 = FOREHEAD.addOrReplaceChild("cube_r367", CubeListBuilder.create().texOffs(72, 86).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -40.05F, -14.25F, -2.618F, 0.0873F, -1.0472F));

        PartDefinition cube_r368 = FOREHEAD.addOrReplaceChild("cube_r368", CubeListBuilder.create().texOffs(138, 171).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -37.8F, -15.5F, 0.0F, -0.2618F, 0.0F));

        PartDefinition cube_r369 = FOREHEAD.addOrReplaceChild("cube_r369", CubeListBuilder.create().texOffs(54, 94).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.45F, -43.15F, -14.4F, -2.6616F, -0.1309F, -0.1309F));

        PartDefinition cube_r370 = FOREHEAD.addOrReplaceChild("cube_r370", CubeListBuilder.create().texOffs(40, 154).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -39.3F, -12.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r371 = FOREHEAD.addOrReplaceChild("cube_r371", CubeListBuilder.create().texOffs(92, 137).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -40.55F, -12.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r372 = FOREHEAD.addOrReplaceChild("cube_r372", CubeListBuilder.create().texOffs(106, 137).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -41.3F, -12.0F, 0.0F, 0.0F, 1.2217F));

        PartDefinition cube_r373 = FOREHEAD.addOrReplaceChild("cube_r373", CubeListBuilder.create().texOffs(82, 78).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -42.55F, -14.5F, -2.6616F, 0.1309F, 0.3054F));

        PartDefinition cube_r374 = FOREHEAD.addOrReplaceChild("cube_r374", CubeListBuilder.create().texOffs(84, 0).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -41.55F, -14.5F, -2.618F, -0.0436F, 0.7854F));

        PartDefinition cube_r375 = FOREHEAD.addOrReplaceChild("cube_r375", CubeListBuilder.create().texOffs(84, 8).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.75F, -40.05F, -14.25F, -2.618F, -0.0873F, 1.0472F));

        PartDefinition cube_r376 = FOREHEAD.addOrReplaceChild("cube_r376", CubeListBuilder.create().texOffs(78, 137).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -41.3F, -12.0F, 0.0F, 0.0F, -1.2217F));

        PartDefinition cube_r377 = FOREHEAD.addOrReplaceChild("cube_r377", CubeListBuilder.create().texOffs(64, 137).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -40.55F, -12.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r378 = FOREHEAD.addOrReplaceChild("cube_r378", CubeListBuilder.create().texOffs(154, 27).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -39.3F, -12.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r379 = FOREHEAD.addOrReplaceChild("cube_r379", CubeListBuilder.create().texOffs(0, 172).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -37.05F, -22.5F, 0.4363F, -0.3927F, 0.0F));

        PartDefinition cube_r380 = FOREHEAD.addOrReplaceChild("cube_r380", CubeListBuilder.create().texOffs(182, 152).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.95F, -39.6F, -17.15F, 0.0F, 0.2618F, 0.0F));

        PartDefinition cube_r381 = FOREHEAD.addOrReplaceChild("cube_r381", CubeListBuilder.create().texOffs(182, 149).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.95F, -39.6F, -17.15F, 0.0F, -0.2618F, 0.0F));

        PartDefinition cube_r382 = FOREHEAD.addOrReplaceChild("cube_r382", CubeListBuilder.create().texOffs(168, 171).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -37.8F, -19.25F, 0.0F, -0.2618F, 0.0F));

        PartDefinition cube_r383 = FOREHEAD.addOrReplaceChild("cube_r383", CubeListBuilder.create().texOffs(148, 171).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -37.8F, -19.25F, 0.0F, 0.2618F, 0.0F));

        PartDefinition cube_r384 = FOREHEAD.addOrReplaceChild("cube_r384", CubeListBuilder.create().texOffs(158, 171).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -37.8F, -15.5F, 0.0F, 0.2618F, 0.0F));

        PartDefinition cube_r385 = FOREHEAD.addOrReplaceChild("cube_r385", CubeListBuilder.create().texOffs(10, 172).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -37.05F, -22.5F, 0.4363F, 0.3927F, 0.0F));

        PartDefinition cube_r386 = FOREHEAD.addOrReplaceChild("cube_r386", CubeListBuilder.create().texOffs(134, 181).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3758F, -35.2389F, -25.6042F, 1.309F, -0.2618F, 0.6981F));

        PartDefinition cube_r387 = FOREHEAD.addOrReplaceChild("cube_r387", CubeListBuilder.create().texOffs(142, 181).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7258F, -35.1389F, -25.2042F, 0.9163F, -0.2618F, 1.0036F));

        PartDefinition MANE = FOREHEAD.addOrReplaceChild("MANE", CubeListBuilder.create(), PartPose.offset(0.0F, -44.0F, -11.0F));

        PartDefinition cube_r388 = MANE.addOrReplaceChild("cube_r388", CubeListBuilder.create().texOffs(96, 24).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.102F, -3.5883F, 0.8438F, 1.2217F, -0.48F, -0.6981F));

        PartDefinition cube_r389 = MANE.addOrReplaceChild("cube_r389", CubeListBuilder.create().texOffs(90, 99).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.602F, -3.0883F, 1.0938F, 1.0908F, 0.4363F, 0.8727F));

        PartDefinition cube_r390 = MANE.addOrReplaceChild("cube_r390", CubeListBuilder.create().texOffs(100, 16).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.852F, -1.8383F, 1.0938F, 0.9599F, 0.48F, 1.0472F));

        PartDefinition cube_r391 = MANE.addOrReplaceChild("cube_r391", CubeListBuilder.create().texOffs(100, 76).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.352F, -0.0883F, 1.0938F, 0.9599F, 0.5672F, 1.2217F));

        PartDefinition cube_r392 = MANE.addOrReplaceChild("cube_r392", CubeListBuilder.create().texOffs(100, 81).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.602F, 1.6617F, 1.8438F, 0.6545F, 0.5672F, 1.2217F));

        PartDefinition cube_r393 = MANE.addOrReplaceChild("cube_r393", CubeListBuilder.create().texOffs(90, 94).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.602F, -3.0883F, 1.0938F, 1.0908F, -0.4363F, -0.8727F));

        PartDefinition cube_r394 = MANE.addOrReplaceChild("cube_r394", CubeListBuilder.create().texOffs(0, 98).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.602F, 1.6617F, 1.8438F, 0.6545F, -0.5672F, -1.2217F));

        PartDefinition cube_r395 = MANE.addOrReplaceChild("cube_r395", CubeListBuilder.create().texOffs(96, 29).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.352F, -0.0883F, 1.0938F, 0.9599F, -0.5672F, -1.2217F));

        PartDefinition cube_r396 = MANE.addOrReplaceChild("cube_r396", CubeListBuilder.create().texOffs(92, 62).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.852F, -1.8383F, 1.0938F, 0.9599F, -0.48F, -1.0472F));

        PartDefinition FIXED_JAW = HEAD.addOrReplaceChild("FIXED_JAW", CubeListBuilder.create().texOffs(134, 138).addBox(-1.5F, -35.05F, -14.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 38.0F, 10.0F));

        PartDefinition cube_r397 = FIXED_JAW.addOrReplaceChild("cube_r397", CubeListBuilder.create().texOffs(132, 153).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r398 = FIXED_JAW.addOrReplaceChild("cube_r398", CubeListBuilder.create().texOffs(154, 108).addBox(-1.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -35.8F, -15.75F, -0.2618F, 0.0F, 1.2217F));

        PartDefinition cube_r399 = FIXED_JAW.addOrReplaceChild("cube_r399", CubeListBuilder.create().texOffs(10, 159).addBox(-0.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -35.8F, -15.75F, -0.2618F, 0.0F, -1.2217F));

        PartDefinition cube_r400 = FIXED_JAW.addOrReplaceChild("cube_r400", CubeListBuilder.create().texOffs(120, 16).addBox(-0.5F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -35.8F, -18.5F, -0.1745F, 0.0F, -1.2217F));

        PartDefinition cube_r401 = FIXED_JAW.addOrReplaceChild("cube_r401", CubeListBuilder.create().texOffs(178, 171).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5693F, -36.3069F, -21.7381F, -0.3491F, 0.0F, -1.2217F));

        PartDefinition cube_r402 = FIXED_JAW.addOrReplaceChild("cube_r402", CubeListBuilder.create().texOffs(178, 81).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5693F, -36.3069F, -21.7381F, -0.3491F, 0.0F, 1.2217F));

        PartDefinition cube_r403 = FIXED_JAW.addOrReplaceChild("cube_r403", CubeListBuilder.create().texOffs(116, 29).addBox(-1.5F, -0.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -35.8F, -18.5F, -0.1745F, 0.0F, 1.2217F));

        PartDefinition cube_r404 = FIXED_JAW.addOrReplaceChild("cube_r404", CubeListBuilder.create().texOffs(154, 55).addBox(-1.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -35.55F, -12.0F, 0.0F, 0.0F, 1.2217F));

        PartDefinition cube_r405 = FIXED_JAW.addOrReplaceChild("cube_r405", CubeListBuilder.create().texOffs(154, 61).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.421F, -34.7698F, -12.0F, 0.0F, 0.0F, 0.8727F));

        PartDefinition cube_r406 = FIXED_JAW.addOrReplaceChild("cube_r406", CubeListBuilder.create().texOffs(154, 73).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1717F, -33.8186F, -12.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r407 = FIXED_JAW.addOrReplaceChild("cube_r407", CubeListBuilder.create().texOffs(120, 153).addBox(-0.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -35.55F, -12.0F, 0.0F, 0.0F, -1.2217F));

        PartDefinition cube_r408 = FIXED_JAW.addOrReplaceChild("cube_r408", CubeListBuilder.create().texOffs(150, 21).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1717F, -33.8186F, -12.0F, 0.0F, 0.0F, -0.5236F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int i) {
        this.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
    }

    @Override
    public @NotNull ModelPart root() {
        return this.FULL;
    }

    @Override
    public void setupAnim(@NotNull ContagionIncarnationEntity entity, float limbSwing,
                          float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);
        if (!entity.isInWaterOrBubble() && !entity.isSprinting()) {
            animateWalk(ContagionIncarnationAnimation.walking, limbSwing, limbSwingAmount, 1f, 2.5f);
        } else if (!entity.isInWaterOrBubble()) {
            animateWalk(ContagionIncarnationAnimation.crawling, limbSwing, limbSwingAmount, 1f, 2.5f);
        }
        animate(entity.dyingAnimationState, ContagionIncarnationAnimation.death, ageInTicks);
        animate(entity.idleAmbient, ContagionIncarnationAnimation.arm_ambient, ageInTicks);
        animate(entity.ambient, ContagionIncarnationAnimation.leg_spasm, ageInTicks);
        animate(entity.ambient, ContagionIncarnationAnimation.breathing, ageInTicks);
        animate(entity.spawnAnimation, ContagionIncarnationAnimation.spawning, ageInTicks);
        animate(entity.rightSwing, ContagionIncarnationAnimation.arm_right_attack, ageInTicks);
        animate(entity.leftSwing, ContagionIncarnationAnimation.arm_left_attack, ageInTicks);
        animate(entity.sonicStrike, ContagionIncarnationAnimation.scream, ageInTicks);
        if (!entity.sonicStrike.isStarted()) this.animateLookAt(entity, netHeadYaw, headPitch);
        this.animateTail(entity);
    }

    private void animateLookAt(ContagionIncarnationEntity entity, float yaw, float pitch) {
        this.section_5.homogeneousRotation(yaw / 6f, pitch / 6f);
    }

    private void animateTail(ContagionIncarnationEntity entity) {
        this.SECTION_6.xRot = entity.getSection_6_rotX();
        this.SECTION_6.yRot = entity.getSection_6_rotY();
        this.SECTION_7.xRot = entity.getSection_7_rotX();
        this.SECTION_7.yRot = entity.getSection_7_rotY();
        this.SECTION_8.xRot = entity.getSection_8_rotX();
        this.SECTION_8.yRot = entity.getSection_8_rotY();
        this.SECTION_9.xRot = entity.getSection_9_rotX();
        this.SECTION_9.yRot = entity.getSection_9_rotY();
        this.SECTION_10.xRot = entity.getSection_10_rotX();
        this.SECTION_10.yRot = entity.getSection_10_rotY();
        this.SECTION_11.xRot = entity.getSection_11_rotX();
        this.SECTION_11.yRot = entity.getSection_11_rotY();
        this.SECTION_12.xRot = entity.getSection_12_rotX();
        this.SECTION_12.yRot = entity.getSection_12_rotY();
        this.SECTION_13.xRot = entity.getSection_13_rotX();
        this.SECTION_13.yRot = entity.getSection_13_rotY();
        this.SECTION_14.xRot = entity.getSection_14_rotX();
        this.SECTION_14.yRot = entity.getSection_14_rotY();
        this.SECTION_15.xRot = entity.getSection_15_rotX();
        this.SECTION_15.yRot = entity.getSection_15_rotY();
        this.SECTION_16.xRot = entity.getSection_16_rotX();
        this.SECTION_16.yRot = entity.getSection_16_rotY();
        this.SECTION_17.xRot = entity.getSection_17_rotX();
        this.SECTION_17.yRot = entity.getSection_17_rotY();
        this.SECTION_18.xRot = entity.getSection_18_rotX();
        this.SECTION_18.yRot = entity.getSection_18_rotY();
        this.END.xRot = entity.getEnd_rotX();
        this.END.yRot = entity.getEnd_rotY();
    }

    private static class Section {
        private static final float MAX_ANGLE = 20F;
        private static final float UNFOLDING_SPEED = 0.3f;
        private static final float REFOLDING_SPEED = 0.1f;
        public float length;
        private final ModelPart section;
        private final Section nextSection;
        private float rotYOld = 0f;

        public Section(float length, ModelPart section, Section nextSection) {
            this.length = length;
            this.section = section;
            this.nextSection = nextSection;
            this.rotYOld = this.getRotationY();
        }

        public void homogeneousRotation(float yaw, float pitch) {
            // Create a rotation equally shared among all the children. This creates a simple curvature effect.
            this.rotateX(pitch);
            this.rotateY(yaw);
            if (this.nextSection != null) {
                this.nextSection.homogeneousRotation(yaw, pitch);
            }
        }

        public float getRotationX() {
            // NB : return the ABSOLUTE rotation in space, not with respect to its parents
            return this.section.xRot;
        }

        public float getRotationY() {
            // NB : return the ABSOLUTE rotation in space, not with respect to its parents
            return this.section.yRot;
        }

        public void absoluteRotateX(float angle) {
            // NB : sets the RELATIVE rotation with respect to its parent
            this.section.xRot = angle * rad;
        }

        public void absoluteRotateY(float angle) {
            // NB : sets the RELATIVE rotation with respect to its parent
            this.rotYOld = this.getRotationY();
            this.section.yRot = angle * rad;
        }

        public void rotateX(float angle) {
            this.section.xRot = this.section.xRot + angle * rad;
        }

        public void rotateY(float angle) {
            this.rotYOld = this.getRotationY();
            this.section.yRot = this.section.yRot + angle * rad;
        }
    }

}