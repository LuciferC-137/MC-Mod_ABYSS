package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record RequestStormStateFromServer() {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "request_storm_state_from_server");

    public static void encode(RequestStormStateFromServer msg, FriendlyByteBuf buf) {
        CodecBuilders.encodeEmpty(msg, buf);
    }

    public static RequestStormStateFromServer decode(FriendlyByteBuf buf) {
        return CodecBuilders.decodeEmpty(buf, RequestStormStateFromServer::new);
    }
}
