package wardentools.block.sculktendril;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TendrilNode {
    private final BlockPos position;
    private final ArrayList<TendrilNode> children;
    @Nullable private TendrilNode parent = null;
    private int depth = 1;


    public TendrilNode(BlockPos position, @Nullable TendrilNode parent, TendrilNode... children) {
        this.position = position;
        this.children = new ArrayList<>(List.of(children));
        if (parent != null) {
            this.parent = parent;
            this.depth = parent.getDepth() + 1;
        }
    }

    public TendrilNode(BlockPos position, int depth) {
        this.position = position;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public int getDepth() {return depth;}

    public void setDepth(int depth) {this.depth = depth;}

    public BlockPos getPosition() {return position;}

    public List<TendrilNode> getChildren() {return children;}

    public void addChild(TendrilNode child) {
        if (children.contains(child)) return; // Already a child
        children.add(child);
        child.setDepth(this.depth + 1); // Update the depth of the child
    }

    @Nullable public TendrilNode getParent() {return parent;}

    public void setParent(@Nullable TendrilNode parent) {this.parent = parent;}

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("position", blockPosToTag(position));
        tag.putInt("depth", depth);
        if (parent != null) {
            tag.put("parent", blockPosToTag(parent.getPosition()));
        }
        return tag;
    }

    public static CompoundTag blockPosToTag(BlockPos pos) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        return tag;
    }

    public static BlockPos blockPosFromTag(CompoundTag tag) {
        return new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    }
}
