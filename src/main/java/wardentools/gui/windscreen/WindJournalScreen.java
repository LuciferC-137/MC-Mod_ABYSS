package wardentools.gui.windscreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
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
    public static final ResourceLocation JOURNAL_FIRST_PAGE_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/wind_journal_first.png");
    public static final ResourceLocation JOURNAL_TEXTURE
            = ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "textures/gui/wind_journal/wind_journal.png");
    public static final ChatFormatting TEXT_COLOR = ChatFormatting.DARK_RED;
    protected static final int TOP_MARGIN = 10;
    protected static final int TEXTURE_WIDTH = 292;
    protected static final int TEXTURE_HEIGHT = 180;
    protected static final int TEXT_WIDTH = 114;
    protected static final int TEXT_HEIGHT = 128;
    protected static final int LINE_HEIGHT = 9;
    protected static final int MAX_LINES_PER_PAGE = TEXT_HEIGHT / LINE_HEIGHT;
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
        this.journalAccess.initPages(this.font);
        this.createMenuControls();
        this.createPageControlButtons();
    }

    protected void createMenuControls() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.onClose();
        }).bounds(this.width / 2 - 100, 196, 200, 20).build());
    }

    protected void createPageControlButtons() {
        int buttonXMargin = 20;
        this.nextPageButton = this.addRenderableWidget(new PageButton(
                leftMargin() + TEXTURE_WIDTH - 2 * buttonXMargin - 4,
                footerYPosition(), true,
                (button) -> {this.goToNextPage();}, this.playSoundOnPageTurn));
        this.previousPageButton = this.addRenderableWidget(new PageButton(
                leftMargin() + buttonXMargin,
                footerYPosition(), false,
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
        this.updateCache();
        super.render(graphics, mouseX, mouseY, partialTick);

        if (this.cachedLeftPageIndex >= 1) {
            this.renderPages(graphics);
            this.renderHoverStyle(graphics, mouseX, mouseY);
        } else if (this.cachedLeftPageIndex == 0) {
            this.renderFirstPage(graphics);
        }

        this.nextPageButton.setFocused(false);
        this.previousPageButton.setFocused(false);
    }

    private void updateCache() {
        if (this.cachedLeftPageIndex != this.currentLeftPageIndex) {
            int maxPages = Math.max(this.getTotalPageCount(), 1);
            maxPages += 1 - maxPages % 2;
            if (this.currentLeftPageIndex == 0) {
                this.cachedRightPageLines = this.journalAccess.getPage(this.currentLeftPageIndex);
            } else {
                this.cachedLeftPageLines = this.journalAccess.getPage(this.currentLeftPageIndex - 1);
                this.cachedRightPageLines = this.journalAccess.getPage(this.currentLeftPageIndex);
                this.leftPageIndicatorText = baseText((this.currentLeftPageIndex) + "/" + maxPages);
            }
            this.rightPageIndicatorText = baseText((this.currentLeftPageIndex + 1) + "/" + maxPages);
            this.cachedLeftPageIndex = this.currentLeftPageIndex;
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

    private void renderPages(GuiGraphics graphics) {
        Objects.requireNonNull(this.font);
        this.renderPage(graphics, false);
        this.renderPage(graphics, true);
    }

    private void renderFirstPage(GuiGraphics graphics) {
        this.renderPage(graphics, true);
    }

    private void renderPage(GuiGraphics graphics, boolean isRightPage) {
        List<FormattedCharSequence> pageLines = isRightPage ? this.cachedRightPageLines : this.cachedLeftPageLines;
        int visibleRightLines = Math.min(MAX_LINES_PER_PAGE, pageLines.size());
        int textX = leftMargin() + PAGE_TEXT_X_OFFSET;
        if (isRightPage) textX += TEXT_WIDTH + 2 * PAGE_TEXT_X_OFFSET - 8;
        for (int lineIndex = 0; lineIndex < visibleRightLines; ++lineIndex) {
            FormattedCharSequence line = pageLines.get(lineIndex);
            Font currentFont = this.font;
            Objects.requireNonNull(this.font);
            graphics.drawString(currentFont, line, textX,
                    PAGE_TEXT_Y_OFFSET + lineIndex * LINE_HEIGHT, 0, false);
        }
        this.renderPageIndex(graphics, isRightPage);
    }

    private void renderPageIndex(GuiGraphics graphics, boolean isRightPage) {
        Component pageIndicatorText = isRightPage ? this.rightPageIndicatorText : this.leftPageIndicatorText;
        int xPosition = isRightPage ? leftMargin() + TEXTURE_WIDTH / 2 + 11  : leftMargin() + TEXTURE_WIDTH / 2 - 34;
        graphics.drawString(this.font, pageIndicatorText, xPosition,
                footerYPosition(), 0, false);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(graphics);
        if (this.cachedLeftPageIndex == 0) {
            graphics.blit(JOURNAL_FIRST_PAGE_TEXTURE, leftMargin(), TOP_MARGIN,
                    0, 0, TEXTURE_WIDTH,
                    TEXTURE_HEIGHT , TEXTURE_WIDTH, TEXTURE_HEIGHT);
        } else {
            graphics.blit(JOURNAL_TEXTURE, leftMargin(), TOP_MARGIN,
                    0, 0, TEXTURE_WIDTH,
                    TEXTURE_HEIGHT , TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
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

    private int footerYPosition() {return TOP_MARGIN + TEXTURE_HEIGHT - 23;}

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

    private static Component baseText(String text) {
        return Component.literal(text).withStyle(TEXT_COLOR);
    }

    private static Component baseText(String text, boolean bold, boolean italic) {
        if (bold && italic) {
            return Component.literal(text).withStyle(TEXT_COLOR).withStyle(ChatFormatting.BOLD)
                    .withStyle(ChatFormatting.ITALIC);
        } else if (bold) {
            return Component.literal(text).withStyle(TEXT_COLOR).withStyle(ChatFormatting.BOLD);
        }
        return Component.literal(text).withStyle(TEXT_COLOR).withStyle(ChatFormatting.ITALIC);
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
        private final List<List<Component>> sections;
        private List<List<FormattedCharSequence>> pages = new ArrayList<>();

        public JournalAccess() {
            this.sections = new ArrayList<>();
            this.sections.addAll(buildSectionsList(Minecraft.getInstance()));
        }

        public void initPages(@NotNull Font font) {
            this.pages = this.buildPagesList(font);
        }

        private List<List<FormattedCharSequence>> buildPagesList(Font font) {
            List<List<FormattedCharSequence>> pages = new ArrayList<>();
            List<Integer> sectionPageIndex = new ArrayList<>();
            int pageIndex = 1;
            for (List<Component> section : this.sections) {
                // New page at the beginning of each section
                List<FormattedCharSequence> currentPage = new ArrayList<>();
                int currentLineCount = 0;

                for (Component component : section) {
                    List<FormattedCharSequence> lines = font.split(component, TEXT_WIDTH);
                    for (FormattedCharSequence line : lines) {
                        if (currentLineCount >= MAX_LINES_PER_PAGE) {
                            if (!currentPage.isEmpty()) {
                                pages.add(currentPage);
                                pageIndex++;
                            }
                            currentPage = new ArrayList<>();
                            currentLineCount = 0;
                        }
                        currentPage.add(line);
                        currentLineCount++;
                    }
                }
                if (!currentPage.isEmpty()) {
                    pages.add(currentPage);
                    pageIndex++;
                }
                sectionPageIndex.add(pageIndex);
            }

            // Replace XX with page proper page index
            int indexParsing = 0;
            List<FormattedCharSequence> firstPage = pages.getFirst();
            for (int k = 0; k < firstPage.size(); k++) {
                FormattedCharSequence line = firstPage.get(k);
                String lineText = extractText(line);
                if (lineText.contains("XX") && indexParsing < sectionPageIndex.size()) {
                    String newLine = lineText.replace("XX", sectionPageIndex.get(indexParsing) + "");
                    firstPage.set(k, buildSimpleLine(newLine, font));
                    indexParsing++;
                }
            }
            return pages;
        }



        public static String extractText(FormattedCharSequence sequence) {
            StringBuilder builder = new StringBuilder();
            sequence.accept((index, style, codePoint) -> {
                builder.appendCodePoint(codePoint);
                return true;
            });
            return builder.toString();
        }

        private List<List<Component>> buildSectionsList(Minecraft minecraft) {
            if (minecraft == null || minecraft.player == null) return List.of();
            List<List<Component>> sections = new ArrayList<>();

            // Cover page
            List<Component> indexSection = new ArrayList<>();
            StringBuilder coverPage = new StringBuilder();
            indexSection.add(baseText("= WIND JOURNAL =\n\n", true, false));
            coverPage.append("Table of Contents:\n");
            for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
                coverPage.append("\n")
                        .append(tag.getName().replace("=", ""))
                        .append(" - ")
                        .append("XX"); // Placeholder for index
            }
            indexSection.add(WindJournalScreen.baseText(coverPage.toString()));
            sections.add(indexSection);

            // One section per tag
            for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
                List<Component> section = new ArrayList<>();
                section.add(baseText(tag.getName() + "\n\n", true, false));
                section.addAll(getWhispersTextByTag(minecraft, tag));
                sections.add(section);
            }

            return sections;
        }


        private List<Component> getWhispersTextByTag(Minecraft minecraft, WhisperTags.Tag tag){
            if (minecraft == null || minecraft.player == null) return List.of();
            List<Component> whispers = new ArrayList<>();
            minecraft.player.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                List<Whisper> whispersList = WhisperManager.WHISPERS.whisperTags.getWhispersWithTag(tag);
                for (Whisper whisper : whispersList) {
                    if (data.knowsWhisper(whisper.id())) {
                        whispers.add(baseText(whisper.id() + " - " + whisper.whisper() + "\n"));
                    } else {
                        whispers.add(baseText(whisper.id() + " - LOCKED\n", false, true));
                    }
                }
            });
            return whispers;
        }

        private static FormattedCharSequence buildSimpleLine(String text, Font font) {
            return font.split(baseText(text), TEXT_WIDTH + 10).getFirst();
        }

        public int getPageCount() {
            return this.pages.size();
        }

        public List<FormattedCharSequence> getPage(int pageIndex) {
            return pageIndex >= 0 && pageIndex < this.getPageCount() ? this.pages.get(pageIndex) : List.of();
        }
    }
}