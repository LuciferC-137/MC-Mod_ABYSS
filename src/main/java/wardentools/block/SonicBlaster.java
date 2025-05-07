package wardentools.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class SonicBlaster extends DirectionalBlock {
    public static final MapCodec<SonicBlaster> CODEC = simpleCodec(SonicBlaster::new);

    @Override
    protected @NotNull MapCodec<SonicBlaster> codec() {return CODEC;}

    public SonicBlaster(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Nullable
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        BlockState blockStatePlacement = super.getStateForPlacement(placeContext);
        if (blockStatePlacement != null) {
            return blockStatePlacement.setValue(FACING, placeContext.getNearestLookingDirection().getOpposite());
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

}
