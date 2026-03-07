package wardentools.weather;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandAbyssWeather {

    public CommandAbyssWeather(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("abyssweather")
            .then(Commands.literal("clear")
                .executes(context -> clear(context.getSource())))
            .then(Commands.literal("storm")
                .executes(context -> storm(context.getSource(), 0))
                .then(Commands.argument("duration", IntegerArgumentType.integer(0))
                    .executes(context -> storm(context.getSource(), IntegerArgumentType.getInteger(context, "duration")))))
            .then(Commands.literal("refraction")
                .executes(context -> refraction(context.getSource(), 0))
                .then(Commands.argument("duration", IntegerArgumentType.integer(0))
                    .executes(context -> refraction(context.getSource(), IntegerArgumentType.getInteger(context, "duration"))))
        ));
    }

    public int clear(CommandSourceStack source) {
        AbyssWeatherEventServer.WEATHER_MANAGER.weatherClear(source.getLevel());
        return Command.SINGLE_SUCCESS;
    }

    public int storm(CommandSourceStack source, int duration) {
        AbyssWeatherEventServer.WEATHER_MANAGER.forceStorm(source.getLevel(), duration);
        return Command.SINGLE_SUCCESS;
    }

    public int refraction(CommandSourceStack source, int duration) {
        AbyssWeatherEventServer.WEATHER_MANAGER.forceCelestialRefraction(source.getLevel(), duration);
        return Command.SINGLE_SUCCESS;
    }
}
