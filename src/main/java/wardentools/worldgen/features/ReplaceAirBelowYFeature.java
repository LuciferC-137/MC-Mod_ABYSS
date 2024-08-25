package wardentools.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import wardentools.block.BlockRegistry;

public class ReplaceAirBelowYFeature extends Feature<NoneFeatureConfiguration> {
    private static final int Y_THRESHOLD = -54;
    private static final int WORLD_MIN_Y = -64;

    public ReplaceAirBelowYFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos origin = context.origin();
        int chunkX = origin.getX() >> 4 << 4;
        int chunkZ = origin.getZ() >> 4 << 4;
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = WORLD_MIN_Y; y <= Y_THRESHOLD; y++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState currentState = worldGenLevel.getBlockState(pos);
                    if (currentState.getBlock() == Blocks.AIR) {
                        worldGenLevel.setBlock(pos,
                                BlockRegistry.LIQUID_CORRUPTION_BLOCK.get().defaultBlockState(), 3);
                    }
                }
            }
        }
        return true;
    }
}
