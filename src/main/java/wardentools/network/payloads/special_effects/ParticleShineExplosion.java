package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ParticleShineExplosion(Vector3f pos, float radius,
                                     float speed, int particleNumber,
                                     int color) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "particle_shine_explosion");

    public static void encode(ParticleShineExplosion msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
        buf.writeFloat(msg.radius());
        buf.writeFloat(msg.speed());
        buf.writeInt(msg.particleNumber());
        buf.writeInt(msg.color());
    }

    public static ParticleShineExplosion decode(FriendlyByteBuf buf) {
        return new ParticleShineExplosion(buf.readVector3f(), buf.readFloat(), buf.readFloat(),
                buf.readInt(), buf.readInt());
    }
}
