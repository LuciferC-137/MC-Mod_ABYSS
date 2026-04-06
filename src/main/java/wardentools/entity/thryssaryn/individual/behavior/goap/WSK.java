package wardentools.entity.thryssaryn.individual.behavior.goap;

import net.minecraft.tags.TagKey;

/**
 * World State Keys
 */
public enum WSK {
    HEALTH(Integer.class),
    HUNGER(Integer.class),
    IS_IN_VILLAGE(Boolean.class),
    BIOME_IN(TagKey.class)
    ;

    private final Class<?> type;

    WSK(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}
