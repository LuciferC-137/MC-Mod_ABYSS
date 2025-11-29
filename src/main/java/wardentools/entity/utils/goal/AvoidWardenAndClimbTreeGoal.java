package wardentools.entity.utils.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.phys.Vec3;
import wardentools.block.BlockRegistry;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.tags.ModTags;
import wardentools.utils.LevelGetterUtils;

import java.util.List;

public class AvoidWardenAndClimbTreeGoal extends Goal {
    private static final int scaredRadius = 20;
    private static final int treeFindRadius = 25;

    private final DeepLurkerEntity entity;
    private final double speedModifier;
    private LivingEntity closestWarden;

    private BlockPos targetTreePos;
    private BlockPos targetBushPos;

    private Step currentStep = Step.RUNNING_TO_TREE;
    private int trialToFindTree = 0;
    private int trialToFindBush = 0;

    public AvoidWardenAndClimbTreeGoal(DeepLurkerEntity entity, double speedModifier) {
        this.entity = entity;
        this.speedModifier = speedModifier;
    }

    public boolean canUse() {
        List<Warden> wardens = entity.level().getEntitiesOfClass(Warden.class,
                entity.getBoundingBox().inflate(scaredRadius));

        if (!wardens.isEmpty()) {
            this.closestWarden = wardens.getFirst();
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        this.trialToFindTree = 0;
        this.trialToFindBush = 0;
        this.findClosestTree();
        if (this.targetTreePos == null) {
            this.setCurrentStep(Step.RUNNING_TO_BUSH);
            this.findClosestBush();
            if (this.targetBushPos == null) {
                this.setCurrentStep(Step.HIDE);
            }
        } else {
            this.setCurrentStep(Step.RUNNING_TO_TREE);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.closestWarden != null && this.closestWarden.isAlive() &&
                this.entity.distanceTo(this.closestWarden) < (float)scaredRadius * 2F;
    }

    @Override
    public void stop() {
        this.closestWarden = null;
        this.targetTreePos = null;
        this.trialToFindTree = 0;
        this.setCurrentStep(Step.RUNNING_TO_TREE);
        this.entity.setClimbing(false);
        this.entity.setNoGravity(false);
        this.entity.setScared(false);
        this.entity.setIsAtTopOfTree(false);
        this.targetBushPos = null;
        this.trialToFindBush = 0;
    }

    @Override
    public void tick() {
        if (this.currentStep == Step.RUNNING_TO_TREE) {
            if (this.distanceToTargetTree() >= 30D) { // lost the tree
                this.findClosestTree();
                if (this.trialToFindTree > 3) { // give up and hide on bush
                    this.setCurrentStep(Step.RUNNING_TO_BUSH);
                    this.findClosestBush();
                }

            }
            if (this.entity.getNavigation().isDone() && this.distanceToTargetTree() > 3D) { // not yet at tree
                if (this.targetTreePos != null) {
                    Vec3 target = this.targetTreePos.getCenter();
                    this.entity.getNavigation().moveTo(target.x(), target.y(), target.z(), this.speedModifier);
                } else {
                    this.setCurrentStep(Step.RUNNING_TO_BUSH);
                    this.findClosestBush();
                }
            }
        }

        if (this.currentStep == Step.RUNNING_TO_BUSH) {
            if (this.distanceToTargetBush() >= 30D) {
                this.findClosestBush();
                if (this.trialToFindBush > 3) { // give up and hide on spot
                    this.setCurrentStep(Step.HIDE);
                }
            }
            if (this.entity.getNavigation().isDone() && this.distanceToTargetBush() > 3D) { // not yet at bush
                if (this.targetBushPos == null) {
                    this.findClosestBush();
                }
                if (this.targetBushPos != null) {
                    Vec3 target = this.targetBushPos.getCenter();
                    this.entity.getNavigation().moveTo(target.x(), target.y(), target.z(), this.speedModifier);
                } else {
                    this.setCurrentStep(Step.HIDE);
                }
            }
        }

        if (this.currentStep == Step.RUNNING_TO_TREE && this.distanceToTargetTree() < 1D) {
            this.setCurrentStep(Step.CLIMBING_TREE);
        }

        if (this.currentStep == Step.RUNNING_TO_BUSH && this.distanceToTargetBush() < 1D) {
            this.setCurrentStep(Step.HIDE);
            if (this.targetBushPos != null) {
                this.entity.setPos(this.targetBushPos.getCenter());
            }
        }

        if (this.closestWarden != null && this.closestWarden.closerThan(this.entity, 2D)) {
            this.setCurrentStep(Step.HIDE);
        }

        if (this.currentStep == Step.CLIMBING_TREE) {
            this.makeEntityFaceTree();
            this.entity.setClimbing(true);
            this.entity.setDeltaMovement(this.entity.getDeltaMovement()
                    .add(0.0D, 0.2D, 0.0D));
            if (this.entity.conditionToBaAtTopOfTree()) {
                this.setCurrentStep(Step.HIDE);
            } else if (this.noMoreTrunkAround()) {
                // Failed to climb (no foliage or weird trunk) fall back to bush hiding
                this.entity.setClimbing(false);
                this.entity.setNoGravity(false);
                this.setCurrentStep(Step.RUNNING_TO_BUSH);
                this.findClosestBush();
            }
        }

        if (this.currentStep == Step.HIDE) {
            this.entity.getNavigation().stop();
            this.entity.setIsAtTopOfTree(this.entity.conditionToBaAtTopOfTree());
            this.entity.getLookControl().setLookAt(this.closestWarden);
            this.entity.setScared(true);
        }
    }

    private boolean noMoreTrunkAround() {
        BlockPos entityPos = this.entity.blockPosition();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos checkPos = entityPos.offset(dx, 0, dz);
                if (this.entity.level().getBlockState(checkPos).is(BlockRegistry.DARKTREE_LOG.get())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void makeEntityFaceTree() {
        if (this.targetTreePos != null) {
            this.entity.setYBodyRot((float) Math.toDegrees(Math.atan2(
                            this.targetTreePos.getZ() - this.entity.getZ(),
                            this.targetTreePos.getX() - this.entity.getX())) - 90
            );
        }
    }

    private double distanceToTargetTree() {
        if (this.targetTreePos != null) {
            return this.entity.position().distanceTo(this.targetTreePos.getCenter());
        }
        return Double.MAX_VALUE;
    }

    private double distanceToTargetBush() {
        if (this.targetBushPos != null) {
            return this.entity.position().distanceTo(this.targetBushPos.getCenter());
        }
        return Double.MAX_VALUE;
    }

    private void findClosestTree() {
        this.trialToFindTree++;
        BlockPos closestTree = LevelGetterUtils.findClosest(this.entity.position(), this.entity.level(),
                BlockRegistry.DARKTREE_LOG.get(), treeFindRadius);
        if (closestTree != null) {
            this.targetTreePos = closestTree;
            this.findLowerTrunkBlock();
        }
    }

    private void findClosestBush() {
        this.trialToFindBush++;
        BlockPos closestBush = LevelGetterUtils.findClosest(this.entity.position(), this.entity.level(),
                ModTags.Blocks.DARK_VEGETATION, 5, 5);
        if (closestBush != null) {
            this.targetBushPos = closestBush;
        }
    }

    private void findLowerTrunkBlock() { // return bottom darktree log block
        while (this.entity.level().getBlockState(targetTreePos.below()).is(BlockRegistry.DARKTREE_LOG.get())) {
                targetTreePos = targetTreePos.below();
        }
    }

    private Step getCurrentStep() {
        return Step.fromId(this.entity.getAvoidWardenStep());
    }

    private void setCurrentStep(Step step) {
        this.currentStep = step;
        this.entity.setAvoidWardenStep(step.getId());
    }

    private enum Step {
        RUNNING_TO_TREE(0),
        RUNNING_TO_BUSH(1),
        CLIMBING_TREE(2),
        HIDE(3);

        private final int id;

        Step(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Step fromId(int id) {
            for (Step step : values()) {
                if (step.id == id) {
                    return step;
                }
            }
            return RUNNING_TO_TREE; // Défaut
        }
    }
}