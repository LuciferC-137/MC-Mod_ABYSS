package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record RadianceParticleExplosion(Vector3f pos, float radius,
                                        float speed, int particleNumber,
                                        boolean implosion) implements CustomPacketPayload {

    public static final Type<RadianceParticleExplosion> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "radiance_particle_explosion"));

    public static final StreamCodec<ByteBuf, RadianceParticleExplosion> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            RadianceParticleExplosion::pos,
            ByteBufCodecs.FLOAT,
            RadianceParticleExplosion::radius,
            ByteBufCodecs.FLOAT,
            RadianceParticleExplosion::speed,
            ByteBufCodecs.INT,
            RadianceParticleExplosion::particleNumber,
            ByteBufCodecs.BOOL,
            RadianceParticleExplosion::implosion,
            RadianceParticleExplosion::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
