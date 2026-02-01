package wardentools.weather;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public enum WeatherEvent implements StringRepresentable {
    CLEAR("clear", 24000, 3000, 0, 10),
    STORM("storm", 6000, 3000, 24000, 1),
    CELESTIAL_REFRACTION("celestial_refraction", 6000,
            6000, 48000, 7);

    private final String name;
    private final int maxDuration;
    private final int minDuration;
    private final int minIntervalWithPrevious;
    private final int weight;
    private int timeOfLast = 0;
    private Consumer<ServerLevel> onStart = event -> {};
    private Consumer<ServerLevel> onEnd = event -> {};
    private Consumer<ServerLevel> onTick = event -> {};

    WeatherEvent(String name, int maxDuration,
                 int minDuration,
                 int minIntervalWithPrevious,
                 int weight) {
        this.name = name;
        this.maxDuration = maxDuration;
        this.minDuration = minDuration;
        this.minIntervalWithPrevious = minIntervalWithPrevious;
        this.weight = weight;
    }

    public static WeatherEvent randomWeatherEvent(RandomSource random,
                                                  WeatherEvent previous,
                                                  int gameTime) {
        WeatherEvent[] events = WeatherEvent.values();
        int totalWeight = 0;
        WeatherEvent selectedEvent = CLEAR;

        for (WeatherEvent event : events) {
            if (event.isEligibleForSelection(gameTime)) {
                totalWeight += event.getWeight();
            }
        }

        int randomWeight = random.nextInt(totalWeight);

        for (WeatherEvent event : events) {
            if (event != CLEAR &&
                (event.isEligibleForSelection(gameTime))) {
                randomWeight -= event.getWeight();
                if (randomWeight < 0) {
                    selectedEvent = event;
                    break;
                }
            }
        }
        selectedEvent.timeOfLast = gameTime;
        return selectedEvent;
    }

    public int randomTickToNextEvent(RandomSource random) {
        return random.nextInt(this.getMinDuration(), this.getMaxDuration() + 1);
    }

    public int randomDuration(RandomSource random) {
        return random.nextInt(this.getMinDuration(), this.getMaxDuration() + 1);
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public int getMinIntervalWithPrevious() {
        return minIntervalWithPrevious;
    }

    public boolean isEligibleForSelection(int gameTime) {
        return gameTime >= this.getMinIntervalWithPrevious() + this.timeOfLast;
    }

    public int getWeight() {
        return weight;
    }

    public void setOnEnd(Consumer<ServerLevel> onEnd) {
        this.onEnd = onEnd;
    }

    public void setOnStart(Consumer<ServerLevel> onStart) {
        this.onStart = onStart;
    }

    public void setOnTick(Consumer<ServerLevel> onTick) {
        this.onTick = onTick;
    }

    public void onEnd(ServerLevel level) {
        this.defaultOnEnd();
        this.onEnd.accept(level);
    }

    public void onStart(ServerLevel level) {
        this.defaultOnStart();
        this.onStart.accept(level);
    }

    public void tick(ServerLevel level) {
        this.defaultOnTick();
        this.onTick.accept(level);
    }

    public void defaultOnStart() {
        // Default no-op
    }

    public void defaultOnEnd() {
        // Default no-op
    }

    public void defaultOnTick() {
        // Default no-op
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public static WeatherEvent fromString(String name) {
        for (WeatherEvent event : WeatherEvent.values()) {
            if (event.getSerializedName().equals(name)) {
                return event;
            }
        }
        return CLEAR;
    }
}
