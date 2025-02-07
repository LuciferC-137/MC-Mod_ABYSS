package wardentools.events.gameevents;

import com.mojang.blaze3d.platform.Window;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wardentools.ModMain;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID)
public class CustomizeBossBar {
	private static final ResourceLocation FULL
			= ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/bossbar/bar_full.png");
	private static final ResourceLocation EMPTY
			= ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/bossbar/bar_empty.png");
	private static final int barWidth = 182;
	private static final int barHeight = 31;
	private static final Function<ResourceLocation, RenderType> GUI = RenderType::guiTextured;
	
	@SubscribeEvent
    public static void onBossEventProgress(CustomizeGuiOverlayEvent.BossEventProgress event) {
		if (event.getBossEvent().getName().getString()
				.equals(Component.translatable("entity." + ModMain.MOD_ID + ".contagion_incarnation").getString())){
			event.setCanceled(true);
			GuiGraphics guiGraphics = event.getGuiGraphics();
	        Window window = event.getWindow();
	        int screenWidth = window.getGuiScaledWidth();
	        
	        LerpingBossEvent bossInfo = event.getBossEvent();
	        int x = (int)(((float)(screenWidth - barWidth)) / 2);
	        int y = 5;
	        
	        int progressWidth = (int)((1f-bossInfo.getProgress())*(float)barWidth);
	       
	        guiGraphics.blit(GUI, EMPTY, x, y,
	        		0, 0, barWidth, barHeight,
					barWidth, barHeight, ARGB.white(1f));
	        guiGraphics.blit(GUI, FULL, x, y,
	        		0, 0, barWidth-progressWidth,
					barHeight, barWidth, barHeight, ARGB.white(1f));
	
		}
        
    }

}
