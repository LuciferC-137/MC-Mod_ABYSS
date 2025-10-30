package wardentools.network.payloads.datasync;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public record SyncDataTaskToServer(int taskId, boolean remove) implements CustomPacketPayload {
    public static final Type<SyncDataTaskToServer> TYPE
            = new Type<>(ResourceLocation
            .fromNamespaceAndPath(ModMain.MOD_ID, "sync_data_task_to_server"));

    public static final StreamCodec<ByteBuf, SyncDataTaskToServer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SyncDataTaskToServer::taskId,
            ByteBufCodecs.BOOL,
            SyncDataTaskToServer::remove,
            SyncDataTaskToServer::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
