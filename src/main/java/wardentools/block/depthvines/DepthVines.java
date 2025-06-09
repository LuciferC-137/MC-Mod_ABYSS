package wardentools.block.depthvines;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import wardentools.items.ItemRegistry;

import javax.annotation.Nullable;
import java.util.function.ToIntFunction;

public interface DepthVines {
    VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0,
            15.0, 16.0, 15.0);
    BooleanProperty BERRIES = BlockStateProperties.BERRIES;

    static InteractionResult use(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        if (state.getValue(BERRIES)) {
            Block.popResource(level, pos, new ItemStack(ItemRegistry.DEPTH_BERRIES.get(), 1));
            float prob = Mth.randomBetween(level.random, 0.8F, 1.2F);
            level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES,
                    SoundSource.BLOCKS, 1.0F, prob);
            BlockState newState = state.setValue(BERRIES, false);
            level.setBlock(pos, newState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, newState));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    static boolean hasGlowBerries(BlockState state) {
        return state.hasProperty(BERRIES) && state.getValue(BERRIES);
    }

    static ToIntFunction<BlockState> emission(int light) {
        return (state) -> state.getValue(BlockStateProperties.BERRIES) ? light : 0;
    }
}
