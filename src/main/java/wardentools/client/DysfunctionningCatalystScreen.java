package wardentools.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.gui.menu.DysfunctionningCatalystMenu;

import java.util.Random;
import java.util.function.Function;

public class DysfunctionningCatalystScreen extends AbstractContainerScreen<DysfunctionningCatalystMenu> {
	private static final ResourceLocation TEXTURE =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
					"textures/gui/dysfunctionning_catalyst_menu.png");
	private static final ResourceLocation TEXTURE_EYES =
			ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID,
					"textures/gui/dysfunctionning_catalyst_menu_eye_overlay.png");
	private static final Function<ResourceLocation, RenderType> GUI
			= (resourceLocation) -> RenderType.gui();
	private static final int BAR_LENGTH = 28;
	private static final int BG_COLOR = 0xff8B8B8B;
	private final Random random;
	private final Long seed;

	public DysfunctionningCatalystScreen(DysfunctionningCatalystMenu menu,
										 Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.imageWidth = 176;
		this.imageHeight = 186;
		this.inventoryLabelY += 20;
		this.titleLabelY -= 1;
		this.random = new Random();
		this.seed = this.random.nextLong();
		this.random.setSeed(this.random.nextLong());
	}

	@Override
	protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		renderTransparentBackground(guiGraphics);
		guiGraphics.blit(GUI, TEXTURE, this.leftPos, this.topPos, 0, 0,
				this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		// LEFT bars
		guiGraphics.fill(this.leftPos + 32 + menu.getCitrineProgression(),
				this.topPos + 22, this.leftPos + 32 + BAR_LENGTH,
				this.topPos + 22 + 2, BG_COLOR);
		guiGraphics.fill(this.leftPos + 32 + menu.getAmethystProgression(),
				this.topPos + 49, this.leftPos + 32 + BAR_LENGTH,
				this.topPos + 49 + 2, BG_COLOR);
		guiGraphics.fill(this.leftPos + 32 + menu.getPaleShardProgression(),
				this.topPos + 77, this.leftPos + 32 + BAR_LENGTH,
				this.topPos + 77 + 2, BG_COLOR);

		//RIGHT bars
		guiGraphics.fill(this.leftPos + 116, this.topPos + 22,
				this.leftPos + 116 + BAR_LENGTH - menu.getRubyProgression(),
				this.topPos + 22 + 2, BG_COLOR);
		guiGraphics.fill(this.leftPos + 116, this.topPos + 49 ,
				this.leftPos + 116 + BAR_LENGTH- menu.getMalachiteProgression(),
				this.topPos + 49 + 2, BG_COLOR);
		guiGraphics.fill(this.leftPos + 116, this.topPos + 77,
				this.leftPos + 116 + BAR_LENGTH - menu.getEchoShardProgression(),
				this.topPos + 77 + 2, BG_COLOR);

		// Center
		this.renderCenterFilling(guiGraphics, this.leftPos + 61, this.topPos + 15,
				menu.getTotalCharge());
		if (!menu.getBlockEntity().isContagionDefeated()) this.renderEyes(guiGraphics);
	}

	private void renderEyes(@NotNull GuiGraphics guiGraphics) {
		RenderSystem.enableBlend();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F,
				(float)menu.getEyeProgression()/(float)DysfunctionningCatalystBlockEntity.MAX_EYE);
		guiGraphics.blit(GUI, TEXTURE_EYES, this.leftPos + 61, this.topPos + 44,
				0, 0, 54, 20, 54, 20);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.disableBlend();
	}

	private void renderCenterFilling(@NotNull GuiGraphics guiGraphics, int left, int top, int totalCharge) {
		this.random.setSeed(this.seed);
		int maxTotal = DysfunctionningCatalystBlockEntity.MAX_TOTAL;
		float p_min = 1f - (float)totalCharge / (float)maxTotal;
		int yMax = top + maxTotal;
		for (int y = top; y < maxTotal + top; y++) {
			float heightFactor = 1f - ((float)(y - top) / ((float)yMax - (float)top));
			heightFactor *= 1 / ( (1f - p_min) + 0.00001F);
			float probability = p_min * p_min * heightFactor;
			for (int x = left; x < left + 54; x++) {
				if (this.random.nextFloat() < probability) {
					guiGraphics.fill(x, y, x + 1, y + 1, BG_COLOR);
				}
			}
		}
	}
	
	@Override
	public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		super.render(graphics, mouseX, mouseY, partialTicks);
		renderTooltip(graphics, mouseX, mouseY);
	}
}
