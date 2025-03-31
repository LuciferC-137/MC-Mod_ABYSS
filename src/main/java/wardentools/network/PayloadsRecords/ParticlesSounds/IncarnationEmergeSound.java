package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record IncarnationEmergeSound(Vector3f pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<IncarnationEmergeSound> TYPE
            = new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "incarnation_emerge_sound"));

    public static final StreamCodec<ByteBuf, IncarnationEmergeSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            IncarnationEmergeSound::pos,
            IncarnationEmergeSound::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
