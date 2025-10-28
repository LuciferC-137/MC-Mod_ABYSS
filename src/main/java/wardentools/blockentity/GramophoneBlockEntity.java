package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.blockentity.util.TickableBlockEntity;
import wardentools.sounds.ModSounds;

import java.util.Optional;

public class GramophoneBlockEntity extends BlockEntity implements TickableBlockEntity, Clearable, ContainerSingleItem.BlockContainerSingleItem {
    public static final String SONG_ITEM_TAG_ID = "RecordItem";
    public static final String TICKS_SINCE_SONG_STARTED_TAG_ID = "ticks_since_song_started";
    public static final String IS_PLAYING_TAG_ID = "isPlaying";
    private static final int TICKS_BEFORE_PLAY = 35;
    private static final int TICKS_BEFORE_STOP = 50;
    private ItemStack item;
    private final JukeboxSongPlayer jukeboxSongPlayer;
    private boolean isPlaying = false;
    private int countdownBeforePlay = 0;
    private int countdownBeforeStop = 0;

    protected GramophoneBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.item = ItemStack.EMPTY;
        this.jukeboxSongPlayer = new JukeboxSongPlayer(this::onSongChanged, this.getBlockPos().above());
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
                this.updateJukeBoxPlayer();
            }
        } else {
            this.jukeboxSongPlayer.tick(this.level, this.getBlockState());
            if (this.countdownBeforeStop > 0) {
                this.countdownBeforeStop--;
                if (this.countdownBeforeStop == 0) {
                    this.setPlaying(false);
                    this.update();
                }
            }
            if (this.jukeboxSongPlayer.getSong() != null
                    && this.jukeboxSongPlayer.getTicksSinceSongStarted()
                    == this.jukeboxSongPlayer.getSong().lengthInTicks()){
                this.scheduleStop();
            }
        }
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

    public void clientTick() {

    }

    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains(SONG_ITEM_TAG_ID, 10)) {
            this.item = ItemStack.parse(provider, tag.getCompound(SONG_ITEM_TAG_ID)).orElse(ItemStack.EMPTY);
        } else this.item = ItemStack.EMPTY;

        if (tag.contains(TICKS_SINCE_SONG_STARTED_TAG_ID, 4)) {
            JukeboxSong.fromStack(provider, this.item).ifPresent((p_342562_) -> {
                this.jukeboxSongPlayer.setSongWithoutPlaying(p_342562_, tag.getLong(TICKS_SINCE_SONG_STARTED_TAG_ID));
            });
        }
        if (tag.contains(IS_PLAYING_TAG_ID, 1)) {
            this.setPlaying(tag.getBoolean(IS_PLAYING_TAG_ID));
        } else this.setPlaying(false);
    }

    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        if (!this.getTheItem().isEmpty()) {
            tag.put(SONG_ITEM_TAG_ID, this.getTheItem().save(provider));
        }
        if (this.jukeboxSongPlayer.getSong() != null) {
            tag.putLong(TICKS_SINCE_SONG_STARTED_TAG_ID, this.jukeboxSongPlayer.getTicksSinceSongStarted());
        }
        tag.putBoolean(IS_PLAYING_TAG_ID, this.isPlaying);
    }

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
            ItemStack item = this.getTheItem();
            if (!item.isEmpty()) {
                this.removeTheItem();
                Vec3 direction = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5)
                        .offsetRandom(this.level.random, 0.7F);
                ItemStack itemCopy = item.copy();
                ItemEntity itemEntity = new ItemEntity(this.level,
                        direction.x(), direction.y(), direction.z(), itemCopy);
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }
        }
    }

    @Override
    public @NotNull BlockEntity getContainerBlockEntity() {return this;}

    @Override
    public @NotNull ItemStack getTheItem() {return this.item;}

    @Override
    public @NotNull ItemStack splitTheItem(int splitValue) {
        ItemStack item = this.item;
        this.setTheItem(ItemStack.EMPTY);
        return item;
    }

    @Override
    public void setTheItem(@NotNull ItemStack stack) {
        if (this.level == null) return;
        this.item = stack;
        if (!this.item.isEmpty()) this.schedulePlay();
        else {
            if (this.isPlaying) this.vinylScratchSound();
            this.setPlaying(false);
            this.updateJukeBoxPlayer();
            this.countdownBeforePlay = 0;
        }
        this.update();
    }

    public void updateJukeBoxPlayer() {
        if (this.level == null) return;
        Optional<Holder<JukeboxSong>> jukeboxSongHolder
                = JukeboxSong.fromStack(this.level.registryAccess(), this.item);
        if (!this.item.isEmpty() && jukeboxSongHolder.isPresent()) {
            this.jukeboxSongPlayer.play(this.level, jukeboxSongHolder.get());
        } else {
            this.jukeboxSongPlayer.stop(this.level, this.getBlockState());
        }
    }

    @Override
    public int getMaxStackSize() {return 1;}

    @Override
    public boolean canPlaceItem(int size, ItemStack stack) {
        return stack.has(DataComponents.JUKEBOX_PLAYABLE) && this.getItem(size).isEmpty();
    }

    @Override
    public boolean canTakeItem(Container container, int i, @NotNull ItemStack stack) {
        return container.hasAnyMatching(ItemStack::isEmpty);
    }

    public boolean isPlaying() {
        return isPlaying;
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

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = super.getUpdateTag(provider);
        saveAdditional(nbt, provider);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

}
