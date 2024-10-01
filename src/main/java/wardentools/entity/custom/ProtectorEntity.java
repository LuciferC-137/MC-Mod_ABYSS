package wardentools.entity.custom;

import java.util.EnumSet;
import java.util.Objects;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.block.ProtectorInvokerBlock;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import net.minecraft.world.entity.animal.AbstractGolem;

public class ProtectorEntity extends AbstractGolem {
	private static final int attackDurationTick = 10;
	private static final int earTickleDuration = 20;
	public static final int invokerRadius = 20;
	public static final int dispawnCoolDown = 40;
	public static final int spawnCoolDown = 25;
	private int earTickleAnimation = 0;
	private static final EntityDataAccessor<Integer> dispawnTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> spawnTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> attackAnimationTick =
            SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState earTickle = new AnimationState();
	public final AnimationState spawning = new AnimationState();
	public BlockPos invokerPos;
	public static final double MAX_HEALTH = 550;
	
	public ProtectorEntity(EntityType<? extends AbstractGolem> entity, Level level) {
		super(entity, level);
		this.earTickleAnimation = 0;
		this.invokerPos = null;
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 3.0D, true));
	    this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 3.0D, 32.0F));
	    this.goalSelector.addGoal(3, new RerturnToInvoker(this, 1.0D, true));
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
				.add(Attributes.JUMP_STRENGTH, 1D)
				.add(Attributes.FOLLOW_RANGE, 30D)
				.add(Attributes.ATTACK_DAMAGE, 30.0D);
	}

	@Override
	public void tick() {
		if (this.invokerPos!=null) {
			this.checkInvoker();
		}
		if (this.getAttackTick() > 0) {
	         this.setAttackTick(this.getAttackTick() - 1);
	    }
		if (this.getSpawnTick() > 0){
			this.setSpawnTick(this.getSpawnTick() - 1);
		}
		if (this.earTickleAnimation > 0) {
			this.earTickleAnimation-=1;
		}
		if (random.nextInt(200)==1) {
			this.earTickleAnimation = earTickleDuration;
		}
		if (level().isClientSide()) {
			this.attackAnimationState.animateWhen(this.getAttackTick() > 0, this.tickCount);
			this.earTickle.animateWhen(this.earTickleAnimation > 0, this.tickCount);
			this.spawning.animateWhen(this.getSpawnTick() > 0, this.tickCount);
		}
		super.tick();
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

	public boolean isSpawning() {return this.getSpawnTick() > 0;}

	public boolean isDispawning() {return this.getDispawnTick() > 0;}

	public void makeSpawnAnimation() {this.setSpawnTick(spawnCoolDown);}

	
	@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(attackAnimationTick, 0);
		this.entityData.define(dispawnTick, 0);
		this.entityData.define(spawnTick, 0);
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
			ProtectorInvokerBlockEntity invoker =
					(ProtectorInvokerBlockEntity)this.level().getBlockEntity(this.invokerPos);
			if (invoker == null) return false;
			invoker.saveHealth(this);
		}
		return super.hurt(source, amount);
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {
	    this.setAttackTick(attackDurationTick);
	    float f = this.getAttackDamage();
	    float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
	    boolean flag = target.hurt(this.damageSources().mobAttack(this), f1);
	    if (flag) {
	       double d2;
	       if (target instanceof LivingEntity livingentity) {
               d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
	       } else {
	          d2 = 0.0D;
	       }
	       double d0 = d2;
	       double d1 = Math.max(0.0D, 1.0D - d0);
	       target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.4F * d1, 0.0D));
	       this.doEnchantDamageEffects(this, target);
	    }

	    this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    return flag;
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
    
    private void checkInvoker() {
    	if (this.level().getBlockEntity(this.invokerPos) instanceof ProtectorInvokerBlockEntity) {
    		if (((ProtectorInvokerBlockEntity) Objects.requireNonNull(this.level().getBlockEntity(this.invokerPos)))
					.isProtectorValid(this)){
    			this.setDispawnTick(0);
				return;
    		}
    	}
		this.setDispawnTick(this.getDispawnTick() + 1);
		if (this.getDispawnTick() >= dispawnCoolDown) {
			this.remove(Entity.RemovalReason.DISCARDED);
		}
    }

	public void setInvokerPos(@NotNull BlockPos pos){
		this.invokerPos = pos;
	}

}

class ChooseMonsterTargetGoal extends NearestAttackableTargetGoal<Monster> {
	public ChooseMonsterTargetGoal(Mob mob, boolean mustSee) {
		super(mob, Monster.class, mustSee);
	}

	@Override
	public void start() {
		this.mob.setTarget(this.target);
		super.start();
	}
}


class RerturnToInvoker extends Goal {
    private final ProtectorEntity entity;
    private final double speedModifier;
    private final boolean canTeleport;
    private static final int goCloserThan = 5;

    public RerturnToInvoker(ProtectorEntity entity, double speedModifier, boolean canTeleport) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.canTeleport = canTeleport;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    
    public boolean canUse() {
        return this.entity.invokerPos != null
				&& !this.entity.invokerPos.closerThan(this.entity.blockPosition(), ProtectorEntity.invokerRadius);
    }

    @Override
    public void start() {
    	this.entity.getNavigation().moveTo(this.entity.invokerPos.getX(),
    			this.entity.invokerPos.getY(), this.entity.invokerPos.getZ(), this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return this.entity.invokerPos != null
				&& !this.entity.invokerPos.closerThan(this.entity.blockPosition(), goCloserThan);
    }

    @Override
    public void tick() {
    	if (canTeleport && !this.entity.invokerPos.closerThan(this.entity.blockPosition(), 100)) {
    		this.entity.teleportTo(this.entity.invokerPos.getX() + 1,
    			this.entity.invokerPos.getY(), this.entity.invokerPos.getZ() + 1);
    	}
    	this.entity.getNavigation().moveTo(this.entity.invokerPos.getX(),
    			this.entity.invokerPos.getY(), this.entity.invokerPos.getZ(), this.speedModifier);
        super.tick();
    }
    
    @Override
    public void stop() {
        this.entity.getNavigation().stop();
    }


}

