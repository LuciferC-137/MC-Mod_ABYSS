package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record WardenLaserParticleSound(Vector3f startPos,
                                       Vector3f direction,
                                       int laserLength) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "warden_laser_particle_sound");

    public static void encode(WardenLaserParticleSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.startPos());
        buf.writeVector3f(msg.direction());
        buf.writeInt(msg.laserLength());
    }

    public static WardenLaserParticleSound decode(FriendlyByteBuf buf) {
        return new WardenLaserParticleSound(buf.readVector3f(), buf.readVector3f(), buf.readInt());
    }
}
