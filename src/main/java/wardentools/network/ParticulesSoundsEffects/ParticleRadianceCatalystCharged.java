package wardentools.network.ParticulesSoundsEffects;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ParticleRadianceCatalystCharged {
    private final BlockPos pos;

    public ParticleRadianceCatalystCharged(BlockPos pos) {
        this.pos = pos;
    }
    
    public ParticleRadianceCatalystCharged(FriendlyByteBuf buffer) {
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
	private static void handlePacket(ParticleRadianceCatalystCharged msg) {
    	BlockPos pos = msg.pos;
        try (ClientLevel level = Minecraft.getInstance().level) {
			if (level != null) {
				double x = pos.getX();
			    double y = pos.getY();
			    double z = pos.getZ();
			    int number = level.random.nextInt(3) + 1;
		        for (int i = 0; i < number; i++) {
		            double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
		            double offsetY = (level.random.nextDouble() - 0.5) * 0.2;
		            double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;
		            level.addParticle(ParticleTypes.END_ROD, true,
		            		x + 0.5, y + 0.5, z + 0.5, offsetX, offsetY, offsetZ);
		        }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
