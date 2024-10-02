package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssPortalBlockEntity extends BlockEntity {

    protected AbyssPortalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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
