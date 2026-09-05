package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record LivingSproutBurst(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "living_sprout_burst");

    public static void encode(LivingSproutBurst msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static LivingSproutBurst decode(FriendlyByteBuf buf) {
        return new LivingSproutBurst(buf.readVector3f());
    }
}
