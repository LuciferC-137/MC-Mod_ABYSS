package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.Crystal;

public class CrystalGolemEntity extends PathfinderMob {
	private static final EntityDataAccessor<Integer> CRYSTAL =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);

	public CrystalGolemEntity(EntityType<? extends PathfinderMob> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
		super.defineSynchedData(entityData);
		entityData.define(CRYSTAL, Crystal.getDefault().getIndex());
	}


	@Override
	public void tick() {
		super.tick();
	}

	public Crystal getCrystal() {
		return Crystal.fromIndex(this.entityData.get(CRYSTAL));
	}

	public void setCrystalType(Crystal crystal) {
		this.entityData.set(CRYSTAL, crystal.getIndex());
	}


	@Override
	protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
		this.setCrystalType(Crystal.fromIndex(this.getCrystal().getIndex() + 1));
		return InteractionResult.SUCCESS;
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		tag.putInt("crystal_type", entityData.get(CRYSTAL));
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		if (tag.contains("crystal_type")) {
			this.setCrystalType(Crystal.fromIndex(tag.getInt("crystal_type")));
		}
		super.readAdditionalSaveData(tag);
	}

	public static boolean canSpawn(EntityType<CrystalGolemEntity> entityType, ServerLevelAccessor level,
								   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
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

}