package wardentools.misc.wind;

import net.minecraft.network.chat.Component;

import java.util.*;

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
        SMALL_TALK,
        LORE,
        ANCIENT_CITADEL,
        DEEPFOREST,
        WHITE_FOREST,
        CRYSTAL_CAVE,
        OVERWORLD;

        public String getName() {
            return Component.translatable("message.wardentools.whisper." + this.getTranslatableTag()).getString();
        }

        public String getTranslatableTag() {
            return switch (this) {
                case SMALL_TALK -> "small_talk";
                case LORE -> "lore";
                case ANCIENT_CITADEL -> "ancient_citadel";
                case DEEPFOREST -> "deepforest";
                case WHITE_FOREST -> "white_forest";
                case CRYSTAL_CAVE -> "crystal_cave";
                case OVERWORLD -> "overworld";
            };
        }

        public int getNumberOfWhispers() {
            return switch (this) {
                case SMALL_TALK -> 40;
                case LORE -> 17;
                case ANCIENT_CITADEL -> 8;
                case DEEPFOREST -> 10;
                case WHITE_FOREST -> 4;
                case CRYSTAL_CAVE -> 13;
                case OVERWORLD -> 19;
            };
        }

        public boolean isContextual() {
            return switch (this) {
                case DEEPFOREST, WHITE_FOREST, CRYSTAL_CAVE, OVERWORLD -> true;
                default -> false;
            };
        }
    }
}
