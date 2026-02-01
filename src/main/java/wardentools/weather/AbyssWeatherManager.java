package wardentools.weather;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.network.payloads.WeatherSyncToClient;
import wardentools.network.payloads.special_effects.WindWhisperSound;
import wardentools.weather.lightning.AbyssLightningEntity;

import java.util.HashSet;
import java.util.Set;

/**
Class only effective on the Server-side. Manage the actual weather events in the abyss.
 This class sends packets to the clients to set the fog distances.
*/
public class AbyssWeatherManager {
    private static final int AVERAGE_TICK_BETWEEN_LIGHTNING_PER_CHUNK = 5000;

    public static final Component stormMessage
            = Component.translatable("message." + ModMain.MOD_ID + ".wind.storm");
    public static final Component stormEndMessage
            = Component.translatable("message." + ModMain.MOD_ID + ".wind.storm_end");

    private int eventCountDown = RandomSource.create()
            .nextInt(WeatherEvent.CLEAR.getMinDuration(), WeatherEvent.CLEAR.getMaxDuration());
    private int tickSinceLastEvent = 0;

    private Set<ChunkPos> loadedChunks = new HashSet<>();

    private WeatherEvent activeEvent = WeatherEvent.CLEAR;

    public AbyssWeatherManager() {
        WeatherEvent.STORM.setOnStart(this::onStartStorm);
        WeatherEvent.STORM.setOnEnd(this::stopStorm);
        WeatherEvent.STORM.setOnTick(this::stormTick);
    }

    public void tick(@NotNull ServerLevel level) {
        this.eventCountDown--;
        this.activeEvent.tick(level);

        if (!this.anyWeatherEventActive()) {
            this.tickSinceLastEvent++;
        }
        if (this.eventCountDown <= 0 && this.anyWeatherEventActive()) {
            this.weatherClear(level, this.activeEvent.randomTickToNextEvent(level.getRandom()));
        } else if (this.eventCountDown <= 0) {
            this.startNewEvent(level);
        }
    }

    private boolean anyWeatherEventActive() {
        return this.activeEvent != WeatherEvent.CLEAR;
    }

    public void weatherClear(ServerLevel level) {
        this.weatherClear(level, this.activeEvent.randomTickToNextEvent(level.getRandom()));
    }

    public void weatherClear(ServerLevel level, int duration) {
        this.eventCountDown = duration;
        this.activeEvent.onEnd(level);
        this.activeEvent = WeatherEvent.CLEAR;
        this.onChangeEvent(level);
    }

    public void startNewEvent(ServerLevel level) {
        this.eventCountDown = this.activeEvent.randomDuration(level.getRandom());
        this.activeEvent = WeatherEvent.randomWeatherEvent(level.getRandom(),
                this.activeEvent, (int)level.getGameTime());
        if (this.activeEvent != WeatherEvent.CLEAR) {
            this.tickSinceLastEvent = 0;
            this.onChangeEvent(level);
        }
        this.activeEvent.onStart(level);
    }

    /**
     * Force a storm to start immediately.
     * @param level The server level where the storm should start.
     * @param duration The duration of the storm in ticks. If duration is less than or equal to 0,
     *                 a random duration will be chosen.
     */
    public void forceStorm(ServerLevel level, int duration) {
        if (this.activeEvent != WeatherEvent.STORM) {
            this.activeEvent.onEnd(level);
            this.activeEvent = WeatherEvent.STORM;
            this.tickSinceLastEvent = 0;
            this.onChangeEvent(level);
            this.activeEvent.onStart(level);
        }
        if (duration > 0) {
            this.eventCountDown = duration;
        } else {
            this.eventCountDown = this.activeEvent.randomDuration(level.getRandom());
        }
    }

    public void onChangeEvent(ServerLevel level) {
        level.players().stream().filter(player -> player.level() == level)
                .forEach((player) -> {
                    PacketDistributor.sendToPlayer(player,
                            new WeatherSyncToClient(this.activeEvent.getSerializedName()));
                });
    }

    public void startNewEvent(ServerLevel level, int duration){
        this.eventCountDown = duration;
        this.startNewEvent(level);
    }

    private void updateLoadedChunks(ServerLevel level) {
        this.loadedChunks = new HashSet<>();
        for (ServerPlayer player : level.players()) {
            if (player.level() == level) {
                ChunkPos playerChunk = new ChunkPos(player.blockPosition());
                int viewDistance = 8;
                for (int dx = -viewDistance; dx <= viewDistance; dx++) {
                    for (int dz = -viewDistance; dz <= viewDistance; dz++) {
                        this.loadedChunks.add(new ChunkPos(playerChunk.x + dx, playerChunk.z + dz));
                    }
                }
            }
        }
    }

    private void stormTick(ServerLevel level) {
        // The way this method is done is to avoid to have more lightning when more players are in the same place.
        // This could be improved by directly having a list of the loaded chunk instead of calculating it.
        if (this.eventCountDown % 20 == 0) this.updateLoadedChunks(level);
        for (ChunkPos chunkPos : this.loadedChunks) {
            if (level.random.nextInt(AVERAGE_TICK_BETWEEN_LIGHTNING_PER_CHUNK) == 0) {
                addLightningInChunk(level, chunkPos);
            }
        }
    }

    private static void addLightningInChunk(ServerLevel level, ChunkPos chunkPos) {
        AbyssLightningEntity lightning = ModEntities.ABYSS_LIGHTNING.get().create(level);
        if (lightning != null) {
            RandomSource random = level.random;
            int x = (chunkPos.x << 4) + random.nextInt(16);
            int z = (chunkPos.z << 4) + random.nextInt(16);
            if (level.random.nextInt(10) == 0) lightning.setIsLegacyLightning(true);
            int y = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z)).getY()
                    + (lightning.isLegacyLightning() ? 0 : 60);
            lightning.moveTo(x, y, z);
            if (level.random.nextInt(10) == 0) lightning.setIsLegacyLightning(true);
            level.addFreshEntity(lightning);
        }
    }

    public void onStartStorm(ServerLevel level) {
        level.players().stream().filter(player -> player.level() == level)
                .forEach((player) -> {
                    PacketDistributor.sendToPlayer(player,
                            new WindWhisperSound(WindWhisperSound.StormStatus.START));
                });
    }

    private void stopStorm(ServerLevel level) {
        level.players().stream().filter(player -> player.level() == level)
                .forEach((player) -> {
                    PacketDistributor.sendToPlayer(player,
                            new WindWhisperSound(WindWhisperSound.StormStatus.END));
                });
        this.loadedChunks.clear();
    }

    public boolean isStorming() {
        return this.activeEvent == WeatherEvent.STORM;
    }
}

