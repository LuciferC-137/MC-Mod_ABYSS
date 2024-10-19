package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class LandGoal extends Goal {
    private static final float heightToSuccessLanding = 3f;
    private static final int maxLandingRange = 100;
    private final NoctilureEntity noctilure;
    private Vec3 targetOnLanding;

    public LandGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        this.setLandingTargetOrCancel();
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToLand() && this.noctilure.getIsFlying();
    }

    @Override
    public void tick() {
        if (this.noctilure.getHeightAboveGround() <= heightToSuccessLanding){
            this.noctilure.land();
        }
        if (this.targetOnLanding == null){ // Reset if the game has been reloaded
            this.setLandingTargetOrCancel();
        }
    }

    private void setLandingTargetOrCancel() {
        BlockPos landingTarget = findValidGround();
        if (landingTarget != null) {
            this.targetOnLanding = landingTarget.getCenter().add(0, -0.5, 0);
            if (!this.noctilure.getNavigation().moveTo(targetOnLanding.x, targetOnLanding.y,
                    targetOnLanding.z, NoctilureEntity.FLYING_SPEED)){
                this.noctilure.setWantsToLand(false);
            }
        } else {
            this.noctilure.setWantsToLand(false);
        }
    }

    private BlockPos findValidGround() {
        BlockPos entityPos = this.noctilure.blockPosition();
        int i = 0;
        while (i<maxLandingRange
                && this.noctilure.level().getBlockState(entityPos.offset(0, -i, 0)).isAir()){
            i += 1;
        }
        return i == maxLandingRange ? null : entityPos.offset(0, -i, 0);
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getWantsToLand();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
