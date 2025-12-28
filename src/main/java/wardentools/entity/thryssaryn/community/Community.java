package wardentools.entity.thryssaryn.community;


import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import java.util.Set;
import java.util.UUID;

/**
 * Thryssaryn community, with members and shared data.
 */
public class Community {
    private CommunityBrain brain;
    private static final Logger LOGGER = LogUtils.getLogger();

    public Community(CommunityMemory memory) {
        this.brain = new CommunityBrain(memory);
    }

    /**
     * Factory method to create a new community. This community must be added to the community data manager.
     * @param members Set of member UUIDs
     * @param center Center position of the community
     * @param radius Radius of the community
     * @return New Community instance
     */
    public static Community create(Set<UUID> members, BlockPos center, int radius) {
        CommunityMemory memory = new CommunityMemory(members, center, radius);
        return new Community(memory);
    }

    public void tick() {
        brain.tick();
    }

    public UUID uuid() {
        return brain.memory.getCommunityId();
    }

    public Set<UUID> members() {
        return brain.memory.getMembers();
    }

    public BlockPos center() {
        return brain.memory.getCenter();
    }

    public int radius() {
        return brain.memory.getRadius();
    }

    /**
     * Calculates the distance from the community boundary to a given position.
     * Positive values indicate outside the community, negative values inside.
     * @param pos The position to measure from
     * @return Distance to community boundary
     */
    public int distanceTo(BlockPos pos) {
        return (int) center().distSqr(pos) - radius();
    }

    public void removeMember(UUID memberUUID) {
        LOGGER.info("Removing member {} from community {}", memberUUID, uuid());
        brain.memory.getMembers().remove(memberUUID);
    }

    public void addMember(@NotNull UUID memberUUID) {
        LOGGER.info("Adding member {} to community {}", memberUUID, uuid());
        brain.memory.getMembers().add(memberUUID);
    }

    public boolean isEmpty() {
        return brain.memory.getMembers().isEmpty();
    }

    /**
     * Fuses another community into this one. This method should only be called
     * inside the CommunityData management to ensure consistency.
     * Merges members and updates center/radius.
     * @param other The other community to fuse
     * @param newCenter The new center position
     * @param newRadius The new radius
     */
    public void fuseCommunity(Community other, ServerLevel level, BlockPos newCenter, int newRadius) {
        this.brain.memory.fuseMemory(other.brain.memory, newCenter, newRadius);
        other.assignNewColonyToMembers(level, this.uuid());
        other.brain.memory.getMembers().clear();
    }

    public void assignNewColonyToMembers(ServerLevel level, UUID newCommunityUUID) {
        Set<UUID> members = brain.memory.getMembers();
        for (UUID memberUUID : members) {
            Entity entity = level.getEntity(memberUUID);
            if (entity instanceof ThryssarynEntity thryssaryn) {
                thryssaryn.setCommunityId(newCommunityUUID);
            }
        }
    }

    /**
     * Registers a member as currently loaded in the world.
     * @param memberUUID The UUID of the loaded member entity
     */
    public void registerLoadedMember(UUID memberUUID) {
        brain.memory.registerLoadedMember(memberUUID);
    }

    /**
     * Unregisters a member as loaded.
     * @param memberUUID The UUID of the unloaded member entity
     */
    public void unregisterLoadedMember(UUID memberUUID) {
        brain.memory.unregisterLoadedMember(memberUUID);
    }

    /**
     * Checks if at least one member is currently loaded.
     * @return true if any member is loaded
     */
    public boolean hasLoadedMembers() {
        return brain.memory.hasLoadedMembers();
    }

    /**
     * Serializes this community to NBT for saving.
     * @param tag The tag to write to
     * @return The tag with community data
     */
    public CompoundTag toNbt(CompoundTag tag) {
        return brain.memory.toNbt(tag);
    }

    /**
     * Deserializes a community from NBT.
     * @param tag The tag containing community data
     * @return The restored community
     */
    public static Community fromNbt(CompoundTag tag) {
        CommunityMemory memory = CommunityMemory.fromNbt(tag);
        return new Community(memory);
    }
}
