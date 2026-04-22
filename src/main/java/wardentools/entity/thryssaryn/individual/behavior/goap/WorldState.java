package wardentools.entity.thryssaryn.individual.behavior.goap;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorldState {
    private final Map<WSK<?>, Object> facts;

    public WorldState() {
        this.facts = new HashMap<>();
    }

    public WorldState(Map<WSK<?>, Object> facts) {
        this.facts = new HashMap<>(facts);
    }

    public <T> void set(WSK<T> wsk, T value) {
        if (!wsk.getType().isInstance(value)) {
            throw new IllegalArgumentException("Value for " + wsk +
                    " must be of type " + wsk.getType().getSimpleName());
        }
        facts.put(wsk, value);
    }

    public <T> @Nullable T get(WSK<T> wsk) {
        Object value = facts.get(wsk);
        if (value == null) {
            return null;
        }
        return wsk.getType().cast(value);
    }

    public <T> boolean contains(WSK<T> wsk) {
        return facts.containsKey(wsk);
    }

    public <T> WorldState with(WSK<T> wsk, T value) {
        Map<WSK<?>, Object> newFacts = new HashMap<>(facts);
        if (!wsk.getType().isInstance(value)) {
            throw new IllegalArgumentException("Value for " + wsk +
                    " must be of type " + wsk.getType().getSimpleName());
        }
        newFacts.put(wsk, value);
        return new WorldState(newFacts);
    }

    public boolean knowsPOI(POIType type) {
        Set<POIType> poiTypes = this.get(WSK.KNOWN_POI_TYPES);
        if (poiTypes == null) return false;
        return poiTypes.contains(type);
    }

    public boolean hasAccessTo(Item item) {
        POIInstance poi = this.get(WSK.POI_AT);
        return poi != null && poi.type().canContain(item);
    }

    public boolean hasAccessTo(Block block) {
        POIInstance poi = this.get(WSK.POI_AT);
        return poi != null && poi.type().canContain(block);
    }

    public WorldState copy() {
        return new WorldState(new HashMap<>(facts));
    }
}
