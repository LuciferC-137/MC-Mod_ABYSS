package wardentools.misc.wind;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class WhisperTags {
    private final Map<Tag, List<Whisper>> tagMap = new EnumMap<>(Tag.class);
    private final List<Whisper> unContextualWhispers = new ArrayList<>();

    public WhisperTags() {
        for (Tag tag : Tag.values()) {
            tagMap.put(tag, new ArrayList<>());
        }
    }

    public void initUnContextualWhispers() {
        for (Tag tag : Tag.values()) {
            if (!tag.isContextual()) {
                unContextualWhispers.addAll(tagMap.get(tag));
            }
        }
    }

    public void addTag(Tag tag, Whisper whisper) {
        tagMap.get(tag).add(whisper);
    }

    public List<Whisper> getWhispersWithTag(Tag tag) {
        return tagMap.get(tag);
    }

    public List<Whisper> getContextualWhispers(Tag tag) {
        if (!tag.isContextual()) return unContextualWhispers;
        List<Whisper> contextualWhispers = new ArrayList<>();
        contextualWhispers.addAll(unContextualWhispers);
        contextualWhispers.addAll(tagMap.get(tag));
        return contextualWhispers;
    }

    public enum Tag {
        SMALL_TALK("small_talk", 40, false),
        LORE("lore", 17, false),
        ANCIENT_CITADEL("ancient_citadel", 8, true),
        DEEPFOREST("deepforest", 10, true),
        WHITE_FOREST("white_forest", 4, true),
        CRYSTAL_CAVE("crystal_cave", 13, true),
        OVERWORLD("overworld", 19, true),
        AMETHYST_CAVE("amethyst_cave", 18, true),
        RUBY_CAVE("ruby_cave", 15, true),
        CITRINE_CAVE("citrine_cave", 19, true),
        MALACHITE_CAVE("malachite_cave", 17, true),
        ECHO_CAVE("echo_cave", 20, true),
        PALE_CAVE("pale_cave", 19, true),;

        private final String key;
        private final int numberOfWhispers;
        private final boolean contextual;

        Tag(String key, int numberOfWhispers, boolean contextual) {
            this.key = key;
            this.numberOfWhispers = numberOfWhispers;
            this.contextual = contextual;
        }

        public String getName() {
            return Component.translatable("message.wardentools.whisper." + this.getTranslatableTag()).getString();
        }

        public String getTranslatableTag() {
            return this.key;
        }

        public int getNumberOfWhispers() {
            return this.numberOfWhispers;
        }

        public boolean isContextual() {
            return this.contextual;
        }
    }
}
