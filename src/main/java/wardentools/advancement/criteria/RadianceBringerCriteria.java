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

public class RadianceBringerCriteria extends SimpleCriterionTrigger<RadianceBringerCriteria.TriggerInstance> {

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    @Override
    public @NotNull Codec<RadianceBringerCriteria.TriggerInstance> codec() {
        return RadianceBringerCriteria.TriggerInstance.CODEC;
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player)
            implements SimpleInstance {
        public static final Codec<RadianceBringerCriteria.TriggerInstance> CODEC
                = RecordCodecBuilder.create(instance -> {
            return instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                            .forGetter(RadianceBringerCriteria.TriggerInstance::player))
                    .apply(instance, RadianceBringerCriteria.TriggerInstance::new);
        });

        public static Criterion<RadianceBringerCriteria.TriggerInstance> choseRadiance() {
            return ModCriteriaTriggers.RADIANCE_BRINGER.createCriterion(
                    new RadianceBringerCriteria.TriggerInstance(Optional.empty()));
        }

        @Override
        public @NotNull Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
