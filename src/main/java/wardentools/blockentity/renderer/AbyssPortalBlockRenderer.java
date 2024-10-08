package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix4f;
import wardentools.ModMain;
import wardentools.blockentity.AbyssPortalBlockEntity;

import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class AbyssPortalBlockRenderer implements BlockEntityRenderer<AbyssPortalBlockEntity> {
    // TEXTURES
    public static final ResourceLocation ABYSS_ENV_LOCATION
            = new ResourceLocation(ModMain.MOD_ID,"textures/misc/abyss_portal_env.png");
    public static final ResourceLocation ABYSS_PORTAL_LOCATION
            = new ResourceLocation(ModMain.MOD_ID,"textures/misc/abyss_portal.png");

    // SHADER
    public static final ResourceLocation shaderLocation
            = new ResourceLocation(ModMain.MOD_ID, "rendertype_abyss_portal");
    public static final ShaderInstance shader;
    static {
        try {
            shader = new ShaderInstance(Minecraft.getInstance().getResourceManager(),
                    shaderLocation, DefaultVertexFormat.POSITION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_SHADER
            = new RenderStateShard.ShaderStateShard(() -> shader);

    // RENDER TYPE
    private static final RenderType ABYSS_PORTAL
            = RenderType.create("abyss_portal", DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS, 1536, false,
            false,RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_SHADER)
                    .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                            .add(ABYSS_ENV_LOCATION,
                                    false, false)
                            .add(ABYSS_PORTAL_LOCATION,
                                    false, false).build())
                    .createCompositeState(false));


    public AbyssPortalBlockRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(AbyssPortalBlockEntity entity, float f1, PoseStack poseStack, MultiBufferSource buffer, int i1, int i2) {
        Matrix4f matrix4f = poseStack.last().pose();
        this.renderCube(entity, matrix4f, buffer.getBuffer(this.renderType()));
    }

    private void renderCube(AbyssPortalBlockEntity entity, Matrix4f matrix4f, VertexConsumer consumer) {
        float f = this.getOffsetDown();
        float f1 = this.getOffsetUp();
        this.renderFace(entity, matrix4f, consumer, 0.0F, 1.0F, 0.0F, 1.0F,
                1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderFace(entity, matrix4f, consumer, 0.0F, 1.0F, 1.0F, 0.0F,
                0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderFace(entity, matrix4f, consumer, 1.0F, 1.0F, 1.0F, 0.0F,
                0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderFace(entity, matrix4f, consumer, 0.0F, 0.0F, 0.0F, 1.0F,
                0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderFace(entity, matrix4f, consumer, 0.0F, 1.0F, f, f,
                0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderFace(entity, matrix4f, consumer, 0.0F, 1.0F, f1, f1,
                1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderFace(AbyssPortalBlockEntity blockEntity, Matrix4f matrix4f, VertexConsumer consumer,
                            float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4,
                            Direction direction) {
        if (blockEntity.shouldRenderFace(direction)) {
            consumer.vertex(matrix4f, x1, y1, z1).endVertex();
            consumer.vertex(matrix4f, x2, y1, z2).endVertex();
            consumer.vertex(matrix4f, x2, y2, z3).endVertex();
            consumer.vertex(matrix4f, x1, y2, z4).endVertex();
        }

    }

    protected float getOffsetUp() {
        return 1.0F;
    }

    protected float getOffsetDown() {
        return 0.0F;
    }

    protected RenderType renderType() {
        return ABYSS_PORTAL;
    }

}
