package wardentools.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
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

    public static void putVec3(CompoundTag tag, String key, Vec3 vec3) {
        CompoundTag posTag = new CompoundTag();
        posTag.putDouble(key + "x", vec3.x);
        posTag.putDouble(key + "y", vec3.y);
        posTag.putDouble(key + "z", vec3.z);
        tag.put(key, posTag);
    }

    public static Vec3 readVec3(CompoundTag tag, String key) {
        if (!tag.contains(key)) {
            return Vec3.ZERO;
        }
        CompoundTag posTag = tag.getCompound(key);
        double x = posTag.getDouble(key + "x");
        double y = posTag.getDouble(key + "y");
        double z = posTag.getDouble(key + "z");
        return new Vec3(x, y, z);
    }

    public static void putSetUUID(CompoundTag tag, String key, Set<UUID> set) {
        ListTag list = new ListTag();
        for (UUID uuid : set) {
            list.add(NbtUtils.createUUID(uuid));
        }
        tag.put(key, list);
    }

    public static Set<UUID> readSetUUID(CompoundTag tag, String key) {
        Set<UUID> result = new HashSet<>();
        if (!tag.contains(key, Tag.TAG_LIST)) {
            return result;
        }
        ListTag list = tag.getList(key, Tag.TAG_INT_ARRAY);
        for (Tag value : list) {
            try {
                result.add(NbtUtils.loadUUID(value));
            } catch (IllegalArgumentException ignored) {

            }
        }
        return result;
    }

}
