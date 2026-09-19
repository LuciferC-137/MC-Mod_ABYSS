package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.sounds.ModSounds;

public class GramophoneBlockEntity extends BlockEntity implements TickableBlockEntity, Clearable, ContainerSingleItem {
    public static final String SONG_ITEM_TAG_ID = "RecordItem";
    public static final String TICKS_SINCE_SONG_STARTED_TAG_ID = "ticks_since_song_started";
    public static final String IS_PLAYING_TAG_ID = "isPlaying";
    public static final String SONG_PLAYING_TAG_ID = "songPlaying";
    public static final String COUNTDOWN_PLAY_TAG_ID = "countdown_before_play";
    public static final String COUNTDOWN_STOP_TAG_ID = "countdown_before_stop";

    private static final int TICKS_BEFORE_PLAY = 35;
    private static final int TICKS_BEFORE_STOP = 50;

    private ItemStack item = ItemStack.EMPTY;

    private boolean isPlaying = false;
    private boolean songPlaying = false;
    private long ticksSinceSongStarted = 0L;
    private int countdownBeforePlay = 0;
    private int countdownBeforeStop = 0;

    protected GramophoneBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GramophoneBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.GRAMOPHONE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        if (this.countdownBeforePlay > 0) {
            this.countdownBeforePlay--;
            if (this.countdownBeforePlay == 0) {
                this.startSong();
            }
            return;
        }

        if (this.countdownBeforeStop > 0) {
            this.countdownBeforeStop--;
            if (this.countdownBeforeStop == 0) {
                this.setPlaying(false);
                this.update();
            }
        }

        if (this.songPlaying) {
            this.ticksSinceSongStarted++;
            if (this.item.getItem() instanceof RecordItem record) {
                if (this.ticksSinceSongStarted >= record.getLengthInTicks()) {
                    this.stopSong();
                    this.scheduleStop();
                } else if (this.ticksSinceSongStarted % 20 == 0) {
                    this.level.gameEvent(GameEvent.JUKEBOX_PLAY, this.getBlockPos(),
                            GameEvent.Context.of(this.getBlockState()));
                    this.spawnMusicParticles();
                }
            } else {
                this.stopSong();
            }
        }
    }

    public void clientTick() {

    }

    public void schedulePlay() {
        this.vinylStartSound();
        this.setPlaying(true);
        this.countdownBeforePlay = TICKS_BEFORE_PLAY;
    }

    public void scheduleStop() {
        this.vinylEndSound();
        this.countdownBeforeStop = TICKS_BEFORE_STOP;
    }

    /** Starts the actual music (vanilla 1.20.1 way: level event 1010). */
    private void startSong() {
        if (this.level == null || this.level.isClientSide) return;
        if (!(this.item.getItem() instanceof RecordItem)) return;
        this.songPlaying = true;
        this.ticksSinceSongStarted = 0L;
        this.level.levelEvent(null, 1010, this.getBlockPos(), Item.getId(this.item.getItem()));
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }

    /** Stops the music (vanilla 1.20.1 way: level event 1011). */
    private void stopSong() {
        this.songPlaying = false;
        this.ticksSinceSongStarted = 0L;
        if (this.level == null || this.level.isClientSide) return;
        this.level.gameEvent(GameEvent.JUKEBOX_STOP_PLAY, this.getBlockPos(),
                GameEvent.Context.of(this.getBlockState()));
        this.level.levelEvent(1011, this.getBlockPos(), 0);
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }

    private void spawnMusicParticles() {
        if (this.level instanceof ServerLevel serverLevel) {
            Vec3 pos = Vec3.atBottomCenterOf(this.getBlockPos()).add(0.0D, 1.2D, 0.0D);
            float note = (float) serverLevel.getRandom().nextInt(4) / 24.0F;
            serverLevel.sendParticles(ParticleTypes.NOTE, pos.x(), pos.y(), pos.z(), 0, note, 0.0D, 0.0D, 1.0D);
        }
    }

    // ------------------------------------------------------------------ NBT

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains(SONG_ITEM_TAG_ID, 10)) {
            this.item = ItemStack.of(tag.getCompound(SONG_ITEM_TAG_ID));
        } else {
            this.item = ItemStack.EMPTY;
        }
        this.ticksSinceSongStarted = tag.getLong(TICKS_SINCE_SONG_STARTED_TAG_ID);
        this.songPlaying = tag.getBoolean(SONG_PLAYING_TAG_ID);
        this.isPlaying = tag.getBoolean(IS_PLAYING_TAG_ID);
        this.countdownBeforePlay = tag.getInt(COUNTDOWN_PLAY_TAG_ID);
        this.countdownBeforeStop = tag.getInt(COUNTDOWN_STOP_TAG_ID);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.item.isEmpty()) {
            tag.put(SONG_ITEM_TAG_ID, this.item.save(new CompoundTag()));
        }
        tag.putLong(TICKS_SINCE_SONG_STARTED_TAG_ID, this.ticksSinceSongStarted);
        tag.putBoolean(SONG_PLAYING_TAG_ID, this.songPlaying);
        tag.putBoolean(IS_PLAYING_TAG_ID, this.isPlaying);
        tag.putInt(COUNTDOWN_PLAY_TAG_ID, this.countdownBeforePlay);
        tag.putInt(COUNTDOWN_STOP_TAG_ID, this.countdownBeforeStop);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // ------------------------------------------------------------------ sync helpers

    public void onSongChanged() {
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

    public void popOutTheItem() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos pos = this.getBlockPos();
            ItemStack stack = this.getTheItem();
            if (!stack.isEmpty()) {
                this.removeTheItem();
                Vec3 direction = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5)
                        .offsetRandom(this.level.random, 0.7F);
                ItemEntity itemEntity = new ItemEntity(this.level,
                        direction.x(), direction.y(), direction.z(), stack.copy());
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }
        }
    }

    public @NotNull ItemStack getTheItem() {
        return this.item;
    }

    public void setTheItem(@NotNull ItemStack stack) {
        this.setItem(0, stack);
    }

    public @NotNull ItemStack removeTheItem() {
        return this.removeItem(0, 1);
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return slot == 0 ? this.item : ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        ItemStack stack = this.item;
        this.setItem(0, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        this.item = stack;
        if (this.level == null) return;

        if (!this.item.isEmpty()) {
            this.schedulePlay();
        } else {
            if (this.isPlaying) this.vinylScratchSound();
            this.setPlaying(false);
            this.stopSong();
            this.countdownBeforePlay = 0;
            this.countdownBeforeStop = 0;
        }
        this.update();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof RecordItem && this.getItem(slot).isEmpty();
    }

    @Override
    public boolean canTakeItem(@NotNull Container container, int slot, @NotNull ItemStack stack) {
        return container.hasAnyMatching(ItemStack::isEmpty);
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    private void vinylStartSound() {
        if (this.level == null || this.level.isClientSide) return;
        this.level.playSound(null, this.getBlockPos(), ModSounds.VINYL_START.get(),
                SoundSource.RECORDS, 1.0F, 1.0F);
    }

    private void vinylEndSound() {
        if (this.level == null || this.level.isClientSide) return;
        this.level.playSound(null, this.getBlockPos(), ModSounds.VINYL_END.get(),
                SoundSource.RECORDS, 1.0F, 1.0F);
    }

    private void vinylScratchSound() {
        if (this.level == null || this.level.isClientSide) return;
        this.level.playSound(null, this.getBlockPos(), ModSounds.VINYL_SCRATCH.get(),
                SoundSource.RECORDS, 1.0F, 1.0F);
    }
}