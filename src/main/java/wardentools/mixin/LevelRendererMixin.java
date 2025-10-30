package wardentools.mixin;

import com.mojang.blaze3d.vertex.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
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
import wardentools.weather.AbyssFogClientHandler;
import wardentools.weather.AbyssWeatherEventClient;
import wardentools.worldgen.dimension.ModDimensions;

import static wardentools.client.rendering.LevelRendererUtils.*;

@OnlyIn(Dist.CLIENT)
@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Shadow private ClientLevel level;
    @Shadow private int ticks;
	
	@Inject(method = "renderSky", at = @At("HEAD"))
	private void onRenderSky(Matrix4f frustumMatrix, Matrix4f proj,
			float partialTick, Camera cam, boolean isFoggy, Runnable skyFogSetup, CallbackInfo ci) {
        if (cam.getEntity().level().dimension() != ModDimensions.ABYSS_LEVEL_KEY) return;
        int BRIGHTNESS = (int)(230f * AbyssWeatherEventClient.CLIENT_WEATHER.currentFogDistance()
                / AbyssFogClientHandler.getMaxFogDistance());
		if (level == null) return;
		if (this.level.effects().renderSky(this.level, this.ticks, partialTick,
                frustumMatrix, cam, proj, isFoggy, skyFogSetup)) {
			return;
		}
		skyFogSetup.run();
	    FogType fogtype = cam.getFluidInCamera();
	    if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
	    	if (level.dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				renderSky(level, frustumMatrix, BRIGHTNESS);
	    	}
	    }
    }

}
