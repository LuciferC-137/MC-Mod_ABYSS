package wardentools.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class SaveUtils {

    public static void putBlockPos(CompoundTag tag, String key, BlockPos pos) {
        tag.putInt(key + "_x", pos.getX());
        tag.putInt(key + "_y", pos.getY());
        tag.putInt(key + "_z", pos.getZ());
    }

    public static BlockPos readBlockPos(CompoundTag tag, String key) {
        if (!tag.contains(key + "_x") || !tag.contains(key + "_y") || !tag.contains(key + "_z")) {
            return BlockPos.ZERO;
        }
        int x = tag.getInt(key + "_x");
        int y = tag.getInt(key + "_y");
        int z = tag.getInt(key + "_z");
        return new BlockPos(x, y, z);
    }
}
