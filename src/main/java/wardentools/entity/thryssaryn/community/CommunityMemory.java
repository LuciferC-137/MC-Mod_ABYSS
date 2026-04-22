package wardentools.entity.thryssaryn.community;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIMemory;
import wardentools.utils.SaveUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommunityMemory implements INBTSerializable<CompoundTag> {
    private static final String COMMUNITY_ID_TAG = "communityId";
    private static final String MEMBERS_TAG = "members";
    private static final String CENTER_TAG = "center";
    private static final String RADIUS_TAG = "radius";
    private static final String POI_MEMORY_TAG = "poiMemory";
    private UUID communityId;
    private Set<UUID> members;
    private BlockPos center;
    private int radius;
    public POIMemory poiMemory;

    /**
     * Transient set of currently loaded members (not saved to NBT).
     * Members register/unregister themselves when they are loaded/unloaded.
     */
    private final Set<UUID> loadedMembers = new java.util.HashSet<>();

    /**
     * Creates a new CommunityMemory with a randomly generated UUID.
     */
    public CommunityMemory(Set<UUID> members, BlockPos center, int radius) {
        this(UUID.randomUUID(), members, center, radius);
        this.poiMemory = new POIMemory(this);
    }

    /**
     * Creates a CommunityMemory with a specific UUID (used for loading from NBT).
     */
    private CommunityMemory(UUID communityId, Set<UUID> members, BlockPos center, int radius) {
        this.communityId = communityId;
        this.members = new HashSet<UUID>(members);
        this.center = center;
        this.radius = radius;
        this.poiMemory = new POIMemory(this);
    }

    /**
     * Registers a member as currently loaded in the world.
     * Should be called by the entity when it starts ticking.
     * @param memberUUID The UUID of the loaded member entity
     */
    public void registerLoadedMember(UUID memberUUID) {
        if (members.contains(memberUUID)) {
            loadedMembers.add(memberUUID);
        }
    }

    /**
     * Unregisters a member as loaded (e.g., when the entity is unloaded).
     * Should be called by the entity when it stops ticking.
     * @param memberUUID The UUID of the unloaded member entity
     */
    public void unregisterLoadedMember(UUID memberUUID) {
        loadedMembers.remove(memberUUID);
    }

    public void fuseMemory(CommunityMemory other, BlockPos newCenter, int newRadius) {
        this.members.addAll(other.members);
        this.center = newCenter;
        this.radius = newRadius;
    }

    /**
     * Checks if at least one member is currently loaded.
     * @return true if any member is loaded
     */
    public boolean hasLoadedMembers() {
        return !loadedMembers.isEmpty();
    }

    /**
     * Gets the set of currently loaded members.
     * @return An unmodifiable view of loaded members
     */
    public Set<UUID> getLoadedMembers() {
        return Set.copyOf(loadedMembers);
    }

    public Set<UUID> getMembers() {
        return members;
    }

    public BlockPos getCenter() {
        return center;
    }

    public void setCenter(BlockPos center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public UUID getCommunityId() {
        return communityId;
    }


    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putUUID(COMMUNITY_ID_TAG, communityId);
        SaveUtils.putBlockPos(tag, CENTER_TAG, center);
        SaveUtils.putSetUUID(tag, MEMBERS_TAG, members);
        tag.putInt(RADIUS_TAG, radius);
        tag.put(POI_MEMORY_TAG, this.poiMemory.serializeNBT(provider));
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider,
                               @NotNull CompoundTag compoundTag) {
        if (compoundTag.contains(POI_MEMORY_TAG)) {
            this.poiMemory.deserializeNBT(provider, compoundTag.getCompound(POI_MEMORY_TAG));
        }
        if (compoundTag.contains(MEMBERS_TAG)) {
            this.members = SaveUtils.readSetUUID(compoundTag, MEMBERS_TAG);
        }
        if (compoundTag.contains(CENTER_TAG)) {
            this.center = SaveUtils.readBlockPos(compoundTag, CENTER_TAG);
        }
        if (compoundTag.contains(RADIUS_TAG)) {
            this.radius = compoundTag.getInt(RADIUS_TAG);
        }
        if (compoundTag.contains(COMMUNITY_ID_TAG)) {
            this.communityId = compoundTag.getUUID(COMMUNITY_ID_TAG);
        }
    }
}
