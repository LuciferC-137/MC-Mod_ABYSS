package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.interfaces.CorruptionMonster;
import wardentools.entity.utils.goal.ClimbParasyteGoal;
import wardentools.particle.ParticleRegistry;
import wardentools.sounds.ModSounds;

public class ParasyteEntity extends CorruptionMonster {
	private static final EntityDataAccessor<Boolean> CLIMBING =
			SynchedEntityData.defineId(ParasyteEntity.class, EntityDataSerializers.BOOLEAN);
	public final AnimationState idleAnimation = new AnimationState();

	public ParasyteEntity(EntityType<? extends Monster> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new ClimbParasyteGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 8.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D)
				.add(Attributes.FLYING_SPEED, 0.01D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(CLIMBING, false);
	}

	public boolean isClimbing() {
		return this.entityData.get(CLIMBING);
	}

	public void setClimbing(boolean climbing) {
		this.entityData.set(CLIMBING, climbing);
	}

	@Override
	public void tick() {
		this.idleAnimation.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
		if (this.level().isClientSide) {
			if (this.tickCount%5 == this.level().getRandom().nextInt(5)){
				Vec3 particlePos = this.getPosition(1f);
				this.level().addParticle(ParticleRegistry.CORRUPTION.get(),
						particlePos.x, particlePos.y, particlePos.z,
						this.level().getRandom().nextFloat() * 0.04f,
						this.level().getRandom().nextFloat() * 0.1f,
						this.level().getRandom().nextFloat() * 0.04f);
			}
		}
		super.tick();
	}

    public static boolean canSpawn(EntityType<ParasyteEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

	@Override
	protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
		return new WallClimberNavigation(this, level);
	}

	@Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
		this.playSound(ModSounds.PARASYTE_STEP.get(), this.getSoundVolume(), 1.0F);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return ModSounds.PARASYTE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {return ModSounds.PARASYTE_DEATH.get();}

	protected float getSoundVolume() {
		return 0.4F;
	}

}