package wardentools.weather;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import wardentools.entity.ModEntities;
import wardentools.weather.lightning.AbyssLightningEntity;

public class AbyssWeatherManager {
    private static final int MAX_EVENT_DURATION = 1200;
    private static final int MIN_EVENT_DURATION = 600;
    private static final int AVERAGE_TICK_BETWEEN_STORM = 2000; // 100 seconds
    public static final float MAX_FOG_DISTANCE = 150f;
    public static final float MIN_FOG_DISTANCE = 20f;
    public static final int FOG_BLEND_DURATION = 300;
    private int weatherTimer = 0;
    private int timeSinceStormBegin = 0;
    private int timeSinceLastEvent = 0;
    private boolean isStorming = false;

    public void tick(Level level) {
        if (level.isClientSide) return;
        this.weatherTimer--;
        if (this.isStorming) stormTick(level);
        if (this.weatherTimer <= 0 && this.anyWeatherEventActive()) {
            this.resetAllWeather(level);
        }
        if (!this.anyWeatherEventActive()) this.timeSinceLastEvent++;
        this.randomChangeInWeather(level);
    }

    private boolean anyWeatherEventActive() {
        return this.isStorming;
    }

    private void resetAllWeather(Level level) {
        this.weatherTimer = 0;
        this.isStorming = false;
        this.stopStorm(level);
    }

    private void randomChangeInWeather(Level level) {
        if (this.weatherTimer <= 0 && level.random.nextInt(AVERAGE_TICK_BETWEEN_STORM) == 0){
            this.weatherTimer = level.random.nextInt(MIN_EVENT_DURATION, MAX_EVENT_DURATION);
            this.timeSinceLastEvent = 0;
            this.isStorming = !this.isStorming;
            if (this.isStorming) startStorm(level);
            else stopStorm(level);
        }
    }

    private void stormTick(Level level) {
        this.timeSinceStormBegin++;
        if (level.random.nextInt(100) == 0) {
            level.players().stream().filter(player -> player.level() == level)
                    .forEach(player -> this.addLightningBolt(level, player.blockPosition()));
        }
    }

    private void addLightningBolt(Level level, BlockPos playerPos) {
        AbyssLightningEntity lightning = ModEntities.ABYSS_LIGHTNING.get().create(level);
        if (lightning != null) {
            lightning.moveTo(playerPos.getX() + level.getRandom().nextInt(300) - 150,
                    level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, playerPos).getY(),
                    playerPos.getZ() + level.getRandom().nextInt(300) - 150);
            level.addFreshEntity(lightning);
        }
    }

    private void startStorm(Level level) {
        level.players().stream().filter(player -> player.level() == level)
                .forEach(player -> player.sendSystemMessage(Component.literal("A storm is coming...")));
    }

    private void stopStorm(Level level) {
        level.players().stream().filter(player -> player.level() == level)
                .forEach(player -> player.sendSystemMessage(Component.literal("The storm is over.")));
        this.timeSinceStormBegin = 0;
    }

    public float getFogDistance() {
        if (this.timeSinceStormBegin < FOG_BLEND_DURATION && this.isStorming) {
            return (1f - (float) this.timeSinceStormBegin / (float) FOG_BLEND_DURATION)
                    * (MAX_FOG_DISTANCE - MIN_FOG_DISTANCE) + MIN_FOG_DISTANCE;
        } else if (this.isStorming) return MIN_FOG_DISTANCE;
        else if (this.timeSinceLastEvent < FOG_BLEND_DURATION) {
            return (float) this.timeSinceLastEvent / (float) FOG_BLEND_DURATION
                    * (MAX_FOG_DISTANCE - MIN_FOG_DISTANCE) + MIN_FOG_DISTANCE;
        }
        return MAX_FOG_DISTANCE;
    }
}

