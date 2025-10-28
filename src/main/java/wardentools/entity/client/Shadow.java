package wardentools.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.animations.ShadowAnimation;
import wardentools.entity.custom.ShadowEntity;
import wardentools.entity.utils.RenderToBufferFunction;

@OnlyIn(Dist.CLIENT)
public class Shadow extends HierarchicalModel<ShadowEntity> {
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

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay, int i) {
		if (this.renderToBufferFunction == null) {
			this.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
		} else {
			this.renderToBufferFunction
					.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
		}
	}

	@Override
	public @NotNull ModelPart root() {
		return this.FULL;
	}

	@Override
	public void setupAnim(@NotNull ShadowEntity shadow, float limbSwing,
						  float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.renderToBufferFunction = shadow.getRenderToBufferFunction(); // Do not remove this line
		root().getAllParts().forEach(ModelPart::resetPose);
		if (shadow.getSetUpAnimFunction() != null){
			shadow.getSetUpAnimFunction().setupAnim(shadow.getMimicEntity(), limbSwing, limbSwingAmount,
					ageInTicks, netHeadYaw, headPitch);
		} else {
			this.HEAD.xRot = this.HEAD.xRot + headPitch * ((float) Math.PI / 180F);
			this.HEAD.yRot = this.HEAD.yRot + netHeadYaw * ((float) Math.PI / 180F);
			animate(shadow.idleAnimation, ShadowAnimation.idle, ageInTicks);
			animate(shadow.stasisAnimation, ShadowAnimation.stasis, ageInTicks);
			animateWalk(ShadowAnimation.walking,
					limbSwing * 13F, limbSwingAmount * 13F,
					1F, 2.5F);
		}
	}
}