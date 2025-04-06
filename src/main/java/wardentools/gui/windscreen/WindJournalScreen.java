package wardentools.gui.windscreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.ClickEvent.Action;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.misc.wind.Whisper;
import wardentools.misc.wind.WhisperManager;
import wardentools.misc.wind.WhisperTags;
import wardentools.playerdata.KnownWhispersDataProvider;

@OnlyIn(Dist.CLIENT)
public class WindJournalScreen extends Screen {
    public static final int PAGE_TEXT_X_OFFSET = 18;
    public static final int PAGE_TEXT_Y_OFFSET = 30;
    public static final ResourceLocation JOURNAL_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal.png");
    protected static final int TOP_MARGIN = 10;
    protected static final int TEXTURE_WIDTH = 292;
    protected static final int TEXTURE_HEIGHT = 180;
    protected static final int TEXT_WIDTH = 114;
    protected static final int TEXT_HEIGHT = 128;
    protected static final int LINE_HEIGHT = 9;
    private final JournalAccess journalAccess;
    private int currentLeftPageIndex;
    private List<FormattedCharSequence> cachedLeftPageLines;
    private List<FormattedCharSequence> cachedRightPageLines;
    private int cachedLeftPageIndex;
    private Component leftPageIndicatorText;
    private Component rightPageIndicatorText;
    private PageButton nextPageButton;
    private PageButton previousPageButton;
    private final boolean playSoundOnPageTurn;

    public WindJournalScreen() {
        this(new JournalAccess(), true);
    }

    private WindJournalScreen(JournalAccess journalAccess, boolean playSoundOnPageTurn) {
        super(GameNarrator.NO_TITLE);
        this.cachedLeftPageLines = Collections.emptyList();
        this.cachedRightPageLines = Collections.emptyList();
        this.cachedLeftPageIndex = -1;
        this.leftPageIndicatorText = CommonComponents.EMPTY;
        this.journalAccess = journalAccess;
        this.playSoundOnPageTurn = playSoundOnPageTurn;
    }

    public boolean setPage(int pageIndex) {
        int clampedPageIndex = Mth.clamp(pageIndex, 0, this.journalAccess.getPageCount() - 1);
        if (clampedPageIndex != this.currentLeftPageIndex) {
            this.currentLeftPageIndex = clampedPageIndex;
            this.updatePageButtonVisibility();
            this.cachedLeftPageIndex = -1;
            return true;
        } else return false;
    }

    protected boolean forcePage(int pageIndex) {
        return this.setPage(pageIndex);
    }

    protected void init() {
        this.createMenuControls();
        this.createPageControlButtons();
    }

