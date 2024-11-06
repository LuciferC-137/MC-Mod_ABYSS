package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ContagionIncarnationSkullBlockEntity extends BlockEntity {

    protected ContagionIncarnationSkullBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ContagionIncarnationSkullBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.CONTAGION_INCARNATION_SKULL_BLOCK_ENTITY.get(), pos, state);
    }
}
