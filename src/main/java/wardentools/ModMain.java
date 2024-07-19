package wardentools;


import org.slf4j.Logger;
import com.mojang.logging.LogUtils;


import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.SurfaceRuleManager;
import wardentools.GUI.MenuRegistry;
import wardentools.GUI.ModCreativeTabs;
import wardentools.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;
import wardentools.loot.WardenLootTableModifier;
import wardentools.worldgen.biome.ModTerrablender;
import wardentools.worldgen.biome.surface.ModSurfaceRules;
import wardentools.worldgen.tree.ModFoliagePlacers;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;



@Mod(ModMain.MOD_ID)
public class ModMain {
	public static final String MOD_ID = "wardentools";
	public static final String MODNAME = "ABYSS";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String VERSION = "0.0.1";
	
	public ModMain() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ArmorRegistry.REGISTAR.register(bus);
		ItemRegistry.REGISTAR.register(bus);
		BlockRegistry.REGISTAR.register(bus);
		BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
		ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
		ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		ModEntities.ENTITY_TYPES.register(bus);
		MenuRegistry.MENU_TYPES.register(bus);
		ModCreativeTabs.CREATIVE_MODE_TABS.register(bus);
		
		ModTerrablender.registerBiomes();

        MinecraftForge.EVENT_BUS.register(this);
        
        bus.addListener(this::commonSetup);
        bus.addListener(this::addCreative);
        
        MinecraftForge.EVENT_BUS.register(WardenLootTableModifier.class);
        
	}
	
	private void commonSetup(final FMLCommonSetupEvent event) {
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
				MOD_ID, ModSurfaceRules.makeRules());;

    }
	
		
	private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ItemRegistry.DEEPINGOTS);
        }
	}
	
}


