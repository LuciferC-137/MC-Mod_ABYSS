package wardentools.worldgen.structure;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.structure.Structure;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class StructureUtils {

    public static @Nullable BlockPos findNearestStructure(ServerLevel level,
                                                          ResourceKey<Structure> structureKey,
                                                          BlockPos pos) {
        Holder<Structure> structureHolder = level.registryAccess()
                .registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(structureKey);
        HolderSet<Structure> structureHolderSet = HolderSet.direct(structureHolder);
        var result = level.getChunkSource().getGenerator()
                .findNearestMapStructure(level, structureHolderSet, pos, 10000, false);
        return result != null ? result.getFirst() : null;
    }

    public static boolean isInsideAnyCrystalTemple(WorldGenLevel level, BlockPos origin) {
        StructureManager structureManager = level.getLevel().structureManager();
        Map<Structure, LongSet> structures = structureManager.getAllStructuresAt(origin);

        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);

        for (Map.Entry<Structure, LongSet> entry : structures.entrySet()) {
            Structure structure = entry.getKey();

            Optional<Holder.Reference<Structure>> holderOpt = registry.getHolder(registry.getId(structure));
            if (holderOpt.isEmpty()) continue;

            Holder<Structure> holder = holderOpt.get();

            if (isCrystalTempleHolder(holder)) {
                AtomicBoolean inside = new AtomicBoolean(false);
                structureManager.fillStartsForStructure(structure, entry.getValue(), (start) -> {
                    if (!start.isValid()) return;
                    if (start.getBoundingBox().isInside(origin)
                            || structureManager.structureHasPieceAt(origin, start)) {
                        inside.set(true);
                    }
                });
                if (inside.get()) return true;
            }
        }

        return false;
    }

    public static boolean isCrystalTempleHolder(Holder<Structure> holder) {
        return holder.is(ModStructures.AMETHYST_TEMPLE)
                || holder.is(ModStructures.RUBY_TEMPLE)
                || holder.is(ModStructures.CITRINE_TEMPLE)
                || holder.is(ModStructures.MALACHITE_TEMPLE)
                // || holder.is(ModStructures.ECHO_TEMPLE) // Still having sculk in echo temples ?
                || holder.is(ModStructures.PALE_TEMPLE);
    }



}
