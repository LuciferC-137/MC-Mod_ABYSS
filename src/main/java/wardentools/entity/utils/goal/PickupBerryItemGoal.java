package wardentools.entity.utils.goal;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.custom.DeepLurkerEntity;
import wardentools.items.ItemRegistry;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class PickupBerryItemGoal extends Goal {
    private static final int SEARCH_RADIUS = 5;
    private static final int CHECK_INTERVAL = 10;
    private static final double PICKUP_DISTANCE = 1.0D;
    private static final double APPROACH_DISTANCE = 2.0D;

    private final DeepLurkerEntity entity;
    private final double speedModifier;
    private ItemEntity targetBerry;
    private int ticksSinceLastCheck;

    public PickupBerryItemGoal(DeepLurkerEntity entity, double speedModifier) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.ticksSinceLastCheck = CHECK_INTERVAL;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.entity.isScared()) {
            return false;
        }
        if (++ticksSinceLastCheck < CHECK_INTERVAL) {
            return this.targetBerry != null && this.targetBerry.isAlive();
        }

        ticksSinceLastCheck = 0;
        AABB searchBox = this.entity.getBoundingBox().inflate(SEARCH_RADIUS);
        List<ItemEntity> berries = this.entity.level().getEntitiesOfClass(
                ItemEntity.class,
                searchBox,
                item -> item.isAlive() &&
                        item.getItem().is(ItemRegistry.BLUE_GLOW_BERRIES.get())
        );

        if (berries.isEmpty()) {
            this.targetBerry = null;
            return false;
        }

        this.targetBerry = berries.stream()
                .min(Comparator.comparingDouble(item ->
                        item.distanceToSqr(this.entity)))
                .orElse(null);

        return this.targetBerry != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.entity.isScared()) {
            return false;
        }

        if (this.targetBerry == null || !this.targetBerry.isAlive()) {
            return false;
        }

        double distanceToBerry = this.entity.distanceTo(this.targetBerry);
        return distanceToBerry < SEARCH_RADIUS * 1.5;
    }

    @Override
    public void start() {
        if (this.targetBerry != null) {
            Vec3 berryPos = this.targetBerry.position();
            this.entity.getNavigation().moveTo(
                    berryPos.x, berryPos.y, berryPos.z,
                    this.speedModifier
            );
        }
    }

    @Override
    public void stop() {
        this.targetBerry = null;
        this.ticksSinceLastCheck = CHECK_INTERVAL;
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.targetBerry == null || !this.targetBerry.isAlive()) {
            return;
        }

        this.entity.getLookControl().setLookAt(this.targetBerry);

        double distanceToBerry = this.entity.distanceTo(this.targetBerry);

        if (distanceToBerry < PICKUP_DISTANCE) {
            this.pickupBerry();
            return;
        }

        if (distanceToBerry < APPROACH_DISTANCE) {
            Vec3 berryPos = this.targetBerry.position();
            this.entity.getNavigation().moveTo(
                    berryPos.x, berryPos.y, berryPos.z,
                    this.speedModifier * 0.6
            );
        } else if (this.entity.getNavigation().isDone()) {
            Vec3 berryPos = this.targetBerry.position();
            this.entity.getNavigation().moveTo(
                    berryPos.x, berryPos.y, berryPos.z,
                    this.speedModifier
            );
        }
    }

    private void pickupBerry() {
        if (this.targetBerry != null && this.targetBerry.isAlive()) {
            this.targetBerry.discard();

            this.entity.playSound(SoundEvents.ITEM_PICKUP, 0.2F,
                 ((this.entity.getRandom().nextFloat()
                         - this.entity.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);

            this.entity.heal(1F);

            this.targetBerry = null;
        }
    }
}
