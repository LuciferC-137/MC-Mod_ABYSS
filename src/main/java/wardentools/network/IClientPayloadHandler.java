package wardentools.network;

import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.network.payloads.datasync.SyncDataTaskToClient;
import wardentools.network.payloads.datasync.SyncKnownWhisperToClient;
import wardentools.network.payloads.special_effects.*;
import wardentools.network.payloads.SendFogStateToClient;
import wardentools.network.payloads.ShowWinScreen;
import wardentools.network.payloads.SwitchCamera;

public interface IClientPayloadHandler {

    static IClientPayloadHandler create() {
        if (FMLEnvironment.dist.isClient()) {
            return new ClientPayloadHandler();
        } else {
            return new DummyClientPayloadHandler();
        }
    }

    void showWinScreen(ShowWinScreen msg, final IPayloadContext ctx);

    void updateFogDistance(SendFogStateToClient msg, final IPayloadContext ctx);

    void switchCamera(SwitchCamera msg, final IPayloadContext ctx) ;

    void ancientLaboratoryGateSound(AncientLaboratoryGateSound msg, final IPayloadContext ctx) ;

    void incarnationEmergeSound(IncarnationEmergeSound msg, final IPayloadContext ctx) ;

    void incarnationScreamSound(IncarnationScreamSound msg, final IPayloadContext ctx) ;

    void incarnationSonicStrikeSound(IncarnationSonicStrikeSound msg, final IPayloadContext ctx) ;

    void contagionParticleExplosion(ContagionParticleExplosion msg, final IPayloadContext ctx) ;

    void particleDarktreeFenceDestroy(ParticleDarktreeFenceDestroy msg, final IPayloadContext ctx) ;

    void radianceCatalystChargedParticleSound(RadianceCatalystChargedParticleSound msg, final IPayloadContext ctx) ;

    void radianceCatalystChargingParticleSound(RadianceCatalystChargingParticleSound msg, final IPayloadContext ctx) ;

    void radianceCatalystPurifyingParticleSound(RadianceCatalystPurifyingParticleSound msg, final IPayloadContext ctx) ;

    void radianceParticleExplosion(RadianceParticleExplosion msg, final IPayloadContext ctx) ;

    void wardenDeathParticle(WardenDeathParticle msg, final IPayloadContext ctx) ;

    void themeIncarnationStart(ThemeIncarnationStart msg, final IPayloadContext ctx) ;

    void themeIncarnationStop(ThemeIncarnationStop msg, final IPayloadContext ctx) ;

    void protectorHeartSynchronize(ProtectorHeartSynchronize msg, final IPayloadContext ctx) ;

    void wardenLaserParticleSound(WardenLaserParticleSound msg, final IPayloadContext ctx) ;

    void windWhisperSound(WindWhisperSound msg, final IPayloadContext ctx) ;

    void particleShineExplosion(ParticleShineExplosion msg, final IPayloadContext ctx);

    void livingSproutBurst(LivingSproutBurst msg, final IPayloadContext ctx);

    void syncDataTask(SyncDataTaskToClient msg, final IPayloadContext ctx);

    void syncKnownWhisper(SyncKnownWhisperToClient msg, final IPayloadContext ctx);

    void sendWhisperToClient(WindWhisperSendToClient msg, final IPayloadContext ctx);
}
