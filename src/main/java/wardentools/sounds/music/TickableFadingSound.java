package wardentools.sounds.music;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Sound with automatic fade-in and fade-out using the TickableSoundInstance API.
 * Simple implementation without internal looping - looping should be managed externally.
 */
@OnlyIn(Dist.CLIENT)
public class TickableFadingSound extends AbstractTickableSoundInstance {
    private static final int FADE_IN_DURATION = 40;  // 2 seconds
    private static final int FADE_OUT_DURATION = 40; // 2 seconds

    private final float targetVolume;
    private int fadeTicks = 0;
    private FadeState fadeState = FadeState.FADING_IN;

    public enum FadeState {
        FADING_IN,
        PLAYING,
        FADING_OUT
    }

    public TickableFadingSound(Music music, boolean doFadeIn, float targetVolume) {
        super(music.getEvent().value(),
                SoundSource.MUSIC,
                SoundInstance.createUnseededRandom());

        this.volume = doFadeIn ? 0.0F : targetVolume;
        this.pitch = 1.0F;
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.delay = 0;
        this.attenuation = SoundInstance.Attenuation.NONE;
        this.relative = true;
        this.looping = false; // Never use native looping for fading sounds

        this.targetVolume = targetVolume;

        if (!doFadeIn) {
            this.fadeState = FadeState.PLAYING;
        }
    }

    public static TickableFadingSound create(Music music) {
        return new TickableFadingSound(music, true, 1.0F);
    }

    public static TickableFadingSound createWithVolume(Music music, float volume) {
        return new TickableFadingSound(music, true, volume);
    }

    public static TickableFadingSound createNoFade(Music music, float volume) {
        return new TickableFadingSound(music, false, volume);
    }

    @Override
    public void tick() {
        if (this.isStopped()) {
            return;
        }

        switch (fadeState) {
            case FADING_IN:
                fadeTicks++;
                float fadeInProgress = Math.min(1.0F, (float) fadeTicks / FADE_IN_DURATION);
                this.volume = Mth.lerp(fadeInProgress, 0.0F, targetVolume);

                if (fadeTicks >= FADE_IN_DURATION) {
                    fadeState = FadeState.PLAYING;
                    fadeTicks = 0;
                }
                break;

            case PLAYING:
                this.volume = targetVolume;
                break;

            case FADING_OUT:
                fadeTicks++;
                float fadeOutProgress = Math.min(1.0F, (float) fadeTicks / FADE_OUT_DURATION);
                this.volume = Mth.lerp(fadeOutProgress, targetVolume, 0.0F);

                if (fadeTicks >= FADE_OUT_DURATION) {
                    this.stop();
                }
                break;
        }
    }

    public void startFadeOut() {
        if (fadeState != FadeState.FADING_OUT && !this.isStopped()) {
            fadeState = FadeState.FADING_OUT;
            fadeTicks = 0;
        }
    }

    public boolean isFinished() {
        return fadeState == FadeState.FADING_OUT && fadeTicks >= FADE_OUT_DURATION;
    }

    public FadeState getFadeState() {
        return fadeState;
    }

    @Override
    public boolean canPlaySound() {
        return !this.isStopped();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public float getVolume() {
        Sound sound = this.getSound();
        if (sound == null) {
            return this.volume;
        }
        return this.volume * sound.getVolume().sample(this.random);
    }

    @Override
    public float getPitch() {
        Sound sound = this.getSound();
        if (sound == null) {
            return this.pitch;
        }
        return this.pitch * sound.getPitch().sample(this.random);
    }
}