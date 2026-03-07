package wardentools.entity.thryssaryn.debug;

import wardentools.entity.thryssaryn.individual.AbstractThryssaryn;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import java.util.function.Function;

/**
 * Defines read-only information fields displayed in the debug screen.
 */
public enum ThryssarynInfoField {
    ENTITY_TYPE("Entity Type", e -> e instanceof ThryssarynEntity ? "ThryssarynEntity" : "AbstractThryssaryn"),
    UUID("UUID", e -> e.getUUID().toString().substring(0, 8) + "..."),
    POSITION("Position", e -> String.format("%.1f, %.1f, %.1f", e.getX(), e.getY(), e.getZ())),
    HEALTH("Health", e -> String.format("%.1f / %.1f", e.getHealth(), e.getMaxHealth())),
    IS_BABY("Is Baby", e -> e.isBaby() ? "Yes" : "No"),
    SIZE_FACTOR("Size Factor", e -> String.format("%.2f", e.getSizeFactor())),
    COMMUNITY("Community", e -> e.getCommunityId()
            .map(uuid -> uuid.toString().substring(0, 8) + "...")
            .orElse("None"));

    private final String displayName;
    private final Function<AbstractThryssaryn, String> valueGetter;

    ThryssarynInfoField(String displayName, Function<AbstractThryssaryn, String> valueGetter) {
        this.displayName = displayName;
        this.valueGetter = valueGetter;
    }

    public String getDisplayName() { return displayName; }

    public String getValue(AbstractThryssaryn entity) { return valueGetter.apply(entity); }
}

