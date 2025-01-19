package wardentools.weather;

import net.minecraft.world.level.Level;

/**
This class allows to interpolate visually the fog during storms.
This avoids any strange behavior between inside and outside ambiances.
*/

public class AbyssFogClientHandler {
    private static final float FOG_INTERPOLATION_SPEED = 0.05f; // per tick
    private float currentFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
    private float targetFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
    private int lastTime = 0;

    public void updateFogDistanceOnTick(Level level) {
        if ((int) level.getGameTime() != this.lastTime) {
            this.currentFogDistance = this.targetFogDistance * FOG_INTERPOLATION_SPEED
                    + this.currentFogDistance * (1 - FOG_INTERPOLATION_SPEED);
            this.lastTime = (int) level.getGameTime();
        }
    }

    public float currentFogDistance() {
        return this.currentFogDistance;
    }

    public void setTargetFogDistance(float targetFogDistance) {
        this.targetFogDistance = targetFogDistance;
    }
}
