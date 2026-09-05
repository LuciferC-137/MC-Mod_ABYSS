package wardentools.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import wardentools.network.payloads.SendFogStateToClient;
import wardentools.network.payloads.ShowWinScreen;
import wardentools.network.payloads.SwitchCamera;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.network.payloads.special_effects.*;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModClientPackets {

    public static int registerClientbound(int packetId) {
        IClientPayloadHandler handler = IClientPayloadHandler.create();

        packetId = registerClientbound(packetId, AncientLaboratoryGateSound.class,
                AncientLaboratoryGateSound::encode, AncientLaboratoryGateSound::decode,
                client(handler::ancientLaboratoryGateSound));
        packetId = registerClientbound(packetId, IncarnationEmergeSound.class,
                IncarnationEmergeSound::encode, IncarnationEmergeSound::decode,
                client(handler::incarnationEmergeSound));
        packetId = registerClientbound(packetId, IncarnationScreamSound.class,
                IncarnationScreamSound::encode, IncarnationScreamSound::decode,
                client(handler::incarnationScreamSound));
        packetId = registerClientbound(packetId, IncarnationSonicStrikeSound.class,
                IncarnationSonicStrikeSound::encode, IncarnationSonicStrikeSound::decode,
                client(handler::incarnationSonicStrikeSound));
        packetId = registerClientbound(packetId, ContagionParticleExplosion.class,
                ContagionParticleExplosion::encode, ContagionParticleExplosion::decode,
                client(handler::contagionParticleExplosion));
        packetId = registerClientbound(packetId, ParticleDarktreeFenceDestroy.class,
                ParticleDarktreeFenceDestroy::encode, ParticleDarktreeFenceDestroy::decode,
                client(handler::particleDarktreeFenceDestroy));
        packetId = registerClientbound(packetId, RadianceCatalystChargedParticleSound.class,
                RadianceCatalystChargedParticleSound::encode, RadianceCatalystChargedParticleSound::decode,
                client(handler::radianceCatalystChargedParticleSound));
        packetId = registerClientbound(packetId, RadianceCatalystChargingParticleSound.class,
                RadianceCatalystChargingParticleSound::encode, RadianceCatalystChargingParticleSound::decode,
                client(handler::radianceCatalystChargingParticleSound));
        packetId = registerClientbound(packetId, RadianceCatalystPurifyingParticleSound.class,
                RadianceCatalystPurifyingParticleSound::encode, RadianceCatalystPurifyingParticleSound::decode,
                client(handler::radianceCatalystPurifyingParticleSound));
        packetId = registerClientbound(packetId, RadianceParticleExplosion.class,
                RadianceParticleExplosion::encode, RadianceParticleExplosion::decode,
                client(handler::radianceParticleExplosion));
        packetId = registerClientbound(packetId, WardenDeathParticle.class,
                WardenDeathParticle::encode, WardenDeathParticle::decode,
                client(handler::wardenDeathParticle));
        packetId = registerClientbound(packetId, ThemeIncarnationStart.class,
                ThemeIncarnationStart::encode, ThemeIncarnationStart::decode,
                client(handler::themeIncarnationStart));
        packetId = registerClientbound(packetId, ThemeIncarnationStop.class,
                ThemeIncarnationStop::encode, ThemeIncarnationStop::decode,
                client(handler::themeIncarnationStop));
        packetId = registerClientbound(packetId, ProtectorHeartSynchronize.class,
                ProtectorHeartSynchronize::encode, ProtectorHeartSynchronize::decode,
                client(handler::protectorHeartSynchronize));
        packetId = registerClientbound(packetId, WardenLaserParticleSound.class,
                WardenLaserParticleSound::encode, WardenLaserParticleSound::decode,
                client(handler::wardenLaserParticleSound));
        packetId = registerClientbound(packetId, WindWhisperSound.class,
                WindWhisperSound::encode, WindWhisperSound::decode,
                client(handler::windWhisperSound));
        packetId = registerClientbound(packetId, ParticleShineExplosion.class,
                ParticleShineExplosion::encode, ParticleShineExplosion::decode,
                client(handler::particleShineExplosion));
        packetId = registerClientbound(packetId, LivingSproutBurst.class,
                LivingSproutBurst::encode, LivingSproutBurst::decode,
                client(handler::livingSproutBurst));
        packetId = registerClientbound(packetId, SyncDataTaskToClient.class,
                SyncDataTaskToClient::encode, SyncDataTaskToClient::decode,
                client(handler::syncDataTask));
        packetId = registerClientbound(packetId, SyncKnownWhisperToClient.class,
                SyncKnownWhisperToClient::encode, SyncKnownWhisperToClient::decode,
                client(handler::syncKnownWhisper));
        packetId = registerClientbound(packetId, ShowWinScreen.class,
                ShowWinScreen::encode, ShowWinScreen::decode,
                client(handler::showWinScreen));
        packetId = registerClientbound(packetId, SendFogStateToClient.class,
                SendFogStateToClient::encode, SendFogStateToClient::decode,
                client(handler::updateFogDistance));
        packetId = registerClientbound(packetId, SwitchCamera.class,
                SwitchCamera::encode, SwitchCamera::decode,
                client(handler::switchCamera));
        packetId = registerClientbound(packetId, WindWhisperSendToClient.class,
                WindWhisperSendToClient::encode, WindWhisperSendToClient::decode,
                client(handler::sendWhisperToClient));

        return packetId;
    }

    private static <MSG> int registerClientbound(int packetId,
                                                  Class<MSG> messageClass,
                                                  BiConsumer<MSG, FriendlyByteBuf> encoder,
                                                  Function<FriendlyByteBuf, MSG> decoder,
                                                  BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        ModPackets.CHANNEL.registerMessage(packetId, messageClass, encoder, decoder, consumer,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        return packetId + 1;
    }

    private static <MSG> BiConsumer<MSG, Supplier<NetworkEvent.Context>> client(
            BiConsumer<MSG, ForgePayloadContext> handler) {
        return (msg, ctx) -> handler.accept(msg, ForgePayloadContext.client(ctx));
    }
}
