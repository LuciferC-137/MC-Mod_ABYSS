package wardentools.network.ParticulesSoundsEffects;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.slf4j.Logger;
import wardentools.particle.ParticleRegistry;

import java.io.IOException;


public class LivingSproutBurstPacket {
	private static final Logger LOGGER = LogUtils.getLogger();

    private final BlockPos pos;

    public LivingSproutBurstPacket(BlockPos pos) {
        this.pos = pos;
    }

    public LivingSproutBurstPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
    }

   
    public void handle(CustomPayloadEvent.Context context) {
    	context.enqueueWork(() -> handlePacket(this));
    	context.setPacketHandled(true);
    }

    private static void handlePacket(LivingSproutBurstPacket msg) {
    	BlockPos pos = msg.pos;
        try (ClientLevel level = Minecraft.getInstance().level) {
			if (level != null) {
				// Spawn particle in the center
				for (int i = 0; i < 10; i++) {
					double offsetX = (level.random.nextFloat() - 0.5F) * 0.2F;
					double offsetY = level.random.nextFloat() * 0.2F;
					double offsetZ = (level.random.nextFloat() - 0.5F) * 0.2F;
					level.addParticle(ParticleTypes.WHITE_ASH,
							(float)pos.getX() + 0.5F + offsetX,
							(float)pos.getY() + 0.5F + offsetY,
							(float)pos.getZ() + 0.5F + offsetZ,
							0F, 0.05F, 0F);
				}
				// Corruption particle in a sphere
				for (int i = 0; i < 40; i++) {
					double theta = 2F * Math.PI * level.random.nextFloat();
					double phi = Math.acos(2F * level.random.nextFloat() - 1);
					double r = 0.3F;
					double offsetX = r * Math.sin(phi) * Math.cos(theta);
					double offsetY = r * Math.cos(phi);
					double offsetZ = r * Math.sin(phi) * Math.sin(theta);
					level.addParticle(ParticleRegistry.CORRUPTION.get(),
							(float)pos.getX() + 0.5F,
							(float)pos.getY() + 0.5F,
							(float)pos.getZ() + 0.5F,
							offsetX, offsetY, offsetZ);
				}
			}
		} catch (IOException e) {
			LOGGER.warn("Error handling LivingSproutBurstPacket: {}", e.getMessage());
		}
    }
}
