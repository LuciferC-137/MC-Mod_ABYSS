package wardentools.events.gameevents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import wardentools.ModMain;
import wardentools.entity.custom.NoctilureEntity;

@EventBusSubscriber(modid = ModMain.MOD_ID)
public class NoctilureSprintBarGuiRender {
    private static final ResourceLocation SPRINT_BAR_BACKGROUND
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_background.png");
    private static final ResourceLocation SPRINT_BAR_PROGRESS
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_progress.png");

    @SubscribeEvent
    private static void onRenderGuiLayers(RenderGuiEvent.Post event) {
        GuiGraphics graphics = event.getGuiGraphics();
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.player.getVehicle() != null) {
            int screenWidth = minecraft.getWindow().getGuiScaledWidth();
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();
            RenderSystem.enableBlend();
            if (minecraft.player.getVehicle() instanceof NoctilureEntity noctilure) {
                minecraft.getProfiler().push("energy");
                float energyNorm = (float)noctilure.getSprintEnergy() / (float)NoctilureEntity.MAX_SPRINT_ENERGY;
                int progress = (int)((1f - energyNorm) * 182.0F);
                int barWidth = 182;
                int barHeight = 5;
                int y = screenHeight - 29;
                int x = screenWidth / 2 - barWidth / 2;
                RenderSystem.enableBlend();
                graphics.blit(SPRINT_BAR_BACKGROUND, x, y,
                            0, 0, barWidth, barHeight, barWidth, barHeight);
                graphics.blit(SPRINT_BAR_PROGRESS,x, y,
                            0, 0, barWidth - progress,
                                barHeight, barWidth, barHeight);
                RenderSystem.disableBlend();
                minecraft.getProfiler().pop();
            }
        }
    }
}
