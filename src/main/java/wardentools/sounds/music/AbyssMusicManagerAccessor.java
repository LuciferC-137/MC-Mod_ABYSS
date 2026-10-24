package wardentools.sounds.music;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface AbyssMusicManagerAccessor {
    AbyssMusicManager abyssMusic$getCustomManager();
}
