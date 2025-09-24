package wardentools.worldgen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.structure.Structure;

import javax.annotation.Nullable;

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
}
