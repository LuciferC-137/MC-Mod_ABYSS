package wardentools.block;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.worldgen.biome.ModBiomes;

public class DarkGrassBlock extends Block {

    public DarkGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (level.getBlockState(blockPos.above()).isCollisionShapeFullBlock(level, blockPos.above())) {
            level.setBlockAndUpdate(blockPos, BlockRegistry.DARKDIRT.get().defaultBlockState());
        }
    }

    public static int getColor(@NotNull BlockState state,
                               @Nullable BlockAndTintGetter getter,
                               @Nullable BlockPos pos,
                               int index) {
        if (getter != null && pos != null) {
            if (index == 0) {
                return BiomeColors.getAverageGrassColor(getter, pos);
            } else if (index == 1) {
                return BiomeColors.getAverageFoliageColor(getter, pos);
            } else return -1;
        } else {
            if (index == 0) return ModBiomes.DEEP_FOREST_1;
            else if (index == 1) return ModBiomes.DEEP_FOREST_2;
            else return -1;
        }

    }

}
