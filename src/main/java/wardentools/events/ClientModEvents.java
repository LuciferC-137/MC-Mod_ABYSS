package wardentools.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.client.model.*;
import wardentools.entity.client.renderer.*;
import wardentools.entity.custom.DarktreeBoat;
import wardentools.entity.custom.DarktreeChestBoat;
import wardentools.entity.custom.WhitetreeBoat;
import wardentools.entity.custom.WhitetreeChestBoat;
import wardentools.items.BoatItem;
import wardentools.items.ModItemProperties;
import wardentools.weather.lightning.AbyssLightningRenderer;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(ModItemProperties::addCustomProperties);
		event.enqueueWork(ClientModEvents::registerBlockRenderTypes);
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.DEEPLURKER.get(), DeepLurkerRenderer::new);
		event.registerEntityRenderer(ModEntities.PALE_WANDERER.get(), PaleWandererRenderer::new);
		event.registerEntityRenderer(ModEntities.PROTECTOR.get(), ProtectorRenderer::new);
		event.registerEntityRenderer(ModEntities.CONTAGION_INCARNATION.get(), ContagionIncarnationRenderer::new);
		event.registerEntityRenderer(ModEntities.DARKTREE_BOAT.get(),
				context -> new ModBoatRenderer(context, BoatItem.Type.DARKTREE_BOAT));
		event.registerEntityRenderer(ModEntities.DARKTREE_CHEST_BOAT.get(),
				context -> new ModBoatRenderer(context, BoatItem.Type.DARKTREE_CHEST_BOAT));
		event.registerEntityRenderer(ModEntities.WHITETREE_BOAT.get(),
				context -> new ModBoatRenderer(context, BoatItem.Type.WHITETREE_BOAT));
		event.registerEntityRenderer(ModEntities.WHITETREE_CHEST_BOAT.get(),
				context -> new ModBoatRenderer(context, BoatItem.Type.WHITETREE_CHEST_BOAT));
		event.registerEntityRenderer(ModEntities.TEMPER.get(), TemperRenderer::new);
		event.registerEntityRenderer(ModEntities.PARASYTE.get(), ParasyteRenderer::new);
		event.registerEntityRenderer(ModEntities.NOCTILURE.get(), NoctilureRenderer::new);
		event.registerEntityRenderer(ModEntities.SHADOW.get(), ShadowRenderer::new);
		event.registerEntityRenderer(ModEntities.CONTAGION_INCARNATION_CORPSE.get(),
				ContagionIncarnationCorpseRenderer::new);
		event.registerEntityRenderer(ModEntities.ABYSS_LIGHTNING.get(), AbyssLightningRenderer::new);
	}

	
	@SubscribeEvent
	public 	static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(DeepLurker.LAYER_LOCATION, DeepLurker::createBodyLayer);
		event.registerLayerDefinition(PaleWanderer.LAYER_LOCATION, PaleWanderer::createBodyLayer);
		event.registerLayerDefinition(Protector.LAYER_LOCATION, Protector::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnation.LAYER_LOCATION, ContagionIncarnation::createBodyLayer);
		event.registerLayerDefinition(DarktreeBoat.DARKTREE_BOAT_LAYER, BoatModel::createBoatModel);
		event.registerLayerDefinition(DarktreeChestBoat.DARKTREE_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(WhitetreeBoat.WHITETREE_BOAT_LAYER, BoatModel::createBoatModel);
		event.registerLayerDefinition(WhitetreeChestBoat.WHITETREE_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(Temper.LAYER_LOCATION, Temper::createBodyLayer);
		event.registerLayerDefinition(Parasyte.LAYER_LOCATION, Parasyte::createBodyLayer);
		event.registerLayerDefinition(Noctilure.LAYER_LOCATION, Noctilure::createBodyLayer);
		event.registerLayerDefinition(Shadow.LAYER_LOCATION, Shadow::createBodyLayer);
		event.registerLayerDefinition(ContagionIncarnationCorpse.LAYER_LOCATION,
				ContagionIncarnationCorpse::createBodyLayer);
	}

	public static void registerBlockRenderTypes() {
		cutout(BlockRegistry.SOUL_SPAWNER);
		cutout(BlockRegistry.PROTECTOR_INVOKER);
		cutout(BlockRegistry.RADIANCE_CATALYST);
		cutout(BlockRegistry.WIND_WHISPERER);
		cutout(BlockRegistry.DARKTREE_LEAVES);
		cutout(BlockRegistry.WHITETREE_LEAVES);
		cutout(BlockRegistry.DARKTREE_SAPLING);
		cutout(BlockRegistry.WHITETREE_SAPLING);
		cutout(BlockRegistry.DARK_GRASS);
		cutout(BlockRegistry.TALL_DARK_GRASS);
		cutout(BlockRegistry.WHITE_GRASS);
		cutout(BlockRegistry.TALL_WHITE_GRASS);
		cutout(BlockRegistry.WHITE_TORCHFLOWER);
		cutout(BlockRegistry.DEEPFLOWER);
		cutout(BlockRegistry.BLUE_BUSH);
		cutout(BlockRegistry.POTTED_WHITE_TORCHFLOWER);
		cutout(BlockRegistry.DARKTREE_DOOR);
		cutout(BlockRegistry.WHITETREE_DOOR);
		cutout(BlockRegistry.DARKTREE_TRAPDOOR);
		cutout(BlockRegistry.WHITETREE_TRAPDOOR);
		cutout(BlockRegistry.PALE_CRISTAL);
		cutout(BlockRegistry.ECHO_CRISTAL);
		cutout(BlockRegistry.CITRINE);
		cutout(BlockRegistry.MALACHITE);
		cutout(BlockRegistry.RUBY);
		cutout(BlockRegistry.RADIANCE_CRISTAL);
		cutout(BlockRegistry.DEEP_CRISTAL);

		translucent(BlockRegistry.DYSFUNCTIONNING_CATALYST);
		translucent(BlockRegistry.REINFORCED_GLASS);
	}

	@SuppressWarnings("deprecation")
	public static void cutout(RegistryObject<? extends Block> block) {
		ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
	}

	@SuppressWarnings("deprecation")
	public static void translucent(RegistryObject<? extends Block> block) {
		ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.translucent());
	}
}
