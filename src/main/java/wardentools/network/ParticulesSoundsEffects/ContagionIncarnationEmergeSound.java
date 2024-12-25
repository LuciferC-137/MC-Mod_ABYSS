package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.sounds.ModSounds;

public class ContagionIncarnationEmergeSound {

    public ContagionIncarnationEmergeSound() {}

    public ContagionIncarnationEmergeSound(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(ContagionIncarnationEmergeSound msg) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            SoundEvent soundEvent = ModSounds.CONTAGION_INCARNATION_EMERGE.get();
            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(soundEvent, 4.0f, 1.0f)
            );
        }
    }
}
