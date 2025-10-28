package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.tags.ModTags;

public class AbyssDoublePlantBlock extends DoublePlantBlock {

    public AbyssDoublePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader reader, @NotNull BlockPos pos) {
        return state.is(ModTags.Blocks.CAN_SUSTAIN_ABYSS_PLANTS) || super.canSurvive(state, reader, pos);
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos) {
        return state.is(ModTags.Blocks.CAN_SUSTAIN_ABYSS_PLANTS) || super.mayPlaceOn(state, blockGetter, pos);
    }
}
