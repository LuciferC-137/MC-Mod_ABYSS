package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.entity.custom.ParasyteEntity;

import java.util.EnumSet;

public class ClimbParasyteGoal extends Goal {
    private final ParasyteEntity entity;

    public ClimbParasyteGoal(ParasyteEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public void start() {
        this.entity.setClimbing(true);
    }

    @Override
    public void stop() {
        this.entity.setClimbing(false);
    }

    @Override
    public boolean canUse() {
        return this.entity.horizontalCollision &&
                this.entity.level().getBlockState(this.entity.blockPosition().above()).isAir();
    }

    @Override
    public void tick() {
        if (this.entity.horizontalCollision) {
            this.entity.setDeltaMovement(this.entity.getDeltaMovement().multiply(1.0D, 0.2D, 1.0D));
            if (this.entity.level().getBlockState(this.entity.blockPosition().above()).isAir()) {
                this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.2D, 0.0D));
            }
        }
    }

}
