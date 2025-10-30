package wardentools.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.block.BlockRegistry;
import wardentools.block.sculktendril.TendrilNode;
import wardentools.block.sculktendril.TendrilTree;
import wardentools.tags.ModTags;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SculkTendrilBlockEntity extends BlockEntity {
    private HashMap<Direction, Boolean> connections = new HashMap<>(Map.of(
            Direction.NORTH, false,
            Direction.SOUTH, false,
            Direction.EAST, false,
            Direction.WEST, false,
            Direction.UP, false,
            Direction.DOWN, false
    ));
    private @Nullable TendrilTree tendrilTreeGraph = null; // Only the origin knows the tree
    private BlockPos originPos; // But every member of the graph remembers the origin.
    // This can lead to inconsistency, make sure to handle null or degenerated cases.

    private int width = 0;

    public SculkTendrilBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.originPos = pos; // Default to the position of the block entity. Must be updated later.
    }

    public SculkTendrilBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.SCULK_TENDRIL_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean isOrigin() {return tendrilTreeGraph != null;}

    public BlockPos getOrigin() {return originPos;}

    public void setOrigin(BlockPos origin) {this.originPos = origin;}

    public @Nullable TendrilTree getTendrilTreeGraph() {return tendrilTreeGraph;}

    public void setTendrilTreeGraph(@NotNull TendrilTree tendrilTreeGraph) {
        this.tendrilTreeGraph = tendrilTreeGraph;
        this.originPos = tendrilTreeGraph.getOrigin();
        sendUpdate();
    }

    public boolean getConnection(Direction direction) {return connections.get(direction);}

    public Map<Direction, Boolean> getAllConnections() {return connections;}

    // This method must be called to retrieve information from the tree.
    public @Nullable TendrilTree getRelativeTendrilTreeGraph() {
        if (this.level == null) return null;
        if (this.level.getBlockEntity(this.originPos) instanceof SculkTendrilBlockEntity originEntity) {
            return originEntity.getTendrilTreeGraph();
        }
        return null;
    }

    public int getWidth() {
        if (this.width == 0) {
            if (this.getRelativeTendrilTreeGraph() == null) return 8;
            this.width = this.getRelativeTendrilTreeGraph().getWidth(this.worldPosition);
        }
        return this.width;
    }

    public void updateWidth() {
        if (this.level == null || level.isClientSide) return;
        if (this.getRelativeTendrilTreeGraph() == null) return;
        this.width = this.getRelativeTendrilTreeGraph().getWidth(this.worldPosition);
        this.updateConnections();
        this.sendUpdate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        super.saveAdditional(tag, provider);
        if (tendrilTreeGraph != null) {
            tag.put("tendril_tree", tendrilTreeGraph.writeTreeToTag());
        }
        tag.put("origin_pos", TendrilNode.blockPosToTag(originPos));
        tag.putInt("width", this.width);
        this.saveConnectionsToTag(tag);
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
        if (tag.contains("width")) {
            this.width = tag.getInt("width");
        } else this.width = 0;
        this.loadConnectionsFromTag(tag);
    }

    public void updateConnections() {
        if (this.level != null) {
            TendrilTree tendrilTree = this.getRelativeTendrilTreeGraph();
            if (tendrilTree != null) {
                for (Direction direction : Direction.values()) {
                    BlockPos neighborPos = this.worldPosition.relative(direction);
                    boolean connected = false;
                    if (tendrilTree.getChildrenOf(this.worldPosition).contains(neighborPos) &&
                            level.getBlockState(neighborPos).is(BlockRegistry.SCULK_TENDRIL_BLOCK.get())) {
                        connected = true;
                    }
                    else if (tendrilTree.getParentOf(this.worldPosition) != null &&
                            Objects.equals(tendrilTree.getParentOf(this.worldPosition), neighborPos)) {
                        connected = true;
                    }
                    else if (this.level.getBlockState(neighborPos).is(ModTags.Blocks.CONNECT_TO_TENDRILS_BLOCKS)) {
                        connected = true;
                    }
                    this.connections.put(direction, connected);
                }
            }
        }
    }

    private void saveConnectionsToTag(CompoundTag tag) {
        for (Map.Entry<Direction, Boolean> entry : connections.entrySet()) {
            if (entry.getValue()) {
                tag.putBoolean(entry.getKey().getSerializedName(), true);
            }
        }
    }

    private void loadConnectionsFromTag(CompoundTag tag) {
        for (Direction direction : Direction.values()) {
            if (tag.contains(direction.getSerializedName())) {
                connections.put(direction, tag.getBoolean(direction.getSerializedName()));
            } else {
                connections.put(direction, false);
            }
        }
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