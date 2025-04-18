package wardentools.gui.windscreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

@OnlyIn(Dist.CLIENT)
public class JumpToTaskButton extends Button {
    public static final ResourceLocation JUMP_TO_TASK_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/jump_to_task_button.png");
    public static final ResourceLocation JUMP_TO_TASK_TEXTURE_HOVER
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/jump_to_task_button_hover.png");
    private static final int LENGTH = 51;
    private static final int HEIGHT = 47;
    private static final Component jump_message = Component.empty()
            .withStyle(Style.EMPTY.withHoverEvent(new HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    TaskNoteAccess.Task.hoveredTranslatableText("message." + ModMain.MOD_ID + ".jump_to_task"))));

    public JumpToTaskButton(int x, int y, OnPress onPress) {
        super(x - 1, y - 1, LENGTH, HEIGHT, Component.empty(), onPress, DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(this.isHovered ? JUMP_TO_TASK_TEXTURE_HOVER:JUMP_TO_TASK_TEXTURE,
                this.getX(), this.getY(), 0, 0,
                LENGTH, HEIGHT, LENGTH, HEIGHT);
        if (this.isHovered()) {
            graphics.renderComponentHoverEffect(
                    Minecraft.getInstance().font,
                    jump_message.getStyle(),
                    mouseX, mouseY);
        }

    }
}
