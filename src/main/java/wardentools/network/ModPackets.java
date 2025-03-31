package wardentools.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import wardentools.ModMain;
import wardentools.network.PayloadsRecords.*;
import wardentools.network.PayloadsRecords.ParticlesSounds.*;


@EventBusSubscriber(modid = ModMain.MOD_ID)
public class ModPackets {

	@SubscribeEvent
	public static void registerClient(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");

		// ------------------ CLIENT SPECIAL EFFECTS PACKETS --------------------------
		registrar.playToClient(
				AncientLaboratoryGateSound.TYPE,
				AncientLaboratoryGateSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::ancientLaboratoryGateSound
				)
		);
		registrar.playToClient(
				IncarnationEmergeSound.TYPE,
				IncarnationEmergeSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::incarnationEmergeSound
				)
		);
		registrar.playToClient(
				IncarnationScreamSound.TYPE,
				IncarnationScreamSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::incarnationScreamSound
				)
		);
		registrar.playToClient(
				IncarnationSonicStrikeSound.TYPE,
				IncarnationSonicStrikeSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::incarnationSonicStrikeSound
				)
		);
		registrar.playToClient(
				ContagionParticleExplosion.TYPE,
				ContagionParticleExplosion.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::contagionParticleExplosion
				)
		);
		registrar.playToClient(
				ParticleDarktreeFenceDestroy.TYPE,
				ParticleDarktreeFenceDestroy.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::particleDarktreeFenceDestroy
				)
		);
		registrar.playToClient(
				RadianceCatalystChargedParticleSound.TYPE,
				RadianceCatalystChargedParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::radianceCatalystChargedParticleSound
				)
		);
		registrar.playToClient(
				RadianceCatalystChargingParticleSound.TYPE,
				RadianceCatalystChargingParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::radianceCatalystChargingParticleSound
				)
		);
		registrar.playToClient(
				RadianceCatalystPurifyingParticleSound.TYPE,
				RadianceCatalystPurifyingParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::radianceCatalystPurifyingParticleSound
				)
		);
		registrar.playToClient(
				RadianceParticleExplosion.TYPE,
				RadianceParticleExplosion.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::radianceParticleExplosion
				)
		);
		registrar.playToClient(
				WardenDeathParticle.TYPE,
				WardenDeathParticle.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::wardenDeathParticle
				)
		);
		registrar.playToClient(
				ThemeIncarnationStart.TYPE,
				ThemeIncarnationStart.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::themeIncarnationStart
				)
		);
		registrar.playToClient(
				ProtectorHeartSynchronize.TYPE,
				ProtectorHeartSynchronize.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::protectorHeartSynchronize
				)
		);
		registrar.playToClient(
				WardenLaserParticleSound.TYPE,
				WardenLaserParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::wardenLaserParticleSound
				)
		);
		registrar.playToClient(
				WindWhispererMessageSound.TYPE,
				WindWhispererMessageSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::windWhispererMessageSound
				)
		);
		registrar.playToClient(
				WindWhisperSound.TYPE,
				WindWhisperSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientSpecialEffectPayloadHandler::windWhisperSound
				)
		);
		// ----------------------- CLIENT GAME PACKETS -----------------------------
		registrar.playToClient(
				ShowWinScreen.TYPE,
				ShowWinScreen.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientMechanicsPayloadHandler::showWinScreen
				)
		);
		registrar.playToClient(
				BossEventSynchronize.TYPE,
				BossEventSynchronize.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientMechanicsPayloadHandler::bossEventSynchronize
				)
		);
		registrar.playToClient(
				SendFogDistanceToClient.TYPE,
				SendFogDistanceToClient.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						ClientMechanicsPayloadHandler::updateFogDistance
				)
		);
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
