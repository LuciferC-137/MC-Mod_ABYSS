package wardentools.entity.thryssaryn.individual.behavior;

import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.thryssaryn.individual.AbstractThryssaryn;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIType;

import java.util.*;
import java.util.stream.Collectors;

public class ThryssarynMemory {
    private final AbstractThryssaryn entity;

    // Map of all POI known to the mob.
    private final Map<POIType, Set<POIInstance>> knownPOIs = new EnumMap<>(POIType.class);

    public ThryssarynMemory(AbstractThryssaryn entity) {
        this.entity = entity;
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
                        this.entity.position().distanceToSqr(Vec3.atCenterOf(p.pos()))));
    }

    /** Return nearest POI that can contain the given Item. */
    public List<POIInstance> nearestPOIsFor(Item item) {
        return POIType.typesFor(item).stream()
                .flatMap(t -> nearest(t).stream())
                .sorted(Comparator.comparingDouble(p ->
                        entity.position().distanceToSqr(Vec3.atCenterOf(p.pos()))))
                .toList();
    }

    /** Return nearest POI that can contain the given Block */
    public List<POIInstance> nearestPOIsFor(net.minecraft.world.level.block.Block block) {
        return POIType.typesFor(block).stream()
                .flatMap(t -> nearest(t).stream())
                .sorted(Comparator.comparingDouble(p ->
                        entity.position().distanceToSqr(Vec3.atCenterOf(p.pos()))))
                .toList();
    }
}
