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

public class CorruptionVesselCriteria extends SimpleCriterionTrigger<CorruptionVesselCriteria.TriggerInstance> {

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    @Override
    public @NotNull Codec<CorruptionVesselCriteria.TriggerInstance> codec() {
        return CorruptionVesselCriteria.TriggerInstance.CODEC;
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player)
            implements SimpleInstance {
        public static final Codec<CorruptionVesselCriteria.TriggerInstance> CODEC
                = RecordCodecBuilder.create(instance -> {
            return instance.group(
                    ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player")
                            .forGetter(CorruptionVesselCriteria.TriggerInstance::player)
            ).apply(instance, CorruptionVesselCriteria.TriggerInstance::new);
        });

        public static Criterion<CorruptionVesselCriteria.TriggerInstance> choseCorruption() {
            return ModCriteriaTriggers.CORRUPTION_VESSEL.createCriterion(
                    new CorruptionVesselCriteria.TriggerInstance(Optional.empty()));
        }

        @Override
        public @NotNull Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
