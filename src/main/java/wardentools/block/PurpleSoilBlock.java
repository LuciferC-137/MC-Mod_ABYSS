package wardentools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PurpleSoilBlock extends Block {

    public PurpleSoilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state,
                                                     @NotNull UseOnContext context,
                                                     @NotNull ItemAbility itemAbility,
                                                     boolean simulate) {
        if (ItemAbilities.HOE_TILL == itemAbility) {
            Block block = state.getBlock();
            if (block instanceof PurpleSoilBlock) {
                return BlockRegistry.PURPLE_FARMLAND.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level,
                           @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (!level.getBlockState(blockPos.above()).isCollisionShapeFullBlock(level, blockPos.above())) {
            level.setBlockAndUpdate(blockPos, BlockRegistry.GRASS_PURPLE_SOIL.get().defaultBlockState());
        }
    }
}
