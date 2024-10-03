package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DysfunctionningCatalystBlockEntity extends BlockEntity {

    protected DysfunctionningCatalystBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DysfunctionningCatalystBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.DYSFUNCTIONNING_CATALYST_BLOCK_ENTITY.get(), pos, state);
    }
}
