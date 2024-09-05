package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class LiquidCorruptionBlock extends LiquidBlock {

    @Deprecated
    public LiquidCorruptionBlock(FlowingFluid p_54694_, BlockBehaviour.Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    public LiquidCorruptionBlock(java.util.function.Supplier<? extends FlowingFluid> p_54694_, BlockBehaviour.Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter getter,
                                  BlockPos pos, PathComputationType pathComputationType) {
        return false;
    }

}
