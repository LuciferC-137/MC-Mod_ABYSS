package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlackLanternBlock;
import wardentools.blockentity.util.TickableBlockEntity;

public class BlackLanternBlockEntity extends BlockEntity implements TickableBlockEntity {
    private int tickCountDown = 0;
    private int numberOfBlink = 0;

    protected BlackLanternBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlackLanternBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.BLACK_LANTERN_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.getBlockState().setValue(BlackLanternBlock.LIT, true);
    }

    @Override
    public void tick() {
        if (this.level == null) return;
        if (!this.level.isClientSide) {
            if (this.numberOfBlink > 0) {
                if (this.tickCountDown == 0) {
                    this.switchState(this.getBlockState(), (ServerLevel)this.level, this.getBlockPos());
                    this.tickCountDown = this.level.getRandom().nextInt(1, 5);
                    this.numberOfBlink--;
                } else {
                    this.tickCountDown--;
                }
            }
        }
    }

    public void switchState(@NotNull BlockState blockState, @NotNull ServerLevel level,
                             @NotNull BlockPos blockPos) {
        if (!blockState.getValue(BlackLanternBlock.LIT)) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(BlackLanternBlock.LIT, true));
        } else {
            level.setBlockAndUpdate(blockPos, blockState.setValue(BlackLanternBlock.LIT, false));
        }
    }

    public void setNumberOfBlink(int numberOfBlink) {
        this.numberOfBlink = numberOfBlink;
    }
}
