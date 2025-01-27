package wardentools.entity.client.model;


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.entity.animations.DeepLurkerAnimation;
import wardentools.entity.client.renderstate.DeeplurkerRenderState;


public class DeepLurker extends EntityModel<DeeplurkerRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deeplurker"), "main");

    private final ModelParts parts;

    public DeepLurker(ModelPart root) {
        super(root);
        ModelPart FULL = root.getChild("FULL");
        ModelPart LOW_BODY = FULL.getChild("LOW_BODY");
        ModelPart ARM_R = LOW_BODY.getChild("ARM_R");
        ModelPart foreArmR = ARM_R.getChild("foreArmR");
        ModelPart ARM_L = LOW_BODY.getChild("ARM_L");
        ModelPart foreArmL = ARM_L.getChild("foreArmL");
        ModelPart LegR = LOW_BODY.getChild("LegR");
        ModelPart LegL = LOW_BODY.getChild("LegL");
        ModelPart HEAD = FULL.getChild("HEAD");
        ModelPart earL = HEAD.getChild("earL");
        ModelPart earR = HEAD.getChild("earR");
        ModelPart earR_r1 = earR.getChild("earR_r1");
        ModelPart body = LOW_BODY.getChild("body");

        this.parts = new ModelParts(FULL, LOW_BODY, ARM_R, foreArmR, ARM_L, foreArmL, LegR, LegL, HEAD, earL, earR, earR_r1, body);
    }

    @SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partdefinition = meshDefinition.getRoot();

        PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        PartDefinition LOW_BODY = FULL.addOrReplaceChild("LOW_BODY", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 0.0F));
        PartDefinition ARM_R = LOW_BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(6, 2).addBox(0.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, -2.0F));
        PartDefinition foreArmR = ARM_R.addOrReplaceChild("foreArmR", CubeListBuilder.create().texOffs(7, 2).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -1.0F));
        PartDefinition ARM_L = LOW_BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(6, 2).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));
        PartDefinition foreArmL = ARM_L.addOrReplaceChild("foreArmL", CubeListBuilder.create().texOffs(7, 2).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));
        PartDefinition LegR = LOW_BODY.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(7, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, -3.0F));
        PartDefinition LegL = LOW_BODY.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(7, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 2.0F));
        PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 19).addBox(-3.0F, -5.0F, -4.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));
        PartDefinition earL = HEAD.addOrReplaceChild("earL", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.0F, 3.0F));
        PartDefinition earR = HEAD.addOrReplaceChild("earR", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -3.0F));
        PartDefinition earR_r1 = earR.addOrReplaceChild("earR_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));
        PartDefinition body = LOW_BODY.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -3.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    private record ModelParts(ModelPart FULL,ModelPart LOW_BODY, ModelPart ARM_R, ModelPart foreArmR, ModelPart ARM_L, ModelPart foreArmL, ModelPart LegR, ModelPart LegL, ModelPart HEAD, ModelPart earL, ModelPart earR, ModelPart earR_r1, ModelPart body) {}

	
	
	@Override
	public void setupAnim(DeeplurkerRenderState state) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(state.scaredAnimationState, DeepLurkerAnimation.scared, state.ageInTicks);
		animate(state.calmAnimationState, DeepLurkerAnimation.calm1, state.ageInTicks);

        animateWalk(DeepLurkerAnimation.walking, state.walkAnimationPos, state.walkAnimationSpeed,
                1f, 2.5f);

        parts.HEAD().yRot = parts.HEAD().yRot + state.yRot * ((float)Math.PI / 180F);
        parts.HEAD().zRot = parts.HEAD().zRot + state.xRot * ((float)Math.PI / 180F);
	}
}
