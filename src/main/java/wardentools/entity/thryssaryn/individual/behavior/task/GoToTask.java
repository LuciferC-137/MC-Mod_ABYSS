package wardentools.entity.thryssaryn.individual.behavior.task;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.pathfinder.Path;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import javax.annotation.Nullable;
import java.util.Set;

public class GoToTask extends Task {
    private static final int INTERVAL_BETWEEN_PATHFINDING = 40; // 2s
    private static final float MAX_DISTANCE_TO_BE_FEASIBLE = 300F;

    private long lastEvaluation = -1;
    @Nullable private Path path;

    BlockPos target;

    public GoToTask(ThryssarynEntity entity, BlockPos target) {
        super(entity, Set.of(), Task.DEFAULT_MAX_TIME);
        this.target = target;
    }

    public GoToTask(ThryssarynEntity entity, Entity targetEntity) {
        super(entity, Set.of(), Task.DEFAULT_MAX_TIME);
        this.target = targetEntity.blockPosition();
    }

    @Override
    public void start() {
        super.start();
        this.entity.moveTo(target.getCenter());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.timeAlive % INTERVAL_BETWEEN_PATHFINDING == 0 || lastEvaluation == -1) {
            this.entity.moveTo(target.getCenter());
            this.path = this.entity.getNavigation().getPath();
        }
        if (this.entity.getNavigation().isDone()) {
            if (this.entity.blockPosition().closerThan(this.target, 1.5F)) {
                this.markDone();
            } else {
                this.fail();
            }
        }
    }

    private void evaluatePath() {
        this.lastEvaluation = this.entity.level().getGameTime();
        this.path = this.entity.getNavigation().createPath(target, 1);
    }

    private boolean shouldRecompute() {
        return this.entity.level().getGameTime() - this.lastEvaluation > INTERVAL_BETWEEN_PATHFINDING;
    }

    @Override
    public float getDifficulty(ThryssarynEntity entity) {
        if (this.path == null && shouldRecompute()) evaluatePath();
        if (this.path == null) return Requirement.MAX_DIFFICULTY;
        return Requirement.clampDifficulty(this.path.getDistToTarget() / MAX_DISTANCE_TO_BE_FEASIBLE);
    }
}
