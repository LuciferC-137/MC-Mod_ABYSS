package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class TakeOffGoal extends Goal {
    private static final double maxAscendSpeed = 2.;
    private final NoctilureEntity noctilure;

    public TakeOffGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        System.out.println("Taking off...");
        this.noctilure.setDeltaMovement(this.noctilure.getDeltaMovement().add(0, 0.5, 0));
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getIsFlying() && this.noctilure.getWantsToTakeOff();
    }

    @Override
    public void tick() {
        if (this.noctilure.getDeltaMovement().y() < maxAscendSpeed) {
            this.directionOverride();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getIsFlying()
                && this.noctilure.getHeightAboveGround() < this.noctilure.getTargetHeightOnTakeOff();
    }

    private void directionOverride(){
        this.noctilure.setDeltaMovement(new Vec3(
                this.noctilure.getDeltaMovement().x(),
                0.5,
                this.noctilure.getDeltaMovement().z()));
    }

    @Override
    public void stop() {
        this.noctilure.setWantsToTakeOff(false);
        super.stop();
        System.out.println("Take off complete.");
    }
}
