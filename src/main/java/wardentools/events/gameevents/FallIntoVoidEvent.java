package wardentools.events.gameevents;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;
import wardentools.worldgen.portal.ModTeleporter;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class FallIntoVoidEvent {
	
	@SubscribeEvent
	public static void playerFallIntoVoid(LivingDamageEvent.Pre event) {
		if ((event.getEntity() instanceof Player)) {
			if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
				if (event.getEntity().level() instanceof ServerLevel serverlevel) {
	                MinecraftServer minecraftserver = serverlevel.getServer();
	                ResourceKey<Level> resourcekey = ModDimensions.ABYSS_LEVEL_KEY;
	                ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
	                if (portalDimension != null && !event.getEntity().isPassenger()) {
	                	event.getEntity().setPos(event.getEntity().blockPosition().getX(),
	                			250, event.getEntity().blockPosition().getX());
	                    event.getEntity().changeDimension(ModTeleporter
								.diveTo(portalDimension,
										event.getEntity().blockPosition().getCenter(), event.getEntity()));
	               }
	            }
	        }
		}
	}
}
