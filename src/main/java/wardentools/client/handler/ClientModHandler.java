package wardentools.client.handler;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import wardentools.ModMain;
import wardentools.blockentity.renderer.DysfunctionningCatalystRenderer;
import wardentools.client.DysfunctionningCatalystScreen;
import wardentools.gui.MenuRegistry;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.renderer.AbyssPortalBlockRenderer;
import wardentools.blockentity.renderer.ProtectorInvokerRenderer;
import wardentools.blockentity.renderer.RadianceCatalystRenderer;
import wardentools.blockentity.renderer.ContagionIncarnationSkullRenderer;
import wardentools.blockentity.renderer.SoulSpawnerRenderer;
import wardentools.client.RadianceCatalystScreen;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {
	
	@SubscribeEvent
	public static void clientSetUp(RegisterMenuScreensEvent event) {
		event.register(MenuRegistry.RADIANCE_CATALYST_MENU.get(),
					RadianceCatalystScreen::new);
		event.register(MenuRegistry.DYSFUNCTIONNING_CATALYST_MENU.get(),
					DysfunctionningCatalystScreen::new);
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntityRegistry.RADIANCE_CATALYST_BLOCK_ENTITY.get(),
				RadianceCatalystRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.PROTECTOR_INVOKER_BLOCK_ENTITY.get(),
				ProtectorInvokerRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.ABYSS_PORTAL_BLOCK_ENTITY.get(),
				AbyssPortalBlockRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY.get(),
				DysfunctionningCatalystRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.CONTAGION_INCARNATION_SKULL_BLOCK_ENTITY.get(),
				ContagionIncarnationSkullRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.SOUL_SPAWNER_BLOCK_ENTITY.get(),
				SoulSpawnerRenderer::new);
	}

}
