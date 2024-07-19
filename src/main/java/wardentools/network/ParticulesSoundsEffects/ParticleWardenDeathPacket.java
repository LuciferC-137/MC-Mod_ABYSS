package wardentools.network.ParticulesSoundsEffects;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ParticleWardenDeathPacket {
    private final BlockPos pos;

    public ParticleWardenDeathPacket(BlockPos pos) {
        this.pos = pos;
    }
    
    public ParticleWardenDeathPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
    }

   
    public void handle(CustomPayloadEvent.Context context) {
    	context.enqueueWork(() -> handlePacket(this));
    	context.setPacketHandled(true);
    }

    @SuppressWarnings("resource")
	private static void handlePacket(ParticleWardenDeathPacket msg) {
    	BlockPos pos = msg.pos;
        try (ClientLevel level = Minecraft.getInstance().level) {
			if (level != null) {
				double x = pos.getX();
			    double y = pos.getY();
			    double z = pos.getZ();
			    
				for (int i=0; i<100; i++) {
			    	double offsetX = (level.random.nextDouble() - 0.5) * 0.6;
			        double offsetY = (level.random.nextDouble() - 0.5) * 0.6;
			        double offsetZ = (level.random.nextDouble() - 0.5) * 0.6;
			    	level.addParticle(ParticleTypes.SQUID_INK, true, x, y+1, z, offsetX, offsetY, offsetZ);

				}
				
				for (int i=0; i<100; i++) {
			    	double offsetX = (level.random.nextDouble() - 0.5) * 0.7;
			        double offsetZ = (level.random.nextDouble() - 0.5) * 0.7;
			    	offsetX = (level.random.nextDouble() - 0.5) * 1.0;
			        offsetZ = (level.random.nextDouble() - 0.5) * 1.0;
			        level.addParticle(ParticleTypes.END_ROD, false, x, y+1, z, offsetX, 0, offsetZ);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
