package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class LandGoal extends Goal {
    private final static double maxDescendSpeed = 2.;
    private final NoctilureEntity noctilure;

    public LandGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        System.out.println("Beginning landing...");
        this.noctilure.setDeltaMovement(this.noctilure.getDeltaMovement()
                .add(0., -0.5, 0.));
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToLand() && this.noctilure.getIsFlying();
    }

    @Override
    public void tick() {
        if (this.noctilure.getDeltaMovement().y() > -maxDescendSpeed){
            directionOverride();
        }
    }

    private void directionOverride(){
        this.noctilure.setDeltaMovement(new Vec3(
                this.noctilure.getDeltaMovement().x(),
                -0.5,
                this.noctilure.getDeltaMovement().z()));
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getHeightAboveGround() > 1.5f; // WARNING depends on the size of the entity
    }

    @Override
    public void stop() {
        this.noctilure.land();
        super.stop();
        System.out.println("Landing complete.");
    }
}
