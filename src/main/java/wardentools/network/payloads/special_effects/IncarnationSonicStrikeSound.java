package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record IncarnationSonicStrikeSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "incarnation_sonic_strike_sound");

    public static void encode(IncarnationSonicStrikeSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static IncarnationSonicStrikeSound decode(FriendlyByteBuf buf) {
        return new IncarnationSonicStrikeSound(buf.readVector3f());
    }
}
