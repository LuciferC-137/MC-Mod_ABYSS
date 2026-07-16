package wardentools.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import wardentools.ModMain;
import wardentools.advancement.criteria.AbyssPortalCriteria;
import wardentools.advancement.criteria.CorruptionVesselCriteria;
import wardentools.advancement.criteria.RadianceBringerCriteria;
import wardentools.advancement.criteria.SummonProtectorCriteria;

public class ModCriteriaTriggers {
    public static final AbyssPortalCriteria ABYSS_PORTAL_OPEN = new AbyssPortalCriteria();
    public static final CorruptionVesselCriteria CORRUPTION_VESSEL = new CorruptionVesselCriteria();
    public static final RadianceBringerCriteria RADIANCE_BRINGER = new RadianceBringerCriteria();
    public static final SummonProtectorCriteria SUMMON_PROTECTOR = new SummonProtectorCriteria();

    public static void init() {
        // static call to register all criteria
    }

    public static void register() {
        CriteriaTriggers.register(ABYSS_PORTAL_OPEN);
        CriteriaTriggers.register(CORRUPTION_VESSEL);
        CriteriaTriggers.register(RADIANCE_BRINGER);
        CriteriaTriggers.register(SUMMON_PROTECTOR);
    }
}
