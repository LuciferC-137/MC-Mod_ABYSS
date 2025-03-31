package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ProtectorHeartSynchronize(Vector3f pos, float health) implements CustomPacketPayload {

    public static final Type<ProtectorHeartSynchronize> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "protector_heart_sync"));

    public static final StreamCodec<ByteBuf, ProtectorHeartSynchronize> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ProtectorHeartSynchronize::pos,
            ByteBufCodecs.FLOAT,
            ProtectorHeartSynchronize::health,
            ProtectorHeartSynchronize::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
