package wardentools.gui;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.gui.menu.DysfunctionningCatalystMenu;
import wardentools.gui.menu.RadianceCatalystMenu;

public class MenuRegistry {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES =
			DeferredRegister.create(ForgeRegistries.MENU_TYPES, ModMain.MOD_ID);
	
	public static final RegistryObject<MenuType<RadianceCatalystMenu>> RADIANCE_CATALYST_MENU
			= MENU_TYPES.register("radiance_catalyst_menu",
			() -> IForgeMenuType.create(RadianceCatalystMenu::new));
	public static final RegistryObject<MenuType<DysfunctionningCatalystMenu>> DYSFUNCTIONNING_CATALYST_MENU
			= MENU_TYPES.register("dysfunctionning_catalyst_menu",
			() -> IForgeMenuType.create(DysfunctionningCatalystMenu::new));
}
