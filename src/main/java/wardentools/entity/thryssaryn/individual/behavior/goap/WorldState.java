package wardentools.entity.thryssaryn.individual.behavior.goap;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class WorldState {
    private Map<WSK, Object> facts = new HashMap<>();

    public WorldState() {
    }

    public WorldState(Map<WSK, Object> facts) {
        this.facts = facts;
    }

    public void set(WSK wsk, Object value) {
        if (!wsk.getType().isInstance(value)) {
            throw new IllegalArgumentException("Value for " + wsk +
                    " must be of type " + wsk.getType().getSimpleName());
        }
        facts.put(wsk, value);
    }

    public @Nullable Object get(WSK wsk) {
        return facts.getOrDefault(wsk, null);
    }

    public boolean contains(WSK wsk) {
        return facts.containsKey(wsk);
    }

    public WorldState with(WSK wsk, Object value) {
        Map<WSK, Object> newFacts = new HashMap<>(facts);
        if (!wsk.getType().isInstance(value)) {
            throw new IllegalArgumentException("Value for " + wsk +
                    " must be of type " + wsk.getType().getSimpleName());
        }
        newFacts.put(wsk, value);
        return new WorldState(newFacts);
    }

    public WorldState copy() {
        return new WorldState(facts);
    }
}
