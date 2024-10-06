package wardentools.entity.utils;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.Level;

public class CustomFlyingPathNavigation extends FlyingPathNavigation {
    public CustomFlyingPathNavigation(Mob mob, Level level) {
        super(mob, level);
    }
}
