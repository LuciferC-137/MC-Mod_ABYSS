package wardentools.sounds.music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.worldgen.dimension.ModDimensions;

import javax.annotation.Nullable;

/**
 * Utility class to control music in the Abyss dimension from anywhere in the code.
 */
@OnlyIn(Dist.CLIENT)
public class AbyssMusicHelper {

    /**
     * Retrieves the AbyssMusicManager instance from the MusicManager via the mixin.
     * This method uses the accessor created by the mixin.
     */
    @Nullable
    private static AbyssMusicManager getManager() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getMusicManager() instanceof AbyssMusicManagerAccessor accessor) {
            return accessor.abyssMusic$getCustomManager();
        }
        return null;
    }

    /**
     * Checks whether the player is currently in the Abyss dimension.
     */
    public static boolean isInAbyssDimension() {
        Minecraft mc = Minecraft.getInstance();
        return mc.player != null && mc.player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY;
    }

    /**
     * Plays a priority music with fade-in.
     * This music will interrupt ambient music and play until it ends.
     *
     * @param music The sound to play
     */
    public static void playPriorityMusic(Music music) {
        AbyssMusicManager manager = getManager();
        if (manager != null && isInAbyssDimension()) {
            manager.playPriorityMusic(music, false, 0);
        }
    }

    /**
     * Plays a looping priority music with fade-in.
     * This music will play indefinitely until stopped manually.
     *
     * @param music The music to play
     */
    public static void playPriorityMusicLooping(Music music, int loopDuration) {
        AbyssMusicManager manager = getManager();
        if (manager != null && isInAbyssDimension()) {
            manager.playPriorityMusic(music,true, loopDuration);
        }
    }

    /**
     * Stops the priority music with a fade-out.
     * Ambient music will resume automatically.
     */
    public static void stopPriorityMusic() {
        AbyssMusicManager manager = getManager();
        if (manager != null) {
            manager.stopPriorityMusic();
        }
    }

    /**
     * Stops the priority music immediately without fade-out.
     */
    public static void stopPriorityMusicImmediate() {
        AbyssMusicManager manager = getManager();
        if (manager != null) {
            manager.stopPriorityMusicImmediate();
        }
    }

    /**
     * Checks if a priority music is currently playing.
     */
    public static boolean isPriorityMusicPlaying() {
        AbyssMusicManager manager = getManager();
        return manager != null && manager.isPriorityMusicPlaying();
    }
}
