package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlackLanternBlockEntity;
import wardentools.blockentity.BlockEntityRegistry;

import javax.annotation.Nullable;

public class BlackLanternBlock extends Block implements EntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BlackLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, true));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (level.getBlockEntity(blockPos) instanceof BlackLanternBlockEntity lantern) {
            if (!blockState.getValue(LIT)) {
                lantern.switchState(blockState, level, blockPos);
            } else {
                lantern.setNumberOfBlink(random.nextInt(1, 5) * 2);
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type){
        return level.isClientSide() ? null : (level0, pos0, state0, blockEntity)
                -> ((BlackLanternBlockEntity)blockEntity).tick();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityRegistry.BLACK_LANTERN_BLOCK_ENTITY.get().create(pos, state);
    }
}
