package wardentools.playerdata.serializables;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;


public class KnownWindWhispers implements INBTSerializable<CompoundTag> {
    private static final String taskDataId = "known_wind_whispers";
    private final Set<Integer> whispers = new HashSet<>();

    public KnownWindWhispers() {}

    public boolean whisperKnown(int id) {
        return whispers.contains(id);
    }

    public void addKnownWhisper(int id) {
        whispers.add(id);
    }

    public void removeKnownWhisper(int id) {
        whispers.remove(id);
    }

    public Set<Integer> getAll() {
        return this.whispers;
    }

    public int[] getAllAsArray() {
        return this.whispers.stream().mapToInt(Integer::intValue).toArray();
    }

    public void copy(KnownWindWhispers other) {
        this.whispers.clear();
        this.whispers.addAll(other.getAll());
    }

    public void copy(Set<Integer> whispers) {
        this.whispers.clear();
        this.whispers.addAll(whispers);
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        int[] arr = whispers.stream().mapToInt(Integer::intValue).toArray();
        tag.putIntArray(taskDataId, arr);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        int[] arr = tag.getIntArray(taskDataId);
        this.whispers.clear();
        for (int i : arr) this.whispers.add(i);
    }
}
