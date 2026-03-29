package wardentools.entity.thryssaryn.individual.behavior;

import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import wardentools.entity.thryssaryn.individual.ThryssarynEntity;
import wardentools.entity.thryssaryn.individual.behavior.sense.Hearing;
import wardentools.entity.thryssaryn.individual.behavior.sense.Sight;

public class Brain {
    private final Hearing hearing;
    private final Sight sight;
    private ThryssarynEntity entity;

    public Brain(ThryssarynEntity entity) {
        this.hearing = new Hearing(entity);
        this.sight = new Sight(entity);
    }

    public Hearing getHearing() {
        return hearing;
    }

    public Sight getSight() {
        return sight;
    }

    public void tick() {
       if (this.entity.level().isClientSide) {
           clientTick();
       } else {
           serverTick();
       }
    }

    public void serverTick() {
        VibrationSystem.Ticker.tick(this.entity.level(),
                this.hearing.getVibrationData(), this.hearing.getVibrationUser());
    }

    public void clientTick() {

    }
}
