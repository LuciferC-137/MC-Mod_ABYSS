package wardentools.network;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;

public class SwitchAchievement {
    private static final ResourceLocation CORRUPTION_ADVANCEMENT
            = new ResourceLocation(ModMain.MOD_ID, "corruption_vessel");
    private static final ResourceLocation RADIANCE_ADVANCEMENT
            = new ResourceLocation(ModMain.MOD_ID, "radiance_bringer");
    private final int index; // 0 gives the radiance bringer, 1 gives the corruption vessel

    public SwitchAchievement(int index) {
        this.index = index;
    }

    public SwitchAchievement(FriendlyByteBuf buffer) {
        this.index = buffer.readInt();
    }

    public static void encode(SwitchAchievement msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.index);
    }

    public static SwitchAchievement decode(FriendlyByteBuf buffer) {
        return new SwitchAchievement(buffer.readInt());
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this, context.getSender()));
        context.setPacketHandled(true);
    }

    public static void handlePacket(SwitchAchievement msg, ServerPlayer serverPlayer) {
        if (serverPlayer != null) {
            if (msg.index == 0) {
                AdvancementHolder corruptionAdvancement = serverPlayer.server.getAdvancements()
                        .get(CORRUPTION_ADVANCEMENT);
                if (corruptionAdvancement != null) {
                    for (String criterion : corruptionAdvancement.value().criteria().keySet()) {
                        serverPlayer.getAdvancements().revoke(corruptionAdvancement, criterion);
                    }
                }
                ModCriteriaTriggers.RADIANCE_BRINGER.trigger(serverPlayer);
            }
            if (msg.index == 1) {
                AdvancementHolder radianceAdvancement = serverPlayer.server.getAdvancements()
                        .get(RADIANCE_ADVANCEMENT);
                if (radianceAdvancement != null) {
                    for (String criterion : radianceAdvancement.value().criteria().keySet()) {
                        serverPlayer.getAdvancements().revoke(radianceAdvancement, criterion);
                    }
                }
                ModCriteriaTriggers.CORRUPTION_VESSEL.trigger(serverPlayer);
            }
        }
    }
}
