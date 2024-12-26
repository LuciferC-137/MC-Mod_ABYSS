package wardentools.entity.utils.goal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import wardentools.entity.custom.ContagionIncarnationEntity;

import java.util.EnumSet;

public class IncarnationAttackGoal extends Goal {
    protected final ContagionIncarnationEntity incarnation;
    private final double speedModifier;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private final int attackInterval = 40;
    private long lastCanUseCheck;
    private static final int COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20;

    public IncarnationAttackGoal(ContagionIncarnationEntity incarnation,
                                 double speedModifier) {
        this.incarnation = incarnation;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        return this.internalCanUse() && this.incarnation.getSonicStrikeTick() == 0;
    }

    public boolean internalCanUse() {
        long i = this.incarnation.level().getGameTime();
        if (i - this.lastCanUseCheck < COOLDOWN_BETWEEN_CAN_USE_CHECKS) return false;
        else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.incarnation.getTarget();
            if (livingentity == null) return false;
            else if (!livingentity.isAlive()) return false;
            else {
                this.path = this.incarnation.getNavigation().createPath(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else return this.incarnation.isWithinMeleeAttackRange(livingentity);
            }
        }
    }

    public boolean canContinueToUse() {
        return this.internalCanContinueToUse() && this.incarnation.getSonicStrikeTick() == 0;
    }

    public boolean internalCanContinueToUse() {
        LivingEntity entity = this.incarnation.getTarget();
        if (entity == null) return false;
        else if (!entity.isAlive()) return false;
        else if (!this.incarnation.isWithinRestriction(entity.blockPosition())) return false;
        else {
            return !(entity instanceof Player) || !entity.isSpectator() && !((Player)entity).isCreative();
        }
    }

    public void start() {
        this.incarnation.getNavigation().moveTo(this.path, this.speedModifier);
        this.incarnation.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

    public void stop() {
        LivingEntity livingentity = this.incarnation.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.incarnation.setTarget((LivingEntity)null);
        }
        this.incarnation.setAggressive(false);
        this.incarnation.getNavigation().stop();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.incarnation.getTarget();
        if (livingentity != null) {
            this.incarnation.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if (this.incarnation.getSensing().hasLineOfSight(livingentity)
                    && this.ticksUntilNextPathRecalculation <= 0
                    && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D
                    && this.pathedTargetZ == 0.0D
                    || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D
                    || this.incarnation.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4
                        + this.incarnation.getRandom().nextInt(7);
                double distanceToTarget = this.incarnation.distanceTo(livingentity);
                if (distanceToTarget > 32f) this.ticksUntilNextPathRecalculation += 40;
                else if (distanceToTarget > 16f) this.ticksUntilNextPathRecalculation += 20;
                if (!this.incarnation.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 20;
                }
                this.ticksUntilNextPathRecalculation
                        = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(livingentity);
        }
    }

    protected void checkAndPerformAttack(LivingEntity entity) {
        if (this.canPerformAttack(entity)) {
            this.resetAttackCooldown();
            this.incarnation.swing(InteractionHand.MAIN_HAND);
            this.incarnation.swingWithClosestHand();
        }
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackInterval);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean canPerformAttack(LivingEntity entity) {
        return this.isTimeToAttack() && this.incarnation.isWithinMeleeAttackRange(entity)
                && this.incarnation.getSensing().hasLineOfSight(entity);
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected int getAttackInterval() {
        return this.adjustedTickDelay(attackInterval);
    }
}
