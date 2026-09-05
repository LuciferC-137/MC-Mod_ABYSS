package wardentools.network.payloads.datasync;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SyncKnownWhisperToServer(int whisperId, boolean remove) {
    public static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "sync_known_whisper_to_server");

    public static void encode(SyncKnownWhisperToServer msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.whisperId());
        buf.writeBoolean(msg.remove());
    }

    public static SyncKnownWhisperToServer decode(FriendlyByteBuf buf) {
        return new SyncKnownWhisperToServer(buf.readInt(), buf.readBoolean());
    }
}
