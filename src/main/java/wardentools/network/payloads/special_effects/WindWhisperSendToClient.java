package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record WindWhisperSendToClient() {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "wind_whisper_send_to_client");

    public static void encode(WindWhisperSendToClient msg, FriendlyByteBuf buf) {
        CodecBuilders.encodeEmpty(msg, buf);
    }

    public static WindWhisperSendToClient decode(FriendlyByteBuf buf) {
        return CodecBuilders.decodeEmpty(buf, WindWhisperSendToClient::new);
    }
}
