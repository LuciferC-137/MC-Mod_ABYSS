package wardentools.client;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import wardentools.ModMain;
import wardentools.GUI.menu.RadianceCatalystMenu;

public class RadianceCatalystScreen extends AbstractContainerScreen<RadianceCatalystMenu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID,
			"textures/gui/radiance_catalyst_menu.png");

	public RadianceCatalystScreen(RadianceCatalystMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		renderTransparentBackground(guiGraphics);
		guiGraphics.blit(TEXTURE, this.leftPos,this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}
	
	@Override
	public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		super.render(graphics, mouseX, mouseY, partialTicks);
		renderTooltip(graphics, mouseX, mouseY);
		
	}

}
