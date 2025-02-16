package wardentools.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.blockentity.DysfunctionningCatalystBlockEntity;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.IncarnationBodyRotationControl;
import wardentools.entity.utils.IncarnationMoveControl;
import wardentools.entity.utils.goal.IncarnationAttackGoal;
import wardentools.entity.utils.goal.IncarnationSonicStrikeAttackGoal;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.StartPlayingIncarnationTheme;
import wardentools.network.SyncBossEventPacket;
import wardentools.sounds.ModMusics;
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
    public static final int SWING_ATTACK_DURATION = 10;
    public static final int SWING_HIT_TICK = 3;
    public static final float ATTACK_RANGE = 5f;
    public static final int CHANCE_TO_CORRUPT_ON_HIT = 10;
    public static final int AVERAGE_TICK_BETWEEN_SONIC_STRIKES = 1000;
    public static final int SONIC_STRIKE_DURATION = 100;
    public static final int SONIC_STRIKE_EFFECT_TICK = 22;
    public static final int SONIC_STRIKE_PARTICLE_DURATION = 40;
    public static final int CAN_USE_SONIC_STRIKE_HEALTH_MIN = 500;
    public static final int MUSIC_DURATION = 2490;
    private static final BlockParticleOption SOLID_CORRUPTION_PARTICLE
            = new BlockParticleOption(ParticleTypes.BLOCK,
            BlockRegistry.SOLID_CORRUPTION.get().defaultBlockState());
	public int contagionIncarnationDeathTime = 0;
	public final AnimationState dyingAnimationState = new AnimationState();
	public final AnimationState ambient = new AnimationState();
	public final AnimationState idleAmbient = new AnimationState();
	public final AnimationState sprint = new AnimationState();
    public final AnimationState spawnAnimation = new AnimationState();
    public final AnimationState rightSwing = new AnimationState();
    public final AnimationState leftSwing = new AnimationState();
    public final AnimationState sonicStrike = new AnimationState();
    private static final EntityDataAccessor<Integer> tickSpawn
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> rightSwingTick
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> leftSwingTick
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> sonicStrikeTick
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> hasBeenSummonedByCatalyst
            = SynchedEntityData.defineId(ContagionIncarnationEntity.class, EntityDataSerializers.BOOLEAN);
    private BlockPos catalystPos = new BlockPos(0, 0, 0);
    private BlockPos lastSonicStrikePos = new BlockPos(0, 0, 0);
    private int deferredSonicStrikeTick = 0;
    private int tickSinceLastMusicPlayed = 0;
    private boolean isClientInBossEvent = false;

	public ContagionIncarnationEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
		this.bossEvent = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()),
				ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        this.bossEvent.setPlayBossMusic(false);
        this.xpReward = 200;
        this.setPersistenceRequired();
        this.overrideDefaultParameters();
        this.moveControl = new IncarnationMoveControl(this);
        this.setHasBeenSummonedByCatalyst(false);
        this.noCulling = true;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new IncarnationSonicStrikeAttackGoal(this));
        this.goalSelector.addGoal(2, new IncarnationAttackGoal(this, 2.0D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 2D){
            @Override
            public boolean canUse() {
                return ((ContagionIncarnationEntity)this.mob).getSonicStrikeTick() == 0 && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return ((ContagionIncarnationEntity)this.mob).getSonicStrikeTick() == 0 && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10f));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1000D)
	            .add(Attributes.MOVEMENT_SPEED, MOV_SPEED)
	            .add(Attributes.JUMP_STRENGTH, 3.0D)
	            .add(Attributes.ATTACK_DAMAGE, 10.0D)
	            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0F)
                .add(Attributes.FOLLOW_RANGE, 80.0D);
	}

	@Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
        PacketHandler.sendToClient(new SyncBossEventPacket(this.getId(), true), player);
        PacketHandler.sendToClient(new StartPlayingIncarnationTheme(), player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
        PacketHandler.sendToClient(new SyncBossEventPacket(this.getId(), false), player);
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
            if (this.hasBeenSummonedByCatalyst()) this.clientMusicManager();
			this.handleAnimationStates();
            this.handleSonicStrikeParticleEffect();
		} else {
            this.handleSpawnLogic();
            this.updateSynchronizedTicks();
            this.randomSonicAttackTrigger();
        }
		super.tick();
	}

    private void clientMusicManager() {
        if (this.tickSinceLastMusicPlayed >= MUSIC_DURATION) {
            this.tickSinceLastMusicPlayed = 0;
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && this.isClientInBossEvent) {
                Minecraft.getInstance().getMusicManager().startPlaying(ModMusics.INCARNATION_THEME);
            } else if (player != null) {
                Minecraft.getInstance().getMusicManager().stopPlaying();
            }
        } else this.tickSinceLastMusicPlayed++;
    }

    private void handleSonicStrikeParticleEffect() {
        if (this.getSonicStrikeTick() == SONIC_STRIKE_DURATION - 1) {
            this.lastSonicStrikePos = this.blockPosition();
        }
        if (this.getSonicStrikeTick() == SONIC_STRIKE_EFFECT_TICK) {
            this.deferredSonicStrikeTick = SONIC_STRIKE_PARTICLE_DURATION;
        }
        if (this.deferredSonicStrikeTick > 0) {
            double baseRadius = 2f;
            double growthRate = 0.4f;
            double particleNumberRate = 0.1f;
            double heightOffset = 0.5f;
            int currentTick = SONIC_STRIKE_PARTICLE_DURATION - this.deferredSonicStrikeTick;
            int particlesPerCircle = (int)(25 * currentTick * particleNumberRate);
            double radius = baseRadius + (growthRate * currentTick);
            Vec3 center = Vec3.atCenterOf(this.lastSonicStrikePos);
            double circleHeight = heightOffset / Mth.sqrt(currentTick);
            for (int i = 0; i < particlesPerCircle; i++) {
                double angle = (2 * Math.PI * i) / particlesPerCircle;
                double xOffset = Math.cos(angle) * radius;
                double zOffset = Math.sin(angle) * radius;
                this.level().addParticle(
                        SOLID_CORRUPTION_PARTICLE,
                        center.x + xOffset,
                        center.y,
                        center.z + zOffset,
                        0, circleHeight, 0
                );
            }
        }
        if (this.deferredSonicStrikeTick > 0) this.deferredSonicStrikeTick--;
    }

    private void randomSonicAttackTrigger() {
        if (this.random.nextInt(AVERAGE_TICK_BETWEEN_SONIC_STRIKES)
                == 0 && this.getHealth() <= CAN_USE_SONIC_STRIKE_HEALTH_MIN
                && this.getTarget() != null && this.getSonicStrikeTick() == 0) {
            this.setSonicStrikeTick(SONIC_STRIKE_DURATION);
        }
    }

    private void updateSynchronizedTicks() {
        if (this.getRightSwingTick() > 0) {
            this.setRightSwingTick(this.getRightSwingTick() - 1);
            if (this.getRightSwingTick() == SWING_HIT_TICK) {
                this.hurtTargetAtEndOfSwingIfStillInRange();
            }
        }
        if (this.getLeftSwingTick() > 0) {
            this.setLeftSwingTick(this.getLeftSwingTick() - 1);
            if (this.getLeftSwingTick() == SWING_HIT_TICK) {
                this.hurtTargetAtEndOfSwingIfStillInRange();
            }
        }
        if (this.getSonicStrikeTick() > 0) {
            this.setSonicStrikeTick(this.getSonicStrikeTick() - 1);
        }
    }

    private void handleAnimationStates() {
        this.dyingAnimationState.animateWhen(this.contagionIncarnationDeathTime > 0, this.tickCount);
        this.idleAmbient.animateWhen(!this.isSprinting() && this.isActive(), this.tickCount);
        this.ambient.animateWhen(!this.walkAnimation.isMoving() && this.isActive(), this.tickCount);
        this.sprint.animateWhen(this.isSprinting() && this.isActive(), this.tickCount);
        this.spawnAnimation.animateWhen(this.getTickSpawn() > 0, this.tickCount);
        this.rightSwing.animateWhen(this.getRightSwingTick() > 0, this.tickCount);
        this.leftSwing.animateWhen(this.getLeftSwingTick() > 0, this.tickCount);
        this.sonicStrike.animateWhen(this.getSonicStrikeTick() > 0, this.tickCount);
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

    private void hurtTargetAtEndOfSwingIfStillInRange() {
        if (this.getTarget() != null) {
            if (this.isWithinMeleeAttackRange(this.getTarget())
                    && this.getSensing().hasLineOfSight(this.getTarget())) {
                this.doHurtTarget(this.getTarget());
                if (this.random.nextInt(CHANCE_TO_CORRUPT_ON_HIT) == 0) {
                    this.getTarget().addEffect(
                            new MobEffectInstance(
                                    ModEffects.CORRUPTED.getHolder().get(), 100, 0));
                }
            }
        }
    }

    public void swingWithClosestHand() {
        if (this.getTarget() != null) {
            double dx = this.getTarget().getX() - this.getX();
            double dz = this.getTarget().getZ() - this.getZ();
            double angleToTarget = Math.toDegrees(Math.atan2(dz, dx)) - 90;
            double yaw = this.yBodyRot % 360;
            double angleDifference = (angleToTarget - yaw + 360) % 360;
            if (angleDifference > 180) angleDifference -= 360;
            if (angleDifference > 0) this.swingAttackAnimation(InteractionHand.MAIN_HAND);
            else this.swingAttackAnimation(InteractionHand.OFF_HAND);
        }
    }

    public void swingAttackAnimation(InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            this.setRightSwingTick(SWING_ATTACK_DURATION);
        } else {
            this.setLeftSwingTick(SWING_ATTACK_DURATION);
        }
    }

    public boolean isWithinMeleeAttackRange(@NotNull LivingEntity entity) {
        return this.distanceTo(entity) <= ATTACK_RANGE && this.isInFront(entity, 80);
    }

    private boolean isInFront(LivingEntity entity, float degreeMax) {
        double dx = entity.getX() - this.getX();
        double dz = entity.getZ() - this.getZ();
        double angleToTarget = Math.toDegrees(Math.atan2(dz, dx)) - 90;
        double yaw = this.yBodyRot % 360;
        double angleDifference = (angleToTarget - yaw + 360) % 360;
        if (angleDifference > 180) {
            angleDifference -= 360;
        }
        return Math.abs(angleDifference) <= degreeMax;
    }



    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(tickSpawn, 0);
        entityData.define(rightSwingTick, 0);
        entityData.define(leftSwingTick, 0);
        entityData.define(sonicStrikeTick, 0);
        entityData.define(hasBeenSummonedByCatalyst, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("tickSpawn", this.getTickSpawn());
        tag.putInt("catalystX", this.catalystPos.getX());
        tag.putInt("catalystY", this.catalystPos.getY());
        tag.putInt("catalystZ", this.catalystPos.getZ());
        tag.putInt("rightSwingTick", this.getRightSwingTick());
        tag.putInt("leftSwingTick", this.getLeftSwingTick());
        tag.putInt("sonicStrikeTick", this.getSonicStrikeTick());
        tag.putInt("lastSonicStrikeX", this.lastSonicStrikePos.getX());
        tag.putInt("lastSonicStrikeY", this.lastSonicStrikePos.getY());
        tag.putInt("lastSonicStrikeZ", this.lastSonicStrikePos.getZ());
        tag.putBoolean("hasBeenSummonedByCatalyst", this.entityData.get(hasBeenSummonedByCatalyst));
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setTickSpawn(tag.getInt("tickSpawn"));
        this.catalystPos = new BlockPos(tag.getInt("catalystX"),
                tag.getInt("catalystY"), tag.getInt("catalystZ"));
        this.setRightSwingTick(tag.getInt("rightSwingTick"));
        this.setLeftSwingTick(tag.getInt("leftSwingTick"));
        this.setSonicStrikeTick(tag.getInt("sonicStrikeTick"));
        this.lastSonicStrikePos = new BlockPos(tag.getInt("lastSonicStrikeX"),
                tag.getInt("lastSonicStrikeY"), tag.getInt("lastSonicStrikeZ"));
        this.entityData.set(hasBeenSummonedByCatalyst, tag.getBoolean("hasBeenSummonedByCatalyst"));
    }

    public void setCatalystPos(BlockPos pos) {
        this.catalystPos = pos;
        this.setHasBeenSummonedByCatalyst(true);
    }
    public BlockPos getCatalystPos() {return this.catalystPos;}


    public void setTickSpawn(int tickSpawn) {this.entityData.set(ContagionIncarnationEntity.tickSpawn, tickSpawn);}
    public int getTickSpawn() {return this.entityData.get(ContagionIncarnationEntity.tickSpawn);}
    public void setRightSwingTick(int rightSwingTick) {this.entityData.set(ContagionIncarnationEntity.rightSwingTick, rightSwingTick);}
    public int getRightSwingTick() {return this.entityData.get(ContagionIncarnationEntity.rightSwingTick);}
    public void setLeftSwingTick(int leftSwingTick) {this.entityData.set(ContagionIncarnationEntity.leftSwingTick, leftSwingTick);}
    public int getLeftSwingTick() {return this.entityData.get(ContagionIncarnationEntity.leftSwingTick);}
    public void setSonicStrikeTick(int sonicStrikeTick) {this.entityData.set(ContagionIncarnationEntity.sonicStrikeTick, sonicStrikeTick);}
    public int getSonicStrikeTick() {return this.entityData.get(ContagionIncarnationEntity.sonicStrikeTick);}

    public boolean hasBeenSummonedByCatalyst() {return this.entityData.get(hasBeenSummonedByCatalyst);}
    private void setHasBeenSummonedByCatalyst(boolean hasBeenSummonedByCatalyst) {this.entityData.set(ContagionIncarnationEntity.hasBeenSummonedByCatalyst, hasBeenSummonedByCatalyst);}

    public void setClientInBossEvent(boolean isClientInBossEvent) {this.isClientInBossEvent = isClientInBossEvent;}

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

    @Override
    protected boolean canRide(@NotNull Entity entity) {
        return false;
    }

    @Override
    public boolean isInWall() {
        return false;
    }

    private void overrideDefaultParameters() {
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
    public boolean removeWhenFarAway(double v) {
        return false;
    }

    @Override
    public void checkDespawn() {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.discard();
        }
    }
}
