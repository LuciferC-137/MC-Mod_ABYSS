package wardentools.client;

import org.jetbrains.annotations.NotNull;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import wardentools.ModMain;
import wardentools.blockentity.RadianceCatalystBlockEntity;

public class RadianceCatalystScreen extends Screen {
	private static final Component TITLE =
			Component.translatable("gui." + ModMain.MOD_ID + ".radiance_catalyst_screen");
	private static final Component DONE =
			Component.translatable("gui." + ModMain.MOD_ID + ".radiance_catalyst_screen.button.done");
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID,
			"textures/gui/radiance_catalyst_screen.png");
	
	private final BlockPos position;
	private final int imageWidth, imageHeight;
	private RadianceCatalystBlockEntity blockEntity;
	private int leftPos, topPos;
	@SuppressWarnings("unused")
	private Button button;

	public RadianceCatalystScreen(BlockPos pos) {
		super(TITLE);
		this.position = pos;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}
	
	@Override
	protected void init() {
		super.init();
		
		this.leftPos = (this.width - this.imageWidth) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;
		
		if (this.minecraft == null) return;
		Level level = this.minecraft.level;
		if (level == null) return;
		
		BlockEntity be = level.getBlockEntity(position);
		if (be instanceof RadianceCatalystBlockEntity blockEntity) {
			this.blockEntity = blockEntity;
		} else {
			return;
		}
		
		this.button = addRenderableWidget(
				Button.builder(
						DONE, this::handleDoneButton)
						.bounds(this.leftPos + 8, this.topPos + 20, 60, 20)
						.tooltip(Tooltip.create(DONE))
						.build());		
	}
	
	@Override
	public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		//renderBackground(graphics, mouseY, mouseY, partialTicks);
		graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
		super.render(graphics, mouseX, mouseX, partialTicks);
		graphics.drawString(this.font, TITLE, this.leftPos + 8, this.topPos + 8, 0x404040, false);
		
		graphics.drawString(this.font, "Seconds Existed: %d".formatted(this.blockEntity.getSecondsExisted()),
				this.leftPos + 40, this.topPos + 80, 0xFF0000, false);
	}
	
	private void handleDoneButton(Button button) {
		// logic here
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
