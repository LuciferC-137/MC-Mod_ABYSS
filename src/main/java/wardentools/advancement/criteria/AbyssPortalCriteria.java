package wardentools.advancement.criteria;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;

public class AbyssPortalCriteria extends SimpleCriterionTrigger<AbyssPortalCriteria.TriggerInstance> {

    static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "abyss_portal_open");

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject json,
                                                   @NotNull ContextAwarePredicate player,
                                                   @NotNull DeserializationContext context) {
        return new TriggerInstance(player);
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ContextAwarePredicate player) {
            super(ID, player);
        }

        public static TriggerInstance openPortal() {
            return new TriggerInstance(ContextAwarePredicate.ANY);
        }
    }
}
