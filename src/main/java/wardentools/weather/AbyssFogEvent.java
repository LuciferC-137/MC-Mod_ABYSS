package wardentools.weather;


import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.fluid.ModFluidTypes;
import wardentools.worldgen.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssFogEvent {
	private static final int RADIUS_FOR_UNDER_SKY_CHECK = 15;
	private static final AbyssFogClientHandler CLIENT_HANDLER = new AbyssFogClientHandler();

	@SubscribeEvent
	public static void onRenderFog(ViewportEvent.RenderFog event) {
		if (event.getCamera().getEntity() instanceof Player player) {
			if (player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				if (event.getCamera().getFluidInCamera() == FogType.LAVA
						|| event.getCamera().getFluidInCamera() == FogType.POWDER_SNOW
						|| event.getCamera().getFluidInCamera() == FogType.WATER
						|| isInLiquidCorruption(event.getCamera())) {
					return;
				}
				CLIENT_HANDLER.updateFogDistanceOnTick(player.level());
				CLIENT_HANDLER.setTargetFogDistance(isPlayerOutside(player) ?
						AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
						: AbyssWeatherManager.MAX_FOG_DISTANCE);
				event.setFarPlaneDistance(CLIENT_HANDLER.currentFogDistance());
				event.setNearPlaneDistance(CLIENT_HANDLER.currentFogDistance() * 0.5f);
				event.setCanceled(true);
			}
		}
	}

	private static boolean isInLiquidCorruption(Camera cam) {
		FluidState fluidstate = cam.getEntity().level().getFluidState(cam.getEntity().blockPosition());
		BlockPos blockpos = cam.getEntity().blockPosition();
		Vec3 pos = cam.getPosition();
		return (fluidstate.getFluidType() == ModFluidTypes.LIQUID_CORRUPTION.get())
				&& pos.y < (double)((float)(blockpos.getY() + 1)
					+ fluidstate.getHeight(cam.getEntity().level(), blockpos));
		}

	public static boolean isPlayerOutside(Player player) {
		if (player.blockPosition().getY() < 30) return false;
		Level level = player.level();
		BlockPos playerPos = player.blockPosition();
		return underSkySquareRadiusCheck(player, RADIUS_FOR_UNDER_SKY_CHECK) ||
			   underSkySquareRadiusCheck(player, RADIUS_FOR_UNDER_SKY_CHECK / 2) ||
			   underOpenSky(level, playerPos);
	}

	public static boolean underSkySquareRadiusCheck(Player player, int radius) {
		Level level = player.level();
		BlockPos playerPos = player.blockPosition();
		return underOpenSky(level, playerPos.offset(radius, 0, 0)) ||
				underOpenSky(level, playerPos.offset(-radius, 0, 0)) ||
				underOpenSky(level, playerPos.offset(0, 0, radius)) ||
				underOpenSky(level, playerPos.offset(0, 0, -radius));
	}

	public static boolean underOpenSky(Level level, BlockPos pos){
		for (int y = pos.getY(); y < Math.min(level.getMaxY(), pos.getY() + 40); y++) {
			BlockPos posAbove = new BlockPos(pos.getX(), y, pos.getZ());
			if (level.getBlockState(posAbove).isCollisionShapeFullBlock(level, posAbove)) {
				return false;
			}
		}
		return true;
	}

}
