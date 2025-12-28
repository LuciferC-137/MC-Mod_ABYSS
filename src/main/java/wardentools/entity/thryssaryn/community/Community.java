package wardentools.entity.thryssaryn.community;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.Set;
import java.util.UUID;

/**
 * Thryssaryn community, with members and shared data.
 */
public class Community {
    private CommunityBrain brain;

    public Community(CommunityMemory memory) {
        this.brain = new CommunityBrain(memory);
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

    public void removeMember(UUID memberUUID) {
        brain.memory.getMembers().remove(memberUUID);
    }

    public void addMember(UUID memberUUID) {
        brain.memory.getMembers().add(memberUUID);
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
