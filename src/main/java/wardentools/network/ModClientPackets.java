package wardentools.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import wardentools.ModMain;
import wardentools.network.PayloadsRecords.*;
import wardentools.network.PayloadsRecords.ParticlesSounds.*;


@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModClientPackets {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		IClientPayloadHandler handler = IClientPayloadHandler.create();
		final PayloadRegistrar registrar = event.registrar("1");
		// ------------------ CLIENT SPECIAL EFFECTS PACKETS --------------------------
		registrar.playToClient(
				AncientLaboratoryGateSound.TYPE,
				AncientLaboratoryGateSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::ancientLaboratoryGateSound
				)
		);
		registrar.playToClient(
				IncarnationEmergeSound.TYPE,
				IncarnationEmergeSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::incarnationEmergeSound
				)
		);
		registrar.playToClient(
				IncarnationScreamSound.TYPE,
				IncarnationScreamSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::incarnationScreamSound
				)
		);
		registrar.playToClient(
				IncarnationSonicStrikeSound.TYPE,
				IncarnationSonicStrikeSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::incarnationSonicStrikeSound
				)
		);
		registrar.playToClient(
				ContagionParticleExplosion.TYPE,
				ContagionParticleExplosion.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::contagionParticleExplosion
				)
		);
		registrar.playToClient(
				ParticleDarktreeFenceDestroy.TYPE,
				ParticleDarktreeFenceDestroy.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::particleDarktreeFenceDestroy
				)
		);
		registrar.playToClient(
				RadianceCatalystChargedParticleSound.TYPE,
				RadianceCatalystChargedParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::radianceCatalystChargedParticleSound
				)
		);
		registrar.playToClient(
				RadianceCatalystChargingParticleSound.TYPE,
				RadianceCatalystChargingParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::radianceCatalystChargingParticleSound
				)
		);
		registrar.playToClient(
				RadianceCatalystPurifyingParticleSound.TYPE,
				RadianceCatalystPurifyingParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::radianceCatalystPurifyingParticleSound
				)
		);
		registrar.playToClient(
				RadianceParticleExplosion.TYPE,
				RadianceParticleExplosion.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::radianceParticleExplosion
				)
		);
		registrar.playToClient(
				WardenDeathParticle.TYPE,
				WardenDeathParticle.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::wardenDeathParticle
				)
		);
		registrar.playToClient(
				ThemeIncarnationStart.TYPE,
				ThemeIncarnationStart.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::themeIncarnationStart
				)
		);
		registrar.playToClient(
				ThemeIncarnationStop.TYPE,
				ThemeIncarnationStop.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::themeIncarnationStop
				)
		);
		registrar.playToClient(
				ProtectorHeartSynchronize.TYPE,
				ProtectorHeartSynchronize.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::protectorHeartSynchronize
				)
		);
		registrar.playToClient(
				WardenLaserParticleSound.TYPE,
				WardenLaserParticleSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::wardenLaserParticleSound
				)
		);
		registrar.playToClient(
				WindWhispererMessageSound.TYPE,
				WindWhispererMessageSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::windWhispererMessageSound
				)
		);
		registrar.playToClient(
				WindWhisperSound.TYPE,
				WindWhisperSound.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::windWhisperSound
				)
		);
		// ----------------------- CLIENT GAME PACKETS -----------------------------
		registrar.playToClient(
				ShowWinScreen.TYPE,
				ShowWinScreen.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::showWinScreen
				)
		);
		registrar.playToClient(
				SendFogDistanceToClient.TYPE,
				SendFogDistanceToClient.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::updateFogDistance
				)
		);
		registrar.playToClient(
				SwitchCamera.TYPE,
				SwitchCamera.STREAM_CODEC,
				new MainThreadPayloadHandler<>(
						handler::switchCamera
				)
		);
	}
}
