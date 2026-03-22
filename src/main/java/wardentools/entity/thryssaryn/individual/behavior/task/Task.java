package wardentools.entity.thryssaryn.individual.behavior.task;

import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

import java.util.Set;

/**
 * Class to represent an atomic task an individual can perform.
 * Tasks have requirements (no ordering). They can only be performed if all
 * requirements are met. Those requirements might have requirement themselves, creating
 * a tree of tasks.
 */
public class Task implements Requirement {
    public static final int DEFAULT_MAX_TIME = 12000; // 10min

    public final Set<Requirement> requirements;
    private TaskState state;
    public int timeAlive = 0;
    private final int maxTimeAlive;
    public final ThryssarynEntity entity;

    public Task(ThryssarynEntity entity, Set<Requirement> requirements, int maxTimeAlive) {
        this.requirements = requirements;
        this.entity = entity;
        this.maxTimeAlive = maxTimeAlive;
    }

    public void tick() {
        if (state != TaskState.ALIVE) return;
        timeAlive++;
        if  (timeAlive >= maxTimeAlive) {
            this.fail();
        }
    }

    public boolean canUse() {
        for (Requirement requirement : requirements) {
            if (!requirement.isMet(this.entity)) {
                return false;
            }
        }
        return true;
    }

    public void start() {
        this.state = TaskState.ALIVE;
    }

    public void markDone() {
        this.state = TaskState.DONE;
    }

    public void fail() {
        this.state = TaskState.FAILED;
    }

    public TaskState getState() {
        return state;
    }

    public boolean isTerminal() {
        return this.requirements.isEmpty();
    }

    @Override
    public boolean isMet(ThryssarynEntity entity) {
        return state == TaskState.DONE;
    }

    @Override
    public float getDifficulty(ThryssarynEntity entity) {
        return 1F;
    }

    @Override
    public int getEstimatedTime(ThryssarynEntity entity) {
        return 0;
    }
}
