package wardentools.entity.thryssaryn.community;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class CommunityBrain implements INBTSerializable<CompoundTag> {
    public static final String MEMORY_TAG = "memory";
    public CommunityMemory memory;

    /**
     * Constructor only for deserialization: data must be manually updated
     */
    public CommunityBrain() {
    }

    public CommunityBrain(CommunityMemory memory) {
        this.memory = memory;
    }

    public void tick() {

    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.put(MEMORY_TAG, this.memory.serializeNBT(provider));
        return compoundTag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider,
                               @NotNull CompoundTag compoundTag) {
        if (compoundTag.contains(MEMORY_TAG)) {
            this.memory.deserializeNBT(provider, compoundTag.getCompound(MEMORY_TAG));
        }
    }
}
