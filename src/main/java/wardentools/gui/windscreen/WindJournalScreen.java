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
import wardentools.misc.wind.Whisper;
import wardentools.misc.wind.WhisperManager;
import wardentools.playerdata.KnownWhispersDataProvider;

@OnlyIn(Dist.CLIENT)
public class WindJournalScreen extends Screen {
    public static final int PAGE_INDICATOR_TEXT_Y_OFFSET = 16;
    public static final int PAGE_TEXT_X_OFFSET = 36;
    public static final int PAGE_TEXT_Y_OFFSET = 30;
    public static final ResourceLocation JOURNAL_TEXTURE
            = ResourceLocation.withDefaultNamespace("textures/gui/book.png");
    protected static final int TEXT_WIDTH = 114;
    protected static final int TEXT_HEIGHT = 128;
    protected static final int IMAGE_WIDTH = 192;
    protected static final int IMAGE_HEIGHT = 192;
    private JournalAccess journalAccess;
    private int currentPageIndex;
    private List<FormattedCharSequence> cachedPageLines;
    private int cachedPageIndex;
    private Component pageIndicatorText;
    private PageButton nextPageButton;
    private PageButton previousPageButton;
    private final boolean playSoundOnPageTurn;

    public WindJournalScreen() {
        this(new JournalAccess(), true);
    }

    private WindJournalScreen(JournalAccess journalAccess, boolean playSoundOnPageTurn) {
        super(GameNarrator.NO_TITLE);
        this.cachedPageLines = Collections.emptyList();
        this.cachedPageIndex = -1;
        this.pageIndicatorText = CommonComponents.EMPTY;
        this.journalAccess = journalAccess;
        this.playSoundOnPageTurn = playSoundOnPageTurn;
    }



    public boolean setPage(int pageIndex) {
        int clampedPageIndex = Mth.clamp(pageIndex, 0, this.journalAccess.getPageCount() - 1);
        if (clampedPageIndex != this.currentPageIndex) {
            this.currentPageIndex = clampedPageIndex;
            this.updatePageButtonVisibility();
            this.cachedPageIndex = -1;
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
        int xOffset = (this.width - IMAGE_WIDTH) / 2;
        this.nextPageButton = this.addRenderableWidget(new PageButton(xOffset + 116,
                159, true, (button) -> {
            this.goToNextPage();
        }, this.playSoundOnPageTurn));
        this.previousPageButton = this.addRenderableWidget(new PageButton(xOffset + 43,
                159, false, (button) -> {
            this.goToPreviousPage();
        }, this.playSoundOnPageTurn));
        this.updatePageButtonVisibility();
    }

    private int getTotalPageCount() {
        return this.journalAccess.getPageCount();
    }

    protected void goToPreviousPage() {
        if (this.currentPageIndex > 0) {
            --this.currentPageIndex;
        }
        this.updatePageButtonVisibility();
    }

    protected void goToNextPage() {
        if (this.currentPageIndex < this.getTotalPageCount() - 1) {
            ++this.currentPageIndex;
        }
        this.updatePageButtonVisibility();
    }

    private void updatePageButtonVisibility() {
        this.nextPageButton.visible = this.currentPageIndex < this.getTotalPageCount() - 1;
        this.previousPageButton.visible = this.currentPageIndex > 0;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        } else {
            return switch (keyCode) {
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
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        int xOffset = (this.width - IMAGE_WIDTH) / 2;
        if (this.cachedPageIndex != this.currentPageIndex) {
            FormattedText pageContent = this.journalAccess.getPage(this.currentPageIndex);
            this.cachedPageLines = this.font.split(pageContent, TEXT_WIDTH);
            this.pageIndicatorText = Component.translatable("book.pageIndicator", new Object[]{this.currentPageIndex + 1, Math.max(this.getTotalPageCount(), 1)});
        }

        this.cachedPageIndex = this.currentPageIndex;
        int pageIndicatorWidth = this.font.width(this.pageIndicatorText);
        graphics.drawString(this.font, this.pageIndicatorText, xOffset - pageIndicatorWidth + IMAGE_WIDTH - 44, 18, 0, false);
        Objects.requireNonNull(this.font);
        int visibleLines = Math.min(TEXT_HEIGHT / 9, this.cachedPageLines.size());

        for(int lineIndex = 0; lineIndex < visibleLines; ++lineIndex) {
            FormattedCharSequence line = this.cachedPageLines.get(lineIndex);
            Font currentFont = this.font;
            int textX = xOffset + PAGE_TEXT_X_OFFSET;
            Objects.requireNonNull(this.font);
            graphics.drawString(currentFont, line, textX, PAGE_TEXT_Y_OFFSET + lineIndex * 9, 0, false);
        }

        Style hoveredStyle = this.getStyleUnderMouse(mouseX, mouseY);
        if (hoveredStyle != null) {
            graphics.renderComponentHoverEffect(this.font, hoveredStyle, mouseX, mouseY);
        }
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(graphics);
        graphics.blit(JOURNAL_TEXTURE, (this.width - IMAGE_WIDTH) / 2,
                2, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            Style clickedStyle = this.getStyleUnderMouse(mouseX, mouseY);
            if (clickedStyle != null && this.handleComponentClicked(clickedStyle)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
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

    @Nullable
    public Style getStyleUnderMouse(double mouseX, double mouseY) {
        if (!this.cachedPageLines.isEmpty()) {
            int relativeX = Mth.floor(mouseX - (double) ((this.width - IMAGE_WIDTH) / 2) - PAGE_TEXT_X_OFFSET);
            int relativeY = Mth.floor(mouseY - 2.0 - PAGE_TEXT_Y_OFFSET);
            if (relativeX >= 0 && relativeY >= 0) {
                Objects.requireNonNull(this.font);
                int visibleLines = Math.min(TEXT_HEIGHT / 9, this.cachedPageLines.size());
                if (relativeX <= TEXT_WIDTH) {
                    if (this.minecraft != null) {
                        Objects.requireNonNull(this.minecraft.font);
                        if (relativeY < 9 * visibleLines + visibleLines) {
                            Objects.requireNonNull(this.minecraft.font);
                            int lineIndex = relativeY / 9;
                            if (lineIndex >= 0 && lineIndex < this.cachedPageLines.size()) {
                                FormattedCharSequence line = this.cachedPageLines.get(lineIndex);
                                return this.minecraft.font.getSplitter().componentStyleAtWidth(line, relativeX);
                            }
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    private static class JournalAccess {
        private final List<Component> pages;

        public JournalAccess() {
            this(new ArrayList<>());
        }

        public JournalAccess(List<Component> pages) {
            this.pages = pages;
            this.pages.addAll(buildWhisperList(Minecraft.getInstance()));
        }

        public List<Component> buildWhisperList(Minecraft minecraft) {
            if (minecraft == null || minecraft.player == null) return List.of();
            List<Component> whispers = new ArrayList<>();
            minecraft.player.getCapability(KnownWhispersDataProvider.WHISPERS_CAPABILITY).ifPresent(data -> {
                for (int id : data.getAll()) {
                    Whisper whisper = WhisperManager.WHISPERS.getWhisper(id);
                    whispers.add(Component.literal(whisper.whisper()));
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