package wardentools.entity.thryssaryn.individual.behavior.task;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.SerializableCompound;

import java.util.Set;

/**
 * Class to represent an atomic task an individual can perform.
 * Tasks have requirements (no ordering). They can only be performed if all
 * requirements are met. Those requirements might have requirement themselves, creating
 * a tree of tasks.
 */
public class Task implements Requirement, SerializableCompound {
    // Data for usage outside the Task
    private long lastTimeComplete = -1;
    public final Set<Requirement> requirements;
    private int priority;

    public static final int DEFAULT_MAX_TIME = 12000; // 10min

    private TaskState state;
    public int tickCount = 0;
    private final int maxTimeAlive;
    public final ThryssarynEntity entity;

    public Task(ThryssarynEntity entity, Set<Requirement> requirements,
                int maxTimeAlive, int priority) {
        this.requirements = requirements;
        this.entity = entity;
        this.maxTimeAlive = maxTimeAlive;
    }

    public void tick() {
        if (state != TaskState.RUNNING) return;
        tickCount++;
        if  (tickCount >= maxTimeAlive) {
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
        this.state = TaskState.RUNNING;
    }

    public void markDone() {
        this.state = TaskState.DONE;
        this.lastTimeComplete = this.entity.level().getGameTime();
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

    public int getPriority() {return this.priority;}

    public void setPriority(int priority) {this.priority = priority;}

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

    @Override
    public @NotNull CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("state", this.state.ordinal());
        tag.putInt("tickCount", this.tickCount);
        tag.putLong("lastTimeComplete", this.lastTimeComplete);
        tag.putInt("priority", this.priority);
        return tag;
    }
}
