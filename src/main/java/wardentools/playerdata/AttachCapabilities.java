package wardentools.playerdata;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.network.PacketHandler;
import wardentools.playerdata.tasks.TaskDataProvider;
import wardentools.playerdata.tasks.TaskDataSyncClientPacket;
import wardentools.playerdata.whispers.KnownWhispersDataProvider;
import wardentools.playerdata.whispers.WhisperDataSyncClientPacket;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AttachCapabilities {
    public static final String whisperDataId = "known_whispers";
    public static final String taskDataId = "tasks";

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
             event.addCapability(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, whisperDataId),
                     new KnownWhispersDataProvider());
             event.addCapability(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, taskDataId),
                     new TaskDataProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        copyData(event.getOriginal(), event.getEntity());
    }

    private static void copyData(Player oldPlayer, Player newPlayer) {
        if (newPlayer.level().isClientSide) return;
        oldPlayer.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(oldStore -> {
            newPlayer.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(newStore -> {
                newStore.copy(oldStore.getAll());
                WhisperDataSyncClientPacket packet = new WhisperDataSyncClientPacket(newStore.getAll().stream().toList());
                PacketHandler.sendToClient(packet, (ServerPlayer)newPlayer);
            });
        });
        oldPlayer.getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(oldStore -> {
            newPlayer.getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(newStore -> {
                newStore.copy(oldStore.getAll());
                TaskDataSyncClientPacket packet = new TaskDataSyncClientPacket(newStore.getAll().stream().toList());
                PacketHandler.sendToClient(packet, (ServerPlayer)newPlayer);
            });
        });
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            event.getEntity().getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                WhisperDataSyncClientPacket packet = new WhisperDataSyncClientPacket(data.getAll().stream().toList());
                PacketHandler.sendToClient(packet, serverPlayer);
            });
            event.getEntity().getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(data -> {
                TaskDataSyncClientPacket packet = new TaskDataSyncClientPacket(data.getAll().stream().toList());
                PacketHandler.sendToClient(packet, serverPlayer);
            });
        }
    }

}
