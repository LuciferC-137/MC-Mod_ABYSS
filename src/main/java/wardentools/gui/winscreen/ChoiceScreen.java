package wardentools.gui.winscreen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import wardentools.ModMain;
import wardentools.sounds.ModMusics;

@OnlyIn(Dist.CLIENT)
public class ChoiceScreen extends Screen {
   private static final ResourceLocation VIGNETTE_LOCATION
           = new ResourceLocation("textures/misc/vignette.png");
   private static final ResourceLocation MOD_BACKGROUND_LOCATION
           = new ResourceLocation(ModMain.MOD_ID, "textures/gui/background.png");
   private final Runnable onFinished;

    public ChoiceScreen(Runnable runnable) {
      super(GameNarrator.NO_TITLE);
      this.onFinished = runnable;
   }

   @Override
   protected void init() {
      int buttonWidth = 200;
      int buttonHeight = 20;
      int x = (this.width - buttonWidth) / 2;
      int y = this.height / 2 - 24;
      Button radianceButton = Button.builder(Component.literal("Radiance"),
              (button) -> this.respawn()).bounds(x, y, buttonWidth, buttonHeight).build();
      Button contagionButton = Button.builder(Component.literal("Contagion"),
              (button) -> this.respawn()).bounds(x, y + 24, buttonWidth, buttonHeight).build();
      this.addRenderableWidget(radianceButton);
      this.addRenderableWidget(contagionButton);
   }

   public void tick() {
      if (this.minecraft != null) {
         this.minecraft.getMusicManager().tick();
         this.minecraft.getSoundManager().tick(false);
      }
   }

   public void onClose() {this.respawn();}

   private void respawn() {this.onFinished.run();}

   public void render(@NotNull GuiGraphics graphics, int x, int y, float a) {
      super.render(graphics, x, y, a);
      int i = this.width / 2 - 128;
      int j = this.height + 50;
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO,
              GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
      graphics.blit(VIGNETTE_LOCATION, 0, 0,
              0, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
      RenderSystem.disableBlend();
      RenderSystem.defaultBlendFunc();
   }

   public void renderBackground(@NotNull GuiGraphics graphics, int x, int y, float a) {
      int i = this.width;
      int j = 64;
      graphics.blit(MOD_BACKGROUND_LOCATION, 0, 0, 0, 0.0F,
              0, i, this.height, j, j);
      graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void removed() {
      if (this.minecraft != null) {
         this.minecraft.getMusicManager().stopPlaying(this.getBackgroundMusic());
      }
   }

   public @NotNull Music getBackgroundMusic() {
      return ModMusics.DEEP_FOREST;
   }
}