package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        while (!stop && i<= 100) {
            String key = getIndexedKey(i);
            if (tag.contains(key)) {
                TendrilNode node = new TendrilNode(tag.getCompound(key));
                nodes.put(node.getPosition(), node);
                if (node.getParent() != null) {
                    addNode(node.getPosition(), node.getParent().getPosition());
                } else {
                    addOrigin(node.getPosition());
                }
            } else stop = true;
            i++;
        }
    }

    public BlockPos getOrigin() {
        return origin;
    }

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
        return Math.max(1, Math.min(2 * MAX_LENGTH, (int) width)); // Width should be between 1 and 16
    }

    public boolean canHaveChildren(BlockPos pos) {
        return nodes.get(pos).getDepth() <= MAX_LENGTH;
    }

    public void recursiveRemove(BlockPos pos) { // Deletes the node at pos and all its children
        if (!nodes.containsKey(pos)) return;
        TendrilNode node = nodes.get(pos);
        for (TendrilNode child : node.getChildren()) {
            recursiveRemove(child.getPosition());
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
                System.out.println("Parent node at " + parentPos + " cannot have more children.");
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

    private int getAscendingLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        int length = 1;

        TendrilNode currentNode = nodes.get(pos);
        while (currentNode.getParent() !=null && currentNode.getParent().getChildren().size() == 1) {
            length++;
            currentNode = currentNode.getParent();
        }

        return length;
    }

    private int getDescendingLength(BlockPos pos) {
        if (!nodes.containsKey(pos)) return 0; // Should be impossible
        int length = 1;
        TendrilNode currentNode = nodes.get(pos);

        for (TendrilNode child : currentNode.getChildren()) {
            length += getDescendingLength(child.getPosition());
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
