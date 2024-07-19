package wardentools.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.minecraftforge.network.PacketDistributor;
import wardentools.ModMain;
import wardentools.network.ParticulesSoundsEffects.ParticleRadianceCatalystCharged;
import wardentools.network.ParticulesSoundsEffects.ParticleWardenDeathPacket;
import wardentools.network.ParticulesSoundsEffects.WardenLaserParticleAndSoundPacket;


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
    }

    public static void sendToServer(Object msg) {
    	INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }
    
    public static void sendToAllClient(Object msg) {
    	INSTANCE.send(msg, PacketDistributor.ALL.noArg());
    }
    
    
}
