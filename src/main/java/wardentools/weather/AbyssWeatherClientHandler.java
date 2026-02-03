package wardentools.weather;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import wardentools.AbyssConfig;

/**
This class allows to interpolate visually the fog during storms.
This avoids any strange behavior between inside and outside ambiances.
 It also handles the active weather event on the client side.
*/
@OnlyIn(Dist.CLIENT)
public class AbyssWeatherClientHandler {
    private static final float FOG_INTERPOLATION_SPEED = 0.05f; // per tick
    private float currentFogDistance = getMaxFogDistance();
    private int lastTime = 0;
    private int lastUpdate = 0;
    private static final int UPDATE_INTERVAL = 20; // ticks

    private WeatherEvent activeEvent = WeatherEvent.CLEAR;

    public void updateFogDistanceOnTick(Level level) {
        if (this.lastUpdate == 0) {
            this.lastUpdate = UPDATE_INTERVAL;
        } else {
            this.lastUpdate--;
        }
        float targetFogDistance1;
        if (this.activeEvent == WeatherEvent.STORM) {
            targetFogDistance1 = Math.min((float)AbyssConfig.CLIENT.ABYSS_FOG_STORM_INTENSITY.get(),
                    getMaxFogDistance());
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

    @SuppressWarnings("ConstantConditions")
    public static float getMaxFogDistance() {
        if (Minecraft.getInstance() == null || Minecraft.getInstance().options == null) {
            return 256f;
        }
        return Minecraft.getInstance().options.renderDistance().get().floatValue() * 16f;
    }

    public WeatherEvent getActiveEvent() {
        return this.activeEvent;
    }

    public void setActiveEvent(WeatherEvent activeEvent) {
        this.activeEvent = activeEvent;
    }

    public float currentFogDistance() {
        return this.currentFogDistance;
    }

}
