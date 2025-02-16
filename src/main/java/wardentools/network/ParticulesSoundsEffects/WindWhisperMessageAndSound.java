package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.misc.wind.WhisperManager;

public class WindWhisperMessageAndSound {

    public WindWhisperMessageAndSound() {}

    public WindWhisperMessageAndSound(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(WindWhisperMessageAndSound msg) {
        if (Minecraft.getInstance().player == null) return;
        WhisperManager.sendRandomWhisperToPlayer(Minecraft.getInstance().player);
    }
}
