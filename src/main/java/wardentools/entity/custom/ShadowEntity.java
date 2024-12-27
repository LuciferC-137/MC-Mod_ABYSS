package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.interfaces.MimicEntity;
import wardentools.fluid.BaseFluidType;
import wardentools.fluid.FluidRegistry;
import wardentools.fluid.LiquidCorruptionFluid;
import wardentools.fluid.ModFluidTypes;
import wardentools.sounds.ModSounds;

public class ShadowEntity extends MimicEntity {
	public final AnimationState idleAnimation = new AnimationState();
	public final AnimationState stasisAnimation = new AnimationState();
	public final AnimationState walkAnim = new AnimationState();
	public final AnimationState walkToIdleAnimation = new AnimationState();
	private static final int WALK_TO_IDLE_TICKS = 5;
	private static final EntityDataAccessor<Boolean> IS_STASIS =
			SynchedEntityData.defineId(ShadowEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> walkToIdleTicks =
			SynchedEntityData.defineId(ShadowEntity.class, EntityDataSerializers.INT);

	public ShadowEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 3.0D, false){
			@Override
			public void start() {
				super.start();
				if (this.mob instanceof ShadowEntity shadow) shadow.setStasis(false);
			}
		});
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
			@Override
			public boolean canUse() {
				return !isStasis() && super.canUse();
			}
		});
		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(1,
				new NearestAttackableTargetGoal<>(this, Player.class, true));
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
		if (this.getWalkToIdleTicks() > 0) this.setWalkToIdleTicks(this.getWalkToIdleTicks() - 1);
		if (this.isAlmostIdle() && this.getDeltaMovement().lengthSqr() > 0
				&& this.getWalkToIdleTicks() == 0 && !this.isStasis()) {
			this.setWalkToIdleTicks(WALK_TO_IDLE_TICKS);
		}
		if (this.level().isClientSide){
			this.walkToIdleAnimation.animateWhen(this.getWalkToIdleTicks() > 0, this.tickCount);
			this.walkAnim.animateWhen(!this.isAlmostIdle()
					&& this.getWalkToIdleTicks() <= 0 && !this.isStasis(), this.tickCount);
			this.idleAnimation.animateWhen(this.isAlmostIdle() && this.getWalkToIdleTicks() <= 0
					&& !this.isStasis(), this.tickCount);
			this.stasisAnimation.animateWhen(this.isStasis(), this.tickCount);
		}
		if (this.isStasis()) this.doStasisTick(); else this.setNoGravity(false);
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
		if (this.getTarget() != null) {this.setStasis(false);}
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
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_STASIS, false);
		this.entityData.define(walkToIdleTicks, 0);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("isStasis", this.isStasis());
		tag.putInt("walkToIdleTicks", this.getWalkToIdleTicks());
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setStasis(tag.getBoolean("isStasis"));
		this.setWalkToIdleTicks(tag.getInt("walkToIdleTicks"));
	}

	public boolean isStasis() {return this.entityData.get(IS_STASIS);}

	public void setStasis(boolean stasis) {this.entityData.set(IS_STASIS, stasis);}

	public int getWalkToIdleTicks() {return this.entityData.get(walkToIdleTicks);}

	public void setWalkToIdleTicks(int ticks) {this.entityData.set(walkToIdleTicks, ticks);}

	public static boolean canSpawn(EntityType<ShadowEntity> entityType, ServerLevelAccessor level,
								   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
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
}