package wardentools.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.custom.ShadowEntity;
import wardentools.entity.utils.RenderToBufferFunction;

@SuppressWarnings("unused")
public class Shadow extends HierarchicalModel<ShadowEntity> {
	public static final ModelLayerLocation LAYER_LOCATION
			= new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, "shadow"), "main");
	private final ModelPart FULL;
	private final ModelPart LEG_L;
	private final ModelPart HEAD;
	private final ModelPart BODY;
	private final ModelPart ARM_L;
	private final ModelPart ARM_R;
	private final ModelPart LEG_R;
	private RenderToBufferFunction renderToBufferFunction;

	public Shadow(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.LEG_L = this.FULL.getChild("LEG_L");
		this.HEAD = this.FULL.getChild("HEAD");
		this.BODY = this.FULL.getChild("BODY");
		this.ARM_L = this.FULL.getChild("ARM_L");
		this.ARM_R = this.FULL.getChild("ARM_R");
		this.LEG_R = this.FULL.getChild("LEG_R");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(-1.0F, 24.0F, 0.0F));
		PartDefinition LEG_L = FULL.addOrReplaceChild("LEG_L", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -12.0F, 1.0F));
		PartDefinition HEAD = FULL.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -24.0F, 1.0F));
		PartDefinition BODY = FULL.addOrReplaceChild("BODY", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -18.0F, 1.0F));
		PartDefinition ARM_L = FULL.addOrReplaceChild("ARM_L", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 1.0F));
		PartDefinition ARM_R = FULL.addOrReplaceChild("ARM_R", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -22.0F, 1.0F));
		PartDefinition LEG_R = FULL.addOrReplaceChild("LEG_R", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -12.0F, 1.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay,
							   float red, float green, float blue, float alpha) {
		if (this.renderToBufferFunction == null) {
			this.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		} else {
			this.renderToBufferFunction
					.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}

	@Override
	public @NotNull ModelPart root() {
		return this.FULL;
	}

	@Override
	public void setupAnim(@NotNull ShadowEntity entity, float limbSwing,
						  float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		if (entity.getSetUpAnimFunction() != null){
			entity.getSetUpAnimFunction().setupAnim(entity.getDeadEntity(), limbSwing, limbSwingAmount,
					ageInTicks, netHeadYaw, headPitch);
		}
		this.renderToBufferFunction = entity.getRenderToBufferFunction(); // Synced the renderer with the entity
	}

	@Override
	public void prepareMobModel(@NotNull ShadowEntity shadow, float p_102615_,
								float p_102616_, float p_102617_) {
		super.prepareMobModel(shadow, p_102615_, p_102616_, p_102617_);
	}
}