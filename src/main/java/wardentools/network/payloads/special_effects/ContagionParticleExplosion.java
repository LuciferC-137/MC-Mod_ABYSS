package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ContagionParticleExplosion(Vector3f pos, float radius,
                                         float speed, int particleNumber,
                                         boolean implosion) implements CustomPacketPayload {

    public static final Type<ContagionParticleExplosion> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "contagion_particle_explosion"));

    public static final StreamCodec<ByteBuf, ContagionParticleExplosion> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ContagionParticleExplosion::pos,
            ByteBufCodecs.FLOAT,
            ContagionParticleExplosion::radius,
            ByteBufCodecs.FLOAT,
            ContagionParticleExplosion::speed,
            ByteBufCodecs.INT,
            ContagionParticleExplosion::particleNumber,
            ByteBufCodecs.BOOL,
            ContagionParticleExplosion::implosion,
            ContagionParticleExplosion::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
