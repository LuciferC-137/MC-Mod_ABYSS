package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.entity.utils.goal.LightCandleGoal;
import wardentools.misc.Crystal;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrystalGolemEntity extends PathfinderMob {
	private static final EntityDataAccessor<Integer> CRYSTAL =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> STATE =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);

	private final List<BlockPos> unlitCandles = new ArrayList<>();
    private static final int CANDLE_CHECKS_PER_TICKS = 5;
	private static final int CANDLE_RADIUS = 10;
	private static final int CANDLE_HEIGHT = 4;

	public final AnimationState deactivatedState1 = new AnimationState();
	public final AnimationState deactivatedState2 = new AnimationState();
	public final AnimationState lightingState = new AnimationState();
	public final AnimationState randomLookAround = new AnimationState();
	public final AnimationState reactivateFrom2 = new AnimationState();

	@Nullable
	private BlockPos restPos = null;
	private int currentCheckIndex = 0;
	private static final List<BlockPos> relativePosToCheck = new ArrayList<>();

	private static final int LOOK_AROUND_DURATION = 77;
	private static final int LOOK_TICK_PER_PROB = 60;
	private int lookAroundTickDown = 0;

	private static final int TURNING_ON_DURATION = 110;
	private int turningOnTick = 0;

	static {
		for (int x = -CANDLE_RADIUS; x <= CANDLE_RADIUS; x++) {
			for (int y = 0; y <= CANDLE_HEIGHT; y++) {
				for (int z = -CANDLE_RADIUS; z <= CANDLE_RADIUS; z++) {
					relativePosToCheck.add(new BlockPos(x, y, z));
				}
			}
		}
	}

	public CrystalGolemEntity(EntityType<? extends PathfinderMob> entity, Level level) {
		super(entity, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new LightCandleGoal(this));
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.1D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(CRYSTAL, Crystal.getDefault().getIndex());
		builder.define(STATE, GolemState.DEACTIVATED_2.getId());
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide) {
			if (this.restPos == null) {
				this.restPos = this.getOnPos();
			}
			for (int i = 0; i < CANDLE_CHECKS_PER_TICKS; i++) {
				if (isCurrentCandlePosUnlit()) {
					this.addUnlitCandle(this.getCurrentCheckBlockPos());
				}
				this.nextPosCheck();
			}
			if (LightCandleGoal.wouldLikeToStart(this) && !this.isActive()) {
				this.setState(GolemState.TURNING_ON);
			}
			if (this.getState() == GolemState.TURNING_ON) {
				if (this.turningOnTick == 0) {
					this.turningOnTick = TURNING_ON_DURATION;
				} else {
					this.turningOnTick--;
					if (this.turningOnTick == 0) {
						this.setState(GolemState.TRAVELING);
					}
				}
			}
		} else {
			this.deactivatedState1.animateWhen(
					this.getState() == GolemState.DEACTIVATED_1, tickCount);
			this.deactivatedState2.animateWhen(
					this.getState() == GolemState.DEACTIVATED_2, tickCount);
			this.lightingState.animateWhen(
					this.getState() == GolemState.LIGHT, tickCount);
			this.randomLookAround.animateWhen(this.lookAroundTickDown > 0, tickCount);
			this.reactivateFrom2.animateWhen(
					this.getState() == GolemState.TURNING_ON, tickCount);

			this.animationTickDownAndRandomTrigger();
		}
		super.tick();
	}

	private void animationTickDownAndRandomTrigger() {
		if (this.lookAroundTickDown > 0) {
			this.lookAroundTickDown--;
		}
		if (this.isActive()) {
			if (this.level().getRandom().nextInt(LOOK_TICK_PER_PROB) == 0) {
				this.lookAroundTickDown = LOOK_AROUND_DURATION;
			}
		}
	}

	public List<BlockPos> getUnlitCandles() {
		return unlitCandles;
	}

	public void addUnlitCandle(BlockPos pos) {
		if (!unlitCandles.contains(pos)) {
			unlitCandles.add(pos.immutable());
		}
	}

	public void pollUnlitCandle() {
		if (!unlitCandles.isEmpty()) {
			unlitCandles.removeFirst();
		}
	}

	public BlockPos peekUnlitCandle() {
		return unlitCandles.isEmpty() ? BlockPos.ZERO : unlitCandles.getFirst();
	}

	public boolean isActive() {
		return this.getState() != GolemState.DEACTIVATED_1
				&& this.getState() != GolemState.DEACTIVATED_2
				&& this.getState() != GolemState.TURNING_ON;
	}

	public BlockPos getRestPos() {return this.restPos == null ? BlockPos.ZERO : this.restPos;}

	public void setState(GolemState state) {this.entityData.set(STATE, state.getId());}

	public GolemState getState() {return GolemState.fromId(this.entityData.get(STATE));}

	private void nextPosCheck() {
		this.currentCheckIndex = (this.currentCheckIndex + 1) % relativePosToCheck.size();
	}

	private boolean isCurrentCandlePosUnlit() {
		if (this.restPos == null) return false;
		BlockPos pos = this.getCurrentCheckBlockPos();
		BlockState state = this.level().getBlockState(pos);
		if (state.is(BlockTags.CANDLES) && state.hasProperty(CandleBlock.LIT)) {
			return !state.getValue(CandleBlock.LIT);
		}
		return false;
	}

	public BlockPos getCurrentCheckBlockPos() {
		if (restPos == null) return BlockPos.ZERO;
		BlockPos relativePos = relativePosToCheck.get(this.currentCheckIndex);
		return restPos.offset(relativePos.getX(), relativePos.getY(), relativePos.getZ());
	}

	public void lightCandlePos() {
		if (this.level().isClientSide) return;
		if (!this.unlitCandles.isEmpty()) {
			BlockState state = this.level().getBlockState(this.unlitCandles.getFirst());
			if (!state.getValue(CandleBlock.LIT)) {
				this.level().setBlock(this.unlitCandles.getFirst(),
						state.setValue(CandleBlock.LIT, true), Block.UPDATE_ALL);
			}
		}
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
		tag.putInt("state", this.getState().getId());
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		if (tag.contains("crystal_type")) {
			this.setCrystalType(Crystal.fromIndex(tag.getInt("crystal_type")));
		}
		if (tag.contains("state")) {
			this.setState(GolemState.fromId(tag.getInt("state")));
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

	public enum GolemState {
		DEACTIVATED_1(0),
		DEACTIVATED_2(1),
		LIGHT(2),
		TRAVELING(3),
		TURNING_ON(4);

		private final int id;
		GolemState(int id) { this.id = id; }
		public int getId() { return id; }

		public static GolemState fromId(int id) {
			for (GolemState s : values()) {
				if (s.id == id) return s;
			}
			return DEACTIVATED_1;
		}
	}

}