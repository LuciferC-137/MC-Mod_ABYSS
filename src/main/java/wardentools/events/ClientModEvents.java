package wardentools.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.block.CrystalInfuserBlock;
import wardentools.block.DarkGrassBlock;
import wardentools.entity.ModEntities;
import wardentools.entity.client.*;
import wardentools.items.CrystalResonatorItem;
import wardentools.items.ItemRegistry;
import wardentools.items.ModItemProperties;
import wardentools.weather.lightning.AbyssLightningRenderer;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(ModItemProperties::addCustomProperties);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.DEEPLURKER.get(), DeepLurkerRenderer::new);
		event.registerEntityRenderer(ModEntities.PALE_WANDERER.get(), PaleWandererRenderer::new);
		event.registerEntityRenderer(ModEntities.PROTECTOR.get(), ProtectorRenderer::new);
		event.registerEntityRenderer(ModEntities.CONTAGION_INCARNATION.get(), ContagionIncarnationRenderer::new);
		event.registerEntityRenderer(ModEntities.MOD_BOAT.get(),
				context -> new ModBoatRenderer(context, false));
		event.registerEntityRenderer(ModEntities.MOD_CHEST_BOAT.get(),
				context -> new ModBoatRenderer(context, true));
		event.registerEntityRenderer(ModEntities.TEMPER.get(), TemperRenderer::new);
		event.registerEntityRenderer(ModEntities.PARASYTE.get(), ParasyteRenderer::new);
		event.registerEntityRenderer(ModEntities.NOCTILURE.get(), NoctilureRenderer::new);
		event.registerEntityRenderer(ModEntities.SHADOW.get(), ShadowRenderer::new);
		event.registerEntityRenderer(ModEntities.CONTAGION_INCARNATION_CORPSE.get(),
				ContagionIncarnationCorpseRenderer::new);
		event.registerEntityRenderer(ModEntities.ABYSS_LIGHTNING.get(), AbyssLightningRenderer::new);
		event.registerEntityRenderer(ModEntities.CRYSTAL_GOLEM.get(), CrystalGolemRenderer::new);
		event.registerEntityRenderer(ModEntities.CRYSTAL_LASER.get(), CrystalLaserRenderer::new);
	}

	
	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepLurker.LAYER_LOCATION, DeepLurker::createBodyLayer);
		event.registerLayerDefinition(PaleWanderer.LAYER_LOCATION, PaleWanderer::createBodyLayer);
		event.registerLayerDefinition(Protector.LAYER_LOCATION, Protector::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnation.LAYER_LOCATION, ContagionIncarnation::createBodyLayer);
		event.registerLayerDefinition(ModBoat.DARKTREE_BOAT_LAYER, BoatModel::createBodyModel);
		event.registerLayerDefinition(ModBoat.DARKTREE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(ModBoat.WHITETREE_BOAT_LAYER, BoatModel::createBodyModel);
		event.registerLayerDefinition(ModBoat.WHITETREE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(Temper.LAYER_LOCATION, Temper::createBodyLayer);
		event.registerLayerDefinition(Parasyte.LAYER_LOCATION, Parasyte::createBodyLayer);
		event.registerLayerDefinition(Noctilure.LAYER_LOCATION, Noctilure::createBodyLayer);
		event.registerLayerDefinition(Shadow.LAYER_LOCATION, Shadow::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnationCorpse.LAYER_LOCATION,
				ContagionIncarnationCorpse::createBodyLayer);
		event.registerLayerDefinition(CrystalGolem.LAYER_LOCATION, CrystalGolem::createBodyLayer);
	}

	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register(CrystalInfuserBlock::getColor,
				BlockRegistry.CRYSTAL_INFUSER.get());
		event.register(DarkGrassBlock::getColor,
				BlockRegistry.DARKGRASS_BLOCK.get());

		ItemBlockRenderTypes.setRenderLayer(BlockRegistry.DARKGRASS_BLOCK.get(),
				RenderType.cutoutMipped());
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register(CrystalResonatorItem::getColor, ItemRegistry.CRYSTAL_RESONATOR.get());
	}
}
