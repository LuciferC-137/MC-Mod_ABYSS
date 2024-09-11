package wardentools.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.items.armors.DeepCristalMaterial;
import wardentools.misc.WardenLaserAttack;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.WardenLaserParticleAndSoundPacket;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class WardenHeartItem extends Item {
	
	private final WardenLaserAttack laserAttack;
	private static int laserLength = 14;
	private Player currentPlayer;

	public WardenHeartItem(Properties properties) {
		super(properties);
		this.laserAttack = new WardenLaserAttack();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
				if (wearDeepChestplate(player)) {
					player.getCooldowns().addCooldown(this, 120);
			            long gameTime = serverLevel.getGameTime();
			            Vec3 startPosition = player.position().add(0.0D, 1.0F, 0.0D);
			            Vec3 direction = player.getLookAngle();
			            //Trigger laser
			            laserAttack.tick(serverLevel, player, gameTime, startPosition, direction, laserLength);
			            //Send packet for sound and visuals
			            PacketHandler.sendToAllClient(
			            		new WardenLaserParticleAndSoundPacket(startPosition, direction, laserLength));
			    }
		}
	    return InteractionResultHolder.success(player.getItemInHand(interactionHand));
	}
	
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		this.currentPlayer = event.player;
    }
	
	private static boolean wearDeepChestplate(Player player) {
		if (player==null) {
			return false;
		}
		if (!player.getInventory().getArmor(2).isEmpty()) {
        	if (((ArmorItem) (player.getInventory().getArmor(2).getItem()))
        			.getMaterial() instanceof DeepCristalMaterial) {
        		return true;
        	} else {
            	return false;
            }
        } else {
        	return false;
        }
	}
	
	@Override
    public boolean isFoil(ItemStack stack) {
        return wearDeepChestplate(this.currentPlayer);
    }
}
