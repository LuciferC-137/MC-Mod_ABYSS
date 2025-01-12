package wardentools.entity.utils.goal;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import wardentools.entity.custom.NoctilureEntity;

import java.util.Objects;

public class JoinOwnerGoal extends Goal {
    private int tickCount = 0;
    private static final int maxTickCount = 1000;
    private final NoctilureEntity noctilure;
    private static final double MAX_DISTANCE_TO_MOVE = 40D;

    public JoinOwnerGoal(NoctilureEntity noctilure){
        this.noctilure = noctilure;
    }

    @Override
    public void start() {
        this.tickCount = 0;
        this.noctilure.resetRandomFlyingLogic();
        if (this.noctilure.getOwnerUUID() == null) {
            this.noctilure.setWantsToJoinOwner(false);
            return;
        }
    }

    @Override
    public boolean canUse() {
        return this.noctilure.getWantsToJoinOwner() && this.noctilure.getOwnerUUID() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.tickCount <= maxTickCount && super.canContinueToUse();
    }

    @Override
    public void tick() {
        if (this.noctilure.level().isClientSide) return;
        this.tickCount += 1;
        if (this.noctilure.getOwnerUUID() == null) {
            this.stop();
            return;
        }
        Player player = this.noctilure.level().getPlayerByUUID(this.noctilure.getOwnerUUID());
        if (player == null) {
            this.stop();
            return;
        }
        this.noctilure.getLookControl().setLookAt(player);
        if (this.noctilure.closerThan(player, 2f)) {
            this.stop();
        }
        if (this.tickCount % 20 == 0) this.setTargetOrCancel();
    }

    @Override
    public void stop() {
        this.tickCount = 0;
        this.landIfGroundCloseEnough();
        this.noctilure.setWantsToJoinOwner(false);
        super.stop();
    }

    private void setTargetOrCancel() {
        if (this.noctilure.getOwnerUUID() == null) {
            this.stop();
            return;
        }
        Player player = this.noctilure.level().getPlayerByUUID(this.noctilure.getOwnerUUID());
        if (player == null) {
            this.noctilure.setWantsToJoinOwner(false);
            return;
        }
        if (this.noctilure.closerThan(player, MAX_DISTANCE_TO_MOVE)) {
            this.noctilure.setIsFlying(true);
            this.noctilure.setNoGravity(true);
            this.noctilure.updateMovementLogic();
            if (!this.noctilure.getNavigation().moveTo(player, 1.0D)) {
                this.noctilure.teleportTo(player.getX(), player.getY(), player.getZ());
                this.stop();
            }
        } else {
            this.noctilure.teleportTo(player.getX(), player.getY(), player.getZ());
            this.stop();
        }
    }

    private void landIfGroundCloseEnough() {
        if (this.noctilure.getHeightAboveGround() < 2f) {
            this.noctilure.setWantsToLand(true);
        }
    }
}
