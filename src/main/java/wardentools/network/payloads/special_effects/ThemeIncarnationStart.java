package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record ThemeIncarnationStart() {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "theme_incarnation_start");

    public static void encode(ThemeIncarnationStart msg, FriendlyByteBuf buf) {
        CodecBuilders.encodeEmpty(msg, buf);
    }

    public static ThemeIncarnationStart decode(FriendlyByteBuf buf) {
        return CodecBuilders.decodeEmpty(buf, ThemeIncarnationStart::new);
    }
}
