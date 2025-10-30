package wardentools.misc.wind;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.payloads.special_effects.WindWhisperSendToClient;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class WhisperManager {
    public static final WhisperManager INSTANCE = new WhisperManager();
    private static final int MIN_TIME_BETWEEN_WHISPERS = 500;
    private int nextMinTime = MIN_TIME_BETWEEN_WHISPERS;
    private int timeSinceLastWhisper = 0;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
        INSTANCE.tick();
    }

    public void tick() {
        timeSinceLastWhisper++;
    }

    public boolean sendRandomWhisperToAllPlayers(ServerLevel level, BlockPos pos) {
        if (this.timeSinceLastWhisper > nextMinTime) {
            this.timeSinceLastWhisper = 0;
            this.nextMinTime = MIN_TIME_BETWEEN_WHISPERS + level.random.nextInt(MIN_TIME_BETWEEN_WHISPERS);
            PacketDistributor.sendToPlayersTrackingChunk(level, level.getChunkAt(pos).getPos(),
                    new WindWhisperSendToClient());
            return true;
        }
        return false;
    }

    public static void sendRandomWhisperToPlayer(@NotNull Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }
        PacketDistributor.sendToPlayer(serverPlayer, new WindWhisperSendToClient());
    }
}
