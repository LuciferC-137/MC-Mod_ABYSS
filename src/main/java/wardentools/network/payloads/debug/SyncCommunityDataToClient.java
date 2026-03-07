package wardentools.network.payloads.debug;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Payload sent from server to client with community data for the debug screen.
 * Uses a custom codec to handle variable-length member UUID lists.
 */
public record SyncCommunityDataToClient(
        UUID communityUuid,
        int centerX, int centerY, int centerZ,
        int radius,
        List<UUID> memberUuids
) implements CustomPacketPayload {

    public static final Type<SyncCommunityDataToClient> TYPE
            = new Type<>(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "sync_community_data"));

    public static final StreamCodec<ByteBuf, SyncCommunityDataToClient> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public void encode(@NotNull ByteBuf buf, @NotNull SyncCommunityDataToClient payload) {
            buf.writeLong(payload.communityUuid.getMostSignificantBits());
            buf.writeLong(payload.communityUuid.getLeastSignificantBits());
            buf.writeInt(payload.centerX);
            buf.writeInt(payload.centerY);
            buf.writeInt(payload.centerZ);
            buf.writeInt(payload.radius);
            buf.writeInt(payload.memberUuids.size());
            for (UUID uuid : payload.memberUuids) {
                buf.writeLong(uuid.getMostSignificantBits());
                buf.writeLong(uuid.getLeastSignificantBits());
            }
        }

        @Override
        public @NotNull SyncCommunityDataToClient decode(@NotNull ByteBuf buf) {
            UUID communityUuid = new UUID(buf.readLong(), buf.readLong());
            int cx = buf.readInt();
            int cy = buf.readInt();
            int cz = buf.readInt();
            int radius = buf.readInt();
            int count = buf.readInt();
            List<UUID> members = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                members.add(new UUID(buf.readLong(), buf.readLong()));
            }
            return new SyncCommunityDataToClient(communityUuid, cx, cy, cz, radius, members);
        }
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
