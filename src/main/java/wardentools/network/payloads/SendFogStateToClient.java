package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SendFogStateToClient(boolean isStorming) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "send_fog_state_to_client");

    public static void encode(SendFogStateToClient msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.isStorming());
    }

    public static SendFogStateToClient decode(FriendlyByteBuf buf) {
        return new SendFogStateToClient(buf.readBoolean());
    }
}
