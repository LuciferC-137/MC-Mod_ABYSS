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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class NoctilureEntity extends TamableAnimal implements NeutralMob {
	private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME
			= SynchedEntityData.defineId(NoctilureEntity.class, EntityDataSerializers.INT);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	public final AnimationState standing = new AnimationState();
	public final AnimationState walking = new AnimationState();
	@Nullable
	private UUID persistentAngerTarget;

	public NoctilureEntity(EntityType<? extends TamableAnimal> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));

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
		this.standing.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
		this.walking.animateWhen(this.walkAnimation.isMoving(), this.tickCount);
		super.tick();
	}

    public static boolean canSpawn(EntityType<NoctilureEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.addPersistentAngerSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.readPersistentAngerSaveData(this.level(), tag);
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
		return null;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(DATA_REMAINING_ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_30404_) {
		this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
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
}