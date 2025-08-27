package wardentools.blockentity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import wardentools.block.LivingSproutBlock;
import wardentools.sounds.ModSounds;

import javax.annotation.Nullable;

public class LivingSproutBlockEntity extends BlockEntity implements GameEventListener.Provider<VibrationSystem.Listener>, VibrationSystem {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int MAX_TRIGGERED_IN_A_ROW = 2;
    private static final int COOLDOWN = 280;

    private VibrationSystem.Data vibrationData;
    private final VibrationSystem.Listener vibrationListener;
    private final VibrationSystem.User vibrationUser;

    private int triggeredInARow = 0;
    private int lastTimeHeard = 0;
    
    public LivingSproutBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
        this.vibrationUser = this.createVibrationUser();
        this.vibrationData = new VibrationSystem.Data();
        this.vibrationListener = new VibrationSystem.Listener(this);
    }

    public LivingSproutBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.LIVING_SPROUT_BLOCK_ENTITY.get(), pos, state);
    }

    public VibrationSystem.User createVibrationUser() {
        return new LivingSproutBlockEntity.VibrationUser(this.getBlockPos());
    }

    @Override
    public @NotNull Listener getListener() {return this.vibrationListener;}

    @Override
    public @NotNull Data getVibrationData() {return this.vibrationData;}

    @Override
    public @NotNull User getVibrationUser() {return this.vibrationUser;}

    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        RegistryOps<Tag> context = provider.createSerializationContext(NbtOps.INSTANCE);
        if (tag.contains("listener", 10)) {
            Data.CODEC.parse(context, tag.getCompound("listener")).resultOrPartial((error) -> {
                LOGGER.error("Failed to parse vibration listener for Living Sprout: '{}'", error);
            }).ifPresent((data) -> {
                this.vibrationData = data;
            });
        }
    }

    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        RegistryOps<Tag> context = provider.createSerializationContext(NbtOps.INSTANCE);
        Data.CODEC.encodeStart(context, this.vibrationData).resultOrPartial((error) -> {
            LOGGER.error("Failed to encode vibration listener for Living Sprout: '{}'", error);
        }).ifPresent((tag1) -> {
            tag.put("listener", tag1);
        });
    }

    public void trigger(int timeTick) {
        if (this.level == null) return;
        if (timeTick - this.lastTimeHeard < COOLDOWN) {
            this.triggeredInARow ++;
        } else {
            this.triggeredInARow = 1;
        }
        if (triggeredInARow >= MAX_TRIGGERED_IN_A_ROW) {
            this.level.destroyBlock(this.getBlockPos(), true);
        }
        this.lastTimeHeard = timeTick;
    }

    protected class VibrationUser implements VibrationSystem.User {
        protected final BlockPos blockPos;
        private final PositionSource positionSource;

        public VibrationUser(final BlockPos pos) {
            this.blockPos = pos;
            this.positionSource = new BlockPositionSource(pos);
        }

        public int getListenerRadius() {return 8;}

        public @NotNull PositionSource getPositionSource() {return this.positionSource;}

        public boolean canTriggerAvoidVibration() {return true;}

        @SuppressWarnings("deprecation")
        public boolean canReceiveVibration(@NotNull ServerLevel level, BlockPos pos,
                                           @NotNull Holder<GameEvent> gameEventHolder,
                                           @Nullable GameEvent.Context context) {
            return (!pos.equals(this.blockPos)
                    || !gameEventHolder.is(GameEvent.BLOCK_DESTROY)
                    && !gameEventHolder.is(GameEvent.BLOCK_PLACE))
                    && LivingSproutBlock.canActivate(LivingSproutBlockEntity.this.getBlockState());
        }

        public void onReceiveVibration(@NotNull ServerLevel level, @NotNull BlockPos pos,
                                       @NotNull Holder<GameEvent> gameEventHolder,
                                       @Nullable Entity entity, @Nullable Entity entity1, float v) {
            BlockState state = LivingSproutBlockEntity.this.getBlockState();
            if (LivingSproutBlock.canActivate(state)) {
                LivingSproutBlock.activate(level, LivingSproutBlockEntity.this.getBlockPos());
            }
            LivingSproutBlockEntity.this.trigger((int)level.getGameTime());
            BlockPos blockPos = LivingSproutBlockEntity.this.getBlockPos();
            if (ModSounds.HEART_BEAT.getHolder().isEmpty()) return;
            level.playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    ModSounds.HEART_BEAT.getHolder().get(),
                    SoundSource.BLOCKS, 5F, 1F, 1L);
        }

        public void onDataChanged() {LivingSproutBlockEntity.this.setChanged();}

        public boolean requiresAdjacentChunksToBeTicking() {
            return true;
        }
    }
}
