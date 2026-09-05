package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record ProtectorHeartSynchronize(Vector3f pos, float health) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "protector_heart_sync");

    public static void encode(ProtectorHeartSynchronize msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
        buf.writeFloat(msg.health());
    }

    public static ProtectorHeartSynchronize decode(FriendlyByteBuf buf) {
        return new ProtectorHeartSynchronize(buf.readVector3f(), buf.readFloat());
    }
}
