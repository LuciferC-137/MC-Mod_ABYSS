package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.particle.ParticleRegistry;

import java.io.IOException;

public class ParticleContagionImplosion {
    private static final double SPEED = 0.5D;
    private static final double RADIUS = 5.0D;
    private final Vec3 source;

    public ParticleContagionImplosion(Vec3 source) {this.source = source;}

    public ParticleContagionImplosion(FriendlyByteBuf buffer) {this.source = buffer.readVec3();}

    public void encode(FriendlyByteBuf buffer) {buffer.writeVec3(this.source);}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleContagionImplosion msg) {
        Vec3 source = msg.source;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                for (int i = 0; i < 100; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * RADIUS;
                    double offsetY = (level.random.nextDouble() - 0.5) * RADIUS;
                    double offsetZ = (level.random.nextDouble() - 0.5) * RADIUS;
                    double norm = Math.sqrt(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
                    double speed = SPEED / norm;
                    level.addParticle(ParticleRegistry.CORRUPTION.get(),
                            source.x + offsetX,
                            source.y + offsetY,
                            source.z + offsetZ,
                            -offsetX * speed, -offsetY * speed, -offsetZ * speed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
