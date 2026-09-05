package wardentools.block.depthvines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;


public class DepthVinesPlantBlock extends GrowingPlantBodyBlock implements BonemealableBlock, DepthVines {

    public DepthVinesPlantBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.registerDefaultState((this.stateDefinition.any()).setValue(BERRIES, false));
    }

    @Override
    protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) BlockRegistry.DEPTH_VINES.get();
    }

    @Override
    protected @NotNull BlockState updateHeadAfterConvertedFromBody(BlockState state, BlockState myState) {
        return myState.setValue(BERRIES, state.getValue(BERRIES));
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter levelReader,
                                                @NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemStack(ItemRegistry.DEPTH_BERRIES.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level,
                                          @NotNull BlockPos pos, @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hitResult) {
        return DepthVines.use(player, state, level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BERRIES);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockRegistry.DARKTREE_LEAVES.get())
                || super.canSurvive(state, level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos,
                                         BlockState state, boolean b) {
        return !(Boolean)state.getValue(BERRIES);
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random,
                                     @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, @NotNull RandomSource random,
                                @NotNull BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(BERRIES, true), 2);
    }
}
