package wardentools.misc;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Crystal implements StringRepresentable {
    AMETHYST(0, 0x8e42f6),
    RUBY(1, 0xed2525),
    CITRINE(2, 0xffc200),
    MALACHITE(3, 0x10b058),
    ECHO(4, 0x244b69),
    PALE(5, 0x1be4eb);

    private final int index;
    private final int color;

    Crystal(int index, int color) {
        this.index = index;
        this.color = color;
    }

    public static Crystal fromIndex(int index) {
        for (Crystal crystal : values()) {
            if (crystal.getIndex() == index) {
                return crystal;
            }
        }
        return getDefault();
    }

    public static Crystal getDefault() {
        return AMETHYST;
    }

    public int getIndex() {return index;}

    public int getColor() {return color;}

    public int getColorARGB() {
        return 0xFF000000 | color;
    }

    @Override
    public @NotNull String getSerializedName() {return this.name().toLowerCase();}
}
