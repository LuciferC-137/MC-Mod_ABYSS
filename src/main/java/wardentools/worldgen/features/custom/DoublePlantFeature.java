package wardentools.worldgen.features.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import org.jetbrains.annotations.NotNull;

public class DoublePlantFeature extends Feature<SimpleBlockConfiguration> {

    public DoublePlantFeature(Codec<SimpleBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<SimpleBlockConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockState state = context.config().toPlace()
                .getState(context.random(), origin);

        if (!state.canSurvive(level, origin)) return false;

        if (level.getBlockState(origin.below()).is(Blocks.SCULK)) {
            if (context.random().nextInt(0, 7) >= 5) {
                // Good chance to not place on sculk
                return false;
            }
        }

        level.setBlock(origin, state, Block.UPDATE_CLIENTS);
        if (state.hasProperty(DoublePlantBlock.HALF)) {
            BlockState aboveState = state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
            level.setBlock(origin.above(), aboveState, Block.UPDATE_CLIENTS);
            level.scheduleTick(origin.above(), state.getBlock(), 5);
        }
        return true;
    }

}
