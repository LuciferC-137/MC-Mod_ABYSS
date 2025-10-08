package wardentools.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.block.CrystalInfuserBlock;
import wardentools.block.DarkGrassBlock;
import wardentools.entity.ModEntities;
import wardentools.entity.client.*;
import wardentools.entity.custom.ModBoatEntity;
import wardentools.entity.custom.ModChestBoatEntity;
import wardentools.items.CrystalResonatorItem;
import wardentools.items.ItemRegistry;
import wardentools.items.ModItemProperties;
import wardentools.weather.lightning.AbyssLightningRenderer;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
		event.registerLayerDefinition(ModBoatEntity.DARKTREE_BOAT_LAYER, BoatModel::createBodyModel);
		event.registerLayerDefinition(ModChestBoatEntity.DARKTREE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(ModBoatEntity.WHITETREE_BOAT_LAYER, BoatModel::createBodyModel);
		event.registerLayerDefinition(ModChestBoatEntity.WHITETREE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
		event.registerLayerDefinition(Temper.LAYER_LOCATION, Temper::createBodyLayer);
		event.registerLayerDefinition(Parasyte.LAYER_LOCATION, Parasyte::createBodyLayer);
		event.registerLayerDefinition(Noctilure.LAYER_LOCATION, Noctilure::createBodyLayer);
		event.registerLayerDefinition(Shadow.LAYER_LOCATION, Shadow::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnationCorpse.LAYER_LOCATION,
				ContagionIncarnationCorpse::createBodyLayer);
		event.registerLayerDefinition(CrystalGolem.LAYER_LOCATION, CrystalGolem::createBodyLayer);
	}

	@SubscribeEvent
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
