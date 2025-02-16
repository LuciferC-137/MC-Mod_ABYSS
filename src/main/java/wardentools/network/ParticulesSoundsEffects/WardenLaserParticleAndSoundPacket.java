package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class WardenLaserParticleAndSoundPacket {
    private final Vec3 startPosition;
    private final Vec3 direction;
    private final int laserLength;

    public WardenLaserParticleAndSoundPacket(Vec3 startPosition, Vec3 direction, int laserLength) {
        this.startPosition = startPosition;
        this.direction = direction;
        this.laserLength = laserLength;
    }
    
    public WardenLaserParticleAndSoundPacket(FriendlyByteBuf buffer) {
    	this(buffer.readVec3(), buffer.readVec3(), buffer.readInt());
    }

    public static void encode(WardenLaserParticleAndSoundPacket packet, FriendlyByteBuf buffer) {
        buffer.writeDouble(packet.startPosition.x);
        buffer.writeDouble(packet.startPosition.y);
        buffer.writeDouble(packet.startPosition.z);
        buffer.writeDouble(packet.direction.x);
        buffer.writeDouble(packet.direction.y);
        buffer.writeDouble(packet.direction.z);
        buffer.writeInt(packet.laserLength);
    }

    

    public static void handle(WardenLaserParticleAndSoundPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                Vec3 startPosition = packet.startPosition;
                Vec3 direction = packet.direction;
                for (int i = 1; i < Mth.floor(direction.length()) + packet.laserLength; ++i) {
                    Vec3 particlePosition = startPosition.add(direction.scale(i));
                    level.addParticle(ParticleTypes.SONIC_BOOM, false,
                            particlePosition.x, particlePosition.y, particlePosition.z, 0.0D, 0.0D, 0.0D);
                }
				level.playLocalSound(new BlockPos((int)startPosition.x, (int)startPosition.y, (int)startPosition.z),
                        SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS,
                        1.0F, 1.0F, false);
            }
        });
        context.setPacketHandled(true);
    }
}
