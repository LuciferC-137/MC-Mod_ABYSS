package wardentools.events;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import wardentools.ModMain;
import wardentools.block.BlockRegistry;
import wardentools.block.CrystalInfuserBlock;
import wardentools.block.DarkGrassBlock;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.renderer.*;
import wardentools.client.AbyssDimensionSpecialEffect;
import wardentools.client.DysfunctionningCatalystScreen;
import wardentools.client.RadianceCatalystScreen;
import wardentools.entity.ModEntities;
import wardentools.entity.client.*;
import wardentools.gui.MenuRegistry;
import wardentools.items.CrystalResonatorItem;
import wardentools.items.ItemRegistry;
import wardentools.items.ModItemProperties;
import wardentools.weather.lightning.AbyssLightningRenderer;
import wardentools.worldgen.dimension.ModDimensions;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, value = Dist.CLIENT)
public class ClientModEvents {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(ModItemProperties::addCustomProperties);

		event.enqueueWork(() -> {
			MenuScreens.register(
					MenuRegistry.RADIANCE_CATALYST_MENU.get(),
					RadianceCatalystScreen::new
			);

			MenuScreens.register(
					MenuRegistry.DYSFUNCTIONNING_CATALYST_MENU.get(),
					DysfunctionningCatalystScreen::new
			);
		});
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

	@SubscribeEvent
	public static void registerDimensionSpecialEffect(RegisterDimensionSpecialEffectsEvent event) {
		event.register(ModDimensions.DIMENSION_RENDERER,
				new AbyssDimensionSpecialEffect(128.0F, false,
						DimensionSpecialEffects.SkyType.NORMAL, false,
						false));

	}

	@SubscribeEvent
	public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
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
		event.registerBlockEntityRenderer(BlockEntityRegistry.GRAMOPHONE_BLOCK_ENTITY.get(),
				GramophoneRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.SCULK_TENDRIL_BLOCK_ENTITY.get(),
				SculkTendrilBlockRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.LIVING_SPROUT_BLOCK_ENTITY.get(),
				LivingSproutRenderer::new);
		event.registerBlockEntityRenderer(BlockEntityRegistry.CRYSTAL_INFUSER_BLOCK_ENTITY.get(),
				CrystalInfuserRenderer::new);
	}
}
