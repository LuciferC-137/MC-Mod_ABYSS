package wardentools.entity.thryssaryn.individual;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.client.color.ColorUtils;
import wardentools.entity.ModEntities;
import wardentools.entity.thryssaryn.individual.behavior.Brain;
import wardentools.entity.utils.AnimationSequence;
import wardentools.items.ItemRegistry;

import java.util.function.BiConsumer;


public class ThryssarynEntity extends AbstractThryssaryn {
	public final AnimationState standing2playingLuth = new AnimationState();
	public final AnimationState playingLuth2standing = new AnimationState();
	public final AnimationState playingLuthWarden = new AnimationState();

	private final Brain brain;

	public static final EntityDataAccessor<Boolean> IS_PLAYING_LUTH =
			SynchedEntityData.defineId(ThryssarynEntity.class, EntityDataSerializers.BOOLEAN);

	public static final ItemStack LUTH_ITEMSTACK = new ItemStack(ItemRegistry.LUTH.get());

	public AnimationSequence thryssarynLuthSequence = new AnimationSequence(
			new int[] {40, 1040, 40},
			standing2playingLuth,
			playingLuthWarden,
			playingLuth2standing
	);

	public ThryssarynEntity(EntityType<? extends AbstractThryssaryn> entity, Level level) {
		super(entity, level);
		this.brain = new Brain(this);
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
		if (!this.level().isClientSide) {
			if (this.getEyesColor() == 0) {
				this.setEyesColor(BASE_EYE_COLORS[this.random.nextInt(BASE_EYE_COLORS.length)]);
			}
		}
		thryssarynLuthSequence.tick(this.tickCount);
		if (!thryssarynLuthSequence.isRunning()) {
			this.setPlayingLuth(false);
		}
		this.brain.tick();
	}

	@Override
	public void updateDynamicGameEventListener(
			@NotNull BiConsumer<DynamicGameEventListener<?>, ServerLevel> consumer) {
		Level level = this.level();
		if (level instanceof ServerLevel serverlevel) {
			consumer.accept(this.brain.getHearing().dynamicGameEventListener, serverlevel);
		}
	}

	public @Nullable ThryssarynEntity getBreedOffspring(@NotNull ServerLevel serverLevel,
                                                        @NotNull ThryssarynEntity otherParent) {
		ThryssarynEntity child = ModEntities.THRYSSARYN.get().create(serverLevel);
		if (child == null) return null;
		child.setBaby(true);
		child.setEyesColor(ColorUtils.lerpColor(this.getEyesColor(),
				(otherParent).getEyesColor(), 0.5F));
		return child;
	}

	@Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder entityData) {
        super.defineSynchedData(entityData);
		entityData.define(IS_PLAYING_LUTH, false);
    }
	
	@Override
	public boolean canAttackType(@NotNull EntityType<?> entity) {
		return super.canAttackType(entity);
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isPlayingLuth", this.isPlayingLuth());
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setPlayingLuth(compound.getBoolean("isPlayingLuth"));
	}

	@Override
	public @NotNull InteractionResult interactAt(@NotNull Player player, @NotNull Vec3 vec,
												 @NotNull InteractionHand hand) {
		if (player.isShiftKeyDown() && this.level().isClientSide) {
			net.minecraft.client.Minecraft.getInstance().setScreen(
					new wardentools.entity.thryssaryn.client.debug.ThryssarynDebugScreen(this.getId())
			);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	public boolean isPlayingLuth() {return this.entityData.get(IS_PLAYING_LUTH);}

	public void setPlayingLuth(boolean isPlayingLuth) {this.entityData.set(IS_PLAYING_LUTH, isPlayingLuth);}


	@Override
	public boolean checkSpawnRules(@NotNull LevelAccessor level, @NotNull MobSpawnType spawnType) {
    	return true;
    }
	
    public static boolean canSpawn(EntityType<ThryssarynEntity> entityType, ServerLevelAccessor level,
                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return true;
    }

	protected float getSoundVolume() {
		return 1.0F;
	}

}

