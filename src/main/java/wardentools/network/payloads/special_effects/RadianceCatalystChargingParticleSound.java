package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record RadianceCatalystChargingParticleSound(Vector3f pos) implements CustomPacketPayload {

    public static final Type<RadianceCatalystChargingParticleSound> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_catalyst_charging_particle_sound"));

    public static final StreamCodec<ByteBuf, RadianceCatalystChargingParticleSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            RadianceCatalystChargingParticleSound::pos,
            RadianceCatalystChargingParticleSound::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
