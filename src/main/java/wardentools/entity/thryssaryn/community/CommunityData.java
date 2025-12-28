package wardentools.entity.thryssaryn.community;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Centralised savable data for Thryssaryn communities.
 * This class extends SavedData to persist community data with the world.
 * Usage:
 * - Get instance: CommunityData.get(serverLevel)
 * - Get community by UUID: getData().getCommunity(uuid)
 * - Add community: getData().addCommunity(community)
 * - Remove community: getData().removeCommunity(uuid)
 */
public class CommunityData extends SavedData {
    private static final String DATA_NAME = "thryssaryn_communities";
    private static final String COMMUNITIES_KEY = "communities";

    private final Map<UUID, Community> communities = new HashMap<>();

    public CommunityData() {
        super();
    }

    /**
     * Gets a community by its UUID.
     * @param uuid The unique identifier of the community
     * @return The community, or null if not found
     */
    @Nullable
    public Community getCommunity(UUID uuid) {
        return communities.get(uuid);
    }

    /**
     * Adds or updates a community.
     * @param community The community to add/update
     */
    public void addCommunity(Community community) {
        communities.put(community.uuid(), community);
        setDirty();
    }

    /**
     * Removes a community by its UUID.
     * @param uuid The unique identifier of the community to remove
     * @return The removed community, or null if not found
     */
    @Nullable
    public Community removeCommunity(UUID uuid) {
        Community removed = communities.remove(uuid);
        if (removed != null) {
            setDirty();
        }
        return removed;
    }

    /**
     * Gets all communities.
     * @return An unmodifiable view of all communities
     */
    public Map<UUID, Community> getAllCommunities() {
        return Map.copyOf(communities);
    }

    /**
     * Ticks all communities that have at least one loaded member.
     * Should be called from server tick event.
     */
    public void tickAll() {
        for (Community community : communities.values()) {
            if (community.hasLoadedMembers()) {
                community.tick();
            }
        }
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        ListTag communitiesList = new ListTag();
        for (Community community : communities.values()) {
            CompoundTag communityTag = community.toNbt(new CompoundTag());
            communitiesList.add(communityTag);
        }
        tag.put(COMMUNITIES_KEY, communitiesList);
        return tag;
    }

    public static CommunityData load(CompoundTag tag, HolderLookup.Provider registries) {
        CommunityData data = new CommunityData();
        if (tag.contains(COMMUNITIES_KEY, Tag.TAG_LIST)) {
            ListTag communitiesList = tag.getList(COMMUNITIES_KEY, Tag.TAG_COMPOUND);
            for (int i = 0; i < communitiesList.size(); i++) {
                CompoundTag communityTag = communitiesList.getCompound(i);
                Community community = Community.fromNbt(communityTag);
                data.communities.put(community.uuid(), community);
            }
        }
        return data;
    }

    /**
     * Gets the CommunityData for a given server level.
     * This is the main entry point to access community data.
     *
     * @param level The server level
     * @return The CommunityData instance for this world
     */
    public static CommunityData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new Factory<>(
                        CommunityData::new,
                        CommunityData::load
                ),
                DATA_NAME
        );
    }
}
