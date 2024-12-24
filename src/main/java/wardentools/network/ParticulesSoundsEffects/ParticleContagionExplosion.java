package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.particle.ParticleRegistry;

import java.io.IOException;

public class ParticleContagionExplosion {
    private final Vec3 source;
    private final float radius;
    private final float speed;
    private final int particleNumber;

    public ParticleContagionExplosion(Vec3 source, float radius, float speed, int particleNumber) {
        this.source = source;
        this.radius = radius;
        this.speed = speed;
        this.particleNumber = particleNumber;
    }

    public ParticleContagionExplosion(FriendlyByteBuf buffer) {
        this.source = buffer.readVec3();
        this.radius = buffer.readFloat();
        this.speed = buffer.readFloat();
        this.particleNumber = buffer.readInt();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeVec3(this.source);
        buffer.writeFloat(this.radius);
        buffer.writeFloat(this.speed);
        buffer.writeInt(this.particleNumber);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleContagionExplosion msg) {
        Vec3 source = msg.source;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                for (int i = 0; i < msg.particleNumber; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * msg.radius;
                    double offsetY = (level.random.nextDouble() - 0.5) * msg.radius;
                    double offsetZ = (level.random.nextDouble() - 0.5) * msg.radius;
                    double norm = Math.sqrt(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
                    double speed = msg.speed / norm;
                    level.addParticle(ParticleRegistry.CORRUPTION.get(),
                            source.x + offsetX,
                            source.y + offsetY,
                            source.z + offsetZ,
                            offsetX * speed, offsetY * speed, offsetZ * speed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
