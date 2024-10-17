package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

public class BlackLanternBlock extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BlackLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (!blockState.getValue(LIT)) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(LIT, true));
        } else {
            level.setBlockAndUpdate(blockPos, blockState.setValue(LIT, false));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
