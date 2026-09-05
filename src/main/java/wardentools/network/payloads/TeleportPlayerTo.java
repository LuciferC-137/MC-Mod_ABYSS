package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record TeleportPlayerTo(Vector3f respawnPos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "teleport_player_to");

    public static void encode(TeleportPlayerTo msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.respawnPos());
    }

    public static TeleportPlayerTo decode(FriendlyByteBuf buf) {
        return new TeleportPlayerTo(buf.readVector3f());
    }
}
