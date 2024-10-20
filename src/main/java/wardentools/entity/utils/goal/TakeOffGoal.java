package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.NoctilureEntity;

public class TakeOffGoal extends Goal {
    private static final int maxTickCount = 2000;
    private int tickCount = 0;
    private final NoctilureEntity noctilure;
    private Vec3 targetOnTakeOff;

    public TakeOffGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        this.tickCount = 0;
        this.setTakeOffTargetOrCancel();
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToTakeOff();
    }

    @Override
    public void tick() {
        this.tickCount += 1;
        if (this.noctilure.getNavigation().isDone()){
            this.noctilure.setWantsToTakeOff(false);
        }
        if (this.targetOnTakeOff == null){ // Reset if the game has been reloaded
            this.start();
        }
    }

    private void setTakeOffTargetOrCancel() {
        this.targetOnTakeOff = findValidTakeOffPosition();
        if (this.targetOnTakeOff == null){
            this.noctilure.setWantsToTakeOff(false);
            return;
        }
        if (!this.noctilure.getNavigation().moveTo(targetOnTakeOff.x, targetOnTakeOff.y,
                targetOnTakeOff.z, NoctilureEntity.FLYING_SPEED)){
            this.noctilure.setWantsToTakeOff(false);
        }
    }

    private Vec3 findValidTakeOffPosition() {
        BlockPos entityPos = this.noctilure.blockPosition();
        int maxRange = 10; // Define the range within which to find a valid position
        int minRange = 5; // Define the minimum range to find a valid position
        for (int dx = -maxRange; dx <= maxRange; dx++) {
            for (int dz = -maxRange; dz <= maxRange; dz++) {
                if (dx * dx + dz * dz < minRange * minRange) {
                    BlockPos checkPos = entityPos.offset(dx, 0, dz);
                    if (this.noctilure.level().getBlockState(checkPos).isAir()) {
                        return new Vec3(checkPos.getX(), this.noctilure.getTargetHeightOnTakeOff()
                                + this.noctilure.getY(), checkPos.getZ());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.noctilure.getWantsToTakeOff() && this.tickCount < maxTickCount;
    }

    @Override
    public void stop() {
        this.tickCount = 0;
        super.stop();
    }
}
