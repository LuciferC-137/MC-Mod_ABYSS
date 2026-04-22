package wardentools.entity.thryssaryn.individual.behavior.goap.poi;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.community.Community;
import wardentools.entity.thryssaryn.community.CommunityMemory;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import java.util.*;
import java.util.stream.Collectors;

public class POIMemory implements INBTSerializable<CompoundTag> {
    private PositionedEntity entity;

    // Map of all POI known to the mob.
    private final Map<POIType, Set<POIInstance>> knownPOIs = new EnumMap<>(POIType.class);

    public POIMemory(ThryssarynEntity thryssaryn) {
        this.entity = thryssaryn::position;
    }

    public POIMemory(CommunityMemory community) {
        this.entity = () -> community.getCenter().getCenter();
    }

    public void setPositionedEntity(CommunityMemory community) {
        this.entity = () -> community.getCenter().getCenter();
    }

    public void setPositionedEntity(ThryssarynEntity thryssaryn) {
        this.entity = thryssaryn::position;
    }

    public Set<POIType> getKnownPOITypes() {
        return this.knownPOIs.keySet();
    }

    public Set<POIInstance> getPOIs(POIType type) {
        return this.knownPOIs.get(type);
    }

    public Set<POIInstance> getAllPOIs() {
        return this.knownPOIs.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /** Return closest POI for a given type */
    public Optional<POIInstance> nearest(POIType type) {
        return knownPOIs.getOrDefault(type, Set.of()).stream()
                .min(Comparator.comparingDouble(p ->
                        this.entity.position().distanceToSqr(Vec3.atCenterOf(p.getPos()))));
    }

    /** Return nearest POI that can contain the given Item. */
    public List<POIInstance> nearestPOIsFor(Item item) {
        return POIType.typesFor(item).stream()
                .flatMap(t -> nearest(t).stream())
                .sorted(Comparator.comparingDouble(p ->
                        entity.position().distanceToSqr(Vec3.atCenterOf(p.getPos()))))
                .toList();
    }

    /** Return nearest POI that can contain the given Block */
    public List<POIInstance> nearestPOIsFor(net.minecraft.world.level.block.Block block) {
        return POIType.typesFor(block).stream()
                .flatMap(t -> nearest(t).stream())
                .sorted(Comparator.comparingDouble(p ->
                        entity.position().distanceToSqr(Vec3.atCenterOf(p.getPos()))))
                .toList();
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<POIType, Set<POIInstance>> entry : knownPOIs.entrySet()) {
            ListTag listTag = new ListTag();
            entry.getValue().stream()
                    .map(p -> p.serializeNBT(provider))
                    .forEach(listTag::add);
            tag.put(entry.getKey().name(), listTag);
        }
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider,
                               @NotNull CompoundTag tag) {
        for (POIType poiType : POIType.values()) {
            if (tag.contains(poiType.name())) {
                ListTag listTag = tag.getList(poiType.name(), Tag.TAG_COMPOUND);
                Set<POIInstance> set = new HashSet<>();
                for (int i = 0; i < listTag.size(); i++) {
                    POIInstance poi = new POIInstance();
                    poi.deserializeNBT(provider, listTag.getCompound(i));
                    set.add(poi);
                }
                knownPOIs.put(poiType, set);
            }
        }
    }

    private interface PositionedEntity {
        Vec3 position();
    }
}
