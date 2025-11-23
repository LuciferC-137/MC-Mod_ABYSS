package wardentools.sounds.music;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface AbyssMusicManagerAccessor {
    AbyssMusicManager abyssMusic$getCustomManager();
}
