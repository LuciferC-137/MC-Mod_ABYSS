package wardentools;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wardentools.GUI.MenuRegistry;
import wardentools.GUI.ModCreativeTabs;
import wardentools.advancement.ModCriteriaTriggers;
import wardentools.items.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.fluid.FluidRegistry;
import wardentools.fluid.ModFluidTypes;
import wardentools.items.ItemRegistry;
import wardentools.items.PaintingsRegistry;
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
	public static final String VERSION = "0.0.1";
	
	public ModMain() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ArmorRegistry.REGISTAR.register(bus);
		ItemRegistry.REGISTAR.register(bus);
		BlockRegistry.REGISTAR.register(bus);
		BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
		PaintingsRegistry.PAINTINGS.register(bus);
		ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
		ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
		ModEntities.ENTITY_TYPES.register(bus);
		MenuRegistry.MENU_TYPES.register(bus);
		ModCreativeTabs.CREATIVE_MODE_TABS.register(bus);
		ModSounds.SOUND_EVENTS.register(bus);
		FluidRegistry.FLUIDS.register(bus);
		ModFluidTypes.FLUID_TYPES.register(bus);
		ModFeatures.FEATURES.register(bus);
		ParticleRegistry.PARTICLE_TYPES.register(bus);
		ModEffects.MOB_EFFECTS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        
        bus.addListener(this::commonSetup);
        
        MinecraftForge.EVENT_BUS.register(WardenLootTableModifier.class);
        
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(()->{
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.WHITE_TORCHFLOWER.getId(),
					BlockRegistry.POTTED_WHITE_TORCHFLOWER);
		});

    }
}



