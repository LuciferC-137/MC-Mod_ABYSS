package wardentools.network.ParticulesSoundsEffects;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.particle.ParticleRegistry;


public class ParticleRadianceCatalystPurifying {
	private static final float SOUND_VOLUME = 0.5F;
    private final BlockPos pos;

    public ParticleRadianceCatalystPurifying(BlockPos pos) {
        this.pos = pos;
    }
    
    public ParticleRadianceCatalystPurifying(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
    }

   
    public void handle(CustomPayloadEvent.Context context) {
    	context.enqueueWork(() -> handlePacket(this));
    	context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleRadianceCatalystPurifying msg) {
    	BlockPos pos = msg.pos;
        try (ClientLevel level = Minecraft.getInstance().level) {
			if (level != null) {
				double x = pos.getX() + 0.5;
		        double y = pos.getY() + 0.5;
		        double z = pos.getZ() + 0.5;
			    int number = level.random.nextInt(3) + 1;
		        for (int i = 0; i < number; i++) {
		            double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
		            double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
		            double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
		            level.addParticle(ParticleRegistry.CORRUPTION.get(), true,
		            		x, y, z, offsetX, offsetY, offsetZ);
		        }
		        level.playLocalSound(x, y, z, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, SOUND_VOLUME, 1.0F, false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
