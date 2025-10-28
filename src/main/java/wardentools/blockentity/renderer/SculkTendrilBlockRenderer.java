package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import wardentools.ModMain;
import wardentools.blockentity.SculkTendrilBlockEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SculkTendrilBlockRenderer implements BlockEntityRenderer<SculkTendrilBlockEntity> {
    private static final ResourceLocation SCULK
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
            "textures/item/sculk_tendril_block.png");
    private static final Map<Direction, Quaternionf> DIRECTION_ROTATIONS = Map.of(
            Direction.NORTH, new Quaternionf(),
            Direction.SOUTH, new Quaternionf().rotateY((float)Math.PI),
            Direction.WEST, new Quaternionf().rotateY((float)Math.PI/2),
            Direction.EAST, new Quaternionf().rotateY(-(float)Math.PI/2),
            Direction.UP, new Quaternionf().rotateX(-(float)Math.PI/2),
            Direction.DOWN, new Quaternionf().rotateX((float)Math.PI/2)
    );


    private int cachedWidth = 0;
    private CubeModel cachedModel;
    private CubeModel cachedComplementaryModel;


    public SculkTendrilBlockRenderer(BlockEntityRendererProvider.Context ctx) {
    }


    @Override
    public void render(@NotNull SculkTendrilBlockEntity blockEntity, float v,
                       @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        if (level == null) return;
        BlockPos pos = blockEntity.getBlockPos();

        if (this.cachedModel == null || this.cachedComplementaryModel == null
                || this.cachedWidth != blockEntity.getWidth()) {
            this.cachedWidth = blockEntity.getWidth();
            this.createCubeModels(blockEntity);
        }

        this.renderMiddle(level, pos, poseStack, buffer, packedOverlay);

        for (Direction direction : Direction.values()) {
            if (!blockEntity.getConnection(direction)) continue;
            this.renderComplementaryInDirection(level, pos, direction, poseStack, buffer, packedOverlay);
        }
    }

    private void renderMiddle(Level level, BlockPos pos, PoseStack poseStack,
                              MultiBufferSource buffer, int packedOverlay) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(SCULK));
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);

        cachedModel.render(poseStack, vertexConsumer, LightTexture.pack(
                level.getBrightness(LightLayer.BLOCK, pos),
                level.getBrightness(LightLayer.SKY, pos)), packedOverlay);
        poseStack.popPose();
    }

    private void renderComplementaryInDirection(Level level, BlockPos pos, Direction direction,
                                                PoseStack poseStack, MultiBufferSource buffer,
                                                int packedOverlay) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(SCULK));
        poseStack.pushPose();

        poseStack.translate(0.5F, 0.5F, 0.5F);

        float centralHalfSize = (float)this.cachedWidth / 32F;
        int branchLength = 8 - this.cachedWidth / 2;
        float branchHalfLength = (float)branchLength / 32F;
        float branchCenterOffset = centralHalfSize + branchHalfLength;

        poseStack.translate(
                direction.getStepX() * branchCenterOffset,
                direction.getStepY() * branchCenterOffset,
                direction.getStepZ() * branchCenterOffset
        );
        poseStack.mulPose(DIRECTION_ROTATIONS.get(direction));

        cachedComplementaryModel.render(poseStack, vertexConsumer, LightTexture.pack(
                level.getBrightness(LightLayer.BLOCK, pos),
                level.getBrightness(LightLayer.SKY, pos)), packedOverlay);
        poseStack.popPose();
    }

    private void createCubeModels(SculkTendrilBlockEntity blockEntity) {
        this.cachedWidth = blockEntity.getWidth();
        if (cachedWidth == 0) return;
        this.cachedModel = new CubeModel(CubeModel
                .createBodyLayer(this.cachedWidth, this.cachedWidth, this.cachedWidth).bakeRoot());
        int branchLength = 8 - this.cachedWidth / 2;
        this.cachedComplementaryModel = new CubeModel(CubeModel
                .createBodyLayer(this.cachedWidth, this.cachedWidth, branchLength).bakeRoot());
    }


    private static class CubeModel extends Model {
        private final ModelPart root;

        public CubeModel(ModelPart parts) {
            super(RenderType::entitySolid);
            this.root = parts;
        }

        public static LayerDefinition createBodyLayer(int x, int y, int z) {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();
            partdefinition.addOrReplaceChild("cube",
                    CubeListBuilder.create().texOffs(-8, -8)
                            .addBox(-(float)x / 2f, -(float)y / 2f, -(float)z / 2f,
                                    (float)x, (float)y, (float)z),
                    PartPose.offset(0.0F, 0.0F, 0.0F));
            return LayerDefinition.create(meshdefinition, 16, 16);
        }

        @Override
        public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
                                   int packedLight, int packedOverlay, int i) {
            root.render(poseStack, vertexConsumer, packedLight, packedOverlay, i);
        }

        public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    @Override
    public boolean shouldRender(@NotNull SculkTendrilBlockEntity tendrilBlockEntity, @NotNull Vec3 cameraPos) {
        BlockPos pos = tendrilBlockEntity.getBlockPos();
        double dx = pos.getX() - cameraPos.x;
        double dy = pos.getY() - cameraPos.y;
        double dz = pos.getZ() - cameraPos.z;
        // Use full block bounds (1x1x1) instead of the hitbox dimensions
        return dx * dx + dy * dy + dz * dz < 64.0D * 64.0D;
    }
}
