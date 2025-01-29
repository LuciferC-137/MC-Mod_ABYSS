package wardentools.gui.winscreen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.network.PacketHandler;
import wardentools.network.SwitchAchievement;
import wardentools.sounds.ModMusics;

import java.util.Random;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class ChoiceScreen extends Screen {
   private static final ResourceLocation VIGNETTE_LOCATION
           = ResourceLocation.withDefaultNamespace("textures/misc/vignette.png");
   private static final ResourceLocation CORRUPTION_LOCATION
              = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/choice_screen/corruption.png");
   private static final ResourceLocation RADIANCE_LOCATION
              = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/choice_screen/radiance.png");
   private static final ResourceLocation CORRUPTION_BUTTON_OVERLAY
              = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/choice_screen/corruption_button_overlay.png");
   private static final ResourceLocation RADIANCE_BUTTON_OVERLAY
              = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/choice_screen/radiance_button_overlay.png");
   private static final int BUTTON_WIDTH = 100;
   private static final int BUTTON_HEIGHT = 20;
   private static final int OVERLAY_MARGIN = 40;
   private final Runnable onFinished;
   private Button contagionButton;
   private Button radianceButton;
   private float spreadFactor = 0.5f;
   private static final float MAX_CENTER_FACTOR = 0.7f;
   private static final float MIN_CENTER_FACTOR = 0.3f;
   private static final float SPREAD_FACTOR = 0.02f;
   private long seed = 123456789L;
   private int fadeInTick = 0;
   private static final int FADE_IN_DURATION = 80;
   private static final Function<ResourceLocation, RenderType> GUI
           = (resourceLocation) -> RenderType.gui();

   public ChoiceScreen(Runnable runnable) {
      super(GameNarrator.NO_TITLE);
      this.onFinished = runnable;
   }

   @Override
   protected void init() {
      Minecraft.getInstance().getMusicManager().startPlaying(this.getBackgroundMusic());
      int spacing = 30;
      int x1 = (this.width / 2) - BUTTON_WIDTH - spacing;
      int x2 = (this.width / 2) + spacing;
      int y = this.height / 2 - 10;
      this.contagionButton = Button.builder(Component.literal("Contagion"),
              (button) -> this.onContagionClicked()).bounds(x1, y, BUTTON_WIDTH, BUTTON_HEIGHT).build();
      this.radianceButton = Button.builder(Component.literal("Radiance"),
              (button) -> this.onRadianceClicked()).bounds(x2, y, BUTTON_WIDTH, BUTTON_HEIGHT).build();
      this.addRenderableWidget(this.radianceButton);
      this.addRenderableWidget(this.contagionButton);
      this.seed = new Random().nextLong();
      this.fadeInTick = FADE_IN_DURATION;
   }

   public void tick() {
      if (fadeInTick > 0) --fadeInTick;
      if (this.minecraft != null) {
         this.minecraft.getMusicManager().tick();
         this.minecraft.getSoundManager().tick(false);
         if (!this.minecraft.getMusicManager().isPlayingMusic(ModMusics.DEEP_FOREST)) {
            this.minecraft.getMusicManager().startPlaying(ModMusics.DEEP_FOREST);
         }
      }
      if (this.radianceButton.isHovered() && this.spreadFactor < MAX_CENTER_FACTOR) {
         this.spreadFactor += SPREAD_FACTOR;
      } else if (this.contagionButton.isHovered() && this.spreadFactor > MIN_CENTER_FACTOR) {
         this.spreadFactor -= SPREAD_FACTOR;
      } else if (!this.radianceButton.isHovered() && !this.contagionButton.isHovered()) {
         if (this.spreadFactor > 0.5f + SPREAD_FACTOR / 2f) this.spreadFactor -= SPREAD_FACTOR;
         else if (this.spreadFactor < 0.5f - SPREAD_FACTOR / 2f) this.spreadFactor += SPREAD_FACTOR;
      }
   }

   public void onClose() {this.respawn();}

   private void respawn() {this.onFinished.run();}

   private void onRadianceClicked() {
      PacketHandler.sendToServer(new SwitchAchievement(0));
      this.respawn();
   }

   private void onContagionClicked() {
      PacketHandler.sendToServer(new SwitchAchievement(1));
      this.respawn();
   }

   public void render(@NotNull GuiGraphics graphics, int x, int y, float a) {
      super.render(graphics, x, y, a);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
      graphics.blit(GUI, VIGNETTE_LOCATION, 0, 0,
              0, 0, this.width, this.height, this.width, this.height);
      RenderSystem.disableBlend();
      RenderSystem.defaultBlendFunc();
      this.renderButtonOverlay(graphics);
   }

   private void renderButtonOverlay(@NotNull GuiGraphics graphics) {
      graphics.blit(GUI, CORRUPTION_BUTTON_OVERLAY,
              this.contagionButton.getX() - OVERLAY_MARGIN / 2,
              this.contagionButton.getY() - OVERLAY_MARGIN / 2,
              0, 0,
              this.contagionButton.getWidth() + OVERLAY_MARGIN,
              this.contagionButton.getHeight()  + OVERLAY_MARGIN,
              this.contagionButton.getWidth()  + OVERLAY_MARGIN,
              this.contagionButton.getHeight()  + OVERLAY_MARGIN);
      graphics.blit(GUI, RADIANCE_BUTTON_OVERLAY,
              this.radianceButton.getX() - OVERLAY_MARGIN / 2,
              this.radianceButton.getY()  - OVERLAY_MARGIN / 2,
              0, 0,
              this.radianceButton.getWidth() + OVERLAY_MARGIN,
              this.radianceButton.getHeight()  + OVERLAY_MARGIN,
              this.radianceButton.getWidth()  + OVERLAY_MARGIN,
              this.radianceButton.getHeight()  + OVERLAY_MARGIN);
   }

   public void renderBackground(@NotNull GuiGraphics graphics, int x, int y, float a) {
      Random random = new Random(this.seed);
      //graphics.setColor(0.5F, 0.5F, 0.5F, 1F);
      float min = 0.1f * this.width;
      float max = 0.8f * this.width;
      for (int i = 0; i < (this.width / 16) + 1; i++) {
         for (int j = 0; j < (this.height / 16) + 1; j++) {
            if (i*16 < min) {
               graphics.blit(GUI, CORRUPTION_LOCATION, i * 16, j * 16,
                       0, 0.0F, 0,
                       16, 16, 16, 16);
            } else if (i*16 > max) {
               graphics.blit(GUI, RADIANCE_LOCATION, i * 16, j * 16,
                       0, 0.0F, 0,
                       16, 16, 16, 16);
            } else {
               float distanceFromLeft = (float)i * 16 - min;
               float maxDistance = max - min;
               float contagionProbability = Math.max(0.0F,
                       1.0F - distanceFromLeft / maxDistance * (2f * this.spreadFactor));
               if (random.nextFloat() < contagionProbability) {
                  graphics.blit(GUI, CORRUPTION_LOCATION, i * 16, j * 16,
                          0, 0.0F, 0,
                          16, 16, 16, 16);
               } else {
                  graphics.blit(GUI, RADIANCE_LOCATION, i * 16, j * 16,
                          0, 0.0F, 0,
                          16, 16, 16, 16);
               }
            }
         }
      }
   }

   public void removed() {
      if (this.minecraft != null) {
         this.minecraft.getMusicManager().stopPlaying(this.getBackgroundMusic());
      }
   }

   public @NotNull Music getBackgroundMusic() {
      return ModMusics.DEEP_FOREST;
   }

   @Override
   public boolean shouldCloseOnEsc() {
      return false;
   }
}