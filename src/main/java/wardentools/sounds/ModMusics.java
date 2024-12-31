package wardentools.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

@SuppressWarnings("deprecation")
public class ModMusics {
    public static final int ONE_SECOND = 20;
    public static final int THIRTY_SECONDS = 600;
    public static final int TEN_MINUTES = 12000;
    public static final int TWENTY_MINUTES = 24000;
    public static final int FIVE_MINUTES = 6000;
    private static final ResourceKey<SoundEvent> DEEP_FOREST_KEY = ResourceKey.create(Registries.SOUND_EVENT,
            ModSounds.DEEP_FOREST.getId());
    private static final ResourceKey<SoundEvent> WHITE_FOREST_KEY = ResourceKey.create(Registries.SOUND_EVENT,
            ModSounds.WHITE_FOREST.getId());
    private static final ResourceKey<SoundEvent> INCARNATION_KEY = ResourceKey.create(Registries.SOUND_EVENT,
            ModSounds.INCARNATION_THEME.getId());
    private static final ResourceKey<SoundEvent> ABYSS_KEY = ResourceKey.create(Registries.SOUND_EVENT,
            ModSounds.ABYSS_THEME.getId());

    private static final Holder<SoundEvent> DEEP_FOREST_SOUND
            = BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(DEEP_FOREST_KEY);
    private static final Holder<SoundEvent> WHITE_FOREST_SOUND
            = BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(WHITE_FOREST_KEY);
    private static final Holder<SoundEvent> INCARNATION_THEME_SOUND
            = BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(INCARNATION_KEY);
    private static final Holder<SoundEvent> ABYSS_THEME_SOUND
            = BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(ABYSS_KEY);

    // Parameters = (SOUND_EVENT, minimum delay before playing again, maximum delay before playing again,
    // replace music)
    public static final Music DEEP_FOREST
            = new Music(DEEP_FOREST_SOUND, FIVE_MINUTES, TEN_MINUTES, true);
    public static final Music WHITE_FOREST
            = new Music(WHITE_FOREST_SOUND, FIVE_MINUTES, TEN_MINUTES, true);
    public static final Music INCARNATION_THEME
            = new Music(INCARNATION_THEME_SOUND, FIVE_MINUTES, TEN_MINUTES, true);
    public static final Music ABYSS_THEME
            = new Music(ABYSS_THEME_SOUND, ONE_SECOND, THIRTY_SECONDS / 2, true);
}
