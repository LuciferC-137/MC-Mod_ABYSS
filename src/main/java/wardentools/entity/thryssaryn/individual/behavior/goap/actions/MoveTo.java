package wardentools.entity.thryssaryn.individual.behavior.goap.actions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.goap.WSK;
import wardentools.entity.thryssaryn.individual.behavior.goap.WorldState;

import java.util.Comparator;
import java.util.List;

public class MoveTo extends GoapAction {

    // Target composed of sealed type: each variant holds its data
    public sealed interface MoveTarget permits MoveTarget.ToEntity,
            MoveTarget.ToNearestType, MoveTarget.ToItem,   MoveTarget.ToBlock {

        record ToEntity(LivingEntity entity) implements MoveTarget {}
        record ToNearestType(EntityType<? extends Mob> type) implements MoveTarget {}
        record ToItem(Item item) implements MoveTarget {}
        record ToBlock(BlockPos pos) implements MoveTarget {}
    }

    private final MoveTarget target;
    private final double speedModifier;
    private final float arrivalThreshold;

    // Monitoring no blocking state.
    private Vec3 lastPos = null;
    private int stuckTicks = 0;
    private static final int STUCK_THRESHOLD = 40;

    // Cache for solved target
    private LivingEntity resolvedEntity = null;
    private BlockPos resolvedPos = null;

    public MoveTo(ThryssarynEntity mob, MoveTarget target,
                  double speedModifier, float arrivalThreshold) {
        super(mob);
        this.target = target;
        this.speedModifier = speedModifier;
        this.arrivalThreshold = arrivalThreshold;
    }

    @Override
    public boolean checkPreconditions(WorldState ws) {
        // TODO: add blocking conditions to the movement
        return true;
    }

    @Override
    public @NotNull WorldState applyEffects(WorldState ws) {
        WorldState next = ws
                .with(WSK.IS_MOVING,false)
                .with(WSK.IS_STUCK,false)
                .with(WSK.IS_AT_TARGET,true)
                .with(WSK.DISTANCE_TO_TARGET,0f);

        // Depending on the target, we fill the required key
        switch (target) {
            case MoveTarget.ToEntity t -> next = next.with(WSK.TARGET_ENTITY, t.entity());
            case MoveTarget.ToNearestType t -> next = next.with(WSK.TARGET_ENTITY_TYPE, t.type());
            case MoveTarget.ToItem t -> next = next.with(WSK.TARGET_ITEM_TYPE, t.item());
            case MoveTarget.ToBlock t -> next = next.with(WSK.TARGET_POS, t.pos());
        }

        return next.with(WSK.HAS_TARGET, true)
                .with(WSK.IS_PATH_REACHABLE, true); // Optimist at planning time
    }
    
    @Override
    public @NotNull Status execute(WorldState ws) {

        // Solving real target
        if (!resolveTarget()) {
            updateWS(ws, false, false, true);
            return Status.FAILURE; // Target not found
        }

        // Checking for arrival
        double dist = distanceToResolved();
        if (dist <= arrivalThreshold) {
            mob.getNavigation().stop();
            updateWS(ws, false, true, false);
            return Status.SUCCESS;
        }

        // Check for stuck
        Vec3 pos = mob.position();
        if (lastPos != null && pos.distanceTo(lastPos) < 0.05) {
            stuckTicks++;
        } else {
            stuckTicks = 0;
        }
        lastPos = pos;

        if (stuckTicks >= STUCK_THRESHOLD) {
            mob.getNavigation().stop();
            updateWS(ws, false, false, true);
            return Status.FAILURE;
        }

        // Start / Maintain navigation
        boolean pathStarted = navigateTo();
        if (!pathStarted) {
            updateWS(ws, false, false, true);
            return Status.FAILURE; // No path found
        }

        updateWS(ws, true, false, false);
        ws.set(WSK.DISTANCE_TO_TARGET, (float) dist);
        return Status.RUNNING;
    }

    /**
     * Solve the abstract target into entity or real position.
     * Return false if no valid target has been found.
     */
    private boolean resolveTarget() {
        switch (target) {

            case MoveTarget.ToEntity t -> {
                if (!t.entity().isAlive()) return false;
                resolvedEntity = t.entity();
                resolvedPos    = null;
            }

            case MoveTarget.ToNearestType t -> {
                // Searching for closest entity
                LivingEntity nearest = mob.level()
                        .getNearestEntity(
                                mob.level().getEntitiesOfClass(
                                        Mob.class,
                                        mob.getBoundingBox().inflate(64),
                                        e -> e.getType() == t.type() && e != mob
                                ),
                                TargetingConditions.forNonCombat(),
                                mob, mob.getX(), mob.getY(), mob.getZ()
                        );
                if (nearest == null) return false;
                resolvedEntity = nearest;
                resolvedPos    = null;
            }

            case MoveTarget.ToItem t -> {
                // Look for closest item
                List<ItemEntity> items = mob.level().getEntitiesOfClass(
                        ItemEntity.class,
                        mob.getBoundingBox().inflate(32),
                        ie -> ie.getItem().is(t.item())
                );
                if (items.isEmpty()) return false;
                // Distance sorting
                items.sort(Comparator.comparingDouble(ie -> ie.distanceToSqr(mob)));
                resolvedEntity = null;
                resolvedPos    = items.getFirst().blockPosition();
            }

            case MoveTarget.ToBlock t -> {
                resolvedEntity = null;
                resolvedPos    = t.pos();
            }
        }
        return true;
    }

    /** Start navigation toward target */
    private boolean navigateTo() {
        if (resolvedEntity != null) {
            return mob.getNavigation().moveTo(resolvedEntity, speedModifier);
        }
        if (resolvedPos != null) {
            return mob.getNavigation().moveTo(
                    resolvedPos.getX() + 0.5,
                    resolvedPos.getY(),
                    resolvedPos.getZ() + 0.5,
                    speedModifier);
        }
        return false;
    }

    /** Distance to target */
    private double distanceToResolved() {
        if (resolvedEntity != null) return mob.distanceTo(resolvedEntity);
        if (resolvedPos    != null) return mob.position()
                .distanceTo(Vec3.atCenterOf(resolvedPos));
        return Double.MAX_VALUE;
    }

    /** Update WorldState with the current navigation state. */
    private void updateWS(WorldState ws, boolean moving, boolean atTarget, boolean stuck) {
        ws.set(WSK.IS_MOVING, moving);
        ws.set(WSK.IS_AT_TARGET, atTarget);
        ws.set(WSK.IS_STUCK, stuck);
        ws.set(WSK.HAS_TARGET, resolvedEntity != null || resolvedPos != null);
    }
}
