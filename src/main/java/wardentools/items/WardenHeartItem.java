package wardentools.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;
import wardentools.items.clientutils.WardenHeartFoilManager;
import wardentools.misc.WardenLaserAttack;
import wardentools.network.payloads.special_effects.WardenLaserParticleSound;

public class WardenHeartItem extends Item {
	
	private final WardenLaserAttack laserAttack;
	private static final int laserLength = 14;

	public WardenHeartItem(Properties properties) {
		super(properties);
		this.laserAttack = new WardenLaserAttack();
	}
	
	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player,
														   @NotNull InteractionHand interactionHand) {
		if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
				if (isCorruptionVessel(player))  {
					player.getCooldowns().addCooldown(this, 120);
					long gameTime = serverLevel.getGameTime();
					Vec3 startPosition = player.position().add(0.0D, 1.0F, 0.0D);
					Vec3 direction = player.getLookAngle();
					//Trigger laser
					laserAttack.tick(serverLevel, player, gameTime, startPosition, direction, laserLength);
					//Send packet for sound and visuals
					PacketDistributor.sendToPlayersTrackingChunk(
							serverLevel,
							serverLevel.getChunkAt(player.getOnPos()).getPos(),
							new WardenLaserParticleSound(startPosition.toVector3f(), direction.toVector3f(), laserLength));
					return InteractionResultHolder.success(player.getItemInHand(interactionHand));
			    }
		}
	    return InteractionResultHolder.fail(player.getItemInHand(interactionHand));
	}

	private static boolean isCorruptionVessel(Player player) {
		if (player==null) return false;
		return player.getEffect(ModEffects.CORRUPTION_VESSEL) != null;
	}
	
	@Override
    public boolean isFoil(@NotNull ItemStack stack) {
		return WardenHeartFoilManager.isFoil();
    }
}
