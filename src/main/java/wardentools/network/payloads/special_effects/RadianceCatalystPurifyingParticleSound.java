package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record RadianceCatalystPurifyingParticleSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "radiance_catalyst_purifying_particle_sound");

    public static void encode(RadianceCatalystPurifyingParticleSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static RadianceCatalystPurifyingParticleSound decode(FriendlyByteBuf buf) {
        return new RadianceCatalystPurifyingParticleSound(buf.readVector3f());
    }
}
