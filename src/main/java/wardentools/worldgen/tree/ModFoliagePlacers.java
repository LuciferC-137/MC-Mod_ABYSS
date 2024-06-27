
package wardentools.worldgen.tree;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.worldgen.tree.custom.DarktreeFoliagePlacer;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ModMain.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<DarktreeFoliagePlacer>> DARKTREE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("darktree_foliage_placer",
            		() -> new FoliagePlacerType<>(DarktreeFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
