package wardentools.entity.thryssaryn.individual.behavior.goap.poi;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import wardentools.utils.TaggableEnum;

import java.util.*;
import java.util.stream.Collectors;

public enum POIType implements TaggableEnum {
    FARM(Set.of(Items.WHEAT, Items.CARROT, Items.POTATO)), //TODO placeholder
    ;

    private final Set<Item> associatedItems;
    private final Set<Block> associatedBlocks;

    POIType(Set<Item> items) {
        this.associatedItems = Set.copyOf(items);
        this.associatedBlocks = Set.of();
    }

    POIType(Set<Item> items, Set<Block> blocks) {
        this.associatedItems = Set.copyOf(items);
        this.associatedBlocks = Set.copyOf(blocks);
    }

    public boolean canContain(Item item) {
        return associatedItems.contains(item);
    }

    public boolean canContain(Block block) {return associatedBlocks.contains(block);}

    /** Return all POI that can contain this item */
    public static Set<POIType> typesFor(Item item) {
        return Arrays.stream(values())
                .filter(t -> t.canContain(item))
                .collect(Collectors.toSet());
    }

    /** Return all POI that can contain this block */
    public static Set<POIType> typesFor(Block block) {
        return Arrays.stream(values())
                .filter(t -> t.canContain(block))
                .collect(Collectors.toSet());
    }

    public static POIType fromTag(String tag) {
        return TaggableEnum.fromTag(POIType.class, tag);
    }
}
