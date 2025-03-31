package wardentools.weather;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.network.PayloadsRecords.ParticlesSounds.WindWhisperSound;
import wardentools.network.PayloadsRecords.SendFogDistanceToClient;
import wardentools.weather.lightning.AbyssLightningEntity;

import java.util.HashSet;
import java.util.Set;

/**
Class only effective on the Server-side. Manage the actual weather events in the abyss.
 This class sends packets to the clients to set the fog distances.
*/

public class AbyssWeatherManager {
    private static final int MAX_EVENT_DURATION = 6000; // 5 minutes
    private static final int MIN_EVENT_DURATION = 3000; // 2.5 minutes
    private static final int MAX_TICK_BETWEEN_EVENT = 48000; // two days
    private static final int MIN_TICK_BETWEEN_EVENT = 24000; // one day
    private static final int AVERAGE_TICK_BETWEEN_LIGHTNING_PER_CHUNK = 5000;
    public static final float MAX_FOG_DISTANCE = 150f;
    public static final float MIN_FOG_DISTANCE = 20f;
    private int weatherTimer = RandomSource.create().nextInt(MIN_TICK_BETWEEN_EVENT, MAX_TICK_BETWEEN_EVENT);
    private int timeSinceStormBegin = 0;
    private boolean isStorming = false;
    private Set<ChunkPos> loadedChunks = new HashSet<>();
    private static final Component stormMessage
            = Component.translatable("message." + ModMain.MOD_ID + ".wind.storm");
    private static final Component stormEndMessage
            = Component.translatable("message." + ModMain.MOD_ID + ".wind.storm_end");

    public void tick(@NotNull ServerLevel level) {
        if (!level.getGameRules().getBoolean(GameRules.RULE_WEATHER_CYCLE)) return;
        this.weatherTimer--;
        if (this.isStorming) stormTick(level);
        if (this.weatherTimer <= 0 && this.anyWeatherEventActive()) {
            this.weatherClear(level, level.random.nextInt(MIN_TICK_BETWEEN_EVENT, MAX_TICK_BETWEEN_EVENT));
        } else if (this.weatherTimer <= 0) {
            this.startNewEvent(level);
        }
    }

    private boolean anyWeatherEventActive() {
        return this.isStorming;
    }

    public void weatherClear(ServerLevel level) {
        this.weatherClear(level, level.random.nextInt(MIN_TICK_BETWEEN_EVENT, MAX_TICK_BETWEEN_EVENT));
    }

    public void weatherClear(ServerLevel level, int duration) {
        this.weatherTimer = duration;
        this.stopStorm(level);
    }

    public void startNewEvent(ServerLevel level) {
        this.startNewStorm(level);
    }

    public void startNewEvent(ServerLevel level, int duration){
        this.weatherTimer = duration;
        this.startNewEvent(level);
    }

    public void startNewStorm(ServerLevel level) {
        this.weatherTimer = level.random.nextInt(MIN_EVENT_DURATION, MAX_EVENT_DURATION);
        this.onStartStorm(level);
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
        this.timeSinceStormBegin++;
        if (this.timeSinceStormBegin%20 == 0) this.updateLoadedChunks(level); //Optimize computation cost
        for (ChunkPos chunkPos : this.loadedChunks) {
            if (level.random.nextInt(AVERAGE_TICK_BETWEEN_LIGHTNING_PER_CHUNK) == 0) {
                addLightningInChunk(level, chunkPos);
            }
        }
    }

    private void addLightningInChunk(ServerLevel level, ChunkPos chunkPos) {
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
        this.isStorming = true;
        this.sendServerFogDistanceToAllClients();
        level.players().stream().filter(player -> player.level() == level)
                .forEach((player) -> {
                    player.sendSystemMessage(stormMessage);
                    PacketDistributor.sendToPlayer(player, new WindWhisperSound());
                });
    }

    private void stopStorm(ServerLevel level) {
        this.isStorming = false;
        this.sendServerFogDistanceToAllClients();
        level.players().stream().filter(player -> player.level() == level)
                .forEach((player) -> {
                    player.sendSystemMessage(stormEndMessage);
                    PacketDistributor.sendToPlayer(player, new WindWhisperSound());
                });
        this.timeSinceStormBegin = 0;
    }

    public float getFogDistance() {
        return this.isStorming ? MIN_FOG_DISTANCE : MAX_FOG_DISTANCE;
    }

    public void sendServerFogDistanceToAllClients() {
        PacketDistributor.sendToAllPlayers(new SendFogDistanceToClient(this.getFogDistance()));
    }

    public void sendServerFogDistanceToClient(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new SendFogDistanceToClient(this.getFogDistance()));
    }
}

