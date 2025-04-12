package wardentools.misc.wind;

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
            return switch (this) {
                case GENERIC -> "==== Generic ====";
                case ANCIENT_CITADEL -> "= Ancient Citadel =";
                case SMALL_TALK ->  "=== Small Talk ===";
                case LORE -> "===== Lore =====";
                case DEEP_LORE -> "=== Deep Lore ===";
                case DEEPFOREST -> "== Deep Forest ==";
                case WHITE_FOREST -> "== White Forest ==";
                case CRYSTAL_CAVE -> "= Crystal Cave =";
            };
        }
    }
}
