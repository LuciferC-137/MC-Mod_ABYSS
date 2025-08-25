package wardentools.block.depthvines;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;
import wardentools.tags.ModTags;

public class DepthVinesBlock extends GrowingPlantHeadBlock implements BonemealableBlock, DepthVines {
    public static final MapCodec<net.minecraft.world.level.block.CaveVinesBlock> CODEC = simpleCodec(net.minecraft.world.level.block.CaveVinesBlock::new);
    private static final float CHANCE_OF_BERRIES_ON_GROWTH = 0.11F;

    public @NotNull MapCodec<net.minecraft.world.level.block.CaveVinesBlock> codec() {
        return CODEC;
    }

    public DepthVinesBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
        this.registerDefaultState(((this.stateDefinition.any())
                .setValue(AGE, 0))
                .setValue(BERRIES, false));
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(@NotNull RandomSource random) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return BlockRegistry.DEPTH_VINES_PLANT.get();
    }

    @Override
    protected @NotNull BlockState updateBodyAfterConvertedFromHead(BlockState state, BlockState myState) {
        return myState.setValue(BERRIES, state.getValue(BERRIES));
    }

    @Override
    protected @NotNull BlockState getGrowIntoState(@NotNull BlockState state, @NotNull RandomSource random) {
        return super.getGrowIntoState(state, random)
                .setValue(BERRIES, random.nextFloat() < CHANCE_OF_BERRIES_ON_GROWTH);
    }

    @SuppressWarnings("deprecation")
    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader reader, @NotNull BlockPos pos,
                                                @NotNull BlockState state) {
        return new ItemStack(ItemRegistry.DEPTH_BERRIES.get());
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        return DepthVines.use(player, state, level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(BERRIES);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos,
                                         BlockState state) {
        return !(Boolean)state.getValue(BERRIES);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return DepthVines.canHangBelow(level.getBlockState(pos.above()), level, pos.above())
                || super.canSurvive(state, level, pos);
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

