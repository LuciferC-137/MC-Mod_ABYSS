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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;
import wardentools.entity.animations.DeepLurkerAnimation;
import wardentools.entity.custom.DeepLurkerEntity;

@OnlyIn(Dist.CLIENT)
public class DeepLurker extends HierarchicalModel<DeepLurkerEntity> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "deeplurker"), "main");

    private final ModelPart FULL;
    private final ModelPart LOW_BODY;
    private final ModelPart body;
    private final ModelPart LegR;
    private final ModelPart LegL;
    private final ModelPart ARM_R;
    private final ModelPart foreArmR;
    private final ModelPart ARM_L;
    private final ModelPart foreArmL;
    private final ModelPart HEAD;
    private final ModelPart earL;
    private final ModelPart earR;

    public DeepLurker(ModelPart root) {
        this.FULL = root.getChild("FULL");
        this.LOW_BODY = this.FULL.getChild("LOW_BODY");
        this.body = this.LOW_BODY.getChild("body");
        this.LegR = this.LOW_BODY.getChild("LegR");
        this.LegL = this.LOW_BODY.getChild("LegL");
        this.ARM_R = this.LOW_BODY.getChild("ARM_R");
        this.foreArmR = this.ARM_R.getChild("foreArmR");
        this.ARM_L = this.LOW_BODY.getChild("ARM_L");
        this.foreArmL = this.ARM_L.getChild("foreArmL");
        this.HEAD = this.FULL.getChild("HEAD");
        this.earL = this.HEAD.getChild("earL");
        this.earR = this.HEAD.getChild("earR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));
        PartDefinition LOW_BODY = FULL.addOrReplaceChild("LOW_BODY", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition body = LOW_BODY.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -2.9955F, -0.0136F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -1.0F));
        PartDefinition LegR = LOW_BODY.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(24, 0).addBox(-1.1F, 1.1F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
               .texOffs(24, 13).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.0F, 1.0F));
        PartDefinition LegL = LOW_BODY.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(24, 8).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
               .texOffs(0, 22).addBox(0.1F, 1.1F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 1.0F));
        PartDefinition ARM_R = LOW_BODY.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(0, 17).addBox(-0.5F, -0.5F, -3.25F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 1.5F, 0.25F));
        PartDefinition foreArmR = ARM_R.addOrReplaceChild("foreArmR", CubeListBuilder.create().texOffs(20, 21).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1F, -0.1F, -2.75F));
        PartDefinition ARM_L = LOW_BODY.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(10, 21).addBox(-0.5F, -0.5F, -3.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 1.5F, 0.5F));
        PartDefinition foreArmL = ARM_L.addOrReplaceChild("foreArmL", CubeListBuilder.create().texOffs(24, 4).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -0.1F, -3.0F));
        PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition earL = HEAD.addOrReplaceChild("earL", CubeListBuilder.create().texOffs(14, 15).addBox(-1.0F, -3.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -4.0F, -0.5F));
        PartDefinition earR = HEAD.addOrReplaceChild("earR", CubeListBuilder.create().texOffs(14, 9).addBox(-3.0F, -3.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.0F, -0.5F));
        return LayerDefinition.create(meshdefinition, 32, 32);
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
	public void setupAnim(DeepLurkerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.scaredAnimationState, DeepLurkerAnimation.scared, ageInTicks);
		animate(entity.calmAnimationState, DeepLurkerAnimation.calm1, ageInTicks);
        animate(entity.climbAnimationState, DeepLurkerAnimation.climbing, ageInTicks * 4F);
        animate(entity.pickingAnimationState, DeepLurkerAnimation.picking, ageInTicks);

        if (!entity.isClimbing()) {
            animateWalk(DeepLurkerAnimation.walking,
                    limbSwing * 4F, limbSwingAmount * 4F,
                    1f, 2.5f);
        }
        if (entity.isAtTopOfTree()) {
            this.FULL.xRot = (float)Math.PI;
            this.FULL.y = 22.0F;
        }

        this.HEAD.xRot = this.HEAD.xRot + headPitch * ((float)Math.PI / 180F);
        this.HEAD.yRot = this.HEAD.yRot + netHeadYaw * ((float)Math.PI / 180F);

        if (entity.isBaby()) {
            this.HEAD.offsetScale(new Vector3f(0.7F, 0.7F, 0.7F));
        }
	}
}
