package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record IncarnationEmergeSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "incarnation_emerge_sound");

    public static void encode(IncarnationEmergeSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static IncarnationEmergeSound decode(FriendlyByteBuf buf) {
        return new IncarnationEmergeSound(buf.readVector3f());
    }
}
