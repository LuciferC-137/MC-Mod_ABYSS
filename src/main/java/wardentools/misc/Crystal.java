package wardentools.misc;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;
import wardentools.block.BlockRegistry;
import wardentools.items.ItemRegistry;
import wardentools.worldgen.structure.ModStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum Crystal implements StringRepresentable {
    AMETHYST(0, 0x8e42f6,
            () -> Blocks.AMETHYST_BLOCK,
            () -> Blocks.AMETHYST_CLUSTER,
            () -> Items.AMETHYST_SHARD,
            ItemRegistry.PENDANT_OF_BALANCE,
            ModStructures.AMETHYST_TEMPLE),
    RUBY(1, 0xed2525,
            BlockRegistry.RUBY_BLOCK,
            BlockRegistry.RUBY,
            ItemRegistry.RUBY_FRAGMENT,
            ItemRegistry.STRENGTH_BRACELET,
            ModStructures.RUBY_TEMPLE),
    CITRINE(2, 0xffc200,
            BlockRegistry.CITRINE_BLOCK,
            BlockRegistry.CITRINE,
            ItemRegistry.CITRINE_FRAGMENT,
            ItemRegistry.MIND_TIARA,
            ModStructures.CITRINE_TEMPLE),
    MALACHITE(3, 0x10b058,
            BlockRegistry.MALACHITE_BLOCK,
            BlockRegistry.MALACHITE,
            ItemRegistry.MALACHITE_FRAGMENT,
            ItemRegistry.RING_OF_WILL,
            ModStructures.MALACHITE_TEMPLE),
    ECHO(4, 0x244b69,
            BlockRegistry.ECHO_BLOCK,
            BlockRegistry.ECHO_CRISTAL,
            () -> Items.ECHO_SHARD,
            ItemRegistry.SHADOW_ORNAMENT,
            ModStructures.ECHO_TEMPLE),
    PALE(5, 0x1be4eb,
            BlockRegistry.PALE_CRISTAL_BLOCK,
            BlockRegistry.PALE_CRISTAL,
            ItemRegistry.PALE_SHARD,
            ItemRegistry.LIGHT_ORNAMENT,
            ModStructures.PALE_TEMPLE),;

    private static class LazyMaps {
        private static final Map<Integer, Crystal> BY_INDEX = buildIndexMap();
        private static final Map<Block, Crystal> BY_BLOCK = buildBlockMap();
        private static final Map<Block, Crystal> BY_BUD   = buildBudMap();
        private static final Map<Item, Crystal>  BY_ITEM  = buildItemMap();
        private static final Map<Item, Crystal> BY_JEWEL = buildJewelMap();

        private static Map<Integer, Crystal> buildIndexMap() {
            Map<Integer, Crystal> map = new HashMap<>();
            for (Crystal c : values()) map.put(c.index, c);
            return map;
        }

        private static Map<Block, Crystal> buildBlockMap() {
            Map<Block, Crystal> map = new HashMap<>();
            for (Crystal c : values()) map.put(c.getCrystalBlock(), c);
            return map;
        }

        private static Map<Block, Crystal> buildBudMap() {
            Map<Block, Crystal> map = new HashMap<>();
            for (Crystal c : values()) map.put(c.getCrystalBud(), c);
            return map;
        }

        private static Map<Item, Crystal> buildItemMap() {
            Map<Item, Crystal> map = new HashMap<>();
            for (Crystal c : values()) map.put(c.getShard(), c);
            return map;
        }

        private static Map<Item, Crystal> buildJewelMap() {
            Map<Item, Crystal> map = new HashMap<>();
            for (Crystal c : values()) map.put(c.getJewel(), c);
            return map;
        }
    }

    private final int index;
    private final int color;
    private final Supplier<Block> crystalBlock;
    private final Supplier<Block> crystalBud;
    private final Supplier<Item> shard;
    private final Supplier<Item> jewel;
    private final ResourceKey<Structure> templeKey;

    Crystal(int index, int color, Supplier<Block> block, Supplier<Block> bud,
            Supplier<Item> shard, Supplier<Item> jewel, ResourceKey<Structure> templeKey) {
        this.index = index;
        this.color = color;
        this.crystalBlock = block;
        this.crystalBud = bud;
        this.shard = shard;
        this.jewel = jewel;
        this.templeKey = templeKey;
    }

    public Block getCrystalBlock() { return crystalBlock.get(); }
    public Block getCrystalBud() { return crystalBud.get(); }
    public Item getShard() { return shard.get(); }
    public Item getJewel() { return jewel.get(); }
    public ResourceKey<Structure> getTempleKey() { return templeKey; }

    public int getIndex() { return index; }
    public int getColor() { return color; }
    public int getColorARGB() { return 0xFF000000 | color;}
    public float getRed() {return ((color >> 16) & 0xFF) / 255F;}
    public float getGreen() {return ((color >> 8) & 0xFF) / 255F;}
    public float getBlue() {return (color & 0xFF) / 255F;}

    public static Crystal fromIndex(int index) {
        return LazyMaps.BY_INDEX.getOrDefault(index, getDefault());
    }
    public static Crystal fromBlock(Block block) {
        return LazyMaps.BY_BLOCK.getOrDefault(block, getDefault());
    }
    public static Crystal fromBud(Block bud) {
        return LazyMaps.BY_BUD.getOrDefault(bud, getDefault());
    }
    public static Crystal fromItem(Item item) {
        return LazyMaps.BY_ITEM.getOrDefault(item, getDefault());
    }
    public static Crystal fromJewel(Item item) {
        return LazyMaps.BY_JEWEL.getOrDefault(item, getDefault());
    }
    public static boolean isCrystalItem(Item item) {
        return LazyMaps.BY_ITEM.containsKey(item);
    }
    public static boolean isCrystalJewel(Item item) {
        return LazyMaps.BY_JEWEL.containsKey(item);
    }

    public static Crystal getDefault() { return AMETHYST; }

    public Crystal getNext() {
        return fromIndex((this.index + 1) % values().length);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase();
    }
}

