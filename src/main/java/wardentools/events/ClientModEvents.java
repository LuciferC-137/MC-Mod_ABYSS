package wardentools.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.entity.ModEntities;
import wardentools.entity.client.*;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.DEEPLURKER.get(), DeepLurkerRenderer::new);
		event.registerEntityRenderer(ModEntities.PALE_WANDERER.get(), PaleWandererRenderer::new);
		event.registerEntityRenderer(ModEntities.PROTECTOR.get(), ProtectorRenderer::new);
		event.registerEntityRenderer(ModEntities.CONTAGION_INCARNATION.get(), ContagionIncarnationRenderer::new);
		event.registerEntityRenderer(ModEntities.TEMPER.get(), TemperRenderer::new);
	}
	
	@SubscribeEvent
	public 	static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepLurker.LAYER_LOCATION, DeepLurker::createBodyLayer);
		event.registerLayerDefinition(PaleWanderer.LAYER_LOCATION, PaleWanderer::createBodyLayer);
		event.registerLayerDefinition(Protector.LAYER_LOCATION, Protector::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnation.LAYER_LOCATION, ContagionIncarnation::createBodyLayer);
		event.registerLayerDefinition(Temper.LAYER_LOCATION, Temper::createBodyLayer);
	}
}
