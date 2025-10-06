package wardentools.tags;

import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import wardentools.ModMain;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CRISTAL_BLOCK = register("cristal_block");
        public static final TagKey<Block> ABYSS_TELEPORTABLE = register("abyss_teleportable");
        public static final TagKey<Block> CAN_SUSTAIN_ABYSS_PLANTS = register("can_sustain_abyss_plants");
        public static final TagKey<Block> CONNECT_TO_TENDRILS_BLOCKS = register("connect_to_tendrils_blocks");
        public static final TagKey<Block> SUSTAIN_LIVING_SPROUT = register("sustain_living_sprout");

        private static TagKey<Block> register(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
        }

    }

    public static class Biomes {
        public static final TagKey<Biome> IS_ABYSS = register("is_abyss");
        public static final TagKey<Biome> WHITE_FOREST = register("white_forest");
        public static final TagKey<Biome> DEEPFOREST = register("deepforest");
        public static final TagKey<Biome> CRYSTAL_CAVES = register("crystal_caves");
        public static final TagKey<Biome> AMETHYST_CAVES = register("amethyst_caves");
        public static final TagKey<Biome> RUBY_CAVES = register("ruby_caves");
        public static final TagKey<Biome> CITRINE_CAVES = register("citrine_caves");
        public static final TagKey<Biome> MALACHITE_CAVES = register("malachite_caves");
        public static final TagKey<Biome> ECHO_CAVES = register("echo_caves");
        public static final TagKey<Biome> PALE_CAVES = register("pale_caves");

        private static TagKey<Biome> register(String name) {
            return BiomeTags.create(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
        }
    }
}
