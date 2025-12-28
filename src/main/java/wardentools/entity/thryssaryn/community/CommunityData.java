package wardentools.entity.thryssaryn.community;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private static final Logger LOGGER = LogUtils.getLogger();

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

    public Community createCommunity(Set<UUID> members, BlockPos center, int radius) {
        LOGGER.info("Creating new community with {} members at {} (radius {})",
                members.size(), center, radius);
        Community community = Community.create(members, center, radius);
        this.addCommunity(community);
        return community;
    }

    /**
     * Adds or updates a community.
     * @param community The community to add/update
     */
    public void addCommunity(Community community) {
        LOGGER.info("Adding/updating community {}", community.uuid());
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
        LOGGER.info("Removing community {}", uuid);
        Community removed = communities.remove(uuid);
        if (removed != null) {
            setDirty();
        }
        return removed;
    }

    /**
     * Fuses two communities into one.
     * The preserved community will absorb the fused community.
     * The UUID of the preserved community remains the same.
     * @param level The server level
     * @param preservedCommunity The UUID of the community to preserve
     * @param fusedCommunity The UUID of the community to fuse
     * @param newCenter The new center position for the fused community
     * @param newRadius The new radius for the fused community
     */
    public void fuseCommunities(ServerLevel level,
                                UUID preservedCommunity, UUID fusedCommunity,
                                BlockPos newCenter, int newRadius) {
        LOGGER.info("Fusing community {} into community {}",
                fusedCommunity, preservedCommunity);
        Community target = communities.get(preservedCommunity);
        Community source = communities.get(fusedCommunity);
        if (target == null || source == null) return;
        target.fuseCommunity(source, level, newCenter, newRadius);
        this.removeCommunity(source.uuid());
    }

    /**
     * Finds the closest community to a given position.
     * @param position The position to check
     * @return The closest community, or null if no communities exist
     */
    public @Nullable Community getClosestCommunity(BlockPos position) {
        Community closest = null;
        double closestDistanceSq = Double.MAX_VALUE;
        for (Community community : communities.values()) {
            double distanceSq = community.distanceTo(position);
            if (distanceSq < closestDistanceSq) {
                closestDistanceSq = distanceSq;
                closest = community;
            }
        }
        return closest;
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
     * Remove empty communities.
     * Should be called from server tick event.
     */
    public void tickAll() {
        List<UUID> toRemove = new ArrayList<>();
        for (Community community : communities.values()) {
            if (community.hasLoadedMembers()) {
                community.tick();
            } else if (community.isEmpty()) {
                toRemove.add(community.uuid());
            }
        }
        for (UUID uuid : toRemove) {
            this.removeCommunity(uuid);
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
        LOGGER.info("Saved Thryssaryn {} communities", communities.size());
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
        LOGGER.info("Loaded {} Thryssaryn communities", data.communities.size());
        for (Community community : data.communities.values()) {
            LOGGER.info(" - Community {} with {} members at {} (radius {})",
                    community.uuid(), community.members().size(),
                    community.center(), community.radius());
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
