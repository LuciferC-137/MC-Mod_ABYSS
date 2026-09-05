package wardentools.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import wardentools.ModMain;
import wardentools.network.payloads.RequestStormStateFromServer;
import wardentools.network.payloads.SwitchAchievement;
import wardentools.network.payloads.TeleportPlayerTo;
import wardentools.network.payloads.datasync.SyncDataTaskToServer;
import wardentools.network.payloads.datasync.SyncKnownWhisperToServer;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPackets {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ModMain.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SubscribeEvent
    public static void register(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            int packetId = 0;
            packetId = registerServerbound(packetId, TeleportPlayerTo.class,
                    TeleportPlayerTo::encode, TeleportPlayerTo::decode,
                    (msg, ctx) -> ServerPayloadHandler.teleportPlayerTo(msg, ForgePayloadContext.server(ctx)));
            packetId = registerServerbound(packetId, SwitchAchievement.class,
                    SwitchAchievement::encode, SwitchAchievement::decode,
                    (msg, ctx) -> ServerPayloadHandler.switchAchievement(msg, ForgePayloadContext.server(ctx)));
            packetId = registerServerbound(packetId, RequestStormStateFromServer.class,
                    RequestStormStateFromServer::encode, RequestStormStateFromServer::decode,
                    (msg, ctx) -> ServerPayloadHandler.sendServerFogDistanceToPlayer(msg, ForgePayloadContext.server(ctx)));
            packetId = registerServerbound(packetId, SyncDataTaskToServer.class,
                    SyncDataTaskToServer::encode, SyncDataTaskToServer::decode,
                    (msg, ctx) -> ServerPayloadHandler.syncTaskData(msg, ForgePayloadContext.server(ctx)));
            packetId = ModClientPackets.registerClientbound(packetId);
            packetId = registerServerbound(packetId, SyncKnownWhisperToServer.class,
                    SyncKnownWhisperToServer::encode, SyncKnownWhisperToServer::decode,
                    (msg, ctx) -> ServerPayloadHandler.syncWindWhisperData(msg, ForgePayloadContext.server(ctx)));
        });
    }

    public static <MSG> void sendToServer(MSG msg) {
        CHANNEL.sendToServer(msg);
    }

    private static <MSG> int registerServerbound(int packetId,
                                                 Class<MSG> messageClass,
                                                 BiConsumer<MSG, FriendlyByteBuf> encoder,
                                                 Function<FriendlyByteBuf, MSG> decoder,
                                                 BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        CHANNEL.registerMessage(packetId, messageClass, encoder, decoder, consumer,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
        return packetId + 1;
    }
}
