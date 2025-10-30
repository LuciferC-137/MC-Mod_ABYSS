package wardentools.network;


import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.network.payloads.special_effects.*;
import wardentools.network.payloads.SendFogStateToClient;
import wardentools.network.payloads.ShowWinScreen;
import wardentools.network.payloads.SwitchCamera;

public class DummyClientPayloadHandler implements IClientPayloadHandler {

    public void showWinScreen(ShowWinScreen msg, final IPayloadContext ctx) {}

    public void updateFogDistance(SendFogStateToClient msg, final IPayloadContext ctx) {}

    public void switchCamera(SwitchCamera msg, final IPayloadContext ctx) {}

    public void ancientLaboratoryGateSound(AncientLaboratoryGateSound msg, final IPayloadContext ctx) {}

    public void incarnationEmergeSound(IncarnationEmergeSound msg, final IPayloadContext ctx) {}

    public void incarnationScreamSound(IncarnationScreamSound msg, final IPayloadContext ctx) {}

    public void incarnationSonicStrikeSound(IncarnationSonicStrikeSound msg, final IPayloadContext ctx) {}

    public void contagionParticleExplosion(ContagionParticleExplosion msg, final IPayloadContext ctx) {}

    public void particleDarktreeFenceDestroy(ParticleDarktreeFenceDestroy msg, final IPayloadContext ctx) {}

    public void radianceCatalystChargedParticleSound(RadianceCatalystChargedParticleSound msg, final IPayloadContext ctx) {}

    public void radianceCatalystChargingParticleSound(RadianceCatalystChargingParticleSound msg, final IPayloadContext ctx) {}

    public void radianceCatalystPurifyingParticleSound(RadianceCatalystPurifyingParticleSound msg, final IPayloadContext ctx) {}

    public void radianceParticleExplosion(RadianceParticleExplosion msg, final IPayloadContext ctx) {}

    public void wardenDeathParticle(WardenDeathParticle msg, final IPayloadContext ctx) {}

    public void themeIncarnationStart(ThemeIncarnationStart msg, final IPayloadContext ctx) {}

    public void themeIncarnationStop(ThemeIncarnationStop msg, final IPayloadContext ctx) {}

    public void protectorHeartSynchronize(ProtectorHeartSynchronize msg, final IPayloadContext ctx) {}

    public void wardenLaserParticleSound(WardenLaserParticleSound msg, final IPayloadContext ctx) {}

    public void windWhisperSound(WindWhisperSound msg, final IPayloadContext ctx) {}

    public void particleShineExplosion(ParticleShineExplosion msg, final IPayloadContext ctx) {}

    public void livingSproutBurst(LivingSproutBurst msg, final IPayloadContext ctx) {}

    public void syncDataTask(SyncDataTaskToClient msg, IPayloadContext ctx) {}

    public void syncKnownWhisper(SyncKnownWhisperToClient msg, IPayloadContext ctx) {}

    public void sendWhisperToClient(WindWhisperSendToClient msg, IPayloadContext ctx) {}

}
