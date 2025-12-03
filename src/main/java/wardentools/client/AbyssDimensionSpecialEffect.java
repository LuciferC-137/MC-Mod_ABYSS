package wardentools.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import wardentools.client.rendering.LevelRendererUtils;
import wardentools.weather.AbyssFogClientHandler;
import wardentools.weather.AbyssWeatherEventClient;

public class AbyssDimensionSpecialEffect extends DimensionSpecialEffects {

    public AbyssDimensionSpecialEffect(float cloudHeight, boolean hasGround, SkyType fogType,
                                    boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudHeight, hasGround, fogType, forceBrightLightmap, constantAmbientLight);
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }

    @Override
    public float[] getSunriseColor(float timeOfDay, float partialTicks) {
        return new float[]{0F, 0F, 0F, 0F};
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(@NotNull Vec3 biomeFogColor, float daylight) {
        return biomeFogColor;
    }

    @Override
    public boolean renderSky(@NotNull ClientLevel level, int ticks, float partialTick,
                             @NotNull Matrix4f modelViewMatrix, @NotNull Camera camera,
                             @NotNull Matrix4f projectionMatrix, boolean isFoggy, @NotNull Runnable setupFog) {
        int brightness = (int)(230f * AbyssWeatherEventClient.CLIENT_WEATHER.currentFogDistance()
                / AbyssFogClientHandler.getMaxFogDistance());
        LevelRendererUtils.renderSky(level, modelViewMatrix, brightness);
        return true;
    }

    @Override
    public boolean tickRain(@NotNull ClientLevel level, int ticks, @NotNull Camera camera) {
        return super.tickRain(level, ticks, camera);
    }

    @Override
    public boolean renderClouds(@NotNull ClientLevel level, int ticks, float partialTick,
                                @NotNull PoseStack poseStack,
                                double camX, double camY, double camZ,
                                @NotNull Matrix4f modelViewMatrix, @NotNull Matrix4f projectionMatrix) {
        return true;
    }

    @Override
    public boolean renderSnowAndRain(@NotNull ClientLevel level, int ticks,
                                     float partialTick, @NotNull LightTexture lightTexture,
                                     double camX, double camY, double camZ) {
        return super.renderSnowAndRain(level, ticks, partialTick, lightTexture, camX, camY, camZ);
    }
}
