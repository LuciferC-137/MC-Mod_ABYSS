package wardentools.playerdata.tasks;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class TaskDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<CompletedTasks> TASKS_CAPABILITY
            = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<CompletedTasks> instance = LazyOptional.of(this::getOrCreateCompletedTasks);
    private CompletedTasks completedTasks = null;

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        return cap == TASKS_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    public CompletedTasks getOrCreateCompletedTasks() {
        if (this.completedTasks == null) {
            this.completedTasks = new CompletedTasks();
        }
        return this.completedTasks;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        getOrCreateCompletedTasks().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        getOrCreateCompletedTasks().loadNBTData(tag);
    }
}
