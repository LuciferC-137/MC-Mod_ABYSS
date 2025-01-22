package wardentools.items;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import wardentools.sounds.ModSounds;

public class ModJukeBoxSongsGenerator {
    public static final ResourceKey<JukeboxSong> ABYSS = create("abyss_theme");
    public static final ResourceKey<JukeboxSong> INCARNATION = create("incarnation_theme");
    public static final ResourceKey<JukeboxSong> DEEPFOREST = create("deepforest_music");
    public static final ResourceKey<JukeboxSong> WHITEFOREST = create("whiteforest_music");

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, ABYSS, ModSounds.getAbyssThemeMusicDisc(), 171, 1);
        register(context, INCARNATION, ModSounds.getIncarnationThemeMusicDisc(), 126, 2);
        register(context, DEEPFOREST, ModSounds.getDeepForestMusicDisc(), 121, 3);
        register(context, WHITEFOREST, ModSounds.getWhiteForestMusicDisc(), 103, 4);
    }

    private static void register(BootstrapContext<JukeboxSong> context,
                                 ResourceKey<JukeboxSong> song, Holder.Reference<SoundEvent> soundEventReference,
                                 int lengthInSeconds, int comparatorOutput) {
        context.register(song,
                new JukeboxSong(soundEventReference,
                        Component.translatable(
                                Util.makeDescriptionId("jukebox_song", song.location())),
                        (float)lengthInSeconds, comparatorOutput));
    }

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.withDefaultNamespace(name));
    }
}
