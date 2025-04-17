package wardentools.playerdata.whispers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class WhisperDataSyncServerPacket {
    private final int id;

    public WhisperDataSyncServerPacket(int id) {
        this.id = id;
    }

    public WhisperDataSyncServerPacket(FriendlyByteBuf buffer) {
        this.id = buffer.readInt();
    }

    public static void encode(WhisperDataSyncServerPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.id);
    }

    public static WhisperDataSyncServerPacket decode(FriendlyByteBuf buffer) {
        return new WhisperDataSyncServerPacket(buffer.readInt());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context));
        context.setPacketHandled(true);
    }

    public static void handlePacket(WhisperDataSyncServerPacket msg, CustomPayloadEvent.Context ctx) {
        if (ctx.isServerSide()) {
            if (ctx.getSender() != null) {
                ctx.getSender().getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                    if (!data.knowsWhisper(msg.id)) {
                        data.addKnownWhisper(msg.id);
                    }
                });
            }
        }
    }
}
