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
import wardentools.entity.animations.PaleWandererAnimation;
import wardentools.entity.client.renderstate.PaleWandererRenderState;

public class PaleWanderer extends EntityModel<PaleWandererRenderState> {

	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "pale_wanderer"), "main");

	private final ModelParts parts;

	public PaleWanderer(ModelPart root) {
		super(root);
		ModelPart FULL = root.getChild("FULL");
		ModelPart BODY = FULL.getChild("BODY");
		ModelPart BODY_FRONT = BODY.getChild("BODY_FRONT");
		ModelPart NECK = BODY_FRONT.getChild("NECK");
		ModelPart LEG_FRONT_L = BODY_FRONT.getChild("LEG_FRONT_L");
		ModelPart LEG_FRONT_R = BODY_FRONT.getChild("LEG_FRONT_R");
		ModelPart BODY_BACK = BODY.getChild("BODY_BACK");
		ModelPart TAIL = BODY_BACK.getChild("TAIL");
		ModelPart TAIL_MAIN = TAIL.getChild("TAIL_MAIN");
		ModelPart TAIL_END = TAIL.getChild("TAIL_END");
		ModelPart LEG_BACK_L = BODY_BACK.getChild("LEG_BACK_L");
		ModelPart LEG_BACK_R = BODY_BACK.getChild("LEG_BACK_R");
		ModelPart BACK = BODY_BACK.getChild("BACK");
		ModelPart HEAD = FULL.getChild("HEAD");
		ModelPart EAR_R = HEAD.getChild("EAR_R");
		ModelPart EAR_L = HEAD.getChild("EAR_L");
		
		this.parts = new ModelParts(FULL, BODY, BODY_FRONT,
				NECK, LEG_FRONT_L, LEG_FRONT_R,
				BODY_BACK, TAIL, TAIL_MAIN, TAIL_END,
				LEG_BACK_L, LEG_BACK_R, BACK, HEAD,
				EAR_R, EAR_L);
	}

	@SuppressWarnings("unused")
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
	
	private record ModelParts(ModelPart FULL, ModelPart BODY, ModelPart BODY_FRONT,
			ModelPart NECK, ModelPart LEG_FRONT_L, ModelPart LEG_FRONT_R,
			ModelPart BODY_BACK, ModelPart TAIL, ModelPart TAIL_MAIN, ModelPart TAIL_END,
			ModelPart LEG_BACK_L, ModelPart LEG_BACK_R, ModelPart BACK, ModelPart HEAD,
			ModelPart EAR_R, ModelPart EAR_L) {}


	@Override
	public void setupAnim(PaleWandererRenderState state) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(state.calmAnimationState, PaleWandererAnimation.sit, state.ageInTicks);

		animateWalk(PaleWandererAnimation.walking, state.walkAnimationPos,
				state.walkAnimationSpeed, 1f, 2.5f);
	
		parts.HEAD().xRot = parts.HEAD().xRot + state.xRot * ((float)Math.PI / 180F);
        parts.HEAD().yRot = parts.HEAD().yRot + state.yRot * ((float)Math.PI / 180F);

	}
}