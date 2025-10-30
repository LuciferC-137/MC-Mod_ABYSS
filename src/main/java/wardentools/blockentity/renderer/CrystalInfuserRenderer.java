package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.CrystalInfuserBlockEntity;
import wardentools.items.ItemRegistry;

@OnlyIn(Dist.CLIENT)
public class CrystalInfuserRenderer implements BlockEntityRenderer<CrystalInfuserBlockEntity> {

    private static final float scale = 0.35F;

    private final BlockEntityRendererProvider.Context context;

	public CrystalInfuserRenderer(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
	}
	
	@Override
    public void render(@NotNull CrystalInfuserBlockEntity blockEntity,
                       float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        for (int i = 0; i < 5; i++) {
            if (!blockEntity.getInventory().getStackInSlot(i).isEmpty()) {
                ItemStack itemStack = blockEntity.getInventory().getStackInSlot(i);
                Vec3 position;
                Vec3 rotation;
                float rotSup = itemStack.is(Items.AMETHYST_SHARD) ? -20.25F : 0F;
                rotSup += itemStack.is(Items.ECHO_SHARD) ? 45F : 0F;
                rotSup += itemStack.is(ItemRegistry.PALE_SHARD.get()) ? 45F : 0F;
                if (i == 4) {
                    float resonatorRot = blockEntity.getLevel() != null ?
                            (blockEntity.getLevel().getGameTime() + partialTick) * 0.8F : 0;
                    position = new Vec3(8F, 16F, 8F);
                    rotation = new Vec3(0F, resonatorRot, 0F);
                } else if (i == 1) {
                    position = new Vec3(5.5F, 10F + scale, 5.5F);
                    rotation = new Vec3(90F, -135F + rotSup, 0F);
                } else if (i == 2) {
                    position = new Vec3(10.5F, 10F + scale, 5.5F);
                    rotation = new Vec3(90F, 135F + rotSup, 0F);
                } else if (i == 3) {
                    position = new Vec3(5.5F, 10F + scale, 10.5F);
                    rotation = new Vec3(90F, -45F + rotSup, 0F);
                } else {
                    position = new Vec3(10.5F, 10F + scale, 10.5F);
                    rotation = new Vec3(90F, 45F + rotSup, 0F);
                }
                renderItemAtPosition(itemStack, poseStack, buffer,
                        blockEntity.getLevel(), position, rotation, packedLight, packedOverlay);
            }
        }
    }

    public void renderItemAtPosition(ItemStack stack,
                                     PoseStack poseStack, MultiBufferSource buffer,
                                     Level level, Vec3 position, Vec3 rotation, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(position.x / 16F,
                position.y / 16F, position.z / 16F);
        poseStack.mulPose(Axis.YP.rotationDegrees((float)rotation.y));
        poseStack.mulPose(Axis.XP.rotationDegrees((float)rotation.x));
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)rotation.z));
        poseStack.scale(scale, scale, scale);

        this.context.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                packedLight,
                packedOverlay,
                poseStack,
                buffer,
                level,
                0
        );

        poseStack.popPose();
    }
}
