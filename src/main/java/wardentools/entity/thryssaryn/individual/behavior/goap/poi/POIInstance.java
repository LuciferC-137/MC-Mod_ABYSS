package wardentools.entity.thryssaryn.individual.behavior.goap.poi;

import net.minecraft.core.BlockPos;

public record POIInstance(
        BlockPos pos,
        POIType type,
        boolean visited,
        long lastSeenTick
) {}