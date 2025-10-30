package wardentools.network.payloads.datasync;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SyncDataTaskToClient(int taskId, boolean remove) implements CustomPacketPayload {
    public static final Type<SyncDataTaskToClient> TYPE
            = new Type<>(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "sync_data_task_to_client"));

    public static final StreamCodec<ByteBuf, SyncDataTaskToClient> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SyncDataTaskToClient::taskId,
            ByteBufCodecs.BOOL,
            SyncDataTaskToClient::remove,
            SyncDataTaskToClient::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
