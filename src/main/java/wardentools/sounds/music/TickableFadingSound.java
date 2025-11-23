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
 * Son avec fade-in et fade-out automatiques utilisant l'API TickableSoundInstance.
 * Override getVolume() pour éviter le NPE avec this.sound qui est null avant resolve().
 */
@OnlyIn(Dist.CLIENT)
public class TickableFadingSound extends AbstractTickableSoundInstance {
    private static final int FADE_IN_DURATION = 40;  // 2 secondes
    private static final int FADE_OUT_DURATION = 40; // 2 secondes

    private final float targetVolume;
    private int fadeTicks = 0;
    private FadeState fadeState = FadeState.FADING_IN;

    public enum FadeState {
        FADING_IN,
        PLAYING,
        FADING_OUT
    }

    /**
     * Constructeur principal - utilise exactement le même pattern que SimpleSoundInstance.forMusic()
     */
    private TickableFadingSound(Music music, boolean looping, float targetVolume) {
        // Appel au constructeur parent avec les mêmes paramètres que SimpleSoundInstance
        super(music.getEvent().value(),
                SoundSource.MUSIC,
                SoundInstance.createUnseededRandom());
        System.out.println("Creating TickableFadingSound for music: " + music.getEvent().value().getLocation());
        // Configuration identique à SimpleSoundInstance.forMusic()
        this.volume = 0.0F;  // Démarre à 0 pour le fade-in
        this.pitch = 1.0F;
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.looping = looping;
        this.delay = 0;
        this.attenuation = SoundInstance.Attenuation.NONE;
        this.relative = true;

        this.targetVolume = targetVolume;
    }

    public static TickableFadingSound create(Music music) {
        return new TickableFadingSound(music, false, 1.0F);
    }

    public static TickableFadingSound createLooping(Music music) {
        return new TickableFadingSound(music, true, 1.0F);
    }

    public static TickableFadingSound createWithVolume(Music music, float volume) {
        return new TickableFadingSound(music, false, volume);
    }

    public static TickableFadingSound createLoopingWithVolume(Music music, float volume) {
        return new TickableFadingSound(music, true, volume);
    }

    @Override
    public void tick() {
        // Ne rien faire si déjà arrêté
        if (this.isStopped()) {
            return;
        }

        switch (fadeState) {
            case FADING_IN:
                fadeTicks++;
                System.out.println("Fading in tick " + fadeTicks);
                float fadeInProgress = Math.min(1.0F, (float) fadeTicks / FADE_IN_DURATION);
                this.volume = Mth.lerp(fadeInProgress, 0.0F, targetVolume);

                if (fadeTicks >= FADE_IN_DURATION) {
                    fadeState = FadeState.PLAYING;
                    fadeTicks = 0;
                }
                break;

            case PLAYING:
                System.out.println("Sound playing at full volume");
                this.volume = targetVolume;
                break;

            case FADING_OUT:
                fadeTicks++;
                System.out.println("Fading out tick " + fadeTicks);
                float fadeOutProgress = Math.min(1.0F, (float) fadeTicks / FADE_OUT_DURATION);
                this.volume = Mth.lerp(fadeOutProgress, targetVolume, 0.0F);

                if (fadeTicks >= FADE_OUT_DURATION) {
                    this.stop(); // Marque le son comme stoppé
                }
                break;
        }
    }

    /**
     * Lance le fade-out. Le son s'arrêtera automatiquement à la fin.
     */
    public void startFadeOut() {
        if (fadeState != FadeState.FADING_OUT && !this.isStopped()) {
            fadeState = FadeState.FADING_OUT;
            fadeTicks = 0;
        }
    }

    /**
     * Vérifie si le fade-out est complètement terminé.
     */
    public boolean isFinished() {
        return fadeState == FadeState.FADING_OUT && fadeTicks >= FADE_OUT_DURATION;
    }

    public FadeState getFadeState() {
        return fadeState;
    }

    @Override
    public boolean canPlaySound() {
        // Toujours true sauf si explicitement stoppé
        return !this.isStopped();
    }

    /**
     * CRITIQUE: Permet au son de démarrer avec volume = 0 pour le fade-in.
     * Sans ça, le SoundEngine ignore le son car il détecte volume = 0.
     */
    @Override
    public boolean canStartSilent() {
        return true;
    }

    /**
     * CRITIQUE: Override pour éviter le NPE quand this.sound est null.
     * Le SoundEngine appelle cette méthode avant que resolve() soit appelé.
     */
    @Override
    public float getVolume() {
        // Si sound n'est pas encore résolu, retourner juste le volume de base
        Sound sound = this.getSound();
        if (sound == null) {
            return this.volume;
        }
        // Sinon, comportement normal: volume * sound.getVolume()
        return this.volume * sound.getVolume().sample(this.random);
    }

    /**
     * CRITIQUE: Override pour éviter le NPE quand this.sound est null (même problème que getVolume).
     */
    @Override
    public float getPitch() {
        Sound sound = this.getSound();
        if (sound == null) {
            return this.pitch;
        }
        return this.pitch * sound.getPitch().sample(this.random);
    }
}