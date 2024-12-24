package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DarkGrassBlock extends Block {

    public DarkGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (level.getBlockState(blockPos.above()).isCollisionShapeFullBlock(level, blockPos.above())) {
            level.setBlockAndUpdate(blockPos, BlockRegistry.DARKDIRT.get().defaultBlockState());
        }
    }

}
