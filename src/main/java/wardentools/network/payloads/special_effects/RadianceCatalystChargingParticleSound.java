package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record RadianceCatalystChargingParticleSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "radiance_catalyst_charging_particle_sound");

    public static void encode(RadianceCatalystChargingParticleSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static RadianceCatalystChargingParticleSound decode(FriendlyByteBuf buf) {
        return new RadianceCatalystChargingParticleSound(buf.readVector3f());
    }
}
