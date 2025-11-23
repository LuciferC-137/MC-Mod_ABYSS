package wardentools.sounds.music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.worldgen.dimension.ModDimensions;

import javax.annotation.Nullable;

/**
 * Classe utilitaire pour contrôler la musique dans la dimension Abyss depuis n'importe où dans le code.
 */
@OnlyIn(Dist.CLIENT)
public class AbyssMusicHelper {

    /**
     * Récupère l'instance d'AbyssMusicManager depuis le MusicManager via le mixin.
     * Cette méthode utilise l'accessor créé par le mixin.
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
     * Vérifie si le joueur est actuellement dans la dimension Abyss.
     */
    public static boolean isInAbyssDimension() {
        Minecraft mc = Minecraft.getInstance();
        return mc.player != null && mc.player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY;
    }

    /**
     * Lance une musique prioritaire avec fade-in.
     * Cette musique interrompra la musique d'ambiance et jouera jusqu'à sa fin.
     *
     * @param music Le son à jouer
     */
    public static void playPriorityMusic(Music music) {
        AbyssMusicManager manager = getManager();
        if (manager != null && isInAbyssDimension()) {
            manager.playPriorityMusic(music, false);
        }
    }

    /**
     * Lance une musique prioritaire en boucle avec fade-in.
     * Cette musique jouera indéfiniment jusqu'à ce qu'elle soit arrêtée manuellement.
     *
     * @param music La musique à jouer
     */
    public static void playPriorityMusicLooping(Music music) {
        AbyssMusicManager manager = getManager();
        if (manager != null && isInAbyssDimension()) {
            manager.playPriorityMusic(music,true);
        }
    }

    /**
     * Arrête la musique prioritaire avec un fade-out.
     * La musique d'ambiance reprendra automatiquement.
     */
    public static void stopPriorityMusic() {
        AbyssMusicManager manager = getManager();
        if (manager != null) {
            manager.stopPriorityMusic();
        }
    }

    /**
     * Arrête immédiatement la musique prioritaire sans fade-out.
     */
    public static void stopPriorityMusicImmediate() {
        AbyssMusicManager manager = getManager();
        if (manager != null) {
            manager.stopPriorityMusicImmediate();
        }
    }

    /**
     * Vérifie si une musique prioritaire est en cours de lecture.
     */
    public static boolean isPriorityMusicPlaying() {
        AbyssMusicManager manager = getManager();
        return manager != null && manager.isPriorityMusicPlaying();
    }
}


