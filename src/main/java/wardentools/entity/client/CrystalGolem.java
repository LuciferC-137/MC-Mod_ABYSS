package wardentools.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.CrystalGolemAnimation;
import wardentools.entity.custom.CrystalGolemEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

@SuppressWarnings("unused")
public class CrystalGolem extends HierarchicalModel<CrystalGolemEntity> {
	public static final ModelLayerLocation LAYER_LOCATION
			= new ModelLayerLocation(ResourceLocation
			.fromNamespaceAndPath(ModMain.MOD_ID, "crystal_golem"), "main");
	private final ModelPart FULL;
	private final ModelPart leg_r;
	private final ModelPart leg_l;
	private final ModelPart top;
	private final ModelPart torso;
	private final ModelPart arm_r;
	private final ModelPart arm_l;
	private final ModelPart head;
	private final ModelPart ear_l;
	private final ModelPart ear_r;

	public CrystalGolem(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.leg_r = this.FULL.getChild("leg_r");
		this.leg_l = this.FULL.getChild("leg_l");
		this.top = this.FULL.getChild("top");
		this.torso = this.top.getChild("torso");
		this.arm_r = this.torso.getChild("arm_r");
		this.arm_l = this.torso.getChild("arm_l");
		this.head = this.top.getChild("head");
		this.ear_l = this.head.getChild("ear_l");
		this.ear_r = this.head.getChild("ear_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition leg_r = FULL.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(32, 40).addBox(-2.0F, 0.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -5.5F, 0.0F));
		PartDefinition leg_l = FULL.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(14, 41).addBox(-2.0F, 0.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -5.5F, 0.0F));
		PartDefinition top = FULL.addOrReplaceChild("top", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));
		PartDefinition torso = top.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 0.0F, -2.5F, 10.0F, 13.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(14, 31).addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 49).addBox(-1.0F, 2.25F, 1.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(48, 23).addBox(-1.5F, 5.25F, 2.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(6, 49).addBox(-1.0F, 9.25F, 1.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));
		PartDefinition cube_r1 = torso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 49).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 9.5F, -2.75F, 0.0F, -0.1745F, 0.4363F));
		PartDefinition cube_r2 = torso.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 49).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 9.5F, -2.75F, 0.0F, 0.1745F, -0.4363F));
		PartDefinition cube_r3 = torso.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 49).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 3.5F, -2.75F, 0.0F, 0.1745F, 0.4363F));
		PartDefinition cube_r4 = torso.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 49).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.5F, -2.75F, 0.0F, -0.1745F, -0.4363F));
		PartDefinition cube_r5 = torso.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(48, 43).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 5.5F, -2.75F, 0.0F, -0.1745F, -0.1745F));
		PartDefinition cube_r6 = torso.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 47).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 7.5F, -2.75F, 0.0F, -0.1745F, 0.1745F));
		PartDefinition cube_r7 = torso.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(48, 45).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 7.5F, -2.75F, 0.0F, 0.1745F, -0.1745F));
		PartDefinition cube_r8 = torso.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 41).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 5.5F, -2.75F, 0.0F, 0.1745F, 0.1745F));
		PartDefinition arm_r = torso.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(44, 12).addBox(-2.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 2.25F, 0.0F));
		PartDefinition arm_l = torso.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(44, 6).addBox(0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 2.25F, 0.0F));
		PartDefinition head = top.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-5.5F, -8.0F, -2.0F, 11.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(48, 27).addBox(4.5F, -7.0F, -3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(48, 34).addBox(-5.5F, -7.0F, -3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(44, 0).addBox(-4.5F, -8.0F, -3.0F, 9.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(44, 3).addBox(-4.5F, -2.0F, -3.0F, 9.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(48, 18).addBox(-1.5F, -6.0F, -3.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));
		PartDefinition ear_l = head.addOrReplaceChild("ear_l", CubeListBuilder.create().texOffs(32, 18).addBox(-2.0F, -8.0F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -7.0F, 0.0F));
		PartDefinition ear_r = head.addOrReplaceChild("ear_r", CubeListBuilder.create().texOffs(32, 29).addBox(-6.0F, -9.0F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -6.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
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
	public void setupAnim(@NotNull CrystalGolemEntity golem, float limbSwing,
						  float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);

		animate(golem.deactivatedState1, CrystalGolemAnimation.deactivate_1, ageInTicks);
		animate(golem.deactivatedState2, CrystalGolemAnimation.deactivate_2, ageInTicks);
		animate(golem.lightingState, CrystalGolemAnimation.lighting_candle, ageInTicks);
		animate(golem.randomLookAround, CrystalGolemAnimation.look_around, ageInTicks);
		animate(golem.reactivateFrom2, CrystalGolemAnimation.rise, ageInTicks);
		animate(golem.reactivateFrom1, CrystalGolemAnimation.reactivate, ageInTicks);

		animateWalk(CrystalGolemAnimation.walking, limbSwing * 11F,
				limbSwingAmount * 11F, 1F, 2.5F);

		float attackProgress = golem.getAttackAnim(ageInTicks);
		if (attackProgress > 0f) {
			float f = (float)Math.sin(attackProgress * Math.PI);
			float f1 = (float)Math.sin((1 - (1 - attackProgress) * (1 - attackProgress)) * Math.PI);

			arm_r.xRot -= f * 1.2F + f1 * 0.4F;
			arm_r.yRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
			arm_r.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		}

		head.xRot += headPitch * ((float)Math.PI / 180F);
		head.yRot += netHeadYaw * ((float)Math.PI / 180F);

	}
}