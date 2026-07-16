package wardentools.advancement.criteria;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import wardentools.ModMain;
import wardentools.advancement.ModCriteriaTriggers;

import java.util.Optional;

public class SummonProtectorCriteria extends SimpleCriterionTrigger<SummonProtectorCriteria.TriggerInstance> {

    static final ResourceLocation ID = new ResourceLocation(ModMain.MOD_ID, "summon_protector_criteria");

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

        public static TriggerInstance summonProtector() {
            return new TriggerInstance(ContextAwarePredicate.ANY);
        }
    }
}
