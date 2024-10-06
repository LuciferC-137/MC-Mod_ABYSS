package wardentools.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

@SuppressWarnings("deprecation")
public class ModMusics {
    private static final ResourceKey<SoundEvent> DEEP_FOREST_KEY = ResourceKey.create(Registries.SOUND_EVENT,
            ModSounds.DEEP_FOREST.getId());
    private static final Holder<SoundEvent> DEEP_FOREST_SOUND
            = BuiltInRegistries.SOUND_EVENT.getHolderOrThrow(DEEP_FOREST_KEY);
    public static final Music DEEP_FOREST
            = new Music(DEEP_FOREST_SOUND, 6000, 24000, true);
}
