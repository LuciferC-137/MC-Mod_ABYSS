package wardentools.entity.thryssaryn.individual.behavior.sense;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;

public class Sense {
    public ThryssarynEntity entity;

    public Sense(ThryssarynEntity entity) {
        this.entity = entity;
    }

    public void tick() {

    }

    public WorldState update(WorldState ws) {
        return ws;
    }
}
