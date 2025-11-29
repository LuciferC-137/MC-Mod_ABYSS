package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.goal.AvoidWardenAndClimbTreeGoal;
import wardentools.entity.utils.goal.ClimbGoal;
import wardentools.items.ItemRegistry;
import wardentools.sounds.ModSounds;
import wardentools.tags.ModTags;

public class DeepLurkerEntity extends Animal {
	public final AnimationState calmAnimationState = new AnimationState();
	public final AnimationState scaredAnimationState = new AnimationState();
    public final AnimationState climbAnimationState = new AnimationState();
	private static final EntityDataAccessor<Boolean> CLIMBING =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SCARED =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_AT_TOP_OF_TREE =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> AVOID_WARDEN_STEP =
            SynchedEntityData.defineId(DeepLurkerEntity.class, EntityDataSerializers.INT);

	public DeepLurkerEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new AvoidWardenAndClimbTreeGoal(this, 3.0D));
		this.goalSelector.addGoal(2, new ClimbGoal(this));
		this.goalSelector.addGoal(3, new PanicGoal(this, 3.0D));
		this.goalSelector.addGoal(4, new BreedGoal(this, 10D));
		this.goalSelector.addGoal(5, new TemptGoal(
				this, 2.0D, Ingredient.of(ItemRegistry.DARKTREE_SAPLING.get()), false));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 2D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 2D) {
            @Override
            public boolean canUse() {
                return super.canUse() && !DeepLurkerEntity.this.isScared();
            }
        });
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Animal.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.MOVEMENT_SPEED, 0.1f)
				.add(Attributes.FOLLOW_RANGE, 15D)
				.add(Attributes.ATTACK_DAMAGE, 2f);
	}
	
	@Override
	public void tick() {
        if (level().isClientSide()) {
			this.scaredAnimationState.animateWhen(this.isScared(), this.tickCount);
			this.calmAnimationState.animateWhen(
					!isInWaterOrBubble()
                            && !this.walkAnimation.isMoving() && !this.isScared(), this.tickCount);
            this.climbAnimationState.animateWhen(this.isClimbing(), this.tickCount);
		} else {
            if (this.isAtTopOfTree()) {
                // Reverse gravity
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.1D, 1.0D));
                if (this.level().getBlockState(this.blockPosition().above()).isAir()) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.1D, 0.0D));
                }
                // Stop having target motion
                this.getNavigation().stop();
                this.setClimbing(false);
                this.setNoGravity(true);
                if (!conditionToBaAtTopOfTree()) {
                    this.setIsAtTopOfTree(false);
                }
            } else {
                this.setNoGravity(false);
            }
        }
		super.tick();
	}

    public boolean conditionToBaAtTopOfTree() {
        BlockPos aboveEntity = this.blockPosition().above();
        return this.level().getBlockState(aboveEntity).is(BlockRegistry.DARKTREE_LEAVES.get())
                || this.level().getBlockState(aboveEntity).is(BlockRegistry.DARKTREE_LOG.get());
    }

    public boolean isCorrectlyHidden() {
        if (!this.isScared()) {
            return false;
        }
        return this.conditionToBaAtTopOfTree() ||
                this.level().getBlockState(this.getOnPos()).is(BlockRegistry.DARKTREE_LEAVES.get())
                || this.level().getBlockState(this.blockPosition()).is(ModTags.Blocks.DARK_VEGETATION);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }

	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
		return ModEntities.DEEPLURKER.get().create(level);
	}
	
	@Override
	public boolean isFood(ItemStack pStack) {
		return pStack.is(ItemRegistry.DEEP_FRUIT.get());
	}

	@Override
	protected @NotNull PathNavigation createNavigation(@NotNull Level level) {

        return new WallClimberNavigation(this, level);
	}
	
	@Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(CLIMBING, false);
        entityData.define(SCARED, false);
        entityData.define(IS_AT_TOP_OF_TREE, false);
        entityData.define(AVOID_WARDEN_STEP, 0);
    }

    public boolean isClimbing() {return this.entityData.get(CLIMBING);}

    public void setClimbing(boolean climbing) {this.entityData.set(CLIMBING, climbing);}
    
    public boolean isScared() {return this.entityData.get(SCARED);}
    
    public void setScared(boolean scared) {this.entityData.set(SCARED, scared);}

    public boolean isAtTopOfTree() {return this.entityData.get(IS_AT_TOP_OF_TREE);}

    public void setIsAtTopOfTree(boolean atTop) {this.entityData.set(IS_AT_TOP_OF_TREE, atTop);}

    public int getAvoidWardenStep() {return this.entityData.get(AVOID_WARDEN_STEP);}

    public void setAvoidWardenStep(int step) {this.entityData.set(AVOID_WARDEN_STEP, step);}

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("climbing", this.isClimbing());
        compound.putBoolean("scared", this.isScared());
        compound.putBoolean("is_at_to_of_tree", this.isAtTopOfTree());
        compound.putInt("avoid_warden_step", this.getAvoidWardenStep());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("climbing")) {
            this.setClimbing(compound.getBoolean("climbing"));
        }
        if (compound.contains("scared")) {
            this.setScared(compound.getBoolean("scared"));
        }
        if (compound.contains("is_at_to_of_tree")) {
            this.setIsAtTopOfTree(compound.getBoolean("is_at_to_of_tree"));
        }
        if (compound.contains("avoid_warden_step")) {
            this.setAvoidWardenStep(compound.getInt("avoid_warden_step"));
        }
    }

    @Override
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull MobSpawnType type) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<DeepLurkerEntity> entityType, ServerLevelAccessor level,
            MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
        // Animal.checkMobSpawnRules(entityType, level, spawnType, startPos, random);
    }
    
    @Override
    public boolean dampensVibrations() {
        return true;
     }
         
	@Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
		return ModSounds.DEEP_LURKER_HURT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DEEP_LURKER_DEATH.get();
    }
}
