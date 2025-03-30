package wardentools.gui;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.gui.menu.DysfunctionningCatalystMenu;
import wardentools.gui.menu.RadianceCatalystMenu;

public class MenuRegistry {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES =
			DeferredRegister.create(Registries.MENU, ModMain.MOD_ID);
	
	public static final DeferredHolder<MenuType<?>, MenuType<RadianceCatalystMenu>> RADIANCE_CATALYST_MENU
			= registerMenuType("radiance_catalyst_menu",RadianceCatalystMenu::new);
	public static final DeferredHolder<MenuType<?>, MenuType<DysfunctionningCatalystMenu>> DYSFUNCTIONNING_CATALYST_MENU
			= registerMenuType("dysfunctionning_catalyst_menu", DysfunctionningCatalystMenu::new);

	private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
																											  IContainerFactory<T> factory) {
		return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
	}

	public static void register(IEventBus eventBus) {
		MENU_TYPES.register(eventBus);
	}
}
