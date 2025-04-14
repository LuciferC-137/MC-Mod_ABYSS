package wardentools.weather;


import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.ParsedCommandNode;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

import java.util.List;

/**
 * This class register all the weather ticks and is in charge of server sync.
 */

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssWeatherEventServer {
	public static final AbyssWeatherManager WEATHER_MANAGER = new AbyssWeatherManager();

	@SubscribeEvent
	public static void onServerTickEvent(ServerTickEvent.Pre event) {
		ServerLevel abyssLevel = event.getServer().getLevel(ModDimensions.ABYSS_LEVEL_KEY);
		if (abyssLevel == null) return;
		WEATHER_MANAGER.tick(abyssLevel);
	}

	@SubscribeEvent
	public static void onWeatherCommand(CommandEvent event) {
		CommandContextBuilder<?> context = event.getParseResults().getContext();
		String command = context.getNodes().getFirst().getNode().getName();
        if (command.equals("weather")) {
            handleWeatherCommand(context, event.getParseResults().getContext().getSource().getLevel());
        }
	}

	private static void handleWeatherCommand(CommandContextBuilder<?> context, ServerLevel level) {
		List<? extends ParsedCommandNode<?>> nodes = context.getNodes();
		if (nodes.size() < 2) return;
		String subCommand = nodes.get(1).getNode().getName();
		int duration = parseDuration(context);
		if (duration == 0) {
			switch (subCommand) {
				case "clear":
					WEATHER_MANAGER.weatherClear(level);
					break;
				case "rain", "thunder":
					WEATHER_MANAGER.startNewEvent(level);
					break;
				default:
					break;
			}
		} else {
			switch (subCommand) {
				case "clear":
					WEATHER_MANAGER.weatherClear(level, duration);
					break;
				case "rain", "thunder":
					WEATHER_MANAGER.startNewEvent(level, duration);
					break;
				default:
					break;
			}
		}
	}

	private static int parseDuration(CommandContextBuilder<?> context) {
		try {
			ParsedArgument<?, ?> durationArg = context.getArguments().get("duration");
			if (durationArg != null) {
                return (int) (Integer) durationArg.getResult();
			}
		} catch (Exception ignored) {
		}
		return 0;
	}
}
