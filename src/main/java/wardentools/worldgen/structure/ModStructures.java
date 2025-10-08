package wardentools.worldgen.structure;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import wardentools.ModMain;

public class ModStructures {
    public static final ResourceKey<Structure> SURFACE_ANCIENT_CITY
            = createKey("surface_ancient_city");
    public static final ResourceKey<Structure> ANCIENT_CITADEL
            = createKey("ancient_citadel");
    public static final ResourceKey<Structure> AMETHYST_TEMPLE
            = createKey("amethyst_temple");
    public static final ResourceKey<Structure> RUBY_TEMPLE
            = createKey("ruby_temple");
    public static final ResourceKey<Structure> CITRINE_TEMPLE
            = createKey("citrine_temple");
    public static final ResourceKey<Structure> MALACHITE_TEMPLE
            = createKey("malachite_temple");
    public static final ResourceKey<Structure> ECHO_TEMPLE
            = createKey("echo_temple");
    public static final ResourceKey<Structure> PALE_TEMPLE
            = createKey("pale_temple");

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }
}
