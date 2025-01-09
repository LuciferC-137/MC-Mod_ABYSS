package wardentools.weather;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.worldgen.dimension.ModDimensions;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class AbyssFogEvent {
	private static final int RADIUS_FOR_UNDER_SKY_CHECK = 15;

	@SubscribeEvent
	public static void onRenderFog(ViewportEvent.RenderFog event) {
		if (event.getCamera().getEntity() instanceof Player player) {
			if (player.level().dimension() == ModDimensions.ABYSS_LEVEL_KEY) {
				float fogDistance = isPlayerOutside(player) ?
						AbyssWeatherEvent.WEATHER_MANAGER.getFogDistance()
						: AbyssWeatherManager.MAX_FOG_DISTANCE;
				event.setFarPlaneDistance(fogDistance);
				event.setNearPlaneDistance(fogDistance * 0.5f);
				event.setCanceled(true);
			}
		}
	}

	public static boolean isPlayerOutside(Player player) {
		if (player.blockPosition().getY() < 30) return false;
		Level level = player.level();
		BlockPos playerPos = player.blockPosition();
		return underSky(level, playerPos.offset(RADIUS_FOR_UNDER_SKY_CHECK, 0, 0)) ||
				underSky(level, playerPos.offset(-RADIUS_FOR_UNDER_SKY_CHECK, 0, 0)) ||
				underSky(level, playerPos.offset(0, 0, RADIUS_FOR_UNDER_SKY_CHECK)) ||
				underSky(level, playerPos.offset(0, 0, -RADIUS_FOR_UNDER_SKY_CHECK));
	}

	public static boolean underSky(Level level, BlockPos pos){
		for (int y = pos.getY(); y < level.getMaxBuildHeight(); y++) {
			BlockPos posAbove = new BlockPos(pos.getX(), y, pos.getZ());
			if (level.getBlockState(posAbove).isCollisionShapeFullBlock(level, posAbove)) {
				return false;
			}
		}
		return true;
	}

}
