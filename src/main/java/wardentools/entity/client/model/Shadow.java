package wardentools.entity.client.model;


import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.ShadowAnimation;
import wardentools.entity.client.renderstate.ShadowRenderState;
import wardentools.entity.utils.RenderToBufferFunction;


public class Shadow extends EntityModel<ShadowRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION
			= new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "shadow"), "main");
	private RenderToBufferFunction renderToBufferFunction;
	private final ModelPart FULL;
	private final ModelPart HEAD;
	private final ModelPart BODY;
	private final ModelPart TORSO;
	private final ModelPart LEG_R;
	private final ModelPart LEG_L;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;

	public Shadow(ModelPart root) {
		super(root);
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

	/*
	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay) {
		if (this.renderToBufferFunction == null) {
			this.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		} else {
			this.renderToBufferFunction
					.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
		}
	}*/

	@Override
	public void setupAnim(@NotNull ShadowRenderState state) {
		this.renderToBufferFunction = state.renderToBufferFunction; // Do not remove this line
		root().getAllParts().forEach(ModelPart::resetPose);
		if (state.setUpAnimFunction != null){
			state.setUpAnimFunction.setupAnim(state);
		} else {
			this.HEAD.xRot = this.HEAD.xRot + state.xRot * ((float) Math.PI / 180F);
			this.HEAD.yRot = this.HEAD.yRot + state.yRot * ((float) Math.PI / 180F);
			animate(state.walkAnim, ShadowAnimation.walking, state.ageInTicks);
			animate(state.idleAnimation, ShadowAnimation.idle, state.ageInTicks);
			animate(state.stasisAnimation, ShadowAnimation.stasis, state.ageInTicks);
			animate(state.walkToIdleAnimation, ShadowAnimation.walkToIdle, state.ageInTicks);
		}
	}
}