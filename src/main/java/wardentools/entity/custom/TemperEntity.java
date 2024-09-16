package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import wardentools.entity.ModEntities;
import wardentools.entity.client.Temper;
import wardentools.items.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class TemperEntity extends PathfinderMob {
	public final AnimationState idleAnimationState = new AnimationState();
	private Player invoker = null;

	public TemperEntity(EntityType<? extends PathfinderMob> entity, Level level) {
		super(entity, level);
		this.moveControl = new FlyingMoveControl(this, 20, true);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new FollowInvokerGoal(this, 2D));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 2D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.FLYING_SPEED, 0.1D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		// TESTING METHOD
		this.setPlayerInvoker(player);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.idleAnimationState.animateWhen(
					!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
		}
		super.tick();
	}
	
	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

	@Override
	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	@Override
	public void setItemInHand(InteractionHand hand, ItemStack stack) {
		if (hand == InteractionHand.MAIN_HAND) {
			this.setItemSlot(EquipmentSlot.MAINHAND, stack);
		}
	}

	@Override
	public ItemStack getItemInHand(InteractionHand hand) {
		if (hand == InteractionHand.MAIN_HAND) {
			return this.getItemBySlot(EquipmentSlot.MAINHAND);
		} else {
			return this.getItemBySlot(EquipmentSlot.OFFHAND);
		}
	}

	public void setPlayerInvoker(Player player){
		this.invoker = player;
	}

	public Player getPlayerInvoker(){
		return this.invoker;
	}

	@Override
	public boolean canHoldItem(ItemStack stack) {
		return stack.getItem() instanceof SwordItem || super.canHoldItem(stack);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.6F;
	}


	@Override
	public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<TemperEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }
	@Override
	protected void dropEquipment() { return;}
    	
	@Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.ALLAY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ALLAY_DEATH;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

    @Override
    public void playSound(SoundEvent soundIn, float volume, float pitch) {
    }
    @Override
    public void playAmbientSound() {
    }

	private class FollowInvokerGoal extends Goal{
		public static final int HORIZONTAL_SCAN_RANGE = 8;
		public static final int VERTICAL_SCAN_RANGE = 4;
		public static final int DONT_FOLLOW_IF_CLOSER_THAN = 3;
		private final TemperEntity temper;
		@Nullable
		private final double speedModifier;
		private int timeToRecalcPath;

		public FollowInvokerGoal(TemperEntity temper, double speedModifier) {
			this.temper = temper;
			this.speedModifier = speedModifier;
		}

		public boolean canUse() {
			return this.temper.getPlayerInvoker() != null;
		}

		public boolean canContinueToUse() {
			return this.temper.getPlayerInvoker() != null;
		}

		public void start() {
			this.timeToRecalcPath = 0;
		}

		public void stop() {
			return;
		}

		public void tick() {
			if (this.temper.getPlayerInvoker() != null){
				if (--this.timeToRecalcPath <= 0) {
					this.timeToRecalcPath = this.adjustedTickDelay(10);
					this.temper.getNavigation().moveTo(this.temper.getPlayerInvoker(), this.speedModifier);
				}
			}
		}
	}
}