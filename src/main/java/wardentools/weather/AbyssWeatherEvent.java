package wardentools.weather;


import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssWeatherEvent {
	public static final AbyssWeatherManager WEATHER_MANAGER = new AbyssWeatherManager();
	
	@SubscribeEvent
	public static void playerFallIntoVoid(TickEvent.LevelTickEvent event) {
		if (event.level.dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
		WEATHER_MANAGER.tick(event.level);
	}
}
