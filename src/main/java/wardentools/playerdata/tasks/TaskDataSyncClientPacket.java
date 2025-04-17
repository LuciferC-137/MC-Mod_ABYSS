package wardentools.playerdata.tasks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.List;

public class TaskDataSyncClientPacket {
    private final int[] ids;

    public TaskDataSyncClientPacket(int[] ids) {
        this.ids = ids;
    }

    public TaskDataSyncClientPacket(List<Integer> ids) {
        this.ids = ids.stream().mapToInt(i -> i).toArray();
    }

    public TaskDataSyncClientPacket(FriendlyByteBuf buffer) {
        this.ids = buffer.readVarIntArray();
    }

    public static void encode(TaskDataSyncClientPacket msg, FriendlyByteBuf buffer) {
        buffer.writeVarIntArray(msg.ids);
    }

    public static TaskDataSyncClientPacket decode(FriendlyByteBuf buffer) {
        return new TaskDataSyncClientPacket(buffer.readVarIntArray());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context));
        context.setPacketHandled(true);
    }

    public static void handlePacket(TaskDataSyncClientPacket msg, CustomPayloadEvent.Context ctx) {
        if (ctx.isClientSide()) {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(
                        TaskDataProvider.TASKS_CAPABILITY).ifPresent(data -> {
                    for (int id : msg.ids) {
                        if (!data.taskCompleted(id)) {
                            data.addCompletedTask(id);
                        } else {
                            data.removeCompletedTask(id);
                        }
                    }
                });
            }
        }
    }
}
