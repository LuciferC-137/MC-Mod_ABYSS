package wardentools.advancement;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.advancement.criteria.AbyssPortalCriteria;
import wardentools.advancement.criteria.CorruptionVesselCriteria;
import wardentools.advancement.criteria.RadianceBringerCriteria;
import wardentools.advancement.criteria.SummonProtectorCriteria;

public class ModCriteriaTriggers {
    public static final AbyssPortalCriteria ABYSS_PORTAL_OPEN
            = register("abyss_portal_used", new AbyssPortalCriteria());
    public static final CorruptionVesselCriteria CORRUPTION_VESSEL
            = register("corruption_vessel", new CorruptionVesselCriteria());
    public static final RadianceBringerCriteria RADIANCE_BRINGER
            = register("radiance_bringer", new RadianceBringerCriteria());
    public static final SummonProtectorCriteria SUMMON_PROTECTOR
            = register("summon_protector", new SummonProtectorCriteria());

    public static void init() {
        // static call to register all criteria
    }

    public static <T extends CriterionTrigger<?>> T register(String id, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES,
                new ResourceLocation(ModMain.MOD_ID, id), trigger);
    }
}
