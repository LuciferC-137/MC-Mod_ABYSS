package wardentools.misc.wind;

import net.minecraft.network.chat.Component;

import java.util.*;

public class WhisperTags {
    private final Map<Tag, List<Whisper>> tagMap = new EnumMap<>(Tag.class);

    public WhisperTags() {
        for (Tag tag : Tag.values()) {
            tagMap.put(tag, new ArrayList<>());
        }
    }

    public void addTag(Tag tag, Whisper whisper) {
        tagMap.get(tag).add(whisper);
    }

    public boolean hasTag(Tag tag, Whisper whisper) {
        return tagMap.get(tag).contains(whisper);
    }

    public List<Whisper> getWhispersWithTag(Tag tag) {
        return tagMap.get(tag);
    }

    public enum Tag {
        SMALL_TALK,
        GENERIC,
        LORE,
        DEEP_LORE,
        ANCIENT_CITADEL,
        DEEPFOREST,
        WHITE_FOREST,
        CRYSTAL_CAVE;

        public String getName() {
            return Component.translatable("message.wardentools.whisper." + this.getTranslatableTag()).getString();
        }

        public String getTranslatableTag() {
            return switch (this) {
                case SMALL_TALK -> "small_talk";
                case GENERIC -> "generic";
                case LORE -> "lore";
                case DEEP_LORE -> "deep_lore";
                case ANCIENT_CITADEL -> "ancient_citadel";
                case DEEPFOREST -> "deepforest";
                case WHITE_FOREST -> "white_forest";
                case CRYSTAL_CAVE -> "crystal_cave";
            };
        }

        public int getNumberOfWhispers() {
            return switch (this) {
                case SMALL_TALK -> 28;
                case GENERIC -> 12;
                case LORE -> 11;
                case DEEP_LORE -> 6;
                case ANCIENT_CITADEL -> 8;
                case DEEPFOREST -> 10;
                case WHITE_FOREST -> 4;
                case CRYSTAL_CAVE -> 13;
            };
        }
    }
}
