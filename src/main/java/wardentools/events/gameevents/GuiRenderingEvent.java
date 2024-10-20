package wardentools.events.gameevents;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;
import wardentools.entity.custom.NoctilureEntity;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class GuiRenderingEvent {
    private static final ResourceLocation SPRINT_BAR_BACKGROUND
            = new ResourceLocation(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_background.png");
    private static final ResourceLocation SPRINT_BAR_PROGRESS
            = new ResourceLocation(ModMain.MOD_ID,
            "textures/gui/sprint_bar/noctilure_progress.png");
    private static final int barWidth = 182;
    private static final int barHeight = 5;

    @SubscribeEvent
    public static void onGuiRendering(RenderGuiOverlayEvent.Pre event) {
        NamedGuiOverlay overlay = event.getOverlay();
        ResourceLocation overlayId = overlay.id();
        Minecraft minecraft = Minecraft.getInstance();
        GuiGraphics graphics = event.getGuiGraphics();
        if (overlayId.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) {
            if (minecraft.player != null && minecraft.player.getVehicle() != null) {
                event.setCanceled(true);
                int screenWidth = minecraft.getWindow().getGuiScaledWidth();
                int screenHeight = minecraft.getWindow().getGuiScaledHeight();
                RenderSystem.enableBlend();
                if (minecraft.player.getVehicle() instanceof NoctilureEntity noctilure) {
                    minecraft.getProfiler().push("energy");
                    float f = (float)noctilure.getSprintEnergy() / (float)NoctilureEntity.MAX_SPRINT_ENERGY;
                    int progress = (int)((1f - f) * 182.0F);
                    int y = screenHeight - 29;
                    int x = screenWidth / 2 - barWidth / 2;
                    graphics.blit(SPRINT_BAR_BACKGROUND, x, y,
                            0, 0, barWidth, barHeight, barWidth, barHeight);
                    graphics.blit(SPRINT_BAR_PROGRESS,x, y,
                                0, 0, barWidth - progress,
                                barHeight, barWidth, barHeight);
                    minecraft.getProfiler().pop();
                }
            }
        }
    }
}
