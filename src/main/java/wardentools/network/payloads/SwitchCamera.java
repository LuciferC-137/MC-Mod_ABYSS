package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record SwitchCamera() {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "switch_camera");

    public static void encode(SwitchCamera msg, FriendlyByteBuf buf) {
        CodecBuilders.encodeEmpty(msg, buf);
    }

    public static SwitchCamera decode(FriendlyByteBuf buf) {
        return CodecBuilders.decodeEmpty(buf, SwitchCamera::new);
    }
}
