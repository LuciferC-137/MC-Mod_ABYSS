package wardentools.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class AbyssConfigScreen extends Screen {
    private final Screen parent;

    public AbyssConfigScreen(Screen parent) {
        super(Component.literal("ABYSS Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 28, 100, 20)
                .build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        graphics.drawCenteredString(this.font,
                Component.literal("Edit the Forge config files in the config folder."),
                this.width / 2,
                this.height / 2 - 10,
                0xA0A0A0);
        graphics.drawCenteredString(this.font,
                Component.literal("Client / common / server values are split across the Forge TOML files."),
                this.width / 2,
                this.height / 2 + 2,
                0xA0A0A0);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }
}

