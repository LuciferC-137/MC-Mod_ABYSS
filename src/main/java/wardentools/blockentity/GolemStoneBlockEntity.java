package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.block.GolemStoneBlock;

public class GolemStoneBlockEntity extends BlockEntity {
    private boolean hasSpawnedGolem = false;
    private boolean hasBeenPlacedByPlayer = false;
    private int delayBeforeCheck = 20;

    public GolemStoneBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.GOLEM_STONE_BLOCK_ENTITY.get(), pos, state);
    }

    public void markPlacedByPlayer() {
        this.hasBeenPlacedByPlayer = true;
        this.setChanged();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putBoolean("HasSpawnedGolem", hasSpawnedGolem);
        tag.putBoolean("HasBeenPlacedByPlayer", hasBeenPlacedByPlayer);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        this.hasSpawnedGolem = tag.getBoolean("HasSpawnedGolem");
        this.hasBeenPlacedByPlayer = tag.getBoolean("HasBeenPlacedByPlayer");
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        if (hasSpawnedGolem || hasBeenPlacedByPlayer) return;

        if (delayBeforeCheck > 0) {
            delayBeforeCheck--;
            if (delayBeforeCheck == 0) {
                this.hasSpawnedGolem = true;
                this.setChanged();

                GolemStoneBlock.placeGolem(level, worldPosition, getBlockState());
            }
        }
    }
}


