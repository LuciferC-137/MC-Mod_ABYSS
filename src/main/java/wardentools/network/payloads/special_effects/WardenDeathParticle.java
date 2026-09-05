package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record WardenDeathParticle(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "warden_death_particle");

    public static void encode(WardenDeathParticle msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static WardenDeathParticle decode(FriendlyByteBuf buf) {
        return new WardenDeathParticle(buf.readVector3f());
    }
}
