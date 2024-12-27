package wardentools.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import wardentools.blockentity.SoulSpawnerBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ShadowEntity;
import wardentools.items.ItemRegistry;

public class SoulSpawnerRenderer implements BlockEntityRenderer<SoulSpawnerBlockEntity> {
	private final BlockEntityRendererProvider.Context context;

	public SoulSpawnerRenderer(BlockEntityRendererProvider.Context ctx) {
		this.context = ctx;
	}

	@Override
	public void render(@NotNull SoulSpawnerBlockEntity soulSpawner,
					   float partialTick, @NotNull PoseStack poseStack,
					   @NotNull MultiBufferSource buffer, int packLight, int packedOverlay) {
		if (soulSpawner.getLevel() == null) return;
		if (soulSpawner.getShadowEntity() == null) {
			ShadowEntity shadowEntity = ModEntities.SHADOW.get().create(soulSpawner.getLevel());
			if (shadowEntity != null) {
				shadowEntity.moveTo(soulSpawner.getBlockPos(), 0.0F, 0.0F);
				soulSpawner.setShadowEntity(shadowEntity);
			}
		}
		ShadowEntity entity = soulSpawner.getShadowEntity();
		if (entity != null) {
			poseStack.pushPose();
			float time = soulSpawner.getLevel().getGameTime() + partialTick;
			float tiltX = (float) Math.sin(time / 10.0) * 30.0F;
			float tiltZ = (float) Math.cos(time / 10.0) * 30.0F;
			float rotY = (time * 31.415F) % 360.0F;
			poseStack.translate(0.5, 0.2, 0.5);
			poseStack.mulPose(Axis.YP.rotationDegrees(rotY));
			poseStack.mulPose(Axis.XP.rotationDegrees(tiltX));
			poseStack.mulPose(Axis.ZP.rotationDegrees(tiltZ));
			float scale = 0.35F;
			poseStack.scale(scale, scale, scale);
			Minecraft.getInstance().getEntityRenderDispatcher().render(
					entity,
					0.0, 0.0, 0.0,
					0.0F,
					partialTick,
					poseStack,
					buffer,
					packLight
			);
			poseStack.popPose();


		}
	}

}
