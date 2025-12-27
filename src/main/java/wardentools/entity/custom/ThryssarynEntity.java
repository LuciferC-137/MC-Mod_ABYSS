package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.entity.utils.AnimationSequence;
import wardentools.items.ItemRegistry;
import wardentools.sounds.ModSounds;

import java.util.ArrayList;
import java.util.List;

public class ThryssarynEntity extends Animal {
	public final AnimationState standing2playingLuth = new AnimationState();
	public final AnimationState playingLuth2standing = new AnimationState();
	public final AnimationState playingLuthWarden = new AnimationState();

	public static final EntityDataAccessor<Boolean> IS_PLAYING_LUTH =
			SynchedEntityData.defineId(ThryssarynEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> EYES_COLOR =
			SynchedEntityData.defineId(ThryssarynEntity.class, EntityDataSerializers.INT);

	private final List<Runnable> scheduledTasks = new ArrayList<>();
	private final List<Integer> taskDelays = new ArrayList<>();

	public static final ItemStack LUTH_ITEMSTACK = new ItemStack(ItemRegistry.LUTH.get());
	public static final int[] EYE_COLORS = new int[] {
			0xFF0000, // Red
			0x00FF00, // Green
			0x0000FF, // Blue
			0xFFFF00, // Yellow
			0xFF00FF, // Magenta
			0x00FFFF, // Cyan
			0xFFFFFF, // White
			0xFFA500  // Orange
	};

	public AnimationSequence thryssarynLuthSequence = new AnimationSequence(
			new int[] {40, 1040, 40},
			standing2playingLuth,
			playingLuthWarden,
			playingLuth2standing
	);

	public ThryssarynEntity(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}
	
	protected void registerGoals() {
	}

	public static AttributeSupplier.Builder createAttribute(){
		return Animal.createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.FOLLOW_RANGE, 30D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	public void tick() {
		super.tick();
		thryssarynLuthSequence.tick(this.tickCount);
		if (!thryssarynLuthSequence.isRunning()) {
			this.setPlayingLuth(false);
		}
		this.handleScheduledTasks();
		if (!this.level().isClientSide && this.tickCount % 200 == 0) {
			this.setEyesColor(EYE_COLORS[this.random.nextInt(EYE_COLORS.length)]);
		}
	}

	public void handleScheduledTasks() {
		for (int i = 0; i < taskDelays.size(); i++) {
			int delay = taskDelays.get(i) - 1;
			if (delay <= 0) {
				scheduledTasks.get(i).run();
				scheduledTasks.remove(i);
				taskDelays.remove(i);
				i--;
			} else {
				taskDelays.set(i, delay);
			}
		}
	}

	public void schedule(Runnable task, int delayTicks) {
		scheduledTasks.add(task);
		taskDelays.add(delayTicks);
	}

	public void startPlayingLuthToCalmWarden() {
		if (!thryssarynLuthSequence.isRunning()) {
			thryssarynLuthSequence.start();
			this.schedule(() -> this.playSound(ModSounds.LUTH_PLAYING_WARDEN.get()), 38);
		}
		this.setPlayingLuth(true);
	}

	public void stopPlayingLuth() {
		if (thryssarynLuthSequence.isRunning()) {
			thryssarynLuthSequence.stop();
		}
		this.setPlayingLuth(false);
	}

	@Override
	public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel,
                                                  @NotNull AgeableMob ageableMob) {
		return null;
	}

	@Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
		entityData.define(IS_PLAYING_LUTH, false);
		entityData.define(EYES_COLOR, EYE_COLORS[0]);
    }
	
	@Override
	public boolean canAttackType(@NotNull EntityType<?> entity) {
		return super.canAttackType(entity);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isPlayingLuth", this.isPlayingLuth());
		compound.putInt("eyesColor", this.getEyesColor());
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setPlayingLuth(compound.getBoolean("isPlayingLuth"));
		if (compound.contains("eyesColor")) {
			this.setEyesColor(compound.getInt("eyesColor"));
		}
	}

	@Override
	public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec,
												 @NotNull InteractionHand hand) {
		if (thryssarynLuthSequence.isRunning()) {
			this.stopPlayingLuth();
		} else {
			this.startPlayingLuthToCalmWarden();
		}
		return InteractionResult.SUCCESS;
	}

	public boolean isPlayingLuth() {return this.entityData.get(IS_PLAYING_LUTH);}

	public void setPlayingLuth(boolean isPlayingLuth) {this.entityData.set(IS_PLAYING_LUTH, isPlayingLuth);}

	public int getEyesColor() {return this.entityData.get(EYES_COLOR);}

	public void setEyesColor(int color) {this.entityData.set(EYES_COLOR, color);}

	@Override
	public boolean isFood(@NotNull ItemStack itemStack) {
		return false;
	}

	@Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, @NotNull DamageSource source) {
        return false;
    }
	
	@Override
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull MobSpawnType spawnType) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<ThryssarynEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return true;
    }

	protected float getSoundVolume() {
		return 0.8F;
	}

}

