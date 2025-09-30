package wardentools.entity.utils;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;

public class CrystalGolemNavigation extends GroundPathNavigation {

    private static final double CANDLE_DISTANCE = 0.5D;

    public CrystalGolemNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected void followThePath() {
        super.followThePath();

        // This stops the navigation so that the golem is actually
        // at the right position to light the candle, not too far, not too close.
        if (this.getTargetPos() != null) {
            if (this.mob.blockPosition().distSqr(this.getTargetPos())
                    <= CANDLE_DISTANCE * CANDLE_DISTANCE) {
                this.stop();
            }
        }
    }


}
