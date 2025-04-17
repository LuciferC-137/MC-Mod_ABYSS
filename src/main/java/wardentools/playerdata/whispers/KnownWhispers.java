package wardentools.playerdata.whispers;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AutoRegisterCapability
public class KnownWhispers {
    private static final String whisperDataId = "knownWhispers";
    private final Set<Integer> whispers = new HashSet<>();

    public KnownWhispers() {}

    public boolean knowsWhisper(int id) {
        return whispers.contains(id);
    }

    public void addKnownWhisper(int id) {
        whispers.add(id);
    }

    public Set<Integer> getAll() {
        return this.whispers;
    }

    public void copy(Set<Integer> whispers) {
        this.whispers.clear();
        this.whispers.addAll(whispers);
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putIntArray(whisperDataId, this.whispers.stream().toList());
    }

    public void loadNBTData(CompoundTag nbt) {
        this.whispers.clear();
        this.whispers.addAll(Arrays.stream(nbt.getIntArray(whisperDataId)).boxed().toList());
    }
}
