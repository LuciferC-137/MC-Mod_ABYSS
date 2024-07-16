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
		
		int energyScale = this.menu.getEnergyStoredScale();
		
		//energy bar background
		guiGraphics.fill(
				this.leftPos + 115,
				this.topPos + 20,
				this.leftPos + 131,
				this.topPos + 60,
				0xFF555555);
		
		//energy bar foreground
		guiGraphics.fill(
				this.leftPos + 116,
				this.topPos + 21 + 38 - energyScale,
				this.leftPos + 130,
				this.topPos + 59,
				0xFFCC2222);
	}
	
	@Override
	public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		super.render(graphics, mouseX, mouseY, partialTicks);
		renderTooltip(graphics, mouseX, mouseY);
		
		int energyStored = this.menu.getEnergy();
		int maxEnergy = this.menu.getMaxBurnTime();
		
		Component text = Component.literal("Energy: " + energyStored + " / " + maxEnergy);
		if (isHovering(115, 20, 16, 40, mouseX, mouseY)) {
			graphics.renderTooltip(this.font, text, mouseX, mouseY);
		}
	}

}
