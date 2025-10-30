package wardentools;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import wardentools.datagen.loot.ModLootModifiers;
import wardentools.gui.MenuRegistry;
import wardentools.gui.ModCreativeTabs;
import wardentools.items.PotionRegistry;
import wardentools.items.armors.ArmorRegistry;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.fluid.FluidRegistry;
import wardentools.fluid.ModFluidTypes;
import wardentools.items.ItemRegistry;
import wardentools.items.recipe.ModRecipes;
import wardentools.particle.ParticleRegistry;
import wardentools.playerdata.ModDataAttachments;
import wardentools.sounds.ModSounds;
import wardentools.worldgen.features.ModFeatures;
import wardentools.worldgen.structure.ModStructureTypes;
import wardentools.worldgen.tree.ModFoliagePlacers;
import wardentools.worldgen.tree.ModTrunkPlacerTypes;


@Mod(ModMain.MOD_ID)
public class ModMain {
	public static final String MOD_ID = "wardentools";
	public static final String MODNAME = "ABYSS";
    public static final String VERSION = "1.1.0";
    private static final Logger LOGGER = LogUtils.getLogger();

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
		PotionRegistry.POTIONS.register(bus);
		ModRecipes.SERIALIZERS.register(bus);
		ModRecipes.TYPES.register(bus);
		ModStructureTypes.STRUCTURE_TYPES.register(bus);
		ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(bus);
        ModDataAttachments.ATTACHMENTS.register(bus);

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }
}



