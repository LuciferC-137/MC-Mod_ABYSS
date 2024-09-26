package wardentools.tags;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import wardentools.ModMain;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CRISTAL_BLOCK = register("cristal_block");
        public static final TagKey<Block> ABYSS_TELEPORTABLE = register("abyss_teleportable");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(new ResourceLocation(ModMain.MOD_ID, name));
        }

    }
}
