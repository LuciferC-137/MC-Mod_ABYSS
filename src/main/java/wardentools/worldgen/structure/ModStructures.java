package wardentools.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import wardentools.ModMain;

public class ModStructures {
    public static final ResourceKey<Structure> SURFACE_ANCIENT_CITY
            = createKey(ModMain.MOD_ID, "surface_ancient_city");

    private static ResourceKey<Structure> createKey(String mod_id, String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(mod_id, name));
    }
}
