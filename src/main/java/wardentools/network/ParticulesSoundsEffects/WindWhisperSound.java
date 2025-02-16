package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.sounds.ModSounds;

public class WindWhisperSound {

    public WindWhisperSound() {}

    public WindWhisperSound(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(WindWhisperSound msg) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        player.playSound(ModSounds.WIND_WHISPERS.get(), 5f,
                (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);

    }
}
