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

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AttachCapabilities {
    public static final String whisperDataId = "known_whispers";

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
             event.addCapability(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, whisperDataId),
                     new KnownWhispersDataProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }
        event.getOriginal().getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(oldStore -> {
            event.getEntity().getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(newStore -> {
                newStore.copy(oldStore.getAll());
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
        }
    }

}
