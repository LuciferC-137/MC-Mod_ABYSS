package wardentools.utils;

public interface TaggableEnum {

    default String toTag() {
        return ((Enum<?>) this).name();
    }

    static <T extends Enum<T>> T fromTag(Class<T> enumClass, String tag) {
        if (tag == null) return null;
        try {
            return Enum.valueOf(enumClass, tag);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
