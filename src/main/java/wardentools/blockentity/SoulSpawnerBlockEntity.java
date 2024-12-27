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
import wardentools.entity.custom.ShadowEntity;

public class SoulSpawnerBlockEntity extends BlockEntity {
    private ShadowEntity shadowEntity;

    protected SoulSpawnerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SoulSpawnerBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SOUL_SPAWNER_BLOCK_ENTITY.get(), pos, state);
    }

    public ShadowEntity getShadowEntity() {return shadowEntity;}
    public void setShadowEntity(ShadowEntity shadowEntity) {this.shadowEntity = shadowEntity;}

}