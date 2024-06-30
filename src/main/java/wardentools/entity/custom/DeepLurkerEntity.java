package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import wardentools.entity.ModEntities;
import wardentools.items.ItemRegistry;

public class DeepLurkerEntity extends Animal {
	
	public final AnimationState calmAnimationState = new AnimationState();

	public DeepLurkerEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);

	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 10D));
		this.goalSelector.addGoal(2, new TemptGoal(
				this, 2D, Ingredient.of(ItemRegistry.DARKTREE_SAPLING.get()), false));
		this.goalSelector.addGoal(3, new FollowParentGoal(this, 2D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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
			this.calmAnimationState.animateWhen(
					!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
		}
		
		super.tick();
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return ModEntities.DEEPLURKER.get().create(level);
	}
	
	@Override
	public boolean isFood(ItemStack pStack) {
		return pStack.is(ItemRegistry.DARKTREE_SAPLING.get());
	}
	
	public static boolean canSpawn(EntityType<DeepLurkerEntity> entityType, ServerLevelAccessor level,
			MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return Animal.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
	}

}
