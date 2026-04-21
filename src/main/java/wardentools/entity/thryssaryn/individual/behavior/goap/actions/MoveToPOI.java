package wardentools.entity.thryssaryn.individual.behavior.goap.actions;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;

public class MoveToPOI extends MoveTo {

    public MoveToPOI(ThryssarynEntity entity, POIInstance target,
                     double speedModifier, float arrivalThreshold) {
        super(entity, new MoveTarget.ToBlock(target.pos()), speedModifier, arrivalThreshold);
    }
}
