package wardentools.block.sculktendril;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.WorldGenLevel;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class TendrilTree {
    private static final int MAX_LENGTH = 8; // Maximum depth of the tendril tree. This is also used to compute the physical width.
    private BlockPos origin = null;
    private final Map<BlockPos, TendrilNode> nodes = new HashMap<>();
    private static final Logger LOGGER = LogUtils.getLogger();

    public TendrilTree(BlockPos origin) {
        this.addOrigin(origin);
    }

    public TendrilTree(CompoundTag tag) {
        if (tag.contains("origin")) {
            this.origin = TendrilNode.blockPosFromTag(tag.getCompound("origin"));
        }

        boolean stop = false;
        int i = 1;
        while (!stop && i <= 100) {
            String key = getIndexedKey(i);
            if (tag.contains(key)) {
                CompoundTag nodeTag = tag.getCompound(key);
                BlockPos position = TendrilNode.blockPosFromTag(nodeTag.getCompound("position"));
                int depth = nodeTag.getInt("depth");

                TendrilNode node = new TendrilNode(position, depth);
                nodes.put(position, node);
            } else {
                stop = true;
            }
            i++;
        }

        i = 1;
        stop = false;
        while (!stop && i <= 100) {
            String key = getIndexedKey(i);
            if (tag.contains(key)) {
                CompoundTag nodeTag = tag.getCompound(key);
                BlockPos position = TendrilNode.blockPosFromTag(nodeTag.getCompound("position"));
                TendrilNode node = nodes.get(position);

                if (nodeTag.contains("parent")) {
                    BlockPos parentPos = TendrilNode.blockPosFromTag(nodeTag.getCompound("parent"));
                    TendrilNode parent = nodes.get(parentPos);
                    if (parent != null) {
                        node.setParent(parent);
                        parent.addChild(node);
                    }
                } else {
                    addOrigin(position);
                }
            } else {
                stop = true;
            }
            i++;
        }
    }

    public BlockPos getOrigin() {return origin;}

    public List<BlockPos> getAllNodes() {return this.nodes.keySet().stream().filter(Objects::nonNull).toList();}

    public List<BlockPos> getChildrenOf(BlockPos pos) {
        if (!nodes.containsKey(pos)) return List.of();
        TendrilNode node = nodes.get(pos);
        return node.getChildren().stream()
                .map(TendrilNode::getPosition)
                .toList();
    }

    public @Nullable BlockPos getParentOf(BlockPos pos) {
        if (!nodes.containsKey(pos)) return null;
        TendrilNode node = nodes.get(pos);
        return node.getParent() == null ? null : node.getParent().getPosition();
    }

    public int getWidth(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        TendrilNode node = nodes.get(pos);
        float length = getLength(node.getPosition());
        float baseWidth = (float)MAX_LENGTH - (float)node.getDepth() + 1;
        return Math.min(Math.max(1, (int)Math.ceil(baseWidth - (MAX_LENGTH - length) / 2F)), MAX_LENGTH) * 2;
    }

    public boolean canHaveChildren(BlockPos pos) {
        return nodes.get(pos).getDepth() <= MAX_LENGTH;
    }

    public void recursiveRemove(BlockPos pos) {
        // Deletes the node at pos and all its children
        this.recursiveRemove(pos, 0);
    }

    public void updateAllNodes(WorldGenLevel level) {
        for (Map.Entry<BlockPos, TendrilNode> entry : nodes.entrySet()) {
            TendrilNode node = entry.getValue();
            level.scheduleTick(node.getPosition(), level.getBlockState(node.getPosition()).getBlock(), 2);
        }
    }

    public void recursiveRemove(BlockPos pos, int depth) {
        if (!nodes.containsKey(pos) || depth > 100) return;
        TendrilNode node = nodes.get(pos);
        for (TendrilNode child : node.getChildren()) {
            this.recursiveRemove(child.getPosition(), depth + 1);
        }
        nodes.remove(pos);
    }

    public CompoundTag writeTreeToTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("origin", origin == null ? new CompoundTag() : TendrilNode.blockPosToTag(origin));
        int i = 0;
        for (Map.Entry<BlockPos, TendrilNode> entry : nodes.entrySet()) {
            i++;
            tag.put(getIndexedKey(i), entry.getValue().toTag());
        }
        return tag;
    }

    public void addNode(BlockPos pos, BlockPos parentPos) {
        if (nodes.containsKey(pos)) return; // Node already exists
        if (nodes.containsKey(parentPos)) {
            if (!canHaveChildren(parentPos)) {
                return;
            }
            TendrilNode newNode = new TendrilNode(pos, nodes.get(parentPos));
            nodes.put(pos, newNode);
            this.addChildToNode(parentPos, pos);
        } else {
            LOGGER.warn("Tendril node at {} cannot be added because parent node at {} does not exist.",
                    pos, parentPos);

        }
    }

    public boolean hasNode(BlockPos pos) {return nodes.containsKey(pos);}

    private void addOrigin(BlockPos origin) {
        this.origin = origin;
        nodes.put(origin, new TendrilNode(origin, null));
    }

    private void addChildToNode(BlockPos parentPos, BlockPos childPos) {
        if (!nodes.containsKey(parentPos) || !nodes.containsKey(childPos)) {
            LOGGER.warn("Cannot add child node at {} to parent node at {} because one of them does not exist.",
                    childPos, parentPos);
            return;
        }
        nodes.get(parentPos).addChild(nodes.get(childPos));
    }

    // Length of the branch, from the origin to the tip of the longest branch
    private int getLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        TendrilNode node = nodes.get(pos);
        return getAscendingLength(node.getPosition()) + getDescendingLength(node.getPosition()) - 1;
    }

    public int getAscendingLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        int length = 1;

        TendrilNode currentNode = nodes.get(pos);
        while (currentNode.getParent() != null) {
            length++;
            currentNode = currentNode.getParent();
        }

        return length;
    }

    // Gets the length of the longest descending branch from this node
    public int getDescendingLength(BlockPos pos) {
        return this.getDescendingLength(pos, 0);
    }

    private int getDescendingLength(BlockPos pos, int depth) {
        if (!nodes.containsKey(pos) || depth > 100) return 0; // Should be impossible
        TendrilNode currentNode = nodes.get(pos);
        int maxLength = 0;
        for (TendrilNode child : currentNode.getChildren()) {
            int childLength = getDescendingLength(child.getPosition(), depth + 1);
            if (childLength > maxLength) {
                maxLength = childLength;
            }
        }
        return 1 + maxLength;
    }

    private TendrilNode getFirstNexusParent(BlockPos pos) {

        TendrilNode currentNode = nodes.get(pos);
        while (currentNode.getParent() != null && currentNode.getParent().getChildren().size() == 1) {
            currentNode = currentNode.getParent();
        }

        return currentNode;
    }

    private static String getIndexedKey(int i) {
        return "node_" + i;
    }

}
