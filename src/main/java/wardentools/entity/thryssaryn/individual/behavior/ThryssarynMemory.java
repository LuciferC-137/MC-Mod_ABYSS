package wardentools.entity.thryssaryn.individual.behavior;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIMemory;

import java.util.Set;

public class ThryssarynMemory implements INBTSerializable<CompoundTag> {
    private static final String KNOWN_POI_TAG = "KNOWN_POIS";
    private final ThryssarynEntity entity;
    private final POIMemory poiMemory;


    public ThryssarynMemory(ThryssarynEntity entity) {
        this.entity = entity;
        this.poiMemory = new POIMemory(entity);
    }

    public Set<POIInstance> getAllPOIs() {
        return this.poiMemory.getAllPOIs();
    }


    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        CompoundTag poiTag = this.poiMemory.serializeNBT(provider);
        tag.put(KNOWN_POI_TAG, poiTag);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider,
                               @NotNull CompoundTag compoundTag) {
        if (compoundTag.contains(KNOWN_POI_TAG)) {
            this.poiMemory.deserializeNBT(provider, compoundTag.getCompound(KNOWN_POI_TAG));
        }
    }
}
