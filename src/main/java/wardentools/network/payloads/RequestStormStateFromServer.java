package wardentools.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record RequestStormStateFromServer() implements CustomPacketPayload {

    public static final Type<RequestStormStateFromServer> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "request_storm_state_from_server"));

    public static final StreamCodec<ByteBuf, RequestStormStateFromServer> STREAM_CODEC =
            CodecBuilders.emptyCodec(RequestStormStateFromServer::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
