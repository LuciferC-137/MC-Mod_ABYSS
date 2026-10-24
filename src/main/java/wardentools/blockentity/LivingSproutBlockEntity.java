package wardentools.blockentity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
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

public class LivingSproutBlockEntity extends BlockEntity implements GameEventListener.Holder<VibrationSystem.Listener>, VibrationSystem {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int COOLDOWN = 216;

    private VibrationSystem.Data vibrationData;
    private final VibrationSystem.Listener vibrationListener;
    private final VibrationSystem.User vibrationUser;

    private boolean isPulsing = false;
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

    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("lastTimeHeard", CompoundTag.TAG_INT)) {
            this.lastTimeHeard = tag.getInt("lastTimeHeard");
        }
        if (tag.contains("isPulsing", CompoundTag.TAG_BYTE)) {
            this.isPulsing = tag.getBoolean("isPulsing");
        }
    }

    protected void saveAdditional(@NotNull CompoundTag tag) {
       super.saveAdditional(tag);
        tag.putInt("lastTimeHeard", this.lastTimeHeard);
        tag.putBoolean("isPulsing", this.isPulsing);
    }

    public void tick() {
        VibrationSystem.Ticker.tick(this.level, this.getVibrationData(), this.getVibrationUser());
        if (level == null) return;
        if (this.level.getGameTime() - this.lastTimeHeard >= COOLDOWN && this.isPulsing) {
            this.calmDown();
        }
    }

    public boolean isPulsing() {return this.isPulsing;}

    public int getLastTimeHeard() {return this.lastTimeHeard;}

    public void trigger(int timeTick) {
        if (this.level == null) return;
        if (timeTick - this.lastTimeHeard < COOLDOWN) {
            if (this.isPulsing) {
                this.level.destroyBlock(this.getBlockPos(), true);
                return;
            }
        } else {
            this.isPulsing = true;
        }
        this.lastTimeHeard = timeTick;
        this.update();
    }

    public void calmDown() {
        this.isPulsing = false;
        this.update();
    }

    public void update() {
        if (this.level == null) return;
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        if (!this.level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(),
                    this.getBlockState(), Block.UPDATE_ALL);
        }
        this.setChanged();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
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


        @Override
        public boolean canReceiveVibration(@NotNull ServerLevel level, BlockPos pos,
                                           @NotNull GameEvent event,
                                           @Nullable GameEvent.Context context) {
            return (!pos.equals(this.blockPos)
                    || !event.equals(GameEvent.BLOCK_DESTROY)
                    && !event.equals(GameEvent.BLOCK_PLACE))
                    && LivingSproutBlock.canActivate(LivingSproutBlockEntity.this.getBlockState())
                    && context != null
                    && (context.sourceEntity() instanceof Player player && !player.isCreative());
        }

        @Override
        public void onReceiveVibration(@NotNull ServerLevel level, @NotNull BlockPos pos,
                                       @NotNull GameEvent event,
                                       @Nullable Entity entity, @Nullable Entity entity1, float v) {
            BlockState state = LivingSproutBlockEntity.this.getBlockState();
            if (LivingSproutBlock.canActivate(state)) {
                if (!LivingSproutBlockEntity.this.isPulsing()) {
                    BlockPos blockPos = LivingSproutBlockEntity.this.getBlockPos();
                    level.playSeededSound(null, blockPos.getX(),
                            blockPos.getY(), blockPos.getZ(),
                            ModSounds.HEART_BEAT.get(),
                            SoundSource.BLOCKS, 5F, 1F, 1L);
                }
                LivingSproutBlock.activate(level, LivingSproutBlockEntity.this.getBlockPos());
            }
            LivingSproutBlockEntity.this.trigger((int)level.getGameTime());

        }

        public void onDataChanged() {LivingSproutBlockEntity.this.setChanged();}

        public boolean requiresAdjacentChunksToBeTicking() {
            return true;
        }
    }
}
