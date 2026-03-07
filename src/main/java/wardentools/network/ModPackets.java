package wardentools.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import wardentools.ModMain;
import wardentools.network.payloads.*;
import wardentools.network.payloads.datasync.SyncDataTaskToServer;
import wardentools.network.payloads.datasync.SyncKnownWhisperToServer;
import wardentools.network.payloads.debug.DebugEditThryssarynToServer;
import wardentools.network.payloads.debug.RequestCommunityDataToServer;


@EventBusSubscriber(modid = ModMain.MOD_ID)
public class ModPackets {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");

		// -------------------------- SERVER PACKETS -------------------------------
		registrar.playToServer(
				TeleportPlayerTo.TYPE,
				TeleportPlayerTo.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ServerPayloadHandler::teleportPlayerTo
				)
		);
		registrar.playToServer(
				SwitchAchievement.TYPE,
				SwitchAchievement.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ServerPayloadHandler::switchAchievement
				)
		);
        registrar.playToServer(
                SyncDataTaskToServer.TYPE,
                SyncDataTaskToServer.STREAM_CODEC,
                new MainThreadPayloadHandler<>(
                        ServerPayloadHandler::syncTaskData
                )
        );
        registrar.playToServer(
                SyncKnownWhisperToServer.TYPE,
                SyncKnownWhisperToServer.STREAM_CODEC,
                new MainThreadPayloadHandler<>(
                        ServerPayloadHandler::syncWindWhisperData
                )
        );
        registrar.playToServer(
                DebugEditThryssarynToServer.TYPE,
                DebugEditThryssarynToServer.STREAM_CODEC,
                new MainThreadPayloadHandler<>(
                        ServerPayloadHandler::debugEditThryssaryn
                )
        );
        registrar.playToServer(
                RequestCommunityDataToServer.TYPE,
                RequestCommunityDataToServer.STREAM_CODEC,
                new MainThreadPayloadHandler<>(
                        ServerPayloadHandler::requestCommunityData
                )
        );
	}
}
