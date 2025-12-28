package wardentools.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.Set;
import java.util.UUID;

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

    public static void putSetUUID(CompoundTag tag, String key, Set<UUID> set) {
            ListTag list = new ListTag();
            for (UUID uuid : set) {
                CompoundTag elem = new CompoundTag();
                elem.putUUID("u", uuid);
                list.add(elem);
            }
            tag.put(key, list);
        }

    public static Set<UUID> readSetUUID(CompoundTag tag, String key) {
        Set<UUID> result = new java.util.HashSet<>();
        if (!tag.contains(key)) {
            return result;
        }
        ListTag list = tag.getList(key, Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag elem = list.getCompound(i);
            result.add(elem.getUUID("u"));
        }
        return result;
    }

}
