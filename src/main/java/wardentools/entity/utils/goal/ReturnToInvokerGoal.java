package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import wardentools.entity.custom.ProtectorEntity;

import java.util.EnumSet;

public class ReturnToInvokerGoal extends Goal {
    private final ProtectorEntity entity;
    private final double speedModifier;
    private final boolean canTeleport;
    private static final int goCloserThan = 5;

    public ReturnToInvokerGoal(ProtectorEntity entity, double speedModifier, boolean canTeleport) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.canTeleport = canTeleport;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        return this.entity.invokerPos != null
                && !this.entity.invokerPos.closerThan(this.entity.blockPosition(), ProtectorEntity.invokerRadius);
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.entity.invokerPos.getX(),
                this.entity.invokerPos.getY(), this.entity.invokerPos.getZ(), this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return this.entity.invokerPos != null
                && !this.entity.invokerPos.closerThan(this.entity.blockPosition(), goCloserThan);
    }

    @Override
    public void tick() {
        if (canTeleport && !this.entity.invokerPos.closerThan(this.entity.blockPosition(), 100)) {
            this.entity.teleportTo(this.entity.invokerPos.getX() + 1,
                    this.entity.invokerPos.getY(), this.entity.invokerPos.getZ() + 1);
        }
        this.entity.getNavigation().moveTo(this.entity.invokerPos.getX(),
                this.entity.invokerPos.getY(), this.entity.invokerPos.getZ(), this.speedModifier);
        super.tick();
    }

    @Override
    public void stop() {
        this.entity.getNavigation().stop();
    }


}
