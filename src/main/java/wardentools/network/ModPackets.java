package wardentools.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import wardentools.ModMain;
import wardentools.network.PayloadsRecords.*;


@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
				RequestFogDistanceFromServer.TYPE,
				RequestFogDistanceFromServer.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ServerPayloadHandler::sendServerFogDistanceToPlayer
				)
		);
	}
}
