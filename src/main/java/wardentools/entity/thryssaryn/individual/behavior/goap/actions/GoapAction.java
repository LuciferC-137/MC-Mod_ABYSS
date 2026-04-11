package wardentools.entity.thryssaryn.individual.behavior.goap.actions;

import net.minecraft.world.entity.PathfinderMob;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;

public class GoapAction {
    PathfinderMob mob;

    public GoapAction(PathfinderMob mob) {
        this.mob = mob;
    }

    public boolean checkPreconditions(WorldState ws) {
        return true;
    }

    public @NotNull WorldState applyEffects(WorldState ws) {
        return ws;
    }

    public @NotNull Status execute(WorldState ws) {
        return Status.FAILURE;
    }

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILURE
    }
}
