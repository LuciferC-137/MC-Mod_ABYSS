package wardentools.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import wardentools.ModMain;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CRISTAL_BLOCK = register("cristal_block");
        public static final TagKey<Block> ABYSS_TELEPORTABLE = register("abyss_teleportable");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
        }

    }

    public static class Items {
        public static final TagKey<Item> DEEPCRISTAL_REPAIRS = register("deepcristal_repairs");
        public static final TagKey<Item> RADIANCE_CRISTAL_REPAIRS = register("radiance_cristal_repairs");

        private static TagKey<Item> register(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
        }
    }
}
