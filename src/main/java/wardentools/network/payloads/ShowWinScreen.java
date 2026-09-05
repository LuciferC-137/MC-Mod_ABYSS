package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ShowWinScreen(Vector3f respawnPos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "show_win_screen");

    public static void encode(ShowWinScreen msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.respawnPos());
    }

    public static ShowWinScreen decode(FriendlyByteBuf buf) {
        return new ShowWinScreen(buf.readVector3f());
    }
}
