package wardentools.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.entity.client.DeepLurker;
import wardentools.entity.client.DeepLurkerRenderer;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.DEEPLURKER.get(), DeepLurkerRenderer::new);
	}
	
	@SubscribeEvent
	public 	static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepLurker.LAYER_LOCATION, DeepLurker::createBodyLayer);
	}
}
