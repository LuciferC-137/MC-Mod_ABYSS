
package wardentools.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.worldgen.tree.custom.DarktreeFoliagePlacer;
import wardentools.worldgen.tree.custom.WhitetreeFoliagePlacer;

import java.util.function.Supplier;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ModMain.MOD_ID);

    public static final Supplier<FoliagePlacerType<DarktreeFoliagePlacer>> DARKTREE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("darktree_foliage_placer",
            		() -> new FoliagePlacerType<>(DarktreeFoliagePlacer.CODEC));
    
    public static final Supplier<FoliagePlacerType<WhitetreeFoliagePlacer>> WHITETREE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("whitetree_foliage_placer",
            		() -> new FoliagePlacerType<>(WhitetreeFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
