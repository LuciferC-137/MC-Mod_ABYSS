package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.CrystalGolemEntity;

public class LightCandleGoal extends Goal {
    private final CrystalGolemEntity golem;
    public Phase phase = Phase.TRAVEL;
    private static final int LIGHT_PHASE_DURATION = 40;
    private static final int TIME_TO_LIGHT = 30;
    private static final double SPEED = 1D;
    private static final int POS_ACCURACY = 0;
    private int lightTick = 0;

    private static final int TIMEOUT = 2000;
    private int timeoutTick = 0;

    public LightCandleGoal(CrystalGolemEntity golem) {
        this.golem = golem;
    }

    @Override
    public void start() {
        this.timeoutTick = 0;
        this.changePhaseTo(Phase.TRAVEL);
        BlockPos targetPos = golem.peekUnlitCandle();
        Vec3 target = targetPos.getCenter();
        if (!golem.getNavigation().moveTo(target.x, target.y, target.z, POS_ACCURACY, SPEED)) {
            golem.pollUnlitCandle();
            this.stop();
        }
    }

    public static boolean wouldLikeToStart(CrystalGolemEntity golem) {
        return !golem.getUnlitCandles().isEmpty();
    }

    @Override
    public boolean canUse() {
        return wouldLikeToStart(this.golem) && golem.isActive();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() || this.phase == Phase.COMEBACK;
    }

    @Override
    public void tick() {
        this.timeoutTick ++;
        if (this.timeoutTick > TIMEOUT) {
            this.stop();
        }
        if (this.phase == Phase.TRAVEL && golem.getNavigation().isDone()) {
            BlockPos targetPos = golem.peekUnlitCandle();
            if (targetPos != BlockPos.ZERO) {
                BlockState state = golem.level().getBlockState(targetPos);
                if (state.is(BlockTags.CANDLES)
                        && state.hasProperty(CandleBlock.LIT)
                        && !state.getValue(CandleBlock.LIT)) {
                    this.changePhaseTo(Phase.LIGHT);
                } else {
                    golem.pollUnlitCandle();
                    if (golem.getUnlitCandles().isEmpty()) {
                        this.changePhaseTo(Phase.COMEBACK);
                        Vec3 target = this.golem.getRestPos().getCenter();
                        golem.getNavigation().moveTo(target.x, target.y, target.z, POS_ACCURACY, SPEED);
                    }
                }
            } else {
                this.changePhaseTo(Phase.COMEBACK);
                Vec3 target = this.golem.getRestPos().getCenter();
                golem.getNavigation().moveTo(target.x, target.y, target.z, POS_ACCURACY, SPEED);
            }
        }
        if (this.phase == Phase.LIGHT) {
            this.lightTick++;
            if (this.lightTick == TIME_TO_LIGHT) {
                this.golem.lightCandleAtPos();
            }
            if (this.lightTick > LIGHT_PHASE_DURATION) {
                this.golem.pollUnlitCandle();
                this.lightTick = 0;
                if (!golem.getUnlitCandles().isEmpty()) {
                    this.changePhaseTo(Phase.TRAVEL);
                    BlockPos next = golem.peekUnlitCandle();
                    Vec3 target = next.getCenter();
                    golem.getNavigation().moveTo(target.x, target.y, target.z, POS_ACCURACY, SPEED);
                } else {
                    this.changePhaseTo(Phase.COMEBACK);
                    Vec3 target = this.golem.getRestPos().getCenter();
                    golem.getNavigation().moveTo(target.x, target.y, target.z, POS_ACCURACY, SPEED);
                }
            }
        }
        if (this.phase == Phase.COMEBACK) {
            if (this.golem.getNavigation().isDone()) {
                this.stop();
            }
        }
    }

    @Override
    public void stop() {
        this.lightTick = 0;
        this.timeoutTick = 0;
        this.phase = Phase.TRAVEL;
        this.golem.getUnlitCandles().clear(); // For more safety
        this.golem.setState(CrystalGolemEntity.GolemState.DEACTIVATED_2);
    }

    private void changePhaseTo(Phase phase) {
        this.phase = phase;
        this.phase.setAssociatedGolemState(this.golem);
    }

    public enum Phase {
        TRAVEL(CrystalGolemEntity.GolemState.TRAVELING),
        LIGHT(CrystalGolemEntity.GolemState.LIGHT),
        COMEBACK(CrystalGolemEntity.GolemState.TRAVELING);

        private final CrystalGolemEntity.GolemState state;

        Phase(CrystalGolemEntity.GolemState associatedState) {
            this.state = associatedState;
        }

        public void setAssociatedGolemState(CrystalGolemEntity golem) {
            golem.setState(this.state);
        }
    }
}
    
