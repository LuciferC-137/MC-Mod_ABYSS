package wardentools.GUI;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.armors.ArmorRegistry;
import wardentools.items.ItemRegistry;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("abyss_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.DEEPCRISTAL.get()))
                    .title(Component.translatable("creativetab.abyss_tab"))
                    .displayItems((pParameters, event) -> {
                    	event.accept(ItemRegistry.DEEPINGOTS.get());
                    	event.accept(ItemRegistry.DEEPCRISTAL.get());
                    	event.accept(ItemRegistry.PALE_SHARD.get());
                    	event.accept(ItemRegistry.PALE_FRAGMENT.get());
                    	event.accept(ItemRegistry.DEEPBLOCK.get());

                    	event.accept(ArmorRegistry.DEEPCRISTAL_HELMET.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_CHESTPLATE.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_LEGGINGS.get());
                        event.accept(ArmorRegistry.DEEPCRISTAL_BOOTS.get());
                        
                    	event.accept(ItemRegistry.DARKTREE_WOOD.get());
                    	event.accept(ItemRegistry.STRIPPED_DARKTREE_LOG.get());
                    	event.accept(ItemRegistry.STRIPPED_DARKTREE_WOOD.get());
                    	
                    	event.accept(ItemRegistry.DARKTREE_PLANKS.get());
                    	event.accept(ItemRegistry.DARKTREE_LEAVES.get());
                    	event.accept(ItemRegistry.DARKTREE_LOG.get());
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
                    	
                    	event.accept(ItemRegistry.DARKDIRT.get());
                    	event.accept(ItemRegistry.DARKGRASS_BLOCK.get());
                    	
                    	event.accept(ItemRegistry.WARDEN_HEART.get());
                    	event.accept(ItemRegistry.CORRUPTED_ESSENCE.get());

                    	event.accept(ItemRegistry.DEEPLURKER_EGG.get());
                    	event.accept(ItemRegistry.DEEP_FRUIT.get());
                    	
                    	event.accept(ItemRegistry.CORRUPTED_VESSEL.get());
                    	event.accept(ItemRegistry.WIND_WHISPERER.get());
                    	event.accept(ItemRegistry.ABYSS_DIVER.get());
                    	                	
                    	
                    	
                    	
                    }).build());
    
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    
}
