package wardentools.entity.custom;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.CustomFlyingPathNavigation;
import wardentools.entity.utils.NoctilureFlyingMoveControl;
import wardentools.entity.utils.goal.JoinOwnerGoal;
import wardentools.entity.utils.goal.LandGoal;
import wardentools.entity.utils.goal.RandomFlyGoal;
import wardentools.entity.utils.goal.TakeOffGoal;
import wardentools.items.ItemRegistry;
import wardentools.sounds.ModSounds;

import java.util.Optional;
import java.util.UUID;

public class NoctilureEntity extends TamableAnimal implements NeutralMob, OwnableEntity {
	public static final float FLYING_SPEED = 0.2f;
	private static final int CHANCE_TO_LAND = 2000;
	private static final int CHANCE_TO_TAKE_OFF = 2000;
	private static final int LANDING_ANIMATION_DURATION = 30;
	private static final int IDLE_FLY_TO_FLY_DURATION = 15;
	private static final float FLYING_INERTIA = 0.8f;
	public static final int MAX_SPRINT_ENERGY = 2000; // In tenth of a tick
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
	private static final EntityDataAccessor<Boolean> was_idle_flying
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> landingTick
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> idleFlyToFlyTick
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> flightSprinting
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> sprintEnergy
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<Boolean> WANTS_TO_JOIN_OWNER
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.BOOLEAN);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	public final AnimationState standing = new AnimationState();
	public final AnimationState walking = new AnimationState();
	public final AnimationState flying = new AnimationState();
	public final AnimationState landing = new AnimationState();
	public final AnimationState idleFlying = new AnimationState();
	public final AnimationState idleFlyToFly = new AnimationState();
	protected CustomFlyingPathNavigation flyingNavigation;
	protected PathNavigation groundNavigation;
	protected NoctilureFlyingMoveControl flyingMoveControl;
	protected MoveControl groundMoveControl;
	private Vec3 previousMovement = Vec3.ZERO;
	private boolean energyWasZero = false;
	@Nullable
	private UUID persistentAngerTarget;

	public NoctilureEntity(EntityType<? extends TamableAnimal> entity, Level level) {
		super(entity, level);
		this.flyingNavigation = new CustomFlyingPathNavigation(this, level);
		this.groundNavigation = new GroundPathNavigation(this, level);
		this.flyingMoveControl = new NoctilureFlyingMoveControl(this);
		this.groundMoveControl = new MoveControl(this);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new JoinOwnerGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(4, new LandGoal(this));
		this.goalSelector.addGoal(5, new TakeOffGoal(this));
		this.goalSelector.addGoal(6, new RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				return !NoctilureEntity.this.getIsFlying()
						&& !NoctilureEntity.this.getWantsToJoinOwner() && super.canUse();
			}
		});
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 4f));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.FLYING_SPEED, 0.1D);
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide) this.handleServerTickers();
		if (this.level().isClientSide) this.handleAnimation();
		else if (!this.isVehicle() && !NoctilureEntity.this.getWantsToJoinOwner()) {
			this.handleRandomFlyingLogic();
		}
		if (this.isVehicle())this.handlePlayerControl();
		super.tick();
	}

	private void handleRandomFlyingLogic() {
		if (this.getIsFlying()){
			// Landing at a random time if no other action is performed
			if (!this.getWantsToLand() && !this.getWantsToTakeOff()
					&& this.getRandom().nextInt(CHANCE_TO_LAND) == 0) {
				this.setWantsToLand(true);
			}
			// Condition to land if the ground is reached by chance
			if (this.getHeightAboveGround() <= 1
					&& !this.getWantsToTakeOff() && !this.getWantsToLand()){
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

	private void handlePlayerControl() {
		if (this.getWantsToLand()) {this.setWantsToLand(false);}
		if (this.getWantsToTakeOff()) {this.setWantsToTakeOff(false);}
		if (Minecraft.getInstance().options.keyDown.isDown()){
			if (this.getIsFlying() && this.getHeightAboveGround() <= 1){
				this.land();
			}
		}
		if (this.getIsFlying()) {
			if (Minecraft.getInstance().options.keySprint.isDown()) {
				if (Minecraft.getInstance().options.keyUp.isDown()) this.lowerEnergy(10);
				if (this.energyWasZero) this.setFlightSprinting(this.getSprintEnergy() > 10);
				else this.setFlightSprinting(this.getSprintEnergy() > 0);
			} else {
				this.setFlightSprinting(false);
			}
			if (this.getSprintEnergy() < MAX_SPRINT_ENERGY) {
				if (!Minecraft.getInstance().options.keyUp.isDown()) {
					increaseEnergy(2);
				} else if (!Minecraft.getInstance().options.keySprint.isDown()) {
					increaseEnergy(1);
				}
			}
		} else if (this.getSprintEnergy() < MAX_SPRINT_ENERGY) {
			increaseEnergy(20);
		}
	}

	private void handleAnimation() {
		if (this.getLandingTick() > 0) {
			this.setLandingTick(this.getLandingTick() - 1);
		}
		if (this.getIdleFlyToFlyTick() > 0) {
			this.setIdleFlyToFlyTick(this.getIdleFlyToFlyTick() - 1);
		}
		this.standing.animateWhen(!this.walkAnimation.isMoving()
				&& !this.getIsFlying() && this.noSecondaryAnimation(), this.tickCount);
		this.walking.animateWhen(this.walkAnimation.isMoving()
				&& !this.getIsFlying() && this.noSecondaryAnimation(), this.tickCount);
		this.flying.animateWhen(this.getIsFlying()
				&& !this.isAlmostIdle() && this.noSecondaryAnimation(), this.tickCount);
		this.landing.animateWhen(this.getLandingTick() > 0, this.tickCount);
		this.idleFlying.animateWhen(this.getIsFlying()
				&& this.isAlmostIdle() && this.noSecondaryAnimation(), this.tickCount);
		this.idleFlyToFly.animateWhen(this.getIdleFlyToFlyTick() > 0
				&& this.getLandingTick() == 0 && this.getIsFlying(), this.tickCount);
	}

	private void handleServerTickers() {
		if (this.getWasIdleFlying() && !this.isAlmostIdle() && this.getIsFlying()) {
			this.setWasIdleFlying(false);
			this.setIdleFlyToFlyTick(IDLE_FLY_TO_FLY_DURATION);
		}
		if (this.isAlmostIdle() && !this.getWasIdleFlying()) {
			this.setWasIdleFlying(true);
		}
	}

	public void increaseEnergy(int energy) {
		int newEnergy = Math.min(this.getSprintEnergy() + energy, MAX_SPRINT_ENERGY);
		this.setSprintEnergy(newEnergy);
		if (this.getSprintEnergy() >= 10) this.energyWasZero = false;
	}

	public void lowerEnergy(int energy) {
		int newEnergy = Math.max(this.getSprintEnergy() - energy, 0);
		this.setSprintEnergy(newEnergy);
		if (this.getSprintEnergy() <= 0) this.energyWasZero = true;
	}

	public void call() {
		if (this.getWantsToJoinOwner()) {
			this.playSound(ModSounds.NOCTILURE_AMBIENT.get(),
					this.getSoundVolume() * 2f, this.getVoicePitch());
		}
		this.setWantsToJoinOwner(true);
	}

	@Override
	public void playAmbientSound() {
		super.playAmbientSound();
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

	public void resetRandomFlyingLogic() {
		this.setWantsToLand(false);
		this.setWantsToTakeOff(false);
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
			if (this.isTamed() && player.getItemInHand(hand).getItem()
					== ItemRegistry.NOCTILURE_TREAT.get() && this.getHealth() < this.getMaxHealth()) {
				this.heal(4f);
				player.getItemInHand(hand).shrink(1);
				return InteractionResult.SUCCESS;
			} else if (this.isTamed() && player.getUUID().equals(this.getOwnerUUID())){
				if (!this.level().isClientSide){
					player.startRiding(this);
					Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
				}
				return InteractionResult.SUCCESS;
			}
		}
		if (!this.isTamed() && player.getItemInHand(InteractionHand.MAIN_HAND)
				.is(ItemRegistry.NOCTILURE_TREAT.get())){
			this.tryToTame(player);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	public void tryToTame(Player player) {
		player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
		if (!this.level().isClientSide) {
			if (this.level().random.nextInt(0, 4) == 0) {
				this.tame(player);
				this.setOwnerUUID(player.getUUID());
			}
		}
		this.spawnTamingParticles(this.isTamed());
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
		return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
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
				this.flyingTravel(player.getViewVector(1f).scale(
						this.getFlightSprinting() ? 1.1f : 0.5f).scale(this.isInWater() ? 0.1f : 1f));
			} else if (!this.getIsFlying()) {
				float forward = player.zza;
				float strafe = player.xxa;
				this.moveRelative(0.1F,
						new Vec3(strafe, travelVector.y, forward).scale(this.isInWater() ? 0.1f : 1f));
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
		if (this.isControlledByLocalInstance() && this.isVehicle()) {
			double scale;
			if (this.isInWater()){
				scale = 0.3D;
			} else {
				scale = this.isInLava() ? 0.1D : 0.8D;
			}
			Vec3 desiredMovement = travelVector.scale(1f - FLYING_INERTIA)
					.add(this.previousMovement.scale(FLYING_INERTIA));
			this.previousMovement = desiredMovement;
			this.setDeltaMovement(desiredMovement);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(scale));
		} else {
			this.flyingAutoTravel(travelVector);
		}
		this.calculateEntityAnimation(false);
	}

	public void flyingAutoTravel(Vec3 vec3) {
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
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(DATA_REMAINING_ANGER_TIME, 0);
		entityData.define(TARGETED_HEIGHT_ON_TAKE_OFF, 0);
		entityData.define(IS_FLYING, false);
		entityData.define(WANTS_TO_LAND, false);
		entityData.define(WANTS_TO_TAKE_OFF, false);
		entityData.define(landingTick, 0);
		entityData.define(idleFlyToFlyTick, 0);
		entityData.define(was_idle_flying, false);
		entityData.define(flightSprinting, false);
		entityData.define(sprintEnergy, MAX_SPRINT_ENERGY);
		entityData.define(OWNER_UUID, Optional.empty());
		entityData.define(WANTS_TO_JOIN_OWNER, false);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addPersistentAngerSaveData(tag);
		tag.putBoolean("is_flying", this.getIsFlying());
		tag.putBoolean("wants_to_land", this.getWantsToLand());
		tag.putBoolean("wants_to_take_off", this.getWantsToTakeOff());
		tag.putInt("landing_tick", this.getLandingTick());
		tag.putBoolean("was_idle_flying", this.getWasIdleFlying());
		tag.putInt("sprint_energy", this.getSprintEnergy());
		if (this.getOwnerUUID() != null)tag.putUUID("owner", this.getOwnerUUID());
		tag.putBoolean("wants_to_join_owner", this.entityData.get(WANTS_TO_JOIN_OWNER));
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readPersistentAngerSaveData(this.level(), tag);
		this.setIsFlying(tag.getBoolean("is_flying"));
		this.setWantsToLand(tag.getBoolean("wants_to_land"));
		this.setWantsToTakeOff(tag.getBoolean("wants_to_take_off"));
		this.setLandingTick(tag.getInt("landing_tick"));
		this.setWasIdleFlying(tag.getBoolean("was_idle_flying"));
		this.setSprintEnergy(tag.getInt("sprint_energy"));
		if (tag.hasUUID("owner")){this.setOwnerUUID(tag.getUUID("owner"));}
		this.setWantsToJoinOwner(tag.getBoolean("wants_to_join_owner"));
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

	public int getIdleFlyToFlyTick() {return this.entityData.get(idleFlyToFlyTick);}

	public void setIdleFlyToFlyTick(int tick) {this.entityData.set(idleFlyToFlyTick, tick);}

	public boolean getWasIdleFlying() {return this.entityData.get(was_idle_flying);}

	public void setWasIdleFlying(boolean wasIdleFlying) {this.entityData.set(was_idle_flying, wasIdleFlying);}

	public boolean getFlightSprinting() {return this.entityData.get(flightSprinting);}

	public void setFlightSprinting(boolean sprinting) {this.entityData.set(flightSprinting, sprinting);}

	public int getSprintEnergy() {return this.entityData.get(sprintEnergy);}

	public void setSprintEnergy(int energy) {this.entityData.set(sprintEnergy, energy);}

	public boolean getWantsToJoinOwner() {return this.entityData.get(WANTS_TO_JOIN_OWNER);}

	public void setWantsToJoinOwner(boolean wantsToJoinOwner) {this.entityData.set(WANTS_TO_JOIN_OWNER, wantsToJoinOwner);}

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
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
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
	public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
		return ModEntities.NOCTILURE.get().create(level);
	}

	@Override
	protected void checkFallDamage(double v, boolean b, @NotNull BlockState state, @NotNull BlockPos pos) {
	}

	@Override
	public @Nullable UUID getOwnerUUID() {
		if (this.entityData.get(OWNER_UUID).isPresent()) return this.entityData.get(OWNER_UUID).get();
		else return null;
	}

	public void setOwnerUUID(@Nullable UUID uuid) {
		this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public boolean isTamed() {
		return this.getOwnerUUID() != null;
	}

	public void onPlayerJump(Player player) {
		if (!this.getIsFlying()) {
			this.setIsFlying(true);
			this.setNoGravity(true);
			this.setIdleFlyToFlyTick(IDLE_FLY_TO_FLY_DURATION);
			this.updateMovementLogic();
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
		Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
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
	protected @NotNull Vec3 getPassengerAttachmentPoint(@NotNull Entity passenger,
															@NotNull EntityDimensions dimensions,
															float offset) {
		float rot = this.yBodyRot * ((float)Math.PI / 180F) + (float)Math.PI / 2F;
		return new Vec3(- 0.3f * offset * Mth.cos(rot),
				this.getPassengersRidingOffsetY(dimensions, offset) + 0.1F * offset,
				- 0.3f * offset * Mth.sin(rot));
	}

	protected float getPassengersRidingOffsetY(EntityDimensions dimensions, float offset) {
		return dimensions.height() + (this.isBaby() ? 0.2F : -0.15F) * offset;
	}

	@Override
	protected boolean canAddPassenger(@NotNull Entity entity) {
		return super.canAddPassenger(entity) && !this.isBaby();
	}

	@Override
	public boolean dampensVibrations() {
		return true;
	}

	private boolean isAlmostIdle() {
		return this.getDeltaMovement().lengthSqr() < 0.01;
	}

	private boolean noSecondaryAnimation() {
		return this.getLandingTick() == 0 && this.getIdleFlyToFlyTick() == 0;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.NOCTILURE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return ModSounds.NOCTILURE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {return ModSounds.NOCTILURE_DEATH.get();}

	@Override
	protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {

	}

	protected float getSoundVolume() {
		return 0.8F;
	}

	@Override
	public boolean isFood(@NotNull ItemStack itemStack) {
		return itemStack.is(ItemRegistry.NOCTILURE_TREAT.get());
	}
}