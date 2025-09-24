package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.misc.Crystal;

public class CrystalInfuserBlock extends Block {
    public static final EnumProperty<Crystal> CRYSTAL;

    static {
        CRYSTAL = EnumProperty.create("crystal_index",
                Crystal.class);
    }

    public CrystalInfuserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any())
                .setValue(CRYSTAL, Crystal.getDefault()));
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter,
                                        @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return Block.box(1.0D, 0.0D, 1.0D,
                15.0D, 12.0D, 15.0D);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(CRYSTAL);
        super.createBlockStateDefinition(builder);
    }

    public static Crystal getCrystal(BlockState state) {
        return state.getValue(CRYSTAL);
    }

    public static int getCrystalColor(BlockState state) {
        return getCrystal(state).getColor();
    }

    public static int getColor(@NotNull BlockState blockState,
                               @Nullable BlockAndTintGetter blockAndTintGetter,
                        @Nullable BlockPos blockPos, int index) {
        if (index != 0) return -1;
        return getCrystalColor(blockState);
    }

}
