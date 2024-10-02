package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.TemperAnimation;
import wardentools.entity.custom.TemperEntity;

public class Temper extends HierarchicalModel<TemperEntity> implements ArmedModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(ModMain.MOD_ID, "temper"), "main");
	private static final float MAX_FLAP_ANGLE = 27f * ((float)Math.PI / 180F); // Rad
	private boolean wingOnAscending = true;
	private float wingAngle = 0f; // Rad
	private final ModelPart FULL;
	private final ModelPart HEAD;
	private final ModelPart BODY;
	private final ModelPart TORSO;
	private final ModelPart WING_R;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;
	private final ModelPart WING_L;

	public Temper(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.HEAD = FULL.getChild("HEAD");
		this.BODY = FULL.getChild("BODY");
		this.TORSO = BODY.getChild("TORSO");
		this.WING_R = BODY.getChild("WING_R");
		this.ARM_L = BODY.getChild("ARM_L");
		this.ARM_R = BODY.getChild("ARM_R");
		this.WING_L = BODY.getChild("WING_L");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(-0.5F, 27.0F, -1.0F));
		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, 1.0F));
		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition TORSO = BODY.addOrReplaceChild("TORSO", CubeListBuilder.create().texOffs(2, 18).addBox(-1.0F, 2.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -8.0F, 1.0F));
    	PartDefinition WING_R = BODY.addOrReplaceChild("WING_R", CubeListBuilder.create(), PartPose.offset(0.0F, -6.5F, 2.0F));
		PartDefinition wing_r_r1 = WING_R.addOrReplaceChild("wing_r_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -0.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -0.4363F, 0.0F));
		PartDefinition ARM_L = BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create(), PartPose.offset(2.0F, -7.75F, 1.0F));
		PartDefinition arm_l_r1 = ARM_L.addOrReplaceChild("arm_l_r1", CubeListBuilder.create().texOffs(23, 0).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.25F, 0.0F, 0.0F, 0.0F, -0.2618F));
		PartDefinition ARM_R = BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create(), PartPose.offset(-1.0F, -7.75F, 1.0F));
		PartDefinition arm_r_r1 = ARM_R.addOrReplaceChild("arm_r_r1", CubeListBuilder.create().texOffs(23, 6).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.25F, 0.0F, 0.0F, 0.0F, 0.2182F));
    	PartDefinition WING_L = BODY.addOrReplaceChild("WING_L", CubeListBuilder.create(), PartPose.offset(1.0F, -6.5F, 2.0F));
		PartDefinition wing_l_r1 = WING_L.addOrReplaceChild("wing_l_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -0.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.4363F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay,
							   float red, float green, float blue, float alpha) {
		FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return FULL;
	}

	public void translateToHand(@NotNull HumanoidArm arm, PoseStack poseStack) {
		poseStack.translate(0.09375F, 1.0F, 0.0F);
		poseStack.mulPose(Axis.XP.rotation(this.ARM_L.xRot));
		poseStack.mulPose(Axis.ZP.rotation(-15f * ((float)Math.PI / 180F) + this.ARM_L.zRot));
		poseStack.translate(0.0F, 0.15625F, 0.0F);
		poseStack.scale(0.4F, 0.4F, 0.4F);
	}

	@Override
	public void setupAnim(TemperEntity entity, float limbSwing,
						  float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		if (!entity.onGround()){
			this.flap(2f);
		}
		animate(entity.attackAnimationState, TemperAnimation.attack, ageInTicks);
		HEAD.xRot = HEAD.xRot + headPitch * ((float)Math.PI / 180F);
		HEAD.yRot = HEAD.yRot + netHeadYaw * ((float)Math.PI / 180F);

	}

	private void flap(float flapSpeed){
		float newAngle = this.wingAngle + (this.wingOnAscending ? flapSpeed : -flapSpeed) * ((float)Math.PI / 180F);
		if (newAngle > MAX_FLAP_ANGLE){
			this.wingOnAscending = false;
		} else if (newAngle < -MAX_FLAP_ANGLE) {
			this.wingOnAscending = true;
		}
		this.wingAngle = newAngle;
		this.WING_L.yRot = this.WING_L.yRot + this.wingAngle;
		this.WING_R.yRot = this.WING_R.yRot - this.wingAngle;
	}
}