package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.phys.Vec3;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;

public class PaleWandererEntity extends Animal {
	public final AnimationState calmAnimationState = new AnimationState();

	public PaleWandererEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(3, new PanicGoal(this, 3.0D));
		this.goalSelector.addGoal(4, new BreedGoal(this, 10D));
		this.goalSelector.addGoal(5, new TemptGoal(
				this, 2.0D, Ingredient.of(ItemRegistry.WHITETREE_SAPLING.get()), false));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 2D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Animal.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.MOVEMENT_SPEED, 0.12f)
				.add(Attributes.JUMP_STRENGTH, 1D)
				.add(Attributes.FOLLOW_RANGE, 15D)
				.add(Attributes.ATTACK_DAMAGE, 2f);
	}
	
	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.calmAnimationState.animateWhen(
					!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
			}
		super.tick();
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return ModEntities.PALE_WANDERER.get().create(level);
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
	public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<PaleWandererEntity> entityType, ServerLevelAccessor level,
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