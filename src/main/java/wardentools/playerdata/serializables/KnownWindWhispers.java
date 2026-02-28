package wardentools.playerdata.serializables;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class KnownWindWhispers implements INBTSerializable<CompoundTag> {
    private static final String whisperDataID = "known_wind_whispers";
    private final Set<String> whispers = new HashSet<>();

    public KnownWindWhispers() {}

    public boolean whisperKnown(String id) {
        return whispers.contains(id);
    }

    public void addKnownWhisper(String id) {
        whispers.add(id);
    }

    public void removeKnownWhisper(String id) {
        whispers.remove(id);
    }

    public Set<String> getAll() {
        return this.whispers;
    }

    public void copy(KnownWindWhispers other) {
        this.whispers.clear();
        this.whispers.addAll(other.getAll());
    }

    public void copy(Set<String> whispers) {
        this.whispers.clear();
        this.whispers.addAll(whispers);
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (String s : whispers) {
            list.add(StringTag.valueOf(s));
        }
        tag.put(whisperDataID, list);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        this.whispers.clear();
        ListTag list = tag.getList(whisperDataID, Tag.TAG_STRING);
        for (Tag t : list) {
            this.whispers.add(t.getAsString());
        }
    }
}
