package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.phys.Vec3;
import wardentools.block.BlockRegistry;
import wardentools.entity.custom.DeepLurkerEntity;

import java.util.EnumSet;
import java.util.List;

public class AvoidWardenAndClimbTreeGoal extends Goal {
    private final DeepLurkerEntity entity;
    private final double speedModifier;
    private final double climbSpeedModifier;
    private LivingEntity target;
    private BlockPos targetTreePos;
    private static final int scaredRadius = 20;

    public AvoidWardenAndClimbTreeGoal(DeepLurkerEntity entity, double speedModifier,
                                       double climbSpeedModifier) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.climbSpeedModifier = climbSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        List<Warden> wardens = entity.level().getEntitiesOfClass(Warden.class,
                entity.getBoundingBox().inflate(scaredRadius));
        if (!wardens.isEmpty()) {
            this.target = wardens.get(0);
            BlockPos startPos = entity.blockPosition();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            int y = startPos.getY();
            for (int r = 1; r <= scaredRadius; r++) {
                for (int dx = -r; dx <= r; dx++) {
                    for (int dz = -r; dz <= r; dz++) {
                        if (Math.abs(dx) == r || Math.abs(dz) == r) {
                            mutableBlockPos.set(startPos.getX() + dx, y,
                                    startPos.getZ() + dz);
                            if (entity.level().getBlockState(mutableBlockPos).getBlock()
                                    == BlockRegistry.DARKTREE_LOG.get()) {
                                this.targetTreePos = mutableBlockPos.immutable();
                                this.targetTreePos = this.getTargetOnTree();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (this.targetTreePos != null) {
            this.entity.getNavigation().moveTo(targetTreePos.getX(),
                    targetTreePos.getY(), targetTreePos.getZ(), this.speedModifier);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() &&
                this.entity.distanceTo(this.target) < scaredRadius &&
                this.entity.level().getBlockState(targetTreePos).getBlock()
                        == BlockRegistry.DARKTREE_LOG.get();
    }

    @Override
    public void stop() {
        this.target = null;
        this.targetTreePos = null;
        this.entity.setClimbing(false);
        this.entity.setNoGravity(false);
        this.entity.setScared(false);
    }

    @Override
    public void tick() {
        if (this.target != null && this.entity.horizontalCollision && !isAtTopOfTree()) {
            this.entity.setClimbing(true);
            this.entity.getNavigation().moveTo(this.targetTreePos.getX(),
                    this.targetTreePos.getY(), this.targetTreePos.getZ(), this.climbSpeedModifier);
            this.entity.setDeltaMovement(this.entity.getDeltaMovement()
                    .add(0.0D, 0.2D, 0.0D));
        }

        else if (isAtTopOfTree()) {
            this.entity.getLookControl().setLookAt(this.target);
            this.entity.setDeltaMovement(Vec3.ZERO);
            this.entity.getNavigation().stop();
            this.entity.setNoGravity(true);
            this.entity.setScared(true);
        }
    }

    private boolean isAtTopOfTree() {
        BlockPos pos = this.entity.blockPosition();
        return this.entity.level().getBlockState(pos.above()).getBlock()
                == BlockRegistry.DARKTREE_LEAVES.get()
                || this.entity.level().getBlockState(pos.above()).getBlock()
                == BlockRegistry.DARKTREE_LOG.get();
    }

    private BlockPos getTargetOnTree() {
        if (targetTreePos != null) {
            int i = 0;
            while (!this.entity.level().getBlockState(targetTreePos.above(i)).isAir() && i < 20) {
                i += 1;
            }
            return targetTreePos.above(i);
        }
        return null;
    }
}