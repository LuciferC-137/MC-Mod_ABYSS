package wardentools.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TallGrassBlock;

public class VerdantGrassBlock extends TallGrassBlock {
    public static final int AURORA_VALLEY_GREEN = 0x26533e;
    public static final int AURORA_VALLEY_LIGHTER = 0x2d6829;

    public VerdantGrassBlock(Properties properties) {
        super(properties);
    }

    public static int getColor(ItemStack stack, int index) {
        if (index == 0) return AURORA_VALLEY_GREEN;
        else if (index == 1) return AURORA_VALLEY_LIGHTER;
        else return -1;
    }
}