    protected void createMenuControls() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.onClose();
        }).bounds(this.width / 2 - 100, 196, 200, 20).build());
    }

    protected void createPageControlButtons() {
        int yButtonPosition = TOP_MARGIN + TEXTURE_HEIGHT - 23;
        int buttonXMargin = 20;
        this.nextPageButton = this.addRenderableWidget(new PageButton(
                leftMargin() + TEXTURE_WIDTH - 2 * buttonXMargin - 4,
                yButtonPosition, true,
                (button) -> {this.goToNextPage();}, this.playSoundOnPageTurn));
        this.previousPageButton = this.addRenderableWidget(new PageButton(
                leftMargin() + buttonXMargin,
                yButtonPosition, false,
                (button) -> {this.goToPreviousPage();}, this.playSoundOnPageTurn));
        this.updatePageButtonVisibility();
    }

    private int getTotalPageCount() {
        return this.journalAccess.getPageCount();
    }

    protected void goToPreviousPage() {
        if (this.currentLeftPageIndex > 0) {
            this.currentLeftPageIndex -= 2;
        }
        this.updatePageButtonVisibility();
    }

    protected void goToNextPage() {
        if (this.currentLeftPageIndex < this.getTotalPageCount() - 1) {
            this.currentLeftPageIndex += 2;
        }
        this.updatePageButtonVisibility();
    }

    private void updatePageButtonVisibility() {
        this.nextPageButton.visible = this.currentLeftPageIndex < this.getTotalPageCount() - 1;
        this.previousPageButton.visible = this.currentLeftPageIndex > 0;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) return true;
        else return switch (keyCode) {
                case 266 -> {
                    this.previousPageButton.onPress();
                    yield true;
                }
                case 267 -> {
                    this.nextPageButton.onPress();
                    yield true;
                }
                default -> false;
            };
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        this.updateCache();

        this.renderPageNumbers(graphics);
        this.renderTextInPage(graphics);
        this.renderHoverStyle(graphics, mouseX, mouseY);

        this.nextPageButton.setFocused(false);
        this.previousPageButton.setFocused(false);
    }

    private void updateCache() {
        if (this.cachedLeftPageIndex != this.currentLeftPageIndex) {
            FormattedText rightPageContent = this.journalAccess.getPage(this.currentLeftPageIndex);
            FormattedText leftPageContent = this.journalAccess.getPage(this.currentLeftPageIndex + 1);
            this.cachedLeftPageLines = this.font.split(rightPageContent, TEXT_WIDTH);
            this.cachedRightPageLines = this.font.split(leftPageContent, TEXT_WIDTH);
            this.cachedLeftPageIndex = this.currentLeftPageIndex;
            int maxPages = Math.max(this.getTotalPageCount(), 1);
            maxPages += maxPages % 2;
            this.leftPageIndicatorText = Component.literal(
                    (this.currentLeftPageIndex + 1) + "/" + maxPages);
            this.rightPageIndicatorText = Component.literal(
                    (this.currentLeftPageIndex + 2) + "/" + maxPages);
        }
    }

    private void renderHoverStyle(GuiGraphics graphics, int mouseX, int mouseY) {
        // DOES NOTHING FOR NOW
        Style hoveredStyle = getStyleUnderMouse(this.font, this.cachedLeftPageLines, mouseX, mouseY,
                leftMargin() + PAGE_TEXT_X_OFFSET, PAGE_TEXT_Y_OFFSET, TEXT_WIDTH, LINE_HEIGHT);
        if (hoveredStyle != null) {
            graphics.renderComponentHoverEffect(this.font, hoveredStyle, mouseX, mouseY);
        }
    }

    private void renderTextInPage(GuiGraphics graphics) {
        Objects.requireNonNull(this.font);
        int visibleLeftLines = Math.min(TEXT_HEIGHT / LINE_HEIGHT, this.cachedLeftPageLines.size());
        int visibleRightLines = Math.min(TEXT_HEIGHT / LINE_HEIGHT, this.cachedRightPageLines.size());
        int textX = leftMargin() + PAGE_TEXT_X_OFFSET;
        for (int lineIndex = 0; lineIndex < visibleLeftLines; ++lineIndex) {
            FormattedCharSequence line = this.cachedLeftPageLines.get(lineIndex);
            Font currentFont = this.font;
            Objects.requireNonNull(this.font);
            graphics.drawString(currentFont, line, textX,
                    PAGE_TEXT_Y_OFFSET + lineIndex * LINE_HEIGHT, 0, false);
        }
        for (int lineIndex = 0; lineIndex < visibleRightLines; ++lineIndex) {
            FormattedCharSequence line = this.cachedRightPageLines.get(lineIndex);
            Font currentFont = this.font;
            Objects.requireNonNull(this.font);
            graphics.drawString(currentFont, line,
                    textX + TEXT_WIDTH + 2 * PAGE_TEXT_X_OFFSET - 4,
                    PAGE_TEXT_Y_OFFSET + lineIndex * LINE_HEIGHT, 0, false);
        }
    }

    private void renderPageNumbers(GuiGraphics graphics) {
        int footerYPosition = TOP_MARGIN + TEXTURE_HEIGHT - 23;
        graphics.drawString(this.font, this.leftPageIndicatorText,
                leftMargin() + TEXTURE_WIDTH / 2 - 39,
                footerYPosition, 0, false);
        graphics.drawString(this.font, this.rightPageIndicatorText,
                leftMargin() + TEXTURE_WIDTH / 2 + 18,
                footerYPosition, 0, false);

    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(graphics);
        graphics.blit(JOURNAL_TEXTURE, leftMargin(), TOP_MARGIN,
                0, 0, TEXTURE_WIDTH,
                TEXTURE_HEIGHT , TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            Style clickedStyle = getStyleUnderMouse(this.font, this.cachedLeftPageLines, mouseX, mouseY,
                    leftMargin() + PAGE_TEXT_X_OFFSET, PAGE_TEXT_Y_OFFSET, TEXT_WIDTH, LINE_HEIGHT);
            if (clickedStyle != null && this.handleComponentClicked(clickedStyle)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private int leftMargin() {
        return (this.width - TEXTURE_WIDTH) / 2;
    }

    public boolean handleComponentClicked(@Nullable Style style) {
        if (style == null) return false;
        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent == null) {
            return false;
        } else if (clickEvent.getAction() == Action.CHANGE_PAGE) {
            String pageValue = clickEvent.getValue();

            try {
                int pageIndex = Integer.parseInt(pageValue) - 1;
                return this.forcePage(pageIndex);
            } catch (Exception exception) {
                return false;
            }
        } else {
            boolean handled = super.handleComponentClicked(style);
            if (handled && clickEvent.getAction() == Action.RUN_COMMAND) {
                this.closeScreen();
            }
            return handled;
        }
    }

    protected void closeScreen() {
        if (this.minecraft != null) this.minecraft.setScreen(null);
    }

    public static @Nullable Style getStyleUnderMouse(Font font, List<FormattedCharSequence> lines,
                                                     double mouseX, double mouseY,
                                                     int textStartX, int textStartY,
                                                     int maxWidth, int lineHeight) {
        int relativeX = Mth.floor(mouseX - textStartX);
        int relativeY = Mth.floor(mouseY - textStartY);

        if (relativeX < 0 || relativeY < 0 || relativeX > maxWidth) return null;

        int lineIndex = relativeY / lineHeight;
        if (lineIndex >= lines.size()) return null;

        FormattedCharSequence line = lines.get(lineIndex);
        return font.getSplitter().componentStyleAtWidth(line, relativeX);
    }


    @OnlyIn(Dist.CLIENT)
    private static class JournalAccess {
        private final List<Component> pages;

        public JournalAccess() {
            this(new ArrayList<>());
        }

        public JournalAccess(List<Component> pages) {
            this.pages = new ArrayList<>(pages);
            this.pages.clear(); // Clear initial pages to build from scratch
            this.pages.addAll(buildPageList(Minecraft.getInstance()));
        }

        public List<Component> buildPageList(Minecraft minecraft) {
            if (minecraft == null || minecraft.player == null) return List.of();
            List<Component> pages = new ArrayList<>();

            // Add cover page with all tags
            StringBuilder coverPage = new StringBuilder();
            coverPage.append("=== WIND JOURNAL ===\n\n");
            coverPage.append("Table of Contents:\n");

            int pageNum = 2; // Starting from page 2 (after cover)
            for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
                coverPage.append("\n")
                        .append(getTagName(tag))
                        .append(" - ")
                        .append(pageNum++);
            }

            pages.add(Component.literal(coverPage.toString()));

            // Add content pages
            for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
                pages.add(createTagPage(minecraft, tag));
            }

            return pages;
        }

        private Component createTagPage(Minecraft minecraft, WhisperTags.Tag tag) {
            // Create a page with header showing the tag name
            StringBuilder page = new StringBuilder();
            page.append("=== ").append(getTagName(tag)).append(" ===\n\n");

            List<String> whispers = getWhispersTextByTag(minecraft, tag);
            for (String whisper : whispers) {
                page.append(whisper).append("\n\n");
            }

            return Component.literal(page.toString());
        }

        private String getTagName(WhisperTags.Tag tag) {
            String name = tag.getName();
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }

        public List<String> getWhispersTextByTag(Minecraft minecraft, WhisperTags.Tag tag){
            if (minecraft == null || minecraft.player == null) return List.of();
            List<String> whispers = new ArrayList<>();
            minecraft.player.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                List<Whisper> whispersList = WhisperManager.WHISPERS.whisperTags.getWhispersWithTag(tag);
                for (Whisper whisper : whispersList) {
                    if (data.knowsWhisper(whisper.id())) {
                        whispers.add(whisper.whisper());
                    } else {
                        whispers.add(whisper.id() + " LOCKED");
                    }
                }
            });
            return whispers;
        }

        public int getPageCount() {
            return this.pages.size();
        }

        public FormattedText getPage(int pageIndex) {
            return pageIndex >= 0 && pageIndex < this.getPageCount() ? this.pages.get(pageIndex) : FormattedText.EMPTY;
        }
    }
}