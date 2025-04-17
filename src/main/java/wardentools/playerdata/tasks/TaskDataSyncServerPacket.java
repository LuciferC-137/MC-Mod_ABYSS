package wardentools.playerdata.tasks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class TaskDataSyncServerPacket {
    private final int id;

    public TaskDataSyncServerPacket(int id) {
        this.id = id;
    }

    public TaskDataSyncServerPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readInt();
    }

    public static void encode(TaskDataSyncServerPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.id);
    }

    public static TaskDataSyncServerPacket decode(FriendlyByteBuf buffer) {
        return new TaskDataSyncServerPacket(buffer.readInt());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context));
        context.setPacketHandled(true);
    }

    public static void handlePacket(TaskDataSyncServerPacket msg, CustomPayloadEvent.Context ctx) {
        if (ctx.isServerSide()) {
            if (ctx.getSender() != null) {
                ctx.getSender().getCapability(TaskDataProvider.TASKS_CAPABILITY).ifPresent(data -> {
                    if (!data.taskCompleted(msg.id)) {
                        data.addCompletedTask(msg.id);
                    } else {
                        data.removeCompletedTask(msg.id);
                    }
                });
            }
        }
    }
}
