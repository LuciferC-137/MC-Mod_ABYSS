package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
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
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;
import wardentools.entity.ModEntities;
import wardentools.entity.client.Temper;
import wardentools.items.ItemRegistry;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class TemperEntity extends TamableAnimal implements NeutralMob {
	public final AnimationState idleAnimationState = new AnimationState();
	private Player invoker = null;
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME
			= SynchedEntityData.defineId(TemperEntity.class, EntityDataSerializers.INT);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	@Nullable
	private UUID persistentAngerTarget;

	public TemperEntity(EntityType<? extends TamableAnimal> entity, Level level) {
		super(entity, level);
		this.moveControl = new FlyingMoveControl(this, 20, true);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		this.setTame(false);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 4.0D, true));
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 4.0D,
				12.0F, 1.0F, true));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 2.0D));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.FLYING_SPEED, 0.2D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	public void tick() {
		if (level().isClientSide()) {
			this.idleAnimationState.animateWhen(
					!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
		}
		dispawnIfOwnerNotRadianceBringer();
		super.tick();
	}

	private void dispawnIfOwnerNotRadianceBringer(){
		if (this.getOwner() == null) {return;}
		if (this.getOwner().getEffect(ModEffects.RADIANCE_BRINGER.get()) == null){
			this.remove(RemovalReason.DISCARDED);
		}
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
	public @NotNull ItemStack getItemInHand(@NotNull InteractionHand hand) {
		if (hand == InteractionHand.MAIN_HAND) {
			return this.getItemBySlot(EquipmentSlot.MAINHAND);
		} else {
			return this.getItemBySlot(EquipmentSlot.OFFHAND);
		}
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
	}

	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addPersistentAngerSaveData(tag);
	}

	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readPersistentAngerSaveData(this.level(), tag);
	}

	public void setPlayerInvoker(Player player){
		this.invoker = player;
		this.tame(player);
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
	public boolean canBeLeashed(Player player) {
		return false;
	}

	@Override
	public boolean isOrderedToSit() {
		return false;
	}

	@Override
	public void setOrderedToSit(boolean sit) {
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType type) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<TemperEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

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
		super.playSound(soundIn, volume, pitch);
    }
    @Override
    public void playAmbientSound() {
    }

	@Override
	public @org.jetbrains.annotations.Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel level,
																			@NotNull AgeableMob mob) {
		return null;
	}



	@Override
	public int getRemainingPersistentAngerTime() {
		return 0;
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_30404_) {
		this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
	}

	@Nullable
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		this.persistentAngerTarget = uuid;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
		if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
			if (target instanceof Wolf) {
				Wolf wolf = (Wolf)target;
				return !wolf.isTame() || wolf.getOwner() != owner;
			} else if (target instanceof TemperEntity){
				TemperEntity temperTarget = (TemperEntity)target;
				return !temperTarget.isTame() || temperTarget.getOwner() != owner;
			}else if (target instanceof Player
					&& owner instanceof Player
					&& !((Player)owner).canHarmPlayer((Player)target)) {
				return false;
			} else if (target instanceof AbstractHorse && ((AbstractHorse)target).isTamed()) {
				return false;
			} else {
				return !(target instanceof TamableAnimal) || !((TamableAnimal)target).isTame();
			}
		} else {
			return false;
		}
	}

}