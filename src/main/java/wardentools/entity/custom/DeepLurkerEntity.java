package wardentools.entity.custom;

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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
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
import wardentools.entity.ModEntities;
import wardentools.entity.utils.goal.AvoidWardenAndClimbTreeGoal;
import wardentools.entity.utils.goal.ClimbGoal;
import wardentools.items.ItemRegistry;
import wardentools.sounds.ModSounds;

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
				.add(Attributes.FOLLOW_RANGE, 15D)
				.add(Attributes.TEMPT_RANGE, 15D)
				.add(Attributes.ATTACK_DAMAGE, 2f);
	}
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.scaredAnimationState.animateWhen(this.isScared(), this.tickCount);
			this.calmAnimationState.animateWhen(
					!isInWaterOrBubble()
                            && !this.walkAnimation.isMoving() && !this.isScared(), this.tickCount);
		}
		super.tick();
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }

	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
		return ModEntities.DEEPLURKER.get().create(level, EntitySpawnReason.BREEDING);
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
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull EntitySpawnReason reason) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<DeepLurkerEntity> entityType, ServerLevelAccessor level,
            EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
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
