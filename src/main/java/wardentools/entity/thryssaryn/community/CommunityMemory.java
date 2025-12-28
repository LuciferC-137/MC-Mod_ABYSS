package wardentools.entity.thryssaryn.community;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import wardentools.utils.SaveUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommunityMemory {
    private final UUID communityId;
    private Set<UUID> members;
    private BlockPos center;
    private int radius;

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
    }

    /**
     * Creates a CommunityMemory with a specific UUID (used for loading from NBT).
     */
    private CommunityMemory(UUID communityId, Set<UUID> members, BlockPos center, int radius) {
        this.communityId = communityId;
        this.members = new HashSet<UUID>(members);
        this.center = center;
        this.radius = radius;
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

    public CompoundTag toNbt(CompoundTag tag) {
        tag.putUUID("communityId", communityId);
        SaveUtils.putBlockPos(tag, "center", center);
        SaveUtils.putSetUUID(tag, "members", members);
        tag.putInt("radius", radius);

        return tag;
    }

    public static CommunityMemory fromNbt(CompoundTag tag) {
        UUID communityId = tag.getUUID("communityId");
        Set<UUID> members = SaveUtils.readSetUUID(tag, "members");
        BlockPos center = SaveUtils.readBlockPos(tag, "center");
        int radius = tag.getInt("radius");

        return new CommunityMemory(communityId, members, center, radius);
    }
}
