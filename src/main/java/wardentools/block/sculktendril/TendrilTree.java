package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class TendrilTree {
    private static final int MAX_LENGTH = 8; // Maximum depth of the tendril tree. This is also used to compute the physical width.
    private BlockPos origin = null;
    private final Map<BlockPos, TendrilNode> nodes = new HashMap<>();

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
        float freeDepth = node.getDepth() - getFirstNexusParent(pos).getDepth() + 1;
        float width = (float) Math.ceil((float) MAX_LENGTH / (length + 1f) * (length - freeDepth + 1f));
        return 2 * Math.max(1, Math.min(MAX_LENGTH, (int) width)); // Width should be between 2 and 16
    }

    public boolean canHaveChildren(BlockPos pos) {
        return nodes.get(pos).getDepth() <= MAX_LENGTH;
    }

    public void recursiveRemove(BlockPos pos) {
        // Deletes the node at pos and all its children
        this.recursiveRemove(pos, 0);
    }

    public void recursiveRemove(BlockPos pos, int depth) {
        if (!nodes.containsKey(pos) || depth > 100) return;
        TendrilNode node = nodes.get(pos);
        for (TendrilNode child : node.getChildren()) {
            recursiveRemove(child.getPosition(), depth + 1);
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
        if (nodes.containsKey(parentPos)) {
            if (!canHaveChildren(parentPos)) {
                return;
            }
            TendrilNode newNode = new TendrilNode(pos, nodes.get(parentPos));
            nodes.put(pos, newNode);
            addChildToNode(parentPos, pos);
        } else {
            System.out.println("Tendril node at " + pos
                    + " cannot be added because parent node at " + parentPos + " does not exist.");
        }
    }

    public boolean hasNode(BlockPos pos) {return nodes.containsKey(pos);}

    public int getTotalDepth(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        TendrilNode node = nodes.get(pos);
        return node.getDepth();
    }

    private void addOrigin(BlockPos origin) {
        this.origin = origin;
        nodes.put(origin, new TendrilNode(origin, null));
    }

    private void addChildToNode(BlockPos parentPos, BlockPos childPos) {
        if (!nodes.containsKey(parentPos) || !nodes.containsKey(childPos)) {
            System.out.println("Cannot add child node at " + childPos + " to parent node at " + parentPos
                    + " because one of them does not exist.");
            return;
        }
        nodes.get(parentPos).addChild(nodes.get(childPos));
    }

    private int getLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        TendrilNode node = nodes.get(pos);
        return getAscendingLength(node.getPosition()) + getDescendingLength(node.getPosition()) - 1;
    }

    public int getAscendingLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        int length = 1;

        TendrilNode currentNode = nodes.get(pos);
        while (currentNode.getParent() !=null && currentNode.getParent().getChildren().size() == 1) {
            length++;
            currentNode = currentNode.getParent();
        }

        return length;
    }

    public int getDescendingLength(BlockPos pos) {
        return this.getDescendingLength(pos, 0);
    }

    private int getDescendingLength(BlockPos pos, int depth) {
        if (!nodes.containsKey(pos) || depth > 100) return 0; // Should be impossible
        int length = 1;
        TendrilNode currentNode = nodes.get(pos);

        for (TendrilNode child : currentNode.getChildren()) {
            length += getDescendingLength(child.getPosition(), depth + 1);
        }
        return length;
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
