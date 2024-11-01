package wardentools.gui.winscreen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import wardentools.ModMain;
import wardentools.sounds.ModMusics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CustomWinScreen extends Screen {
   private static final Logger LOGGER = LogUtils.getLogger();
   private static final ResourceLocation VIGNETTE_LOCATION
           = new ResourceLocation("textures/misc/vignette.png");
   private static final String WIN_TXT = "wardentools:texts/win.txt";
   private static final String MOD_CREDITS = "wardentools:texts/mod_credits.json";
   private static final ResourceLocation MOD_BACKGROUND_LOCATION
           = new ResourceLocation(ModMain.MOD_ID, "textures/gui/background.png");
   private static final Component SECTION_HEADING
           = Component.literal("========================").withStyle(ChatFormatting.WHITE);
   private static final String OBFUSCATE_TOKEN
           = "" + ChatFormatting.YELLOW
           + ChatFormatting.OBFUSCATED + ChatFormatting.GREEN + ChatFormatting.AQUA;
   private static final float SPEEDUP_FACTOR = 5.0F;
   private static final float SPEEDUP_FACTOR_FAST = 15.0F;
   private final boolean poem;
   private final Runnable onFinished;
   private float scroll;
   private List<FormattedCharSequence> lines;
   private IntSet centeredLines;
   private int totalScrollLength;
   private boolean speedupActive;
   private final IntSet speedupModifiers = new IntOpenHashSet();
   private float scrollSpeed;
   private final float unmodifiedScrollSpeed;
   private int direction;
   private final LogoRenderer logoRenderer = new LogoRenderer(false);
   private int musicTickCountDown = 0;
   private static final int MUSIC_TICK_COUNTDOWN = 80;

   public CustomWinScreen(boolean hasPoem, Runnable runnable) {
      super(GameNarrator.NO_TITLE);
      this.poem = hasPoem;
      this.onFinished = runnable;
      if (!hasPoem) this.unmodifiedScrollSpeed = 0.75F;
      else this.unmodifiedScrollSpeed = 0.5F;
      this.direction = 1;
      this.scrollSpeed = this.unmodifiedScrollSpeed;
   }

   private float calculateScrollSpeed() {
      return this.speedupActive ? this.unmodifiedScrollSpeed
              * (SPEEDUP_FACTOR + (float)this.speedupModifiers.size() * SPEEDUP_FACTOR_FAST)
              * (float)this.direction : this.unmodifiedScrollSpeed * (float)this.direction;
   }

   public void tick() {
      if (this.minecraft != null) {
         this.minecraft.getMusicManager().tick();
         this.minecraft.getSoundManager().tick(false);
      }
      if (this.musicTickCountDown == 1 && this.minecraft != null) {
         this.minecraft.getMusicManager().startPlaying(this.getBackgroundMusic());
         --this.musicTickCountDown;
      } else if (this.musicTickCountDown > 0) --this.musicTickCountDown;
      float f = (float)(this.totalScrollLength + this.height + this.height + 24);
      if (this.scroll > f) this.changeToChoiceScreen();
   }

   public boolean keyPressed(int key, int i, int j) {
      if (key == 265) this.direction = -1;
      else if (key != 341 && key != 345) {
         if (key == 32) this.speedupActive = true;
         else this.speedupModifiers.add(key);
      }
      this.scrollSpeed = this.calculateScrollSpeed();
      return super.keyPressed(key, i, j);
   }

   public boolean keyReleased(int key, int i, int j) {
      if (key == 265) this.direction = 1;
      if (key == 32) this.speedupActive = false;
      else if (key == 341 || key == 345) this.speedupModifiers.remove(key);
      this.scrollSpeed = this.calculateScrollSpeed();
      return super.keyReleased(key, i, j);
   }

   public void onClose() {this.changeToChoiceScreen();}

   private void changeToChoiceScreen() {
      if (this.minecraft != null) {
         ChoiceScreen choiseScreen = new ChoiceScreen(this.onFinished);
         choiseScreen.init(this.minecraft, this.minecraft.getWindow().getGuiScaledWidth(),
                 this.minecraft.getWindow().getGuiScaledHeight());
         this.minecraft.setScreen(choiseScreen);
      }
   }

   protected void init() {
      if (this.lines == null) {
         this.lines = Lists.newArrayList();
         this.centeredLines = new IntOpenHashSet();
         if (this.poem) this.wrapCreditsIO(WIN_TXT, this::addPoemFile);
         this.wrapCreditsIO(MOD_CREDITS, this::addCreditsFile);
         if (this.poem) this.wrapCreditsIO("texts/postcredits.txt", this::addPoemFile);
         this.totalScrollLength = this.lines.size() * 12;
      }
      this.musicTickCountDown = MUSIC_TICK_COUNTDOWN;
   }

   private void wrapCreditsIO(String name, CreditsReader reader1) {
      if (this.minecraft != null) {
         try (Reader reader = this.minecraft.getResourceManager().openAsReader(new ResourceLocation(name))) {
            reader1.read(reader);
         } catch (Exception exception) {
            LOGGER.error("Couldn't load credits", (Throwable)exception);
         }
      }
   }

   private void addPoemFile(Reader reader) throws IOException {
      BufferedReader bufferedreader = new BufferedReader(reader);
      RandomSource randomsource = RandomSource.create(8124371L);
      if (this.minecraft != null) {
         String s;
         while((s = bufferedreader.readLine()) != null) {
            int i;
            String s1;
            String s2;
            for(s = s.replaceAll("PLAYERNAME", this.minecraft.getUser().getName());
                (i = s.indexOf(OBFUSCATE_TOKEN))
                        != -1; s = s1 + ChatFormatting.WHITE
                    + ChatFormatting.OBFUSCATED + "XXXXXXXX"
                    .substring(0, randomsource.nextInt(4) + 3) + s2) {
               s1 = s.substring(0, i);
               s2 = s.substring(i + OBFUSCATE_TOKEN.length());
            }
            this.addPoemLines(s);
            this.addEmptyLine();
         }
         for(int j = 0; j < 8; ++j) {
            this.addEmptyLine();
         }
      }
   }

   private void addCreditsFile(Reader reader) {
      for(JsonElement jsonelement : GsonHelper.parseArray(reader)) {
         JsonObject jsonobject = jsonelement.getAsJsonObject();
         String s = jsonobject.get("section").getAsString();
         this.addCreditsLine(SECTION_HEADING, true);
         this.addCreditsLine(Component.literal(s).withStyle(ChatFormatting.YELLOW), true);
         this.addCreditsLine(SECTION_HEADING, true);
         this.addEmptyLine();
         this.addEmptyLine();

         for(JsonElement jsonelement1 : jsonobject.getAsJsonArray("disciplines")) {
            JsonObject jsonobject1 = jsonelement1.getAsJsonObject();
            String s1 = jsonobject1.get("discipline").getAsString();
            if (StringUtils.isNotEmpty(s1)) {
               this.addCreditsLine(Component.literal(s1).withStyle(ChatFormatting.YELLOW), true);
               this.addEmptyLine();
               this.addEmptyLine();
            }

            for(JsonElement jsonelement2 : jsonobject1.getAsJsonArray("titles")) {
               JsonObject jsonobject2 = jsonelement2.getAsJsonObject();
               String s2 = jsonobject2.get("title").getAsString();
               JsonArray jsonarray = jsonobject2.getAsJsonArray("names");
               this.addCreditsLine(Component.literal(s2).withStyle(ChatFormatting.GRAY), false);

               for(JsonElement jsonelement3 : jsonarray) {
                  String s3 = jsonelement3.getAsString();
                  this.addCreditsLine(Component.literal("           ").append(s3).withStyle(ChatFormatting.WHITE), false);
               }

               this.addEmptyLine();
               this.addEmptyLine();
            }
         }
      }

   }

   private void addEmptyLine() {
      this.lines.add(FormattedCharSequence.EMPTY);
   }

   private void addPoemLines(String p_181398_) {
      if (this.minecraft != null) {
         this.lines.addAll(this.minecraft.font.split(Component.literal(p_181398_), 256));
      }
   }

   private void addCreditsLine(Component component, boolean center) {
      if (center) this.centeredLines.add(this.lines.size());
      this.lines.add(component.getVisualOrderText());
   }

   public void render(@NotNull GuiGraphics graphics, int x, int y, float a) {
      this.scroll = Math.max(0.0F, this.scroll + a * this.scrollSpeed);
      super.render(graphics, x, y, a);
      int i = this.width / 2 - 128;
      int j = this.height + 50;
      float f = -this.scroll;
      graphics.pose().pushPose();
      graphics.pose().translate(0.0F, f, 0.0F);
      this.logoRenderer.renderLogo(graphics, this.width, 1.0F, j);
      int k = j + 100;

      for(int l = 0; l < this.lines.size(); ++l) {
         if (l == this.lines.size() - 1) {
            float f1 = (float)k + f - (float)(this.height / 2 - 6);
            if (f1 < 0.0F) {
               graphics.pose().translate(0.0F, -f1, 0.0F);
            }
         }

         if ((float)k + f + 12.0F + 8.0F > 0.0F && (float)k + f < (float)this.height) {
            FormattedCharSequence formattedcharsequence = this.lines.get(l);
            if (this.centeredLines.contains(l)) {
               graphics.drawCenteredString(
                       this.font, formattedcharsequence, i + 128, k, 16777215);
            } else {
               graphics.drawString(this.font, formattedcharsequence, i, k, 16777215);
            }
         }

         k += 12;
      }

      graphics.pose().popPose();
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
      float f = this.scroll * 0.5F;
      int j = 64;
      float f1 = this.scroll / this.unmodifiedScrollSpeed;
      float f2 = f1 * 0.02F;
      float f3 = (float)(this.totalScrollLength + this.height + this.height + 24)
              / this.unmodifiedScrollSpeed;
      float f4 = (f3 - 20.0F - f1) * 0.005F;
      if (f4 < f2) f2 = f4;
      if (f2 > 1.0F)  f2 = 1.0F;
      f2 *= f2;
      f2 = f2 * 96.0F / 255.0F;
      graphics.setColor(f2, f2, f2, 1.0F);
      graphics.blit(MOD_BACKGROUND_LOCATION, 0, 0, 0, 0.0F,
              f, i, this.height, 64, 64);
      graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public @NotNull Music getBackgroundMusic() {
      return ModMusics.DEEP_FOREST;
   }

   @FunctionalInterface
   @OnlyIn(Dist.CLIENT)
   interface CreditsReader {
      void read(Reader p_232822_) throws IOException;
   }
}