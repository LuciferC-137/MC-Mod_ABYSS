package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.particle.options.ShineParticleOptions;

import java.io.IOException;

public class ParticleShineExplosion {
    private final Vec3 source;
    private final float radius;
    private final float speed;
    private final int particleNumber;
    private final int color;

    public ParticleShineExplosion(Vec3 source, float radius,
                                  float speed, int particleNumber, int color) {
        this.source = source;
        this.radius = radius;
        this.speed = speed;
        this.particleNumber = particleNumber;
        this.color = color;
    }

    public ParticleShineExplosion(FriendlyByteBuf buffer) {
        this.source = buffer.readVec3();
        this.radius = buffer.readFloat();
        this.speed = buffer.readFloat();
        this.particleNumber = buffer.readInt();
        this.color = buffer.readInt();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeVec3(this.source);
        buffer.writeFloat(this.radius);
        buffer.writeFloat(this.speed);
        buffer.writeInt(this.particleNumber);
        buffer.writeInt(this.color);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleShineExplosion msg) {
        Vec3 source = msg.source;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                for (int i = 0; i < msg.particleNumber; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * msg.radius;
                    double offsetY = (level.random.nextDouble() - 0.5) * msg.radius;
                    double offsetZ = (level.random.nextDouble() - 0.5) * msg.radius;
                    double norm = Math.sqrt(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
                    double speed = msg.speed / norm;
                    level.addParticle(new ShineParticleOptions(Vec3.ZERO, msg.color),
                            source.x + offsetX,
                            source.y + offsetY,
                            source.z + offsetZ,
                            offsetX * speed, offsetY * speed, offsetZ * speed);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling ParticleShineExplosion packet: " + e.getMessage());
        }
    }
}
