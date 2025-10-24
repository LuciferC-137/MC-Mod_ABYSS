package wardentools.mixin;

import com.mojang.blaze3d.vertex.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.material.FogType;
import wardentools.weather.AbyssWeatherEvent;
import wardentools.weather.AbyssWeatherManager;
import wardentools.worldgen.dimension.ModDimensions;

import static wardentools.client.rendering.LevelRendererUtils.*;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Shadow private ClientLevel level;
	
	@Inject(method = "renderSky", at = @At("HEAD"))
	private void onRenderSky(Matrix4f pose, Matrix4f matrix,
			float time, Camera cam, boolean bool, Runnable runnable, CallbackInfo ci) {
        if (cam.getEntity().level().dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
        int BRIGHTNESS = (int)(230f * AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
						/ AbyssWeatherManager.MAX_FOG_DISTANCE);
		LevelRenderer levelRenderer = (LevelRenderer) (Object) this;
		if (level == null) return;
		if (level.effects().renderSky(level, levelRenderer.getTicks(),
				time, cam, pose, bool, runnable)) {
			return;
		}
		runnable.run();
	    FogType fogtype = cam.getFluidInCamera();
	    if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
	    	if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				renderSky(level, pose, BRIGHTNESS);
	    	}
	    }
    }

}
