package wardentools.entity.thryssaryn.individual.behavior.goap.poi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import wardentools.utils.SaveUtils;

public class POIInstance implements INBTSerializable<CompoundTag> {
    private static final String POS_TAG = "pos";
    private static final String TYPE_TAG = "type";
    private static final String LAST_SEEN_TICK_TAG = "last_seen_tick";

    private BlockPos pos;
    private POIType type;
    private long lastSeenTick;

    public POIInstance() {}

    public POIInstance(BlockPos pos, POIType type) {
        this(pos, type, -1);
    }

    public POIInstance(BlockPos pos, POIType type, long lastSeenTick) {
        this.pos = pos;
        this.type = type;
        this.lastSeenTick = lastSeenTick;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        SaveUtils.putBlockPos(tag, POS_TAG, pos);
        tag.putString(TYPE_TAG, type.toTag());
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider,
                               @NotNull CompoundTag tag) {
        if  (tag.contains(POS_TAG)) {
            this.pos = SaveUtils.readBlockPos(tag, POS_TAG);
        }
        if (tag.contains(TYPE_TAG)) {
           this.type = POIType.fromTag(tag.getString(TYPE_TAG));
        }
        if  (tag.contains(LAST_SEEN_TICK_TAG)) {
            this.lastSeenTick = tag.getLong(LAST_SEEN_TICK_TAG);
        }
    }

    public BlockPos getPos() {
        return pos;
    }

    public long getLastSeenTick() {
        return lastSeenTick;
    }

    public void setLastSeenTick(long lastSeenTick) {
        this.lastSeenTick = lastSeenTick;
    }

    public POIType getType() {
        return type;
    }
}