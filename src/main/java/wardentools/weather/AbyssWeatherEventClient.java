package wardentools.weather;


import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import wardentools.ModMain;

/**
 * This class register all the weather ticks and is in charge of client sync.
 */

@EventBusSubscriber(modid = ModMain.MOD_ID, value = Dist.CLIENT)
public class AbyssWeatherEventClient {
	public static final AbyssFogClientHandler CLIENT_WEATHER = new AbyssFogClientHandler();

	@SubscribeEvent
	public static void onClientLevelTickEvent(ClientTickEvent.Pre event) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.level == null || minecraft.player == null) return;
		CLIENT_WEATHER.updateFogDistanceOnTick(minecraft.level);
	}
}
