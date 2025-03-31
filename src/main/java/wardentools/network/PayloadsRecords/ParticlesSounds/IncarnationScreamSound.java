package wardentools.network.PayloadsRecords.ParticlesSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import wardentools.ModMain;

public record IncarnationScreamSound(Vector3f pos) implements CustomPacketPayload {

    public static final Type<IncarnationScreamSound> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "incarnation_scream_sound"));

    public static final StreamCodec<ByteBuf, IncarnationScreamSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            IncarnationScreamSound::pos,
            IncarnationScreamSound::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
