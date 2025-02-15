package wardentools.advancement.criteria;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;
import wardentools.advancement.ModCriteriaTriggers;

import java.util.Optional;

public class SummonProtectorCriteria extends SimpleCriterionTrigger<SummonProtectorCriteria.TriggerInstance> {

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    @Override
    public @NotNull Codec<SummonProtectorCriteria.TriggerInstance> codec() {
        return SummonProtectorCriteria.TriggerInstance.CODEC;
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player)
            implements SimpleInstance {
        public static final Codec<SummonProtectorCriteria.TriggerInstance> CODEC
                = RecordCodecBuilder.create(instance -> {
            return instance.group(
                    ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player")
                            .forGetter(SummonProtectorCriteria.TriggerInstance::player)
            ).apply(instance, SummonProtectorCriteria.TriggerInstance::new);
        });

        public static Criterion<SummonProtectorCriteria.TriggerInstance> summonProtector() {
            return ModCriteriaTriggers.SUMMON_PROTECTOR.createCriterion(
                    new SummonProtectorCriteria.TriggerInstance(Optional.empty()));
        }

        @Override
        public @NotNull Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
