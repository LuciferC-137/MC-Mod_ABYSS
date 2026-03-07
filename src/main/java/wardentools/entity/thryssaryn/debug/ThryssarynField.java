package wardentools.entity.thryssaryn.debug;

import wardentools.entity.thryssaryn.individual.AbstractThryssaryn;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Defines editable fields for Thryssaryn entities in the debug screen.
 * To add a new editable field, simply add a new enum constant.
 * The fieldId is the ordinal of the enum.
 */
public enum ThryssarynField {
    EYES_COLOR("Eyes Color", ThryssarynField::getEyesColor, ThryssarynField::setEyesColor, FieldType.COLOR),
    AGE("Age", ThryssarynField::getAge, ThryssarynField::setAge, FieldType.INT),
    IS_PAUSED("Paused", ThryssarynField::getIsPaused, ThryssarynField::setIsPaused, FieldType.BOOLEAN),
    IS_PLAYING_LUTH("Playing Luth", ThryssarynField::getIsPlayingLuth, ThryssarynField::setIsPlayingLuth, FieldType.BOOLEAN);

    public enum FieldType {
        INT,
        BOOLEAN,
        COLOR
    }

    private final String displayName;
    private final Function<AbstractThryssaryn, Integer> getter;
    private final BiConsumer<AbstractThryssaryn, Integer> setter;
    private final FieldType fieldType;

    ThryssarynField(String displayName,
                    Function<AbstractThryssaryn, Integer> getter,
                    BiConsumer<AbstractThryssaryn, Integer> setter,
                    FieldType fieldType) {
        this.displayName = displayName;
        this.getter = getter;
        this.setter = setter;
        this.fieldType = fieldType;
    }

    public String getDisplayName() { return displayName; }
    public int getValue(AbstractThryssaryn entity) { return getter.apply(entity); }
    public void setValue(AbstractThryssaryn entity, int value) { setter.accept(entity, value); }
    public FieldType getFieldType() { return fieldType; }
    public int getFieldId() { return ordinal(); }

    public static ThryssarynField fromId(int id) {
        ThryssarynField[] values = values();
        if (id >= 0 && id < values.length) return values[id];
        return null;
    }

    // ---- Getter/Setter helpers ----

    private static int getEyesColor(AbstractThryssaryn e) { return e.getEyesColor(); }
    private static void setEyesColor(AbstractThryssaryn e, int v) { e.setEyesColor(v); }

    private static int getAge(AbstractThryssaryn e) { return e.getAge(); }
    private static void setAge(AbstractThryssaryn e, int v) { e.setAge(v); }

    private static int getIsPaused(AbstractThryssaryn e) { return e.isPaused() ? 1 : 0; }
    private static void setIsPaused(AbstractThryssaryn e, int v) { e.setPaused(v != 0); }

    private static int getIsPlayingLuth(AbstractThryssaryn e) {
        return (e instanceof ThryssarynEntity t) ? (t.isPlayingLuth() ? 1 : 0) : 0;
    }
    private static void setIsPlayingLuth(AbstractThryssaryn e, int v) {
        if (e instanceof ThryssarynEntity t) t.setPlayingLuth(v != 0);
    }
}

