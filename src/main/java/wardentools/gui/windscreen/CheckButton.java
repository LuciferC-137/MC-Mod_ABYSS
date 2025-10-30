package wardentools.gui.windscreen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

@OnlyIn(Dist.CLIENT)
public class CheckButton extends Button {
    public static final ResourceLocation CHECKED_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/checkbox_ok.png");
    public static final ResourceLocation UNCHECKED_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/checkbox_empty.png");
    private final TaskNoteAccess.Task task;

    public CheckButton(int x, int y, TaskNoteAccess.Task task, OnPress onPress) {
        super(x, y, 16, 16, CommonComponents.EMPTY, onPress, DEFAULT_NARRATION);
        this.task = task;
    }

    public void updateXPos(int x) {
        this.setX(x);
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (this.isHovered) {
            graphics.fill(this.getX() + 2, this.getY() + 4,
                    this.getX() + 10, this.getY() + 12,
                    FastColor.ARGB32.color(50, 50, 50, 50));
        }
        graphics.blit(this.task.isOk ? CHECKED_TEXTURE:UNCHECKED_TEXTURE,
                this.getX(), this.getY(), 0, 0,
                16, 16, 16, 16);
    }
}
