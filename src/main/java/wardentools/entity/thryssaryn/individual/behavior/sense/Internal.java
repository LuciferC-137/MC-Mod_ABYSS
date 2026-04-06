package wardentools.entity.thryssaryn.individual.behavior.sense;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WSK;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;

public class Internal extends Sense {

    public Internal(ThryssarynEntity entity) {
        super(entity);
    }

    @Override
    public WorldState update(WorldState ws) {
        ws.set(WSK.HEALTH, this.entity.getHealth());
        ws.set(WSK.HUNGER, this.entity.getHunger());
        return super.update(ws);
    }
}
