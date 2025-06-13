package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.blockentity.BlockEntityRegistry;
import wardentools.blockentity.SculkTendrilBlockEntity;

import javax.annotation.Nullable;

public class SculkTendrilBlock extends DropExperienceBlock implements EntityBlock {

    public SculkTendrilBlock(IntProvider intProvider, Properties properties) {
        super(intProvider, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityRegistry.SCULK_TENDRIL_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        // Retrieve the block that was clicked on
        Block clickedBlock = placeContext.getLevel().getBlockState(placeContext.getClickedPos()).getBlock();

        // Retrieve the block position where the block is about to be placed
        BlockPos placePos = placeContext.getClickedPos().relative(placeContext.getClickedFace());

        this.updateGraphOnPlace(clickedBlock, placePos, placeContext.getLevel());
        return super.getStateForPlacement(placeContext);
    }

    private void updateGraphOnPlace(Block placeOnBlock, BlockPos placePos, Level level) {
        if (level.getBlockEntity(placePos) instanceof SculkTendrilBlockEntity tendrilBlockEntity) {

        }
    }
}
