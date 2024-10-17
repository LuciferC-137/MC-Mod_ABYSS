package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class TakeOffGoal extends Goal {
    private final NoctilureEntity noctilure;
    private Vec3 targetOnTakeOff;

    public TakeOffGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        targetOnTakeOff = new Vec3(this.noctilure.getX(),
                this.noctilure.getTargetHeightOnTakeOff() + this.noctilure.getY(),
                this.noctilure.getZ());
        this.noctilure.getNavigation().moveTo(targetOnTakeOff.x, targetOnTakeOff.y,
                targetOnTakeOff.z, NoctilureEntity.FLYING_SPEED);
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToTakeOff();
    }

    @Override
    public void tick() {
        if (this.noctilure.getNavigation().isDone()){
            this.noctilure.setWantsToTakeOff(false);
        }
        if (this.targetOnTakeOff == null){ // Reset if the game has been reloaded
            this.start();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getWantsToTakeOff();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
