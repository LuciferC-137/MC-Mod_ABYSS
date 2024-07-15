package wardentools.client.handler;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import wardentools.ModMain;
import wardentools.GUI.MenuRegistry;
import wardentools.client.RadianceCatalystScreen;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {
	
	@SubscribeEvent
	public static void clientSetUp(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MenuRegistry.RADIANCE_CATALYST_MENU.get(), RadianceCatalystScreen::new);
		});
	}

}
