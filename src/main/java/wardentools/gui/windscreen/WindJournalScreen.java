package wardentools.gui.windscreen;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.ClickEvent.Action;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

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
    private final TaskNoteAccess taskNoteAccess;
    private int currentLeftPageIndex;
    private List<FormattedCharSequence> cachedLeftPageLines;
    private List<FormattedCharSequence> cachedRightPageLines;
    private List<TaskNoteAccess.Task> cachedRightTaskPage;
    private List<TaskNoteAccess.Task> cachedLeftTaskPage;
    private List<CheckButton> cachedLeftTaskButtons;
    private List<CheckButton> cachedRightTaskButtons;
    private int cachedLeftPageIndex;
    private Component leftPageIndicatorText;
    private Component rightPageIndicatorText;
    private PageButton nextPageButton;
    private PageButton previousPageButton;
    private Button firstPageButton;
    private Button jumpToTaskButton;
    private final boolean playSoundOnPageTurn;

    public WindJournalScreen() {
        this(new JournalAccess(), new TaskNoteAccess(), true);
    }

    private WindJournalScreen(JournalAccess journalAccess, TaskNoteAccess taskNoteAccess, boolean playSoundOnPageTurn) {
        super(GameNarrator.NO_TITLE);
        this.cachedLeftPageLines = Collections.emptyList();
        this.cachedRightPageLines = Collections.emptyList();
        this.cachedLeftPageIndex = -1;
        this.leftPageIndicatorText = CommonComponents.EMPTY;
        this.journalAccess = journalAccess;
        this.taskNoteAccess = taskNoteAccess;
        this.playSoundOnPageTurn = playSoundOnPageTurn;
    }

    public boolean setPage(int doublePageIndex) {
        int clampedPageIndex = Mth.clamp(doublePageIndex, 0, this.getTotalPageCount() - 1);
        int newPageIndex = clampedPageIndex - clampedPageIndex % 2; // Avoiding odd page index
        if (newPageIndex != this.currentLeftPageIndex) {
            this.currentLeftPageIndex = newPageIndex;
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
                (button) -> this.goToNextPage(), this.playSoundOnPageTurn));
        this.previousPageButton = this.addRenderableWidget(new PageButton(
                leftMargin() + buttonXMargin,
                footerYPosition(), false,
                (button) -> this.goToPreviousPage(), this.playSoundOnPageTurn));
        this.firstPageButton = this.addRenderableWidget(Button.builder(
                Component.literal(" <-"),
                (button) -> this.setPage(0))
                .bounds(TEXTURE_WIDTH + this.leftMargin() + 5, TOP_MARGIN + 2,
                        12, 12).build());
        this.jumpToTaskButton = this.addRenderableWidget(
                new JumpToTaskButton(this.leftMargin() + 7, TOP_MARGIN + 78,
                (button) -> this.setPage(this.getJournalPageCount() + 2)));
        this.updatePageButtonVisibility();
    }

    private int getTotalPageCount() {
        return this.getJournalPageCount() + this.taskNoteAccess.getPageCount();
    }

    private int getJournalPageCount() {
        int journalPages = this.journalAccess.getPageCount();
        if (journalPages % 2 == 0) {
            journalPages++;
        }
        return journalPages;
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
        this.firstPageButton.visible = this.currentLeftPageIndex != 0;
        this.previousPageButton.visible = this.currentLeftPageIndex > 0;
        this.jumpToTaskButton.visible = this.currentLeftPageIndex == 0;
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
        if (this.cachedLeftPageIndex == 0) {
            this.renderFirstPage(graphics);
        } else if (this.currentLeftPageIndex <= this.journalAccess.getPageCount()) {
            this.renderPages(graphics);
        } else {
            this.renderTaskPages(graphics, mouseX, mouseY, partialTick);
        }
        this.renderHoverStyle(graphics, mouseX, mouseY);
        this.nextPageButton.setFocused(false);
        this.previousPageButton.setFocused(false);
        this.firstPageButton.setFocused(false);
    }

    private void updateCache() {
        if (this.cachedLeftPageIndex != this.currentLeftPageIndex) {
            int maxPages = Math.max(this.getTotalPageCount(), 1);
            maxPages += 1 - maxPages % 2;
            if (this.currentLeftPageIndex == 0) {
                this.cachedRightPageLines = this.journalAccess.getPage(this.currentLeftPageIndex);
                this.removeAllButtons(this.cachedLeftTaskButtons);
                this.removeAllButtons(this.cachedRightTaskButtons);
            } else if (this.currentLeftPageIndex <= this.getJournalPageCount()) {
                this.cachedLeftPageLines = this.journalAccess.getPage(this.currentLeftPageIndex - 1);
                this.cachedRightPageLines = this.journalAccess.getPage(this.currentLeftPageIndex);
                this.leftPageIndicatorText = JournalAccess.baseText((this.currentLeftPageIndex) + "/" + maxPages);
                this.removeAllButtons(this.cachedLeftTaskButtons);
                this.removeAllButtons(this.cachedRightTaskButtons);
            } else {
                // Task section
                int trunkIndex = this.currentLeftPageIndex - this.getJournalPageCount();
                this.cachedLeftTaskPage = this.taskNoteAccess.getTaskPage(trunkIndex - 1);
                this.cachedRightTaskPage = this.taskNoteAccess.getTaskPage(trunkIndex);
                this.cachedLeftTaskButtons = this.taskNoteAccess
                        .getTaskButtons(trunkIndex - 1,
                                this.leftMargin() + PAGE_TEXT_X_OFFSET,
                                TOP_MARGIN + PAGE_TEXT_Y_OFFSET);
                this.cachedRightTaskButtons = this.taskNoteAccess.
                        getTaskButtons(trunkIndex,
                                this.rightPageXStart() + PAGE_TEXT_X_OFFSET,
                                TOP_MARGIN + PAGE_TEXT_Y_OFFSET);
                this.leftPageIndicatorText = JournalAccess.baseText((this.currentLeftPageIndex) + "/" + maxPages);
                this.addAllButtons(this.cachedLeftTaskButtons);
                this.addAllButtons(this.cachedRightTaskButtons);
            }
            this.rightPageIndicatorText = JournalAccess.baseText((this.currentLeftPageIndex + 1) + "/" + maxPages);
            this.cachedLeftPageIndex = this.currentLeftPageIndex;
        }
    }

    private void addAllButtons(@Nullable List<CheckButton> checkButtons) {
        if (checkButtons == null) return;
        for (CheckButton button : checkButtons) {
            this.addRenderableWidget(button);
        }
    }

    private void removeAllButtons(@Nullable List<CheckButton> checkButtons) {
        if (checkButtons == null) return;
        for (CheckButton button : checkButtons) {
            this.removeWidget(button);
        }
    }

    private void renderHoverStyle(GuiGraphics graphics, int mouseX, int mouseY) {
        Style stylePageAt = this.getStylePage(mouseX, mouseY);
        if (stylePageAt != null) {
            graphics.renderComponentHoverEffect(this.font, stylePageAt, mouseX, mouseY);
        }
    }

    private Style getStylePage(int mouseX, int mouseY) {
        return (this.cachedLeftPageIndex <= this.journalAccess.getTableOfContentLength()) ?
                getStyleUnderMouseOnTableOfContentPage(mouseX, mouseY)
                : getStyleUnderMouse(this.font, this.cachedLeftPageLines, mouseX, mouseY,
                this.rightPageXStart(), PAGE_TEXT_Y_OFFSET, TEXT_WIDTH, LINE_HEIGHT);
    }

    private void renderPages(GuiGraphics graphics) {
        Objects.requireNonNull(this.font);
        this.renderPage(graphics, false);
        this.renderPage(graphics, true);
    }
    private void renderTaskPages(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (this.cachedLeftTaskPage != null) this.renderTaskPage(graphics, false, mouseX, mouseY, partialTick);
        if (this.cachedRightTaskPage != null) this.renderTaskPage(graphics, true, mouseX, mouseY, partialTick);
    }

    private void renderFirstPage(GuiGraphics graphics) {
        this.renderPage(graphics, true);
    }

    private void renderPage(GuiGraphics graphics, boolean isRightPage) {
        List<FormattedCharSequence> pageLines = isRightPage ? this.cachedRightPageLines : this.cachedLeftPageLines;
        int visibleRightLines = Math.min(MAX_LINES_PER_PAGE, pageLines.size());
        int textX = !isRightPage ? leftMargin() + PAGE_TEXT_X_OFFSET : this.rightPageXStart();
        for (int lineIndex = 0; lineIndex < visibleRightLines; ++lineIndex) {
            FormattedCharSequence line = pageLines.get(lineIndex);
            Font currentFont = this.font;
            Objects.requireNonNull(this.font);
            graphics.drawString(currentFont, line, textX,
                    PAGE_TEXT_Y_OFFSET + lineIndex * LINE_HEIGHT, 0, false);
        }
        this.renderPageIndex(graphics, isRightPage);
    }

    private void renderTaskPage(GuiGraphics graphics, boolean isRightPage,
                                int mouseX, int mouseY, float partialTick) {
        List<TaskNoteAccess.Task> pageTasks = isRightPage ? this.cachedRightTaskPage : this.cachedLeftTaskPage;
        int xPos = isRightPage ? this.rightPageXStart() : leftMargin() + PAGE_TEXT_X_OFFSET + 10;
        for (int i = 0; i < pageTasks.size(); i++) {
            TaskNoteAccess.Task task = pageTasks.get(i);
            ResourceLocation icon = task.getIcon();
            int yPos = TOP_MARGIN + PAGE_TEXT_Y_OFFSET / 2
                    + i * (TaskNoteAccess.HEIGHT_OF_ONE_TASK + TaskNoteAccess.Y_TASK_MARGIN);
            graphics.blit(icon, xPos,yPos,
                    0, 0,
                    TaskNoteAccess.LENGTH_OF_ONE_TASK, TaskNoteAccess.HEIGHT_OF_ONE_TASK,
                    TaskNoteAccess.LENGTH_OF_ONE_TASK, TaskNoteAccess.HEIGHT_OF_ONE_TASK);
            if (this.isHovering(xPos, yPos,
                    TaskNoteAccess.LENGTH_OF_ONE_TASK, TaskNoteAccess.HEIGHT_OF_ONE_TASK,
                    mouseX, mouseY)) {
                graphics.renderComponentHoverEffect(this.font, task.getHoveredText().getStyle(), mouseX, mouseY);
            }
        }
        this.renderPageIndex(graphics, isRightPage);
    }

    private void renderPageIndex(GuiGraphics graphics, boolean isRightPage) {
        Component pageIndicatorText = isRightPage ? this.rightPageIndicatorText : this.leftPageIndicatorText;
        int xPosition = isRightPage ? leftMargin() + TEXTURE_WIDTH / 2 + 11 - (this.currentLeftPageIndex > 9 ? 5 : 0)
                : leftMargin() + TEXTURE_WIDTH / 2 - 34;
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
            Style clickedStyle = this.getStylePage((int)mouseX, (int)mouseY);
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

    public int rightPageXStart(){
        return leftMargin() + TEXT_WIDTH + 3 * PAGE_TEXT_X_OFFSET - 8;
    }

    private boolean isHovering(int x1, int y1, int x2, int y2, int mouseX, int mouseY) {
        return x1 <= mouseX && y1 <= mouseY &&
                x1 + x2 >= mouseX && y1 + y2 >= mouseY;
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

    private @Nullable Style getStyleUnderMouseOnTableOfContentPage(double mouseX, double mouseY) {
        List<List<Component>> tocPages = this.journalAccess.getTableOfContentComponents();
        if (tocPages == null || tocPages.isEmpty()) return null;

        boolean overRightPage = mouseX >= this.rightPageXStart();

        int tocPageIndex;
        if (this.currentLeftPageIndex == 0) {
            if (!overRightPage) return null;
            tocPageIndex = 0;
        } else {
            tocPageIndex = overRightPage ? this.currentLeftPageIndex : this.currentLeftPageIndex - 1;
        }

        if (tocPageIndex < 0 || tocPageIndex >= tocPages.size()) return null;

        List<Component> pageLines = tocPages.get(tocPageIndex);
        int pageTextStartX = overRightPage ? this.rightPageXStart() : this.leftMargin() + PAGE_TEXT_X_OFFSET;
        int relativeX = Mth.floor(mouseX - pageTextStartX);

        int extraYOffset = (tocPageIndex == 0) ? this.journalAccess.tableOfContentComponentYOffset : 0;
        int relativeY = Mth.floor(mouseY - PAGE_TEXT_Y_OFFSET - extraYOffset);

        if (relativeX < 0 || relativeY < 0 || relativeX > TEXT_WIDTH) return null;
        int lineIndex = relativeY / LINE_HEIGHT;
        if (lineIndex >= pageLines.size()) return null;

        Component line = pageLines.get(lineIndex);
        return this.font.getSplitter().componentStyleAtWidth(line.getVisualOrderText(), relativeX);
    }


}