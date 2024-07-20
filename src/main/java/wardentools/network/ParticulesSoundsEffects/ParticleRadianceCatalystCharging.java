package wardentools.network.ParticulesSoundsEffects;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ParticleRadianceCatalystCharging {
    private final BlockPos pos;
    private static final double speedReduction = 15;

    public ParticleRadianceCatalystCharging(BlockPos pos) {
        this.pos = pos;
    }
    
    public ParticleRadianceCatalystCharging(FriendlyByteBuf buffer) {
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
	private static void handlePacket(ParticleRadianceCatalystCharging msg) {
    	BlockPos pos = msg.pos;
        try (ClientLevel level = Minecraft.getInstance().level) {
			if (level != null) {
				double x = pos.getX() + 0.5;
		        double y = pos.getY() + 0.5;
		        double z = pos.getZ() + 0.5;

		        double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
		        double offsetY = (level.random.nextDouble() - 0.5) * 2.0;
		        double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;
		        level.addParticle(ParticleTypes.END_ROD, true,
		            	x+offsetX, y+offsetY, z+offsetZ,
		            	-offsetX/speedReduction, -offsetY/speedReduction, -offsetZ/speedReduction);
		        if (level.random.nextInt(20)==1) {
		        	level.playLocalSound(x, y, z, SoundEvents.FURNACE_FIRE_CRACKLE,
			        		SoundSource.BLOCKS, 1.0F, 1.0F, false);
		        }
		        
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
