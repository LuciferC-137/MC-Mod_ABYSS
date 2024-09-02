package wardentools.GUI;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.armors.ArmorRegistry;
import wardentools.items.ItemRegistry;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ABYSS_TAB = CREATIVE_MODE_TABS.register("abyss_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DEEPCRISTAL.get()))
                    .title(Component.translatable("creativetab.abyss_tab"))
                    .displayItems((pParameters, event) -> {
                    	event.accept(ItemRegistry.ABYSSALITE.get());
                    	event.accept(ItemRegistry.CHISELED_ABYSSALITE.get());
                    	event.accept(ItemRegistry.ABYSSALITE_BRICKS.get());
                    	event.accept(ItemRegistry.ABYSSALITE_BRICKS_STAIRS.get());
                    	event.accept(ItemRegistry.ABYSSALITE_BRICKS_SLAB.get());
                    	event.accept(ItemRegistry.CRACKED_ABYSSALITE_BRICKS.get());

						event.accept(ItemRegistry.ABYSSALITE_COAL_ORE.get());
						event.accept(ItemRegistry.ABYSSALITE_LAPIS_ORE.get());
						event.accept(ItemRegistry.ABYSSALITE_DIAMOND_ORE.get());
						event.accept(ItemRegistry.ABYSSALITE_DEEP_ORE.get());


						event.accept(ItemRegistry.DEEP_FRAGMENT.get());
						event.accept(ItemRegistry.DEEPCRISTAL.get());
						event.accept(ItemRegistry.DEEPINGOTS.get());
						event.accept(ItemRegistry.DEEPBLOCK.get());
						event.accept(ItemRegistry.PALE_FRAGMENT.get());
                    	event.accept(ItemRegistry.PALE_SHARD.get());
						event.accept(ItemRegistry.PALE_CRISTAL_BLOCK.get());
						event.accept(ItemRegistry.CITRINE_FRAGMENT.get());
						event.accept(ItemRegistry.CITRINE.get());
						event.accept(ItemRegistry.CITRINE_BLOCK.get());
						event.accept(Items.ECHO_SHARD);
						event.accept(ItemRegistry.ECHO_CRISTAL.get());
						event.accept(ItemRegistry.ECHO_BLOCK.get());

                    	event.accept(ArmorRegistry.DEEPCRISTAL_HELMET.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_LEGGINGS.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_BOOTS.get());
                        
                        event.accept(ItemRegistry.DARKTREE_LOG.get());
                    	event.accept(ItemRegistry.DARKTREE_WOOD.get());
                    	event.accept(ItemRegistry.STRIPPED_DARKTREE_LOG.get());
                    	event.accept(ItemRegistry.STRIPPED_DARKTREE_WOOD.get());
                    	event.accept(ItemRegistry.DARKTREE_PLANKS.get());
                    	event.accept(ItemRegistry.DARKTREE_LEAVES.get());
                    	event.accept(ItemRegistry.DARKTREE_SAPLING.get());
                    	event.accept(ItemRegistry.DARKTREE_STAIRS.get());
                    	event.accept(ItemRegistry.DARKTREE_SLAB.get());
                    	event.accept(ItemRegistry.DARKTREE_FENCE.get());
                    	event.accept(ItemRegistry.DARKTREE_BUTTON.get());
                    	event.accept(ItemRegistry.DARKTREE_DOOR.get());
                    	event.accept(ItemRegistry.DARKTREE_TRAPDOOR.get());
                    	event.accept(ItemRegistry.DARKTREE_FENCE_GATE.get());
                    	event.accept(ItemRegistry.DARKTREE_PRESSURE_PLATE.get());
                    	event.accept(ItemRegistry.DARK_STICK.get());
                    	
                    	event.accept(ItemRegistry.BLUE_BUSH.get());
                    	event.accept(ItemRegistry.DEEPFLOWER.get());
                    	
                    	event.accept(ItemRegistry.WHITETREE_LOG.get());
                    	event.accept(ItemRegistry.WHITETREE_WOOD.get());
                    	event.accept(ItemRegistry.STRIPPED_WHITETREE_LOG.get());
                    	event.accept(ItemRegistry.STRIPPED_WHITETREE_WOOD.get());
                    	event.accept(ItemRegistry.WHITETREE_LEAVES.get());
                    	event.accept(ItemRegistry.WHITETREE_SAPLING.get());
                    	event.accept(ItemRegistry.WHITETREE_PLANKS.get());
                    	event.accept(ItemRegistry.WHITETREE_STAIRS.get());
                    	event.accept(ItemRegistry.WHITETREE_SLAB.get());
                    	event.accept(ItemRegistry.WHITETREE_FENCE.get());
                    	event.accept(ItemRegistry.WHITETREE_BUTTON.get());
                    	event.accept(ItemRegistry.WHITETREE_DOOR.get());
                    	event.accept(ItemRegistry.WHITETREE_TRAPDOOR.get());
                    	event.accept(ItemRegistry.WHITETREE_FENCE_GATE.get());
                    	event.accept(ItemRegistry.WHITETREE_PRESSURE_PLATE.get());
                    	event.accept(ItemRegistry.WHITE_SEED.get());
                    	
                    	event.accept(ItemRegistry.WHITE_GRASS.get());
                    	event.accept(ItemRegistry.TALL_WHITE_GRASS.get());
                    	event.accept(ItemRegistry.WHITE_TORCHFLOWER.get());
                    	
                    	event.accept(ItemRegistry.DARKDIRT.get());
                    	event.accept(ItemRegistry.DARKGRASS_BLOCK.get());
                    	event.accept(ItemRegistry.TALL_DARK_GRASS.get());
                    	event.accept(ItemRegistry.DARK_GRASS.get());

                    	event.accept(ItemRegistry.DEEPLURKER_EGG.get());
                    	event.accept(ItemRegistry.PALEWANDERER_EGG.get());
                    	event.accept(ItemRegistry.DEEP_FRUIT.get());
                    	
                    	event.accept(ItemRegistry.WARDEN_HEART.get());
                    	event.accept(ItemRegistry.CORRUPTED_ESSENCE.get());
                    	event.accept(ItemRegistry.CORRUPTED_VESSEL.get());
                    	event.accept(ItemRegistry.WIND_WHISPERER.get());
                    	event.accept(ItemRegistry.ABYSS_DIVER.get());
                    	                    	
                    	event.accept(ItemRegistry.RADIANCE_CATALYST.get());
                    	event.accept(ItemRegistry.PURE_ESSENCE.get());
                    	event.accept(ItemRegistry.RADIANT_CORE.get());
                    	event.accept(ItemRegistry.PURE_VESSEL.get());
                    	event.accept(ItemRegistry.PROTECTOR_HEART.get());
                    	event.accept(ItemRegistry.DYING_PROTECTOR_HEART.get());
                    	
                    	event.accept(ItemRegistry.PROTECTOR_INVOKER.get());
                    	
                    }).build());
    
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    
}
