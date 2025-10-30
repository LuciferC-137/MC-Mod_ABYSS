package wardentools.network.payloads.datasync;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

import java.nio.IntBuffer;

public record SyncKnownWhisperToClient(int[] whisperIds) implements CustomPacketPayload {
    public static final Type<SyncKnownWhisperToClient> TYPE
            = new Type<>(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "sync_known_whisper_to_client"));

    public static final StreamCodec<ByteBuf, SyncKnownWhisperToClient> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT
                            .apply(ByteBufCodecs.list())
                            .map(list -> list.stream().mapToInt(Integer::intValue).toArray(),
                                    arr -> java.util.Arrays.stream(arr).boxed().toList()),
                    SyncKnownWhisperToClient::whisperIds,
                    SyncKnownWhisperToClient::new
            );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
