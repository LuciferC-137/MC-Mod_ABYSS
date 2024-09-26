package wardentools.gui.title;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;


/*
Unused class. Nothing interesting here.
*/
@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomMainMenuScreen extends TitleScreen {

    public CustomMainMenuScreen(Minecraft minecraft) {
        super(false);
        this.minecraft = minecraft;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int i1, int i2, float i3) {
        super.render(guiGraphics, i1, i2, i3);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int i, int i1, float i2) {
        super.renderBackground(guiGraphics, i, i1, i2);
    }

}
