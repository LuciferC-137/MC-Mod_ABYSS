package wardentools.playerdata.serializables;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;


public class CompletedTasks implements INBTSerializable<CompoundTag> {
    private static final String taskDataId = "task_list";
    private final Set<Integer> tasks = new HashSet<>();

    public CompletedTasks() {}

    public boolean taskCompleted(int id) {
        return tasks.contains(id);
    }

    public void addCompletedTask(int id) {
        tasks.add(id);
    }

    public void removeCompletedTask(int id) {
        tasks.remove(id);
    }

    public Set<Integer> getAll() {
        return this.tasks;
    }

    public void copy(CompletedTasks other) {
        this.tasks.clear();
        this.tasks.addAll(other.getAll());
    }

    public void copy(Set<Integer> whispers) {
        this.tasks.clear();
        this.tasks.addAll(whispers);
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        int[] arr = tasks.stream().mapToInt(Integer::intValue).toArray();
        tag.putIntArray(taskDataId, arr);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
        int[] arr = tag.getIntArray(taskDataId);
        this.tasks.clear();
        for (int i : arr) this.tasks.add(i);
    }
}
