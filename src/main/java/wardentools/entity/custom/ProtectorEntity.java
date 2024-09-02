package wardentools.entity.custom;

import java.util.EnumSet;

import net.minecraft.core.BlockPos;
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
import wardentools.block.BlockRegistry;
import wardentools.block.ProtectorInvokerBlock;
import wardentools.blockentity.ProtectorInvokerBlockEntity;
import net.minecraft.world.entity.animal.AbstractGolem;

public class ProtectorEntity extends AbstractGolem {
	private static final int attackDurationTick = 10;
	private static final int earTickleDuration = 20;
	public static final int invokerRadius = 20;
	public static final int dispawnCoolDown = 30;
	private int earTickleAnimation = 0;
	private static final EntityDataAccessor<Integer> dispawnTick =
			SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> attackAnimationTick =
            SynchedEntityData.defineId(ProtectorEntity.class, EntityDataSerializers.INT);
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState earTickle = new AnimationState();
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
		if (this.invokerPos==null) {
			this.invokerPos = this.getInvoker();
		}
		
		if (this.getAttackTick() > 0) {
	         this.setAttackTick(this.getAttackTick() - 1);;
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
		}
		this.checkInvoker();
		super.tick();
	}
	
	public int getAttackTick() {
		return this.entityData.get(attackAnimationTick);
	}
	
	public void setAttackTick(int tick) {
		this.entityData.set(attackAnimationTick, tick);
	}

	public int getDispawnTick() {return this.entityData.get(dispawnTick);}

	public void setDispawnTick(int tick) {this.entityData.set(dispawnTick, tick);}


	
	@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(attackAnimationTick, 0);
		this.entityData.define(dispawnTick, 0);
    }
	
	@Override
	public boolean canAttackType(EntityType<?> entity) {
	      if (entity == EntityType.PLAYER) {
	         return false;
	      } else {
	         return super.canAttackType(entity);
	      }
	}
	
	private float getAttackDamage() {
	      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.invokerPos != null){
			ProtectorInvokerBlockEntity invoker =
					(ProtectorInvokerBlockEntity)this.level().getBlockEntity(this.invokerPos);
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
	       if (target instanceof LivingEntity) {
	          LivingEntity livingentity = (LivingEntity)target;
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
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }
	
	@Override
	public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<ProtectorEntity> entityType, ServerLevelAccessor level,
            MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return true;
    }
    
    private static class ChooseMonsterTargetGoal extends NearestAttackableTargetGoal<Monster> {
        public ChooseMonsterTargetGoal(Mob mob, boolean mustSee) {
            super(mob, Monster.class, mustSee);
        }
        
        @Override
        public void start() {
            this.mob.setTarget(this.target);
            super.start();
         }

        @Override
        protected double getFollowDistance() {
            return this.mob.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
        
        @Override
        public void stop() {
            this.mob.setTarget((LivingEntity)null);
            this.targetMob = null;
         }
    }
    
    private void checkInvoker() {
    	if (this.level().getBlockEntity(this.invokerPos) instanceof ProtectorInvokerBlockEntity) {
    		if (((ProtectorInvokerBlockEntity)this.level().getBlockEntity(this.invokerPos)).isProtectorValid(this)){
    			this.setDispawnTick(0);
				return;
    		}
    	}
		this.setDispawnTick(this.getDispawnTick() + 1);
		if (this.getDispawnTick() >= dispawnCoolDown) {
			this.remove(Entity.RemovalReason.DISCARDED);
		}
    }
    
    private BlockPos getInvoker() {
        int lookRadius = ProtectorInvokerBlock.radiusForProtectorSpawn + 1;
        Level level = this.level();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        
        for (int x = -lookRadius; x <= lookRadius; x++) {
            for (int y = -lookRadius; y <= lookRadius; y++) {
                for (int z = -lookRadius; z <= lookRadius; z++) {
                    mutablePos.set(this.blockPosition()).move(x, y, z);
                    if (level.getBlockState(mutablePos).is(BlockRegistry.PROTECTOR_INVOKER.get())) {
                        return mutablePos.immutable();
                    }
                }
            }
        }
        return this.blockPosition();
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
        return !this.entity.invokerPos.closerThan(this.entity.blockPosition(), ProtectorEntity.invokerRadius);
    }

    @Override
    public void start() {
    	this.entity.getNavigation().moveTo(this.entity.invokerPos.getX(),
    			this.entity.invokerPos.getY(), this.entity.invokerPos.getZ(), this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return !this.entity.invokerPos.closerThan(this.entity.blockPosition(), goCloserThan);
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

