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
import wardentools.entity.ModEntities;
import wardentools.loot.WardenLootTableModifier;
import wardentools.registries.ArmorRegistry;
import wardentools.registries.BlockRegistry;
import wardentools.registries.ItemRegistry;
import wardentools.worldgen.biome.ModTerrablender;
import wardentools.worldgen.biome.surface.ModSurfaceRules;
import wardentools.worldgen.tree.ModFoliagePlacers;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;



@Mod(ModMain.MOD_ID)
public class ModMain {
	public static final String MOD_ID = "wardentools";
	public static final String MODNAME = "DEEP ARMOR";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String VERSION = "0.0.1";
	
	public ModMain() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ArmorRegistry.REGISTAR.register(bus);
		ItemRegistry.REGISTAR.register(bus);
		BlockRegistry.REGISTAR.register(bus);
		ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
		ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		ModEntities.ENTITY_TYPES.register(bus);
		
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
        
        if(event.getTabKey()==CreativeModeTabs.COMBAT) {
        	event.accept(ArmorRegistry.DEEPCRISTAL_HELMET);
            event.accept(ArmorRegistry.DEEPCRISTAL_CHESTPLATE);
            event.accept(ArmorRegistry.DEEPCRISTAL_LEGGINGS);
            event.accept(ArmorRegistry.DEEPCRISTAL_BOOTS);
        }
        
        if(event.getTabKey()==CreativeModeTabs.BUILDING_BLOCKS) {
        	event.accept(ItemRegistry.DEEPBLOCK);
        	event.accept(ItemRegistry.DARKTREE_WOOD);
        	event.accept(ItemRegistry.STRIPPED_DARKTREE_LOG);
        	event.accept(ItemRegistry.STRIPPED_DARKTREE_WOOD);
        	event.accept(ItemRegistry.DARKTREE_PLANKS);
        }
        
        if (event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS) {
        	event.accept(ItemRegistry.DEEPCRISTAL);
        	event.accept(ItemRegistry.DARKTREE_LEAVES);
        	event.accept(ItemRegistry.DARKTREE_LOG);
        	event.accept(ItemRegistry.DARKTREE_SAPLING);
        	event.accept(ItemRegistry.DARKDIRT);
        	event.accept(ItemRegistry.DARKGRASS_BLOCK);
        	
        }
        
        if (event.getTabKey()==CreativeModeTabs.TOOLS_AND_UTILITIES) {
        	event.accept(ItemRegistry.WARDEN_HEART);
        }
        
        if (event.getTabKey()==CreativeModeTabs.FUNCTIONAL_BLOCKS) {
        	event.accept(ItemRegistry.ABYSS_PORTAL);
        }
        
        if (event.getTabKey()==CreativeModeTabs.SPAWN_EGGS) {
        	event.accept(ItemRegistry.DEEPLURKER_EGG);
        }
	}
	
}



