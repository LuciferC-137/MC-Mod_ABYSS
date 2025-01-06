package wardentools.weather;


import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssFogEvent {

	@SubscribeEvent
	public static void onRenderFog(ViewportEvent.RenderFog event) {
		if (event.getCamera().getEntity() instanceof Player player) {
			if (player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				event.setFarPlaneDistance(AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance());
				event.setNearPlaneDistance(AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance() * 0.5f);
				event.setCanceled(true);
			}
		}
	}
}
