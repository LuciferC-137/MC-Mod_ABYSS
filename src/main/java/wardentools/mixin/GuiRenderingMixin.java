package wardentools.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wardentools.ModMain;
import wardentools.entity.custom.NoctilureEntity;

import java.util.function.Function;

@Mixin(Gui.class)
public class GuiRenderingMixin {
    @Unique private static final ResourceLocation SPRINT_BAR_BACKGROUND
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_background.png");
    @Unique private static final ResourceLocation SPRINT_BAR_PROGRESS
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_progress.png");
    @Unique private static final Function<ResourceLocation, RenderType> GUI_RENDER_TYPE
            = (resourceLocation) -> RenderType.gui();


    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void onGuiRendering(GuiGraphics graphics, DeltaTracker tracker, CallbackInfo ci) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.player.getVehicle() != null) {
            int screenWidth = minecraft.getWindow().getGuiScaledWidth();
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();
            RenderSystem.enableBlend();
            if (minecraft.player.getVehicle() instanceof NoctilureEntity noctilure) {
                Profiler.get().push("energy");
                float energyNorm = (float)noctilure.getSprintEnergy() / (float)NoctilureEntity.MAX_SPRINT_ENERGY;
                int progress = (int)((1f - energyNorm) * 182.0F);
                int barWidth = 182;
                int barHeight = 5;
                int y = screenHeight - 29;
                int x = screenWidth / 2 - barWidth / 2;
                graphics.blitSprite(GUI_RENDER_TYPE, SPRINT_BAR_BACKGROUND, x, y,
                            0, 0, barWidth, barHeight, barWidth, barHeight);
                graphics.blitSprite(GUI_RENDER_TYPE, SPRINT_BAR_PROGRESS,x, y,
                                0, 0, barWidth - progress,
                                barHeight, barWidth, barHeight);
                Profiler.get().pop();
            }
        }
    }
}
