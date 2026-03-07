package wardentools.network.payloads.debug;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

/**
 * Payload sent from client to server to request community data.
 * The server will respond with a {@link SyncCommunityDataToClient}.
 * @param communityUuidMost The most significant bits of the community UUID
 * @param communityUuidLeast The least significant bits of the community UUID
 */
public record RequestCommunityDataToServer(long communityUuidMost, long communityUuidLeast)
        implements CustomPacketPayload {

    public static final Type<RequestCommunityDataToServer> TYPE
            = new Type<>(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "request_community_data"));

    public static final StreamCodec<ByteBuf, RequestCommunityDataToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG, RequestCommunityDataToServer::communityUuidMost,
            ByteBufCodecs.VAR_LONG, RequestCommunityDataToServer::communityUuidLeast,
            RequestCommunityDataToServer::new
    );

    public java.util.UUID getCommunityUuid() {
        return new java.util.UUID(communityUuidMost, communityUuidLeast);
    }

    public static RequestCommunityDataToServer fromUuid(java.util.UUID uuid) {
        return new RequestCommunityDataToServer(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

