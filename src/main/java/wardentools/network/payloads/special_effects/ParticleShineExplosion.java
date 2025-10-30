package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ParticleShineExplosion(Vector3f pos, float radius,
                                     float speed, int particleNumber,
                                     int color) implements CustomPacketPayload {

    public static final Type<ParticleShineExplosion> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "particle_shine_explosion"));

    public static final StreamCodec<ByteBuf, ParticleShineExplosion> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ParticleShineExplosion::pos,
            ByteBufCodecs.FLOAT,
            ParticleShineExplosion::radius,
            ByteBufCodecs.FLOAT,
            ParticleShineExplosion::speed,
            ByteBufCodecs.INT,
            ParticleShineExplosion::particleNumber,
            ByteBufCodecs.INT,
            ParticleShineExplosion::color,
            ParticleShineExplosion::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
