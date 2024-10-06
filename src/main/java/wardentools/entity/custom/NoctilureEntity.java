package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.CustomFlyingPathNavigation;
import wardentools.entity.utils.NoctilureFlyingMoveControl;
import wardentools.entity.utils.goal.RandomFlyGoal;

import java.util.UUID;

public class NoctilureEntity extends TamableAnimal implements NeutralMob {
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> IS_FLYING
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	public final AnimationState standing = new AnimationState();
	public final AnimationState walking = new AnimationState();
	public final AnimationState flying = new AnimationState();
	protected PathNavigation flyingNavigation;
	protected NoctilureFlyingMoveControl flyingMoveControl;
	public Vec3 moveTargetPoint = Vec3.ZERO;
	public BlockPos anchorPoint = BlockPos.ZERO;
	@Nullable
	private UUID persistentAngerTarget;

	public NoctilureEntity(EntityType<? extends TamableAnimal> entity, Level level) {
		super(entity, level);
		this.flyingNavigation = new CustomFlyingPathNavigation(this, level);
		this.flyingMoveControl = new NoctilureFlyingMoveControl(this, 0.4f);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new RandomFlyGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	@Override
	public void tick() {
		if (this.level().isClientSide){
			this.standing.animateWhen(!this.walkAnimation.isMoving() && !this.getIsFlying(), this.tickCount);
			this.walking.animateWhen(this.walkAnimation.isMoving() && !this.getIsFlying(), this.tickCount);
			this.flying.animateWhen(this.getIsFlying(), this.tickCount);
		}
		super.tick();
	}

	@Override
	public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
		if (this.getIsFlying()){
			this.land();
			return InteractionResult.SUCCESS;
		} else {
			this.takeOff();
			return InteractionResult.SUCCESS;
		}
	}

	public void takeOff() {
		this.setIsFlying(true);
		this.setNoGravity(true);
	}

	public void land() {
		this.setIsFlying(false);
		this.setNoGravity(false);
	}

	@Override
	public @NotNull PathNavigation getNavigation() {
		Entity entity = this.getControlledVehicle();
		if (entity instanceof Mob mob) {
			return mob.getNavigation();
		} else {
			return this.getIsFlying() ? this.flyingNavigation : this.navigation;
		}
	}

	@Override
	public @NotNull MoveControl getMoveControl() {
		Entity entity = this.getControlledVehicle();
		if (entity instanceof Mob mob) {
			return mob.getMoveControl();
		} else {
			return this.moveControl;
		}
	}

    public static boolean canSpawn(EntityType<NoctilureEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

	@Override
	public void travel(@NotNull Vec3 vec3) {
		if (this.getIsFlying()){
			this.flyingTravel(vec3);
		} else{
			super.travel(vec3);
		}
	}

	public void flyingTravel(Vec3 vec3) {
		// Copied from FlyingMob
		if (this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.moveRelative(0.02F, vec3);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double)0.8F));
			} else if (this.isInLava()) {
				this.moveRelative(0.02F, vec3);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} else {
				BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
				float f = 0.91F;
				if (this.onGround()) {
					f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
				}

				float f1 = 0.16277137F / (f * f * f);
				f = 0.91F;
				if (this.onGround()) {
					f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
				}

				this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, vec3);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
			}
		}
		this.calculateEntityAnimation(false);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
		this.entityData.define(IS_FLYING, false);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addPersistentAngerSaveData(tag);
		tag.putBoolean("is_flying", this.getIsFlying());
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readPersistentAngerSaveData(this.level(), tag);
		this.setIsFlying(tag.getBoolean("is_flying"));
	}

	public boolean getIsFlying() {
		return this.entityData.get(IS_FLYING);
	}

	public void setIsFlying(boolean flying) {this.entityData.set(IS_FLYING, flying);}

	@Override
	public int getRemainingPersistentAngerTime() {return this.entityData.get(DATA_REMAINING_ANGER_TIME);}

	@Override
	public void setRemainingPersistentAngerTime(int tick) {
		this.entityData.set(DATA_REMAINING_ANGER_TIME, tick);
	}

	@javax.annotation.Nullable
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@javax.annotation.Nullable UUID uuid) {
		this.persistentAngerTarget = uuid;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	@Override
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull MobSpawnType type) {
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
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {return null;}

	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public void playSound(@NotNull SoundEvent soundIn, float volume, float pitch) {
		super.playSound(soundIn, volume, pitch);
	}
	@Override
	public void playAmbientSound() {
	}

	@Override
	public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
		return ModEntities.NOCTILURE.get().create(level);
	}

	@Override
	protected void checkFallDamage(double v, boolean b, @NotNull BlockState state, @NotNull BlockPos pos) {
	}
}