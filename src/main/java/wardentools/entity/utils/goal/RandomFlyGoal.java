package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class RandomFlyGoal extends Goal {
    private final NoctilureEntity noctilure;
    private float angle;
    private float distance;
    private float height;
    private float clockwise;

    public RandomFlyGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        this.distance = 5.0F + this.noctilure.getRandom().nextFloat() * 10.0F;
        this.height = -4.0F + this.noctilure.getRandom().nextFloat() * 9.0F;
        this.clockwise = this.noctilure.getRandom().nextBoolean() ? 1.0F : -1.0F;
        this.selectNext();
    }

    private void selectNext() {
        if (BlockPos.ZERO.equals(this.noctilure.anchorPoint)) {
            this.noctilure.anchorPoint = this.noctilure.blockPosition();
        }
        this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
        this.noctilure.moveTargetPoint = Vec3.atLowerCornerOf(this.noctilure.anchorPoint)
                .add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height),
                        (double)(this.distance * Mth.sin(this.angle)));
    }

    @Override
    public void tick() {
        if (this.noctilure.getRandom().nextInt(this.adjustedTickDelay(350)) == 0) {
            this.height = -4.0F + this.noctilure.getRandom().nextFloat() * 9.0F;
        }
        if (this.noctilure.getRandom().nextInt(this.adjustedTickDelay(250)) == 0) {
            ++this.distance;
            if (this.distance > 15.0F) {
                this.distance = 5.0F;
                this.clockwise = -this.clockwise;
            }
        }
        if (this.noctilure.getRandom().nextInt(this.adjustedTickDelay(450)) == 0) {
            this.angle = this.noctilure.getRandom().nextFloat() * 2.0F * (float)Math.PI;
            this.selectNext();
        }
        if (this.touchingTarget()) {
            this.selectNext();
        }
        if (this.noctilure.moveTargetPoint.y < this.noctilure.getY() 
                && !this.noctilure.level().isEmptyBlock(this.noctilure.blockPosition().below(1))) {
            this.height = Math.max(1.0F, this.height);
            this.selectNext();
        }
        if (this.noctilure.moveTargetPoint.y > this.noctilure.getY() 
                && !this.noctilure.level().isEmptyBlock(this.noctilure.blockPosition().above(1))) {
            this.height = Math.min(-1.0F, this.height);
            this.selectNext();
        }
    }

    protected boolean touchingTarget() {
        return this.noctilure.moveTargetPoint.distanceToSqr(this.noctilure.getX(),
                this.noctilure.getY(), this.noctilure.getZ()) < 4.0D;
    }

    @Override
    public boolean canUse() {
        return noctilure.getIsFlying();
    }
}
