package wardentools.weather;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.network.ModPackets;
import wardentools.weather.network.RequestFogDistanceUpdateFromServer;

/**
This class allows to interpolate visually the fog during storms.
This avoids any strange behavior between inside and outside ambiances.
*/

@OnlyIn(Dist.CLIENT)
public class AbyssFogClientHandler {
    private static final float FOG_INTERPOLATION_SPEED = 0.05f; // per tick
    private float currentFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
    private float serverFogDistance = -1F;
    private int lastTime = 0;

    public void updateFogDistanceOnTick(Level level) {
        if (this.serverFogDistance == -1F) this.initializeFogDistance();
        LocalPlayer player = Minecraft.getInstance().player;
        float targetFogDistance;
        if (player != null) {
            targetFogDistance = AbyssFogEvent.isPlayerOutside(player) ?
                    this.serverFogDistance : AbyssWeatherManager.MAX_FOG_DISTANCE;
        } else {
            targetFogDistance = this.serverFogDistance;
        }
        if ((int) level.getGameTime() != this.lastTime) {
            this.currentFogDistance = targetFogDistance * FOG_INTERPOLATION_SPEED
                    + this.currentFogDistance * (1 - FOG_INTERPOLATION_SPEED);
            this.lastTime = (int) level.getGameTime();
        }
    }

    public void initializeFogDistance() {
        ModPackets.sendToServer(new RequestFogDistanceUpdateFromServer());
        this.serverFogDistance = AbyssWeatherManager.MAX_FOG_DISTANCE;
        // The line above is to prevent absurd fog distance if the server has too much delay to answer.
    }

    public float currentFogDistance() {
        return this.currentFogDistance;
    }

    public void setServerFogDistance(float serverFogDistance) {
        this.serverFogDistance = serverFogDistance;
    }
}
