package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AbyssPortalBlockEntity extends BlockEntity {
    private boolean shouldShowWinScreen = false;

    protected AbyssPortalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putBoolean("shouldShowWinScreen", this.shouldShowWinScreen);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        this.shouldShowWinScreen = tag.getBoolean("shouldShowWinScreen");
    }

    public boolean shouldShowWinScreen() {
        return this.shouldShowWinScreen;
    }

    public void setShouldShowWinScreen(boolean shouldShowWinScreen) {
        this.shouldShowWinScreen = shouldShowWinScreen;
        this.setChanged();
    }

    public AbyssPortalBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.ABYSS_PORTAL_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean shouldRenderFace(Direction direction) {
        if (this.level == null) return false;
        return Block.shouldRenderFace(this.getBlockState(), this.level,
                this.getBlockPos(), direction, this.getBlockPos().relative(direction));
    }
}
