package wardentools.worldgen.tree;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import wardentools.ModMain;
import wardentools.worldgen.tree.custom.DarktreeTrunkPlacer;
import wardentools.worldgen.tree.custom.WhitetreeTrunkPlacer;

import java.util.function.Supplier;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ModMain.MOD_ID);

    public static final Supplier<TrunkPlacerType<DarktreeTrunkPlacer>> DARKTREE_TRUNK_PLACER =
            TRUNK_PLACER.register("darktree_trunk_placer",
            		() -> new TrunkPlacerType<>(DarktreeTrunkPlacer.CODEC));
    
    public static final Supplier<TrunkPlacerType<WhitetreeTrunkPlacer>> WHITETREE_TRUNK_PLACER =
            TRUNK_PLACER.register("whitetree_trunk_placer",
            		() -> new TrunkPlacerType<>(WhitetreeTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}