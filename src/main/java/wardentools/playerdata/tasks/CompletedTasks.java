package wardentools.playerdata.tasks;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AutoRegisterCapability
public class CompletedTasks {
    private static final String taskDataId = "completedTask";
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

    public void copy(Set<Integer> whispers) {
        this.tasks.clear();
        this.tasks.addAll(whispers);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putIntArray(taskDataId, this.tasks.stream().toList());
    }

    public void loadNBTData(CompoundTag nbt) {
        this.tasks.clear();
        this.tasks.addAll(Arrays.stream(nbt.getIntArray(taskDataId)).boxed().toList());
    }
}
