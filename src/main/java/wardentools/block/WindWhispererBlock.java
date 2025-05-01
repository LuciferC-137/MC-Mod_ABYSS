package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.misc.wind.WhisperManager;
import wardentools.network.PacketHandler;
import wardentools.network.ParticulesSoundsEffects.ParticleContagionImplosion;

public class WindWhispererBlock extends Block {
    private static final VoxelShape SHAPE_BOTTOM = Block.box(4.5, 1.0, 4.5, 11.5, 2.0, 11.5);
    private static final VoxelShape SHAPE_MIDDLE_BOTTOM = Block.box(3.5, 2.0, 3.5, 12.5, 4.0, 12.5);
    private static final VoxelShape SHAPE_MIDDLE = Block.box(3.0, 4.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape SHAPE_TOP = Block.box(3.5, 8.0, 3.5, 12.5, 9.0, 12.5);
    private static final VoxelShape SHAPE_TOP_BIG = Block.box(4.5, 9.0, 4.5, 8.5, 12.0, 8.5);
    private static final VoxelShape SHAPE_TOP_MIDDLE = Block.box(8.5, 9.0, 5.0, 10.5, 11.0, 7.0);
    private static final VoxelShape SHAPE_TOP_SMALL = Block.box(7.0, 9.0, 9.0, 11.0, 10.0, 12.0);
    private static final VoxelShape SHAPE = Shapes.or(SHAPE_BOTTOM, SHAPE_MIDDLE_BOTTOM, SHAPE_MIDDLE,
            SHAPE_TOP, SHAPE_TOP_BIG, SHAPE_TOP_MIDDLE, SHAPE_TOP_SMALL);




    public WindWhispererBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world,
                                        @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (WhisperManager.INSTANCE.sendRandomWhisperToAllPlayers(level)) {
            PacketHandler.sendToAllClient(new ParticleContagionImplosion(blockPos.getCenter()));
        }
    }
}
