package wardentools.playerdata;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import wardentools.ModMain;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.playerdata.serializables.CompletedTasks;
import wardentools.playerdata.serializables.KnownWindWhispers;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class AttachCapabilities {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ModDataAttachments.COMPLETED_TASKS)) {
            event.getEntity().getData(ModDataAttachments.COMPLETED_TASKS)
                    .copy(event.getOriginal().getData(ModDataAttachments.COMPLETED_TASKS));
        }
        if (event.isWasDeath() && event.getOriginal().hasData(ModDataAttachments.KNOWN_WIND_WHISPERS)) {
            event.getEntity().getData(ModDataAttachments.KNOWN_WIND_WHISPERS)
                    .copy(event.getOriginal().getData(ModDataAttachments.KNOWN_WIND_WHISPERS));
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CompletedTasks taskData = player.getData(ModDataAttachments.COMPLETED_TASKS);
            for (int taskId : taskData.getAll()) {
                PacketDistributor.sendToPlayer(player, new SyncDataTaskToClient(taskId, false));
            }
            KnownWindWhispers whisperData = player.getData(ModDataAttachments.KNOWN_WIND_WHISPERS);
            PacketDistributor.sendToPlayer(player, new SyncKnownWhisperToClient(whisperData.getAllAsArray()));
        }
    }

}
