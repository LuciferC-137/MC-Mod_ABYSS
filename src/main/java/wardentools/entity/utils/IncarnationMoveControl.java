package wardentools.entity.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.VoxelShape;
import wardentools.entity.custom.ContagionIncarnationEntity;

public class IncarnationMoveControl extends MoveControl {
    public static final float MIN_SPEED_SQR = 2.5000003E-7F;
    protected static final int MAX_TURN = 10;
    private static final float rad = (float)Math.PI / 180F;
    protected final ContagionIncarnationEntity incarnation;
    protected double wantedX;
    protected double wantedY;
    protected double wantedZ;
    protected double speedModifier;
    protected float strafeForwards;
    protected float strafeRight;
    protected IncarnationMoveControl.Operation operation = IncarnationMoveControl.Operation.WAIT;

    public IncarnationMoveControl(ContagionIncarnationEntity mob) {
        super(mob);
        this.incarnation = mob;
    }

    @Override
    public boolean hasWanted() {
        return this.operation == IncarnationMoveControl.Operation.MOVE_TO;
    }

    @Override
    public double getSpeedModifier() {
        return this.speedModifier;
    }

    @Override
    public void setWantedPosition(double wantedX, double wantedY, double wantedZ, double speedModifier) {
        this.wantedX = wantedX;
        this.wantedY = wantedY;
        this.wantedZ = wantedZ;
        this.speedModifier = speedModifier;
        if (this.operation != IncarnationMoveControl.Operation.JUMPING) {
            this.operation = IncarnationMoveControl.Operation.MOVE_TO;
        }

    }

    @Override
    public void strafe(float strafeForwards, float strafeRight) {
        this.operation = IncarnationMoveControl.Operation.STRAFE;
        this.strafeForwards = strafeForwards;
        this.strafeRight = strafeRight;
        this.speedModifier = 0.25D;
    }

    @Override
    public void tick() {
        if (this.operation == IncarnationMoveControl.Operation.STRAFE) {
            float speedAttribute = (float)this.incarnation.getAttributeValue(Attributes.MOVEMENT_SPEED);
            float modifiedSpeed = (float)this.speedModifier * speedAttribute;
            float strafeSpeed = Mth.sqrt(this.strafeForwards * this.strafeForwards
                    + this.strafeRight * this.strafeRight);
            if (strafeSpeed < 1.0F) strafeSpeed = 1.0F;
            strafeSpeed = modifiedSpeed / strafeSpeed;
            this.strafeForwards *= strafeSpeed;
            this.strafeRight *= strafeSpeed;
            float sinYRot = Mth.sin(this.incarnation.getYRot() * rad);
            float cosYRot = Mth.cos(this.incarnation.getYRot() * rad);
            float strafeX = this.strafeForwards * cosYRot - this.strafeRight * sinYRot;
            float strafeZ = this.strafeRight * cosYRot + this.strafeForwards * sinYRot;
            if (!this.isWalkable(strafeX, strafeZ)) {
                this.strafeForwards = 1.0F;
                this.strafeRight = 0.0F;
            }
            this.incarnation.setSpeed(modifiedSpeed);
            this.incarnation.setZza(this.strafeForwards);
            this.incarnation.setXxa(this.strafeRight);
            this.operation = IncarnationMoveControl.Operation.WAIT;
        } else if (this.operation == IncarnationMoveControl.Operation.MOVE_TO) {
            this.operation = IncarnationMoveControl.Operation.WAIT;
            double dx = this.wantedX - this.incarnation.getX();
            double dy = this.wantedZ - this.incarnation.getZ();
            double dz = this.wantedY - this.incarnation.getY();
            double distSqr = dx * dx + dz * dz + dy * dy;
            if (distSqr < (double)MIN_SPEED_SQR) {
                this.incarnation.setZza(0.0F);
                return;
            }
            float targetYaw = (float)(Mth.atan2(dy, dx)) / rad - 90.0F;
            this.incarnation.setYRot(this.rotlerp(this.incarnation.getYRot(), targetYaw, MAX_TURN));
            this.incarnation.setSpeed((float)(this.speedModifier
                    * this.incarnation.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            BlockPos blockpos = this.incarnation.blockPosition();
            BlockState blockstate = this.incarnation.level().getBlockState(blockpos);
            VoxelShape voxelshape = blockstate.getCollisionShape(this.incarnation.level(), blockpos);
            if (dz > (double)this.incarnation.maxUpStep()
                    && dx * dx + dy * dy < (double)Math.max(1.0F, this.incarnation.getBbWidth())
                    || !voxelshape.isEmpty()
                    && this.incarnation.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY()
                    && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES)) {
                this.incarnation.getJumpControl().jump();
                this.operation = IncarnationMoveControl.Operation.JUMPING;
            }
        } else if (this.operation == IncarnationMoveControl.Operation.JUMPING) {
            this.incarnation.setSpeed((float)(this.speedModifier
                    * this.incarnation.getAttributeValue(Attributes.MOVEMENT_SPEED)));
            if (this.incarnation.onGround()) {
                this.operation = IncarnationMoveControl.Operation.WAIT;
            }
        } else {
            this.incarnation.setZza(0.0F);
        }

    }

    private boolean isWalkable(float dx, float dy) {
        PathNavigation navigation = this.mob.getNavigation();
        NodeEvaluator nodeEvaluator = navigation.getNodeEvaluator();
        return nodeEvaluator.getPathType(this.mob, BlockPos
                .containing(this.mob.getX() + (double) dx,
                        (double) this.mob.getBlockY(), this.mob.getZ() + (double) dy))
                == PathType.WALKABLE;
    }

    protected float rotlerp(float currentYaw, float targetYaw, float maxTurn) {
        float deltaYaw = Mth.wrapDegrees(targetYaw - currentYaw);
        if (deltaYaw > maxTurn) deltaYaw = maxTurn;
        if (deltaYaw < -maxTurn) deltaYaw = -maxTurn;
        float newYaw = currentYaw + deltaYaw;
        if (newYaw < 0.0F) newYaw += 360.0F;
        else if (newYaw > 360.0F) newYaw -= 360.0F;
        return newYaw;
    }

    @Override
    public double getWantedX() {
        return this.wantedX;
    }

    @Override
    public double getWantedY() {
        return this.wantedY;
    }

    @Override
    public double getWantedZ() {
        return this.wantedZ;
    }

    protected enum Operation {
        WAIT,
        MOVE_TO,
        STRAFE,
        JUMPING;
    }
}
