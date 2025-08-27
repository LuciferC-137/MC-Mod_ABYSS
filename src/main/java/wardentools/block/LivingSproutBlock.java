package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.LivingSproutBlockEntity;
import wardentools.entity.ModEntities;
import wardentools.entity.custom.ParasyteEntity;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.LivingSproutBurstPacket;

public class LivingSproutBlock extends Block implements EntityBlock {
    static {
        PHASE = BlockStateProperties.SCULK_SENSOR_PHASE;
        TRIGGERED = BlockStateProperties.TRIGGERED;
    }

    public static final int ACTIVE_TICKS = 60;
    public static final EnumProperty<SculkSensorPhase> PHASE;
    public static final BooleanProperty TRIGGERED;

    public LivingSproutBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(((this.stateDefinition.any())
                .setValue(PHASE, SculkSensorPhase.INACTIVE))
                .setValue(TRIGGERED, false));

    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
                                                @NotNull BlockState blockState) {
        return new LivingSproutBlockEntity(blockPos, blockState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(PHASE).add(TRIGGERED);
    }

    public static SculkSensorPhase getPhase(BlockState state) {
        return state.getValue(PHASE);
    }

    public static boolean canActivate(BlockState state) {
        return getPhase(state) == SculkSensorPhase.INACTIVE;
    }

    public static void activate(@NotNull Level level, @NotNull BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (canActivate(state)) {
            level.playSound(null, pos, SoundEvents.SCULK_CLICKING, SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.2F + 0.8F);
            level.setBlock(pos, state.setValue(PHASE, SculkSensorPhase.ACTIVE)
                    .setValue(TRIGGERED, true), Block.UPDATE_ALL);
            level.scheduleTick(pos, state.getBlock(), ACTIVE_TICKS);
        }
    }

    public static void deactivate(@NotNull Level level, @NotNull BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (getPhase(state) == SculkSensorPhase.ACTIVE) {
            level.setBlock(pos, state.setValue(PHASE, SculkSensorPhase.COOLDOWN)
                    .setValue(TRIGGERED, false), Block.UPDATE_ALL);
            level.scheduleTick(pos, state.getBlock(), ACTIVE_TICKS);
        } else if (getPhase(state) == SculkSensorPhase.COOLDOWN) {
            level.setBlock(pos, state.setValue(PHASE, SculkSensorPhase.INACTIVE)
                    .setValue(TRIGGERED, false), Block.UPDATE_ALL);
        }
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level,
                        @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.tick(state, level, pos, random);
        if (getPhase(state) == SculkSensorPhase.INACTIVE) return;
        if (getPhase(state) == SculkSensorPhase.COOLDOWN) {
            level.playSound(null, pos, SoundEvents.SCULK_CLICKING_STOP, SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.2F + 0.8F);
        }
        deactivate(level, pos);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state,
                       @NotNull Entity entity) {
        super.stepOn(level, pos, state, entity);
        if (entity instanceof Player player && !player.isCreative()) {
            burst(level, pos);
        } else {
            activate(level, pos);
        }
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level,
                            @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.animateTick(state, level, pos, random);
    }

    public static void burst(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            level.players().stream().filter(player -> player.distanceToSqr(pos.getCenter()) < 32)
                    .forEach((player) -> {
                        PacketHandler.sendToClient(new LivingSproutBurstPacket(pos),
                                (ServerPlayer)player);
                    });
            int parasyteNumber = level.random.nextInt(2, 4);
            for (int i = 0; i < parasyteNumber; i++) {
                ParasyteEntity parasyte = new ParasyteEntity(ModEntities.PARASYTE.get(), level);
                parasyte.setPos(pos.getCenter());
                parasyte.setHealth(parasyte.getMaxHealth());
                parasyte.setDeltaMovement((level.random.nextDouble() - 0.5) * 0.5,
                        0.5, (level.random.nextDouble() - 0.5) * 0.5);
                level.addFreshEntity(parasyte);
            }
        }
    }

    @Override
    protected void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level,
                                   @NotNull BlockPos pos, @NotNull ItemStack stack, boolean silkTouch) {
        super.spawnAfterBreak(state, level, pos, stack, silkTouch);
        burst(level, pos);
    }


    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> entityType) {
        return level.isClientSide ? null : (level0, pos, state0, blockEntity)
                -> VibrationSystem.Ticker.tick(level0,
                ((LivingSproutBlockEntity)blockEntity).getVibrationData(),
                ((LivingSproutBlockEntity)blockEntity).getVibrationUser());
    }
}
