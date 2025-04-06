package wardentools.playerdata;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.List;

public class WhisperDataSyncClientPacket {
    private final int[] ids;

    public WhisperDataSyncClientPacket(int[] ids) {
        this.ids = ids;
    }

    public WhisperDataSyncClientPacket(List<Integer> ids) {
        this.ids = ids.stream().mapToInt(i -> i).toArray();
    }

    public WhisperDataSyncClientPacket(FriendlyByteBuf buffer) {
        this.ids = buffer.readVarIntArray();
    }

    public static void encode(WhisperDataSyncClientPacket msg, FriendlyByteBuf buffer) {
        buffer.writeVarIntArray(msg.ids);
    }

    public static WhisperDataSyncClientPacket decode(FriendlyByteBuf buffer) {
        return new WhisperDataSyncClientPacket(buffer.readVarIntArray());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context));
        context.setPacketHandled(true);
    }

    public static void handlePacket(WhisperDataSyncClientPacket msg, CustomPayloadEvent.Context ctx) {
        if (ctx.isClientSide()) {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(
                        KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                    for (int id : msg.ids) {
                        if (!data.knowsWhisper(id)) {
                            data.addKnownWhisper(id);
                        }
                    }
                });
            }
        }
    }
}
