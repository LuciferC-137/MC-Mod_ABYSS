package wardentools.entity.thryssaryn.individual.behavior.goap.actions;

import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;

public class SeekPOI extends GoapAction {

    public SeekPOI(ThryssarynEntity entity) {
        super(entity);
    }

    public boolean checkPreconditions(WorldState ws) {
        return true;
    }

    public @NotNull WorldState applyEffects(WorldState ws) {
        // TODO: fetch pois from Community
        return ws;
    }

    public @NotNull Status execute(WorldState ws) {
        return Status.FAILURE;
    }
}
