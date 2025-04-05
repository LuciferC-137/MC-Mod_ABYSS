package wardentools.misc.wind;

import java.util.List;
import java.util.EnumMap;
import java.util.ArrayList;
import java.util.Map;

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
        GENERIC,
        ANCIENT_CITADEL,
        SMALL_TALK,
        LORE,
        DEEP_LORE,
        DEEPFOREST,
        WHITE_FOREST,
        CRYSTAL_CAVES
    }
}
