package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import net.minecraft.world.entity.animal.AbstractGolem;
import wardentools.entity.utils.goal.ChooseMonsterTargetGoal;
import wardentools.entity.utils.goal.ReturnToInvokerGoal;
import wardentools.network.PayloadsRecords.ParticlesSounds.ProtectorHeartSynchronize;
import wardentools.network.PayloadsRecords.ParticlesSounds.RadianceParticleExplosion;
import wardentools.sounds.ModSounds;

public class ProtectorEntity extends AbstractGolem {
	public int protectorDeathTime = 0;
	private static final int attackDurationTick = 10;
	public static final int invokerRadius = 20;
	public static final int dispawnCoolDown = 60;
	public static final int spawnCoolDown = 25;
	public static final int earTickleDuration = 20;
	private static final EntityDataAccessor<Integer> dispawnTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> spawnTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> attackAnimationTick =
            SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> earTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState spawning = new AnimationState();
	public final AnimationState dispawning = new AnimationState();
	public final AnimationState earTickle = new AnimationState();
	public BlockPos invokerPos;
	public static final double MAX_HEALTH = 550;
	public static final int DEATH_DURATION = dispawnCoolDown;
	
	public ProtectorEntity(EntityType<? extends AbstractGolem> entity, Level level) {
		super(entity, level);
		this.invokerPos = null;
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2D, true));
	    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 2D, 32.0F));
	    this.goalSelector.addGoal(3, new ReturnToInvokerGoal(this, 1.0D, true));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		
		this.targetSelector.addGoal(0, new ChooseMonsterTargetGoal(this, true));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Animal.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, MAX_HEALTH)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0f)
				.add(Attributes.FOLLOW_RANGE, 30D)
				.add(Attributes.ATTACK_DAMAGE, 30.0D);
	}

	@Override
	public void tick() {
		if (this.getAttackTick() > 0) {
	         this.setAttackTick(this.getAttackTick() - 1);
	    }
		if (this.getSpawnTick() > 0){
			this.setSpawnTick(this.getSpawnTick() - 1);
		}
		if (this.getEarTick() == 0){
			if (random.nextInt( 200) == 0 && !this.isEarTickling()){
				this.setEarTick(earTickleDuration);
			}
		}
		if (this.isEarTickling()){
			this.setEarTick(this.getEarTick() - 1);
		}
		if (level().isClientSide()) {
			this.attackAnimationState.animateWhen(this.getAttackTick() > 0, this.tickCount);
			this.earTickle.animateWhen(this.isEarTickling(), this.tickCount);
			this.spawning.animateWhen(this.isSpawning(), this.tickCount);
			this.dispawning.animateWhen(this.isDispawning(), this.tickCount);
		}
		this.dispawnTick();
		super.tick();
		if (this.isDispawning() || this.isSpawning()) {
			this.setDeltaMovement(0, 0, 0);
		}
	}
	
	public int getAttackTick() {
		return this.entityData.get(attackAnimationTick);
	}
	
	public void setAttackTick(int tick) {
		this.entityData.set(attackAnimationTick, tick);
	}

	public int getSpawnTick() {return this.entityData.get(spawnTick);}

	public void setSpawnTick(int tick) {this.entityData.set(spawnTick, tick);}

	public int getDispawnTick() {return this.entityData.get(dispawnTick);}

	public void setDispawnTick(int tick) {this.entityData.set(dispawnTick, tick);}

	public int getEarTick() {return this.entityData.get(earTick);}

	public void setEarTick(int tick) {this.entityData.set(earTick, tick);}

	public boolean isEarTickling() {return this.getEarTick() > 0;}

	public boolean isSpawning() {return this.getSpawnTick() > 0;}

	public boolean isDispawning() {return this.getDispawnTick() > 0;}

	public void makeSpawnAnimation() {this.setSpawnTick(spawnCoolDown);}

	private void dispawnTick(){
		if (this.invokerPos!=null) {
			if (!this.checkInvoker()) {
				if (this.getDispawnTick() == 0) {
					this.setDispawnTick(dispawnCoolDown);
				}
			} else if (!this.isDeadOrDying()){
				this.setDispawnTick(0);
			}
		}
		if (this.isDeadOrDying() && this.getDispawnTick() == 0){
			this.setDispawnTick(dispawnCoolDown);
		}
		if (this.isDispawning()){
			this.setDispawnTick(this.getDispawnTick() - 1);
			if (this.getDispawnTick() == 0){
				this.deathParticleEffect();
				this.remove(Entity.RemovalReason.DISCARDED);
			}
		}
	}

	private boolean checkInvoker() {
		if (this.level().getBlockEntity(this.invokerPos) instanceof ProtectorInvokerBlockEntity invoker) {
            return invoker.isProtectorValid(this);
		}
		return false;
	}

	public void setInvokerPos(@NotNull BlockPos pos){
		this.invokerPos = pos;
	}

	private void deathParticleEffect() {
		if (this.level() instanceof ServerLevel level) {
			PacketDistributor.sendToPlayersTrackingChunk(level,
					level.getChunkAt(this.blockPosition()).getPos(),
					new RadianceParticleExplosion(this.getPosition(1f).add(0, 1, 0).toVector3f(),
							3.0F, 0.2F, 100, true)
					);
		}
	}

	@Override
	protected void tickDeath() {
		++this.protectorDeathTime;
		if (this.protectorDeathTime >= DEATH_DURATION
				&& !this.level().isClientSide() && !this.isRemoved()) {
			this.level().broadcastEntityEvent(this, (byte)60);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	@Override
	public void die(@NotNull DamageSource source) {
		if (this.invokerPos != null
				&& (this.level().getBlockEntity(this.invokerPos) instanceof ProtectorInvokerBlockEntity invoker)) {
			invoker.saveHealth(this);
		}
		if (!this.isRemoved() && !this.dead) {
			Entity entity = source.getEntity();
			LivingEntity livingentity = this.getKillCredit();
			if (this.deathScore >= 0 && livingentity != null) {
				livingentity.awardKillScore(this, this.deathScore, source);
			}
			if (this.isSleeping()) {
				this.stopSleeping();
			}
			this.dead = true;
			this.getCombatTracker().recheckStatus();
			Level level = this.level();
			if (level instanceof ServerLevel serverlevel) {
				if (entity == null || entity.killedEntity(serverlevel, this)) {
					this.gameEvent(GameEvent.ENTITY_DIE);
					this.dropAllDeathLoot(serverlevel, source);
					this.createWitherRose(livingentity);
				}
				this.level().broadcastEntityEvent(this, (byte)3);
			}
		}
		super.die(source);
	}

	@Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(attackAnimationTick, 0);
		entityData.define(dispawnTick, 0);
		entityData.define(spawnTick, 0);
		entityData.define(earTick, 0);
    }
	
	@Override
	public boolean canAttackType(@NotNull EntityType<?> entity) {
	      if (entity == EntityType.PLAYER) {
	         return false;
	      } else {
	         return super.canAttackType(entity);
	      }
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (this.invokerPos != null) {
			compound.putInt("InvokerPosX", this.invokerPos.getX());
			compound.putInt("InvokerPosY", this.invokerPos.getY());
			compound.putInt("InvokerPosZ", this.invokerPos.getZ());
		}
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("InvokerPosX")
				&& compound.contains("InvokerPosY")
				&& compound.contains("InvokerPosZ")) {
			int x = compound.getInt("InvokerPosX");
			int y = compound.getInt("InvokerPosY");
			int z = compound.getInt("InvokerPosZ");
			this.invokerPos = new BlockPos(x, y, z);
		}
	}

	private float getAttackDamage() {
	      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	}

	@Override
	public boolean hurt(@NotNull DamageSource source, float amount) {
		if (this.invokerPos != null){
			PacketDistributor.sendToAllPlayers(
					new ProtectorHeartSynchronize(this.invokerPos.getCenter().toVector3f(), this.getHealth()));
			if (this.level().getBlockEntity(this.invokerPos) instanceof ProtectorInvokerBlockEntity invoker) {
				invoker.saveHealth(this);
			}
		}
		return super.hurt(source, amount);
	}
	
	@Override
	public boolean doHurtTarget(@NotNull Entity target) {
	    this.setAttackTick(attackDurationTick);
	    return super.doHurtTarget(target);
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }
	
	@Override
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull MobSpawnType spawnType) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<ProtectorEntity> entityType, ServerLevelAccessor level,
            MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return true;
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.PROTECTOR_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return ModSounds.PROTECTOR_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {return ModSounds.PROTECTOR_DEATH.get();}

	protected float getSoundVolume() {
		return 0.8F;
	}

}

