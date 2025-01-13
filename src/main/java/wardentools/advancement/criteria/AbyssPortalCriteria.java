package wardentools.advancement.criteria;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import wardentools.advancement.ModCriteriaTriggers;

import java.util.Optional;

public class AbyssPortalCriteria extends SimpleCriterionTrigger<AbyssPortalCriteria.TriggerInstance> {

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    @Override
    public @NotNull Codec<AbyssPortalCriteria.TriggerInstance> codec() {
        return AbyssPortalCriteria.TriggerInstance.CODEC;
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player)
            implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<AbyssPortalCriteria.TriggerInstance> CODEC
                = RecordCodecBuilder.create(instance -> {
            return instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                    .forGetter(AbyssPortalCriteria.TriggerInstance::player))
                    .apply(instance, AbyssPortalCriteria.TriggerInstance::new);
        });

        public static Criterion<AbyssPortalCriteria.TriggerInstance> openPortal() {
            return ModCriteriaTriggers.ABYSS_PORTAL_OPEN.createCriterion(
                    new AbyssPortalCriteria.TriggerInstance(Optional.empty()));
        }

        @Override
        public @NotNull Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
