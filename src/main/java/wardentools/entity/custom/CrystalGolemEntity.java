package wardentools.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.entity.ModEntities;
import wardentools.entity.utils.CrystalGolemNavigation;
import wardentools.entity.utils.goal.CrystalGolemAttackGoal;
import wardentools.entity.utils.goal.LightCandleGoal;
import wardentools.misc.Crystal;
import wardentools.particle.ModParticleUtils;
import wardentools.particle.options.GlyphParticleOptions;
import wardentools.particle.options.ShineParticleOptions;
import wardentools.sounds.ModSounds;
import wardentools.utils.SaveUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CrystalGolemEntity extends PathfinderMob {
	private static final EntityDataAccessor<Integer> CRYSTAL =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> STATE =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<BlockPos> CURRENT_CANDLE_TARGET =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Integer> LASER_TICK =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> SYNC_Y_ROT =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Boolean> GRIEF =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SCULK =
			SynchedEntityData.defineId(CrystalGolemEntity.class, EntityDataSerializers.BOOLEAN);

	private static final float NEARBY_ALLIED_RANGE = 20.0F;

	private final List<BlockPos> unlitCandles = new ArrayList<>();
    private static final int CANDLE_CHECKS_PER_TICKS = 5;
	private static final int CANDLE_RADIUS = 10;
	private static final int CANDLE_HEIGHT = 4;
	private static final int CHANCE_TO_UNLIT_CANDLE = 400;

	public final AnimationState deactivatedState1 = new AnimationState();
	public final AnimationState deactivatedState2 = new AnimationState();
	public final AnimationState lightingState = new AnimationState();
	public final AnimationState randomLookAround = new AnimationState();
	public final AnimationState reactivateFrom2 = new AnimationState();
	public final AnimationState reactivateFrom1 = new AnimationState();

	@Nullable
	private BlockPos golemStonePos = null;
	private int currentCheckIndex = 0;
	private static final List<BlockPos> relativePosToCheck = new ArrayList<>();

	private static final int LOOK_AROUND_DURATION = 77;
	private static final int LOOK_TICK_PER_PROB = 60;
	private int lookAroundTickDown = 0;

	private static final int TURNING_ON_DURATION = 110;
	private int turningOnTick = 0;
	private boolean flickerState = false;
	private int nextFlickerScheduledTick = 0;

	private static final int FADING_DURATION = 60;
	private int turningOffTick = 0;

	private static final float PARTICLE_SPEED = 0.2F;

	private GolemState previousState = GolemState.DEACTIVATED_2;
	private GolemState previousDeactivatedState = GolemState.DEACTIVATED_2;
	private static final int MAX_TIME_AWAKE_WITHOUT_GOAL = 1200;
	private int timeAwakeWithoutGoal = 0;

	public static final int LASER_DURATION = 80;
	private static final float LASER_POSITION_MIN = 1.875F;
	private static final float LASER_POSITION_MAX = 2.25F;

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
		this.navigation = new CrystalGolemNavigation(this, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CrystalGolemAttackGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new LightCandleGoal(this));

		this.targetSelector.addGoal(1,
				new NearestAttackableTargetGoal<Player>(this, Player.class, true) {
					@Override
					public boolean canUse() {
						return super.canUse() && CrystalGolemEntity.this.hasGrief();
					}
				});
	}
	
	public static AttributeSupplier.Builder createAttribute(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.12D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(CRYSTAL, Crystal.getDefault().getIndex());
		builder.define(STATE, GolemState.DEACTIVATED_2.getId());
		builder.define(CURRENT_CANDLE_TARGET, BlockPos.ZERO);
		builder.define(LASER_TICK, 0);
		builder.define(SYNC_Y_ROT, 0F);
		builder.define(GRIEF, false);
		builder.define(SCULK, false);
	}

	@Override
	public void tick() {
		if (!this.level().isClientSide) {
			this.handleTimeAwake();
			this.handleCandleCheck();
			this.laserTick();
			if (LightCandleGoal.wouldLikeToStart(this) && !this.isActive()) {
				if (!LightCandleGoal.surroundingGolemHasSameTarget(this)) {
					this.setState(GolemState.TURNING_ON);
				}
			}
		} else {
			this.handleAnimationStates();
			this.animationTickDownAndRandomTrigger();
			if (this.getState() == GolemState.LIGHT) {
				this.lightingCandleParticleEffect();
			}
			if (this.getLaserTick() > 0) {
				this.laserChargingParticleEffect();
			}
		}
		this.updateSwingTime();
		this.handleTurningOffTick();
		this.handleTurningOnTick();
		super.tick();
	}

	private void laserTick() {
		if (this.getState() == GolemState.CHARGING_LASER) {
			if (this.getLaserTick() == 0) {
				this.setLaserTick(LASER_DURATION);
				this.makeSound(ModSounds.LASER_CHARGE.get());
			} else {
				this.setLaserTick(this.getLaserTick() - 1);
				if (this.getLaserTick() == 0 && !this.level().isClientSide()) {
					this.shootLaser();
					this.setState(GolemState.TRAVELING);
				}
			}
		} else {
			this.setLaserTick(0);
		}
	}

	public void shootLaser() {
		CrystalLaserEntity laser = new CrystalLaserEntity(ModEntities.CRYSTAL_LASER.get(), this.level());
		laser.setPos(this.getLaserFirePosition());
		laser.setCrystalType(this.getCrystal());
		laser.buildCrystalChain();
		this.level().addFreshEntity(laser);
	}

	private void handleTimeAwake() {
		if (this.isActive() && !LightCandleGoal.wouldLikeToStart(this) && this.getTarget() == null) {
			this.timeAwakeWithoutGoal++;
			if (this.timeAwakeWithoutGoal > MAX_TIME_AWAKE_WITHOUT_GOAL) {
				this.setState(GolemState.DEACTIVATED_1);
				this.timeAwakeWithoutGoal = 0;
			}
		} else {
			this.timeAwakeWithoutGoal = 0;
		}
	}

	private void handleCandleCheck() {
		if (this.golemStonePos == null) {
			this.golemStonePos = this.getOnPos();
		}
		for (int i = 0; i < CANDLE_CHECKS_PER_TICKS; i++) {
			if (isCurrentCandlePosUnlit()) {
				this.addUnlitCandle(this.getCurrentCheckBlockPos());
			} else if (isCurrentCandlePosLit()) {
				if (this.level().getRandom().nextInt(CHANCE_TO_UNLIT_CANDLE) == 0) {
					this.turnOffCandleAtPos(this.getCurrentCheckBlockPos());
				}
			}

			this.nextPosCheck();
		}
	}

	private void handleTurningOnTick() {
		// Turning On Tick
		if (this.turningOnTick > 0) {
			this.turningOnTick--;
			if (this.turningOnTick == 0 && !this.level().isClientSide()) {
				this.setState(GolemState.TRAVELING);
			}
		}
		// Flickering Logic
		if (this.turningOnTick > 0) {
			if (nextFlickerScheduledTick <= this.tickCount) {
				this.flickerState = !this.flickerState;

				if (this.flickerState) {
					int flickerNumber = (TURNING_ON_DURATION - this.turningOnTick) / 5;
					int onDuration = level().getRandom().nextInt(1, 3)
							+ flickerNumber * level().getRandom().nextInt(1, 3);
					this.nextFlickerScheduledTick = this.tickCount + onDuration;
				} else {
					this.nextFlickerScheduledTick = this.tickCount + 3;
				}
			}
		} else {
			this.flickerState = this.isActive() || this.turningOffTick > 0;
		}
	}

	private void handleTurningOffTick() {
		if (this.turningOffTick > 0) {
			this.turningOffTick--;
		}
	}

	private void handleAnimationStates() {
		this.deactivatedState1.animateWhen(
				this.getState() == GolemState.DEACTIVATED_1, tickCount);
		this.deactivatedState2.animateWhen(
				this.getState() == GolemState.DEACTIVATED_2, tickCount);
		this.lightingState.animateWhen(
				this.getState() == GolemState.LIGHT, tickCount);
		this.randomLookAround.animateWhen(this.lookAroundTickDown > 0
				&& this.isActive(), tickCount);

		if (this.getState() == GolemState.DEACTIVATED_2
				|| this.getState() == GolemState.DEACTIVATED_1) {
			this.previousDeactivatedState = this.getState();
		}
		this.reactivateFrom2.animateWhen(this.previousDeactivatedState == GolemState.DEACTIVATED_2
				&& this.getState() == GolemState.TURNING_ON, tickCount);
		this.reactivateFrom1.animateWhen(this.previousDeactivatedState == GolemState.DEACTIVATED_1
						&& this.getState() == GolemState.TURNING_ON, tickCount);
	}

	private void animationTickDownAndRandomTrigger() {
		if (this.lookAroundTickDown > 0) {
			this.lookAroundTickDown--;
		}
		if (this.isActive() && this.getState() != GolemState.CHARGING_LASER &&
				!this.hasGrief()) {
			if (this.level().getRandom().nextInt(LOOK_TICK_PER_PROB) == 0) {
				this.lookAroundTickDown = LOOK_AROUND_DURATION;
			}
		}
	}

	public void laserChargingParticleEffect() {
		if (this.level().isClientSide) {
			Vec3 emissionPos = this.position()
					.add(0, this.getLaserChargingPosition(), 0);
			RandomSource random = this.level().getRandom();
			for (int i = 0; i < (LASER_DURATION - this.getLaserTick()) / 7; i++) {
				Vec3 direction = emissionPos.subtract(emissionPos
								.offsetRandom(random, 1F)).normalize().scale(0.3F);
				ModParticleUtils.addClientParticle(this.level(),
						new ShineParticleOptions(direction, this.getCrystal().getColorARGB()),
						emissionPos, direction);
			}
			if (this.getLaserTick() == LASER_DURATION - 3) {
				ModParticleUtils.particleCircle(this.level(),
						new GlyphParticleOptions(Vec3.ZERO, this.getCrystal().getColorARGB()),
						emissionPos, 0.5F, 15,
						new Vec3(this.getLookAngle().x, 0, this.getLookAngle().z),
						new Vec3(0, 0.0065F, 0));
			}
		}
	}

	public void lightingCandleParticleEffect() {
		if (this.level().isClientSide) {
			BlockPos targetPos = this.getCurrentCandleTarget();
			if (!targetPos.equals(BlockPos.ZERO)) {
				Vec3 target = targetPos.getCenter();
				target = target.offsetRandom(this.level().getRandom(),
						0.1F);
				target.add(0, -0.1F, 0);
				Vec3 emissionPos = this.position().add(0, 0.7F, 0);
				emissionPos = emissionPos.offsetRandom(this.level().getRandom(),
						0.2F);
				// Adjust target slightly away from emission position
				target = target.add(target.subtract(emissionPos).scale(0.15F));
				emissionPos = emissionPos.add(target.subtract(emissionPos).scale(0.15F));
				ModParticleUtils.addClientParticle(this.level(),
						new ShineParticleOptions(target, this.getCrystal().getColorARGB()),
						emissionPos, target, PARTICLE_SPEED);
			}
		}
	}

	public int getFadedColor() {
		if (this.turningOffTick > 0) {
			float progress = (FADING_DURATION - this.turningOffTick) / (float)FADING_DURATION;
			int alpha = (int)(255 * (1.0f - progress));
			return this.getCrystal().getColorARGB() & 0x00FFFFFF | (alpha << 24);
		}
		return this.getCrystal().getColorARGB();
	}

	public float getLaserChargingPosition() {
		return ((float)LASER_DURATION - (float)this.getLaserTick())
				/ (float)LASER_DURATION * (LASER_POSITION_MAX - LASER_POSITION_MIN) + LASER_POSITION_MIN;
	}

	public Vec3 getLaserFirePosition() {
		return this.position().add(0F, LASER_POSITION_MAX, 0F);
	}

	public boolean getFlickerState() {return this.flickerState;	}

	public void onTurningOff() {
		this.turningOffTick = FADING_DURATION;
		this.nextFlickerScheduledTick = 0;
		this.turningOnTick = 0;
		this.setGrief(false);
		this.makeSound(ModSounds.CRYSTAL_GOLEM_TURNING_OFF.get());
	}

	public void onTurningOn() {
		this.turningOnTick = TURNING_ON_DURATION;
		this.turningOffTick = 0;
		this.flickerState = true;
		this.nextFlickerScheduledTick = this.tickCount + 1;
		this.makeSound(ModSounds.CRYSTAL_GOLEM_TURNING_ON.get());
	}

	public List<BlockPos> getUnlitCandles() {
		return unlitCandles;
	}

	public void addUnlitCandle(BlockPos pos) {
		if (!unlitCandles.contains(pos)) {
			if (unlitCandles.isEmpty()) {
				this.setCurrentCandleTarget(pos.immutable());
			}
			unlitCandles.add(pos.immutable());
		}
	}

	public void pollUnlitCandle() {
		if (!unlitCandles.isEmpty()) {
			unlitCandles.removeFirst();
			if (!unlitCandles.isEmpty()) {
				this.setCurrentCandleTarget(unlitCandles.getFirst());
			} else {
				this.setCurrentCandleTarget(BlockPos.ZERO);
			}
		}
	}

	public BlockPos peekUnlitCandle() {
		return unlitCandles.isEmpty() ? BlockPos.ZERO : unlitCandles.getFirst();
	}

	public float distanceToTarget() {
		BlockPos targetPos = this.peekUnlitCandle();
		if (targetPos != BlockPos.ZERO) {
			Vec3 target = targetPos.getCenter();
			return (float) this.position().distanceTo(target);
		}
		return Float.MAX_VALUE;
	}

	public List<CrystalGolemEntity> getNearbyGolems() {
		if (this.level().isClientSide) return List.of();
		AABB box = this.getBoundingBox().inflate(NEARBY_ALLIED_RANGE, 4.0D, NEARBY_ALLIED_RANGE);
		return this.level().getEntitiesOfClass(CrystalGolemEntity.class, box, golem ->
				golem != this && golem.isAlive());
	}

	public boolean isActive() {
		return !isDeactivated(this.getState())
				&& this.getState() != GolemState.TURNING_ON;
	}

	public static boolean isDeactivated(GolemState state) {
		return state == GolemState.DEACTIVATED_1
				|| state == GolemState.DEACTIVATED_2;
	}

	public BlockPos getGolemStonePos() {return this.golemStonePos == null ? BlockPos.ZERO : this.golemStonePos;}

	public void setCurrentCandleTarget(BlockPos pos) {this.entityData.set(CURRENT_CANDLE_TARGET, pos);}

	public BlockPos getCurrentCandleTarget() {return this.entityData.get(CURRENT_CANDLE_TARGET);}

	public void setLaserTick(int tick) {this.entityData.set(LASER_TICK, tick);}

	public int getLaserTick() {return this.entityData.get(LASER_TICK);}

	public void setSyncedYRot(float rot) {this.entityData.set(SYNC_Y_ROT, rot);}

	public float getSyncedYRot() {return this.entityData.get(SYNC_Y_ROT);}

	public void setState(GolemState state) {this.entityData.set(STATE, state.getId());}

	public GolemState getState() {return GolemState.fromId(this.entityData.get(STATE));}

	public boolean hasGrief() {return this.entityData.get(GRIEF);}

	public void setGrief(boolean grief) {this.entityData.set(GRIEF, grief);}

	public boolean hasSculk() {return this.entityData.get(SCULK);}

	public void setSculk(boolean sculk) {this.entityData.set(SCULK, sculk);}

	public void forceYRot(float rot) {
		this.setYRot(rot);
		this.yRotO = rot;
		this.yBodyRot = rot;
		this.yBodyRotO = rot;
		this.yHeadRot = rot;
		this.yHeadRotO = rot;
	}

	private void nextPosCheck() {
		this.currentCheckIndex = (this.currentCheckIndex + 1) % relativePosToCheck.size();
	}

	private boolean isCurrentCandlePosUnlit() {
		if (this.golemStonePos == null) return false;
		BlockPos pos = this.getCurrentCheckBlockPos();
		BlockState state = this.level().getBlockState(pos);
		if (state.is(BlockTags.CANDLES) && state.hasProperty(CandleBlock.LIT)) {
			return !state.getValue(CandleBlock.LIT);
		}
		return false;
	}

	private boolean isCurrentCandlePosLit() {
		if (this.golemStonePos == null) return false;
		BlockPos pos = this.getCurrentCheckBlockPos();
		BlockState state = this.level().getBlockState(pos);
		if (state.is(BlockTags.CANDLES) && state.hasProperty(CandleBlock.LIT)) {
			return state.getValue(CandleBlock.LIT);
		}
		return false;
	}

	public BlockPos getCurrentCheckBlockPos() {
		if (golemStonePos == null) return BlockPos.ZERO;
		BlockPos relativePos = relativePosToCheck.get(this.currentCheckIndex);
		return golemStonePos.offset(relativePos.getX(), relativePos.getY(), relativePos.getZ());
	}

	public void lightCandleAtPos() {
		if (this.level().isClientSide) return;
		if (!this.unlitCandles.isEmpty()) {
			BlockState state = this.level().getBlockState(this.unlitCandles.getFirst());
			if (!state.getValue(CandleBlock.LIT)) {
				this.level().setBlock(this.unlitCandles.getFirst(),
						state.setValue(CandleBlock.LIT, true), Block.UPDATE_ALL);
				this.playSound(SoundEvents.FIRECHARGE_USE, 0.2F, 1.0F);
			}
		}
	}

	public void turnOffCandleAtPos(BlockPos pos) {
		if (this.level().isClientSide) return;
		BlockState state = this.level().getBlockState(pos);
		if (state.is(BlockTags.CANDLES) && state.hasProperty(CandleBlock.LIT)
				&& state.getValue(CandleBlock.LIT)) {
			this.level().setBlock(pos,
					state.setValue(CandleBlock.LIT, false), Block.UPDATE_ALL);
		}
	}

	public Crystal getCrystal() {
		return Crystal.fromIndex(this.entityData.get(CRYSTAL));
	}

	public void setCrystalType(Crystal crystal) {
		this.entityData.set(CRYSTAL, crystal.getIndex());
	}

	public void setGolemStonePos(@Nullable BlockPos pos) {
		this.golemStonePos = pos;
	}

	public int finalizeReturnToStone() {
		if (this.level().isClientSide) return 30;
		if (this.golemStonePos == null) return 1;
		BlockState state = this.level().getBlockState(this.golemStonePos);
		if (!state.is(BlockRegistry.GOLEM_STONE.get())) return 1;

		this.moveTo(this.golemStonePos.getCenter().add(0, 0.5F, 0));

		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
		this.syncRotToFacing(facing);
		return 30;
	}

	private void syncRotToFacing(Direction facing) {
		if (this.level().isClientSide) return;

		float yaw;
		switch (facing) {
			case NORTH -> yaw = 180f;
			case SOUTH -> yaw = 0f;
			case WEST  -> yaw = 90f;
			case EAST  -> yaw = -90f;
			default    -> yaw = 0f;
		}

		this.setYRot(yaw);
		this.setXRot(0f);
		this.yHeadRot = yaw;
		this.yHeadRotO = yaw;
		this.yBodyRot = yaw;

		ServerLevel server = (ServerLevel) this.level();
		byte yawByte = (byte)((int)(yaw * 256.0F / 360.0F));
		byte pitchByte = 0;

		server.getChunkSource().broadcast(this,
				new ClientboundRotateHeadPacket(this, yawByte));
		server.getChunkSource().broadcast(this,
				new ClientboundMoveEntityPacket.Rot(this.getId(), yawByte, pitchByte, this.onGround()));
	}

	public void choseViolence() {
		this.setGrief(true);
		this.setState(GolemState.TURNING_ON);
		this.playSound(ModSounds.CRYSTAL_GOLEM_AGGRESSION.get(), this.getSoundVolume(),
				this.level().getRandom().nextFloat() * 0.2F + 0.9F);
	}

	private void alertOthers() {
		for (CrystalGolemEntity golem : this.getNearbyGolems()) {
			if (golem != this && !golem.isActive()) {
				golem.choseViolence();
			}
		}
	}

	@Override
	public boolean hurt(@NotNull DamageSource source, float damage) {
		if (source.getEntity() instanceof Player player){
			if (player.isCreative()) {
				return super.hurt(source, damage);
			}
		}
		if (!this.hasGrief()) {
			this.choseViolence();
			this.alertOthers();
			this.setState(GolemState.TRAVELING);
		}
		return super.hurt(source, damage);
	}

	@Override
	public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key.equals(STATE)) {
			GolemState newState = this.getState();
			if (newState != this.previousState) {
				if (isDeactivated(newState) && !isDeactivated(this.previousState)) {
					this.onTurningOff();
				}
				if (newState == GolemState.TURNING_ON && isDeactivated(this.previousState)) {
					this.onTurningOn();
				}
				this.previousState = newState;
			}
		}
		if (key.equals(SYNC_Y_ROT)) {
			float newRot = this.getSyncedYRot();
			if (newRot != this.getYRot()) {
				this.forceYRot(newRot);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		tag.putInt("crystal_type", entityData.get(CRYSTAL));
		tag.putInt("state", this.getState().getId());
		tag.putInt("laser_tick", this.getLaserTick());
		if (this.golemStonePos != null) {
			SaveUtils.putBlockPos(tag, "golem_stone_pos", this.golemStonePos);
		}
		tag.putBoolean("has_grief", this.hasGrief());
		tag.putBoolean("has_sculk", this.hasSculk());
		super.addAdditionalSaveData(tag);
	}

	@Override
	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		if (tag.contains("crystal_type")) {
			this.setCrystalType(Crystal.fromIndex(tag.getInt("crystal_type")));
		}
		if (tag.contains("state")) {
			this.previousState = GolemState.fromId(tag.getInt("state"));
			this.setState(this.previousState);
		}
		if (tag.contains("laser_tick")) {
			this.setLaserTick(tag.getInt("laser_tick"));
		}
		if (tag.contains("golem_stone_pos")) {
			this.golemStonePos = SaveUtils.readBlockPos(tag, "golem_stone_pos");
		}
		if (tag.contains("has_grief")) {
			this.setGrief(tag.getBoolean("has_grief"));
		}
		if (tag.contains("has_sculk")) {
			this.setSculk(tag.getBoolean("has_sculk"));
		}
		super.readAdditionalSaveData(tag);
		this.flickerState = this.isActive();
	}

	public static boolean canSpawn(EntityType<CrystalGolemEntity> entityType, ServerLevelAccessor level,
								   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getBlockState(pos).isAir();
    }

	@Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
		this.playSound(ModSounds.CRYSTAL_GOLEM_STEP.get(), this.getSoundVolume(),
				this.level().getRandom().nextFloat() * 0.2F + 0.9F);
    }

	@Override
	public void playAmbientSound() {
		if (this.isActive()) super.playAmbientSound();
	}

	@Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.CRYSTAL_GOLEM_AMBIENT.get();
    }

	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
		return ModSounds.CRYSTAL_GOLEM_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {return ModSounds.CRYSTAL_GOLEM_DEATH.get();}

	protected float getSoundVolume() {
		return 0.4F;
	}

	public enum GolemState {
		DEACTIVATED_1(0),
		DEACTIVATED_2(1),
		LIGHT(2),
		TRAVELING(3),
		TURNING_ON(4),
		CHARGING_LASER(5);

		private final int id;
		GolemState(int id) { this.id = id; }
		public int getId() { return id; }

		public static GolemState fromId(int id) {
			for (GolemState s : values()) {
				if (s.id == id) return s;
			}
			return TRAVELING;
		}
	}

}