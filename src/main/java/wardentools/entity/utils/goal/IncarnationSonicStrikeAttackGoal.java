package wardentools.entity.utils.goal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ContagionIncarnationEntity;
import wardentools.entity.custom.ParasyteEntity;
import wardentools.network.PayloadsRecords.ParticlesSounds.IncarnationEmergeSound;
import wardentools.network.PayloadsRecords.ParticlesSounds.IncarnationSonicStrikeSound;

import javax.annotation.Nullable;


public class IncarnationSonicStrikeAttackGoal extends Goal {
    private static final int SCREAM_SOUND_DELAY = 20;
    private static final int SONIC_SOUND_ADVANCE_TICK = 23;
    private static final int MAX_PARASYTES = 25;
    private static final float PARASYTE_RADIUS_CHECK = 30.0f;
    private static final int PARASYTES_TO_SPAWN = 10;
    private static final float PARASYTE_SPAWN_RADIUS = 5f;
    private static final int PLAYER_BLINDNESS_DURATION = 100;
    private static final float PLAYER_BLINDNESS_RADIUS = 30.0f;
    private final ContagionIncarnationEntity incarnation;
    @Nullable
    private LivingEntity targetCached;

    public IncarnationSonicStrikeAttackGoal(ContagionIncarnationEntity incarnation) {
        this.incarnation = incarnation;
    }

    @Override
    public void start() {
        this.incarnation.getNavigation().stop();
        this.targetCached = this.incarnation.getTarget();
        this.incarnation.setTarget(null);
        this.incarnation.setDeltaMovement(Vec3.ZERO);
    }

    private boolean cancelParasyteSummoning() {
        ServerLevel serverLevel = (ServerLevel) this.incarnation.level();
        long parasyteCount = serverLevel.getEntitiesOfClass(ParasyteEntity.class,
                        this.incarnation.getBoundingBox().inflate(PARASYTE_RADIUS_CHECK))
                .stream()
                .filter(entity -> entity.getType() == ModEntities.PARASYTE.get())
                .count();
        return (int)parasyteCount > MAX_PARASYTES;
    }

    @Override
    public void tick() {
        if (this.incarnation.level() instanceof ServerLevel serverLevel) {
            if (this.incarnation.getSonicStrikeTick()
                    == ContagionIncarnationEntity.SONIC_STRIKE_DURATION - SCREAM_SOUND_DELAY) {
                PacketDistributor.sendToPlayersTrackingChunk(
                        serverLevel,
                        serverLevel.getChunkAt(this.incarnation.blockPosition()).getPos(),
                        new IncarnationEmergeSound(this.incarnation.position().toVector3f()));
            }
            if (this.incarnation.getSonicStrikeTick()
                    == ContagionIncarnationEntity.SONIC_STRIKE_EFFECT_TICK + SONIC_SOUND_ADVANCE_TICK) {
                PacketDistributor.sendToPlayersTrackingChunk(
                        serverLevel,
                        serverLevel.getChunkAt(this.incarnation.blockPosition()).getPos(),
                        new IncarnationSonicStrikeSound(this.incarnation.position().toVector3f()));
            }
            if (this.incarnation.getSonicStrikeTick() == ContagionIncarnationEntity.SONIC_STRIKE_EFFECT_TICK) {
                applyBlindnessAndSummonParasyte();
            }
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    private void applyBlindnessAndSummonParasyte() {
        ServerLevel serverLevel = (ServerLevel) incarnation.level();
        AABB area = incarnation.getBoundingBox().inflate(PLAYER_BLINDNESS_RADIUS);
        for (Player player : serverLevel.getEntitiesOfClass(Player.class, area)) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, PLAYER_BLINDNESS_DURATION, 0));
        }
        if (!this.cancelParasyteSummoning()) {
            spawnParasytesAroundIncarnation(serverLevel, ModEntities.PARASYTE.get());
        }
    }

    private void spawnParasytesAroundIncarnation(ServerLevel level, EntityType<?> entityType) {
        for (int i = 0; i < PARASYTES_TO_SPAWN; i++) {
            double angle = i * (360.0 / PARASYTES_TO_SPAWN);
            double xOffset = Math.cos(Math.toRadians(angle)) * PARASYTE_SPAWN_RADIUS;
            double zOffset = Math.sin(Math.toRadians(angle)) * PARASYTE_SPAWN_RADIUS;
            var entity = entityType.create(level);
            if (entity != null) {
                entity.moveTo(incarnation.getX() + xOffset, incarnation.getY(),
                        incarnation.getZ() + zOffset, 0, 0);
                level.addFreshEntity(entity);
            }
        }
    }

    @Override
    public boolean canUse() {
        return this.incarnation.getSonicStrikeTick() > 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.incarnation.getSonicStrikeTick() > 0;
    }

    @Override
    public void stop() {
        this.incarnation.setTarget(this.targetCached);
    }
}
