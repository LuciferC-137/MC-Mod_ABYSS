package wardentools.items.clientutils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.sounds.ModSounds;

@OnlyIn(Dist.CLIENT)
public class WhistleSounds {
    private SoundInstance lastSoundInstance;
    private static final SoundEvent SOUND = ModSounds.WHISTLE.get();
    public static final WhistleSounds INSTANCE = new WhistleSounds();

    public void onPlay(Player player) {
        this.lastSoundInstance = new EntityBoundSoundInstance(SOUND, SoundSource.PLAYERS,
                0.8f, 1.0F, player, 1L);
        Minecraft.getInstance().getSoundManager().play(this.lastSoundInstance);
    }

    public boolean isSoundActive() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getSoundManager().isActive(this.lastSoundInstance);
    }
}
