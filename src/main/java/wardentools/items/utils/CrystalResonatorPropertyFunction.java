package wardentools.items.utils;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class CrystalResonatorPropertyFunction implements ClampedItemPropertyFunction {
    public static final int ROTATION_NONE = -1;
    private final CompassWobble wobble = new CompassWobble();
    @Nullable public final CompassTarget compassTarget;

    public CrystalResonatorPropertyFunction(@Nullable CompassTarget compassTarget) {
        this.compassTarget = compassTarget;
    }

    @Override
    @SuppressWarnings("deprecation")
    public float call(@NotNull ItemStack stack, @Nullable ClientLevel level,
                      @Nullable LivingEntity entity, int tick) {
        return unclampedCall(stack, level, entity, tick);
    }

    public float unclampedCall(@NotNull ItemStack stack, @Nullable ClientLevel level,
                               @Nullable LivingEntity entity, int tick) {
        Entity stackEntity = entity != null ? entity : stack.getEntityRepresentation();
        if (stackEntity == null) {
            return ROTATION_NONE;
        } else {
            level = this.tryFetchLevelIfMissing(stackEntity, level);
            return level == null ? ROTATION_NONE : this.getCompassRotation(stack, level, stackEntity);
        }
    }

    private float getCompassRotation(ItemStack stack, ClientLevel level, Entity entity) {
        if (this.compassTarget == null) {
            return ROTATION_NONE;
        }
        GlobalPos pos = this.compassTarget.getPos(level, stack, entity);
        long time = level.getGameTime();
        return !this.isValidCompassTargetPos(entity, pos) ? ROTATION_NONE :
                this.getRotationTowardsCompassTarget(entity, time, pos.pos());
    }

    private float getRotationTowardsCompassTarget(Entity entity, long partialTick, BlockPos pos) {
        double angleFromEntityToPos = this.getAngleFromEntityToPos(entity, pos);
        double visualRotationY = this.getWrappedVisualRotationY(entity);
        double finalRotation;
        if (entity instanceof Player player) {
            if (player.isLocalPlayer() && player.level().tickRateManager().runsNormally()) {
                if (this.wobble.shouldUpdate(partialTick)) {
                    this.wobble.update(partialTick, 0.5 - (visualRotationY - 0.25));
                }

                finalRotation = angleFromEntityToPos + this.wobble.rotation;
                return Mth.positiveModulo((float)finalRotation, 1.0F);
            }
        }

        finalRotation = 0.5 - (visualRotationY - 0.25 - angleFromEntityToPos);
        return Mth.positiveModulo((float)finalRotation, 1.0F);
    }

    @Nullable
    private ClientLevel tryFetchLevelIfMissing(Entity entity, @Nullable ClientLevel level) {
        return level == null && entity.level() instanceof ClientLevel ?
                (ClientLevel)entity.level() : level;
    }

    private boolean isValidCompassTargetPos(Entity entity, @Nullable GlobalPos pos) {
        return pos != null && pos.pos() != BlockPos.ZERO && pos.dimension() == entity.level().dimension()
                && !(pos.pos().distToCenterSqr(entity.position()) < 9.999999747378752E-6);
    }

    private double getAngleFromEntityToPos(Entity entity, BlockPos pos) {
        Vec3 center = Vec3.atCenterOf(pos);
        return Math.atan2(center.z() - entity.getZ(), center.x() - entity.getX()) / 6.2831854820251465;
    }

    private double getWrappedVisualRotationY(Entity entity) {
        return Mth.positiveModulo(entity.getVisualRotationYInDegrees() / 360.0F, 1.0);
    }

    @OnlyIn(Dist.CLIENT)
    static class CompassWobble {
        double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        CompassWobble() {
        }

        boolean shouldUpdate(long partialTick) {
            return this.lastUpdateTick != partialTick;
        }

        void update(long partialTick, double prevRot) {
            this.lastUpdateTick = partialTick;
            double deltaRot = prevRot - this.rotation;
            deltaRot = Mth.positiveModulo(deltaRot + 0.5, 1.0) - 0.5;
            this.deltaRotation += deltaRot * 0.1;
            this.deltaRotation *= 0.8;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public interface CompassTarget {
        @Nullable
        GlobalPos getPos(ClientLevel level, ItemStack stack, Entity entity);
    }
}
