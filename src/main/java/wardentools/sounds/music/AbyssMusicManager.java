package wardentools.sounds.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class AbyssMusicManager {
    private final RandomSource random = RandomSource.create();
    private final Minecraft minecraft;
    private final SituationalMusic situationalMusic;

    @Nullable
    private TickableFadingSound currentMusic;
    @Nullable
    private TickableFadingSound priorityMusic;

    private int nextSongDelay = 100;
    private boolean isPriorityMusicPlaying = false;
    private int priorityMusicStartDelay = 5;

    public AbyssMusicManager(Minecraft minecraft) {
        this.minecraft = minecraft;
        this.situationalMusic = new SituationalMusic(minecraft);
    }

    public void tick() {
        // Gérer la musique prioritaire d'abord
        if (this.handlePriorityMusic()) {
            return;
        }

        // Logique de sélection de musique d'ambiance
        Music music = this.situationalMusic.getAbyssSituationalMusic(this);
        if (music == null) {
            if (this.currentMusic != null) {
                this.stopPlayingWithFade();
            }
            this.nextSongDelay = 0;
            return;
        }

        this.updateActiveMusic(music);
    }

    private boolean handlePriorityMusic() {
        if (!isPriorityMusicPlaying || priorityMusic == null) {
            return false;
        }

        // Décrémenter le délai de grâce si nécessaire
        if (priorityMusicStartDelay > 0) {
            priorityMusicStartDelay--;
            return true;
        }

        // Vérifier si la musique prioritaire est terminée
        if (priorityMusic.isStopped()) {
            System.out.println("Priority music ended");
            priorityMusic = null;
            isPriorityMusicPlaying = false;
            priorityMusicStartDelay = 0;
            // Réinitialiser le délai pour la musique d'ambiance
            this.nextSongDelay = 100;
            return false;
        }

        // La musique prioritaire est toujours en cours
        return true;
    }

    private void updateActiveMusic(Music music) {
        if (this.currentMusic != null) {
            // Si une nouvelle musique différente est demandée
            if (!music.getEvent().value().getLocation().equals(this.currentMusic.getLocation())
                    && music.replaceCurrentMusic()) {
                this.stopPlayingWithFade();
                this.nextSongDelay = Mth.nextInt(this.random, 0, music.getMinDelay() / 2);
            }

            // Vérifier si la musique actuelle est terminée
            if (!this.minecraft.getSoundManager().isActive(this.currentMusic)
                    || this.currentMusic.isStopped()) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay,
                        Mth.nextInt(this.random, music.getMinDelay(), music.getMaxDelay()));
            }
        }

        // Démarrer une nouvelle musique si nécessaire
        this.nextSongDelay = Math.min(this.nextSongDelay, music.getMaxDelay());
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            this.startPlayingWithFade(music);
        }
    }

    public void startPlayingWithFade(Music music) {
        // S'assurer qu'aucune musique n'est en cours
        if (this.currentMusic != null) {
            this.stopPlayingWithFade();
            return;
        }

        // Créer et jouer la nouvelle musique avec fade-in automatique
        this.currentMusic = TickableFadingSound.create(music);

        if (this.currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
            this.minecraft.getSoundManager().play(this.currentMusic);
        }

        this.nextSongDelay = Integer.MAX_VALUE;
    }

    public void stopPlayingWithFade() {
        if (this.currentMusic != null && !this.currentMusic.isStopped()) {
            // Lancer le fade-out
            this.currentMusic.startFadeOut();
            // La musique sera automatiquement retirée quand le fade-out sera terminé
        }
    }

    public void stopPlayingImmediate() {
        if (this.currentMusic != null) {
            this.minecraft.getSoundManager().stop(this.currentMusic);
            this.currentMusic = null;
        }
        this.nextSongDelay += 100;
    }

    public boolean isPlayingMusic(Music music) {
        return this.currentMusic != null
                && !this.currentMusic.isStopped()
                && music.getEvent().value().getLocation().equals(this.currentMusic.getLocation());
    }

    @Nullable
    public TickableFadingSound getCurrentMusic() {
        return this.currentMusic;
    }

    public boolean isFading() {
        if (this.currentMusic != null) {
            TickableFadingSound.FadeState state = this.currentMusic.getFadeState();
            return state == TickableFadingSound.FadeState.FADING_IN
                    || state == TickableFadingSound.FadeState.FADING_OUT;
        }
        return false;
    }

    /**
     * Lance une musique prioritaire qui interrompt la musique d'ambiance actuelle.
     * Cette musique aura la priorité sur toutes les autres jusqu'à ce qu'elle se termine.
     *
     * @param music L'événement sonore à jouer
     * @param looping Si true, la musique sera jouée en boucle
     */
    public void playPriorityMusic(Music music, boolean looping) {
        // Arrêter toute musique d'ambiance en cours avec fade-out
        if (this.currentMusic != null) {
            this.currentMusic.startFadeOut();
            this.currentMusic = null;
        }

        // Arrêter toute musique prioritaire précédente
        if (this.priorityMusic != null) {
            this.priorityMusic.startFadeOut();
        }

        // Créer et lancer la nouvelle musique prioritaire
        if (looping) {
            this.priorityMusic = TickableFadingSound.createLooping(music);
        } else {
            this.priorityMusic = TickableFadingSound.create(music);
        }

        this.isPriorityMusicPlaying = true;

        // Jouer la musique
        this.minecraft.getSoundManager().play(this.priorityMusic);

        // Réinitialiser les délais
        this.priorityMusicStartDelay = 5;
        this.nextSongDelay = Integer.MAX_VALUE;
    }

    /**
     * Arrête la musique prioritaire en cours avec un fade-out.
     * La musique d'ambiance reprendra après.
     */
    public void stopPriorityMusic() {
        if (this.priorityMusic != null && isPriorityMusicPlaying) {
            this.priorityMusic.startFadeOut();
            // La musique sera nettoyée automatiquement dans handlePriorityMusic()
        }
    }

    /**
     * Arrête immédiatement la musique prioritaire sans fade-out.
     */
    public void stopPriorityMusicImmediate() {
        if (this.priorityMusic != null) {
            this.minecraft.getSoundManager().stop(this.priorityMusic);
            this.priorityMusic = null;
            this.isPriorityMusicPlaying = false;
            this.priorityMusicStartDelay = 0;
            this.nextSongDelay = 100; // Reprendre la musique d'ambiance rapidement
        }
    }

    /**
     * Vérifie si une musique prioritaire est en cours de lecture.
     */
    public boolean isPriorityMusicPlaying() {
        return isPriorityMusicPlaying && priorityMusic != null && !priorityMusic.isStopped();
    }

    /**
     * Récupère la musique prioritaire actuelle.
     */
    @Nullable
    public TickableFadingSound getPriorityMusic() {
        return priorityMusic;
    }

    /**
     * Nettoie toutes les ressources. À appeler lors de la fermeture.
     */
    public void cleanup() {
        stopPlayingImmediate();
        stopPriorityMusicImmediate();
    }
}