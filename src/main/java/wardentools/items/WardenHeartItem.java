package wardentools.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import wardentools.armors.DeepCristalMaterial;
import wardentools.effects.WardenLaserAttack;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.WardenLaserParticleAndSoundPacket;


public class WardenHeartItem extends Item {
	
	private final WardenLaserAttack laserAttack;
	private static int laserLength = 14;

	public WardenHeartItem(Properties properties) {
		super(properties);
		this.laserAttack = new WardenLaserAttack();
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		ItemStack armorStack = player.getInventory().getArmor(2);
		if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
			if (!armorStack.isEmpty()){
				ArmorItem armorItem = (ArmorItem) armorStack.getItem();
				ArmorMaterial armorMaterial = armorItem.getMaterial();
				if (armorMaterial instanceof DeepCristalMaterial) {
					player.getCooldowns().addCooldown(this, 120);

			            long gameTime = serverLevel.getGameTime();
			            Vec3 startPosition = player.position().add(0.0D, 1.0F, 0.0D);
			            Vec3 direction = player.getLookAngle();
			            //Déclenche le laser
			            laserAttack.tick(serverLevel, player, gameTime, startPosition, direction, laserLength);
			            //Envoie les packets pour les effets sonores et visuels
			            PacketHandler.sendToAllClient(
			            		new WardenLaserParticleAndSoundPacket(startPosition, direction, laserLength));
			    }
			}
		}

	    return InteractionResultHolder.success(player.getItemInHand(interactionHand));

	}
	
	@Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
