package wardentools.network.payloads.datasync;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SyncKnownWhisperToClient(int[] whisperIds) {
    public static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "sync_known_whisper_to_client");

    public static void encode(SyncKnownWhisperToClient msg, FriendlyByteBuf buf) {
        buf.writeVarIntArray(msg.whisperIds());
    }

    public static SyncKnownWhisperToClient decode(FriendlyByteBuf buf) {
        return new SyncKnownWhisperToClient(buf.readVarIntArray());
    }
}
