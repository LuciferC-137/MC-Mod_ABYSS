package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.ModEntities;
import wardentools.entity.interfaces.CorruptionMonster;
import wardentools.sounds.ModSounds;

import java.util.Objects;

public class ContagionIncarnationEntity extends CorruptionMonster implements Enemy {
	protected static final float DEFAULT_EYE_HEIGHT = 4.8F;
	public static final double MOV_SPEED = 0.2D;
	private final ServerBossEvent bossEvent;
	public static final int CHANCE_OF_SCREAM_ON_HIT = 4;
	public static final int DEATH_DURATION = 200;
	public int contagionIncarnationDeathTime = 0;
	public static final int SWING_DURATION = 10;
	public final AnimationState dyingAnimationState = new AnimationState();
	public final AnimationState ambient = new AnimationState();
	public final AnimationState idleAmbient = new AnimationState();
	public final AnimationState sprint = new AnimationState();
	public final AnimationState headAmbient = new AnimationState();

	public ContagionIncarnationEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
		this.bossEvent = new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()),
				ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		
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
	      return 220F;
    }
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.dyingAnimationState.animateWhen(this.contagionIncarnationDeathTime > 0, this.tickCount);
			this.idleAmbient.animateWhen(!this.isSprinting() && !this.isDeadOrDying(), this.tickCount);
			this.ambient.animateWhen(!this.walkAnimation.isMoving() && !this.isDeadOrDying(), this.tickCount);
			this.sprint.animateWhen(this.isSprinting(), this.tickCount);
			this.headAmbient.animateWhen(!this.getLookControl().isLookingAtTarget()
					&& !this.isSprinting() && !this.isDeadOrDying(), this.tickCount);
		}
		super.tick();
	}
	
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

	@Override
    public void spawnAnim() {
        if (this.level().isClientSide) {
           for(int i = 0; i < 20; ++i) {
              double d0 = this.random.nextGaussian() * 0.02D;
              double d1 = this.random.nextGaussian() * 0.02D;
              double d2 = this.random.nextGaussian() * 0.02D;
              this.level().addParticle(ParticleTypes.POOF, this.getX(1.0D) - d0 * 10.0D,
            		  this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, d0, d1, d2);
           }
        } else {
           this.level().broadcastEntityEvent(this, (byte)20);
        }
     }   

}
