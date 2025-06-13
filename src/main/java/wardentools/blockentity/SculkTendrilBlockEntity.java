package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.block.sculktendril.TendrilNode;
import wardentools.block.sculktendril.TendrilTree;

import java.util.Map;

public class SculkTendrilBlockEntity extends BlockEntity {
    private Map<Direction, Boolean> connections = Map.of(
        Direction.NORTH, false,
        Direction.SOUTH, false,
        Direction.EAST, false,
        Direction.WEST, false,
        Direction.UP, false,
        Direction.DOWN, false
    );
    private @Nullable TendrilTree tendrilTreeGraph = null;
    private BlockPos originPos;

    public SculkTendrilBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.originPos = pos; // Default to the position of the block entity. Must be updated later.
    }

    public SculkTendrilBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SCULK_TENDRIL_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean isOrigin() {return tendrilTreeGraph != null;}

    public BlockPos getOrigin() {return originPos;}

    public @Nullable TendrilTree getTendrilTreeGraph() {return tendrilTreeGraph;}

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        if (tendrilTreeGraph != null) {
            tag.put("tendril_tree", tendrilTreeGraph.writeTreeToTag());
        }
        tag.put("origin_pos", TendrilNode.blockPosToTag(originPos));
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("tendril_tree")) {
            this.tendrilTreeGraph = new TendrilTree(tag.getCompound("tendril_tree"));
        }
        if (tag.contains("origin_pos")) {
            this.originPos = TendrilNode.blockPosFromTag(tag.getCompound("origin_pos"));
        }
        loadConnections();
    }

    private void loadConnections() {
        if (this.level != null && this.level.getBlockEntity(this.originPos) instanceof SculkTendrilBlockEntity originEntity) {
            TendrilTree tendrilTree = originEntity.getTendrilTreeGraph();
            if (tendrilTree != null) {
                for (BlockPos pos : tendrilTree.getChildrenOf(this.worldPosition)) {
                    Direction direction = getDirectionFromBlockPos(pos);
                    this.connections.put(direction, true);
                }
                BlockPos parentPos = tendrilTree.getParentOf(this.worldPosition);
                if (parentPos != null) {
                    Direction direction = getDirectionFromBlockPos(parentPos);
                    this.connections.put(direction, true);
                }
            }
        }
    }

    private Direction getDirectionFromBlockPos(BlockPos pos) {
        if (pos.getX() < this.worldPosition.getX()) return Direction.WEST;
        if (pos.getX() > this.worldPosition.getX()) return Direction.EAST;
        if (pos.getY() < this.worldPosition.getY()) return Direction.DOWN;
        if (pos.getY() > this.worldPosition.getY()) return Direction.UP;
        if (pos.getZ() < this.worldPosition.getZ()) return Direction.NORTH;
        if (pos.getZ() > this.worldPosition.getZ()) return Direction.SOUTH;
        return Direction.UP; // Should not happen
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = super.getUpdateTag(provider);
        saveAdditional(nbt, provider);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void sendUpdate() {
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

}
