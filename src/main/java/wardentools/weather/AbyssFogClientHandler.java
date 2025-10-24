package wardentools.weather;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
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
    private float currentFogDistance = getMaxFogDistance();
    private boolean isStorming = false;
    private int lastTime = 0;
    private int lastUpdate = 0;
    private static final int UPDATE_INTERVAL = 20; // ticks

    public void updateFogDistanceOnTick(Level level) {
        if (this.lastUpdate == 0) {
            PacketHandler.sendToServer(new RequestFogDistanceUpdateFromServer());
            this.lastUpdate = UPDATE_INTERVAL;
        } else {
            this.lastUpdate--;
        }
        float targetFogDistance1;
        if (isStorming){
            targetFogDistance1 = AbyssWeatherManager.MIN_FOG_DISTANCE;
        } else{
            targetFogDistance1 = getMaxFogDistance();
        }
        LocalPlayer player = Minecraft.getInstance().player;
        float targetFogDistance;
        if (player != null) {
            targetFogDistance = AbyssFogEvent.isPlayerOutside(player) ?
                    targetFogDistance1 : getMaxFogDistance();
        } else {
            targetFogDistance = targetFogDistance1;
        }
        if ((int) level.getGameTime() != this.lastTime) {
            this.currentFogDistance = targetFogDistance * FOG_INTERPOLATION_SPEED
                    + this.currentFogDistance * (1 - FOG_INTERPOLATION_SPEED);
            this.lastTime = (int) level.getGameTime();
        }
    }

    public static float getMaxFogDistance() {
        return Minecraft.getInstance().options.renderDistance().get().floatValue() * 16f;
    }

    public float currentFogDistance() {
        return this.currentFogDistance;
    }

    public void setIsStorming(boolean isStorming) {
        this.isStorming = isStorming;
    }
}
