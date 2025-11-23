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
    @Nullable private Music priorityMusicType; // Track the Music type for looping

    private int nextSongDelay = 100;
    private boolean isPriorityMusicPlaying = false;
    private int priorityMusicStartDelay = 5;
    private boolean priorityMusicLooping = false;
    private int priorityLoopDuration = 0;
    private int priorityLoopCountdown = 0;

    public AbyssMusicManager(Minecraft minecraft) {
        this.minecraft = minecraft;
        this.situationalMusic = new SituationalMusic(minecraft);
    }

    public void tick() {
        // Handle priority music first
        if (this.handlePriorityMusic()) {
            return;
        }

        // Ambient music selection logic
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

        // Decrement the grace delay if necessary
        if (priorityMusicStartDelay > 0) {
            priorityMusicStartDelay--;
            return true;
        }

        // Handle looping of the priority music
        if (priorityMusicLooping && priorityLoopCountdown > 0) {
            priorityLoopCountdown--;

            // If countdown reaches 0, restart the music
            if (priorityLoopCountdown == 0 && priorityMusicType != null) {
                // Fade out the old instance
                priorityMusic.startFadeOut();

                // Create and play a new instance without fade-in
                priorityMusic = TickableFadingSound.createNoFade(priorityMusicType, 1.0F);
                minecraft.getSoundManager().play(priorityMusic);

                // Reset the countdown
                priorityLoopCountdown = priorityLoopDuration;
                return true;
            }
        }

        // Check if priority music has finished
        if (priorityMusic.isStopped()) {
            System.out.println("Priority music ended");
            priorityMusic = null;
            priorityMusicType = null;
            isPriorityMusicPlaying = false;
            priorityMusicStartDelay = 0;
            priorityMusicLooping = false;
            priorityLoopDuration = 0;
            priorityLoopCountdown = 0;
            // Reset the delay for ambient music
            this.nextSongDelay = 100;
            return false;
        }

        return true;
    }

    private void updateActiveMusic(Music music) {
        if (this.currentMusic != null) {
            // If a new different music is requested
            if (!music.getEvent().value().getLocation().equals(this.currentMusic.getLocation())
                    && music.replaceCurrentMusic()) {
                this.stopPlayingWithFade();
                this.nextSongDelay = Mth.nextInt(this.random, 0, music.getMinDelay() / 2);
            }

            // Check if the current music has finished
            if (!this.minecraft.getSoundManager().isActive(this.currentMusic)
                    || this.currentMusic.isStopped()) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay,
                        Mth.nextInt(this.random, music.getMinDelay(), music.getMaxDelay()));
            }
        }

        // Start a new music if necessary
        this.nextSongDelay = Math.min(this.nextSongDelay, music.getMaxDelay());
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            this.startPlayingWithFade(music);
        }
    }

    public void startPlayingWithFade(Music music) {
        // Ensure no music is currently playing
        if (this.currentMusic != null) {
            this.stopPlayingWithFade();
            return;
        }

        // Create and play the new music with automatic fade-in
        this.currentMusic = TickableFadingSound.create(music);

        if (this.currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
            this.minecraft.getSoundManager().play(this.currentMusic);
        }

        this.nextSongDelay = Integer.MAX_VALUE;
    }

    public void stopPlayingWithFade() {
        if (this.currentMusic != null && !this.currentMusic.isStopped()) {
            this.currentMusic.startFadeOut();
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
     * Play a priority music that interrupts the current ambient music.
     *
     * @param music The sound event to play
     * @param looping If true, the music will be looped
     * @param loopDuration Duration in ticks before restarting the music (if looping = true)
     */
    public void playPriorityMusic(Music music, boolean looping, int loopDuration) {
        // Stop any current ambient music with fade-out
        if (this.currentMusic != null) {
            this.currentMusic.startFadeOut();
            this.currentMusic = null;
        }

        // Stop any previous priority music
        if (this.priorityMusic != null) {
            this.priorityMusic.startFadeOut();
        }

        // Create and start the new priority music
        this.priorityMusic = TickableFadingSound.create(music);
        this.priorityMusicType = music;
        this.isPriorityMusicPlaying = true;
        this.priorityMusicLooping = looping;
        this.priorityLoopDuration = loopDuration;
        this.priorityLoopCountdown = loopDuration;

        // Play the music
        this.minecraft.getSoundManager().play(this.priorityMusic);

        // Reset delays
        this.priorityMusicStartDelay = 5;
        this.nextSongDelay = Integer.MAX_VALUE;
    }

    /**
     * Stops the current priority music with a fade-out.
     */
    public void stopPriorityMusic() {
        if (this.priorityMusic != null && isPriorityMusicPlaying) {
            this.priorityMusic.startFadeOut();
            this.priorityMusicLooping = false; // Disable looping
            // The music will be cleaned up automatically in handlePriorityMusic()
        }
    }

    /**
     * Stops priority music immediately without fade-out.
     */
    public void stopPriorityMusicImmediate() {
        if (this.priorityMusic != null) {
            this.minecraft.getSoundManager().stop(this.priorityMusic);
            this.priorityMusic = null;
            this.priorityMusicType = null;
            this.isPriorityMusicPlaying = false;
            this.priorityMusicStartDelay = 0;
            this.priorityMusicLooping = false;
            this.priorityLoopDuration = 0;
            this.priorityLoopCountdown = 0;
            this.nextSongDelay = 100;
        }
    }

    /**
     * Checks if a priority music is currently playing.
     */
    public boolean isPriorityMusicPlaying() {
        return isPriorityMusicPlaying && priorityMusic != null && !priorityMusic.isStopped();
    }

    /**
     * Gets the current priority music.
     */
    @Nullable
    public TickableFadingSound getPriorityMusic() {
        return priorityMusic;
    }

    /**
     * Cleans up all resources.
     */
    public void cleanup() {
        stopPlayingImmediate();
        stopPriorityMusicImmediate();
    }
}