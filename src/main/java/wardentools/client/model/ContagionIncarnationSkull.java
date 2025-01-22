package wardentools.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class ContagionIncarnationSkull extends Model {
    private final ModelPart root;

    public ContagionIncarnationSkull(ModelPart parts) {
        super(RenderType::entitySolid);
        this.root = parts;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(46, 24).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 53).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(154, 35).addBox(-0.9393F, -4.3393F, -6.2197F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.2659F, -0.1825F, 0.1194F));
        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(154, 56).addBox(0.3501F, -3.9857F, -5.66F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.9969F, -0.473F, 0.5895F));
        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(81, 9).addBox(-1.05F, 0.9533F, -9.351F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3126F, 0.0F, 0.0F));
        PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(81, 17).addBox(-0.6486F, 0.8317F, -9.3838F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3146F, 0.0785F, 0.1675F));
        PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(76, 51).addBox(-2.5599F, 0.871F, -9.4699F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3293F, -0.0207F, -0.3309F));
        PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(14, 76).addBox(-3.036F, 1.5527F, -9.6897F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3631F, 0.2865F, -0.743F));
        PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(3, 71).addBox(-0.1382F, 1.4236F, -2.1637F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.4687F, -0.2279F, 0.385F));
        PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(154, 72).addBox(-1.0607F, -4.3393F, -6.2197F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.2659F, 0.1825F, -0.1194F));
        PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(36, 156).addBox(-2.7287F, -5.1121F, -6.6595F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.1334F, 0.549F, -0.9168F));
        PartDefinition cube_r10 = bb_main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(74, 156).addBox(-1.637F, -6.7153F, -5.7227F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.6077F, 0.473F, -0.5895F));
        PartDefinition cube_r11 = bb_main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(84, 154).addBox(-2.3501F, -3.9857F, -5.66F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.9969F, 0.473F, -0.5895F));
        PartDefinition cube_r12 = bb_main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(70, 16).addBox(0.9561F, 1.9088F, -3.0034F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.515F, -0.4014F, 0.662F));
        PartDefinition cube_r13 = bb_main.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(69, 70).addBox(-2.9561F, 1.9088F, -3.0034F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.515F, 0.4014F, -0.662F));
        PartDefinition cube_r14 = bb_main.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(70, 62).addBox(-1.8618F, 1.4236F, -2.1637F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.4687F, 0.2279F, -0.385F));
        PartDefinition cube_r15 = bb_main.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(156, 76).addBox(-0.45F, -6.637F, -6.5388F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.7453F, 0.0F, 0.0F));
        PartDefinition cube_r16 = bb_main.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 27).addBox(-1.05F, 1.1297F, -2.9426F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.4435F, 0.0F, 0.0F));
        PartDefinition cube_r17 = bb_main.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(72, 39).addBox(-3.7182F, 1.8656F, -10.0028F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.4323F, 0.3865F, -1.003F));
        PartDefinition cube_r18 = bb_main.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(131, 24).addBox(-5.75F, 0.6092F, 4.9188F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
               .texOffs(76, 113).addBox(-1.5F, -3.3908F, 3.9188F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
               .texOffs(132, 93).addBox(3.75F, 0.6092F, 4.9188F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.3491F, 0.0F, 0.0F));
        PartDefinition cube_r19 = bb_main.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 79).addBox(-1.3514F, 0.8317F, -9.3838F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3146F, -0.0785F, -0.1675F));
        PartDefinition cube_r20 = bb_main.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(131, 105).addBox(-5.0322F, 1.0008F, 4.9188F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.3186F, -0.1451F, 0.413F));
        PartDefinition cube_r21 = bb_main.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(115, 50).addBox(-4.2815F, 0.6682F, 3.9188F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.2519F, -0.2443F, 0.7543F));
        PartDefinition cube_r22 = bb_main.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(36, 77).addBox(-3.5463F, 0.4676F, 3.9188F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.1238F, -0.3272F, 1.2013F));
        PartDefinition cube_r23 = bb_main.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(54, 78).addBox(0.5599F, 0.871F, -9.4699F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3293F, 0.0207F, 0.3309F));
        PartDefinition cube_r24 = bb_main.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(43, 77).addBox(1.036F, 1.5527F, -9.6897F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.3631F, -0.2865F, 0.743F));
        PartDefinition cube_r25 = bb_main.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(25, 77).addBox(1.7182F, 1.8656F, -10.0028F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, -2.4323F, -0.3865F, 1.003F));
        PartDefinition cube_r26 = bb_main.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(60, 115).addBox(1.5463F, 0.4676F, 3.9188F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.1238F, 0.3272F, -1.2013F));
        PartDefinition cube_r27 = bb_main.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 117).addBox(2.2815F, 0.6682F, 3.9188F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.2519F, 0.2443F, -0.7543F));
        PartDefinition cube_r28 = bb_main.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(26, 132).addBox(3.0322F, 1.0008F, 4.9188F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.3186F, 0.1451F, -0.413F));
        PartDefinition cube_r29 = bb_main.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(143, 24).addBox(-4.4111F, 0.1847F, -5.0419F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.8116F, -0.3678F, -0.1407F));
        PartDefinition cube_r30 = bb_main.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(76, 25).addBox(3.4111F, 0.1847F, -5.0419F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 0.8116F, 0.3678F, 0.1407F));
        PartDefinition cube_r31 = bb_main.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(31, 155).addBox(0.637F, -6.7153F, -5.7227F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.6077F, -0.473F, 0.5895F));
        PartDefinition cube_r32 = bb_main.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(111, 8).addBox(1.7287F, -5.1121F, -6.6595F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.7406F, -1.8088F, 1.1334F, -0.549F, 0.9168F));
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int i) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}

