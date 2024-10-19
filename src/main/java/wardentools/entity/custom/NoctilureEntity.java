package wardentools.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.CustomFlyingPathNavigation;
import wardentools.entity.utils.NoctilureFlyingMoveControl;
import wardentools.entity.utils.goal.LandGoal;
import wardentools.entity.utils.goal.RandomFlyGoal;
import wardentools.entity.utils.goal.TakeOffGoal;

import java.util.UUID;

public class NoctilureEntity extends Animal implements NeutralMob, OwnableEntity {
	public static final float FLYING_SPEED = 0.2f;
	private static final int CHANCE_TO_LAND = 200;
	private static final int CHANCE_TO_TAKE_OFF = 200;
	private static final int LANDING_ANIMATION_DURATION = 30;
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> TARGETED_HEIGHT_ON_TAKE_OFF
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> IS_FLYING
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> WANTS_TO_LAND
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> WANTS_TO_TAKE_OFF
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> landingTick
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	public final AnimationState standing = new AnimationState();
	public final AnimationState walking = new AnimationState();
	public final AnimationState flying = new AnimationState();
	public final AnimationState landing = new AnimationState();
	protected CustomFlyingPathNavigation flyingNavigation;
	protected PathNavigation groundNavigation;
	protected NoctilureFlyingMoveControl flyingMoveControl;
	protected MoveControl groundMoveControl;
	protected float playerJumpPendingScale;
	@Nullable
	private UUID persistentAngerTarget;
	@Nullable
	private UUID owner;

