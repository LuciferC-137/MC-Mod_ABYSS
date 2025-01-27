package wardentools.entity.custom;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import wardentools.entity.interfaces.MimicEntity;
import wardentools.fluid.FluidRegistry;
import wardentools.particle.ParticleRegistry;
import wardentools.sounds.ModSounds;

import java.util.function.BiConsumer;

public class ShadowEntity extends MimicEntity implements VibrationSystem {
	private static final Logger LOGGER = LogUtils.getLogger();
	public final AnimationState idleAnimation = new AnimationState();
	public final AnimationState stasisAnimation = new AnimationState();
	public final AnimationState walkAnim = new AnimationState();
	public final AnimationState walkToIdleAnimation = new AnimationState();
	private static final int WALK_TO_IDLE_TICKS = 5;
	private static final EntityDataAccessor<Boolean> IS_STASIS =
			SynchedEntityData.defineId(ShadowEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> walkToIdleTicks =
			SynchedEntityData.defineId(ShadowEntity.class, EntityDataSerializers.INT);
	private final VibrationSystem.User vibrationUser;
	private VibrationSystem.Data vibrationData;
	private final DynamicGameEventListener<Listener> dynamicGameEventListener;
	private int outOfStasisTicks = 0;
	private static final int MAX_OUT_OF_STASIS_TICKS = 6000;

	public ShadowEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
		this.vibrationUser = new ShadowEntity.ShadowVibrationUser();
		this.vibrationData = new VibrationSystem.Data();
		this.dynamicGameEventListener = new DynamicGameEventListener<>(new VibrationSystem.Listener(this));

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 3.0D, false){
			@Override
			public boolean canUse() {
				return super.canUse() && !((ShadowEntity)this.mob).isStasis();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !((ShadowEntity)this.mob).isStasis();
			}
		});
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				return !isStasis() && super.canUse();
			}
			@Override
			public boolean canContinueToUse() {
				return !isStasis() && super.canContinueToUse();
			}
		});
		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.FLYING_SPEED, 0.01D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level().isClientSide) {
			VibrationSystem.Ticker.tick(this.level(), this.vibrationData, this.vibrationUser);
		}
		if (this.getWalkToIdleTicks() > 0) this.setWalkToIdleTicks(this.getWalkToIdleTicks() - 1);
		if (this.isAlmostIdle() && this.getDeltaMovement().lengthSqr() > 0
				&& this.getWalkToIdleTicks() == 0 && !this.isStasis()) {
			this.setWalkToIdleTicks(WALK_TO_IDLE_TICKS);
		}
		if (this.level().isClientSide) {
			this.walkToIdleAnimation.animateWhen(this.getWalkToIdleTicks() > 0, this.tickCount);
			this.walkAnim.animateWhen(!this.isAlmostIdle()
					&& this.getWalkToIdleTicks() <= 0 && !this.isStasis(), this.tickCount);
			this.idleAnimation.animateWhen(this.isAlmostIdle() && this.getWalkToIdleTicks() <= 0
					&& !this.isStasis(), this.tickCount);
			this.stasisAnimation.animateWhen(this.isStasis(), this.tickCount);
			this.animateParticleClient();
		}
		if (this.isStasis()) this.doStasisTick(); else this.setNoGravity(false);
		if (!this.isStasis() && this.getTarget() == null) {
			outOfStasisTicks++;
			if (outOfStasisTicks >= MAX_OUT_OF_STASIS_TICKS) {
				this.setStasis(true);
				outOfStasisTicks = 0;
			}
		} else {
			outOfStasisTicks = 0;
		}
	}

	@Override
	public void updateDynamicGameEventListener(
			@NotNull BiConsumer<DynamicGameEventListener<?>, ServerLevel> consumer) {
		Level level = this.level();
		if (level instanceof ServerLevel serverlevel) {
			consumer.accept(this.dynamicGameEventListener, serverlevel);
		}
	}

	private void doStasisTick() {
		this.setNoGravity(true);
		if (this.getHeightAboveGround() < 3D) {
			this.setDeltaMovement(0, 0.015, 0);
		} else {
			this.setDeltaMovement(0D,
					Math.cos((double)this.tickCount * 0.02D) * 0.02D,
					0D);
		}
		this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
	}

	public double getHeightAboveGround() {
		BlockPos groundPos = this.blockPosition();
		while (groundPos.getY() > this.level().getMinY()
				&& this.level().getBlockState(groundPos).isAir()) {
			groundPos = groundPos.below();
		}
		return this.getY() - groundPos.getY();
	}

	@Override
	protected boolean isAffectedByFluids() {
		return false;
	}

	@Override
	public boolean canStandOnFluid(@NotNull FluidState fluidState) {
		if (fluidState.is(FluidRegistry.SOURCE_LIQUID_CORRUPTION.get())
				|| fluidState.is(FluidRegistry.FLOWING_LIQUID_CORRUPTION.get())) {
			return true;
		}
		return super.canStandOnFluid(fluidState);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(IS_STASIS, false);
		entityData.define(walkToIdleTicks, 0);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("isStasis", this.isStasis());
		tag.putInt("walkToIdleTicks", this.getWalkToIdleTicks());
		VibrationSystem.Data.CODEC.encodeStart(NbtOps.INSTANCE, this.vibrationData)
				.resultOrPartial(LOGGER::error).ifPresent((p_219418_) -> {
			tag.put("listener", p_219418_);
		});
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setStasis(tag.getBoolean("isStasis"));
		this.setWalkToIdleTicks(tag.getInt("walkToIdleTicks"));
		if (tag.contains("listener", 10)) {
			VibrationSystem.Data.CODEC.parse(
					new Dynamic<>(NbtOps.INSTANCE, tag.getCompound("listener")))
					.resultOrPartial(LOGGER::error).ifPresent((data) -> {
				this.vibrationData = data;
			});
		}
	}

	private void animateParticleClient() {
		float particleSpawnRadius = this.getBbWidth() * 4f;
		Vec3 center = this.getBoundingBox().getCenter();
		if (this.isStasis()) {
			if (this.tickCount%10 == this.getRandom().nextInt(10)) {
				float x =  (this.getRandom().nextFloat() - 0.5f) * 1.2f
						*  (float)this.getBoundingBox().getXsize();
				float y = (this.getRandom().nextFloat() - 0.5f) * 1.2f
						* (float)this.getBoundingBox().getYsize();
				float z = (this.getRandom().nextFloat() - 0.5f) * 1.2f
						* (float)this.getBoundingBox().getZsize();
				this.level().addParticle(ParticleRegistry.CORRUPTION.get(),
						(float)center.x + x,
						(float)center.y + y,
						(float)center.z + z,
						0, -0.2, 0);
			}
		} else {
			if (this.tickCount%10 == this.getRandom().nextInt(10)){
				float x =  (this.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float y = (this.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float z = (this.getRandom().nextFloat() * 2 - 1f) * particleSpawnRadius;
				float norm = Mth.sqrt(x*x + y*y + z*z) / (0.1f * particleSpawnRadius) ;
				this.level().addParticle(ParticleRegistry.CORRUPTION.get(),
						(float)center.x + x,
						(float)center.y + y,
						(float)center.z + z,
						-x / norm,
						-y / norm,
						-z / norm);
			}
		}
	}

	public boolean isStasis() {return this.entityData.get(IS_STASIS);}

	public void setStasis(boolean stasis) {this.entityData.set(IS_STASIS, stasis);}

	public int getWalkToIdleTicks() {return this.entityData.get(walkToIdleTicks);}

	public void setWalkToIdleTicks(int ticks) {this.entityData.set(walkToIdleTicks, ticks);}

	@Override
	public @NotNull Data getVibrationData() {
		return this.vibrationData;
	}

	@Override
	public VibrationSystem.@NotNull User getVibrationUser() {
		return this.vibrationUser;
	}

	public static boolean canSpawn(EntityType<ShadowEntity> entityType, ServerLevelAccessor level,
								   EntitySpawnReason spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
    }

	private boolean isAlmostIdle() {
		return this.getDeltaMovement().lengthSqr() < 0.002;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
		this.playSound(SoundEvents.DROWNED_STEP, this.getSoundVolume(), 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isStasis() ? null : ModSounds.SHADOW_AMBIENT.get();
    }

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return ModSounds.SHADOW_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {return ModSounds.SHADOW_DEATH.get();}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}


	class ShadowVibrationUser implements VibrationSystem.User {
		private static final int LISTEN_RADIUS = 16;
		private final PositionSource positionSource
				= new EntityPositionSource(ShadowEntity.this, ShadowEntity.this.getEyeHeight());

		@Override
		public int getListenerRadius() {
			return LISTEN_RADIUS;
		}

		@Override
		public @NotNull PositionSource getPositionSource() {
			return this.positionSource;
		}

		@Override
		public @NotNull TagKey<GameEvent> getListenableEvents() {
			return GameEventTags.WARDEN_CAN_LISTEN;
		}

		@Override
		public boolean canTriggerAvoidVibration() {
			return ShadowEntity.this.isStasis();
		}

		@Override
		public boolean canReceiveVibration(@NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos,
										   @NotNull Holder<GameEvent> holder,
										   GameEvent.@NotNull Context context) {
			return ShadowEntity.this.isStasis();
		}

		@Override
		public void onReceiveVibration(@NotNull ServerLevel level, @NotNull BlockPos blockPos,
									   @NotNull Holder<GameEvent> holder, @Nullable Entity source,
									   @Nullable Entity entity1, float v) {
			if (source instanceof ServerPlayer player && !player.isCreative()) {
				if (ShadowEntity.this.isStasis()) {
					ShadowEntity.this.setStasis(false);
					ShadowEntity.this.playSound(SoundEvents.WARDEN_TENDRIL_CLICKS,
							5.0F, ShadowEntity.this.getVoicePitch());
					level.broadcastEntityEvent(ShadowEntity.this, (byte) 61);
				}
			}
		}

	}
}