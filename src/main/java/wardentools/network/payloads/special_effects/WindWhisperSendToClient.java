package wardentools.network.payloads.special_effects;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record WindWhisperSendToClient() implements CustomPacketPayload {

    public static final Type<WindWhisperSendToClient> TYPE
            = new Type<>(
                    ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "wind_whisper_send_to_client"));

    public static final StreamCodec<ByteBuf, WindWhisperSendToClient> STREAM_CODEC
            = CodecBuilders.emptyCodec(WindWhisperSendToClient::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
