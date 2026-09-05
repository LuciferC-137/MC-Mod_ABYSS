package wardentools.network.payloads.datasync;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SyncDataTaskToServer(int taskId, boolean remove) {
    public static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "sync_data_task_to_server");

    public static void encode(SyncDataTaskToServer msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.taskId());
        buf.writeBoolean(msg.remove());
    }

    public static SyncDataTaskToServer decode(FriendlyByteBuf buf) {
        return new SyncDataTaskToServer(buf.readInt(), buf.readBoolean());
    }
}
