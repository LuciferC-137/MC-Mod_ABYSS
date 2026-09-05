package wardentools.network;

import net.minecraftforge.fml.loading.FMLEnvironment;
import wardentools.network.payloads.SendFogStateToClient;
import wardentools.network.payloads.ShowWinScreen;
import wardentools.network.payloads.SwitchCamera;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.network.payloads.special_effects.*;

public interface IClientPayloadHandler {

    static IClientPayloadHandler create() {
        if (FMLEnvironment.dist.isClient()) {
            return new ClientPayloadHandler();
        } else {
            return new DummyClientPayloadHandler();
        }
    }

    void showWinScreen(ShowWinScreen msg, ForgePayloadContext ctx);

    void updateFogDistance(SendFogStateToClient msg, ForgePayloadContext ctx);

    void switchCamera(SwitchCamera msg, ForgePayloadContext ctx);

    void ancientLaboratoryGateSound(AncientLaboratoryGateSound msg, ForgePayloadContext ctx);

    void incarnationEmergeSound(IncarnationEmergeSound msg, ForgePayloadContext ctx);

    void incarnationScreamSound(IncarnationScreamSound msg, ForgePayloadContext ctx);

    void incarnationSonicStrikeSound(IncarnationSonicStrikeSound msg, ForgePayloadContext ctx);

    void contagionParticleExplosion(ContagionParticleExplosion msg, ForgePayloadContext ctx);

    void particleDarktreeFenceDestroy(ParticleDarktreeFenceDestroy msg, ForgePayloadContext ctx);

    void radianceCatalystChargedParticleSound(RadianceCatalystChargedParticleSound msg, ForgePayloadContext ctx);

    void radianceCatalystChargingParticleSound(RadianceCatalystChargingParticleSound msg, ForgePayloadContext ctx);

    void radianceCatalystPurifyingParticleSound(RadianceCatalystPurifyingParticleSound msg, ForgePayloadContext ctx);

    void radianceParticleExplosion(RadianceParticleExplosion msg, ForgePayloadContext ctx);

    void wardenDeathParticle(WardenDeathParticle msg, ForgePayloadContext ctx);

    void themeIncarnationStart(ThemeIncarnationStart msg, ForgePayloadContext ctx);

    void themeIncarnationStop(ThemeIncarnationStop msg, ForgePayloadContext ctx);

    void protectorHeartSynchronize(ProtectorHeartSynchronize msg, ForgePayloadContext ctx);

    void wardenLaserParticleSound(WardenLaserParticleSound msg, ForgePayloadContext ctx);

    void windWhisperSound(WindWhisperSound msg, ForgePayloadContext ctx);

    void particleShineExplosion(ParticleShineExplosion msg, ForgePayloadContext ctx);

    void livingSproutBurst(LivingSproutBurst msg, ForgePayloadContext ctx);

    void syncDataTask(SyncDataTaskToClient msg, ForgePayloadContext ctx);

    void syncKnownWhisper(SyncKnownWhisperToClient msg, ForgePayloadContext ctx);

    void sendWhisperToClient(WindWhisperSendToClient msg, ForgePayloadContext ctx);
}
