package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.IncarnationBodyRotationControl;
import wardentools.sounds.ModSounds;

import java.lang.reflect.Field;
import java.util.Objects;

public class ContagionIncarnationEntity extends ContagionIncarnationPartManager implements Enemy {
	public static final double MOV_SPEED = 0.2D;
	private final ServerBossEvent bossEvent;
	public static final int CHANCE_OF_SCREAM_ON_HIT = 4;
	public static final int DEATH_DURATION = 200;
    public static final int SPAWN_DURATION = 150;
    public static final int MOVE_DELAY_DURING_SPAWN = 80;
    public static final float SPEED_DURING_SPAWN = 0.05F;
	public int contagionIncarnationDeathTime = 0;
	public final AnimationState dyingAnimationState = new AnimationState();
	public final AnimationState ambient = new AnimationState();
	public final AnimationState idleAmbient = new AnimationState();
	public final AnimationState sprint = new AnimationState();
	public final AnimationState headAmbient = new AnimationState();
    public final AnimationState spawnAnimation = new AnimationState();
    private static final EntityDataAccessor<Integer> tickSpawn
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.INT);
    private BlockPos catalystPos = new BlockPos(0, 0, 0);

	public ContagionIncarnationEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
		this.bossEvent = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()),
				ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        this.xpReward = 100;
        try {
            Field field = Mob.class.getDeclaredField("bodyRotationControl");
            field.setAccessible(true);
            field.set(this, new IncarnationBodyRotationControl(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field field = Entity.class.getDeclaredField("eyeHeight");
            field.setAccessible(true);
            field.set(this, 4.5F);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 2D));
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 10f));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	    this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1000D)
	            .add(Attributes.MOVEMENT_SPEED, MOV_SPEED)
	            .add(Attributes.JUMP_STRENGTH, 3.0D)
	            .add(Attributes.ATTACK_DAMAGE, 10.0D)
	            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0F);
	}

	@Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void remove(@NotNull RemovalReason reason) {
        super.remove(reason);
        this.bossEvent.removeAllPlayers();
    }
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }
	
	@Override
	public int getMaxHeadYRot() {
        return 80;
    }
	
	protected float getMaxHeadRotationRelativeToBody() {
	      return 120F;
    }
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.dyingAnimationState.animateWhen(this.contagionIncarnationDeathTime > 0, this.tickCount);
			this.idleAmbient.animateWhen(!this.isSprinting() && this.isActive(), this.tickCount);
			this.ambient.animateWhen(!this.walkAnimation.isMoving() && this.isActive(), this.tickCount);
			this.sprint.animateWhen(this.isSprinting() && this.isActive(), this.tickCount);
			//this.headAmbient.animateWhen(!this.getLookControl().isLookingAtTarget()
			//		&& !this.isSprinting() && this.isActive(), this.tickCount);
            this.spawnAnimation.animateWhen(this.getTickSpawn() > 0, this.tickCount);
		} else {
            this.handleSpawnLogic();
        }
		super.tick();
	}

    private void handleSpawnLogic() {
        if (this.getTickSpawn() > 0) {
            this.setInvulnerable(true);
            this.setTickSpawn(this.getTickSpawn() - 1);
            if (this.getTickSpawn() <= 0) this.setInvulnerable(false);
            this.handleTailSubParts();
            if (this.getTickSpawn() <= MOVE_DELAY_DURING_SPAWN
                    && this.getTickSpawn() >= MOVE_DELAY_DURING_SPAWN / 2) {
                this.setDeltaMovement(
                        this.getYRot()
                                * Mth.sin(this.getYRot() * (float)Math.PI / 180.0F)
                                * SPEED_DURING_SPAWN,
                        0, this.getYRot()
                                * Mth.cos(this.getYRot() * (float)Math.PI / 180.0F)
                                * SPEED_DURING_SPAWN);
            }
        }
    }

    @Override
    public void aiStep() {
        if (this.getTickSpawn() <= MOVE_DELAY_DURING_SPAWN) {
            super.aiStep();
        }
    }

    public void initiateSpawnAnimation() {
        this.setTickSpawn(SPAWN_DURATION);
    }

    public boolean isActive() {
        return this.getTickSpawn() <= 0 && !this.isDeadOrDying();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(tickSpawn, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("tickSpawn", this.getTickSpawn());
        tag.putInt("catalystX", this.catalystPos.getX());
        tag.putInt("catalystY", this.catalystPos.getY());
        tag.putInt("catalystZ", this.catalystPos.getZ());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setTickSpawn(tag.getInt("tickSpawn"));
        this.catalystPos = new BlockPos(tag.getInt("catalystX"),
                tag.getInt("catalystY"), tag.getInt("catalystZ"));
    }

    public void setCatalystPos(BlockPos pos) {this.catalystPos = pos;}

    public BlockPos getCatalystPos() {return this.catalystPos;}

    public void setTickSpawn(int tickSpawn) {this.entityData.set(ContagionIncarnationEntity.tickSpawn, tickSpawn);}

    public int getTickSpawn() {return this.entityData.get(ContagionIncarnationEntity.tickSpawn);}

    public static boolean canSpawn(EntityType<ContagionIncarnationEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    	return true;
    }


    @Override
	public boolean checkSpawnRules(@NotNull LevelAccessor p_21686_, @NotNull MobSpawnType p_21687_) {
    	return true;
    }
	
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.CONTAGION_INCARNATION_AMBIENT.get();
    }

	@Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
    	if (level().random.nextInt(CHANCE_OF_SCREAM_ON_HIT)==0) {
    		return ModSounds.CONTAGION_INCARNATION_SCREAM.get();
    	}
    	return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
    	 return ModSounds.CONTAGION_INCARNATION_DEATH.get();
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(ModSounds.CONTAGION_INCARNATION_CRAWL.get(), 1.0F, 1.0F);
    }
    
    @Override
    protected float getSoundVolume() {
        return 2.0F;
     }
    
    @Override
    protected void tickDeath() {
        ++this.contagionIncarnationDeathTime;
        if (this.contagionIncarnationDeathTime >= DEATH_DURATION
        		&& !this.level().isClientSide() && !this.isRemoved()) {
           this.level().broadcastEntityEvent(this, (byte)60);
           this.remove(Entity.RemovalReason.KILLED);
           this.createCorpse();
           this.informCatalystOfDeath();
        }
     }

	@Override
    public void die(@NotNull DamageSource source) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, source)) return;
        if (!this.isRemoved() && !this.dead) {
           Entity entity = source.getEntity();
           LivingEntity livingentity = this.getKillCredit();
           if (this.deathScore >= 0 && livingentity != null) {
              livingentity.awardKillScore(this, this.deathScore, source);
           }
           if (this.isSleeping()) this.stopSleeping();
           this.dead = true;
           this.getCombatTracker().recheckStatus();
           Level level = this.level();
           if (level instanceof ServerLevel serverlevel) {
               if (entity == null || entity.killedEntity(serverlevel, this)) {
                 this.gameEvent(GameEvent.ENTITY_DIE);
                 this.dropAllDeathLoot(source);
                 this.createWitherRose(livingentity);
               }
               this.level().broadcastEntityEvent(this, (byte)3);
           }
        }
    }

    private void createCorpse() {
        ContagionIncarnationCorpseEntity corpse
                = new ContagionIncarnationCorpseEntity(
                        ModEntities.CONTAGION_INCARNATION_CORPSE.get(), this.level());
        corpse.setPos(this.getX(), this.getY(), this.getZ());
        corpse.setYRot(this.getYRot());
        corpse.setYHeadRot(this.getYHeadRot());
        corpse.setYBodyRot(this.yBodyRot);
        corpse.setHealth(corpse.getMaxHealth());
        this.level().addFreshEntity(corpse);
    }

    private void informCatalystOfDeath() {
        BlockEntity blockEntity = this.level().getBlockEntity(this.catalystPos);
        if (blockEntity instanceof DysfunctionningCatalystBlockEntity catalyst) {
            catalyst.contagionDied();
        }
    }

    protected boolean canRide(Entity entity) {
        return false;
    }

}
