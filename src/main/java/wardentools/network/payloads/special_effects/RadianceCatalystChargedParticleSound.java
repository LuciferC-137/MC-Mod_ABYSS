package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record RadianceCatalystChargedParticleSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "radiance_catalyst_charged_particle_sound");

    public static void encode(RadianceCatalystChargedParticleSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static RadianceCatalystChargedParticleSound decode(FriendlyByteBuf buf) {
        return new RadianceCatalystChargedParticleSound(buf.readVector3f());
    }
}
