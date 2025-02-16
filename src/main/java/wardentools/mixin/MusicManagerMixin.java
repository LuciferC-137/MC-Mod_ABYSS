package wardentools.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.sounds.ModMusics;
import wardentools.worldgen.dimension.ModDimensions;

@Mixin(MusicManager.class)
public abstract class MusicManagerMixin {
    @Final @Shadow private Minecraft minecraft;
    @Shadow private SoundInstance currentMusic;
    @Shadow private int nextSongDelay;
    @Final @Shadow private RandomSource random;

    @Inject(method="tick", at=@At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo ci) {
        Music music = this.minecraft.getSituationalMusic();
        if (this.currentMusic != null
                && !(ModMusics.INCARNATION_THEME.getEvent().is(this.currentMusic.getLocation()))) {
            if (!music.getEvent().value().location().equals(this.currentMusic.getLocation())
                    && music.replaceCurrentMusic()) {
                if (!ModMusics.isAbyssMusic(music) || !ModMusics.isAbyssMusic(this.currentMusic)) {
                    this.minecraft.getSoundManager().stop(this.currentMusic);
                    this.nextSongDelay = Mth.nextInt(this.random, 0, music.getMinDelay() / 2);
                }
            }
            if (!this.minecraft.getSoundManager().isActive(this.currentMusic)) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay,
                        Mth.nextInt(this.random, music.getMinDelay(), music.getMaxDelay()));
            }
        }
        this.nextSongDelay = Math.min(this.nextSongDelay,
                Mth.nextInt(this.random, music.getMinDelay(), music.getMaxDelay()));
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            ((MusicManager)(Object)this).startPlaying(music);
        }
        if (this.nextSongDelay > ModMusics.TEN_MINUTES && this.minecraft.player != null
        && this.minecraft.player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
            this.nextSongDelay = ModMusics.TEN_MINUTES;
        }
        ci.cancel();
    }

    @Inject(method="startPlaying", at=@At("HEAD"))
    public void onStartPlaying(Music music, CallbackInfo ci) {
        ((MusicManager)(Object)this).stopPlaying();
    }
}
