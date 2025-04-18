package wardentools.gui.windscreen;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.misc.wind.Whisper;
import wardentools.misc.wind.WhisperManager;
import wardentools.misc.wind.WhisperTags;
import wardentools.misc.wind.WindWhispers;
import wardentools.playerdata.whispers.KnownWhispersDataProvider;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class JournalAccess {
    private final List<List<Component>> sections;
    private List<List<FormattedCharSequence>> pages = new ArrayList<>();
    private final List<Component> firstPageComponents = new ArrayList<>();
    public int firstPageComponentYOffset = 0;

    public JournalAccess() {
        this.sections = new ArrayList<>();
        this.sections.addAll(buildSectionsList(Minecraft.getInstance()));
    }

    public void initPages(@NotNull Font font) {
        this.pages = this.buildPagesList(font);
    }

    private List<List<FormattedCharSequence>> buildPagesList(Font font) {
        firstPageComponents.clear();
        List<List<FormattedCharSequence>> pages = new ArrayList<>();
        List<Integer> sectionPageIndex = new ArrayList<>();
        int pageIndex = 1;
        for (List<Component> section : this.sections) {
            // New page at the beginning of each section
            List<FormattedCharSequence> currentPage = new ArrayList<>();
            int currentLineCount = 0;
            for (Component component : section) {
                List<FormattedCharSequence> lines = font.split(component, WindJournalScreen.TEXT_WIDTH);
                for (FormattedCharSequence line : lines) {
                    if (currentLineCount >= WindJournalScreen.MAX_LINES_PER_PAGE) {
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
        return buildFirstPageIndex(font, pages, sectionPageIndex);
    }

    public List<List<FormattedCharSequence>> buildFirstPageIndex(Font font, List<List<FormattedCharSequence>> pages,
                                                                 List<Integer> sectionPageIndex) {
        // Replace XX with page proper page index and adds links to it.
        int indexParsing = 0;
        List<FormattedCharSequence> firstPage = pages.getFirst();
        for (int k = 0; k < firstPage.size(); k++) {
            FormattedCharSequence line = firstPage.get(k);
            String lineText = extractText(line);
            if (lineText.contains("XX") && indexParsing < sectionPageIndex.size()) {
                if (indexParsing == 0) this.firstPageComponentYOffset = k * WindJournalScreen.LINE_HEIGHT;
                int targetPage = sectionPageIndex.get(indexParsing);
                String label = lineText.replace("XX", String.valueOf(targetPage));
                Component clickableComponent = clickable(label, targetPage);
                this.firstPageComponents.add(clickableComponent);
                FormattedCharSequence clickableLine = font.split(clickableComponent,
                        WindJournalScreen.TEXT_WIDTH + 10).getFirst();
                firstPage.set(k, clickableLine);
                indexParsing++;
            }
        }
        return pages;
    }

    private static Component clickable(String text, int pageIndex) {
        return Component.literal(text).withStyle(Style.EMPTY
                .withColor(WindJournalScreen.TEXT_COLOR)
                .withClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(pageIndex + 1)))
                .withHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        hoveredText(Component
                                .translatable( "message." + ModMain.MOD_ID + ".go_to").getString()
                                + pageIndex))));
    }

    private static String extractText(FormattedCharSequence sequence) {
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
        indexSection.add(Component.translatable("message.wardentools.wind_journal")
                .withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED));
        coverPage.append(Component.translatable("message.wardentools.table_of_content").getString());
        for (WhisperTags.Tag tag : WhisperTags.Tag.values()) {
            coverPage.append("\n")
                    .append(tag.getName().replace("=", ""))
                    .append(" - ")
                    .append("XX"); // Placeholder for index
        }
        indexSection.add(baseText(coverPage.toString()));
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
                if (data.knowsWhisper(whisper.globalId())) {
                    whispers.add(baseText((whisper.globalId() + 1 ) + " - "
                            + whisper.whisper().getString() + "\n"));
                } else {
                    whispers.add(baseText((whisper.globalId() + 1)+  " - " +
                            WindWhispers.getLockedString() + "\n", false, true));
                }
            }
        });
        return whispers;
    }

    public int getPageCount() {
        return this.pages.size();
    }

    public List<FormattedCharSequence> getPage(int pageIndex) {
        return pageIndex >= 0 && pageIndex < this.getPageCount() ? this.pages.get(pageIndex) : List.of();
    }

    public List<Component> getFirstPageComponents() {
        return firstPageComponents;
    }

    public static Component baseText(String text) {
        return Component.literal(text).withStyle(WindJournalScreen.TEXT_COLOR);
    }

    public static Component baseText(String text, boolean bold, boolean italic) {
        if (bold && italic) {
            return Component.literal(text).withStyle(WindJournalScreen.TEXT_COLOR).withStyle(ChatFormatting.BOLD)
                    .withStyle(ChatFormatting.ITALIC);
        } else if (bold) {
            return Component.literal(text).withStyle(WindJournalScreen.TEXT_COLOR).withStyle(ChatFormatting.BOLD);
        }
        return Component.literal(text).withStyle(WindJournalScreen.TEXT_COLOR).withStyle(ChatFormatting.ITALIC);
    }

    public static Component hoveredText(String text) {
        return Component.literal(text).withStyle(ChatFormatting.GRAY);
    }

}
