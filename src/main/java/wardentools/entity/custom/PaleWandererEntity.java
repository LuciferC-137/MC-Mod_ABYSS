package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;
import wardentools.sounds.ModSounds;

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
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }

	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
		return ModEntities.PALE_WANDERER.get().create(level, EntitySpawnReason.BREEDING);
	}
	
	@Override
	public boolean isFood(ItemStack pStack) {
		return pStack.is(ItemRegistry.WHITE_SEED.get());
	}
    
    @Override
	public boolean checkSpawnRules(@NotNull LevelAccessor accessor, @NotNull EntitySpawnReason reason) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<PaleWandererEntity> entityType, ServerLevelAccessor level,
								   EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return ModSounds.PALE_WANDERER_HURT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PALE_WANDERER_DEATH.get();
    }
}