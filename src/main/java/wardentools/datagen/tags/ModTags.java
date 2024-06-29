package wardentools.datagen.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import wardentools.ModMain;

public class ModTags {
    public static class Biomes {
        public static final TagKey<Biome> HAS_EXPLORER_HOUSE = register("has_abandonned_explorer_house");

        private static TagKey<Biome> register(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(ModMain.MOD_ID, name));
        }
    }
}
