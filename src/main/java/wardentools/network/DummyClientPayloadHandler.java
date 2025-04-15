package wardentools.network;


import net.neoforged.neoforge.network.handling.IPayloadContext;
import wardentools.network.PayloadsRecords.ParticlesSounds.*;
import wardentools.network.PayloadsRecords.SendFogDistanceToClient;
import wardentools.network.PayloadsRecords.ShowWinScreen;
import wardentools.network.PayloadsRecords.SwitchCamera;

public class DummyClientPayloadHandler implements IClientPayloadHandler {

    public void showWinScreen(ShowWinScreen msg, final IPayloadContext ctx) {}

    public void updateFogDistance(SendFogDistanceToClient msg, final IPayloadContext ctx) {}

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

    public void windWhispererMessageSound(WindWhispererMessageSound msg, final IPayloadContext ctx) {}

    public void windWhisperSound(WindWhisperSound msg, final IPayloadContext ctx) {}

}
