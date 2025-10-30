package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record WardenLaserParticleSound(Vector3f startPos,
                                       Vector3f direction,
                                       int laserLength) implements CustomPacketPayload {

    public static final Type<WardenLaserParticleSound> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "warden_laser_particle_sound"));

    public static final StreamCodec<ByteBuf, WardenLaserParticleSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            WardenLaserParticleSound::startPos,
            ByteBufCodecs.VECTOR3F,
            WardenLaserParticleSound::direction,
            ByteBufCodecs.INT,
            WardenLaserParticleSound::laserLength,
            WardenLaserParticleSound::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
