package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.block.GolemStoneBlock;

public class GolemStoneBlockEntity extends BlockEntity {
    private int delayBeforeCheck = 40;

    public GolemStoneBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.GOLEM_STONE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        if (!level.getBlockState(this.getBlockPos()).hasProperty(GolemStoneBlock.HAS_SPAWNED_GOLEM)) return;
        if (this.getBlockState().getValue(GolemStoneBlock.HAS_SPAWNED_GOLEM)) return;

        if (delayBeforeCheck > 0) {
            delayBeforeCheck--;
            if (delayBeforeCheck == 0) {
                GolemStoneBlock.placeGolem(level, worldPosition, getBlockState());
                this.level.setBlock(this.worldPosition,
                        this.getBlockState().setValue(GolemStoneBlock.HAS_SPAWNED_GOLEM, true), 3);
            }
        }
    }
}


