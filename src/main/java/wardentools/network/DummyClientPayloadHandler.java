package wardentools.network;


import wardentools.network.payloads.SendFogStateToClient;
import wardentools.network.payloads.ShowWinScreen;
import wardentools.network.payloads.SwitchCamera;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.network.payloads.special_effects.*;

public class DummyClientPayloadHandler implements IClientPayloadHandler {

    public void showWinScreen(ShowWinScreen msg, ForgePayloadContext ctx) {}

    public void updateFogDistance(SendFogStateToClient msg, ForgePayloadContext ctx) {}

    public void switchCamera(SwitchCamera msg, ForgePayloadContext ctx) {}

    public void ancientLaboratoryGateSound(AncientLaboratoryGateSound msg, ForgePayloadContext ctx) {}

    public void incarnationEmergeSound(IncarnationEmergeSound msg, ForgePayloadContext ctx) {}

    public void incarnationScreamSound(IncarnationScreamSound msg, ForgePayloadContext ctx) {}

    public void incarnationSonicStrikeSound(IncarnationSonicStrikeSound msg, ForgePayloadContext ctx) {}

    public void contagionParticleExplosion(ContagionParticleExplosion msg, ForgePayloadContext ctx) {}

    public void particleDarktreeFenceDestroy(ParticleDarktreeFenceDestroy msg, ForgePayloadContext ctx) {}

    public void radianceCatalystChargedParticleSound(RadianceCatalystChargedParticleSound msg, ForgePayloadContext ctx) {}

    public void radianceCatalystChargingParticleSound(RadianceCatalystChargingParticleSound msg, ForgePayloadContext ctx) {}

    public void radianceCatalystPurifyingParticleSound(RadianceCatalystPurifyingParticleSound msg, ForgePayloadContext ctx) {}

    public void radianceParticleExplosion(RadianceParticleExplosion msg, ForgePayloadContext ctx) {}

    public void wardenDeathParticle(WardenDeathParticle msg, ForgePayloadContext ctx) {}

    public void themeIncarnationStart(ThemeIncarnationStart msg, ForgePayloadContext ctx) {}

    public void themeIncarnationStop(ThemeIncarnationStop msg, ForgePayloadContext ctx) {}

    public void protectorHeartSynchronize(ProtectorHeartSynchronize msg, ForgePayloadContext ctx) {}

    public void wardenLaserParticleSound(WardenLaserParticleSound msg, ForgePayloadContext ctx) {}

    public void windWhisperSound(WindWhisperSound msg, ForgePayloadContext ctx) {}

    public void particleShineExplosion(ParticleShineExplosion msg, ForgePayloadContext ctx) {}

    public void livingSproutBurst(LivingSproutBurst msg, ForgePayloadContext ctx) {}

    public void syncDataTask(SyncDataTaskToClient msg, ForgePayloadContext ctx) {}

    public void syncKnownWhisper(SyncKnownWhisperToClient msg, ForgePayloadContext ctx) {}

    public void sendWhisperToClient(WindWhisperSendToClient msg, ForgePayloadContext ctx) {}
}
