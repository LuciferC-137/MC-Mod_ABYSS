package wardentools.entity.thryssaryn.individual.behavior.goap.actions;

import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WSK;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;

public class MoveToPOI extends MoveTo {
    private final POIInstance poi;

    public MoveToPOI(ThryssarynEntity entity, POIInstance target,
                     double speedModifier, float arrivalThreshold) {
        super(entity, new MoveTarget.ToBlock(target.pos()), speedModifier, arrivalThreshold);
        this.poi = target;
    }

    @Override
    public @NotNull WorldState applyEffects(WorldState ws) {
        WorldState newWS = super.applyEffects(ws);
        newWS.set(WSK.POI_AT, this.poi);
        return newWS;
    }
}
