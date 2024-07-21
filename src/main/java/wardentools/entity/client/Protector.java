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
import wardentools.ModMain;
import wardentools.entity.animations.ProtectorAnimation;
import wardentools.entity.custom.ProtectorEntity;

public class Protector extends HierarchicalModel<ProtectorEntity> {
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, "protector"), "main");
	private final ModelParts parts;

	public Protector(ModelPart root) {
		ModelPart FULL = root.getChild("FULL");
		ModelPart BODY = FULL.getChild("BODY");
		ModelPart HEAD = BODY.getChild("HEAD");
		ModelPart EAR_L = HEAD.getChild("EAR_L");
		ModelPart EAR_R = HEAD.getChild("EAR_R");
		ModelPart TORSO = BODY.getChild("TORSO");
		ModelPart ARM_L = TORSO.getChild("ARM_L");
		ModelPart ARM_R = TORSO.getChild("ARM_R");
		ModelPart LEG_R = FULL.getChild("LEG_R");
		ModelPart LEG_L = FULL.getChild("LEG_L");
		
		this.parts = new ModelParts(FULL, BODY, HEAD, TORSO, ARM_R, ARM_L, LEG_R, LEG_L, EAR_R, EAR_L);
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));
		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));
		PartDefinition HEAD = BODY.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));
		PartDefinition EAR_L = HEAD.addOrReplaceChild("EAR_L", CubeListBuilder.create().texOffs(59, 6).addBox(8.0F, -52.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 33.0F, 0.0F));
		PartDefinition EAR_R = HEAD.addOrReplaceChild("EAR_R", CubeListBuilder.create().texOffs(58, 37).addBox(-18.0F, -52.0F, 0.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 33.0F, 0.0F));
		PartDefinition TORSO = BODY.addOrReplaceChild("TORSO", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, -5.0F, 18.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));
		PartDefinition ARM_L = TORSO.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(44, 50).addBox(0.0F, -3.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 4.0F, 0.0F));
		PartDefinition ARM_R = TORSO.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(0, 58).addBox(-8.0F, -3.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 4.0F, 0.0F));
		PartDefinition LEG_R = FULL.addOrReplaceChild("LEG_R", CubeListBuilder.create().texOffs(76, 77).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 11.0F, 0.0F));
		PartDefinition LEG_L = FULL.addOrReplaceChild("LEG_L", CubeListBuilder.create().texOffs(76, 49).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 11.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}
	
	private record ModelParts(ModelPart FULL, ModelPart BODY, ModelPart HEAD, ModelPart TORSO, ModelPart ARM_R, ModelPart ARM_L, ModelPart LEG_R, ModelPart LEG_L, ModelPart EAR_R, ModelPart EAR_L) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.parts.FULL().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.parts.FULL();
	}

	@Override
	public void setupAnim(ProtectorEntity entity, float limbSwing,
			float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		if (!entity.getIsTargeting()) {
			animateWalk(ProtectorAnimation.walking, limbSwing, limbSwingAmount, 1f, 2.5f);
		} else {
			animateWalk(ProtectorAnimation.running, limbSwing, limbSwingAmount, 1f, 2.5f);
		}
		animate(entity.attackAnimationState, ProtectorAnimation.hit, ageInTicks);
		
		parts.HEAD().xRot = parts.HEAD().xRot + headPitch * ((float)Math.PI / 180F);
        parts.HEAD().yRot = parts.HEAD().yRot + netHeadYaw * ((float)Math.PI / 180F);
	}
}