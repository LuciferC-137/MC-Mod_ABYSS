package wardentools.network.payloads.datasync;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SyncDataTaskToClient(int taskId, boolean remove) {
    public static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "sync_data_task_to_client");

    public static void encode(SyncDataTaskToClient msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.taskId());
        buf.writeBoolean(msg.remove());
    }

    public static SyncDataTaskToClient decode(FriendlyByteBuf buf) {
        return new SyncDataTaskToClient(buf.readInt(), buf.readBoolean());
    }
}
