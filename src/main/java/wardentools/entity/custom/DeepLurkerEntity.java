package wardentools.entity.custom;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;

public class DeepLurkerEntity extends Animal {
	public final AnimationState calmAnimationState = new AnimationState();
	public final AnimationState scaredAnimationState = new AnimationState();
	private static final EntityDataAccessor<Boolean> CLIMBING =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SCARED =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.BOOLEAN);

	public DeepLurkerEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new AvoidWardenAndClimbTreeGoal(this, 3.0D, 0.3D));
		this.goalSelector.addGoal(2, new ClimbGoal(this));
		this.goalSelector.addGoal(3, new PanicGoal(this, 3.0D));
		this.goalSelector.addGoal(4, new BreedGoal(this, 10D));
		this.goalSelector.addGoal(5, new TemptGoal(
				this, 2.0D, Ingredient.of(ItemRegistry.DARKTREE_SAPLING.get()), false));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 2D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Animal.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.MOVEMENT_SPEED, 0.1f)
				.add(Attributes.JUMP_STRENGTH, 1D)
				.add(Attributes.FOLLOW_RANGE, 15D)
				.add(Attributes.ATTACK_DAMAGE, 2f);
	}
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.scaredAnimationState.animateWhen(this.isScared(), this.tickCount);
			this.calmAnimationState.animateWhen(
					!isInWaterOrBubble() && !this.walkAnimation.isMoving() && !this.isScared(), this.tickCount);
		}
		super.tick();
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return ModEntities.DEEPLURKER.get().create(level);
	}
	
	@Override
	public boolean isFood(ItemStack pStack) {
		return pStack.is(ItemRegistry.DEEP_FRUIT.get());
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
	      return new WallClimberNavigation(this, level);
	}
	
	@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLIMBING, false);
        this.entityData.define(SCARED, false);
    }

    public boolean isClimbing() {
        return this.entityData.get(CLIMBING);
    }

    public void setClimbing(boolean climbing) {
        this.entityData.set(CLIMBING, climbing);
    }
    
    public boolean isScared() {
    	return this.entityData.get(SCARED);
    }
    
    public void setScared(boolean scared) {
        this.entityData.set(SCARED, scared);
    }
    
    @Override
	public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<DeepLurkerEntity> entityType, ServerLevelAccessor level,
            MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    	boolean canSpawn = level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
		return canSpawn;// Animal.checkMobSpawnRules(entityType, level, spawnType, pos, random);
    }
    	
	@Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
    }
    @Override
    public void playSound(SoundEvent soundIn, float volume, float pitch) {
    }
    @Override
    public void playAmbientSound() {  
    }
}

class ClimbGoal extends Goal {
    private final DeepLurkerEntity entity;

    public ClimbGoal(DeepLurkerEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }
    
    @Override
    public void start() {
        this.entity.setClimbing(true);
    }

    @Override
    public void stop() {
        this.entity.setClimbing(false);
    }

    @Override
    public boolean canUse() {
        return this.entity.horizontalCollision &&
        		this.entity.level().getBlockState(this.entity.blockPosition().above()).isAir();
    }

    @Override
    public void tick() {
        if (this.entity.horizontalCollision) {
            this.entity.setDeltaMovement(this.entity.getDeltaMovement().multiply(1.0D, 0.2D, 1.0D));
            if (this.entity.level().getBlockState(this.entity.blockPosition().above()).isAir()) {
                this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.2D, 0.0D));
            }
        }
    }
    
}


class AvoidWardenAndClimbTreeGoal extends Goal {
    private final DeepLurkerEntity entity;
    private final double speedModifier;
    private final double climbSpeedModifier;
    private LivingEntity target;
    private BlockPos targetTreePos;
    private static final int scaredRadius = 20;

    public AvoidWardenAndClimbTreeGoal(DeepLurkerEntity entity, double speedModifier, double climbSpeedModifier) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.climbSpeedModifier = climbSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        List<Warden> wardens = entity.level().getEntitiesOfClass(Warden.class, entity.getBoundingBox().inflate(scaredRadius));
        if (!wardens.isEmpty()) {
            this.target = wardens.get(0);
            BlockPos startPos = entity.blockPosition();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            for (int dx = -scaredRadius; dx <= scaredRadius; dx++) {
                for (int dz = -scaredRadius; dz <= scaredRadius; dz++) {
                    for (int dy = -scaredRadius; dy <= scaredRadius; dy++) {
                        mutableBlockPos.set(startPos.getX() + dx, startPos.getY() + dy, startPos.getZ() + dz);
                        if (entity.level().getBlockState(mutableBlockPos).getBlock() == BlockRegistry.DARKTREE_LOG.get()) {
                            this.targetTreePos = mutableBlockPos.immutable();
                            this.targetTreePos = this.getTargetOnTree();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (this.targetTreePos != null) {
            this.entity.getNavigation().moveTo(targetTreePos.getX(), targetTreePos.getY(), targetTreePos.getZ(), this.speedModifier);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() &&
                this.entity.distanceTo(this.target) < scaredRadius &&
                this.entity.level().getBlockState(targetTreePos).getBlock() == BlockRegistry.DARKTREE_LOG.get();
    }

    @Override
    public void stop() {
        this.target = null;
        this.targetTreePos = null;
        this.entity.setClimbing(false);
        this.entity.setNoGravity(false);
        this.entity.setScared(false);
    }

    @Override
    public void tick() {
        if (this.target != null && this.entity.horizontalCollision && !isAtTopOfTree()) {
            this.entity.setClimbing(true);
            this.entity.getNavigation().moveTo(this.targetTreePos.getX(),
            		this.targetTreePos.getY(), this.targetTreePos.getZ(), this.climbSpeedModifier);
            this.entity.setDeltaMovement(this.entity.getDeltaMovement()
            		.add(0.0D, 0.2D, 0.0D));
        }

        else if (isAtTopOfTree()) {
        	this.entity.getLookControl().setLookAt(this.target);
        	this.entity.setDeltaMovement(Vec3.ZERO);;
            this.entity.getNavigation().stop();
            this.entity.setNoGravity(true);
            this.entity.setScared(true);
        }
    }

    private boolean isAtTopOfTree() {
        BlockPos pos = this.entity.blockPosition();
        return this.entity.level().getBlockState(pos.above()).getBlock() == BlockRegistry.DARKTREE_LEAVES.get();
    }

    private BlockPos getTargetOnTree() {
        if (targetTreePos != null) {
            int i = 0;
            while (!this.entity.level().getBlockState(targetTreePos.above(i)).isAir() && i < 20) {
                i += 1;
            }
            return targetTreePos.above(i);
        }
        return null;
    }
}
