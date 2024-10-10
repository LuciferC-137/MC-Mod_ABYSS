package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class LandGoal extends Goal {
    private static final float heightToSuccessLanding = 2.5f;
    private static final int maxLandingRange = 100;
    private final NoctilureEntity noctilure;

    public LandGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        System.out.println("Beginning landing...");
        this.setLandingTargetOrCancel();
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToLand() && this.noctilure.getIsFlying();
    }

    @Override
    public void tick() {
        if (this.noctilure.getHeightAboveGround() <= heightToSuccessLanding){
            this.finalizeLanding();
        }
    }

    private void setLandingTargetOrCancel() {
        BlockPos landingTarget = findValidGround();
        if (landingTarget != null) {
            this.noctilure.moveTargetPoint = new Vec3(landingTarget.getX(),
                    landingTarget.getY(), landingTarget.getZ());
            System.out.println("landing point set to :" + landingTarget.toShortString());
        } else {
            this.noctilure.setWantsToLand(false);
            System.out.println("No valid ground found, landing canceled.");
        }
    }

    private BlockPos findValidGround() {
        int directionX = this.noctilure.getRandom().nextInt(-1, 1);
        int directionZ = this.noctilure.getRandom().nextInt(-1, 1);
        BlockPos entityPos = this.noctilure.blockPosition();
        int i = 0;
        while (i<maxLandingRange
                && this.noctilure.level().getBlockState(entityPos.offset(0, -i, 0)).isAir()){
            i += 1;
        }
        return i == maxLandingRange ? null : entityPos.offset(0, -i, 0);
    }

    private void finalizeLanding() {
        this.noctilure.land();
        System.out.println("Landing complete.");
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getWantsToLand();
    }

    @Override
    public void stop() {
        super.stop();
        this.noctilure.moveTargetPoint = null;
    }
}
