package wardentools.advancement;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import wardentools.ModMain;
import wardentools.advancement.criteria.AbyssPortalCriteria;

public class ModCriteriaTriggers {
    public static final AbyssPortalCriteria ABYSS_PORTAL_OPEN
            = register("abyss_portal_used", new AbyssPortalCriteria());

    public static void init() {
        // static call to register all criteria
    }

    public static <T extends CriterionTrigger<?>> T register(String id, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES,
                new ResourceLocation(ModMain.MOD_ID, id), trigger);
    }
}
