package wardentools.block.depthvines;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;

public class DepthVinesPlantBlock extends GrowingPlantBodyBlock implements BonemealableBlock, DepthVines {
    public static final MapCodec<net.minecraft.world.level.block.CaveVinesPlantBlock> CODEC = simpleCodec(net.minecraft.world.level.block.CaveVinesPlantBlock::new);

    public @NotNull MapCodec<net.minecraft.world.level.block.CaveVinesPlantBlock> codec() {
        return CODEC;
    }

    public DepthVinesPlantBlock(BlockBehaviour.Properties p_153000_) {
        super(p_153000_, Direction.DOWN, SHAPE, false);
        this.registerDefaultState((this.stateDefinition.any()).setValue(BERRIES, false));
    }

    protected @NotNull GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) BlockRegistry.DEPTH_VINES.get();
    }

    protected @NotNull BlockState updateHeadAfterConvertedFromBody(BlockState state, BlockState myState) {
        return myState.setValue(BERRIES, state.getValue(BERRIES));
    }

    public @NotNull ItemStack getCloneItemStack(@NotNull LevelReader levelReader,
                                                @NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemStack(ItemRegistry.DEPTH_BERRIES.get());
    }

    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level,
                                                        @NotNull BlockPos pos, @NotNull Player player,
                                                        @NotNull BlockHitResult hitResult) {
        return DepthVines.use(player, state, level, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(new Property[]{BERRIES});
    }

    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos pos,
                                         BlockState state) {
        return !(Boolean)state.getValue(BERRIES);
    }

    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random,
                                     @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, @NotNull RandomSource random,
                                @NotNull BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(BERRIES, true), 2);
    }
}
