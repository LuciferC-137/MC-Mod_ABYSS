package wardentools.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;

public record SwitchAchievement(int index) {

    public static final ResourceLocation ID =
            new ResourceLocation(ModMain.MOD_ID, "switch_achievement");

    public static void encode(SwitchAchievement msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.index());
    }

    public static SwitchAchievement decode(FriendlyByteBuf buf) {
        return new SwitchAchievement(buf.readInt());
    }
}
