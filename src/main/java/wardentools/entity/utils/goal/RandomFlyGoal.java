package wardentools.entity.utils.goal;

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
    private float circleWidness;

    public RandomFlyGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        this.distance = 300.0F + this.noctilure.getRandom().nextFloat() * 100.0F;
        this.height = (float)this.noctilure.getY();
        this.clockwise = this.noctilure.getRandom().nextBoolean() ? 1.0F : -1.0F;
        this.circleWidness =  0.01F + this.noctilure.getRandom().nextFloat() * 0.02F;
    }

    @Override
    public void tick() {
        this.angle += this.clockwise * this.circleWidness;
        double x = this.noctilure.getX() + this.distance * Mth.cos(this.angle);
        double z = this.noctilure.getZ() + this.distance * Mth.sin(this.angle);
        double y = this.height;
        this.noctilure.setDeltaMovement(new Vec3(x - this.noctilure.getX(),
                y - this.noctilure.getY(),
                z - this.noctilure.getZ()).normalize().scale(0.1));
        this.noctilure.setYRot((float)(Mth.atan2(z - this.noctilure.getZ(),
                x - this.noctilure.getX()) * (180F / Math.PI)) - 90.0F);
        this.noctilure.yBodyRot = this.noctilure.getYRot();

    }

    @Override
    public boolean canUse() {
        return noctilure.getIsFlying() && !this.noctilure.getWantsToTakeOff() && !this.noctilure.getWantsToLand();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
