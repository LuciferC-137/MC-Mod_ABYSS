package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record IncarnationScreamSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "incarnation_scream_sound");

    public static void encode(IncarnationScreamSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static IncarnationScreamSound decode(FriendlyByteBuf buf) {
        return new IncarnationScreamSound(buf.readVector3f());
    }
}
