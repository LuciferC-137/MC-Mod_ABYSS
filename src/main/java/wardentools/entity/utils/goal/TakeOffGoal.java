package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class TakeOffGoal extends Goal {
    private final NoctilureEntity noctilure;

    public TakeOffGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        System.out.println("Taking off...");
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToTakeOff();
    }

    @Override
    public void tick() {
        this.directionOverride();
        if (this.noctilure.getHeightAboveGround() >= this.noctilure.getTargetHeightOnTakeOff()) {
            this.noctilure.setWantsToTakeOff(false);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getWantsToTakeOff();
    }

    private void directionOverride(){
        this.noctilure.setDeltaMovement(new Vec3(
                this.noctilure.getDeltaMovement().x(),
                NoctilureEntity.FLYING_SPEED,
                this.noctilure.getDeltaMovement().z()));
    }

    @Override
    public void stop() {
        this.noctilure.setDeltaMovement(Vec3.ZERO);
        super.stop();
        System.out.println("Take off complete.");
    }
}
