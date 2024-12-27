package wardentools.network.ParticulesSoundsEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.network.CustomPayloadEvent;
import wardentools.sounds.ModMusics;
import wardentools.sounds.ModSounds;

public class StartPlayingIncarnationTheme {

    public StartPlayingIncarnationTheme() {}

    public StartPlayingIncarnationTheme(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> handlePacket(this));
        context.setPacketHandled(true);
    }

    private static void handlePacket(StartPlayingIncarnationTheme msg) {
        Minecraft.getInstance().getMusicManager().startPlaying(ModMusics.INCARNATION_THEME);
    }
}
