package wardentools.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.utils.RenderToBufferFunction;
import wardentools.entity.utils.SetUpAnimFunction;
import wardentools.entity.utils.getBobFunction;

import java.lang.reflect.Method;

public class ShadowEntity extends Monster {
	private static final EntityDataAccessor<Integer> DEAD_ENTITY_ID
			= SynchedEntityData.defineId(ShadowEntity.class, EntityDataSerializers.INT);
	private LivingEntity deadEntity;

	public ShadowEntity(EntityType<? extends Monster> entity, Level level) {
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
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.FLYING_SPEED, 0.01D);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide) {
			int id = this.entityData.get(DEAD_ENTITY_ID);
			if (id != -1) {
				Entity entity = this.level().getEntity(id);
				if (entity instanceof LivingEntity) {
					this.deadEntity = (LivingEntity) entity;
				}
			}
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DEAD_ENTITY_ID, -1);
	}

	public RenderToBufferFunction getRenderToBufferFunction() {
		if (this.deadEntity == null) return null;
		EntityRenderer<? super LivingEntity> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
				.getRenderer(this.deadEntity);
		if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
			return livingRenderer.getModel()::renderToBuffer;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends LivingEntity> SetUpAnimFunction<T> getSetUpAnimFunction() {
		if (this.deadEntity == null) return null;
		T deadEntityCasted = (T) this.deadEntity;
		EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
				.getRenderer(deadEntityCasted);
		if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
			return ((LivingEntityRenderer<T, ?>)livingRenderer).getModel()::setupAnim;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends LivingEntity> getBobFunction<T> getGetBobFunction() {
		if (this.deadEntity == null) return null;
		T deadEntityCasted = (T) this.deadEntity;
		EntityRenderer<? super T> renderer = Minecraft.getInstance().getEntityRenderDispatcher()
				.getRenderer(deadEntityCasted);
		if (renderer instanceof LivingEntityRenderer<?, ?> livingRenderer) {
			try {
				Method getBobMethod = LivingEntityRenderer.class
						.getDeclaredMethod("getBob", LivingEntity.class, float.class);
				getBobMethod.setAccessible(true);
				return (getBobFunction<T>) (entity, partialTicks) -> {
					try {
						return (float) getBobMethod.invoke(livingRenderer, entity, partialTicks);
					} catch (Exception e) {
						System.out.println("Error invoking getBob method");
						return 0.0f;
					}
				};
			} catch (NoSuchMethodException e) {
				System.out.println("Error invoking getBob method: NoSuchMethodException");
			}
		}
		return null;
	}

	public LivingEntity getDeadEntity() {return deadEntity;}

	public void setDeadEntity(LivingEntity deadEntity) {
		this.deadEntity = deadEntity;
		this.entityData.set(DEAD_ENTITY_ID, deadEntity.getId());
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		if (this.deadEntity != null) {
			tag.putInt("DeadEntityId", this.deadEntity.getId());
		}
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("DeadEntityId")) {
			int id = tag.getInt("DeadEntityId");
			this.entityData.set(DEAD_ENTITY_ID, id);
		}
	}

	private void readDeadEntity(@NotNull CompoundTag tag){
		if (tag.contains("deadEntity")) {
			Entity entity = EntityType.loadEntityRecursive(tag.getCompound("deadEntity"),
					this.level(), (deadEntity) -> {
						deadEntity.setPos(this.getX(), this.getY(), this.getZ());
						return deadEntity;
					});
			if (entity instanceof LivingEntity living) this.deadEntity = living;
		}
	}

	public static boolean canSpawn(EntityType<ShadowEntity> entityType, ServerLevelAccessor level,
								   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
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
}