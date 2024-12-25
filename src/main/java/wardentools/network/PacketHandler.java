package wardentools.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.minecraftforge.network.PacketDistributor;
import wardentools.ModMain;
import wardentools.network.ParticulesSoundsEffects.*;


@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PacketHandler {
    
    public static final SimpleChannel INSTANCE = ChannelBuilder.named(new ResourceLocation(ModMain.MOD_ID, "main"))
    		.serverAcceptedVersions((status, version)->true)
    		.clientAcceptedVersions((status, version)->true)
    		.networkProtocolVersion(1)
    		.simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(ParticleWardenDeathPacket.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleWardenDeathPacket::encode)
				.decoder(ParticleWardenDeathPacket::new)
				.consumerMainThread(ParticleWardenDeathPacket::handle)
				.add();
        INSTANCE.messageBuilder(WardenLaserParticleAndSoundPacket.class, NetworkDirection.PLAY_TO_CLIENT)
        		.encoder(WardenLaserParticleAndSoundPacket::encode)
        		.decoder(WardenLaserParticleAndSoundPacket::new)
        		.consumerMainThread(WardenLaserParticleAndSoundPacket::handle)
        		.add();
        INSTANCE.messageBuilder(ParticleRadianceCatalystCharged.class, NetworkDirection.PLAY_TO_CLIENT)
        		.encoder(ParticleRadianceCatalystCharged::encode)
        		.decoder(ParticleRadianceCatalystCharged::new)
        		.consumerMainThread(ParticleRadianceCatalystCharged::handle)
        		.add();
        INSTANCE.messageBuilder(ParticleRadianceCatalystPurifying.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleRadianceCatalystPurifying::encode)
				.decoder(ParticleRadianceCatalystPurifying::new)
				.consumerMainThread(ParticleRadianceCatalystPurifying::handle)
				.add();
        INSTANCE.messageBuilder(ParticleRadianceCatalystCharging.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleRadianceCatalystCharging::encode)
				.decoder(ParticleRadianceCatalystCharging::new)
				.consumerMainThread(ParticleRadianceCatalystCharging::handle)
				.add();
		INSTANCE.messageBuilder(ParticleRadianceExplosion.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleRadianceExplosion::encode)
				.decoder(ParticleRadianceExplosion::new)
				.consumerMainThread(ParticleRadianceExplosion::handle)
				.add();
		INSTANCE.messageBuilder(ParticleContagionImplosion.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleContagionImplosion::encode)
				.decoder(ParticleContagionImplosion::new)
				.consumerMainThread(ParticleContagionImplosion::handle)
				.add();
		INSTANCE.messageBuilder(ParticleRadianceImplosion.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleRadianceImplosion::encode)
				.decoder(ParticleRadianceImplosion::new)
				.consumerMainThread(ParticleRadianceImplosion::handle)
				.add();
		INSTANCE.messageBuilder(TeleportPlayerTo.class, NetworkDirection.PLAY_TO_SERVER)
				.encoder(TeleportPlayerTo::encode)
				.decoder(TeleportPlayerTo::new)
				.consumerMainThread(TeleportPlayerTo::handle)
				.add();
		INSTANCE.messageBuilder(ShowWinScreen.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ShowWinScreen::encode)
				.decoder(ShowWinScreen::new)
				.consumerMainThread(ShowWinScreen::handle)
				.add();
		INSTANCE.messageBuilder(SwitchAchievement.class, NetworkDirection.PLAY_TO_SERVER)
				.encoder(SwitchAchievement::encode)
				.decoder(SwitchAchievement::new)
				.consumerMainThread(SwitchAchievement::handle)
				.add();
		INSTANCE.messageBuilder(ParticleContagionExplosion.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(ParticleContagionExplosion::encode)
				.decoder(ParticleContagionExplosion::new)
				.consumerMainThread(ParticleContagionExplosion::handle)
				.add();
		INSTANCE.messageBuilder(AncientLaboratoryGateSound.class, NetworkDirection.PLAY_TO_CLIENT)
				.encoder(AncientLaboratoryGateSound::encode)
				.decoder(AncientLaboratoryGateSound::new)
				.consumerMainThread(AncientLaboratoryGateSound::handle)
				.add();

	}

    public static void sendToServer(Object msg) {
    	INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }
    
    public static void sendToAllClient(Object msg) {
    	INSTANCE.send(msg, PacketDistributor.ALL.noArg());
    }

	public static void sendToClient(Object msg, ServerPlayer player) {
		INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
	}
    
    
}
