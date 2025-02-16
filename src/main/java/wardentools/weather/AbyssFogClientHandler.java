package wardentools.weather;

import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wardentools.network.PacketHandler;
import wardentools.weather.network.RequestFogDistanceUpdateFromServer;

/**
This class allows to interpolate visually the fog during storms.
This avoids any strange behavior between inside and outside ambiances.
*/

@OnlyIn(Dist.CLIENT)
public class AbyssFogClientHandler {
    private static final float FOG_INTERPOLATION_SPEED = 0.05f; // per tick
    private float currentFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
    private float targetFogDistance = -1F;
    private int lastTime = 0;

    public void updateFogDistanceOnTick(Level level) {
        if (this.targetFogDistance == -1F) this.initializeFogDistance();
        if ((int) level.getGameTime() != this.lastTime) {
            this.currentFogDistance = this.targetFogDistance * FOG_INTERPOLATION_SPEED
                    + this.currentFogDistance * (1 - FOG_INTERPOLATION_SPEED);
            this.lastTime = (int) level.getGameTime();
        }
    }

    public void initializeFogDistance() {
        PacketHandler.sendToServer(new RequestFogDistanceUpdateFromServer());
        this.targetFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
        // The line above is to prevent absurd fog distance if the server has too much delay to answer.
    }

    public float currentFogDistance() {
        return this.currentFogDistance;
    }

    public void setTargetFogDistance(float targetFogDistance) {
        this.targetFogDistance = targetFogDistance;
    }
}
