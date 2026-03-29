package wardentools.entity.thryssaryn.individual.behavior.sense;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;

public class Hearing extends Sense implements VibrationSystem {
    private final VibrationSystem.User vibrationUser;
    private final VibrationSystem.Data vibrationData;
    public final DynamicGameEventListener<Listener> dynamicGameEventListener;

    public Hearing(ThryssarynEntity entity) {
        super(entity);
        this.vibrationUser = new Hearing.ThryssarynHearingUser();
        this.vibrationData = new VibrationSystem.Data();
        this.dynamicGameEventListener = new DynamicGameEventListener<>(new
                VibrationSystem.Listener(this));

    }

    @Override
    public @NotNull Data getVibrationData() {
        return this.vibrationData;
    }

    @Override
    public @NotNull User getVibrationUser() {
        return this.vibrationUser;
    }

    class ThryssarynHearingUser implements VibrationSystem.User {
        private static final int LISTEN_RADIUS = 16;
        private final PositionSource positionSource
                = new EntityPositionSource(Hearing.this.entity, Hearing.this.entity.getEyeHeight());

        @Override
        public int getListenerRadius() {
            return LISTEN_RADIUS;
        }

        @Override
        public @NotNull PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public @NotNull TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.VIBRATIONS;
        }

        @Override
        public boolean canTriggerAvoidVibration() {
            return true;
        }

        @Override
        public boolean canReceiveVibration(@NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos,
                                           @NotNull Holder<GameEvent> holder,
                                           GameEvent.@NotNull Context context) {
            return true;
        }

        @Override
        public void onReceiveVibration(@NotNull ServerLevel level, @NotNull BlockPos blockPos,
                                       @NotNull Holder<GameEvent> holder, @Nullable Entity source,
                                       @Nullable Entity entity1, float v) {

        }

    }
}
