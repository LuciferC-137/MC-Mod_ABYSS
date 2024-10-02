package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import org.jetbrains.annotations.NotNull;
import wardentools.particle.ParticleRegistry;

import java.io.IOException;

public class ParticleRadianceExplosion {
    private final Vec3 source;

    public ParticleRadianceExplosion(Vec3 source) {this.source = source;}

    public ParticleRadianceExplosion(FriendlyByteBuf buffer) {this.source = buffer.readVec3();}

    public void encode(FriendlyByteBuf buffer) {buffer.writeVec3(this.source);}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ParticleRadianceExplosion msg) {
        Vec3 source = msg.source;
        try (ClientLevel level = Minecraft.getInstance().level) {
            if (level != null) {
                for (int i = 0; i < 100; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * 1.1;
                    double offsetY = (level.random.nextDouble() - 0.5) * 1.1;
                    double offsetZ = (level.random.nextDouble() - 0.5) * 1.1;
                    level.addParticle(ParticleRegistry.RADIANCE.get(), source.x, source.y, source.z,
                            offsetX, offsetY, offsetZ);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