	public NoctilureEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
		this.flyingNavigation = new CustomFlyingPathNavigation(this, level);
		this.groundNavigation = new GroundPathNavigation(this, level);
		this.flyingMoveControl = new NoctilureFlyingMoveControl(this);
		this.groundMoveControl = new MoveControl(this);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new LandGoal(this));
		this.goalSelector.addGoal(4, new TakeOffGoal(this));
		this.goalSelector.addGoal(5, new RandomFlyGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D)
				.add(Attributes.FLYING_SPEED, 0.1D);
	}

	@Override
	public void tick() {
		if (this.level().isClientSide){
			this.standing.animateWhen(!this.walkAnimation.isMoving()
					&& !this.getIsFlying() && this.getLandingTick() == 0, this.tickCount);
			this.walking.animateWhen(this.walkAnimation.isMoving()
					&& !this.getIsFlying() && this.getLandingTick() == 0, this.tickCount);
			this.flying.animateWhen(this.getIsFlying(), this.tickCount);
			this.landing.animateWhen(this.getLandingTick() > 0, this.tickCount);
		} else if (!this.isVehicle()) {
			if (this.getIsFlying()){
				// Landing at a random time if no other action is performed
				if (!this.getWantsToLand() && !this.getWantsToTakeOff()
						&& this.getRandom().nextInt(CHANCE_TO_LAND) == 0) {
					this.setWantsToLand(true);
				}
				// Condition to land if the ground is reached by chance
				if (this.onGround() && !this.getWantsToTakeOff() && !this.getWantsToLand()){
					this.land();
				}
			} else {
				// Taking off at a random time if no other action is performed
				if (!this.getWantsToTakeOff() && !this.getWantsToLand()
						&& this.getRandom().nextInt(CHANCE_TO_TAKE_OFF) == 0){
					this.takeOff();
				}
			}
		}
		if (this.getLandingTick() > 0) {
			this.setLandingTick(this.getLandingTick() - 1);
		}
		super.tick();
	}

	public void takeOff() {
		// To call at the beginning of the taking off
		this.setWantsToTakeOff(true);
		this.setIsFlying(true);
		this.setNoGravity(true);
		this.updateMovementLogic();
		this.setTargetedHeightOnTakeOff(this.getRandom().nextInt(10, 30));
	}

	public void land() {
		// To call at the end of the landing
		this.setLandingTick(LANDING_ANIMATION_DURATION);
		this.setWantsToLand(false);
		this.setIsFlying(false);
		this.setNoGravity(false);
		this.updateMovementLogic();
	}

	public double getHeightAboveGround() {
        BlockPos groundPos = this.blockPosition();
		while (groundPos.getY() > this.level().getMinBuildHeight()
				&& this.level().getBlockState(groundPos).isAir()) {
			groundPos = groundPos.below();
		}
		return this.getY() - groundPos.getY();
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
			return this.getIsFlying() ? this.flyingMoveControl : this.groundMoveControl;
		}
	}

	@Override
	public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
		if (!this.isVehicle()){
			if (this.isTamed() && player.getUUID().equals(this.getOwnerUUID())){
				if (!this.level().isClientSide){
					player.startRiding(this);
				}
				return InteractionResult.SUCCESS;
			}
		}
		if (!this.isTamed()){
			if (!this.level().isClientSide){
				this.setOwnerUUID(player.getUUID());
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	public void updateMoveControl() {
		this.moveControl = this.getMoveControl();
	}

	public void updateNavigation() {
		this.navigation = this.getNavigation();
	}

	public void updateMovementLogic() {
		this.updateMoveControl();
		this.updateNavigation();
	}

    public static boolean canSpawn(EntityType<NoctilureEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

	@Override
	public void travel(@NotNull Vec3 travelVector) {
		if (this.isVehicle() && this.getControllingPassenger() instanceof Player player) {
			this.setYRot(player.getYRot());
			this.yRotO = this.getYRot();
			this.setXRot(player.getXRot() * 0.5F);
			this.setRot(this.getYRot(), this.getXRot());
			this.yBodyRot = this.getYRot();
			this.yHeadRot = this.yBodyRot;
			if (this.getIsFlying() && Minecraft.getInstance().options.keyUp.isDown()){
				this.flyingTravel(player.getViewVector(1f));
			} else if (!this.getIsFlying()) {
				float forward = player.zza;
				float strafe = player.xxa;
				this.moveRelative(0.1F, new Vec3(strafe, travelVector.y, forward));
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.91F));
				super.travel(travelVector);
			} else {
				this.flyingTravel(travelVector);
			}
		} else if (this.getIsFlying()){
			this.flyingTravel(travelVector);
		} else {
			super.travel(travelVector);
		}
	}

	public void flyingTravel(Vec3 travelVector) {
		if (this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.setDeltaMovement(travelVector);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.8D));
			} else if (this.isInLava()) {
				this.setDeltaMovement(travelVector);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} else {
				this.setDeltaMovement(travelVector);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.8D));
			}
		}
		this.calculateEntityAnimation(false);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
		this.entityData.define(TARGETED_HEIGHT_ON_TAKE_OFF, 0);
		this.entityData.define(IS_FLYING, false);
		this.entityData.define(WANTS_TO_LAND, false);
		this.entityData.define(WANTS_TO_TAKE_OFF, false);
		this.entityData.define(landingTick, 0);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addPersistentAngerSaveData(tag);
		tag.putBoolean("is_flying", this.getIsFlying());
		tag.putBoolean("wants_to_land", this.getWantsToLand());
		tag.putBoolean("wants_to_take_off", this.getWantsToTakeOff());
		tag.putInt("landing_tick", this.getLandingTick());
		if (this.getOwnerUUID() != null){
			tag.putUUID("owner", this.getOwnerUUID());
		}
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readPersistentAngerSaveData(this.level(), tag);
		this.setIsFlying(tag.getBoolean("is_flying"));
		this.setWantsToLand(tag.getBoolean("wants_to_land"));
		this.setWantsToTakeOff(tag.getBoolean("wants_to_take_off"));
		this.setLandingTick(tag.getInt("landing_tick"));
		if (tag.hasUUID("owner")){this.setOwnerUUID(tag.getUUID("owner"));}
	}

	public int getTargetHeightOnTakeOff() {return this.entityData.get(TARGETED_HEIGHT_ON_TAKE_OFF);}

	public void setTargetedHeightOnTakeOff(int height) {this.entityData.set(TARGETED_HEIGHT_ON_TAKE_OFF, height);}

	public boolean getIsFlying() {return this.entityData.get(IS_FLYING);}

	public void setIsFlying(boolean flying) {this.entityData.set(IS_FLYING, flying);}

	public boolean getWantsToLand() {return this.entityData.get(WANTS_TO_LAND);}

	public void setWantsToLand(boolean land) {this.entityData.set(WANTS_TO_LAND, land);}

	public boolean getWantsToTakeOff() {return this.entityData.get(WANTS_TO_TAKE_OFF);}

	public void setWantsToTakeOff(boolean takeOff) {this.entityData.set(WANTS_TO_TAKE_OFF, takeOff);}

	public int getLandingTick() {return this.entityData.get(landingTick);}

	public void setLandingTick(int tick) {this.entityData.set(landingTick, tick);}

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

	@Override
	public @Nullable UUID getOwnerUUID() {return this.owner;}

	public void setOwnerUUID(@javax.annotation.Nullable UUID p_30587_) {
		this.owner = p_30587_;
	}

	public boolean isTamed() {
		return this.getOwnerUUID() != null;
	}

	public void onPlayerJump(Player player) {
		if (this.getIsFlying()){
			this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1, 0));
		} else {
			this.takeOff();
		}
	}

	@Override
	public boolean isPushable() {
		return !this.isVehicle();
	}

	@Override
	protected void positionRider(@NotNull Entity rider, Entity.@NotNull MoveFunction moveFunction) {
		super.positionRider(rider, moveFunction);
		if (rider instanceof LivingEntity) {
			((LivingEntity)rider).yBodyRot = this.yBodyRot;
		}
	}

	@Override
	@Nullable
	public LivingEntity getControllingPassenger() {
		Entity entity = this.getFirstPassenger();
		if (entity instanceof Player) {return (Player)entity;}
		return super.getControllingPassenger();
	}

	@Nullable
	private Vec3 getDismountLocationInDirection(Vec3 pos, LivingEntity passenger) {
		double d0 = this.getX() + pos.x;
		double d1 = this.getBoundingBox().minY;
		double d2 = this.getZ() + pos.z;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(Pose pose : passenger.getDismountPoses()) {
			blockpos$mutableblockpos.set(d0, d1, d2);
			double d3 = this.getBoundingBox().maxY + 0.75D;

			while(true) {
				double d4 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
				if ((double)blockpos$mutableblockpos.getY() + d4 > d3) {
					break;
				}
				if (DismountHelper.isBlockFloorValid(d4)) {
					AABB aabb = passenger.getLocalBoundsForPose(pose);
					Vec3 vec3 = new Vec3(d0, (double)blockpos$mutableblockpos.getY() + d4, d2);
					if (DismountHelper.canDismountTo(this.level(), passenger, aabb.move(vec3))) {
						passenger.setPose(pose);
						return vec3;
					}
				}
				blockpos$mutableblockpos.move(Direction.UP);
				if (!((double)blockpos$mutableblockpos.getY() < d3)) {
					break;
				}
			}
		}
		return null;
	}

	@Override
	public @NotNull Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
		Vec3 vec3 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(),
				(double)passenger.getBbWidth(),
				this.getYRot() + (passenger.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F));
		Vec3 vec31 = this.getDismountLocationInDirection(vec3, passenger);
		if (vec31 != null) {
			return vec31;
		} else {
			Vec3 vec32 = getCollisionHorizontalEscapeVector((double)this.getBbWidth(),
					(double)passenger.getBbWidth(),
					this.getYRot() + (passenger.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F));
			Vec3 vec33 = this.getDismountLocationInDirection(vec32, passenger);
			return vec33 != null ? vec33 : this.position();
		}
	}

	@Override
	protected @NotNull Vector3f getPassengerAttachmentPoint(@NotNull Entity passenger,
															@NotNull EntityDimensions dimensions,
															float offset) {
		return new Vector3f(0.0F,
				this.getPassengersRidingOffsetY(dimensions, offset) + 0.15F  * offset,
				-0.7F * offset);
	}

	protected float getPassengersRidingOffsetY(EntityDimensions dimensions, float offset) {
		return dimensions.height + (this.isBaby() ? 0.125F : -0.15625F) * offset;
	}
}