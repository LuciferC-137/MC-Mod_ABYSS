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
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(ModMain.MOD_ID, "textures/gui/radiance_catalyst_menu.png");

	public RadianceCatalystScreen(RadianceCatalystMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		renderTransparentBackground(guiGraphics);
		guiGraphics.blit(TEXTURE, this.leftPos,this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		
		if (this.menu.getMaxBurnTime() == 0){
			return;
		}
		
		int bar1Length1 = 36;
		int bar1Length2 = 7;
		int bar1Height = 15;
		int barTotalLength = bar1Length1 + bar1Length2 + bar1Height + 6;
		int energyScaleBar = (int)((float)this.menu.getEnergy()/(float)this.menu.getMaxEnergy()*barTotalLength);

		if (energyScaleBar <= bar1Length1) {
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 65 + energyScaleBar,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar == bar1Length1 + 1){
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 44,
					this.leftPos + 102,
					this.topPos + 45,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 65 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar == bar1Length1 + 2){
			guiGraphics.fill(
					this.leftPos + 102,
					this.topPos + 44,
					this.leftPos + 103,
					this.topPos + 45,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 66 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar <= bar1Length1 + bar1Height + 3) {
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 43,
					this.leftPos + 103,
					this.topPos + 43 - (energyScaleBar - (bar1Length1 + 3)),
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 67 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar == bar1Length1 + bar1Height + 4) {
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 27,
					this.leftPos + 102,
					this.topPos + 28,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 43,
					this.leftPos + 103,
					this.topPos + 43 - bar1Height,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 67 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar == bar1Length1 + bar1Height + 5) {
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 26,
					this.leftPos + 102,
					this.topPos + 27,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 43,
					this.leftPos + 103,
					this.topPos + 42 - bar1Height,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 67 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		} else if (energyScaleBar <= barTotalLength) {
			guiGraphics.fill(
					this.leftPos + 103,
					this.topPos + 26,
					this.leftPos + 103 + energyScaleBar - (bar1Length1 + bar1Height + 6),
					this.topPos + 28,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 101,
					this.topPos + 43,
					this.leftPos + 103,
					this.topPos + 41 - bar1Height,
					0xFF33DDDD);
			guiGraphics.fill(
					this.leftPos + 64,
					this.topPos + 43,
					this.leftPos + 67 + bar1Length1,
					this.topPos + 45,
					0xFF33DDDD);
		}
	}
	
	@Override
	public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		super.render(graphics, mouseX, mouseY, partialTicks);
		renderTooltip(graphics, mouseX, mouseY);
		
		Component corrupted = Component.literal("Corrupted material");
		if (isHovering(131, 22, 140, 31, mouseX, mouseY)) {
			graphics.renderTooltip(this.font, corrupted, mouseX, mouseY);
		}
		
		Component purified = Component.literal("Puriefied material");
		if (isHovering(131, 57, 140, 66, mouseX, mouseY)) {
			graphics.renderTooltip(this.font, purified, mouseX, mouseY);
		}
	}
}
