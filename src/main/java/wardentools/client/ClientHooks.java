package wardentools.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {
	public static void openRadianceCatalystScreen(BlockPos pos) {
		Minecraft.getInstance().setScreen(new RadianceCatalystScreen(pos));
	}
}
