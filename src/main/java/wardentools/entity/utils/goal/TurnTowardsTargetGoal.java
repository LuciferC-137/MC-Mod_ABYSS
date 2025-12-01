package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.ContagionIncarnationEntity;

public class TurnTowardsTargetGoal extends Goal {
    private static final float ANGLE_THRESHOLD = 10F;
    private static final double PUSH_STRENGTH = 0.15D;

    private final ContagionIncarnationEntity incarnation;
    private final double speed;
    private int stuckTicks = 0;
    private static final int MAX_STUCK_TICKS = 40;

    public TurnTowardsTargetGoal(ContagionIncarnationEntity incarnation, double speed) {
        this.incarnation = incarnation;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        return this.incarnation.horizontalCollision && this.incarnation.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return (this.incarnation.horizontalCollision || this.angleTowardTarget() > ANGLE_THRESHOLD)
                && this.incarnation.getTarget() != null
                && stuckTicks < MAX_STUCK_TICKS;
    }

    @Override
    public void start() {
        super.start();
        stuckTicks = 0;
    }

    @Override
    public void tick() {
        super.tick();

        float targetAngle = this.angleTowardTarget();
        float currentYaw = this.incarnation.getYRot();
        float angleDifference = targetAngle - currentYaw;

        while (angleDifference > 180F) angleDifference -= 360F;
        while (angleDifference < -180F) angleDifference += 360F;

        if (Math.abs(angleDifference) > ANGLE_THRESHOLD) {
            float turnAmount = (float) (this.speed * Math.signum(angleDifference));
            this.incarnation.setYRot(currentYaw + turnAmount);
            this.incarnation.yRotO = this.incarnation.getYRot();
        }

        // Push away from wall if in collision
        if (this.incarnation.horizontalCollision) {
            pushAwayFromWall();
            stuckTicks++;
        }
    }

    private void pushAwayFromWall() {
        AABB boundingBox = this.incarnation.getBoundingBox();
        Vec3 entityPos = this.incarnation.position();

        int minX = (int) Math.floor(boundingBox.minX);
        int maxX = (int) Math.ceil(boundingBox.maxX);
        int minY = (int) Math.floor(boundingBox.minY);
        int maxY = (int) Math.ceil(boundingBox.maxY);
        int minZ = (int) Math.floor(boundingBox.minZ);
        int maxZ = (int) Math.ceil(boundingBox.maxZ);

        Vec3 pushDirection = Vec3.ZERO;
        int collisionCount = 0;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = this.incarnation.level().getBlockState(pos);

                    if (!state.isAir() && state.isSolidRender(this.incarnation.level(), pos)) {
                        Vec3 blockCenter = Vec3.atCenterOf(pos);
                        Vec3 awayFromBlock = entityPos.subtract(blockCenter).normalize();

                        awayFromBlock = new Vec3(awayFromBlock.x, 0, awayFromBlock.z).normalize();

                        pushDirection = pushDirection.add(awayFromBlock);
                        collisionCount++;
                    }
                }
            }
        }

        if (collisionCount > 0) {
            pushDirection = pushDirection.normalize().scale(PUSH_STRENGTH);
            Vec3 currentDelta = this.incarnation.getDeltaMovement();
            this.incarnation.setDeltaMovement(
                    currentDelta.x + pushDirection.x,
                    currentDelta.y,
                    currentDelta.z + pushDirection.z
            );
        }
    }

    private float angleTowardTarget() {
        if (this.incarnation.getTarget() == null) return 0F;
        double dx = this.incarnation.getTarget().getX() - this.incarnation.getX();
        double dz = this.incarnation.getTarget().getZ() - this.incarnation.getZ();
        return (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
    }
}