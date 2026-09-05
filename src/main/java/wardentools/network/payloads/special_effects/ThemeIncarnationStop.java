package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.network.CodecBuilders;

public record ThemeIncarnationStop() {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "theme_incarnation_stop");

    public static void encode(ThemeIncarnationStop msg, FriendlyByteBuf buf) {
        CodecBuilders.encodeEmpty(msg, buf);
    }

    public static ThemeIncarnationStop decode(FriendlyByteBuf buf) {
        return CodecBuilders.decodeEmpty(buf, ThemeIncarnationStop::new);
    }
}
