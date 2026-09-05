package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ContagionParticleExplosion(Vector3f pos, float radius,
                                         float speed, int particleNumber,
                                         boolean implosion) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "contagion_particle_explosion");

    public static void encode(ContagionParticleExplosion msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
        buf.writeFloat(msg.radius());
        buf.writeFloat(msg.speed());
        buf.writeInt(msg.particleNumber());
        buf.writeBoolean(msg.implosion());
    }

    public static ContagionParticleExplosion decode(FriendlyByteBuf buf) {
        return new ContagionParticleExplosion(buf.readVector3f(), buf.readFloat(), buf.readFloat(),
                buf.readInt(), buf.readBoolean());
    }
}
