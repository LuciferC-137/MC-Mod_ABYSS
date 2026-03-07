package wardentools.entity.thryssaryn.debug;

import java.util.List;
import java.util.function.Function;

/**
 * Defines read-only information fields displayed in the Community debug screen.
 * To add a new info field, simply add a new enum constant.
 * The data source is a {@link CommunityInfoField.CommunitySnapshot} passed at screen creation.
 */
public enum CommunityInfoField {
    UUID("UUID", s -> s.communityUuid().toString().substring(0, 8) + "..."),
    CENTER("Center", s -> s.centerX() + ", " + s.centerY() + ", " + s.centerZ()),
    RADIUS("Radius", s -> String.valueOf(s.radius())),
    MEMBER_COUNT("Members", s -> s.memberUuids().size() + " total");

    private final String displayName;
    private final Function<CommunitySnapshot, String> valueGetter;

    CommunityInfoField(String displayName, Function<CommunitySnapshot, String> valueGetter) {
        this.displayName = displayName;
        this.valueGetter = valueGetter;
    }

    public String getDisplayName() { return displayName; }

    public String getValue(CommunitySnapshot snapshot) { return valueGetter.apply(snapshot); }

    /**
     * Immutable snapshot of community data received from the server.
     */
    public record CommunitySnapshot(
            java.util.UUID communityUuid,
            int centerX, int centerY, int centerZ,
            int radius,
            List<java.util.UUID> memberUuids
    ) {}
}


