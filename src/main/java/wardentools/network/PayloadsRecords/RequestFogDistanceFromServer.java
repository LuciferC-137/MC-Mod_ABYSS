package wardentools.network.PayloadsRecords;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record RequestFogDistanceFromServer() implements CustomPacketPayload {

    public static final Type<RequestFogDistanceFromServer> TYPE
            = new Type<>(
            ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "request_fog_distance_from_server"));

    public static final StreamCodec<ByteBuf, RequestFogDistanceFromServer> STREAM_CODEC =
            CodecBuilders.emptyCodec(RequestFogDistanceFromServer::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
