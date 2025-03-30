package wardentools;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import wardentools.gui.MenuRegistry;
import wardentools.gui.ModCreativeTabs;
import wardentools.items.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.fluid.FluidRegistry;
import wardentools.fluid.ModFluidTypes;
import wardentools.items.ItemRegistry;
import wardentools.loot.WardenLootTableModifier;
import wardentools.particle.ParticleRegistry;
import wardentools.sounds.ModSounds;
import wardentools.worldgen.features.ModFeatures;
import wardentools.worldgen.tree.ModFoliagePlacers;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;


@Mod(ModMain.MOD_ID)
public class ModMain {
	public static final String MOD_ID = "wardentools";
	public static final String MODNAME = "ABYSS";
	public static final String VERSION = "1.0.2";
	
	public ModMain(IEventBus bus, ModContainer container) {

		ArmorRegistry.ARMORS.register(bus);
		ItemRegistry.ITEMS.register(bus);
		BlockRegistry.BLOCKS.register(bus);
		BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
		ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
		ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		ModEntities.ENTITY_TYPES.register(bus);
		MenuRegistry.MENU_TYPES.register(bus);
		ModSounds.SOUND_EVENTS.register(bus);
		FluidRegistry.FLUIDS.register(bus);
		ModFluidTypes.FLUID_TYPES.register(bus);
		ModFeatures.FEATURES.register(bus);
		ParticleRegistry.PARTICLE_TYPES.register(bus);
		ModEffects.MOB_EFFECTS.register(bus);
		ModCreativeTabs.CREATIVE_MODE_TABS.register(bus);

		NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(WardenLootTableModifier.class);
        
	}
}



