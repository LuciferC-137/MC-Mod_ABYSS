package wardentools;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import wardentools.client.gui.AbyssConfigScreen;

public final class WardenToolsMainClient {

    private WardenToolsMainClient() {
    }

    public static void registerConfigScreen() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new AbyssConfigScreen(parent)));
    }
}

