package wardentools.worldgen.tree;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import wardentools.ModMain;
import wardentools.worldgen.tree.custom.DarktreeTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ModMain.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<DarktreeTrunkPlacer>> DARKTREE_TRUNK_PLACER =
            TRUNK_PLACER.register("darktree_trunk_placer",
            		() -> new TrunkPlacerType<>(DarktreeTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}