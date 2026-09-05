package wardentools.network.payloads.special_effects;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
import wardentools.ModMain;

public record AncientLaboratoryGateSound(Vector3f pos) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "ancient_laboratory_gate_sound");

    public static void encode(AncientLaboratoryGateSound msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.pos());
    }

    public static AncientLaboratoryGateSound decode(FriendlyByteBuf buf) {
        return new AncientLaboratoryGateSound(buf.readVector3f());
    }
}
