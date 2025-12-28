package wardentools.entity.thryssaryn.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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
import wardentools.ModMain;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

@OnlyIn(Dist.CLIENT)
public class Thryssaryn extends HierarchicalModel<ThryssarynEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "thryssaryn"), "main");
	private final ModelPart FULL;
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart neck_head;
	private final ModelPart head_main;
	private final ModelPart mandible_r;
	private final ModelPart mandible_tip_r;
	private final ModelPart mandible_l;
	private final ModelPart mandible_tip_l;
	private final ModelPart neck_base;
	private final ModelPart body;
	private final ModelPart thorax;
	private final ModelPart leg_bl;
	private final ModelPart end_bl;
	private final ModelPart leg_br;
	private final ModelPart end_br;
	private final ModelPart leg_mr;
	private final ModelPart end_mr;
	private final ModelPart leg_ml;
	private final ModelPart end_ml;
	private final ModelPart leg_fl;
	private final ModelPart end_fl;
	private final ModelPart leg_fr;
	private final ModelPart end_fr;
	private final ModelPart abdomen;

	public Thryssaryn(ModelPart root) {
		this.FULL = root.getChild("FULL");
		this.head = this.FULL.getChild("head");
		this.neck = this.head.getChild("neck");
		this.neck_head = this.neck.getChild("neck_head");
		this.head_main = this.neck_head.getChild("head_main");
		this.mandible_r = this.head_main.getChild("mandible_r");
		this.mandible_tip_r = this.mandible_r.getChild("mandible_tip_r");
		this.mandible_l = this.head_main.getChild("mandible_l");
		this.mandible_tip_l = this.mandible_l.getChild("mandible_tip_l");
		this.neck_base = this.neck.getChild("neck_base");
		this.body = this.FULL.getChild("body");
		this.thorax = this.body.getChild("thorax");
		this.leg_bl = this.thorax.getChild("leg_bl");
		this.end_bl = this.leg_bl.getChild("end_bl");
		this.leg_br = this.thorax.getChild("leg_br");
		this.end_br = this.leg_br.getChild("end_br");
		this.leg_mr = this.thorax.getChild("leg_mr");
		this.end_mr = this.leg_mr.getChild("end_mr");
		this.leg_ml = this.thorax.getChild("leg_ml");
		this.end_ml = this.leg_ml.getChild("end_ml");
		this.leg_fl = this.thorax.getChild("leg_fl");
		this.end_fl = this.leg_fl.getChild("end_fl");
		this.leg_fr = this.thorax.getChild("leg_fr");
		this.end_fr = this.leg_fr.getChild("end_fr");
		this.abdomen = this.body.getChild("abdomen");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FULL = partdefinition.addOrReplaceChild("FULL", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition head = FULL.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, -3.0F));
		PartDefinition neck = head.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.5F, -0.45F, -0.5F));
		PartDefinition neck_head = neck.addOrReplaceChild("neck_head", CubeListBuilder.create(), PartPose.offset(-0.45F, -4.0218F, -1.3796F));
		PartDefinition cube_r1 = neck_head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 92).addBox(-2.0F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -1.0F, -1.75F, 1.3675F, -0.1703F, -0.7162F));
		PartDefinition head_main = neck_head.addOrReplaceChild("head_main", CubeListBuilder.create(), PartPose.offset(-1.15F, -0.8782F, -3.7204F));
		PartDefinition cube_r2 = head_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(78, 26).addBox(-2.9233F, -0.9669F, 0.1594F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5233F, 2.0169F, -8.0594F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r3 = head_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 52).addBox(-2.9166F, -1.3978F, 0.1515F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5233F, 2.6169F, -8.0594F, 0.4758F, 0.0973F, -0.009F));
		PartDefinition cube_r4 = head_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 68).addBox(-2.0834F, -1.3978F, 0.1515F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3233F, 2.6169F, -8.0594F, 0.4758F, -0.0973F, 0.009F));
		PartDefinition cube_r5 = head_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 68).addBox(-1.0F, -3.0F, -7.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, 2.35F, -0.5F, 0.2679F, 0.2106F, 0.0573F));
		PartDefinition cube_r6 = head_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 68).addBox(-4.0F, -3.0F, -7.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.25F, -0.5F, 0.2679F, -0.2106F, -0.0573F));
		PartDefinition mandible_r = head_main.addOrReplaceChild("mandible_r", CubeListBuilder.create(), PartPose.offset(-1.4F, 2.85F, -6.9F));
		PartDefinition cube_r7 = mandible_r.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(12, 92).addBox(-0.5F, -0.5F, -3.9F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.1F, -0.5F, 0.2618F, 0.3491F, 0.0F));
		PartDefinition mandible_tip_r = mandible_r.addOrReplaceChild("mandible_tip_r", CubeListBuilder.create(), PartPose.offset(-0.7F, 1.2F, -4.0F));
		PartDefinition cube_r8 = mandible_tip_r.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 56).addBox(-0.5F, -0.5F, -1.9F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 0.0F, -0.6F, 0.2949F, -0.5792F, -0.2561F));
		PartDefinition mandible_l = head_main.addOrReplaceChild("mandible_l", CubeListBuilder.create(), PartPose.offset(3.6F, 2.85F, -6.9F));
		PartDefinition cube_r9 = mandible_l.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(24, 92).addBox(-0.5F, -0.5F, -3.9F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.1F, -0.5F, 0.2618F, -0.3491F, 0.0F));
		PartDefinition mandible_tip_l = mandible_l.addOrReplaceChild("mandible_tip_l", CubeListBuilder.create(), PartPose.offset(0.7F, 1.2F, -4.0F));
		PartDefinition cube_r10 = mandible_tip_l.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 60).addBox(-0.5F, -0.5F, -1.9F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 0.0F, -0.6F, 0.2949F, 0.5792F, 0.2561F));
		PartDefinition neck_base = neck.addOrReplaceChild("neck_base", CubeListBuilder.create(), PartPose.offset(0.0F, 1.25F, -1.0F));
		PartDefinition cube_r11 = neck_base.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(80, 90).addBox(-2.0F, -6.0F, 0.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));
		PartDefinition body = FULL.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 97).addBox(-1.0F, 8.0F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.0F, -3.0F));
		PartDefinition thorax = body.addOrReplaceChild("thorax", CubeListBuilder.create(), PartPose.offset(0.2F, 16.2742F, 9.175F));
		PartDefinition cube_r12 = thorax.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(40, 52).addBox(-4.0F, -3.3905F, -3.5604F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -12.8F, -8.2F, -1.0675F, 0.1509F, -0.2666F));
		PartDefinition cube_r13 = thorax.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 56).addBox(-3.0F, -1.0F, -5.1507F, 6.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -2.082F, -4.2371F, -0.9861F, 0.0F, 0.0F));
		PartDefinition cube_r14 = thorax.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(78, 16).addBox(-3.0F, -1.0F, -4.3493F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -9.6251F, -8.33F, -1.1606F, 0.0F, 0.0F));
		PartDefinition cube_r15 = thorax.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(48, 92).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, -9.1228F, -2.5765F, -1.169F, 0.3728F, -0.7086F));
		PartDefinition cube_r16 = thorax.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(36, 92).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8F, -9.1228F, -2.5765F, -1.169F, -0.3728F, 0.7086F));
		PartDefinition cube_r17 = thorax.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -8.5F, 6.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -9.4229F, -2.4765F, -1.0297F, 0.0F, 0.0F));
		PartDefinition cube_r18 = thorax.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(40, 35).addBox(-4.5F, -3.5F, -9.5F, 7.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, 0.5258F, -0.675F, -1.0509F, -0.0653F, 0.1135F));
		PartDefinition cube_r19 = thorax.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(40, 18).addBox(-2.5F, -3.5F, -9.5F, 7.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 0.5258F, -0.675F, -1.0509F, 0.0653F, -0.1135F));
		PartDefinition cube_r20 = thorax.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -3.3905F, -3.5604F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, -12.7F, -8.2F, -1.0675F, -0.1509F, 0.2666F));
		PartDefinition leg_bl = thorax.addOrReplaceChild("leg_bl", CubeListBuilder.create(), PartPose.offset(5.3F, -1.9742F, -1.175F));
		PartDefinition cube_r21 = leg_bl.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(78, 38).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 0.1F, -0.2F, -0.0306F, -0.1719F, 0.1772F));
		PartDefinition cube_r22 = leg_bl.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(76, 64).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, -0.7F, -0.1F, -0.0456F, -0.1685F, 0.2657F));
		PartDefinition end_bl = leg_bl.addOrReplaceChild("end_bl", CubeListBuilder.create(), PartPose.offset(9.45F, 2.05F, 1.6F));
		PartDefinition cube_r23 = end_bl.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0507F, 0.05F, -0.3013F, 0.5997F, -0.3293F, 1.353F));
		PartDefinition cube_r24 = end_bl.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(78, 50).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 2.55F, 1.0F, 0.0038F, 0.0612F, 1.5276F));
		PartDefinition cube_r25 = end_bl.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(44, 16).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 2.55F, -0.1F, 0.0038F, -0.0872F, 1.527F));
		PartDefinition cube_r26 = end_bl.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(80, 84).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.05F, 2.55F, 0.5F, 0.0F, 0.0F, 1.5708F));
		PartDefinition cube_r27 = end_bl.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(84, 78).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 2.55F, 0.5F, 0.0F, 0.0F, 1.4835F));
		PartDefinition leg_br = thorax.addOrReplaceChild("leg_br", CubeListBuilder.create(), PartPose.offset(-5.7F, -1.9742F, -1.175F));
		PartDefinition cube_r28 = leg_br.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(78, 46).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 0.1F, -0.2F, -0.0306F, 0.1719F, -0.1772F));
		PartDefinition cube_r29 = leg_br.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(78, 42).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -0.7F, -0.1F, -0.0456F, 0.1685F, -0.2657F));
		PartDefinition end_br = leg_br.addOrReplaceChild("end_br", CubeListBuilder.create(), PartPose.offset(-9.7F, 2.05F, 1.35F));
		PartDefinition cube_r30 = end_br.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(92, 90).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1993F, 0.05F, -0.0513F, 0.5997F, 0.3293F, -1.353F));
		PartDefinition cube_r31 = end_br.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(40, 84).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 2.55F, 1.25F, 0.0038F, -0.0612F, -1.5276F));
		PartDefinition cube_r32 = end_br.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(20, 84).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 2.55F, 0.15F, 0.0038F, 0.0872F, -1.527F));
		PartDefinition cube_r33 = end_br.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(40, 86).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 2.55F, 0.75F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r34 = end_br.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(20, 86).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.55F, 0.75F, 0.0F, 0.0F, -1.4835F));
		PartDefinition leg_mr = thorax.addOrReplaceChild("leg_mr", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.75F, -9.0742F, -4.775F, 0.0547F, -0.3006F, -0.7064F));
		PartDefinition cube_r35 = leg_mr.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(80, 0).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6749F, 0.7782F, 0.2208F, -0.0306F, 0.1719F, -0.1772F));
		PartDefinition cube_r36 = leg_mr.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 80).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6749F, -0.0218F, 0.3208F, -0.0456F, 0.1685F, -0.2657F));
		PartDefinition end_mr = leg_mr.addOrReplaceChild("end_mr", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.6749F, 2.9282F, 1.8208F, 0.0F, 0.0F, 0.6283F));
		PartDefinition cube_r37 = end_mr.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(44, 97).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1181F, 0.0389F, -0.1013F, 0.5997F, 0.3293F, -1.353F));
		PartDefinition cube_r38 = end_mr.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(84, 68).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4812F, 2.5389F, 1.2F, 0.0038F, -0.0612F, -1.5276F));
		PartDefinition cube_r39 = end_mr.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(60, 84).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4812F, 2.5389F, 0.1F, 0.0038F, 0.0872F, -1.527F));
		PartDefinition cube_r40 = end_mr.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(60, 86).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8812F, 2.5389F, 0.7F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r41 = end_mr.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 90).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0812F, 2.5389F, 0.7F, 0.0F, 0.0F, -1.4835F));
		PartDefinition leg_ml = thorax.addOrReplaceChild("leg_ml", CubeListBuilder.create(), PartPose.offsetAndRotation(4.35F, -9.0742F, -4.775F, 0.0547F, 0.3006F, 0.7064F));
		PartDefinition cube_r42 = leg_ml.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(80, 8).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6749F, 0.7782F, 0.2208F, -0.0306F, -0.1719F, 0.1772F));
		PartDefinition cube_r43 = leg_ml.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(80, 4).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6749F, -0.0218F, 0.3208F, -0.0456F, -0.1685F, 0.2657F));
		PartDefinition end_ml = leg_ml.addOrReplaceChild("end_ml", CubeListBuilder.create(), PartPose.offsetAndRotation(10.6249F, 3.0282F, 1.8708F, 0.0F, 0.0F, -0.6283F));
		PartDefinition cube_r44 = end_ml.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(92, 94).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0189F, -0.0127F, -0.1513F, 0.5997F, -0.3293F, 1.353F));
		PartDefinition cube_r45 = end_ml.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(40, 90).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5805F, 2.4873F, 1.15F, 0.0038F, 0.0612F, 1.5276F));
		PartDefinition cube_r46 = end_ml.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(20, 90).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5805F, 2.4873F, 0.05F, 0.0038F, -0.0872F, 1.527F));
		PartDefinition cube_r47 = end_ml.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(84, 70).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9805F, 2.4873F, 0.65F, 0.0F, 0.0F, 1.5708F));
		PartDefinition cube_r48 = end_ml.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(80, 86).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1805F, 2.4873F, 0.65F, 0.0F, 0.0F, 1.4835F));
		PartDefinition leg_fl = thorax.addOrReplaceChild("leg_fl", CubeListBuilder.create(), PartPose.offsetAndRotation(4.3F, -13.5742F, -8.475F, 0.5612F, 0.4554F, 1.0287F));
		PartDefinition cube_r49 = leg_fl.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(26, 80).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6393F, 0.6254F, 0.3024F, -0.0306F, -0.1719F, 0.1772F));
		PartDefinition cube_r50 = leg_fl.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(60, 92).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2607F, -0.1746F, 0.1024F, 0.0602F, -0.1639F, -0.354F));
		PartDefinition cube_r51 = leg_fl.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(80, 12).addBox(-2.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6393F, -0.1746F, 0.4024F, -0.0456F, -0.1685F, 0.2657F));
		PartDefinition end_fl = leg_fl.addOrReplaceChild("end_fl", CubeListBuilder.create(), PartPose.offsetAndRotation(10.6393F, 2.8254F, 1.9024F, 0.0F, 0.0F, -0.7592F));
		PartDefinition cube_r52 = end_fl.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(60, 96).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1808F, 0.0069F, -0.1013F, 0.5997F, -0.3293F, 1.353F));
		PartDefinition cube_r53 = end_fl.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(40, 88).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4185F, 2.5069F, 1.2F, 0.0038F, 0.0612F, 1.5276F));
		PartDefinition cube_r54 = end_fl.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(20, 88).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4185F, 2.5069F, 0.1F, 0.0038F, -0.0872F, 1.527F));
		PartDefinition cube_r55 = end_fl.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(60, 90).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8185F, 2.5069F, 0.7F, 0.0F, 0.0F, 1.5708F));
		PartDefinition cube_r56 = end_fl.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(84, 72).addBox(-2.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0185F, 2.5069F, 0.7F, 0.0F, 0.0F, 1.4835F));
		PartDefinition leg_fr = thorax.addOrReplaceChild("leg_fr", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.7F, -13.5742F, -8.475F, 0.5612F, -0.4554F, -0.9414F));
		PartDefinition cube_r57 = leg_fr.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(70, 92).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2607F, -0.1746F, 0.1024F, 0.0602F, 0.1639F, 0.354F));
		PartDefinition cube_r58 = leg_fr.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(52, 80).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6393F, -0.1746F, 0.4024F, -0.0456F, 0.1685F, -0.2657F));
		PartDefinition cube_r59 = leg_fr.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(78, 80).addBox(-9.0F, -1.0F, -1.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6393F, 0.6254F, 0.3024F, -0.0306F, 0.1719F, -0.1772F));
		PartDefinition end_fr = leg_fr.addOrReplaceChild("end_fr", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.4893F, 2.7254F, 2.1024F, 0.0F, 0.0F, 0.7592F));
		PartDefinition cube_r60 = end_fr.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(68, 96).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1409F, 0.1827F, -0.3013F, 0.5997F, 0.3293F, -1.353F));
		PartDefinition cube_r61 = end_fr.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(84, 76).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4585F, 2.6827F, 1.0F, 0.0038F, -0.0612F, -1.5276F));
		PartDefinition cube_r62 = end_fr.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(84, 74).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4585F, 2.6827F, -0.1F, 0.0038F, 0.0872F, -1.527F));
		PartDefinition cube_r63 = end_fr.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(80, 88).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8585F, 2.6827F, 0.5F, 0.0F, 0.0F, -1.5708F));
		PartDefinition cube_r64 = end_fr.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(60, 88).addBox(-7.0F, 0.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0585F, 2.6827F, 0.5F, 0.0F, 0.0F, -1.4835F));
		PartDefinition abdomen = body.addOrReplaceChild("abdomen", CubeListBuilder.create(), PartPose.offset(0.0F, 15.7F, 9.4F));
		PartDefinition cube_r65 = abdomen.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(0, 84).addBox(-4.0F, -3.5F, -1.0F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 10.75F, -0.3229F, 0.0F, 0.0F));
    	PartDefinition cube_r66 = abdomen.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(0, 37).addBox(-7.0F, -4.5F, 0.0F, 7.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.1F, -2.4F, -0.2998F, 0.1956F, -0.0592F));
		PartDefinition cube_r67 = abdomen.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -4.5F, 0.0F, 7.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.4F, -0.2998F, -0.1956F, 0.0592F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
							   int packedLight, int packedOverlay, int i) {
		this.FULL.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
	}

	public void applyLeftHandTransform(PoseStack pose) {
		this.FULL.translateAndRotate(pose);
		this.body.translateAndRotate(pose);
		this.thorax.translateAndRotate(pose);
		this.leg_fl.translateAndRotate(pose);
		this.end_fl.translateAndRotate(pose);

		pose.translate(0F, 10F / 16F, 0F);
	}

	public void applyBackTransform(PoseStack pose) {
		this.FULL.translateAndRotate(pose);
		this.body.translateAndRotate(pose);

		pose.mulPose(Axis.ZP.rotationDegrees(180F));
		pose.mulPose(Axis.XP.rotationDegrees(-38F));

		pose.translate(0F, -11.5F / 16F, 6F / 16F);
	}

	public void applyLuthPlayingPoseTransform(PoseStack pose) {

		pose.mulPose(Axis.YP.rotationDegrees(-10F));
		pose.mulPose(Axis.ZP.rotationDegrees(86F));

		pose.translate(14F / 16F, -2F / 16F, -6F / 16F);
	}

	public void applyLuthPositionTransform(ThryssarynEntity thryssaryn, PoseStack pose) {
		if (thryssaryn.isPlayingLuth()
				&& thryssaryn.thryssarynLuthSequence.currentIndex == 0
				&& thryssaryn.thryssarynLuthSequence.currentTick >= 11) {
			this.applyLeftHandTransform(pose);
		} else if (thryssaryn.isPlayingLuth()
				&& thryssaryn.thryssarynLuthSequence.currentIndex == 1) {
			this.applyLuthPlayingPoseTransform(pose);
		} else {
			this.applyBackTransform(pose);
		}
		pose.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.FULL;
	}

	@Override
	public void setupAnim(ThryssarynEntity thryssaryn, float limbSwing, float limbSwingAmount,
						 float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animateWalk(ThryssarynAnimation.walking, limbSwing, limbSwingAmount,
				1.0F, 1.0F);
		animate(thryssaryn.standing2playingLuth, ThryssarynAnimation.playing_luth_position, ageInTicks);
		animate(thryssaryn.playingLuthWarden, ThryssarynAnimation.playing_luth_warden, ageInTicks);
		animate(thryssaryn.playingLuth2standing, ThryssarynAnimation.playing_luth_position,
				ageInTicks, -1.0F);
	}

}