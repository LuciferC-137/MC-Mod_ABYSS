package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import wardentools.block.BlockRegistry;
import wardentools.block.BlueBush;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.items.ItemRegistry;
import wardentools.utils.LevelGetterUtils;

import java.util.EnumSet;

public class PickBerryGoal extends Goal {
    private static final int UPDATE_INTERVAL = 20;
    private static final LevelGetterUtils.PredicateFunction BERRY_STATE_PREDICATE =
            (level, pos) -> level.getBlockState(pos).is(BlockRegistry.BLUE_BUSH.get())
                    && level.getBlockState(pos).getValue(BlueBush.BERRY_STATE) == BlueBush.BerryState.BLUE_BERRY;
    private static final int BERRY_PICK_DURATION = 50;
    private static final double PICKING_DISTANCE_SQ = 1.5;

    private final DeepLurkerEntity entity;
    private final double speedModifier;

    private @Nullable BlockPos bushPos;
    private int searchBushTick = UPDATE_INTERVAL;
    private int pickBerryTick = 0;

    public PickBerryGoal(DeepLurkerEntity entity, double speedModifier) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.entity.isScared()) {
            return false;
        }

        if (--searchBushTick <= 0) {
            searchBushTick = UPDATE_INTERVAL;
            BlockPos pos = this.closestBlueBush();
            if (pos != null) {
                this.bushPos = pos;
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (this.bushPos == null) {
            return;
        }
        this.pickBerryTick = 0;
        Vec3 bushVec = Vec3.atCenterOf(this.bushPos);
        this.entity.getNavigation().moveTo(bushVec.x, bushVec.y, bushVec.z, this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.entity.isScared()) {
            return false;
        }

        return this.bushPos != null
                && BERRY_STATE_PREDICATE.test(this.entity.level(), this.bushPos);
    }

    @Override
    public void stop() {
        this.bushPos = null;
        this.pickBerryTick = 0;
        this.searchBushTick = UPDATE_INTERVAL;
        this.entity.setPickingBerries(false);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.bushPos == null) {
            this.stop();
            return;
        }

        this.entity.getLookControl().setLookAt(Vec3.atCenterOf(this.bushPos));

        double distanceSq = this.entity.distanceToSqr(Vec3.atCenterOf(this.bushPos));

        if (distanceSq < PICKING_DISTANCE_SQ) {
            this.entity.getNavigation().stop();

            if (this.pickBerryTick > 0) {
                if (this.pickBerryTick %10 == 0) {
                    this.entity.playSound(SoundEvents.GRASS_STEP, 1.0F, 1.0F);
                }
                this.pickBerryTick--;
                this.entity.setPickingBerries(true);
                this.entity.setClimbing(false);

                if (this.pickBerryTick == 0) {
                    this.entity.level().setBlock(this.bushPos,
                            this.entity.level().getBlockState(this.bushPos)
                                    .setValue(BlueBush.BERRY_STATE, BlueBush.BerryState.NONE), 3);
                    this.entity.spawnAtLocation(ItemRegistry.BLUE_GLOW_BERRIES.get(), 1);
                    this.entity.setPickingBerries(false);
                    this.stop();
                }
            } else {
                this.pickBerryTick = BERRY_PICK_DURATION;
                this.entity.playSound(SoundEvents.GRASS_STEP, 1.0F, 1.0F);
            }
        } else {
            if (this.entity.getNavigation().isDone()) {
                Vec3 bushVec = Vec3.atCenterOf(this.bushPos);
                this.entity.getNavigation().moveTo(bushVec.x, bushVec.y, bushVec.z, this.speedModifier);
            }
        }
    }

    private @Nullable BlockPos closestBlueBush() {
        return LevelGetterUtils.findClosest(this.entity.position(), this.entity.level(),
                BERRY_STATE_PREDICATE, 10, 2);
    }
}