package wardentools.weather;


import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.ParsedCommandNode;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

import java.util.List;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssWeatherEvent {
	public static final AbyssWeatherManager WEATHER_MANAGER = new AbyssWeatherManager();
	
	@SubscribeEvent
	public static void onLevelTickEvent(TickEvent.LevelTickEvent event) {
		if (event.level.dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
		WEATHER_MANAGER.tick(event.level);
	}

	@SubscribeEvent
	public static void onWeatherCommand(CommandEvent event) {
		CommandContextBuilder<?> context = event.getParseResults().getContext();
		String command = context.getNodes().get(0).getNode().getName();
        if (command.equals("weather")) {
            handleWeatherCommand(context, event.getParseResults().getContext().getSource().getLevel());
        }
	}

	private static void handleWeatherCommand(CommandContextBuilder<?> context, Level level) {
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
