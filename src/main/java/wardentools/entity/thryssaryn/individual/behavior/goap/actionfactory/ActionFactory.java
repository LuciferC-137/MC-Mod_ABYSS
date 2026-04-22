package wardentools.entity.thryssaryn.individual.behavior.goap.actionfactory;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;
import wardentools.entity.thryssaryn.individual.behavior.goap.actions.GoapAction;
import wardentools.entity.thryssaryn.individual.behavior.goap.actions.MoveToPOI;
import wardentools.entity.thryssaryn.individual.behavior.goap.poi.POIInstance;

import java.util.HashSet;
import java.util.Set;

/**
 * The Action Factory is the builder that collects and instantiates all possible actions
 * for the scheduler to chose from.
 * Complex actions have their own factory extending this base class.
*/
public class ActionFactory {
    private final ThryssarynEntity entity;

    public ActionFactory(ThryssarynEntity entity) {
        this.entity = entity;
    }

    public Set<GoapAction> generate(WorldState ws) {
        Set<GoapAction> moveToPOIs = buildMoveToActions(ws);
        return moveToPOIs;
    }

    private Set<GoapAction> buildMoveToActions(WorldState ws) {
        Set<POIInstance> pois = this.entity.getThrBrain().getMemory().getAllPOIs();
        Set<GoapAction> actions = new HashSet<GoapAction>();
        for  (POIInstance poi : pois) {
            MoveToPOI moveToPOI = new MoveToPOI(this.entity, poi,
                    1.0F, 4);
            actions.add(moveToPOI);
        }
        return actions;
    }
}
