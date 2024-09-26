package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.effect.ModEffects;

public class SolidCorruptionBlock extends Block {
    private static final int AVERAGE_FREEZE_DURATION = 10;

    public SolidCorruptionBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (noLiquidNeighbor(level, blockPos)) {
            level.setBlockAndUpdate(blockPos, BlockRegistry.LIQUID_CORRUPTION_BLOCK.get().defaultBlockState());
        }
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos blockPos,
                       @NotNull BlockState blockState, @NotNull Entity entity) {
        super.stepOn(level, blockPos, blockState, entity);
        if (entity instanceof LivingEntity entity1){
            entity1.addEffect(new MobEffectInstance(ModEffects.CORRUPTED.get(),
                    40, 0, false, false));
        }
    }

    private boolean noLiquidNeighbor(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            if (neighborState.is(Blocks.WATER) || neighborState.is(Blocks.LAVA)) {
                return false;
            }
        }
        return true;
    }
}
