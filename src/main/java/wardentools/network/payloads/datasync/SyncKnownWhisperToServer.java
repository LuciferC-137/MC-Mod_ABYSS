package wardentools.network.payloads.datasync;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SyncKnownWhisperToServer(int whisperId, boolean remove) implements CustomPacketPayload {
    public static final Type<SyncKnownWhisperToServer> TYPE
            = new Type<>(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "sync_known_whisper_to_server"));

    public static final StreamCodec<ByteBuf, SyncKnownWhisperToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SyncKnownWhisperToServer::whisperId,
            ByteBufCodecs.BOOL,
            SyncKnownWhisperToServer::remove,
            SyncKnownWhisperToServer::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
